import { EEntityState } from "./entitydefine";

/**
 * 实体基类
 */
export class BaseEntity{
    /**
     * 实体状态
     */
    public state:EEntityState;

    constructor(){
        //设置状态为new
        this.state = EEntityState.NEW;
    }

    /**
     * 保存实体，如果状态为none，则修改为updated，如果为new，则保持不变
     */
    save(){
        if(this.state === EEntityState.NONE){
            this.state = EEntityState.UPDATED;
        }
    }

    /**
     * 删除实体，如果状态为new，则从实体状态改为deleted
     */
    delete(){
        this.state = EEntityState.DELETED;
    }

    /**
     * 持久化 ，如果是state为new或updated，则执行后为none，如果为deleted，则从实体管理器中删除
     */
    persist(){
        this.state = EEntityState.NONE;
    }
}
