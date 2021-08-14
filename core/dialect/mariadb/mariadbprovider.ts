import { Connection } from "../../connection";
import { ErrorFactory } from "../../errorfactory";
import { BaseProvider } from "../../baseprovider";
import { IMariadbConnectionCfg } from "./mariadboptions";

/**
 * mariadb provider
 * @since 0.3.0
 */
export class MariadbProvider extends BaseProvider {
    /**
     * 构造器
     * @param cfg   连接配置
     */
    constructor(cfg: IMariadbConnectionCfg) {
        super(cfg);
        this.dbMdl = require('mariadb');
        this.options = cfg.options ? cfg.options : {
            host: cfg.host,
            port: cfg.port,
            user: cfg.username,
            password: cfg.password,
            database: cfg.database,
            ssl: cfg.ssl,
            charset: cfg.charset,
            timezone: cfg.timezone,
            foundRows: cfg.foundRows,
            dateStrings: cfg.dateStrings,
            connectTimeout: cfg.connectTimeout,
            multipleStatements: cfg.multipleStatements,
            supportBigNumbers: cfg.supportBigNumbers,
            bigNumberStrings: cfg.bigNumberStrings,
            flags: cfg.flags
        };

        // 连接池
        if (cfg.usePool || cfg.pool) {
            if (!cfg.options && cfg.pool) {
                this.options['connectionLimit'] = cfg.pool.max;
                this.options['idleTimeout'] = cfg.idleTimeout;
                this.options['minimumIdle'] = cfg.pool.min;
            }
            this.pool = this.dbMdl.createPool(this.options);
        }
    }

    /**
     * 获取连接
     * @returns     数据库连接
     */
    public async getConnection(): Promise<any> {
        if (this.pool) {
            return await this.pool.getConnection();
        }
        return await this.dbMdl.createConnection(this.options);
    }

    /**
     * 关闭连接
     * @param connection    数据库连接对象
     */
    public async closeConnection(connection: Connection) {
        if (this.pool) {
            await connection.conn.release();
        } else {
            try {
                await connection.conn.end();
            } catch (err) {
                ErrorFactory.getError('0202', [err]);
            }
        }
    }

    /**
     * 执行sql语句
     * @param connection    db connection
     * @param sql           待执行sql
     * @param params        参数数组
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: any[]) {
        return await connection.conn.query(sql, params);
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
        if (limit > 0 && Number.isInteger(limit)) {
            if (start >= 0 && Number.isInteger(start)) {
                return sql + ' limit ' + start + ',' + limit;
            }
            return sql + ' limit ' + limit;
        }
        return sql;
    }

    /**
     * 从sql执行结果获取identityid，仅对主键生成策略是identity的有效
     * @param result    sql执行结果
     * @returns         主键
     */
    public getIdentityId(result: any): number {
        return result.insertId;
    }

    /**
     * 加表锁
     */
    public lockTable(table: string, schema?: string): string {
        return "LOCK TABLE " + table + " write";
    }

    /**
     * 释放表锁，返回null即事务commit/rollback释放表锁
     */
    public unLockTable(table?: string, schema?: string): string {
        return "UNLOCK TABLES";
    }
}