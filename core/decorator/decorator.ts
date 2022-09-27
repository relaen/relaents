import { IEntityPKey, IEntityColumn, IEntityRelation, IEntityRefColumn, ERelationType, IJoinTable } from "../types";
import { EntityFactory } from "../entityfactory";

/**
 * 装饰器（注解类）
 */

/**
 * @exclude
 * Entity装饰器，装饰实体(表)，装饰类
 * @param tblName   表名
 * @param schema    数据库名
 */
function Entity(tblName:string,schema?:string){
    return (target) => {
        EntityFactory.addEntityConfig(target,tblName,schema);
    }
}

/**
 * @exclude
 * 主键装饰器，装饰属性
 * @param cfg       配置项
 */
function Id(cfg?:IEntityPKey){
    return (target: any, propertyName: string) => {
        EntityFactory.addPKey(target.constructor.name,propertyName,cfg);
    }
}

/**
 * @exclude
 * 字段装饰器，装饰属性
 * @param cfg 配置项
 */
function Column(cfg:IEntityColumn){
    return (target:any,propertyName:string)=>{
        if(!cfg || !cfg.type){
            throw "@Column配置参数错误";
        }
        EntityFactory.addColumn(target.constructor.name,propertyName,cfg);
    }
}

/**
 * @exclude
 * 版本号装饰器，乐观锁时有效，装饰属性
 */
function Version(){
    return (target:any,propertyName:string)=>{
        EntityFactory.addVersion(target.constructor.name,propertyName);
    }
}

/**
 * @exclude
 * 字段装饰器，装饰属性
 * @param cfg 配置项
 */
function JoinTable(cfg:IJoinTable){
    return (target:any,propertyName:string)=>{
        let cfg1:IEntityColumn = {
            joinColumn:cfg.columnName,
            joinTable:cfg.table,
            refName:cfg.refName
        }
        EntityFactory.addColumn(target.constructor.name,propertyName,cfg1);
    }
}

/**
 * @exclude
 * 字段装饰器，装饰属性
 * @param cfg 配置项
 */
function JoinColumn(cfg:IEntityRefColumn){
    return (target:any,propertyName:string)=>{
        //引用外键字段名默认与字段名一致
        if(!cfg.refName){
            cfg.refName = cfg.name;
        }
        EntityFactory.addColumn(target.constructor.name,propertyName,cfg);
    }
}

/**
 * @exclude
 * 一对多关系，装饰属性
 * @param cfg   实体关系配置
 */
function OneToMany(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        cfg.type = ERelationType.OneToMany;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

/**
 * @exclude
 * 一对一关系，装饰属性
 * @param cfg   实体关系配置
 */
function OneToOne(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        cfg.type = ERelationType.OneToOne;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

/**
 * @exclude
 * 多对一关系，装饰属性
 * @param cfg   实体关系配置
 */
function ManyToOne(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        cfg.type = ERelationType.ManyToOne;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

/**
 * @exclude
 * 多对多关系，装饰属性
 * @param cfg   实体关系配置
 */
function ManyToMany(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        cfg.type = ERelationType.ManyToMany;
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
    }
}

export {Entity,Id,Column,Version,JoinTable,JoinColumn,OneToMany,OneToOne,ManyToOne,ManyToMany}