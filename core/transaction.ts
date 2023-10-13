import { Connection } from "./connection";
import { Logger } from "./logger";
import { RelaenTipManager } from "./message/tipmanager";
import { RelaenThreadLocal } from "./threadlocal";
import { TransactionFactory } from "./transactionfactory";
import { EIsolationLevel } from "./types";

/**
 * 事务基类
 */
export abstract class Transaction {
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
    isolation: EIsolationLevel;

    /**
     * 构造器
     * @param conn -  真实连接对象
     */
    constructor(conn: Connection) {
        this.conn = conn;
        //如果无threadid，则新建
        this.threadId = RelaenThreadLocal.getThreadId()||RelaenThreadLocal.newThreadId();
    }

    /**
     * 设置当前事务
     * @param isolationLevel - 事务隔离级
     */
    public async setIsolationLevel(isolationLevel: EIsolationLevel) {
        this.isolation = isolationLevel
    }

    /**
     * 事务开始
     */
    public async begin() {
        Logger.log(RelaenTipManager.getTip('txbegin'));
    }

    /**
     * 事务提交,继承类需要重载
     */
    public async commit() {
        //从事务管理器移除
        TransactionFactory.remove();
        Logger.log(RelaenTipManager.getTip('txcommit'));
    }

    /**
     * 事务回滚,继承类需要重载
     */
    public async rollback() {
        //从事务管理器移除
        TransactionFactory.remove();
        Logger.log(RelaenTipManager.getTip('txrollback'));
    }
}