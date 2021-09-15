import { ConnectionManager } from "../../connectionmanager";
import { Logger } from "../../logger";
import { IsolationLevel, Transaction } from "../../transaction";

/**
 * mariadb 事务类
 * @since 0.3.0
 */
class MariadbTransaction extends Transaction {

    /**
     * 设置当前事务
     * @param isolation 事务隔离级
     */
    public async setisolationLevel(isolationLevel: IsolationLevel) {
        if (isolationLevel) {
            let sql = "SET TRANSACTION ISOLATION LEVEL " + isolationLevel;
            await ConnectionManager.provider.exec(this.conn, sql);
            Logger.log(sql);
        }
    }

    /**
     * 开始事务
     */
    public async begin() {
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