import {IEntityCfg, EEntityState, IEntity, IEntityRelation } from "./entitydefine";
import { Translator } from "./translator";
import { SqlExecutor } from "./sqlexecutor";
import { EntityFactory } from "./entityfactory";
import { Query } from "./query";
import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { ErrorFactory } from "./errorfactory";
import { NativeQuery } from "./nativequery";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { RelaenUtil } from "./relaenutil";

/**
 * 实体管理器
 */
class EntityManager{
    /**
     * 连接
     */
    public connection:Connection;

    /**
     * 实体缓存map {cacheId:{entity:object,fk:外键值map}
     */
    public entityMap:Map<string,any>;
    
    /**
     * 构造函数
     * @param conn  连接对象
     */
    constructor(conn:Connection){
        this.connection = conn;
        this.entityMap = new Map();
    }

    /**
     * 保存新对象
     * 如果状态为new，则执行insert，同时改变为persist，如果为persist，则执行update
     * @param entity                实体
     * @param ignoreUndefinedValue  忽略undefined值，针对update时有效
     * @returns                     保存后的实体  
     */
    public async save(entity:IEntity,ignoreUndefinedValue?:boolean):Promise<IEntity>{
        //无主键或状态为new
        if(entity.__status === EEntityState.NEW){
            //检查并生成主键
            
            let sql:string;
            if(RelaenUtil.getIdValue(entity)){ //存在主键
                sql = Translator.entityToUpdate(entity);
            }else{ //无主键
                //根据策略生成主键
                await this.genKey(entity);
                sql = Translator.entityToInsert(entity);
            }
            let r = await SqlExecutor.exec(this,sql);
            if(r === null){
                return;
            }
            //修改状态
            entity.__status = EEntityState.PERSIST;
            //设置主键值
            if(!RelaenUtil.getIdValue(entity)){
                RelaenUtil.setIdValue(entity,r);
            }
        }else{ //update
            let cacheId = this.genCacheId(entity);
            if(cacheId){
                if(this.entityMap.has(cacheId)){
                    let entity1:IEntity = this.entityMap.get(cacheId).entity;
                    //对比有差别才进行更新
                    if(entity1 && !entity.compare(entity1)){
                        //更新到数据库
                        let sql:string = Translator.entityToUpdate(entity,ignoreUndefinedValue);
                        let r = await SqlExecutor.exec(this,sql);
                        if(r === null){
                            return null;
                        }
                    }    
                }
            }
        }
        return entity;
    }

    /**
     * 删除实体
     * @param entity    待删除实体
     * @returns         被删除实体
     */
    public async delete(entity:IEntity):Promise<IEntity>{
        let sql:string = Translator.entityToDelete(entity);
        let r = await SqlExecutor.exec(this,sql);
        if(r === null){
            return null;
        }
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
            return this.genEntityFromObject(this.entityMap.get(key).entity);
        }else{  //从数据库获取
            let idName:string = RelaenUtil.getIdName(entityClassName);
            if(!idName){
                throw ErrorFactory.getError("0103");
            }
            
            let params = {};
            params[idName] = id;
            let query = this.createNativeQuery(Translator.genEntityQuery(entityClassName,params),entityClassName);
            query.setParameter(0,id);
            return await query.getResult();
        }
    }

    /**
     * 根据条件查找一个对象
     * @param entityClassName   实体类名 
     * @param params            参数对象{paramName1:paramValue1,paramName2:{value:paramValue2,rel:'>'}...}
     *                          参数值有两种方式，一种是直接在参数名后给值，一种是给对象，对象中包括 value:值和rel:关系，
     *                          关系包括 >,<,>=,<=,<>,is,like等
     */
    public async findOne(entityClassName:string,params:object):Promise<any>{
        let lst = await this.findMany(entityClassName,params,0,1);
        if(lst && lst.length>0){
            return lst[0];
        }
        return null;
    }

    /**
     * 根据条件查找多个对象
     * @param entityClassName   实体类名 
     * @param params            参数对象，参考findOne注释
     * @param start             开始记录行
     * @param limit             获取记录数
     */
    public async findMany(entityClassName:string,params?:object,start?:number,limit?:number):Promise<Array<any>>{
        let rql = "select m from " + entityClassName + " m ";
        let pValues = [];

        //条件参数名
        if (params && typeof params === 'object') {
            let pn = [];
            Object.getOwnPropertyNames(params).forEach(item => {
                let v = params[item];
                let rel: string = '=';
                //参数值为对象
                if ( v !== null && typeof v === 'object') {
                    if (v.rel) {
                        rel = v.rel;
                    }
                    v = v.value;
                }

                //如果值为null且关系为“=”，则需要改为“is”
                if (params[item] === null && rel === '=') {
                    rel = 'is';
                }
                //like 添加%
                if (rel === 'like') {
                    v = '%' + v + '%';
                }
                pn.push('m.' + item + ' ' + rel + ' ?');
                pValues.push(v);
            });
            if (pn.length > 0) {
                rql += ' where ' + pn.join(' and ');
            }
        }

        let query: Query = this.createQuery(rql, entityClassName);
        if (pValues.length > 0) {
            query.setParameters(pValues);
        }
        return await query.getResultList(start,limit);
    }

    /**
     * 创建查询对象
     * @param rql               relean ql
     * @param entityClassName   实体类名
     */
    public createQuery(rql:string,entityClassName?:string):Query{
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
        return entity.constructor.name + '@' + RelaenUtil.getIdValue(entity);
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
        this.entityMap.set(this.genCacheId(entity),{entity:entity.clone(),fk:fk});
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
     * 清除缓存
     */
    public clearCache(){
        this.entityMap.clear();
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
                    await SqlExecutor.exec(this,"update " + orm.id.table + " set id_value=" + value + " where id_name='" + fn + "'");
                    break;
                case 'uuid':
                    value = require('uuid').v1();
                    break;
            }
            //设置主键值
            if(value){
                RelaenUtil.setIdValue(entity,value);
            }
        }
    }

    /**
     * 从对象生成实体
     * @param obj   对象
     * @returns     实体对象
     */
    private genEntityFromObject(obj:any):IEntity{
        if(!obj.__entityClassName){
            return null;
        }
        let ecfg:IEntityCfg = EntityFactory.getClass(obj.__entityClassName);
        if(!ecfg){
            throw ErrorFactory.getError("0020",[obj.__entityClassName]);
        }
        let en:IEntity = new ecfg.entity;
        for(let col of ecfg.columns){
            //字段属性名
            let fn = col[0];
            //字段对象
            let fo = col[1];
            if(!fo.refName){ //非外键
                en[fn] = obj[fn];
            }else if(obj[fn] && ecfg.relations.has(fn)){ //外键
                let rel:IEntityRelation = ecfg.relations.get(fn);
                en[fn] = this.find(rel.entity,obj[fn]);
            }
        }
    }
}

export{EntityManager}