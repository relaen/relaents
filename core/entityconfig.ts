import { ErrorFactory } from "./errorfactory";
import { EntityOption, EntityColumnOption, EntityPKey, EntityRelation, UnknownClass } from "./types";

/**
 * 实体工厂中的实体配置对象
 */
export class EntityConfig{
    /**
     * 实体类
     */
    public entity?: UnknownClass;
    
    /**
     * 表名
     */
    public table?: string;

    /**
     * 数据库名
     */
    public schema?: string;

    /**
     * 主键
     */
    public id?: EntityPKey;

    /**
     * 版本属性名，用于
     */
    public version?:string;
    
    /**
     * 字段集合，键为对象属性名(非表字段名)
     */
    public columns: Map<string, EntityColumnOption>;

    /**
     * 关系集合，键为对象属性名(非表字段名)
     */
    public relations: Map<string, EntityRelation>;

    /**
     * 构造器
     * @param cfg -   实体配置对象
     */
    constructor(cfg?:EntityOption){
        if(cfg){
            for(const k of Object.keys(cfg)){
                this[k] = cfg[k];
            }
        }
        this.columns = new Map();
        this.relations = new Map();
    }

    /**
     * 设置表名
     * @param tableName - 
     */
    public setTableName(tableName:string){
        this.table = tableName;
    }

    /**
     * 设置schema名
     * @param schemaName - 
     */
    public setSchemaName(schemaName:string){
        this.schema = schemaName;
    }

    /**
     * 设置实体类
     * @param entityCls -     实体类
     */
    public setEntityClass(entityCls:UnknownClass){
        this.entity = entityCls;
    }

    /**
     * 设置主键对象
     * @param cfg -   主键配置
     */
    public setId(cfg:EntityPKey){
        this.id = cfg;
    }

    /**
     * 获取id对象
     * @returns     主键字段对象
     * @throws      错误码 0020
     */
    public getId():EntityPKey{
        if(!this.id){
            throw ErrorFactory.getError('0020', [this.entity.name]);
        }
        return this.id;
    }

    /**
     * 获取id字段名
     * @returns id字段名
     */
    public getIdName():string{
        const id  = this.getId();
        return this.columns.get(id.name).name;
    }

    /**
     * 获取字段对象
     * @param propName -  实体属性名
     * @returns         属性对应字段对象         
     * @throws          错误码0022
     */
     public getColumn(propName:string){
        if(!this.columns.has(propName)){
            throw ErrorFactory.getError('0022', [this.entity.name,propName]);
        }
        return this.columns.get(propName);
    }

    /**
     * 获取对应表名
     * @param withSchema -    是否返回schema
     * @returns             table name
     */
    public getTableName(withSchema?:boolean):string{
        if(withSchema && this.schema){
            return this.schema + '.' + this.table;
        }
        return this.table;
    }

    /**
     * 获取schema名
     * @returns     schema name
     */
    public getSchemaName():string{
        return this.schema;
    }

    /**
     * 属性名是否对应关系字段
     * @param propName -  属性名  
     * @returns         true/false
     */
    public hasRelation(propName:string):boolean{
        return this.relations.has(propName);
    }

    /**
     * 属性名是否是字段
     * @param propName -  属性名  
     * @returns         true/false
     */
    public hasColumn(propName:string):boolean{
        return this.columns.has(propName);
    }
    /**
     * 获取关系字段对象
     * @param propName -  实体属性名
     * @returns         实体属性对应关系对象
     * @throws          0023
     */
    public getRelation(propName:string):EntityRelation{
        if(!this.relations.has(propName)){
            throw ErrorFactory.getError('0023', [this.entity.name,propName]);
        }
        return this.relations.get(propName);
    }

    /**
     * 添加列
     * @param colName -   属性名 
     * @param cfg -       列配置
     */
    public addColumn(colName:string,cfg:EntityColumnOption){
        //默认nullable为true
        if(cfg.nullable === undefined){
            cfg.nullable = true;
        }
        this.columns.set(colName, cfg);
    }

    /**
     * 添加关系
     * @param colName -   属性名
     * @param cfg -       关系配置
     */
    public addRelation(colName:string,cfg:EntityRelation){
        this.relations.set(colName,cfg);
    }
}