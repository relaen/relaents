import { EntityManager } from "./entitymanager";
import { Translator } from "./translator";
import { BaseEntity } from "./baseentity";
import { SqlExecutor } from "./sqlexecutor";
import { EntityFactory } from "./entityfactory";
import { IEntityCfg, IEntity, EEntityState } from "./entitydefine";
import { ErrorFactory } from "./errorfactory";
import { RelaenUtil } from "./relaenutil";

/**
 * 查询类
 */
class Query{
    /**
     * entity 管理器
     */
    entityManager:EntityManager;

    /**
     * 参数值数组
     */
    paramArr:any[];

    /**
     * 执行sql
     */
    execSql:string;

    /**
     * 实体类名
     */
    entityClassName:string;

    /**
     * 查询记录的start index
     */
    start:number;

    /**
     * 查询记录的条数限制
     */
    limit:number;
    
    /**
     * 别名关联map {aliasName:{entity:对应实体类,fromEntity:来源实体,propName:属性名}}
     */
    aliasMap:Map<string,object>;

    /**
     * 外键数组
     */
    fkArray:string[];
    /**
     * 构造query对象
     * @param rql               relean ql 
     * @param em                entity manager
     * @param entityClassName   对应的结果实体类名
     * @param notTransate       不转换rql
     */
    constructor(rql:string,em:EntityManager,entityClassName?:string,notTransate?:boolean){
        if(entityClassName && !EntityFactory.getClass(entityClassName)){
            throw ErrorFactory.getError("0020",[entityClassName]);
        }
        this.entityManager = em;
        this.entityClassName = entityClassName;
        
        //需要转换rql
        if(!notTransate){
            let obj = Translator.getQuerySql(rql);
            this.execSql = obj.sql;
            this.aliasMap = obj.map;
            this.fkArray = obj.fk;
        }else{
            this.execSql = rql;
        }
        
        this.paramArr = [];
    }

    /**
     * 设置查询参数值
     * @param paramName 
     * @param value 
     */
    public setParameter(index:number,value:any){
        //补全参数个数
        if(this.paramArr.length<=index){
            for(let i=this.paramArr.length;i<=index;i++){
                this.paramArr.push(null);
            }
        }
        //对于entity，只获取其主键
        this.paramArr[index] = value instanceof BaseEntity? RelaenUtil.getIdValue(value):value;
    }

    /**
     * 设置多个参数值，从下标0开始
     * @param valueArr 值数组
     */
    public setParameters(valueArr:Array<any>){
        valueArr.forEach((value,i)=>{
            //对于entity，只获取其主键
            let v =  value instanceof BaseEntity? RelaenUtil.getIdValue(value):value;
            if(i >= this.paramArr.length){
                this.paramArr.push(v);
            }else{
                this.paramArr[i] = v;
            }
        });
    }

    /**
     * 设置开始记录位置
     * @param start     开始位置
     */
    public setStart(start:number){
        this.start = start;
    }

    /**
     * 设置获取记录数
     * @param limit     记录数
     */
    public setLimit(limit:number){
        this.limit = limit;
    }

    /**
     * 获取单个查询结果
     * 如果类型不为实体且只有一个属性值，则直接返回属性值，否则返回对象
     */
    public async getResult():Promise<any>{
        let r = await this.getResultList(0,1);
        if(r && r.length>0){
            if(r instanceof BaseEntity){
                return r[0];
            }
            let props = Object.getOwnPropertyNames(r[0]);
            //如果只有一个属性，则只返回属性值
            if(props.length === 1){
                return r[0][props[0]];
            }
            return r[0];
        }
        return null;
    }



    /**
     * 获取结果列表
     * @param start     开始索引
     * @param limit     记录数
     */
    public async getResultList(start?:number,limit?:number):Promise<Array<any>>{
        if(start >= 0){
            this.start = start;
        }
        if(limit > 0){
            this.limit = limit;
        }
        let results:any[] = await SqlExecutor.exec(this.entityManager,this.execSql,this.paramArr,this.start,this.limit);
        if(this.entityClassName && Array.isArray(results)){
            let retArr:any[] = [];
            for(let r of results){
                retArr.push(this.genEntity(r));
            }
            return retArr;
        }
        return results;
    }

    /**
     * 生成实体
     * @param r     查询结果
     * @returns     实体对象
     */
    private genEntity(r:any):IEntity{
        let ecfg:IEntityCfg = EntityFactory.getClass(this.entityClassName);
        let entity:IEntity = new ecfg.entity();
        Object.getOwnPropertyNames(r).forEach((field)=>{
            let ind:number = field.indexOf('_');
            //别名
            let alias:string = field.substr(0,ind);
            if(alias !== 't0'){
                return;
            }
            //属性名
            let propName:string = field.substr(ind+1);
            if(ecfg.columns.has(propName) && !ecfg.columns.get(propName).refName){ //属性
                entity[propName] = r[field];
            }
        });
        
        //存储外键值
        let map:Map<string,any> = new Map();
        
        if(this.fkArray.length>0){
            for(let fk of this.fkArray){
                let fn = fk.substr(fk.indexOf('_')+1);
                //去掉表别名再存储
                map.set(ecfg.columns.get(fn).name,r[fk]);
            }
        }
        this.entityManager.addCache(entity,map);
        entity.__status = EEntityState.PERSIST;
        return entity;
    }
}

export {Query}