import { IConnectionCfg } from "../../types";

/**
 * postgres ConnectionCfg
 * @since 0.4.0
 */
export interface IPostgresConnectionCfg extends IConnectionCfg {

    /**
     * passed directly to node.TLSSocket, supports all tls.connect options
     */
    readonly ssl: boolean;
}