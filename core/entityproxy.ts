import { EntityRelation, ERelationType, EntityColumnOption, IEntity } from "./types";
import { EntityFactory } from "./entityfactory";
import { EntityManager } from "./entitymanager";
import { getEntityManager } from "./entitymanagerfactory";
import { RelaenError } from "./message/error";
import { NativeQuery } from "./nativequery";
import { Logger } from "./logger";
import { EntityConfig } from "./entityconfig";

/**
 * 实体代理类
 */
class EntityProxy {
    /**
     * 获取实体关联对象值
     * @param entity -    实体
     * @param propName -  关联属性名
     * @returns         实体关联对象
     */
    public static async get(entity: IEntity, propName: string): Promise<any> {
        if (!EntityFactory.getIdValue(entity)) {
            Logger.error(new RelaenError("0105"));
            return null;
        }

        const pv = entity[propName];
        if (pv !== undefined && pv !== null) {
            return pv;
        }
        const eo: EntityConfig = EntityFactory.getEntityConfig(entity.constructor.name);
        const rel: EntityRelation = eo.getRelation(propName);
        //具备关联关系
        if (rel) {
            const em: EntityManager = await getEntityManager();
            const rel: EntityRelation = eo.getRelation(propName);
            //关联实体配置
            const eo1: EntityConfig = EntityFactory.getEntityConfig(rel.entity);
            //关联字段
            const column: EntityColumnOption = eo.columns.get(propName);
            //引用外键
            if (!rel.mappedBy) {  //ManyToOne OneToOne
                if(rel.type === ERelationType.ManyToOne || rel.type === ERelationType.OneToOne){
                    const sql = "SELECT m.* FROM " + eo1.getTableName(true) + " m," + eo.getTableName(true) + " m1 WHERE m." +
                        column.refName + "= m1." + column.name + " AND m1." + eo.getIdName() + " = ?";
                    const query: NativeQuery = em.createNativeQuery(sql, rel.entity);
                    //设置外键id
                    query.setParameter(0, EntityFactory.getIdValue(entity));

                    //当state=2时，可能不存在外键，则query不存在
                    if (query) {
                        entity[propName] = await query.getResult();
                    }
                }else if(rel.type === ERelationType.ManyToMany){  //ManyToMany
                    const joinTblName = eo.schema?eo.schema + '.' + column.joinTable:column.joinTable;
                    //关联表主键，默认为关联表主键名
                    const refName = column.refName || eo1.getIdName();
                    //join column 默认为主键名
                    const jcName = column.joinColumn || eo.getIdName();
                    const sql = "SELECT m1.* FROM " + joinTblName + " m LEFT JOIN " + eo1.getTableName(true) + " m1 ON m." + refName + 
                        " = m1." + eo1.getIdName() + " WHERE " + "m." + jcName + " = ?";
                        const query: NativeQuery = em.createNativeQuery(sql, rel.entity);
                    //设置外键id
                    query.setParameter(0, EntityFactory.getIdValue(entity));

                    //当state=2时，可能不存在外键，则query不存在
                    if (query) {
                        entity[propName] = await query.getResultList();
                    }
                }
            }else{ //被引用
                if (!eo1) {
                    throw new RelaenError('0020', rel.entity);
                }
             if((rel.type === ERelationType.OneToMany || rel.type === ERelationType.OneToOne)) {  // OneToOne OneToMany
                    //通过mappedby找到引用属性
                    const column1: EntityColumnOption = eo1.columns.get(rel.mappedBy);
                    if (!column1) {
                        throw new RelaenError('0022', rel.entity, column1);
                    }
                    
                    const sql: string = "SELECT * FROM " + eo1.getTableName(true) + " WHERE " + column1.name + " = ?";
                    //查询外键对象
                    const query: NativeQuery = em.createNativeQuery(sql, rel.entity);
                    //设置查询值
                    query.setParameter(0, EntityFactory.getIdValue(entity));
                    entity[propName] = rel.type === ERelationType.OneToOne ? await query.getResult() : await query.getResultList();
                }else if(rel.type === ERelationType.ManyToMany){  //mantomany
                    const column: EntityColumnOption = eo1.getColumn(rel.mappedBy);
                    //关联表主键，默认为关联表主键名
                    const refName = column.refName || eo.getIdName();
                    //join column 默认为主键名
                    const jcName = column.joinColumn || eo1.getIdName();
                    
                    const joinTblName = eo1.schema?eo1.schema + '.' + column.joinTable:column.joinTable;
                    const sql = "SELECT m1.* FROM " + joinTblName + " m LEFT JOIN " + eo1.getTableName() + " m1 ON m." + jcName  + 
                        " = m1." + eo1.getIdName() + " WHERE " + "m." + refName + " = ?";

                    const query: NativeQuery = em.createNativeQuery(sql, rel.entity);
                    //设置外键id
                    query.setParameter(0, EntityFactory.getIdValue(entity));
    
                    //当state=2时，可能不存在外键，则query不存在
                    if (query) {
                        entity[propName] = await query.getResultList();
                    }
                }
            } 
            //新建的需要关闭
            await em.close();
            return entity[propName];
        }
    }
}

export { EntityProxy }