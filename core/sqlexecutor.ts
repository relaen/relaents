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
    public static async exec(sql:string):Promise<any>{
        switch(RelaenManager.dialect){
            case 'mysql':
                return await this.execMysql(sql);
            case 'oracle':

            case 'mssql':
        }

    }

    /**
     * 执行mysql sql语句
     * @param sql 
     */
    private static async execMysql(sql:string){
        let conn = await ConnectionManager.getConnection();
        let r:any = await new Promise((resolve,reject)=>{
            conn.query(sql,(error,results,fields)=>{
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
     * @param sql 
     */
    private static async execOracle(sql:string){

    }

    /**
     * 执行mssql sql语句
     * @param sql 
     */
    private static async execMssql(sql:string){

    }
}

export {SqlExecutor}