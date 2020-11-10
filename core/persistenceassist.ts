import { BaseEntity } from "./entity";
import { RedisManager } from "./redismanager";
import { EEntityState } from "./entitydefine";
import { Translator } from "./translator";
import ConnectionManager from "./connectionmanager";
import {SqlExecutor} from "./sqlexecutor";

/**
 * 持久化助手
 */
class PersistenceAssist{
    public static persistAll(){

    }

    /**
     * 持久化单个实体
     * @param entity 
     */
    public static async persistOne(entity:BaseEntity){
        switch(entity.state){
            case EEntityState.NEW:
                let r = await SqlExecutor.exec(Translator.entityToInsert(entity));
                //设置id
            case EEntityState.UPDATED:
                SqlExecutor.exec(Translator.entityToUpdate(entity));
                
            break;
            case EEntityState.DELETED:

            break;
        }
        //处理关联对象
        return entity;
    }
}