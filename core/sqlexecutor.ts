import ConnectionManager from "./connectionmanager";
import { RelaenManager } from "./relaenmanager";

/**
 * sql执行器
 */
class SqlExecutor{
    /**
     * 执行sql
     * @param sql 
     */
    public static async exec(sql:string,params?:any[],start?:number,limit?:number):Promise<any>{
        switch(RelaenManager.dialect){
            case 'mysql':
                return await this.execMysql(sql,params,start,limit);
            case 'oracle':
                return await this.execOracle(sql,params,start,limit);
            case 'mssql':
                    return await this.execMssql(sql,params,start,limit);
        }

    }

    /**
     * 执行mysql sql语句
     * @param sql       待执行sql
     * @param params    参数数组
     * @param start     开始记录行
     * @param limit     最大记录行
     */
    private static async execMysql(sql:string,params?:any[],start?:number,limit?:number){
        let conn = await ConnectionManager.getConnection();
        if(start && limit){
            sql += ' limit ' + start + ',' + limit;
        }
        let r:any = await new Promise((resolve,reject)=>{
            conn.query(sql,params,(error,results,fields)=>{
                if(error){
                    reject(error);
                }
                resolve(results);
            });
        });
        if(r.insertId){
            return r.insertId;
        }
        return r;
    }

    /**
     * 执行oracle sql语句
     * @param sql       待执行sql
     * @param params    参数数组
     * @param start     开始记录行
     * @param limit     最大记录行
     */
    private static async execOracle(sql:string,params?:any[],start?:number,limit?:number){

    }

    /**
     * 执行mssql sql语句
     * @param sql       待执行sql
     * @param params    参数数组
     * @param start     开始记录行
     * @param limit     最大记录行
     */
    private static async execMssql(sql:string,params?:any[],start?:number,limit?:number){

    }
}

export {SqlExecutor}