import { IEntityPKey, IEntityColumn, IEntityRelation } from "../entitydefine";
import { EntityManager } from "../entitymanager";

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
        EntityManager.addClass(target.prototype.name,tblName,schema);
    }
}

/**
 * @exclude
 * 主键装饰器，装饰属性
 * @param cfg       配置项
 */
function Id(cfg?:IEntityPKey){
    return (target:any,propertyName:string)=>{
        process.nextTick(()=>{
            EntityManager.addPKey(target.prototype.name,propertyName,cfg);
        });
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
        process.nextTick(()=>{
            EntityManager.addColumn(target.prototype.name,propertyName,cfg);
        });
    }
}

/**
 * @exclude
 * 一对多关系，装饰属性
 * @param cfg   实体关系配置
 */
function OneToMany(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        
    }
}

/**
 * @exclude
 * 一对一关系，装饰属性
 * @param cfg   实体关系配置
 */
function OneToOne(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        
    }
}

/**
 * @exclude
 * 多对一关系，装饰属性
 * @param cfg   实体关系配置
 */
function ManyToOne(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        
    }
}

/**
 * @exclude
 * 多对多关系，装饰属性
 * @param cfg   实体关系配置
 */
function ManyToMany(cfg:IEntityRelation){
    return (target:any,propertyName:string)=>{
        
    }
}

export {Entity,Id,Column,OneToMany,OneToOne,ManyToOne,ManyToMany}