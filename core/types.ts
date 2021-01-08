/**
 * 连接池配置
 */
interface IConnectionPool{
    /**
     * 最大连接数
     */
    max:number;
    /**
     * 最小连接数
     */
    min:number;
}

/**
 * 连接配置
 */
interface IConnectionCfg{
    /**
     * 数据库产品
     */
    dialect:string;
    /**
     * 服务器地址
     */
    host:string;
     /**
      * 端口号
      */
    port:number;
    /**
     * 用户名
     */
    username:string;
    /**
     * 密码
     */
    password:string;
    /**
     * 数据库
     */
    database:string;
    /**
     * 连接池配置
     */
    pool:IConnectionPool;
    /**
     * 是否cache
     */
    cache:boolean;
    /**
     * 是否调试模式
     */
    debug:boolean;

    /**
     * 实体配置数组 
     */
    entities:Array<string>
}

/**
 * 实体接口
 */
interface IEntity{
    /**
     * 状态
     */
    __status:EEntityState;
    /**
     * 保存方法
     */
    save:Function;
    /**
     * 删除方法
     */
    delete:Function;
    /**
     * 比较方法
     */
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
     * 被引用时对应子表属性 
     */
    mappedBy?:string;
}

/**
 * 外键约束
 */
enum EFkConstraint{
    /**
     * 无操作
     */
    NONE='none',
    /**
     * 限制修改和删除
     */
    RESTRICT='restrict',
    /**
     * 级联操作
     */
    CASCADE='cascade',
    /**
     * 外键置空
     */
    SETNULL='set null'
}

/**
 * 关系类型
 */
enum ERelationType{
    /**
     * 一对一关系
     */
    OneToOne = 1,
    /**
     * 一对多关系
     */
    OneToMany = 2,
    /**
     * 多对一关系
     */
    ManyToOne = 3,
    /**
     * 多对多关系
     */
    ManyToMany = 4
}

/**
 * 实体状态
 */
enum EEntityState{
    /**
     * 新建状态
     */
    NEW = 1,   

    /**
     * 持久化状态
     */
    PERSIST = 2     
}

/**
 * query类型
 */
enum EQueryType{
    /**
     * select
     */
    SELECT = 0,
    /**
     * insert
     */
    INSERT = 1,

    /**
     * update
     */
    UPDATE = 2,

    /**
     * delete
     */
    DELETE = 3

}
export {IConnectionCfg,IConnectionPool,IEntity,IEntityCfg,IEntityColumn,IEntityRefColumn,IEntityPKey,IEntityRelation,EFkConstraint,ERelationType,EEntityState,EQueryType}
