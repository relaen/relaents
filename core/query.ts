import { EntityManager } from "./entitymanager";
import { Translator } from "./translator";
import { BaseEntity } from "./entity";
import { SqlExecutor } from "./sqlexecutor";
import { RelaenManager } from "./relaenmanager";
import { EntityFactory } from "./entityfactory";

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
     * 实体
     */
    entity:BaseEntity;

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
    aliasMap:Map<string,object> = new Map();
    /**
     * 构造query对象
     * @param rql       relean ql 
     * @param em        entity manager
     * @param entity    对应的结果实体
     */
    constructor(rql:string,em:EntityManager,entity?:BaseEntity){
        this.entityManager = em;        
        this.entity = entity;
        let obj = Translator.getQuerySql(rql);
        this.execSql = obj.sql;
        this.aliasMap = obj.map;
    }

    /**
     * 设置查询参数值
     * @param paramName 
     * @param value 
     */
    public setParameter(index:number,value:any){
        if(this.paramArr.length<=index){
            for(let i=this.paramArr.length-1;i<index;i++){
                this.paramArr.push(null);
            }
        }
        //对于entity，只获取其主键
        if(value instanceof BaseEntity){
            value = this.entityManager.getIdValue(value);
        }
        this.paramArr[index] = value;
    }

    /**
     * 设置多个参数值，从下标0开始
     * @param valueArr 值数组
     */
    public setParameters(valueArr:Array<any>){
        if(this.paramArr.length< valueArr.length){
            for(let i=this.paramArr.length-1;i<valueArr.length;i++){
                this.paramArr.push(null);
            }
        }
        valueArr.forEach((value,i)=>{
            if(value instanceof BaseEntity){
                value = this.entityManager.getIdValue(value);
            }
            this.paramArr[i] = value;
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
     * 获取结果列表
     */
    public async getResultList<T>():Promise<Array<T>>{
        //调试模式，输出执行的sql
        if(RelaenManager.debug){
            console.log(this.execSql);
        }
        let results:any[] = await SqlExecutor.exec(this.execSql,this.paramArr,this.start,this.limit);
        let retArr:any[] = [];

        for(let r of results){
            let map:Map<string,BaseEntity> = new Map();
            Object.getOwnPropertyNames(r).forEach((field)=>{
                let fa:string[] = field.split('_');
                let fo = this.aliasMap.get(fa[0]);
                if(!map.has(fa[0])){
                    let enObj:any = EntityFactory.getClass(fo['entity']);
                    map.set(fa[0],Reflect.construct(enObj.entity,[]));
                }
                let entity:any = map.get(fa[0]);
                entity[fa[1]] = r[field];
            });
            
            //给对象加上关联关系
            for(let m of this.aliasMap){
                if(m[1]['from']){
                    let mo = map.get(m[1]['from']);
                    mo[m[1]['propName']] = map.get(m[0]);
                }
            }
            
            //加入entity manager缓存
            for(let m of map){
                this.entityManager.addCache(m[1]);
            }
            
            retArr.push(map.get('t0'));
        }
        return retArr;
    }
}

export {Query}