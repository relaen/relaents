import { IEntityCfg, IEntityRelation, ERelationType, IEntityColumn, IEntity } from "./entitydefine";
import { EntityFactory } from "./entityfactory";
import { EntityManager } from "./entitymanager";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { Query } from "./query";
import { ErrorFactory } from "./errorfactory";
import { NativeQuery } from "./nativequery";

/**
 * 实体代理类
 */
class EntityProxy{
    /**
     * 获取实体关联对象
     * @param entity    实体
     * @param propName  关联属性名
     */
    public static async get(entity:IEntity,propName:string):Promise<any>{
        let em:EntityManager = await EntityManagerFactory.createEntityManager();
        let pv = entity[propName];
        if(pv !== undefined && pv !== null){
            return pv;
        }

        let eo:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
                
        //具备关联关系
        if(eo.relations.has(propName)){
            let rel:IEntityRelation = eo.relations.get(propName);
            //关联实体配置
            let eo1:IEntityCfg = EntityFactory.getClass(rel.entity);

            let column:IEntityColumn = eo.columns.get(propName);
            //引用外键
            if(rel.type === ERelationType.ManyToOne || rel.type === ERelationType.OneToOne && !rel.mappedBy){
                let enObj = em.getCache(entity);
                let rql:string;
                let query:NativeQuery;
                //查询外键对象
                if(enObj && enObj.fk && enObj.fk.has(column.name)){ //外键存在
                    rql = "select * from " + eo1.table + " m where " + column.refName + " = ?";
                    query = em.createNativeQuery(rql,rel.entity);
                    //设置外键id
                    query.setParameter(0,enObj.fk.get(column.name));
                }else{
                    rql = "select m.* from " + eo1.table + " m,"+ eo.table +" m1 where m." +
                                    column.refName + "= m1." +  column.name + " and m1." + eo.columns.get(eo.id.name).name + " = ?";
                    query = em.createNativeQuery(rql,rel.entity);
                    //设置外键id
                    query.setParameter(0,em.getIdValue(entity));                                    
                }
                entity[propName] = await query.getResult();
            }else if(rel.mappedBy && (rel.type === ERelationType.OneToMany || rel.type === ERelationType.OneToOne)){ //被引用
                if(!eo1){
                    throw ErrorFactory.getError('0020',[rel.entity]);
                }
                //通过mappedby找到引用属性
                let column1:IEntityColumn = eo1.columns.get(rel.mappedBy);
                if(!column1){
                    throw ErrorFactory.getError('0022',[rel.entity,column1]);
                }
                
                let rql:string = "select * from " + eo1.table + " where " + column1.refName + " = ?";
                //查询外键对象
                let query:NativeQuery = em.createNativeQuery(rql,rel.entity);
                //设置查询值
                query.setParameter(0,em.getIdValue(entity));
                entity[propName] = rel.type===ERelationType.OneToOne?await query.getResult():await query.getResultList();
            }

            return entity[propName];
        }
    }
}

export{EntityProxy}