import { BaseEntity } from "./baseentity";
import { IEntityCfg, IEntityRelation, ERelationType, IEntityColumn } from "./entitydefine";
import { EntityFactory } from "./entityfactory";
import { O_EXCL } from "constants";
import { EntityManager } from "./entitymanager";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { Query } from "./query";
import { ErrorFactory } from "./errorfactory";

class EntityProxy{
    async get(entity:BaseEntity,propName:string){
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
                if(enObj && enObj.fk && enObj.fk.has(propName)){ //外键存在
                    rql = "select m from " + rel.entity + " m where " + column.refName + " = ?";
                }else{
                    rql = "select m from " + rel.entity + " m,"+ entity.constructor.name +" m1 where m." +
                                    eo1.id.name + "= m1." +  column.name + " and m1." + eo.id.name + " = ?";
                }
                //查询外键对象
                let query:Query = em.createQuery(rql,rel.entity);
                //设置外键id
                query.setParameter(0,enObj.fk.get(propName));
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
                let rql:string = "select m from " + rel.entity + " m where " + column1.refName + " = ?";
                //查询外键对象
                let query:Query = em.createQuery(rql,rel.entity);
                //设置查询值
                query.setParameter(0,em.getIdValue(entity));
                entity[propName] = rel.type===ERelationType.OneToOne?await query.getResult():await query.getResultList();
            }

            return entity[propName];
        }
    }
}

export{EntityProxy}