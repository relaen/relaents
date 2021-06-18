import { Transaction } from "../../transaction";

/**
 * oracle 事务类
 * @since 0.3.0
 */
export class OracleTransaction extends Transaction {

    /**
     * 开始事务
     */
    async begin() {
        // transaction 中引用原conn
        this.conn['autoCommit'] = false;
        super.begin();
    }

    /**
     * 事务提交
     */
    async commit() {
        try {
            await this.conn.commit();
            this.conn['autoCommit'] = true;
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
            await this.conn.rollback();
            this.conn['autoCommit'] = true;
            super.rollback();
            return null;
        } catch (err) {
            throw err;
        }
    }
}