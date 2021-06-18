import { Transaction } from "./transaction";
import { ConnectionManager } from "./connectionmanager";
import { TransactionFactory } from "./transactionfactory";
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
    fromId: number;
    
    /**
     * 事务对象
     */
    transaction:Transaction;
    /**
     * 是否自动提交
     */
    // oracle 中不使用
    // autoCommit: boolean = true;

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
        let trClass = TransactionFactory.get();
        let con = this.conn;
        return trClass?Reflect.construct(trClass,[con]):null;
    }
}

export {Connection}