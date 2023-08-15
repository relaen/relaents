import { ConnectionManager } from "../../connectionmanager";
import { Logger } from "../../logger";
import { Transaction } from "../../transaction";
import { EIsolationLevel } from "../../types";

/**
 * mysql 事务类
 */
class MysqlTransaction extends Transaction {

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
     * @throws      开始事务错误
     */
    public async begin() {
        await new Promise((resolve, reject) => {
            this.conn.conn.beginTransaction((err) => {
                if (err) {
                    return reject(err);
                }
                super.begin();
                resolve(null);
            });
        });
    }

    /**
     * 事务提交
     * @throws      提交事务错误
     */
    public async commit() {
        await new Promise((resolve, reject) => {
            this.conn.conn.commit(async (err) => {
                if (err) {
                    await this.rollback();
                    return reject(err);
                }
                super.commit();
                resolve(null);
            });
        });
    }

    /**
     * 事务回滚
     * @throws      回滚事务错误
     */
    public async rollback() {
        await new Promise((resolve, reject) => {
            this.conn.conn.rollback((err) => {
                if (err) {
                    return reject(err);
                }
                super.rollback();
                resolve(null);
            });
        });
    }
}


export { MysqlTransaction }