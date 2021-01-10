import { Transaction } from "./transaction/transaction";
import { ConnectionManager } from "./connectionmanager";
/**
 * 数据库连接类
 */
class Connection{
    /**
     * 真实连接对象
     */
    conn:any;
    /**
     * 线程id
     */
    threadId:number;
    /**
     * 连接状态
     */
    connected:boolean;

    /**
     * 创建者对象id
     */
    fromId:number;

    constructor(conn){
        this.conn = conn;
    }

    /**
     * 关闭连接
     * @param force     是否强制关闭
     */
    public async close(force?:boolean){
        await ConnectionManager.closeConnection(this,force);
    }
    
    /**
     * 创建事务对象
     */
    public createTransaction():Transaction{
        return new Transaction(this);
    }
}

export {Connection}