import { IEntityPKey, IEntityColumn, IEntityCfg, IEntityRelation } from "./entitydefine";
import { BaseEntity } from "./baseentity";
import { Translator } from "./translator";
import { SqlExecutor } from "./sqlexecutor";
import { EntityFactory } from "./entityfactory";
import { Query } from "./query";
import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { ErrorFactory } from "./errorfactory";


/**
 * 实体管理器
 */
class EntityManager{

    /**
     * 连接
     */
    public connection:Connection;

    /**
     * 实体缓存map
     */
    public entityMap:Map<string,BaseEntity>;
    
    constructor(conn:Connection){
        this.connection = conn;
        this.entityMap = new Map();
    }

    /**
     * 同步到数据库
     * @param entity 
     */
    public async persist(entity:any):Promise<any>{
        let sql:string = Translator.entityToInsert(entity);
        let r = await SqlExecutor.exec(this.connection,sql);
        //针对单主键设置主键
        this.setIdValue(entity,r);
        //加入缓存
        this.entityMap.set(this.genCacheId(entity),entity);
        return entity;
    }

    /**
     * 合并实体
     * @param entity    待存储实体
     * @returns         保存后的实体
     */
    public async merge<T>(entity:T):Promise<T>{
        let sql:string = Translator.entityToUpdate(entity);
        let r = await SqlExecutor.exec(this.connection,sql);
        return entity;
    }


    /**
     * 删除实体
     * @param entity    待删除实体
     * @returns         是否删除成功
     */
    public async delete(entity:BaseEntity):Promise<BaseEntity>{
        let sql:string = Translator.entityToDelete(entity);
        let r = await SqlExecutor.exec(this.connection,sql);
        return entity;
    }


    /**
     * 通过id查找实体
     * @param entityClass   entity class 名 
     * @param id            entity id 值
     * @returns             entity
     */
    public async find(entityClassName:string,id:any):Promise<BaseEntity>{
        let key:string = entityClassName + '@' + id;
        if(this.entityMap.has(key)){  //从缓存中获取
            return this.entityMap.get(key);
        }else{  //从数据库获取
            let idName:string = this.getIdName(entityClassName);
            if(!idName){
                throw ErrorFactory.getError("0103");
            }
            let query = this.createQuery("select m from " + entityClassName + " m where m." + idName + "=?",entityClassName);
            query.setParameter(0,id);
            let en = await query.getResult();
            return en;
        }
    }

    /**
     * 创建查询对象
     * @param rql       relean ql
     * @param entity    实体类
     */
    public createQuery(rql:string,entity:any):Query{
        return new Query(rql,this,entity);
    }

    /**
     * 原生sql查询
     * @param sql 
     */
    public createNativeQuery(sql:string):Query{
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
    public setIdValue(entity:BaseEntity,value:any){
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(cfg.id && cfg.id.name){
            entity[cfg.id.name] = value;
        }
    }

    /**
     * 获取id值
     * @param entity    实体对象
     */
    public getIdValue(entity:BaseEntity):any{
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(cfg.id){
            return entity[cfg.id.name];
        }
    }

    /**
     * 获取id名 
     * @param entity 实体对象或实体名
     * @returns      实体id名
     */
    public getIdName(entity:any):string{
        let en:string;
        if(entity instanceof BaseEntity){
            en = entity.constructor.name;
        }else if(typeof entity === 'string'){
            en = entity;
        }
        let cfg:IEntityCfg = EntityFactory.getClass(en);
        if(cfg.id){
            return cfg.id.name
        }
    }

    /**
     * 添加实体对象到cache
     * @param entity 实体对象
     */
    public addCache(entity:any){
        //如果cache设置为false，则不缓存
        if(!RelaenManager.cache){
            return;    
        }
        this.entityMap.set(this.genCacheId(entity),entity);
    }
}

export{EntityManager}