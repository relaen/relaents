import { IConnectionCfg } from "../../types";

/**
 * mssql ConnectionCfg
 */
export interface IMssqlConnectionCfg extends IConnectionCfg {
    /**
     * 请求超时时间，毫秒为单位 (默认: 15000)。
     */
    readonly requestTimeout: number;
}