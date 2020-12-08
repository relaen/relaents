import { Transaction } from "./transaction/transaction";
import { ConnectionManager } from "./connectionmanager";
/**
 * 数据库连接类
 */
class Connection{
    conn:any;
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
        ConnectionManager.closeConnection(this);
    }
    
    /**
     * 创建事务对象
     */
    public createTransaction():Transaction{
        return new Transaction(this);
    }
}

export {Connection}