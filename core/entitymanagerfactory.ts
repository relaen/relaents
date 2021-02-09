import { EntityManager } from "./entitymanager";
import { Connection } from "./connection";
import { getConnection } from "./connectionmanager";
import { RelaenUtil } from "./relaenutil";
import { RelaenThreadLocal } from "./threadlocal";
import { IEntity, EEntityState } from "./types";


/**
 * entity manager 工厂
 */
class EntityManagerFactory{
    /**
     * 连接map {threadId:{num:em创建次数,em:entity manager}}
     * 保证一个异步方法中只能有一个entitymanager
     */
    private static entityManagerMap:Map<number,any> = new Map();

    /**
     * 实体状态map
     */
    private static entityStatusMap:WeakMap<IEntity,EEntityState> = new WeakMap();
    /**
     * 创建 entity manager，使用后需要释放
     * @param conn  数据库连接对象
     * @returns     entitymanager
     */
    public static async createEntityManager(){
        let id:number = RelaenUtil.genId();
        let conn:Connection = await getConnection(id);
        let sid = conn.threadId;
        let em:EntityManager;
        if(!this.entityManagerMap.has(sid)){
            em = new EntityManager(conn,id);
            this.entityManagerMap.set(sid,{
                num:1,
                em:em
            });
        }else{
            let o = this.entityManagerMap.get(sid);
            o.num++;
            em = o.em;
        }
        return em;
    }

    /**
     * 关闭entitymanager
     * @param em        entitymanager
     * @param force     是否强制关闭
     */
    public static async closeEntityManager(em:EntityManager,force?:boolean){
        //获取threadId
        let sid:number = em.connection.threadId;
        if(!force){
            if(!sid || !this.entityManagerMap.has(sid)){
                return;
            }
            let o = this.entityManagerMap.get(sid);
            if(--o.num <= 0){
                force = true;
            }
        }
        if(force){
            //清除缓存
            em.clearCache();
            //从map移除
            this.entityManagerMap.delete(sid);
            //如果connection的创建者id与该entitymanager一致，则也需要释放该connection
            if(em.id && em.id === em.connection.fromId){
                await em.connection.close(true);
            }else{
                await em.connection.close();
            }
        }
    }

    /**
     * 获取当前entitymanager，使用后不用释放
     */
    public static getCurrentEntityManager():EntityManager{
        let sid = RelaenThreadLocal.getThreadId();
        if(!sid || !this.entityManagerMap.has(sid)){
            return null;
        }
        return this.entityManagerMap.get(sid).em;
    }

    /**
     * 设置实体状态
     * @param entity    实体 
     * @param state     状态
     */
    public static setEntityStatus(entity:IEntity,state:EEntityState){
        this.entityStatusMap.set(entity,state);
    }

    /**
     * 获取实体状态
     * @param entity    实体对象
     * @returns         实体状态或undefined
     */
    public static getEntityStatus(entity:IEntity):EEntityState{
        return this.entityStatusMap.get(entity);
    }
}

/**
 * 返回entity manager
 */
async function getEntityManager():Promise<EntityManager>{
    return await EntityManagerFactory.createEntityManager();
}

export {EntityManagerFactory,getEntityManager};