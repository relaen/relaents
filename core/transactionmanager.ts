import { ConnectionManager } from "./connectionmanager";
import { RelaenThreadLocal } from "./threadlocal";
import { Transaction } from "./transaction";

/**
 * 事务管理器
 */
export class TransactionManager{
    /**
     * 事务集合
     * key threadId,value 事务对象
     */
    private static transactions:Map<number,Transaction> = new Map();

    /**
     * 添加事务
     * @param tx -     transaction实例
     */
    public static add(tx:Transaction){
        this.transactions.set(tx.threadId,tx);
        //因为已经修改theadId，需要把connection绑定到新threadid
        ConnectionManager.addConnection(tx.threadId,tx.conn);
    }

    /**
     * 获取事务
     * @param threadId -    thread id，默认为当前thread id
     * @returns             transaction实例 或 undefined
     */
    public static get(threadId?:number):Transaction{
        return this.transactions.get(threadId || RelaenThreadLocal.getThreadId());
    }

    /**
     * 删除事务
     * @param threadId -  thread id，默认为当前thread id
     */
    public static remove(threadId?:number){
        threadId ||= RelaenThreadLocal.getThreadId();
        this.transactions.delete(threadId);
        ConnectionManager.removeConnection(threadId);
    }
}
