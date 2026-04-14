import { ConnectionOption } from "../../types";

/**
 * postgres 配置项
 */
export type PostgresOption = {

    /**
     * passed directly to node.TLSSocket, supports all tls.connect options
     */
    readonly ssl: boolean;
} & ConnectionOption;