import {IEntityCfg, EEntityState, IEntity, IEntityRelation, IEntityColumn } from "./types";
import { Translator } from "./translator";
import { SqlExecutor } from "./sqlexecutor";
import { EntityFactory } from "./entityfactory";
import { Query } from "./query";
import { Connection } from "./connection";
import { ErrorFactory } from "./errorfactory";
import { NativeQuery } from "./nativequery";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { RelaenUtil } from "./relaenutil";
import { BaseEntity } from "./baseentity";

/**
 * 实体管理器
 */
class EntityManager{
    /**
     * 对象id
     */
    public id:number;
    /**
     * 连接
     */
    public connection:Connection;

    /**
     * 查询结果集缓存
     *     key:sql语句和参数值组合成的字符串，value:查询结果集
     */
    private cache:Map<string,any> = new Map();

    /**
     * 实体状态map
     */
    private entityStatusMap:WeakMap<IEntity,EEntityState> = new WeakMap();
    /**
     * 构造函数
     * @param conn  连接对象
     * @param id    entity manager id
     */
    constructor(conn:Connection,id?:number){
        this.id = id;
        this.connection = conn;
        this.cache = new Map();
    }

    /**
     * 保存新对象
     * 如果状态为new，则执行insert，同时改变为persist，如果为persist，则执行update
     * @param entity                实体
     * @param ignoreUndefinedValue  忽略undefined值，针对update时有效
     * @returns                     保存后的实体  
     */
    public async save(entity:IEntity,ignoreUndefinedValue?:boolean):Promise<IEntity>{
        //先进行预处理
        if(!this.preHandleEntity(entity,ignoreUndefinedValue)){
            return null;
        }
        let status = this.getEntityStatus(entity);
        //无主键或状态为new
        if(status === EEntityState.NEW){
            //检查并生成主键
            let idValue:any = RelaenUtil.getIdValue(entity);
            let sqlAndValue:any[];
            if(idValue){ //存在主键
                //如果有主键，则查询是否存在对应实体
                let en:IEntity = await this.find(entity.constructor.name,idValue);
                if(en){ //如果该实体已存在，则执行update
                    sqlAndValue = Translator.entityToUpdate(entity,ignoreUndefinedValue);
                }else{  //实体不存在，则执行insert
                    sqlAndValue = Translator.entityToInsert(entity);
                }
            }else{ //无主键
                //根据策略生成主键
                await this.genKey(entity);
                sqlAndValue = Translator.entityToInsert(entity);
            }
            let r = await SqlExecutor.exec(this,sqlAndValue[0],sqlAndValue[1]);
            if(r === null){
                return;
            }
            //修改状态
            this.setEntityStatus(entity,EEntityState.PERSIST);
            //设置主键值
            if(!RelaenUtil.getIdValue(entity)){
                RelaenUtil.setIdValue(entity,r);
            }
        }else{ //update
            //更新到数据库
            let sqlAndValue:any[] = Translator.entityToUpdate(entity,ignoreUndefinedValue);
            let r = await SqlExecutor.exec(this,sqlAndValue[0],sqlAndValue[1]);
            if(r === null){
                return null;
            }
        }
        return entity;
    }

    /**
     * 删除实体
     * @param entity        待删除实体或id
     * @param className     实体类名
     * @returns             被删除实体
     */
    public async delete(entity:any,className?:string):Promise<boolean>{
        let sqlAndValue = Translator.toDelete(entity,className);
        if(sqlAndValue){
            await SqlExecutor.exec(this,sqlAndValue[0],[sqlAndValue[1]]);
        }
        return true;
    }

    /**
     * 通过id查找实体
     * @param entityClass   entity class 名 
     * @param id            entity id 值
     * @returns             entity
     */
    public async find(entityClassName:string,id:any):Promise<IEntity>{
        let orm:IEntityCfg = EntityFactory.getClass(entityClassName);
        if(!orm){
            throw ErrorFactory.getError("0020",[entityClassName]);
        }
        let idName:string = RelaenUtil.getIdName(entityClassName);
        if(!idName){
            throw ErrorFactory.getError("0103");
        }
        let query = this.createNativeQuery("select * from " + orm.table + " where " + orm.columns.get(idName).name + '=?',entityClassName);
        query.setParameter(0,id);
        return await query.getResult();
    }

