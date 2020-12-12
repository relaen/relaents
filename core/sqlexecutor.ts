import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { Logger } from "./logger";
import { EntityManager } from "./entitymanager";

/**
 * sql执行器
 */
class SqlExecutor{
    /**
     * 执行mysql sql语句
     * @param connection    db connection
     * @param sql           待执行sql
     * @param params        参数数组
     * @param start         开始记录行
     * @param limit         最大记录行
     * @returns             执行结果或undefined
     */
    public static async exec(em:EntityManager,sql:string,params?:any[],start?:number,limit?:number):Promise<any>{
        sql = sql.trim();
        
        let r:any;
        try{
            switch(RelaenManager.dialect){
                case 'mysql':
                    r = await this.execMysql(em.connection,sql,params,start,limit);
                    break;
                case 'oracle':
                    r = await this.execOracle(em.connection,sql,params,start,limit);
                    break;
                case 'mssql':
                    r = await this.execMssql(em.connection,sql,params,start,limit);
                    break;
            }
            //操作成功，且执行增删改，则清空cache
            if(r && (sql.startsWith('insert') || sql.startsWith('update') || sql.startsWith('delete'))){
                em.clearCache();
            }
        }catch(e){
            Logger.console("[Relaen execute sql] Error:\"" + e.message + "\"");
            //只抛出异常信息
            throw e.message;
        }
        
        Logger.console("[Relaen execute sql]:\"OK\"");
        return r;
    }

    /**
     * 执行mysql sql语句
     * @param connection    db connection
     * @param sql           待执行sql
     * @param params        参数数组
     * @param start         开始记录行
     * @param limit         最大记录行
     */
    private static async execMysql(connection:Connection,sql:string,params?:any[],start?:number,limit?:number){
        if(!connection.connected){
            await connection.connect();
        }
        if(Number.isInteger(start) && start>=0 && Number.isInteger(limit) && limit>0){
            sql += ' limit ' + start + ',' + limit;
        }
        if(RelaenManager.debug){
            Logger.console("[Relaen execute sql]:\"" + sql + "\"");
            if(params){
                Logger.console("Parameters is " + JSON.stringify(params));
            }
        }
        let r:any = await new Promise((resolve,reject)=>{
            connection.conn.query(sql,params,(error,results,fields)=>{
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
    private static async execOracle(connection:Connection,sql:string,params?:any[],start?:number,limit?:number){

    }

    /**
     * 执行mssql sql语句
     * @param sql       待执行sql
     * @param params    参数数组
     * @param start     开始记录行
     * @param limit     最大记录行
     */
    private static async execMssql(connection:Connection,sql:string,params?:any[],start?:number,limit?:number){

    }
}

export {SqlExecutor}