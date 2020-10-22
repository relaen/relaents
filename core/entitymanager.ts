import { IEntityPKey, IEntityColumn, IEntityCfg } from "./entitydefine";


/**
 * 实体管理器
 */
class EntityManager{
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
    static addClass(entityName:string,tblName:string,schema?:string){
        if(this.entityClasses.has(entityName)){
            let clazz:IEntityCfg = this.entityClasses.get(entityName);
            clazz.table = tblName;
            clazz.schema = schema;
        }else{
            this.entityClasses.set(entityName,{
                table:tblName,
                schema:schema,
                ids:new Map(),
                columns:new Map()
            });
        }
    }

    /**
     * 添加主键
     * @param entityName    实体类名    
     * @param propName      实体字段名
     * @param cfg 
     */
    static addPKey(entityName:string,colName:string,cfg:IEntityPKey){
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
    static addColumn(entityName:string,colName:string,cfg:IEntityColumn){
        if(!this.entityClasses.has(entityName)){
            return;
        }
        let entity:IEntityCfg = this.entityClasses.get(entityName);
        entity.columns.set(colName,cfg);
    }

    /**
     * 获取entity对应的entity class 配置型
     * @param entityName 
     */
    public static getClass(entityName:string):IEntityCfg{
        return this.entityClasses.get(entityName);
    }

    /**
     * 保存实体
     * @param entity    待删除实体
     * @returns         保存后的实体
     */
    public static async save<T>(entity:T):Promise<T>{

        return entity;
    }

    /**
     * 删除实体
     * @param entity    待删除实体
     * @returns         是否删除成功
     */
    public static async delete<T>(entity:T):Promise<boolean>{

        return true;
    }


    /**
     * 通过id查找实体
     * @param entity 
     * @param idValue 
     */
    public static async findById<T>(entity:T,idValue:any):Promise<T>{
        return entity;
    }
    /**
     * 查询实体
     */
    query(){

    }

}

export{EntityManager}