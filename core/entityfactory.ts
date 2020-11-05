import { IEntityCfg, IEntityPKey, IEntityColumn, IEntityRelation } from "./entitydefine";

/**
 * 实体工厂，管理所有实体类
 */
class EntityFactory{
    /**
     * 实体类集
     */
    private static entityClasses:Map<string,IEntityCfg> = new Map();

    /**
     * 添加实体类
     * @param entityName    实体类名
     * @param tblName       表名       
     * @param schema        数据库名
     */
    public static addClass(entityName:string,tblName:string,schema?:string){
        if(this.entityClasses.has(entityName)){
            let clazz:IEntityCfg = this.entityClasses.get(entityName);
            clazz.table = tblName;
            clazz.schema = schema;
        }else{
            this.entityClasses.set(entityName,{
                table:tblName,
                schema:schema,
                ids:new Map(),
                columns:new Map(),
                relations:new Map()
            });
        }
    }

    /**
     * 添加主键
     * @param entityName    实体类名    
     * @param propName      实体字段名
     * @param cfg 
     */
    public static addPKey(entityName:string,colName:string,cfg:IEntityPKey){
        if(!this.entityClasses.has(entityName)){
            return;
        }
        let entity:IEntityCfg = this.entityClasses.get(entityName);
        entity.ids.set(colName,cfg);
    }

    /**
     * 添加实体字段
     * @param entityName    实体类名    
     * @param propName      实体字段名
     * @param cfg 
     */
    public static addColumn(entityName:string,colName:string,cfg:IEntityColumn){
        if(!this.entityClasses.has(entityName)){
            return;
        }
        let entity:IEntityCfg = this.entityClasses.get(entityName);
        entity.columns.set(colName,cfg);
    }

    /**
     * 添加实体关系
     * @param entityName    实体名 
     * @param colName       属性名
     * @param rel           关系对象
     */
    public static addRelation(entityName:string,colName:string,rel:IEntityRelation){
        if(!this.entityClasses.has(entityName)){
            return;
        }
        let entity:IEntityCfg = this.entityClasses.get(entityName);
        entity.relations.set(colName,rel);
    }

    /**
     * 获取entity对应的entity class 配置型
     * @param entityName 
     */
    public static getClass(entityName:string):IEntityCfg{
        return this.entityClasses.get(entityName);
    }
}

export {EntityFactory}