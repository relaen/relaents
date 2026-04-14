import { ConnectionOption } from "../../types";

/**
 * mssql ConnectionCfg
 */
export type MssqlOption = {
    /**
     * 请求超时时间，毫秒为单位 (默认: 15000)。
     */
    readonly requestTimeout: number;
} & ConnectionOption;