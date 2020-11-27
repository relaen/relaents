import { BaseEntity } from "./baseentity";

/**
 * 实体接口
 */
interface IEntity{
    save:Function;
    delete:Function;
    compare:Function;
}
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
     * 对应属性名
     */
    name?:string;
    /**
     * 主键生成策略 identity table uuid
     */
    generator?:string;

    /**
     * 主键来源表，数据库表，专门用于主键生成
     * 该表必须包含两个字段 
     * id_name: string,对应某个实体主键生成器名,
     * id_value: int,对应主键值
     */
    table?:string;

    /**
     * 主键对应来源表记录项，如User实体用id_name='ID_USER'这一条记录产生主键，则keyName='ID_USER'
     */
    keyName?:string;
}

/**
 * 实体关系配置
 */
interface IEntityRelation{
    /**
     * 被依赖的实体类名
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
    NEW = 1,        //新建状态
    PERSIST = 2     //持久化状态
}
export {IEntity,IEntityCfg,IEntityColumn,IEntityRefColumn,IEntityPKey,IEntityRelation,EFkConstraint,ERelationType,EEntityState}