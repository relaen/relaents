import { RelaenManager } from "./relaenmanager";
import { UnknownClass } from "./types";

/**
 * provider 工厂
 */
export class ProviderFactory{
    /**
     * provider类型集合
     */
    private static providers:Map<string,unknown> = new Map();

    /**
     * 添加provider
     * @param name -      provider 名
     * @param provider -  provider 类
     */
    public static add(name:string,provider:unknown){
        this.providers.set(name,provider);
    }

    /**
     * 获取provider
     * @param name -    provider名 
     * @returns         provider类 或 undefined
     */
    public static get(name?:string):unknown{
        return this.providers.get(name||RelaenManager.dialect);
    }
}
