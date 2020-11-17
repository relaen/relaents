import { IEntityPKey, IEntityColumn, IEntityRelation, IEntityRefColumn, ERelationType } from "../entitydefine";
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
    return (target) =>{
        EntityFactory.addClass(target.name,tblName,schema);
    }
}

/**
 * @exclude
 * 主键装饰器，装饰属性
 * @param cfg       配置项
 */
function Id(cfg?:IEntityPKey){
    return (target:any,propertyName:string)=>{
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
        // process.nextTick(()=>{
        EntityFactory.addRelation(target.constructor.name,propertyName,cfg);
        // });
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

export {Entity,Id,Column,JoinColumn,OneToMany,OneToOne,ManyToOne,ManyToMany}