    /**
     * 根据条件查找一个对象
     * @param entityClassName   实体类名 
     * @param params            参数对象{propName1:propValue1,propName2:{value:propValue2,rel:'>',before:'(',after:')',logic:'OR'}...}
     *                          参数值有两种方式，一种是值，一种是值对象，值对象参考ICondValueObj接口说明
     * @param order             排序对象 {propName1:asc,propName2:desc,...}
     * @since 0.1.3
     */
    public async findOne(entityClassName:string,params?:object,order?:object):Promise<any>{
        let lst = await this.findMany(entityClassName,params,0,1,order);
        if(lst && lst.length>0){
            return lst[0];
        }
        return null;
    }

    /**
     * 根据条件查找多个对象
     * @param entityClassName   实体类名 
     * @param params            参数对象，参考findOne
     * @param start             开始记录行
     * @param limit             获取记录数
     * @param order             排序对象，参考findOne
     * @since 0.1.3                 
     */
    public async findMany(entityClassName:string,params?:object,start?:number,limit?:number,order?:object):Promise<Array<any>>{
        let query:Query = this.createQuery(entityClassName);
        return await query.select('*')
                    .where(params)
                    .orderBy(order)
                    .getResultList(start,limit);
    }

    /**
     * 获取记录数
     * @param entityClassName   实体类名
     * @param params            参数对象，参考findOne
     */
    public async getCount(entityClassName:string,params?:object){
        let query:Query = this.createQuery(entityClassName);
        return await query.select('count(*)')
                          .where(params)
                          .getResult(true);
    }

    /**
     * 删除多个
     * @param entityClassName   实体类名
     * @param params            条件参数，参考findOne
     * @returns                 成功:true，失败:false
     * @since 0.1.3
     */
    public async deleteMany(entityClassName:string,params?:object):Promise<boolean>{
        return await this.createQuery(entityClassName).delete().where(params).getResult();
    }
    /**
     * 创建查询对象
     * @param rql               relean ql
     * @param entityClassName   实体类名
     */
    public createQuery(entityClassName?:string):Query{
        return new Query(this,entityClassName);
    }

    /**
     * 原生sql查询
     * @param sql 
     */
    public createNativeQuery(sql:string,entityClassName?:string):NativeQuery{
        return new NativeQuery(sql,this,entityClassName);
    }

    /**
     * 关闭entity manager
     * @param force     是否强制关闭
     */
    public async close(force?:boolean){
        EntityManagerFactory.closeEntityManager(this,force);
    }

    
    /**
     * 加入cache
     * @param key       key 
     * @param value     结果集
     * @since           0.2.0
     */
    public addToCache(key:string,value:any){
        this.cache.set(key,value);
    }
    /**
     * 从cache中获取
     * @param key   缓存key
     * @since       0.2.0
     */
    public getFromCache(key:string){
        return this.cache.get(key);
    }

    /**
     * 清除缓存
     */
    public clearCache(){
        this.cache.clear();
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

    /**
     * 预处理实体对象
     * @param entity                实体对象
     * @param ignoreUndefinedValue  忽略undefined值
     */
    private preHandleEntity(entity:IEntity,ignoreUndefinedValue?:boolean):boolean{
        let className:string = entity.constructor.name;
        let orm:IEntityCfg = EntityFactory.getClass(className);
        if(!orm){
            throw ErrorFactory.getError("0010",[className]);
        }
        for(let key of orm.columns){
            let fo:IEntityColumn = key[1];
            let v:any;
            if(fo.refName){ //外键，只取主键
                if(entity[key[0]] instanceof BaseEntity){
                    v = RelaenUtil.getIdValue(entity[key[0]]);
                }
            }else{
                v = entity[key[0]];
            }
            if((v === null || v === undefined)){
                if(!ignoreUndefinedValue && !fo.nullable){//null 判断
                    if(key[0] !== orm.id.name){//如果与主键不同且不能为空，则抛出异常 
                        throw ErrorFactory.getError('0021',[key[0]]);
                    }
                }
                entity[key[0]] = null;
            }else if(key[1].length && v.length>key[1].length){ //长度检测
                throw ErrorFactory.getError('0024',[className,key[0],key[1].length]);
            }
        }
        return true;
    }

    /**
     * 设置实体状态
     * @param entity    实体 
     * @param state     状态
     */
    public setEntityStatus(entity:IEntity,state:EEntityState){
        this.entityStatusMap.set(entity,state);
    }

    /**
     * 获取实体状态
     * @param entity    实体对象
     * @returns         实体状态或undefined
     */
    public getEntityStatus(entity:IEntity):EEntityState{
        return this.entityStatusMap.get(entity);
    }
}

export{EntityManager}