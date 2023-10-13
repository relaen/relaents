import { Connection } from "../../connection";
import { BaseProvider } from "../../baseprovider";
import { EntityManager } from "../../entitymanager";
import { NativeQuery } from "../../nativequery";
import { ELockType } from "../../types";
import { PostgresConnectionOption } from "./postgresoptions";

/**
 * postgres provider
 */
export class PostgresProvider extends BaseProvider {
    /**
     * 构造器
     * @param cfg -   连接配置
     */
    constructor(cfg: PostgresConnectionOption) {
        super(cfg);
        this.dbMdl = require('pg');
        this.options = cfg.options ? cfg.options : {
            user: cfg.username,
            password: cfg.password,
            host: cfg.host,
            port: cfg.port,
            database: cfg.database,
            ssl: cfg.ssl,
            connectionTimeoutMillis: cfg.connectTimeout,
        };

        // 连接池
        if (cfg.usePool || cfg.pool) {
            if (!cfg.options && cfg.pool) {
                this.options['max'] = cfg.pool.max;
                this.options['idleTimeoutMillis'] = cfg.idleTimeout;
            }
            this.pool = new this.dbMdl.Pool(this.options);
        }
    }

    /**
     * 获取postgres连接
     * @returns     数据库连接
     */
    public async getConnection(): Promise<any> {
        if (this.pool) {
            return await this.pool.connect();
        }
        const conn = new this.dbMdl.Client(this.options);
        await conn.connect();
        return conn;
    }

    /**
     * 关闭postgres连接
     * @param connection -    数据库连接对象
     */
    public async closeConnection(connection: Connection) {
        if (this.pool) {
            await connection.conn.release();
        } else {
            await connection.conn.end();
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
    public async exec(connection: Connection, sql: string, params?: unknown[]): Promise<unknown> {
        const r = await connection.conn.query(sql, params);
        return r.rows ? r.rows : r;
    }

    /**
     * 处理记录起始记录索引和记录数
     * @param sql -       sql
     * @param start -     开始索引
     * @param limit -     记录数
     * @returns         处理后的sql
     */
    public handleStartAndLimit(sql: string, start?: number, limit?: number): string {
        if (limit && start) {
            return sql + ' LIMIT ' + limit + ' OFFSET ' + start;
        }
        if (limit) {
            return sql + ' LIMIT ' + limit;
        }
        if (start) {
            return sql + ' OFFSET ' + start;
        }
        return sql;
    }

    /**
     * 获取实体sequence，针对主键生成策略为sequence时有效
     * @param em -        entity manager
     * @param seqName -   sequence name
     * @param schema -    schema
     * @returns         sequence 值
     */
    public async getSequenceValue(em: EntityManager, seqName: string, schema?: string): Promise<number> {
        // 需要指定sequence所属schema
        const query: NativeQuery = em.createNativeQuery(
            "SELECT NEXTVAL('" + (schema ? schema + "." + seqName : seqName) + "')"
        );
        const r = await query.getResult();
        if (r) {
            //转换为整数
            return parseInt(<string>r);
        }
        return 0;
    }

    /**
     * 从sql执行结果获取identityid，仅对主键生成策略是identity的有效
     * @param result -  sql执行结果
     * @returns         主键
     */
    public getIdentityId(result: unknown[]): number {
        if (!result || result.length > 1) {
            return;
        }
        return <number>Object.values(result[0])[0];
    }

    /**
     * 获取加锁sql语句
     * @param type -    锁类型    
     * @param tables -  表名，表锁时使用
     * @param schema -  模式名，表锁时使用
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
                return "LOCK TABLE " + tables.join() + " IN SHARE MODE";
            case 'table_write':
                return "LOCK TABLE " + tables.join() + " IN EXCLUSIVE MODE";
            case 'row_read':
                return "FOR SHARE";
            case 'row_write':
                return "FOR UPDATE";
            default:
                return '';
        }
    }

    /**
     * 获取新增返回主键字段sql语句
     * @param idField - 主键字段
     */
    public insertReturn(idField: string): string {
        return 'RETURNING ' + idField;
    }
}