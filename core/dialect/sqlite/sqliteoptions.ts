import { IConnectionCfg } from "../../types";

/**
 * Sqlite ConnectionCfg
 */
export interface ISqliteConnectionCfg extends IConnectionCfg{
    /**
     * SQLITE_BUSY 重新执行时间
     */
    readonly busyTimeout?: number;
}