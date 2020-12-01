import { RelaenManager } from "../relaenmanager";
import { Connection } from "../connection";
import { Logger } from "../logger";

/**
 * 事务基类
 */
class Transaction{
    /**
     * 连接
     */
    private connection:Connection;
    /**
     * 源transaction
     */
    private tr:BaseTransaction;

    constructor(connection){
        this.connection = connection;
    }

    /**
     * 事务开始
     */
    async begin(){
        if(!this.connection.connected){
            await this.connection.connect();
        }
        Logger.console('[Relaen]:Transaction is started!');
        switch(RelaenManager.dialect){
            case 'mysql':
                this.tr = new MysqlTransaction();
                break;
            case 'oracle':

            case 'mssql':
        }

        await this.tr.begin(this.connection);
    }

    /**
     * 提交
     */
    async commit(){
        if(this.tr && this.connection.connected){
            await this.tr.commit(this.connection);
        }
        Logger.console('[Relaen]:Transaction is commited!');
    }

    /**
     * 回滚
     */
    async rollback(){
        if(this.tr && this.connection.connected){
            await this.tr.rollback(this.connection);
        }
        Logger.console('[Relaen]:Transaction is rollbacked!');
    }
}


class BaseTransaction{
    /**
     * 事务开始
     */
    async begin(connection:Connection){}
    /**
     * 事务提交,继承类需要重载
     */
    async commit(connection:Connection){}

    /**
     * 事务回滚,继承类需要重载
     */
    async rollback(connection:Connection){}
}

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
        await new Promise((resolve,reject)=>{
            connection.conn.commit((err)=>{
                if(err){
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

export {Transaction}
