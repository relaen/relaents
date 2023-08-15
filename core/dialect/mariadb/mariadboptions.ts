import { ConnectionOption, } from "../../types";

/**
 * mariadb Connection配置项
 */
export interface MariadbConnectionOption extends ConnectionOption {
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
     * Whether to retrieve dates as strings or as Date objects. 
     */
    readonly dateStrings: boolean;
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
    readonly flags: string;
}
