import { EntityManager } from "./entitymanager";
import { Connection } from "./connection";
import { ThreadLocal } from "./threadlocal";
import { ErrorFactory } from "./errorfactory";

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
    public static createEntityManager(conn:Connection){
        if(!conn){
            throw ErrorFactory.getError("0250");
        }
        //获取threadId
        let sid:number = ThreadLocal.getThreadId();
        if(!sid){
            sid = ThreadLocal.newThreadId();
        }
        let em:EntityManager;
        if(!this.entityManagerMap.has(sid)){ //
            em = new EntityManager(conn);
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
     * @param em    entitymanager
     */
    public static closeEntityManager(em:EntityManager){
        //获取threadId
        let sid:number = ThreadLocal.getThreadId();
        if(!sid || !this.entityManagerMap.has(sid)){
            return;
        }
        let o = this.entityManagerMap.get(sid);
        if(--o.num <= 0){
            this.entityManagerMap.delete(sid);
        }
    }

    /**
     * 获取当前实体管理器(线程内唯一)
     */
    public static getCurrentEntityManager(){
        //获取threadId
        let sid:number = ThreadLocal.getThreadId();
        if(sid && this.entityManagerMap.has(sid)){
            let o = this.entityManagerMap.get(sid);
            if(o.em){
                return o.em;
            }
        }
        return null;
    }
}

export {EntityManagerFactory};