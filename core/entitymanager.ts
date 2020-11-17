import { IEntityPKey, IEntityColumn, IEntityCfg, IEntityRelation } from "./entitydefine";
import { BaseEntity } from "./entity";
import { Translator } from "./translator";
import { SqlExecutor } from "./sqlexecutor";
import { EntityFactory } from "./entityfactory";
import { Query } from "./query";


/**
 * 实体管理器
 */
class EntityManager{
    public entityMap:Map<string,BaseEntity>;

    constructor(){
        this.entityMap = new Map();
    }

    /**
     * 同步到数据库
     * @param entity 
     */
    public async persist(entity:any):Promise<any>{
        let sql:string = Translator.entityToInsert(entity);
        let r = await SqlExecutor.exec(sql);
        //针对单主键设置主键
        entity.setId(r);
        //加入缓存
        this.entityMap.set(this.genCacheId(entity),entity);
        return entity;
    }

    /**
     * 保存实体
     * @param entity    待存储实体
     * @returns         保存后的实体
     */
    public async merge<T>(entity:T):Promise<T>{
        
        return entity;
    }

    /**
     * 删除实体
     * @param entity    待删除实体
     * @returns         是否删除成功
     */
    public async delete<T>(entity:T):Promise<boolean>{

        return true;
    }


    /**
     * 通过id查找实体
     * @param entityClass   entity class 
     * @param id            entity id
     * @returns             entity
     */
    public async find(entityClass:any,id:any):Promise<any>{
        let key:string = entityClass.prototype.name + '@' + id;
        if(this.entityMap.has(key)){  //从缓存中获取
            return this.entityMap.get(key);
        }else{  //从数据库获取
            // let sql:string = Translator.entityToInsert(entity);
            
        }
        
        return null;
    }

    /**
     * 查询实体
     */
    public async createQuery(sql:string):Promise<Query>{

        return null;
    }

    /**
     * 关闭
     */
    public close(){
        this.entityMap.clear();
    }


    /**
     * 生成缓存id
     * @param entity    实体对象
     */
    private genCacheId(entity:BaseEntity):string{
        return entity.constructor.name + '@' + this.getIdValue(entity);
    }

    /**
     * 设置属性值
     * @param entity    实体对象
     * @param value     实体值
     */
    setIdValue(entity:BaseEntity,value:any){
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(cfg.id){
            entity.setProp(cfg.id.name,value);
        }
    }


    /**
     * 获取id值
     * @param entity    实体对象
     */
    getIdValue(entity:BaseEntity):any{
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(cfg.id){
            return entity[cfg.id.name];
        }
    }

}

export{EntityManager}