import { IConnectionCfg } from "../../types";

/**
 * oracle ConnectionCfg
 * @since 0.4.0
 */
export interface IOracleConnectionCfg extends IConnectionCfg {

    /**
     * 在建立到数据库的连接时使用的特权
     * The privilege to use when establishing connection to the database. 
     * This optional property should be one of the privileged connection constants (https://oracle.github.io/node-oracledb/doc/api.html#oracledbconstantsprivilege). 
     * Multiple privileges may be used by when required, for example oracledb.SYSDBA | oracledb.SYSPRELIM.
     */
    readonly privilege: number;
    /**
     * 池中每个连接的语句缓存中要缓存的语句数
     * The number of statements to be cached in the statement cache of each connection. 
     * This optional property may be used to override the oracledb.stmtCacheSize property.
     */
    readonly stmtCacheSize: number;
    /**
     * 当从连接池获取连接时使用，指示从连接池返回的连接应该具有的标记
     * Used when getting a connection from a connection pool.
     */
    readonly tag: string;
    /**
     * 当连接请求超过当前打开的连接数时打开的连接数（默认值：1）
     * The number of connections that are opened whenever a connection request exceeds the number of currently open connections.
     */
    readonly poolIncrement: number;
}