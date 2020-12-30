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
    constructor(conn){
        this.conn = conn;
    }

    /**
     * 连接
     */
    public async connect(){
        if(this.connected){
            return;
        }
        this.connected = true;
    }

    /**
     * 关闭连接
     */
    public async close(){
        if(!this.connected){
            return;
        }
        await ConnectionManager.closeConnection(this);
    }
    
    /**
     * 创建事务对象
     */
    public createTransaction():Transaction{
        return new Transaction(this);
    }
}

export {Connection}