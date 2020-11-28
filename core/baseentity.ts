import { EntityManager } from "./entitymanager";
import { EntityFactory } from "./entityfactory";
import { IEntityCfg, IEntity, EEntityState } from "./entitydefine";
import { EntityManagerFactory } from "./entitymanagerfactory";

/**
 * 实体基类
 */
export class BaseEntity extends Object implements IEntity{
    /**
     * 状态
     */
    public __status:EEntityState;

    constructor(){
        super();
        //设置新建状态
        this.__status = EEntityState.NEW;
    }

    /**
     * 保存实体
     * @param em    entity manager
     * @returns     保存后的实体
     */
    async save(ignoreUndefinedValue?:boolean):Promise<any>{
        let em:EntityManager = await EntityManagerFactory.createEntityManager();
        return em.save(this,ignoreUndefinedValue);
    }

    /**
     * 删除实体
     * @param em    entity manager
     */
    async delete(){
        let em:EntityManager = await EntityManagerFactory.createEntityManager();
        return em.delete(this);
    }

    /**
     * 对比
     * @param entity    被比较entity
     * @returns         如果相同，则返回true，否则返回false
     */
    compare(entity:IEntity):boolean{
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
