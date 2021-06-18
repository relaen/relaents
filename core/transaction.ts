import { Logger } from "./logger";
import { TransactionManager } from "./transactionmanager";

/**
 * 事务基类
 */
abstract class Transaction{
    /**
     * 实际连接对象，与dialect对应
     */
    conn:any;
    /**
     * 构造器
     * @param conn  真实连接对象
     */
    constructor(conn:any){
        this.conn = conn;
        //加入事务管理器
        TransactionManager.add(this);
    }
    /**
     * 事务开始
     */
    public async begin(){
        Logger.console('Transaction is began.');
    }
    /**
     * 事务提交,继承类需要重载
     */
    public async commit(){
        //从事务管理器移除
        TransactionManager.remove();
        Logger.console('Transaction is commited.');
    }

    /**
     * 事务回滚,继承类需要重载
     */
    public async rollback(){
        //从事务管理器移除
        TransactionManager.remove();
        Logger.console('Transaction is rolled back.');
    }
}
export {Transaction}