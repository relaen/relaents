import { IConnectionCfg } from "../../types";

/**
 * postgres ConnectionCfg
 */
export interface IPostgresConnectionCfg extends IConnectionCfg {

    /**
     * passed directly to node.TLSSocket, supports all tls.connect options
     */
    readonly ssl: boolean;
    /**
     * The milliseconds before a timeout occurs during the initial connection to the postgres server. 
     */
    readonly connectTimeout: number;
    /**
     * number of milliseconds a client must sit idle in the pool and not be checked out before it is disconnected from the backend and discarded
     */
    readonly idleTimeout: number;
}