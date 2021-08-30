import { IsolationLevel, Transaction } from "../../transaction";

/**
 * mariadb 事务类
 * @since 0.3.0
 */
class MariadbTransaction extends Transaction {
    /**
     * 开始事务
     */
    public async begin(isolationLevel?: IsolationLevel) {
        if (isolationLevel) {
            await this.conn.conn.query('SET TRANSACTION LEVEL ' + isolationLevel);
        }
        await this.conn.conn.beginTransaction();
        super.begin();
    }

    /**
     * 事务提交
     */
    public async commit() {
        await this.conn.conn.commit();
        super.commit();
    }

    /**
     * 事务回滚
     */
    public async rollback() {
        await this.conn.conn.rollback();
        super.rollback();
    }
}

export { MariadbTransaction }