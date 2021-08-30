import { Logger } from "../../logger";
import { IsolationLevel, Transaction } from "../../transaction";

/**
 * oracle 事务类
 * @since 0.3.0
 */
export class OracleTransaction extends Transaction {

    /**
     * 开始事务
     */
    async begin(isolationLevel?: IsolationLevel) {
        // Oracle only supports SERIALIZABLE and READ COMMITTED isolation
        if (isolationLevel === "SERIALIZABLE" || isolationLevel === "READ COMMITTED") {
            let sql = "SET TRANSACTION ISOLATION LEVEL " + isolationLevel;
            await this.conn.conn.execute(sql);
            Logger.log(sql);
        }
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