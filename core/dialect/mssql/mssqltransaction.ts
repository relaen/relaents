import { Transaction } from "../../transaction";

/**
 * mssql 事务类
 * @since 0.3.0
 */
export class MssqlTransaction extends Transaction {
    /**
     * 实际的transaction
     */
    private tr:any;

    /**
     * 构造器
     * @param conn      connection 
     */
    constructor(conn:any){
        super(conn);
        //创建实际的transaction
        this.tr = conn.conn.transaction();
    }
    /**
     * 开始事务
     */
    async begin() {
        await this.tr.begin();
        super.begin();
    }

    /**
     * 提交事务
     */
    async commit() {
        await this.tr.commit();
        super.commit();
    }

    /**
     * 事务回滚
     */
    async rollback() {
        await this.tr.rollback();
        super.rollback();
    }
}
