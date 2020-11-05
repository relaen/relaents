import { IEntityPKey, IEntityColumn, IEntityCfg, IEntityRelation } from "./entitydefine";


/**
 * 实体管理器
 */
class EntityManager{
    
    /**
     * 同步到数据库
     * @param entity 
     */
    public async persist<T>(entity:T):Promise<T>{
        
        return entity;
    }

    /**
     * 保存实体
     * @param entity    待存储实体
     * @returns         保存后的实体
     */
    public async save<T>(entity:T):Promise<T>{
        
        return entity;
    }

    /**
     * 删除实体
     * @param entity    待删除实体
     * @returns         是否删除成功
     */
    public async delete<T>(entity:T):Promise<boolean>{

        return true;
    }


    /**
     * 通过id查找实体
     * @param entity 
     * @param idValue 
     */
    public async findById<T>(entity:T,idValue:any):Promise<T>{
        return entity;
    }

    /**
     * 查询实体
     */
    public async query(){

    }

}

export{EntityManager}