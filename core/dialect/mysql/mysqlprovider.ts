import { Connection } from "../../connection";
import { ErrorFactory } from "../../errorfactory";
import { BaseProvider } from "../../baseprovider";
import { EntityManager } from "../../entitymanager";
import { IMysqlConnectionCfg } from "./mysqloptions";

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
            flags: cfg.flags,
            trace: cfg.trace
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
     * @param connection    数据库连接对象
     */
    public async closeConnection(connection: Connection) {
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
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: any[]) {
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
        if (limit > 0 && Number.isInteger(limit)) {
            if (start >= 0 && Number.isInteger(start)) {
                return sql + ' limit ' + start + ',' + limit;
            }
            return sql + ' limit ' + limit;
        }
        return sql;
    }

    /**
     * 获取实体sequence，针对主键生成策略为sequence时有效
     * mysql 不支持sequence，返回0
     * @param em        entity manager
     * @param seqName   sequence name
     * @param schema    schema
     * @returns         sequence 值
     */
    public async getSequenceValue(em: EntityManager, seqName: string, schema?: string): Promise<number> {
        return null;
    }

    /**
     * 从sql执行结果获取identityid，仅对主键生成策略是identity的有效
     * @param result    sql执行结果
     * @returns         主键
     */
    public getIdentityId(result: any): number {
        return result.insertId;
    }
}