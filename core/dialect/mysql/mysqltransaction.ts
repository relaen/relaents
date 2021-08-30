import { rejects } from "assert";
import { Logger } from "../../logger";
import { IsolationLevel, Transaction } from "../../transaction";

/**
 * mysql 事务类
 * @since 0.3.0
 */
class MysqlTransaction extends Transaction {
    /**
     * 开始事务
     */
    public async begin(isolationLevel?: IsolationLevel) {
        if (isolationLevel) {
            await new Promise((resolve, reject) => {
                let sql = "SET TRANSACTION ISOLATION LEVEL " + isolationLevel;
                this.conn.conn.query(sql, (err) => {
                    if (err) {
                        return reject(err);
                    }
                    Logger.log(sql)
                    resolve(null);
                });
            });
        }
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
     */
    public async commit() {
        await new Promise((resolve, reject) => {
            this.conn.conn.commit(async (err) => {
                if (err) {
                    await this.rollback();
                    return reject(err);
                }
                // TODO 还原
                super.commit();
                resolve(null);
            });
        });
    }

    /**
     * 事务回滚
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