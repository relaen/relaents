import { Transaction } from "../../transaction";

/**
 * Sqlite 事务类
 * @since 0.3.0
 */
class SqliteTransaction extends Transaction {
    /**
     * 开始事务
     */
    public async begin(isolationLevel?: any) {
        await new Promise((resolve, reject) => {
            let sql = 'begin';
            if (isolationLevel === 'immediate' || isolationLevel === 'exclusive') {
                sql += isolationLevel;
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