import { EntityManager } from "./entitymanager";
import { Connection } from "./connection";
import { getConnection } from "./connectionmanager";
import { RelaenUtil } from "./relaenutil";

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
     * 创建 entity manager
     * @param conn  数据库连接对象
     * @returns     entitymanager
     */
    public static async createEntityManager(){
        let id:number = RelaenUtil.genId();
        let conn:Connection = await getConnection(id);
        let sid = conn.threadId;
        let em:EntityManager;
        if(!this.entityManagerMap.has(id)){
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
}

/**
 * 返回entity manager
 */
async function getEntityManager():Promise<EntityManager>{
    return await EntityManagerFactory.createEntityManager();
}

export {EntityManagerFactory,getEntityManager};