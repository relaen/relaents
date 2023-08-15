import { Transaction } from "../../transaction";
import { ESqliteTransactionType } from "../../types";

/**
 * Sqlite 事务类
 */
class SqliteTransaction extends Transaction {

    //sqlite事务类型
    private type: ESqliteTransactionType;

    /**
     * 设置sqlite事务类型（仅sqlite事务使用）
     * @param type -  事务类型
     */
    public setType(type: ESqliteTransactionType) {
        if (type) {
            this.type = type;
        }
    }

    /**
     * 开始事务
     * @param sqliteTransaction -     事务类型
     */
    public async begin() {
        await new Promise((resolve, reject) => {
            const sql = this.type ? 'begin ' + this.type : 'begin';
            this.conn.conn.run(sql, (err) => {
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
            this.conn.conn.run('commit', (err) => {
                if (err) {
                    return reject(err);
                }
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
            this.conn.conn.run('rollback', (err) => {
                if (err) {
                    return reject(err);
                }
                super.commit();
                resolve(null);
            });
        });
    }
}


export { SqliteTransaction }