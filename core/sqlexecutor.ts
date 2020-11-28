import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { Logger } from "./logger";

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
    public static async exec(conn:Connection,sql:string,params?:any[],start?:number,limit?:number):Promise<any>{
        if(RelaenManager.debug){
            Logger.console("[Execute relaen sql]:\"" + sql + "\"");
        }
        let r:any;
        try{
            switch(RelaenManager.dialect){
                case 'mysql':
                    r = await this.execMysql(conn,sql,params,start,limit);
                case 'oracle':
                    r = await this.execOracle(conn,sql,params,start,limit);
                case 'mssql':
                    r = await this.execMssql(conn,sql,params,start,limit);
            }
        }catch(e){
            Logger.console("[Execute relaen sql error]:\"" + e + "\"");
            return;
        }
        Logger.console("[Execute relaen sql]:\"OK\"");
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
        if(start && limit){
            sql += ' limit ' + start + ',' + limit;
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