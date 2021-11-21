import { IConnectionCfg } from "../../types";

/**
 * Sqlite ConnectionCfg
 * @since 0.4.0
 */
export interface ISqliteConnectionCfg extends IConnectionCfg{
    /**
     * SQLITE_BUSY 重复执行时间
     */
     readonly busyErrorRetry: number;
     /**
      * SQLITE_BUSY retry重复执行超时时间
      */
     readonly busyTimeout: number;
}