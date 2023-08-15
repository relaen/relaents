import { Connection } from "../../connection";
import { ELockType } from "../../types";
import { BaseProvider } from "../../baseprovider";
import { EntityFactory } from "../../entityfactory";
import { EntityManager } from "../../entitymanager";
import { NativeQuery } from "../../nativequery";
import { TransactionManager } from "../../transactionmanager";
import { IMssqlConnectionCfg } from "./mssqloptions";
import { EntityConfig } from "../../entityconfig";

/**
 * mssql provider
 */
export class MssqlProvider extends BaseProvider {
    /**
     * 构造器
     * @param cfg -   连接配置
     */
    constructor(cfg: IMssqlConnectionCfg) {
        super(cfg);
        this.dbMdl = require('mssql');
        this.options = cfg.options ? cfg.options : {
            server: cfg.host,
            port: cfg.port,
            user: cfg.username,
            password: cfg.password,
            database: cfg.database,
            connectionTimeout: cfg.connectTimeout,
            requestTimeout: cfg.requestTimeout,
            options: {
                trustServerCertificate: true,
            }
        };

        // 连接池
        if (cfg.usePool || cfg.pool) {
            if (!cfg.options && cfg.pool) {
                this.options['pool'] = {
                    max: cfg.pool.max,
                    min: cfg.pool.min,
                    idleTimeoutMillis: cfg.idleTimeout
                }
            }
            this.pool = new this.dbMdl.ConnectionPool(this.options);
            console.log(this.pool.totalCount);
        }
    }

    /**
     * 获取连接
     * @returns     数据库连接
     */
    public async getConnection(): Promise<any> {
        if (this.pool) {
            return await this.pool.connect();
        }
        return await this.dbMdl.connect(this.options);
    }

    /**
     * 关闭连接
     * @param connection -    数据库连接对象
     */
    public async closeConnection(connection: Connection) {
        if (!this.pool) {
            await connection.conn.close();
        }
        return null;
    }

    /**
     * 执行sql语句
     * @param connection -    db connection
     * @param sql -           待执行sql
     * @param params -        参数数组
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: unknown[] | object): Promise<unknown> {
        let request; //用request作为sql执行器
        //如果事务存在，则通过事务获取request，否则通过connection获取
        const tr = TransactionManager.get();
        if (tr) {
            request = tr['tr'].request();
        } else {
            request = connection.conn.request();
        }
        params = params || [];
        // 绑定数组或对象参数
        if (Array.isArray(params)) {
            params.forEach((value, index) => {
                request.input(index.toString(), value);
            });
        } else {
            Object.getOwnPropertyNames(params).forEach((item) => {
                request.input(item, params[item]);
            })
        }
        const result = await request.query(sql);
        return result.recordset;
    }

    /**
     * 处理记录起始记录索引和记录数
     * @param sql -       sql
     * @param start -     开始索引
     * @param limit -     记录数
     * @returns         处理后的sql
     */
    public handleStartAndLimit(sql: string, start?: number, limit?: number): string {
        //mssql 需要orderby，offset fetch同时使用
        handleOrder();
        if (start && limit) {
            return sql + " OFFSET " + start + " ROWS FETCH NEXT " + limit + " ROWS ONLY";
        }
        if (limit) {
            return sql + " OFFSET 0 ROWS FETCH NEXT " + limit + " ROWS ONLY";
        }
        if (start) {
            return sql + " OFFSET " + start + " ROWS";
        }
        return sql;

        //无order by 则需要添加
        function handleOrder() {
            if (!/order\s+by/i.test(sql)) {
                const r = /from\s+\w+/i.exec(sql);
                if (!r) {
                    return sql;
                }
                const tbl = r[0].replace(/from\s+/i, '');
                const t0 = /from\s+\w+\s+t0/i.test(sql);
                let orderBy = '(SELECT NULL)';
                const cfg: EntityConfig = EntityFactory.getEntityCfgByTblName(tbl);
                if (cfg) {
                    orderBy = (t0 ? 't0.' : '') + cfg.columns.get(cfg.id.name).name + ' ASC';
                }
                sql += ' ORDER BY ' + orderBy;
            }
        }
    }

    /**
     * 获取实体sequence，针对主键生成策略为sequence时有效
     * @param em -        entity manager
     * @param seqName -   sequence name
     * @param schema -    schema
     * @returns         sequence 值
     */
    public async getSequenceValue(em: EntityManager, seqName: string, schema?: string): Promise<number> {
        const query: NativeQuery = em.createNativeQuery("select next value for " + seqName);
        const r = await query.getResult();
        if (r) {
            //转换为整数
            return parseInt(<string>r);
        }
        return 0;
    }

    /**
     * 从sql执行结果获取identityid，仅对主键生成策略是identity的有效
     * @param result -    sql执行结果
     * @returns         主键
     */
    public getIdentityId(result: unknown[]): number {
        if (!result || result.length > 1 || !result[0]['insertId']) {
            return;
        }
        return result[0]['insertId'];
    }

    /**
     * 获取加锁sql语句
     * @param type -      锁类型    
     * @param tables -    表名，表锁时使用
     * @param schema -    模式名，表锁时使用
     * @returns         加锁sql语句
    */
    public lock(type: ELockType, tables?: string[], schema?: string): string {
        if (schema && tables) {
            tables.forEach((v, i) => {
                tables[i] = schema + '.' + tables[i];
            });
        }
        switch (type) {
            case 'table_read':
                return "SELECT * FROM " + tables.join() + " WITH (HOLDLOCK,TABLOCK)";
            case 'table_write':
                return "SELECT * FROM " + tables.join() + " WITH (UPDLOCK,TABLOCK)";
            case 'row_read':
                return "WITH(HOLDLOCK,ROWLOCK)";
            case 'row_write':
                return "WITH(UPDLOCK,ROWLOCK)";
        }
    }

    /**
     * 获取新增返回主键字段sql语句
     * @param idField -   主键字段
     * @returns         查询主键sql语句
     */
    public insertReturn():string {
        return "SELECT @@IDENTITY AS insertId";
    }

}