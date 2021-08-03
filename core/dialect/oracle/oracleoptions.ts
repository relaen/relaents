import { IConnectionCfg } from "../../types";

/**
 * oracle ConnectionCfg
 */
export interface IOracleConnectionCfg extends IConnectionCfg {

    /**
     * The privilege to use when establishing connection to the database. 
     * This optional property should be one of the privileged connection constants (https://oracle.github.io/node-oracledb/doc/api.html#oracledbconstantsprivilege). 
     * Multiple privileges may be used by when required, for example oracledb.SYSDBA | oracledb.SYSPRELIM.
     */
    readonly privilege: number;
    /**
     * The number of statements to be cached in the statement cache of each connection. 
     * This optional property may be used to override the oracledb.stmtCacheSize property.
     */
    readonly stmtCacheSize: string;
    /**
     * Used when getting a connection from a connection pool.
     */
    readonly tag: string;
    /**
     * The poolAlias is an optional property that is used to explicitly add pools to the connection pool cache.
     */
    readonly poolAlias: string;
    /**
     * The number of connections that are opened whenever a connection request exceeds the number of currently open connections.
     */
    readonly poolIncrement: number;
    /**
     * The number of seconds after which idle connections (unused in the pool) may be terminated. 
     * Idle connections are terminated only when the pool is accessed.
     */
    readonly idleTimeout: number;
    /**
     * Recording of pool statistics can be enabled by setting enableStatistics to true. 
     * Statistics can be retrieved with pool.getStatistics(), or pool.logStatistics(). 
     * See https://oracle.github.io/node-oracledb/doc/api.html#connpoolmonitor.
     */
    readonly enableStatistics: boolean;
}