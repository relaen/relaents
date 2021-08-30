import { BaseProvider } from "../../baseprovider";
import { Connection } from "../../connection";
import { ISqliteConnectionCfg } from "./sqliteoptions";

/**
 * sqlite provider
 * @since 0.3.0
 */
export class SqliteProvider extends BaseProvider {

    /**
     * SQLITE_BUSY 重复执行时间
     */
    private busyErrorRetry: number;
    /**
     * SQLITE_BUSY retry重复执行超时时间
     */
    private busyTimeout: number;

    /**
     * 构造器
     * @param cfg   连接配置
     */
    constructor(cfg: ISqliteConnectionCfg) {
        super(cfg);
        this.dbMdl = require('sqlite3');
        this.options = cfg.options ? cfg.options : {
            database: cfg.database
        }
        this.busyErrorRetry = cfg.busyErrorRetry || 500;
        this.busyTimeout = cfg.busyTimeout >= 0 ? cfg.busyTimeout : 2000;
    }

    /**
     * 获取连接
     * @returns     数据库连接 
     */
    public async getConnection(): Promise<any> {
        return new Promise((resolve, reject) => {
            new this.dbMdl.Database(this.options.database, function (err) {
                if (err) {
                    return reject(err);
                }
                resolve(this);
            });
        });
    }

    /**
     * 关闭连接
     * @param connection 数据库连接对象
     */
    public async closeConnection(connection: Connection): Promise<any> {
        return new Promise((resolve, reject) => {
            connection.conn.close((err) => {
                if (err) {
                    return reject(err);
                }
            });
            resolve(null);
        });
    }

    /**
     * 执行sql语句
     * @param connection    db connection
     * @param sql           待执行sql
     * @param params        参数数组
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: any[] | object) {
        return new Promise(async (resolve, reject) => {
            // insert into 使用run
            const isInsertQuery = sql.substr(0, 11).toLocaleLowerCase() === 'insert into';
            const isBeginImmediate = sql.substr(0, 15).toLocaleLowerCase() === 'begin immediate';
            const busyErrorRetry = this.busyErrorRetry;
            const busyTimeout = this.busyTimeout;
            let retryNum = 0;
            await execute();

            // 执行函数
            async function execute() {
                if (isInsertQuery || isBeginImmediate) {
                    connection.conn.run(sql, params, handler);
                } else {
                    connection.conn.all(sql, params, handler);
                }
            };

            function handler(err: any, rows: any) {
                if (err) {
                    // SQLITE_BUSY 配置时间
                    if (err.code === 'SQLITE_BUSY' && busyErrorRetry > 0) {
                        // busyTime 为0，一直循环执行到成功
                        if (busyTimeout > 0 && (++retryNum * busyErrorRetry) > busyTimeout) {
                            return reject(err);
                        }
                        setTimeout(execute, this.busyErrorRetry);
                        return;
                    }
                    return reject(err);
                }
                // 结果返回
                if (isInsertQuery) {
                    resolve(this);
                } else {
                    resolve(rows);
                }
            };
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
        if (Number.isInteger(limit) && limit > 0) {
            if (Number.isInteger(start) && start >= 0) {
                return sql + ' limit ' + limit + ' offset ' + start;
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
        return result.lastID;
    }

    /**
     * 加表锁
     */
    public lockTable(table?: string, schema?: string): string {
        return "begin immediate";
    }
}