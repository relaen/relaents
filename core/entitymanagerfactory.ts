import { EntityManager } from "./entitymanager";
import { Connection } from "./connection";
import { ThreadLocal } from "./threadlocal";
import { ErrorFactory } from "./errorfactory";
import { getConnection } from "./connectionmanager";
import { connect } from "net";
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
        if(!this.entityManagerMap.has(sid)){ //
            em = new EntityManager(conn,id);
            this.entityManagerMap.set(id,{
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
        if(!force){
            //获取threadId
            let sid:number = em.connection.threadId;
            if(!sid || !this.entityManagerMap.has(sid)){
                return;
            }
            let o = this.entityManagerMap.get(sid);
            if(--o.num <= 0){
                this.entityManagerMap.delete(sid);
                force = true;
            }
        }
        if(force){
            //清除缓存
            em.clearCache();
            //如果connection的创建者id与该entitymanager一致，则也需要释放该connection
            if(em.id && em.id === em.connection.fromId){
                await em.connection.close(true);
            }else{
                await em.connection.close();
            }
        }
    }

    /**
     * 获取当前实体管理器(线程内唯一)
     */
    // public static getCurrentEntityManager(){
    //     //获取threadId
    //     let sid:number = ThreadLocal.getThreadId();
    //     if(sid && this.entityManagerMap.has(sid)){
    //         let o = this.entityManagerMap.get(sid);
    //         if(o.em){
    //             return o.em;
    //         }
    //     }
    //     return null;
    // }
}

/**
 * 返回entity manager
 */
async function getEntityManager():Promise<EntityManager>{
    // let sid:number = ThreadLocal.getThreadId();
    // let conn:Connection = await getConnection();
    return await EntityManagerFactory.createEntityManager();
}

export {EntityManagerFactory,getEntityManager};