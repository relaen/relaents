import { Connection } from "../../connection";
import { ErrorFactory } from "../../errorfactory";
import { BaseProvider } from "../../baseprovider";
import { IMysqlConnectionCfg } from "./mysqloptions";
import { LockType } from "../../types";

/**
 * mysql provider
 * @since 0.3.0
 */
export class MysqlProvider extends BaseProvider {

    /**
     * 构造器
     * @param cfg   连接配置
     */
    constructor(cfg: IMysqlConnectionCfg) {
        super(cfg);
        this.dbMdl = require('mysql');
        this.options = cfg.options ? cfg.options : {
            host: cfg.host,
            port: cfg.port,
            user: cfg.username,
            password: cfg.password,
            database: cfg.database,
            ssl: cfg.ssl,
            charset: cfg.charset,
            timezone: cfg.timezone,
            dateStrings: cfg.dateStrings,
            connectTimeout: cfg.connectTimeout,
            supportBigNumbers: cfg.supportBigNumbers,
            bigNumberStrings: cfg.bigNumberStrings,
            multipleStatements: cfg.multipleStatements,
            flags: cfg.flags
        };
        //连接池
        if (cfg.usePool || cfg.pool) {
            if (!cfg.options && cfg.pool) {
                this.options["connectionLimit"] = cfg.pool.max;
            }
            this.pool = this.dbMdl.createPool(this.options);
        }
    }

    /**
     * 获取连接
     * @throws      获取连接失败错误
     * @returns     数据库连接
     */
    public async getConnection(): Promise<any> {
        return new Promise((resolve, reject) => {
            if (this.pool) {
                this.pool.getConnection((err, conn) => {
                    if (err) {
                        return reject(err);
                    }
                    resolve(conn);
                })
            } else {
                let conn = this.dbMdl.createConnection(this.options);
                conn.connect(err => {
                    if (err) {
                        return reject(err);
                    }
                    resolve(conn);
                });
            }
        });
    }

    /**
     * 关闭连接
     * @throws              关闭连接错误
     * @param connection    数据库连接对象
     */
    public async closeConnection(connection: Connection): Promise<any> {
        if (this.pool) {
            connection.conn.release();
            return Promise.resolve(null);
        } else {
            return new Promise((resolve, reject) => {
                connection.conn.end(err => {
                    if (err) {
                        return reject(ErrorFactory.getError('0202', [err]));
                    }
                    resolve(null);
                });
            });
        }
    }

    /**
     * 执行sql语句
     * @param connection    db connection
     * @param sql           待执行sql
     * @param params        参数数组
     * @throws              语句执行错误
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: any[]): Promise<any> {
        return await new Promise((resolve, reject) => {
            connection.conn.query(sql, params, (err, results, fields) => {
                if (err) {
                    return reject(err);
                }
                resolve(results);
            });
        });
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
        if (limit && start) {
            return sql + ' LIMIT ' + start + ',' + limit;
        }
        if (limit) {
            return sql + ' LIMIT ' + limit;
        }
        //mysql不能单独设置偏移量
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
     * 获取加锁sql语句
     * @param type      锁类型    
     * @param tables    表名，表锁时使用
     * @param schema    模式名，表锁时使用
     * @retruns         加锁sql语句
     * @since           0.4.0
     */
    public lock(type: LockType, tables?: string[], schema?: string): string {
        switch (type) {
            //表连接为 ' ' 空格
            case 'table_read':
                return "LOCK TABLE " + tables.join(' ') + " READ";
            case 'table_write':
                return "LOCK TABLE " + tables.join(' ') + " WRITE";
            case 'row_read':
                return "IN SHARE MODE";
            case 'row_write':
                return "FOR UPDATE";
        }
    }

    /**
     * 获取释放锁sql语句
     * @param type      锁类型
     * @returns         释放锁sql语句
     * @since           0.4.0
     */
    public unlock(type: LockType): string {
        switch (type) {
            case 'table_write':
                return "UNLOCK TABLES";
        }
    }
}