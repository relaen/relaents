import { EntityPKey, EntityColumnOption, EntityRelation, EntityRefColumn, ERelationType, JoinTableOption } from "../types";
import { EntityFactory } from "../entityfactory";

/**
 * 装饰器（注解类）
 */

/**
 
 * Entity装饰器，装饰实体(表)，装饰类
 * @param tblName -   表名
 * @param schema -    数据库名
 */
function Entity(tblName:string,schema?:string){
    return (target) => {
        EntityFactory.addEntityConfig(target,tblName,schema);
    }
}

/**
 
 * 主键装饰器，装饰属性
 * @param cfg -       配置项
 */
function Id(cfg?:EntityPKey){
    return (target:unknown, propertyName: string) => {
        EntityFactory.addPKey(target.constructor.name,propertyName,cfg);
    }
}

/**
 
 * 字段装饰器，装饰属性
 * @param cfg - 配置项
 */
function Column(cfg:EntityColumnOption){
    return (target:unknown,propertyName:string)=>{
        if(!cfg || !cfg.type){
            throw "@Column配置参数错误";
        }
        EntityFactory.addColumn(target.constructor.name,propertyName,cfg);
    }
}

/**
 
 * 版本号装饰器，乐观锁时有效，装饰属性
 */
function Version(){
    return (target:unknown,propertyName:string)=>{
        EntityFactory.addVersion(target.constructor.name,propertyName);
    }
}

/**
 
 * 字段装饰器，装饰属性
 * @param cfg - 配置项
 */
function JoinTable(cfg:JoinTableOption){
    return (target:unknown,propertyName:string)=>{
        const cfg1:EntityColumnOption = {
            joinColumn:cfg.columnName,
            joinTable:cfg.table,
            refName:cfg.refName
        }
        EntityFactory.addColumn(target.constructor.name,propertyName,cfg1);
    }
}

/**
 
 * 字段装饰器，装饰属性
 * @param cfg - 配置项
 */
function JoinColumn(cfg:EntityRefColumn){
    return (target:unknown,propertyName:string)=>{
        //引用外键字段名默认与字段名一致
        if(!cfg.refName){
            cfg.refName = cfg.name;
        }
        EntityFactory.addColumn(target.constructor.name,propertyName,cfg);
    }
}

/**
 
 * 一对多关系，装饰属性
 * @param cfg -   实体关系配置
 */
function OneToMany(cfg:EntityRelation){
    return (target:unknown,propertyName:string)=>{
        cfg.type = ERelationType.OneToMany;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

/**
 
 * 一对一关系，装饰属性
 * @param cfg -   实体关系配置
 */
function OneToOne(cfg:EntityRelation){
    return (target:unknown,propertyName:string)=>{
        cfg.type = ERelationType.OneToOne;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

/**
 * 多对一关系，装饰属性
 * @param cfg -   实体关系配置
 */
function ManyToOne(cfg:EntityRelation){
    return (target:unknown,propertyName:string)=>{
        cfg.type = ERelationType.ManyToOne;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

/**
 * 多对多关系，装饰属性
 * @param cfg -   实体关系配置
 */
function ManyToMany(cfg:EntityRelation){
    return (target:unknown,propertyName:string)=>{
        cfg.type = ERelationType.ManyToMany;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

export {Entity,Id,Column,Version,JoinTable,JoinColumn,OneToMany,OneToOne,ManyToOne,ManyToMany}