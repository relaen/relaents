import { RelaenManager } from "./relaenmanager";
import { RelaenThreadLocal } from "./threadlocal";
import { Transaction } from "./transaction";

/**
 * 事务 管理器
 */
export class TransactionManager{
    /**
     * 事务集合
     * key threadId,value 事务对象
     */
    private static transactions:Map<number,Transaction> = new Map();

    /**
     * 添加事务
     * @param value     transaction 类
     * @param threadId  thread id，默认新建
     */
    public static add(value:Transaction,threadId?:number){
        this.transactions.set(threadId || RelaenThreadLocal.newThreadId(),value);
    }

    /**
     * 获取事务
     * @param threadId  thread id，默认为当前thread id
     * @returns         transaction实例 或 undefined
     */
    public static get(threadId?:number):any{
        return this.transactions.get(threadId || RelaenThreadLocal.getThreadId());
    }

    /**
     * 删除事务
     * @param threadId  thread id，默认为当前thread id
     */
    public static remove(threadId?:number){
        this.transactions.delete(threadId||RelaenThreadLocal.getThreadId());
    }
}
