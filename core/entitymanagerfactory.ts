import { EntityManager } from "./entitymanager";
import { Connection } from "./connection";
import { BaseEntity } from "./baseentity";

/**
 * entity manager 工厂
 */
class EntityManagerFactory{
    /**
     * 创建 entity manager
     * @param conn  数据库连接对象
     * @returns     entitymanager
     */
    public static createEntityManager(conn:Connection){
        return new EntityManager(conn);
    }
}

export {EntityManagerFactory};