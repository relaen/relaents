import { Connection } from "./connection";
import { RelaenManager } from "./relaenmanager";
import { RelaenThreadLocal } from "./threadlocal";
import { Transaction } from "./transaction";
import { UnknownClass } from "./types";

/**
 * 事务类型工厂
 */
export class TransactionFactory{
    /**
     * 事务类型集合
     */
    private static transactions:Map<number,Transaction> = new Map();

    /**
     * 事务map集合
     */
    private static classMap:Map<string,unknown> = new Map();


    /**
     * 添加事务类
     * @param name -    dialect 名
     * @param value -   transaction 类
     */
    public static addClass(name:string,value:unknown){
        this.classMap.set(name,value);
    }

    /**
     * 获取事务类
     * @param conn -    连接对象，如果不设置，当不存在事务对象时，则不进行新建
     * @returns         事务对象
     */
    public static get(conn?:Connection):Transaction{
        const id = RelaenThreadLocal.getThreadId();
        if(this.transactions.has(id)){
            return this.transactions.get(id);
        }
        if(conn){
            //新建tx对象
            const cls = this.classMap.get(RelaenManager.dialect); 
            if(cls){
                const tx = Reflect.construct(<UnknownClass>cls,[conn]);
                this.transactions.set(tx.threadId,tx);
                return tx;
            }
        }
    }

    /**
     * 删除事务
     * @param threadId -  thread id，默认为当前thread id
     */
    public static remove(threadId?:number){
        threadId ||= RelaenThreadLocal.getThreadId();
        this.transactions.delete(threadId);
    }
}
