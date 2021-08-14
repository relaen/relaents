import { Connection } from "../../connection";
import { IEntityCfg } from "../../types";
import { BaseProvider } from "../../baseprovider";
import { EntityFactory } from "../../entityfactory";
import { EntityManager } from "../../entitymanager";
import { NativeQuery } from "../../nativequery";
import { TransactionManager } from "../../transactionmanager";
import { IMssqlConnectionCfg } from "./mssqloptions";
import { ErrorFactory } from "../../errorfactory";

/**
 * mssql provider
 * @since 0.3.0
 */
export class MssqlProvider extends BaseProvider {
    /**
     * 构造器
     * @param cfg   连接配置
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
     * @param connection    数据库连接对象
     */
    public async closeConnection(connection: Connection) {
        if (!this.pool) {
            await connection.conn.close();
        }
        return null;
    }

    /**
     * 执行sql语句
     * @param connection    db connection
     * @param sql           待执行sql
     * @param params        参数数组
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: any[]): Promise<any> {
        let request; //用request作为sql执行器
        //如果事务存在，则通过事务获取request，否则通过connection获取
        let tr = TransactionManager.get();
        if (tr) {
            request = tr.tr.request();
        } else {
            request = connection.conn.request();
        }
        params = params || [];
        params.forEach((value, index) => {
            request.input(index.toString(), value);
        });
        let result = await request.query(sql);
        return result.recordset;
    }

    /**
     * 处理记录起始记录索引和记录数
     * @param sql       sql
     * @param start     开始索引
     * @param limit     记录数
     * @returns         处理后的sql
     */
    public handleStartAndLimit(sql: string, start?: number, limit?: number): string {
        if (!Number.isInteger(start) || start < 0 || !Number.isInteger(limit) || limit <= 0) {
            return sql;
        }
        //无order by 则需要添加
        if (!/order\s+by/i.test(sql)) {
            let r = /from\s+\w+/i.exec(sql);
            if (!r) {
                return sql;
            }
            let tbl = r[0].replace(/from\s+/i, '');
            let t0 = /from\s+\w+\s+t0/i.test(sql);
            let cfg: IEntityCfg = EntityFactory.getEntityCfgByTblName(tbl);
            sql += ' ORDER BY ' + (t0 ? 't0.' : '') + cfg.columns.get(cfg.id.name).name + ' ASC ';
        }
        return sql + ' OFFSET ' + start + ' ROWS FETCH NEXT ' + limit + ' ROWS ONLY';
    }

    /**
     * 获取实体sequence，针对主键生成策略为sequence时有效
     * @param em        entity manager
     * @param seqName   sequence name
     * @param schema    schema
     * @returns         sequence 值
     */
    public async getSequenceValue(em: EntityManager, seqName: string, schema?: string): Promise<number> {
        let query: NativeQuery = em.createNativeQuery("select next value for " + seqName);
        let r = await query.getResult();
        if (r) {
            //转换为整数
            return parseInt(r);
        }
        return 0;
    }

    /**
     * 从sql执行结果获取identityid，仅对主键生成策略是identity的有效
     * @param result    sql执行结果
     * @returns         主键
     */
    public getIdentityId(result: any): any {
        if (!result || result.length > 1 || !result[0].insertId) {
            return;
        }
        return result[0].insertId;
    }

    /**
     * 加表锁
     */
    public lockTable(table: string, schema?: string): string {
        return "select * from " + (schema ? schema + "." + table : table) + " with (tablockx)";
    }

}