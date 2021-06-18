import { RelaenManager } from "./relaenmanager";

/**
 * translator类工厂
 */
export class TranslatorFactory{
    /**
     * translator类集合
     */
    private static translators:Map<string,any> = new Map();

    /**
     * 添加translator类
     * @param name              translator类名
     * @param translatorClass   translator类
     */
    public static add(name:string,translatorClass:any){
        this.translators.set(name,translatorClass);
    }

    /**
     * 获取事务类
     * @param args      解释器初始化参数，通常为实体类名
     * @returns         translator类或undefined
     */
    public static get(args?:any):any{
        let ts = this.translators.get(RelaenManager.dialect);
        return Reflect.construct(ts,[args]);
    }
}
