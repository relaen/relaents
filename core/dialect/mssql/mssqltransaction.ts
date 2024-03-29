import { ConnectionManager } from "../../connectionmanager";
import { Logger } from "../../logger";
import { Transaction } from "../../transaction";
import { EIsolationLevel } from "../../types";

/**
 * mssql 事务类
 */
export class MssqlTransaction extends Transaction {
    /**
     * 实际的transaction
     */
    private tx: any;

    /**
     * 构造器
     * @param conn -      connection 
     */
    constructor(conn: any) {
        super(conn);
        //创建实际的transaction
        this.tx = conn.conn.transaction();
    }

    /**
     * 设置当前事务
     * @param isolation - 事务隔离级
     */
    public async setIsolationLevel(isolationLevel: EIsolationLevel) {
        if (isolationLevel) {
            const sql = "SET TRANSACTION ISOLATION LEVEL " + isolationLevel;
            await ConnectionManager.provider.exec(this.conn, sql);
            Logger.log(sql);
        }
    }

    /**
     * 开始事务
     */
    async begin() {
        await this.tx.begin(this.isolation);
        super.begin();
    }

    /**
     * 提交事务
     */
    async commit() {
        await this.tx.commit();
        super.commit();
    }

    /**
     * 事务回滚
     */
    async rollback() {
        await this.tx.rollback();
        super.rollback();
    }
}
