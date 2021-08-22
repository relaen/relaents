import { IConnectionCfg } from "../../types";

/**
 * mysql ConnectionCfg
 */
export interface IMysqlConnectionCfg extends IConnectionCfg {
    /**
     * 带有 ssl 参数的对象或包含 ssl 配置文件名称的字符串
     * object with ssl parameters or a string containing name of ssl profile.
     */
    readonly ssl: any;
    /**
     * 连接的字符集（默认值：UTF8_GENERAL_CI）
     * The charset for the connection. 
     * This is called "collation" in the SQL-level of MySQL (like utf8_general_ci). 
     * If a SQL-level charset is specified (like utf8mb4) then the default collation for that charset is used. (Default: 'UTF8_GENERAL_CI')
     */
    readonly charset: string;
    /**
     * 服务器上配置的时区（默认：local）
     * The timezone configured on the MySQL server.
     * This is used to type cast server date/time values to JavaScript Date object and vice versa. 
     * This can be 'local', 'Z', or an offset in the form +HH:MM or -HH:MM. (Default: 'local')
     */
    readonly timezone: string;
    /**
     * 强制日期类型作为字符串返回
     * Force date types (TIMESTAMP, DATETIME, DATE) to be returned as strings rather than inflated into JavaScript Date objects. 
     * Can be true/false or an array of type names to keep as strings. (Default: false)
     */
    readonly dateStrings: boolean;
    /**
     * 处理数据库中的大数字
     * When dealing with big numbers (BIGINT and DECIMAL columns) in the database, you should enable this option (Default: false).
     */
    readonly supportBigNumbers: boolean;
    /**
     * 同时启用supportBigNumbers和bigNumberStrings会强制将大数字（BIGINT和DECIMAL）作为 JavaScript String 对象返回
     * Enabling both supportBigNumbers and bigNumberStrings forces big numbers (BIGINT and DECIMAL columns) to be always returned as JavaScript String objects (Default: false). 
     * Enabling supportBigNumbers but leaving bigNumberStrings disabled will return big numbers as String objects only when they cannot be accurately represented with JavaScript Number objects (which happens when they exceed the [-2^53, +2^53] range), otherwise they will be returned as Number objects. 
     * This option is ignored if supportBigNumbers is disabled.
     */
    readonly bigNumberStrings: boolean;
    /**
     * 允许每个查询多个mysql语句。要小心，这可能会增加SQL注入攻击的范围（默认：false）
     * Allow multiple mysql statements per query. Be careful with this, it could increase the scope of SQL injection attacks. (Default: false)
     */
    readonly multipleStatements: boolean;
    /**
     * 使用非默认连接标志的连接标志列表
     * List of connection flags to use other than the default ones. It is also possible to blacklist default ones.
     * For more information, check https://www.npmjs.com/package/mysql#connection-flags.
     */
    readonly flags: string;
}