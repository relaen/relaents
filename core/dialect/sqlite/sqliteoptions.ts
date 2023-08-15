import { ConnectionOption } from "../../types";

/**
 * Sqlite ConnectionCfg
 */
export interface SqliteConnectionOption extends ConnectionOption{
    /**
     * SQLITE_BUSY 重复执行时间
     */
     readonly busyErrorRetry: number;
     /**
      * SQLITE_BUSY retry重复执行超时时间
      */
     readonly busyTimeout: number;
}