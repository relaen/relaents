/**
 * 未知方法
 */
export type UnknownMethod = (...args)=>unknown;

/**
 * 未知类
 */
export type UnknownClass = ()=>void;

/**
 * dialect类型
 */
export type DialectType = "mysql" | "oracle" | "mssql" | "postgres" | "mariadb" | "sqlite";

/**
 * 连接池配置
 */
export type ConnectionPoolOption = {
    /**
     * 最大连接数
     */
    max: number;
    /**
     * 最小连接数
     */
    min: number;
}

/**
 * 连接配置
 */
export type ConnectionOption = {
    /**
     * 数据库产品
     */
    dialect: DialectType;
    /**
     * 服务器地址
     */
    host?: string;
    /**
     * 端口号
     */
    port?: number;
    /**
     * 用户名
     */
    username?: string;
    /**
     * 密码
     */
    password?: string;
    /**
     * 数据库
     */
    database?: string;
    /**
     * 连接超时时间（ms）
     */
    connectTimeout?: number;
    /**
     * 空闲超时时间，开启连接池使用（ms）
     */
    idleTimeout?: number;
    /**
     * 连接池配置
     */
    pool?: {min:number,max:number};
    /**
     * 是否cache
     */
    cache?: boolean;
    /**
     * 是否调试模式
     */
    debug?: boolean;
    /**
     * 实体配置数组 
     */
    entities?: Array<string>
    
    /**
     * 是否开启连接池，数据库原生配置使用
     */
    usePool?: boolean;
    /**
     * 是否允许全表操作
     */
    fullTableOperation?: boolean;

    /**
     * 是否文件日志
     */
    fileLog?:object|boolean;

    /**
     * 各dialect npm原生配置，配置该项后，另外配置失效
     */
    options?:object;
}

/**
 * 实体接口
 */
export interface IEntity {
    /**
     * 保存方法
     */
    save: UnknownMethod;
    /**
     * 删除方法
     */
    delete: UnknownMethod;
    /**
     * 比较方法
     */
    compare: UnknownMethod;
}
/**
 * entity类配置项
 */
export type EntityOption = {
    /**
     * 实体类
     */
    entity?: unknown;
    /**
     * 表名
     */
    table?: string;

    /**
     * 数据库名
     */
    schema?: string;

    /**
     * 主键
     */
    id?: EntityPKey;

    /**
     * 字段集合，键为对象属性名(非表字段名)
     */
    columns?: Map<string, EntityColumnOption>;

    /**
     * 关系集合，键为对象属性名(非表字段名)
     */
    relations?: Map<string, EntityRelation>;
}

/**
 * 实体字段配置
 */
export type EntityColumnOption = {
    /**
     * 字段名，默认为属性名
     */
    name?: string;

    /**
     * 外键字段名
     */
    refName?: string;

    /**
     * 数据类型 int double string date object
     */
    type?: string;

    /**
     * 是否可空
     */
    nullable?: boolean;

    /**
     * 长度，type为字符串时有效
     */
    length?: number;

    /**
     * 是否自增，mssql
     */
    identity?: boolean;

    /**
     * 关联表，ManyToMany有效
     */
    joinTable?: string;

    /**
     * 在关联表中的字段名，ManyToMany中有效
     */
    joinColumn?: string;

    /**
     * 乐观锁，数据版本Version
     */
    version?: boolean;

    /**
     * 实体类查询，不显示字段
     */
    select?: boolean;
}

/**
 * 外键字段类型
 */
export type EntityRefColumn = {
    /**
     * 字段名，默认为属性名
     */
    name?: string;

    /**
     * 外键字段名
     */
    refName?: string;

    /**
     * 是否可空
     */
    nullable?: boolean;
}

/**
 * 中间表，ManyToMany时有效
 */
export type JoinTableOption = {
    /**
     * 中间表名
     */
    table: string;

    /**
     * 在table中的主键字段名，默认为主键字段名
     */
    columnName?: string;

    /**
     * 外键字段名，默认为对应实体主键字段名
     */
    refName?: string;
 }

/**
 * 实体主键配置
 */
export type EntityPKey = {
    /**
     * 对应属性名
     */
    name?: string;

    /**
     * 主键生成策略 identity,sequence,table,uuid
     */
    generator?: "identity" | "sequence" | "table" | "uuid";

    /**
     * 主键来源表，数据库表，专门用于主键生成
     */
    // table?: string;

    /**
     * 主键键字段名，如果generator为'table'，则该项不能为空
     */
    columnName?: string;

    /**
     * 主键值字段名，如果generator为'table'，则该项不能为空
     */
    valueName?: string;

    /**
     * 主键对应记录项名，如果generator为'table'，则该项不能为空
     * 如User实体用columnName=ID_USER这一条记录产生主键，则keyName='ID_USER'
     */
    keyName?: string;

    /**
     * 对应sequence生成策略，表示sequence name
     */
    seqName?: string;
}

/**
 * 实体关系配置
 */
export type EntityRelation = {
    /**
     * 被依赖的实体类名
     */
    entity: string;

    /**
     * 关系类型
     */
    type?: ERelationType;

    /**
     * 外键删除策略 cascade,setnull,none
     */
    onDelete?: string;

    /**
     * 外键更新策略 cascade,setnull,none
     */
    onUpdate?: string;

    /**
     * 被引用时对应子表属性 
     */
    mappedBy?: string;
}

/**
 * where条件值对象接口，用于链式操作时构造条件语句
 */
export type CondValueOption = {
    /**
     * 值
     */
    value: unknown;

    /**
     * 字段与值的逻辑关系，如 `=,>,<,>=,<=,<>,is,like`等，默认`=`
     */
    rel?: string;

    /**
     * 与前一个字段的逻辑关系(第一个字段条件此项无效)，可选值为AND,OR，默认AND
     */
    logic?: string

    /**
     * 表达式前置串，通常为"("
     */
    before?: string;

    /**
     * 表达式后置串，通常为")"
     */
    after?: string;
}

/**
 * 关系类型
 */
export enum ERelationType {
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
export enum EEntityState {
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
export enum EQueryType {
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

/**
 * 锁机制类型
 */
export enum ELockType {
    TABLEREAD = 'table_read',
    TABLEWRITE = 'table_write',
    ROWREAD = 'row_read',
    ROWWRITE = 'row_write'
};

/**
 * 锁类型
 */
export enum ELockMode {
    OPTIMISTIC = 'optimistic',
    PESSIMISTIC_READ = 'pessimistic_read',
    PESSIMISTIC_WRITE = 'pessimistic',
}

/**
 * 事务隔离级别
 */
export enum EIsolationLevel {
    SERIALIZABLE = 'SERIALIZABLE',
    READUNCOMMITTED = 'READ UNCOMMITTED',
    READCOMMITTED = 'READ COMMITTED',
    REPEATABLEREAD = 'REPEATABLE READ'
};

/**
 * Sqlite事务类型
 */
export enum ESqliteTransactionType {
    DEFERRED = 'deferred',
    IMMEDIATE = 'immediate',
    EXCLUSIVE = 'exclusive'
};
