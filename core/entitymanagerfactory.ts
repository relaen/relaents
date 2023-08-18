import { EntityManager } from "./entitymanager";
import { Connection } from "./connection";
import { getConnection } from "./connectionmanager";
import { RelaenUtil } from "./relaenutil";
import { RelaenThreadLocal } from "./threadlocal";
import { IEntity, EEntityState } from "./types";
import { RelaenManager } from "./relaenmanager";


/**
 * entity manager 工厂
 */
class EntityManagerFactory {
    /**
     * 连接map 
     * @remarks
     * 用于保证一个异步方法中只能有一个entitymanager，结构为：
     * ```json
     * {
     *  threadId:{
     *      num:em创建次数,
     *      em:entity manager
     *  }
     * }
     * ```
     */  
    private static entityManagerMap: Map<number, {num:number,em:EntityManager}> = new Map();

    /**
     * 实体状态map
     */
    private static entityStatusMap: WeakMap<IEntity, EEntityState> = new WeakMap();

    /**
     * 创建 entity manager，使用后需要释放
     * @param isCache -   是否开启缓存
     * @returns         实体管理器
     */
    public static async createEntityManager(isCache?: boolean): Promise<EntityManager> {
        const id: number = RelaenUtil.genId();
        const conn: Connection = await getConnection(id);
        const sid = conn.threadId;
        let em: EntityManager;
        if (!this.entityManagerMap.has(sid)) {
            em = new EntityManager(conn, id, isCache);
            this.entityManagerMap.set(sid, {
                num: 1,
                em: em
            });
        } else {
            const o = this.entityManagerMap.get(sid);
            o.num++;
            em = o.em;
        }
        return em;
    }

    /**
     * 关闭entitymanager
     * @param em -        实体管理器
     * @param force -     是否强制关闭
     */
    public static async closeEntityManager(em: EntityManager, force?: boolean): Promise<void> {
        //获取threadId
        const sid: number = em.connection.threadId;
        if (!sid || !this.entityManagerMap.has(sid)) {
            return;
        }
        if (!force) {
            const o = this.entityManagerMap.get(sid);
            if (--o.num <= 0) {
                force = true;
            }
        }
        if (force) {
            //清除缓存
            em.clearCache();
            //从map移除
            this.entityManagerMap.delete(sid);
            //如果connection的创建者id与该entitymanager一致，则也需要释放该connection
            if (em.id && em.id === em.connection.fromId) {
                await em.connection.close(true);
            } else {
                await em.connection.close();
            }
        }
    }

    /**
     * 根据id获取entity manager
     * @param id    entity manager id
     * @returns     entity manager
     */
    public static getEntityManager(id:number):EntityManager{
        const obj = this.entityManagerMap.get(id);
        if(obj){
            return obj.em;
        }
    }

    /**
     * 获取当前entitymanager，使用后不用释放
     * @returns     实体管理器
     */
    public static getCurrentEntityManager(): EntityManager {
        const sid = RelaenThreadLocal.getThreadId();
        if (!sid || !this.entityManagerMap.has(sid)) {
            return null;
        }
        return this.entityManagerMap.get(sid).em;
    }

    
    /**
     * 关闭当前entity manager
     */
    public static closeCurrentEntityManager(){
        const em:EntityManager = this.getCurrentEntityManager();
        if(em){
            this.closeEntityManager(em);
        }
    }

    /**
     * 设置实体状态
     * @param entity -    实体 
     * @param state -     状态
     */
    public static setEntityStatus(entity: IEntity, state: EEntityState) {
        this.entityStatusMap.set(entity, state);
    }

    /**
     * 获取实体状态
     * @param entity -    实体对象
     * @returns         实体状态或undefined
     */
    public static getEntityStatus(entity: IEntity): EEntityState {
        return this.entityStatusMap.get(entity);
    }
}

/**
 * 返回entity manager
 * @param isCache -   是否开启缓存
 * @returns         实体管理器
 */
async function getEntityManager(isCache?: boolean): Promise<EntityManager> {
    isCache = typeof isCache === "boolean" ? isCache : RelaenManager.cache;
    return await EntityManagerFactory.createEntityManager(isCache);
}

export { EntityManagerFactory, getEntityManager };