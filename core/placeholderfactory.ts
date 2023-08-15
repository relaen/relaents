import { RelaenManager } from "./relaenmanager";

/**
 * 占位符工厂
 */
export class PlaceholderFactory{
    /**
     * 占位符map
     * @remarks
     * 格式为：
     * ```js
     * key: 数据库dialect
     * value:{ch:占位符,start:开始索引号(有的开始于0，有的开始于1)}
     * ```
     */
    private static map:Map<string,{ch:string,start:number}> = new Map();
    /**
     * 添加placeholder
     * @param dialect -           数据库产品
     * @param placeholder -       占位符
     * @param startIndex -        开始索引号
     */
    public static add(dialect:string,placeholder:string,startIndex?:number){
        this.map.set(dialect,{
            ch:placeholder,
            start:startIndex
        });
    }

    /**
     * 获取占位符
     * @param index -   占位符索引，从0开始
     * @returns         占位符+索引号
     */
    public static get(index:number):string{
        const obj = this.map.get(RelaenManager.dialect);
        if(obj){
            return obj['ch'] + (obj['start'] + index);
        }
    }
}