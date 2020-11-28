import { EntityManager } from "./entitymanager";
import { SqlExecutor } from "./sqlexecutor";
import { BaseEntity } from "./baseentity";
import { IEntityCfg, IEntityColumn, IEntity } from "./entitydefine";
import { EntityFactory } from "./entityfactory";
import { Query } from "./query";

/**
 * 原生查询
 */
class NativeQuery extends Query{
    /**
     * 构造query对象
     * @param rql               relean ql 
     * @param em                entity manager
     * @param entityClassName   实体类名
     */
    constructor(sql:string,em:EntityManager,entityClassName?:string){
        super(sql,em,entityClassName,true);
    }

    /**
     * 获取单个实体
     */
    public async getResult():Promise<any>{
        let results:any[] = await SqlExecutor.exec(this.entityManager.connection,this.execSql,this.paramArr);
        if(results && results.length>0){
            return this.genOne(results[0]);
        }
        return null;
    }

    /**
     * 获取结果列表
     * @param start     开始索引
     * @param limit     记录数
     */
    public async getResultList<T>(start?:number,limit?:number):Promise<Array<T>>{
        if(start){
            this.start = start;
        }
        if(limit){
            this.limit = limit;
        }
        let results:any[] = await SqlExecutor.exec(this.entityManager.connection,this.execSql,this.paramArr,this.start,this.limit);
        let arr = [];
        for(let r of results){
            arr.push(this.genOne(r));
        }
        return arr;
    }

    /**
     * 根据查询结果生成单个数据对象
     * @param r 
     */
    private genOne(r:any){
        if(this.entityClassName){
            let ecfg:IEntityCfg = EntityFactory.getClass(this.entityClassName);
            if(ecfg){  //具备该实体类，则处理为实体
                let fkMap:Map<string,any> = new Map();
                let entity:IEntity = new ecfg.entity();
                for(let col of ecfg.columns){
                    let c:IEntityColumn = col[1];
                    //该字段无值
                    if(r[c.name] === undefined){
                        continue;
                    }
                    if(c.refName){ //外键 需要保存到外键map
                        fkMap.set(c.name,r[c.name]);
                    }else{      //自有属性
                        entity[col[0]] = r[c.name];
                    }
                }    
                this.entityManager.addCache(entity,fkMap);
                return entity;
            }
        }
        let obj = {};
        Object.getOwnPropertyNames(r).forEach(item=>{
            obj[item] = r[item];
        });
        return obj;
    }
}

export {NativeQuery}