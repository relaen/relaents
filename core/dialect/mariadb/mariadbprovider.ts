import { Connection } from "../../connection";
import { ErrorFactory } from "../../errorfactory";
import { BaseProvider } from "../../baseprovider";
import { MariadbConnectionOption } from "./mariadboptions";
import { ELockType } from "../../types";

/**
 * mariadb provider
 */
export class MariadbProvider extends BaseProvider {
    /**
     * 构造器
     * @param cfg -   连接配置
     */
    constructor(cfg: MariadbConnectionOption) {
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
                //TODO 检查参数对应
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
     * @param connection -    数据库连接对象
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
     * @param connection -    db connection
     * @param sql -           待执行sql
     * @param params -        参数数组
     * @returns             结果(集)
     */
    public async exec(connection: Connection, sql: string, params?: unknown[]): Promise<unknown> {
        return await connection.conn.query(sql, params);
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
            return sql + ' LIMIT ' + start + ',' + limit;
        }
        if (limit) {
            return sql + ' LIMIT ' + limit;
        }
        return sql;
    }

    /**
     * 从sql执行结果获取identityid，仅对主键生成策略是identity的有效
     * @param result -    sql执行结果
     * @returns         主键
     */
    public getIdentityId(result: any): number {
        return result.insertId;
    }


    /**
     * 获取加锁sql语句
     * @param type -      锁类型    
     * @param tables -    表名，表锁时使用
     * @param schema -    模式名，表锁时使用
     * @returns         加锁sql语句
     */
    public lock(type: ELockType, tables?: string[], schema?: string): string {
        switch (type) {
            //表连接为 ' ' 空格
            case 'table_read':
                return "LOCK TABLE " + tables.join(' ') + " READ";
            case 'table_write':
                return "LOCK TABLE " + tables.join(' ') + " WRITE";
            case 'row_read':
                return "LOCK IN SHARE MODE";
            case 'row_write':
                return "FOR UPDATE";
        }
    }

    /**
     * 获取释放锁sql语句
     * @param type -      锁类型
     * @returns         释放锁sql语句
     */
    public unlock(type: ELockType) {
        switch (type) {
            case 'table_write':
                return "UNLOCK TABLES";
        }
    }
}