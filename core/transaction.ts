import { Connection } from "./connection";
import { Logger } from "./logger";
import { RelaenThreadLocal } from "./threadlocal";
import { TransactionManager } from "./transactionmanager";

/**
 * 事务基类
 */
abstract class Transaction {
    /**
     * 连接对象
     */
    conn: Connection;

    /**
     * 线程id
     */
    threadId: number;
    /**
     * 构造器
     * @param conn  真实连接对象
     */
    constructor(conn: any) {
        this.conn = conn;
        //每个transaction都新建一个threadId
        this.threadId = RelaenThreadLocal.newThreadId();
        //加入事务管理器
        TransactionManager.add(this);
    }
    /**
     * 事务开始
     */
    public async begin(isolationLevel?: IsolationLevel) {
        Logger.log('Transaction is begun.');
    }
    /**
     * 事务提交,继承类需要重载
     */
    public async commit() {
        //从事务管理器移除
        TransactionManager.remove(this.threadId);
        Logger.log('Transaction is committed.');
    }

    /**
     * 事务回滚,继承类需要重载
     */
    public async rollback() {
        //从事务管理器移除
        TransactionManager.remove(this.threadId);
        Logger.log('Transaction is rolled back.');
    }
}
// 事务隔离级别
type IsolationLevel = "SERIALIZABLE" | "READ UNCOMMITTED" | "READ COMMITTED" | "REPEATABLE READ" | "immediate" | "exclusive";
export { Transaction, IsolationLevel }