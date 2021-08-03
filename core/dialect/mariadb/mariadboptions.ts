import { IConnectionCfg, } from "../../types";

/**
 * mariadb ConnectionCfg
 */
export interface IMariadbConnectionCfg extends IConnectionCfg {
    /**
     * Enables TLS support. For more information, see the ssl option documentation.
     */
    readonly ssl: any;
    /**
     * Protocol character set used with the server. 
     * Connection collation will be the default collation associated with charset. 
     * It's mainly used for micro-optimizations. 
     * The default is often sufficient.
     */
    readonly charset: string;
    /**
     * Forces use of the indicated timezone, rather than the current Node.js timezone. 
     * This has to be set when database timezone differ from Node.js timezone. 
     * Possible values are IANA time zone (ex: 'America/New_York')
     */
    readonly timezone: string;
    /**
     * When enabled, the update number corresponds to update rows. 
     * When disabled, it indicates the real rows changed.
     */
    readonly foundRows: boolean;
    /**
     * Whether to retrieve dates as strings or as Date objects. 
     */
    readonly dateStrings: boolean;
    /**
     * Sets the connection timeout in milliseconds.
     */
    readonly connectTimeout: number;
    /**
     * Allows you to issue several SQL statements in a single quer() call.
     * This may be a security risk as it allows for SQL Injection attacks.
     */
    readonly multipleStatements: boolean;
    /**
     * Whether the query should return integers as Long objects when they are not in the safe range.
     */
    readonly supportBigNumbers: boolean;
    /**
     * Whether the query should return integers as strings when they are not in the safe range.
     */
    readonly bigNumberStrings: boolean;
    /**
     * Shows the byte-encoded flags. 
     */
    readonly flags: boolean;
    /**
     * Indicate idle time after which a pool connection is released. 
     * Value must be lower than @@wait_timeout. 
     */
    readonly idleTimeout: number;

}
