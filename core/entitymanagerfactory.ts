import { EntityManager } from "./entitymanager";

class EntityManagerFactory{
    public static createEntityManager(){
        return new EntityManager();
    }
}

export {EntityManagerFactory};