import { RelaenManager } from "./relaenmanager";

/**
 * provider 工厂
 */
export class ProviderFactory{
    /**
     * provider类型集合
     */
    private static providers:Map<string,any> = new Map();

    /**
     * 添加provider
     * @param name      provider 名
     * @param provider  provider 类
     */
    public static add(name:string,provider:any){
        this.providers.set(name,provider);
    }

    /**
     * 获取provider
     * @param name      provider名 
     * @returns         provider类 或 undefined
     */
    public static get(name?:string):any{
        return this.providers.get(name||RelaenManager.dialect);
    }
}
