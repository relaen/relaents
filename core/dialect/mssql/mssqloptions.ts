import { IConnectionCfg } from "../../types";

/**
 * mssql ConnectionCfg
 */
export interface IMssqlConnectionCfg extends IConnectionCfg {
    /**
     * Connection timeout in ms (default: 15000).
     */
    readonly connectTimeout: number;
    /**
     * The Number of milliseconds before closing an unused connection (default: 30000).
     */
    readonly idleTimeout: number;
}