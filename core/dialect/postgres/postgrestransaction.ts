import { IsolationLevel, Transaction } from "../../transaction";

/**
 * postgres 事务类
 * @since 0.3.0
 */
export class PostgresTransaction extends Transaction {
    /**
     * 事务开始
     */
    async begin(isolationLevel?: IsolationLevel) {
        if (isolationLevel) {
            await this.conn.conn.query("SET TRANSACTION ISOLATION LEVEL " + isolationLevel);
        }
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