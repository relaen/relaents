import {IEntityCfg, EEntityState, IEntity } from "./entitydefine";
import { BaseEntity } from "./baseentity";
import { Translator } from "./translator";
import { SqlExecutor } from "./sqlexecutor";
import { EntityFactory } from "./entityfactory";
import { Query } from "./query";
import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { ErrorFactory } from "./errorfactory";
import { NativeQuery } from "./nativequery";
import { EntityManagerFactory } from "./entitymanagerfactory";

/**
 * 实体管理器
 */
class EntityManager{
    /**
     * 连接
     */
    public connection:Connection;

    /**
     * 实体缓存map {cacheId:{entity:Entity,fk:外键值map}
     */
    public entityMap:Map<string,any>;
    
    /**
     * 实体状态map {实体:}
     */
    public statusMap:WeakMap<IEntity,EEntityState>;

    /**
     * 构造函数
     * @param conn  连接对象
     */
    constructor(conn:Connection){
        this.connection = conn;
        this.entityMap = new Map();
        this.statusMap = new WeakMap();
    }

    /**
     * 保存新对象
     * 如果状态为new，则执行insert，同时改变为persist，如果为persist，则执行update
     * @param entity    实体
     * @returns         保存后的实体  
     */
    public async save(entity:IEntity):Promise<any>{
        let idValue = this.getIdValue(entity);
        //无主键或状态为new
        if(idValue === undefined || idValue === null || 
                this.statusMap.has(entity) && this.statusMap.get(entity) === EEntityState.NEW){
            //设置为new
            if(!this.statusMap.has(entity)){
                this.statusMap.set(entity,EEntityState.NEW);
            }
            
            //检查并生成主键
            await this.genKey(entity);
            let sql:string = Translator.entityToInsert(entity);
            let r = await SqlExecutor.exec(this.connection,sql);
            //针对单主键设置主键
            this.setIdValue(entity,r);
            //加入缓存
            if(RelaenManager.cache){
                this.addCache(entity);
            }
            this.statusMap.set(entity,EEntityState.PERSIST);
        }else{ //update
            let cacheId = this.genCacheId(entity);
            if(cacheId){
                if(this.entityMap.has(cacheId)){
                    let entity1:IEntity = this.entityMap.get(cacheId).entity;
                    //对比有差别
                    if(entity1 && !entity.compare(entity1)){
                        //更新到数据库
                        let sql:string = Translator.entityToUpdate(entity);
                        await SqlExecutor.exec(this.connection,sql);
                        //更新缓存
                        this.addCache(entity);
                    }    
                }
            }
        }
        return entity;
    }

    /**
     * 删除实体
     * @param entity    待删除实体
     * @returns         是否删除成功
     */
    public async delete(entity:IEntity):Promise<IEntity>{
        let sql:string = Translator.entityToDelete(entity);
        let r = await SqlExecutor.exec(this.connection,sql);
        //从状态map移除
        this.statusMap.delete(entity);
        //从实体map移除
        this.entityMap.delete(this.genCacheId(entity));
        return entity;
    }

    /**
     * 通过id查找实体
     * @param entityClass   entity class 名 
     * @param id            entity id 值
     * @returns             entity
     */
    public async find(entityClassName:string,id:any):Promise<IEntity>{
        
        let key:string = entityClassName + '@' + id;
        if(this.entityMap.has(key)){  //从缓存中获取
            return this.entityMap.get(key).entity;
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
     * @param rql               relean ql
     * @param entityClassName   实体类名
     */
    public createQuery(rql:string,entityClassName:string):Query{
        return new Query(rql,this,entityClassName);
    }

    /**
     * 原生sql查询
     * @param sql 
     */
    public createNativeQuery(sql:string,entityClassName?:string):NativeQuery{
        return new NativeQuery(sql,this,entityClassName);
    }

    /**
     * 关闭
     */
    public close(){
        EntityManagerFactory.closeEntityManager(this);
    }

    /**
     * 生成缓存id
     * @param entity    实体对象
     */
    private genCacheId(entity:IEntity):string{
        return entity.constructor.name + '@' + this.getIdValue(entity);
    }

    /**
     * 设置属性值
     * @param entity    实体对象
     * @param value     实体值
     */
    public setIdValue(entity:IEntity,value:any){
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(cfg.id && cfg.id.name){
            entity[cfg.id.name] = value;
        }
    }

    /**
     * 获取id值
     * @param entity    实体对象
     */
    public getIdValue(entity:IEntity):any{
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
     * @param fk     外键值map
     */
    public addCache(entity:any,fk?:Map<string,any>){
        //如果cache设置为false，则不缓存
        if(!RelaenManager.cache){
            return;
        }
        this.entityMap.set(this.genCacheId(entity),{entity:entity,fk:fk});
    }

    /**
     * 获取cache
     * @param entity  实体对象
     * @returns       {entity:实体,fk:外键map}
     */
    public getCache(entity:any):any{
        let cacheId:string = this.genCacheId(entity);
        if(this.entityMap.has(cacheId)){
            return this.entityMap.get(cacheId);
        }
    }
    /**
     * 生成主键
     * @param entity 
     */
    private async genKey(entity:IEntity){
        //如果generator为table，则从指定主键生成表中获取主键，并赋予entity
        let orm:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        
        if(orm && orm.id){
            let value;
            switch(orm.id.generator){
                case 'table':
                    let fn:string = orm.id.keyName;
                    let query:NativeQuery = this.createNativeQuery("select id_value from " + orm.id.table + " where id_name='" + fn + "'");
                    let r = await query.getResult();
                    if(r){
                        value = r['id_value'];
                        //转换为整数
                        value = parseInt(value);
                        value++;
                    }
                    
                    //主键值+1并写回数据库
                    await SqlExecutor.exec(this.connection,"update " + orm.id.table + " set id_value=" + value + " where id_name='" + fn + "'");
                    break;
                case 'uuid':
                    value = require('uuid').v1();
                    break;
            }
            //设置主键值
            if(value){
                this.setIdValue(entity,value);
            }
        }
    }
}

export{EntityManager}