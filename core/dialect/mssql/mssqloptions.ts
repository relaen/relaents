import { ConnectionOption } from "../../types";

/**
 * mssql ConnectionCfg
 */
export interface IMssqlConnectionCfg extends ConnectionOption {
    /**
     * 请求超时时间，毫秒为单位 (默认: 15000)。
     */
    readonly requestTimeout: number;
}