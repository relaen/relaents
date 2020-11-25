import { EntityManager } from "./entitymanager";
import { EntityFactory } from "./entityfactory";
import { IEntityCfg } from "./entitydefine";

/**
 * 实体基类
 */
export class BaseEntity extends Object{
    constructor(){
        super();
    }

    /**
     * 保存实体，如果状态为none，则修改为updated，如果为new，则保持不变
     */
    save(em:EntityManager){
        return em.save(this);
    }

    /**
     * 删除实体，如果状态为new，则从实体状态改为deleted
     */
    delete(em:EntityManager){
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
