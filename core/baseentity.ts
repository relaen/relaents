import { EntityManager } from "./entitymanager";
import { EntityFactory } from "./entityfactory";
import { IEntityCfg } from "./entitydefine";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { ErrorFactory } from "./errorfactory";
import { getConnection } from "./connectionmanager";

/**
 * 实体基类
 */
export class BaseEntity extends Object{
    constructor(){
        super();
    }

    /**
     * 保存实体
     * @param em    entity manager
     * @returns     保存后的实体
     */
    async save(em?:EntityManager):Promise<any>{
        if(!em){
            em = await EntityManagerFactory.createEntityManager();
        }
        if(!em){
            throw ErrorFactory.getError('0300');
        }
        return em.save(this);
    }

    /**
     * 删除实体
     * @param em    entity manager
     */
    async delete(em?:EntityManager){
        if(!em){
            em = await EntityManagerFactory.createEntityManager();
        }
        if(!em){
            throw ErrorFactory.getError('0300');
        }
        return em.delete(this);
    }


    /**
     * 对比
     * @param entity    被比较entity
     * @returns         如果相同，则返回true，否则返回false
     */
    compare(entity:BaseEntity):boolean{
        let orm:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        for(let c of orm.columns){
            let fn:string = c[0];
            if(this[fn] !== entity[fn]){
                return false;
            }
        }
        return true;
    }
}
