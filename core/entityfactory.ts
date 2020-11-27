import { IEntityCfg, IEntityPKey, IEntityColumn, IEntityRelation, IEntity } from "./entitydefine";
import { BaseEntity } from "./baseentity";

/**
 * 实体工厂，管理所有实体类
 */
class EntityFactory{
    /**
     * 实体类集
     */
    private static entityClasses:Map<string,IEntityCfg> = new Map();
    /**
     * 新建实体map，用于存放新建状态的实体，当实体执行save、delete操作后，将从该map移除
     * {entity:createTime}
     */
    private static newEntityMap:WeakMap<IEntity,number> = new WeakMap();

    /**
     * 添加实体类
     * @param entityName    实体类名
     * @param tblName       表名       
     * @param schema        数据库名
     */
    public static addClass(entity:any,tblName:string,schema?:string){
        let entityName:string = entity.name;
        if(this.entityClasses.has(entityName)){
            let clazz:IEntityCfg = this.entityClasses.get(entityName);
            clazz.table = tblName;
            clazz.schema = schema;
            clazz.entity = entity;
        }else{
            this.entityClasses.set(entityName,{
                entity:entity,
                table:tblName,
                schema:schema,
                id:null,
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
    public static addPKey(entityName:string,propName:string,cfg:IEntityPKey){
        this.checkAndNewClass(entityName);
        if(!cfg){
            cfg = {
                name:propName
            }
        }else{
            cfg.name = propName;
        }
        let entity:IEntityCfg = this.entityClasses.get(entityName);
        entity.id = cfg;
    }

    /**
     * 添加实体字段
     * @param entityName    实体类名    
     * @param propName      实体字段名
     * @param cfg 
     */
    public static addColumn(entityName:string,colName:string,cfg:IEntityColumn){
        this.checkAndNewClass(entityName);
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
        this.checkAndNewClass(entityName);
        let entity:IEntityCfg = this.entityClasses.get(entityName);
        entity.relations.set(colName,rel);
    }

    /**
     * 检查class是否存在，不存在则新建
     * @param entityName    实体类名
     */
    private static checkAndNewClass(entityName){
        if(!this.entityClasses.has(entityName)){
            this.entityClasses.set(entityName,{
                columns:new Map(),
                relations:new Map()
            });
        }
    }
    /**
     * 获取entity对应的entity class 配置型
     * @param entityName    实体类名
     * @returns             实体配置
     */
    public static getClass(entityName:string):IEntityCfg{
        return this.entityClasses.get(entityName);
    }


    /**
     * 从文件添加实体到工厂
     * @param path  文件路径
     */
    public static addEntities(path:string){
        const basePath = process.cwd();
        let pathArr = path.split('/');
        let pa = [basePath];
        let handled:boolean = false;    //是否已处理
        for(let i=0;i<pathArr.length-1;i++){
            const p = pathArr[i];
            if(p.indexOf('*') === -1 && p !== ""){
                pa.push(p);
            }else if(p === '**'){ //所有子孙目录
                handled=true;
                if(i<pathArr.length-2){
                    throw "entities config is not correct!";
                }
                handleDir(pa.join('/'),pathArr[pathArr.length-1],true);
            }
        }
        if(!handled){
            handleDir(pa.join('/'),pathArr[pathArr.length-1]);
        }

        function handleDir(dirPath:string,fileExt:string,deep?:boolean){
            const fsMdl = require('fs');
            const pathMdl = require('path');
            const dir = fsMdl.readdirSync(dirPath,{withFileTypes:true});
            
            let fn:string = fileExt;
            let reg:RegExp = EntityFactory.toReg(fn,3);
            
            for (const dirent of dir) {
                if(dirent.isDirectory()){
                    if(deep){
                        handleDir(pathMdl.resolve(dirPath ,dirent.name),fileExt,deep);
                    }
                }else if(dirent.isFile()){
                    // @Instance注解方式文件，自动执行instance创建操作
                    if(reg.test(dirent.name)){
                        require(pathMdl.resolve(dirPath , dirent.name));
                    }
                }            
            }
        }
    }

    
    private static toReg(str:string,side?:number):RegExp{
        // 转字符串为正则表达式并加入到数组
        //替换/为\/
        str = str.replace(/\//g,'\\/');
        //替换.为\.
        str = str.replace(/\./g,'\\.');
        //替换*为.*
        str = str.replace(/\*/g,'.*');
        if(side !== undefined){
            switch(side){
                case 1:
                    str = '^' + str;
                    break;
                case 2:
                        str = str + '$';
                    break;
                case 3:
                    str = '^' + str + '$';
            }
        }
        return new RegExp(str);
    }

    /**
     * 添加实体到新建实体map
     * @param entity    待加入的实体
     */
    public static addNewEntity(entity:IEntity){
        if(this.newEntityMap.has(entity)){
            return;
        }
        this.newEntityMap.set(entity,new Date().getTime());
    }

    /**
     * 从新建实体map移除实体
     * @param entity    待移除的实体
     */
    public static removeNewEntity(entity:IEntity){
        if(!this.newEntityMap.has(entity)){
            return;
        }
        this.newEntityMap.delete(entity);
    }

    /**
     * 是否存在new entity
     * @param entity    待查询entity
     * @returns         true/false
     */
    public static hasNewEntity(entity:IEntity):boolean{
        return this.newEntityMap.has(entity);
    }
}

export {EntityFactory}