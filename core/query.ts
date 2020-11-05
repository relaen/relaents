import { EntityManager } from "./entitymanager";
import { Translator } from "./translator";
import { BaseEntity } from "./entity";

/**
 * 查询类
 */
class Query{
    /**
     * entity 管理器
     */
    entityManager:EntityManager;

    /**
     * 参数map
     */
    paramMap:Map<string,any> = new Map();
    /**
     * 实体
     */
    entity:BaseEntity;

    constructor(em:EntityManager,entity?:BaseEntity){
        this.entityManager = em;        
        this.entity = entity;
    }

    /**
     * 设置查询参数值
     * @param paramName 
     * @param value 
     */
    public setParameter(paramName:string,value:any){
        this.paramMap.set(paramName,value);
    }

    /**
     * 获取结果列表
     */
    public getResultList<T>():Array<T>{
        let sql:string = Translator.toQueryString(this.entity,this.paramMap);
        
    }
}