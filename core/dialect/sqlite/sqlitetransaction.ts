import { Transaction } from "../../transaction";

/**
 * Sqlite 事务类
 * @since 0.3.0
 */
class SqliteTransaction extends Transaction {
    /**
     * 开始事务
     */
    public async begin() {
        await new Promise((resolve, reject) => {
            this.conn.conn.run('begin', (err) => {
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