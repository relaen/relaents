import { ConnectionManager } from "../../connectionmanager";
import { Logger } from "../../logger";
import { Transaction } from "../../transaction";
import { EIsolationLevel } from "../../types";

/**
 * postgres 事务类
 */
export class PostgresTransaction extends Transaction {

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
     * 事务开始
     */
    async begin() {
        await this.conn.conn.query('begin');
        super.begin();
    }

    /**
     * 事务提交
     */
    async commit() {
        await this.conn.conn.query('commit');
        super.commit();
    }

    /**
     * 事务回滚
     */
    async rollback() {
        await this.conn.conn.query('rollback');
        super.rollback();
    }
}