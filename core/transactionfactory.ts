import { RelaenManager } from "./relaenmanager";
import { UnknownClass } from "./types";

/**
 * 事务类型工厂
 */
export class TransactionFactory{
    /**
     * 事务类型集合
     */
    private static transactions:Map<string,unknown> = new Map();

    /**
     * 添加事务类
     * @param name -    dialect 名
     * @param value -   transaction 类
     */
    public static add(name:string,value:unknown){
        this.transactions.set(name,value);
    }

    /**
     * 获取事务类
     * @param name -    dialect名 
     * @returns         transaction类 或 undefined
     */
    public static get(name?:string):unknown{
        return this.transactions.get(name||RelaenManager.dialect);
    }
}
