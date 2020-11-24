import { EntityManager } from "./entitymanager";
import { RelaenManager } from "./relaenmanager";
import { SqlExecutor } from "./sqlexecutor";

/**
 * 原生查询
 */
class NativeQuery{
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
     * 查询记录的start index
     */
    start:number;

    /**
     * 查询记录的条数限制
     */
    limit:number;

    /**
     * 构造query对象
     * @param rql       relean ql 
     * @param em        entity manager
     */
    constructor(sql:string,em:EntityManager){
        this.entityManager = em;
        this.paramArr = [];
        this.execSql = sql;
        //调试模式，输出执行的sql
        if(RelaenManager.debug){
            console.log(this.execSql);
        }
    }

    /**
     * 获取单个实体
     */
    public async getResult():Promise<any>{
        let results:any[] = await SqlExecutor.exec(this.entityManager.connection,this.execSql,this.paramArr);
        if(results.length>0){
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
        let obj = {};
        Object.getOwnPropertyNames(r).forEach(item=>{
            obj[item] = r[item];
        });
        return obj;
    }
}

export {NativeQuery}