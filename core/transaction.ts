import { Connection } from "./connection";
import { Logger } from "./logger";
import { RelaenThreadLocal } from "./threadlocal";
import { TransactionManager } from "./transactionmanager";
import { IsolationLevel } from "./types";

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
     * 事务隔离级别
     */
    isolation: IsolationLevel;

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
     * 设置当前事务
     * @param isolation 事务隔离级
     */
    public async setisolationLevel(isolationLevel: IsolationLevel) {
    }

    /**
     * 事务开始
     * @param sqliteTransaction sqlite事务类型
     */
    public async begin(sqliteTransaction?: 'immediate' | 'exclusive') {
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

export { Transaction }