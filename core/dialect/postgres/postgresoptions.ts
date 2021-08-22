import { IConnectionCfg } from "../../types";

/**
 * postgres ConnectionCfg
 */
export interface IPostgresConnectionCfg extends IConnectionCfg {

    /**
     * passed directly to node.TLSSocket, supports all tls.connect options
     */
    readonly ssl: boolean;
}