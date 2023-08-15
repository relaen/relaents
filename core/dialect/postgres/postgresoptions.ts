import { ConnectionOption } from "../../types";

/**
 * postgres ConnectionCfg
 */
export interface PostgresConnectionOption extends ConnectionOption {

    /**
     * passed directly to node.TLSSocket, supports all tls.connect options
     */
    readonly ssl: boolean;
}