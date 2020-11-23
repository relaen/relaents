import { BaseEntity } from "./baseentity";

/**
 * entity类配置项
 */
interface IEntityCfg{
    /**
     * 实体类
     */
    entity?:any;
    /**
     * 表名
     */
    table?:string;

    /**
     * 数据库名
     */
    schema?:string;

    /**
     * 主键
     */
    id?:IEntityPKey;

    /**
     * 字段集合，键为对象属性名(非表字段名)
     */
    columns:Map<string,IEntityColumn>;

    /**
     * 关系集合，键为对象属性名(非表字段名)
     */
    relations:Map<string,IEntityRelation>;
}

/**
 * 实体字段配置
 */
interface IEntityColumn{
    /**
     * 字段名，默认为属性名
     */
    name?:string;
    
    /**
     * 外键字段名
     */
    refName?:string;

    /**
     * 数据类型 int double string date object
     */
    type?:string;

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
 * 外键字段类型
 */
interface IEntityRefColumn{
    /**
     * 字段名，默认为属性名
     */
    name?:string;

    /**
     * 外键字段名
     */
    refName?:string;

    /**
     * 是否可空
     */
    nullable?:boolean;
}

/**
 * 实体主键配置
 */
interface IEntityPKey{
    /**
     * 主键名
     */
    name:string;
    /**
     * 主键生成策略 identity table uuid
     */
    generator?:string;

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
     * 被依赖的实体类
     */
    entity:string;

    /**
     * 关系类型
     */
    type?:ERelationType;

    /**
     * 外键删除策略 cascade,setnull,none
     */
    onDelete?:string;

    /**
     * 外键更新策略 cascade,setnull,none
     */
    onUpdate?:string;

    /**
     * 懒加载关联对象, false 懒加载，true 非懒加载
     */
    eager?:boolean;
    
    /**
     * 被引用时对应子表属性 
     */
    mappedBy?:string;
}

/**
 * 外键约束
 */
enum EFkConstraint{
    NONE='none',
    RESTRICT='restrict',
    CASCADE='cascade',
    SETNULL='set null'
}

/**
 * 关系类型
 */
enum ERelationType{
    OneToOne = 1,
    OneToMany = 2,
    ManyToOne = 3,
    ManyToMany = 4
}

/**
 * 实体状态
 */
enum EEntityState{
    NONE = 0,       //无状态，持久化时不需要做任何操作，如果为new或updated，则持久化后设置为none
    NEW = 1,        //新建，持久化时执行sql insert操作
    UPDATED = 2,    //修改，持久化时执行sql update操作
    DELETED = 3     //删除，持计化时执行sql delete操作
}
export {IEntityCfg,IEntityColumn,IEntityRefColumn,IEntityPKey,IEntityRelation,EFkConstraint,ERelationType,EEntityState}