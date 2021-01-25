import { EntityManager } from "./entitymanager";
import { SqlExecutor } from "./sqlexecutor";
import { IEntityCfg, IEntityColumn, IEntity, EEntityState } from "./types";
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
        super(em,entityClassName);
        this.execSql = sql;
    }

    /**
     * 获取单个实体或单个属性值
     */
    public async getResult():Promise<any>{
        let results:any[] = await this.getResultList(0,1);
        if(results && results.length>0){
            let props = Object.getOwnPropertyNames(results[0]);
            //如果只有一个属性，则只返回属性值
            if(props.length === 1){
                return results[0][props[0]];
            }
            return results[0];
        }
        return null;
    }

    /**
     * 获取结果列表
     * @param start     开始索引
     * @param limit     记录数
     */
    public async getResultList(start?:number,limit?:number):Promise<any>{
        if(start >= 0){
            this.start = start;
        }
        if(limit > 0){
            this.limit = limit;
        }
        let results:any[] = await SqlExecutor.exec(this.entityManager,this.execSql,this.paramArr,this.start,this.limit);
        if(results && Array.isArray(results)){
            let arr = [];
            for(let r of results){
                arr.push(this.genOne(r));
            }
            return arr;
        }
        return results;
    }

    /**
     * 根据查询结果生成单个数据对象
     * @param r 
     */
    private genOne(r:any){
        if(this.entityClassName){
            let ecfg:IEntityCfg = EntityFactory.getClass(this.entityClassName);
            if(ecfg){  //具备该实体类，则处理为实体
                //外键map
                let entity:IEntity = new ecfg.entity();
                for(let col of ecfg.columns){
                    let c:IEntityColumn = col[1];
                    //该字段无值或是外键
                    if(r[c.name] === null || c.refName){
                        continue;
                    }
                    entity[col[0]] = r[c.name];
                }    
                
                //改变状态
                entity.__status = EEntityState.PERSIST;
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