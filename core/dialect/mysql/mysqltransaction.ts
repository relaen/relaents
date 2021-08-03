import { Transaction } from "../../transaction";

/**
 * mysql 事务类
 * @since 0.3.0
 */
class MysqlTransaction extends Transaction{
    /**
     * 开始事务
     */
    public async begin(){
        await new Promise((resolve,reject)=>{
            this.conn.conn.beginTransaction((err)=>{
                if(err){
                    return reject(err);
                }
                super.begin();
                resolve(null);
            });
        });
    }

    /**
     * 事务提交
     */
    public async commit(){
        await new Promise((resolve,reject)=>{
            this.conn.conn.commit(async (err)=>{
                if(err){
                    await this.rollback();
                    return reject(err);
                }
                super.commit();
                resolve(null);
            });
        });
    }

    /**
     * 事务回滚
     */
    public async rollback(){
        await new Promise((resolve,reject)=>{
            this.conn.conn.rollback((err)=>{
                if(err){
                    return reject(err);
                }
                super.rollback();
                resolve(null);
            });
        });
    }
}


export{MysqlTransaction}