import { RelaenManager } from "../relaenmanager";
import { Connection } from "../connection";
import { Logger } from "../logger";
import { MysqlTransaction } from "./mysqltransaction";
import { BaseTransaction } from "./basetransaction";

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


export {Transaction}
