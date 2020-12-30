import { Connection } from "../connection";
import { BaseTransaction } from "./basetransaction";

/**
 * mysql 事务类
 */
class MysqlTransaction extends BaseTransaction{
    /**
     * 开始事务
     */
    async begin(connection:Connection){
        await new Promise((resolve,reject)=>{
            connection.conn.beginTransaction((err,conn)=>{
                if(err){
                    reject(err);
                }
                resolve(null);
            });
        });
    }

    /**
     * 事务提交
     */
    async commit(connection:Connection){
        const me = this;
        await new Promise((resolve,reject)=>{
            connection.conn.commit(async (err)=>{
                if(err){
                    await me.rollback(connection.conn); 
                    reject(err);
                }
                resolve(null);
            });
        });
    }

    /**
     * 事务回滚
     */
    async rollback(connection:Connection){
        await new Promise((resolve,reject)=>{
            connection.conn.rollback((err)=>{
                if(err){
                    reject(err);
                }
                resolve(null);
            });
        });
    }
}
export {MysqlTransaction}