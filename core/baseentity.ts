import { EEntityState, IEntityCfg } from "./entitydefine";
import { EntityManager } from "./entitymanager";

/**
 * 实体基类
 */
export class BaseEntity extends Object{
    /**
     * 实体状态
     */
    public state:EEntityState;

    constructor(){
        super();
        //设置状态为new
        this.state = EEntityState.NEW;
    }

    /**
     * 保存实体，如果状态为none，则修改为updated，如果为new，则保持不变
     */
    merge(em:EntityManager){
        if(this.state === EEntityState.NONE){
            this.state = EEntityState.UPDATED;
        }
        return em.merge(this);
    }

    /**
     * 删除实体，如果状态为new，则从实体状态改为deleted
     */
    delete(em:EntityManager){
        this.state = EEntityState.DELETED;
        return em.delete(this);
    }

    /**
     * 持久化 ，如果是state为new或updated，则执行后为none，如果为deleted，则从实体管理器中删除
     */
    persist(em:EntityManager){
        this.state = EEntityState.NONE;
        return em.persist(this);
    }
}
