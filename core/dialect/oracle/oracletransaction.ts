import { ConnectionManager } from "../../connectionmanager";
import { ErrorFactory } from "../../errorfactory";
import { Logger } from "../../logger";
import { Transaction } from "../../transaction";
import { EIsolationLevel } from "../../types";

/**
 * oracle 事务类
 */
export class OracleTransaction extends Transaction {

    /**
     * 设置当前事务
     * @param isolation - 事务隔离级
     */
    public async setIsolationLevel(isolationLevel: EIsolationLevel) {
        // Oracle only supports SERIALIZABLE and READ COMMITTED isolation
        if (isolationLevel === 'SERIALIZABLE' || isolationLevel === 'READ COMMITTED') {
            const sql = "SET TRANSACTION ISOLATION LEVEL " + isolationLevel;
            await ConnectionManager.provider.exec(this.conn, sql);
            Logger.log(sql);
        } else if (isolationLevel === 'READ UNCOMMITTED' || isolationLevel === 'REPEATABLE READ') {
            throw ErrorFactory.getError('0407');
        }
    }

    /**
     * 开始事务
     */
    async begin() {
        this.conn.autoCommit = false;
        super.begin();
    }

    /**
     * 事务提交
     */
    async commit() {
        try {
            await this.conn.conn.commit();
            this.conn.autoCommit = true;
            super.commit();
        } catch (err) {
            throw err;
        }
    }

    /**
     * 事务回滚
     */
    async rollback() {
        try {
            await this.conn.conn.rollback();
            this.conn.autoCommit = true;
            super.rollback();
        } catch (err) {
            throw err;
        }
    }
}