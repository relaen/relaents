import { Connection } from "../connection";

class BaseTransaction{
    /**
     * 事务开始
     */
    async begin(connection:Connection){}
    /**
     * 事务提交,继承类需要重载
     */
    async commit(connection:Connection){}

    /**
     * 事务回滚,继承类需要重载
     */
    async rollback(connection:Connection){}
}
export {BaseTransaction}