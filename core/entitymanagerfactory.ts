import { EntityManager } from "./entitymanager";
import { Connection } from "./connection";

class EntityManagerFactory{
    public static createEntityManager(conn:Connection){
        return new EntityManager(conn);
    }
}

export {EntityManagerFactory};