import { BaseEntity } from "./entity";
import { RedisManager } from "./redismanager";
import { EEntityState } from "./entitydefine";
import { Translator } from "./translator";
import ConnectionManager from "./connectionmanager";

/**
 * 持久化助手
 */
class PersistenceAssist{
    public static persistAll(){

    }

    /**
     * 持久化单个实体
     * @param entity 
     */
    public static persistOne(entity:BaseEntity){
        switch(entity.state){
            case EEntityState.NEW:
                Translator.entityToInsert(entity);
            case EEntityState.UPDATED:

            break;
            case EEntityState.DELETED:

            break;
        }
        //处理子对象
    }

    /**
     * 执行sql
     * @param sql 
     */
    public static async execSql(sql:string):Promise<any>{
        switch(RelaenManager.product){
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
    public static async execMysql(sql:string){
        let conn = await ConnectionManager.getConnection();
        return await conn.query(sql);
    }


    /**
     * 执行oracle sql语句
     * @param sql 
     */
    public static async execOracle(sql:string){

    }

    /**
     * 执行mssql sql语句
     * @param sql 
     */
    public static async execMssql(sql:string){

    }
}