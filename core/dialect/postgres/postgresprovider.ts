import { Connection } from "../../connection";
import { BaseProvider } from "../../baseprovider";
import { EntityManager } from "../../entitymanager";
import { NativeQuery } from "../../nativequery";
import { IPostgresConnectionCfg } from "./postgresoptions";

/**
 * postgres provider
 * @since 0.3.0
 */
export class PostgresProvider extends BaseProvider {
    /**
     * 构造器
     * @param cfg   连接配置
     */
    constructor(cfg: IPostgresConnectionCfg) {
        super(cfg);
        this.dbMdl = require('pg');
        this.options = cfg.options ? cfg.options : {
            user: cfg.username,
            password: cfg.password,
            host: cfg.host,
            port: cfg.port,
            database: cfg.database,
            // ssl: cfg.ssl,
            connectionTimeoutMillis: cfg.connectTimeout,
        };

        // 连接池
        if (cfg.options || cfg.pool) {
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
        let conn = new this.dbMdl.Client(this.options);
        await conn.connect();
        return conn;
    }

    /**
     * 关闭postgres连接
     * @param connection    数据库连接对象
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
     * @param connection    db connection
     * @param sql           待执行sql
     * @param params        参数数组
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: any[]) {
        let r = await connection.conn.query(sql, params);
        return r.rows ? r.rows : r;
    }

    /**
     * 处理记录起始记录索引和记录数
     * @param sql       sql
     * @param start     开始索引
     * @param limit     记录数
     * @returns         处理后的sql
     * @since           0.2.0
     */
    public handleStartAndLimit(sql: string, start?: number, limit?: number): string {
        if (Number.isInteger(limit) && limit > 0) {
            if (Number.isInteger(start) && start >= 0) {
                return sql + ' LIMIT ' + limit + ' OFFSET ' + start;
            }
            return sql + ' LIMIT ' + limit;
        }
        return sql;
    }

    /**
     * 获取实体sequence，针对主键生成策略为sequence时有效
     * @param em        entity manager
     * @param seqName   sequence name
     * @param schema    schema
     * @returns         sequence 值
     */
    public async getSequenceValue(em: EntityManager, seqName: string, schema?: string): Promise<number> {
        // 需要指定sequence所属schema
        let query: NativeQuery = em.createNativeQuery(
            "select nextval('" + (schema ? schema + "." + seqName : seqName) + "')"
        );
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
    public getIdentityId(result: any): number {
        if (!result || result.length > 1) {
            return;
        }
        return <number>Object.values(result[0])[0];
    }
}