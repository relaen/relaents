import { EntityManager } from "./entitymanager";
import { EntityFactory } from "./entityfactory";
import { IEntityCfg, IEntity, EEntityState } from "./entitydefine";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { RelaenUtil } from "./relaenutil";
import { getConnection } from "./connectionmanager";
import { Connection } from "./connection";
import { ENGINE_METHOD_CIPHERS } from "constants";

/**
 * 实体基类
 */
export class BaseEntity extends Object implements IEntity{
    /**
     * 状态
     */
    public __status:EEntityState;

    constructor(){
        super();
        //设置新建状态
        this.__status = EEntityState.NEW;
    }

    /**
     * 保存实体
     * @param em                    entity manager
     * @param ignoreUndefinedValue  忽略undefined值，针对update时有效
     * @returns                     保存后的实体
     */
    public async save(ignoreUndefinedValue?:boolean):Promise<IEntity>{
        let em:EntityManager = EntityManagerFactory.getCurrentEntityManager();
        let conn:Connection;
        if(em === null){
            conn = await getConnection();
            em = EntityManagerFactory.createEntityManager(conn);
        }
        await em.save(this,ignoreUndefinedValue);
        if(conn){
            em.close();
            conn.close();
        }
        return this;
    }

    /**
     * 删除实体
     * @param em    entity manager
     */
    public async delete():Promise<IEntity>{
        let em:EntityManager = EntityManagerFactory.getCurrentEntityManager();
        let conn:Connection;
        if(em === null){
            conn = await getConnection();
            em = EntityManagerFactory.createEntityManager(conn);
        }
        em.delete(this);
        if(conn){
            em.close();
            conn.close();
        }
        return this;
    }

    /**
     * 对比
     * @param obj   简化后的实体值对象
     * @returns     如果相同，则返回true，否则返回false
     */
    public compare(obj:object):boolean{
        let ecfg:IEntityCfg = EntityFactory.getClass(this.constructor.name);
        for(let col of ecfg.columns){
            //字段对象
            let fo = col[1];
            //字段名
            let fn = col[0];
            if(!fo.refName &&  obj[fn] !== this[fn] || 
                obj[fn] !== RelaenUtil.getIdValue(this)){
                    return false;
            }
        }
        return true;
    }


    /**
     * 浅拷贝，外键对象只拷贝主键值
     */
    public clone():object{
        let obj = new Object();
        let ecfg:IEntityCfg = EntityFactory.getClass(this.constructor.name);
        for(let col of ecfg.columns){
            if(this[col[0]] === undefined){
                continue;
            }
            //字段对象
            let fo = col[1];
            if(fo.refName){ //外键只取id
                if(this[col[0]] !== null){
                    obj[col[0]] = RelaenUtil.getIdValue(this[col[0]]);
                }
            }else{
                //null也需要保留
                obj[col[0]] = this[col[0]];
            }
        }
        //保留className
        obj['__entityClassName'] = this.constructor.name;
        return obj;
    }
}
