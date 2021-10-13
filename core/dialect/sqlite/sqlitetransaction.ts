import { Transaction } from "../../transaction";

/**
 * Sqlite 事务类
 * @since 0.4.0
 */
class SqliteTransaction extends Transaction {
    /**
     * 开始事务
     */
    public async begin(sqliteTransaction?: 'immediate' | 'exclusive') {
        await new Promise((resolve, reject) => {
            let sql = 'begin';
            if (sqliteTransaction === 'immediate' || sqliteTransaction === 'exclusive') {
                sql += ' ' + sqliteTransaction;
            }
            this.conn.conn.run(sql, (err) => {
                if (err) {
                    return reject(err);
                }
                super.begin();
                resolve(null);
            });
        });
    }

    public async beginImmediate() {

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