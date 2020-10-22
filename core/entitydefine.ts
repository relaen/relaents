
/**
 * entity类配置项
 */
interface IEntityCfg{
    table:string;
    schema?:string;
    ids:Map<string,IEntityPKey>;      //可能存在多个主键
    columns:Map<string,IEntityColumn>;
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
     * 主键生成策略
     */
    method?:string;

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

export {IEntityCfg,IEntityColumn,IEntityPKey,IEntityRelation}