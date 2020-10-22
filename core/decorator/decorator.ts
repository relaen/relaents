/**
 * 实体字段配置
 */
interface IEntityColumn{
    /**
     * 字段名，默认为属性名
     */
    name?:string;

    /**
     * 数据类型 int double string date
     */
    type:string;

    /**
     * 是否可空
     */
    nullable?:boolean;

    /**
     * 长度，type为字符串时有效
     */
    length?:number;
}

/**
 * 实体主键配置
 */
interface IEntityPKey{
    /**
     * 主键来源表，数据库表，专门用于主键生成
     */
    table?:string;

    /**
     * 主键对应来源表字段
     */
    column?:string;
}

/**
 * 实体关系配置
 */
interface IEntityRelation{
    /**
     * 实体类名
     */
    entity:string;

    /**
     * 实体字段字段名
     */
    colunn:string;

    /**
     * 外键删除策略  cascade,setnull,none
     */
    onDelete?:string;

    /**
     * 外键更新策略 cascade,setnull,none
     */
    onUpdate?:string;
}
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
        //初始化orm配置项
        target.prototype.__orm = {
            table:tblName,      //表名
            ids:new Map(),       //可能存在多个主键
            columns:new Map()   //字段集合 {columnName:tableColumnName,...}
        }
    }
}

/**
 * @exclude
 * 主键装饰器，装饰属性
 * @param genMethod 主键生成方法
 * @param cfg       配置项
 */
function Id(genMethod:string,cfg?:IEntityPKey){
    return (target:any,propertyName:string)=>{
        process.nextTick(()=>{
            target.__orm.ids.set(propertyName,{
                genMethod:genMethod,
                cfg:cfg
            })
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
            target.__orm.columns.set(propertyName,cfg);
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

export {IEntityColumn,IEntityPKey,IEntityRelation,Entity,Id,Column,OneToMany,OneToOne,ManyToOne,ManyToMany}