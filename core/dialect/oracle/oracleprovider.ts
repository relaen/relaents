import { Connection } from "../../connection";
import { BaseProvider } from "../../baseprovider";
import { EntityManager } from "../../entitymanager";
import { NativeQuery } from "../../nativequery";
import { IOracleConnectionCfg } from "./oracleoptions";
import { resolve } from "path/posix";

/**
 * oracle provider
 * @since 0.3.0
 */
// 需要手动加
export class OracleProvider extends BaseProvider {

    /**
     * 是否开启连接池
     */
    private isPool: boolean;

    /**
     * 构造器
     * @param cfg   连接配置
     */
    constructor(cfg: IOracleConnectionCfg) {
        super(cfg);
        this.dbMdl = require('oracledb');
        this.dbMdl.autoCommit = true;
        this.options = cfg.options ? cfg.options : {
            user: cfg.username,
            password: cfg.password,
            connectString: cfg.host + ":" + cfg.port + "/" + cfg.database,
            privilege: cfg.privilege,
            stmtCacheSize: cfg.stmtCacheSize,
            tag: cfg.tag
        }

        // 连接池
        if (!cfg.options && cfg.pool) {
            this.options['poolIncrement'] = cfg.poolIncrement;
            this.options['poolMax'] = cfg.pool.max;
            this.options['poolMin'] = cfg.pool.min;
            this.options['poolTimeout'] = cfg.idleTimeout;
        }
        if (cfg.usePool || cfg.pool) {
            this.isPool = true;
        }
    }

    /**
     * 获取连接
     * @returns     connection
     */
    public async getConnection(): Promise<any> {
        if (this.isPool) {
            if (!this.pool) {
                this.pool = await this.dbMdl.createPool(this.options);
            }
            return await this.pool.getConnection();
        }
        return await this.dbMdl.getConnection(this.options);
    }

    /**
     * 关闭连接
     * @param connection    数据库连接对象
     */
    public async closeConnection(connection: Connection) {
        if (this.pool) {
            await connection.conn.release();
        } else {
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
    public async exec(connection: Connection, sql: string, params?: any[]) {
        // 默认自动提交
        let autoCommit = connection.conn.autoCommit === undefined ? true : connection.conn.autoCommit;
        let r = await connection.conn.execute(sql, params, { autoCommit: autoCommit, outFormat: 4002 });
        if (r.rows) {
            return r.rows;
        }
        return r;
    }

    /**
     * 处理记录起始记录索引和记录数
     * @param sql       sql
     * @param start     开始索引
     * @param limit     记录数
     * @returns         处理后的sql
     * @since           0.2.0
     */
    public handleStartAndLimit(sql: string, start?: number, limit?: number) {
        if (limit > 0 && Number.isInteger(limit)) {
            if (start >= 0 && Number.isInteger(start)) {
                return sql + ' OFFSET ' + start + ' ROWS FETCH NEXT ' + limit + ' ROWS ONLY';
            }
            return sql + ' FETCH NEXT ' + limit + ' ROWS ONLY';
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
            "select " + (schema ? schema + "." + seqName : seqName) + ".nextval from dual"
        );
        let r = await query.getResultList(-1, -1);
        if (r[0].NEXTVAL) {
            //转换为整数
            return parseInt(r[0].NEXTVAL);
        }
        return 0;
    }

    /**
     * 加表锁
     */
     public lockTable(table: string, schema?: string): string {
        return "lock table " + (schema?schema+"."+table:table) + " in exclusive mode";
    }
}