import { ConnectionOption } from "../../types";

/**
 * Sqlite option
 */
export type SqliteOption = {
    /**
     * SQLITE_BUSY 重复执行时间
     */
     readonly busyErrorRetry: number;
     /**
      * SQLITE_BUSY retry重复执行超时时间
      */
     readonly busyTimeout: number;
} & ConnectionOption;