import { EntityConfig } from "./entityconfig";
import { ErrorFactory } from "./errorfactory";
import { EntityPKey, EntityColumnOption, EntityRelation, IEntity, UnknownClass } from "./types";

/**
 * 实体工厂，管理所有实体类
 */
class EntityFactory {
    /**
     * 实体类集
     */
    private static entityConfigs: Map<string, EntityConfig> = new Map();
    
    /**
     * 添加实体类
     * @param entityName -    实体类名
     * @param tblName -       表名
     * @param schema -        数据库名
     */
    public static addEntityConfig(entity: UnknownClass, tblName: string, schema?: string) {
        const entityName: string = entity.name;
        if (EntityFactory.entityConfigs.has(entityName)) {
            const cfg: EntityConfig = EntityFactory.entityConfigs.get(entityName);
            cfg.setEntityClass(entity);
            cfg.setTableName(tblName);
            cfg.setSchemaName(schema);
        } else {
            EntityFactory.entityConfigs.set(entityName, new EntityConfig({
                entity: entity,
                table: tblName,
                schema: schema
            }));
        }
    }

    /**
     * 添加主键
     * @param entityName -    实体类名
     * @param propName -      实体字段名
     * @param cfg -           主键配置对象
     */
    public static addPKey(entityName: string, propName: string, cfg: EntityPKey) {
        this.checkAndNew(entityName);
        if (!cfg) {
            cfg = {
                name: propName
            }
        } else {
            cfg.name = propName;
        }
        // 生成器类型为table无keyName或sequence无seqName
        if (cfg.generator === 'table' && !cfg.keyName || cfg.generator === 'sequence' && !cfg.seqName) {
            throw ErrorFactory.getError("0050", [entityName]);
        }
        const ecfg: EntityConfig = this.entityConfigs.get(entityName);
        ecfg.setId(cfg);
    }

    /**
     * 设置版本号字段名
     * @param entityName -    实体类名
     * @param propName -      版本号字段属性名
     */
    public static addVersion(entityName: string, propName: string){
        this.checkAndNew(entityName);
        const ecfg: EntityConfig = this.entityConfigs.get(entityName);
        //TODO注释代码何意
        // let column:EntityColumnOption = ecfg.getColumn(propName);
        // if(column){  //选择时，不显示
        //     column.select = false;
        // }
        ecfg.version = propName;
    }

    /**
     * 添加实体字段
     * @param entityName -    实体类名
     * @param colName -       实体字段名
     * @param cfg -           实体字段配置
     */
    public static addColumn(entityName: string, colName: string, cfg: EntityColumnOption) {
        this.checkAndNew(entityName);
        const ecfg: EntityConfig = this.entityConfigs.get(entityName);
        //column name 默认为属性名
        if (!cfg.name) {
            cfg.name = colName;
        }
        ecfg.addColumn(colName, cfg);
    }

    /**
     * 添加实体关系
     * @param entityName -    实体名 
     * @param colName -       属性名
     * @param rel -           关系对象
     */
    public static addRelation(entityName: string, colName: string, rel: EntityRelation) {
        this.checkAndNew(entityName);
        const ecfg: EntityConfig = this.entityConfigs.get(entityName);
        ecfg.addRelation(colName, rel);
    }

    /**
     * 检查class是否存在，不存在则新建
     * @param entityName -    实体类名
     */
    private static checkAndNew(entityName) {
        if (!this.entityConfigs.has(entityName)) {
            this.entityConfigs.set(entityName, new EntityConfig());
        }
    }
    /**
     * 获取entity classname  对应的配置项
     * @param entityName -    实体类名
     * @returns             实体配置
     */
    public static getEntityConfig(entityName: string): EntityConfig {
        return this.entityConfigs.get(entityName);
    }

    /**
     * 是否有entity class
     * @param entityName -    实体类名
     * @returns             是否存在
     */
    public static hasEntityConfig(entityName: string): boolean {
        return this.entityConfigs.has(entityName);
    }

    /**
     * 从文件添加实体到工厂
     * @param path -  文件路径
     */
    public static async addEntities(path: string) {
        const basePath = process.cwd();
        const pathArr = path.split('/');
        const pa = [basePath];
        let handled: boolean = false;    //是否已处理
        for (let i = 0; i < pathArr.length - 1; i++) {
            const p = pathArr[i];
            if (p.indexOf('*') === -1 && p !== "") {
                pa.push(p);
            } else if (p === '**') { //所有子孙目录
                handled = true;
                if (i < pathArr.length - 2) {
                    throw "entities config is not correct!";
                }
                await handleDir(pa.join('/'), pathArr[pathArr.length - 1], true);
            }
        }
        if (!handled) {
            await handleDir(pa.join('/'), pathArr[pathArr.length - 1]);
        }

        /**
         * 处理子目录
         * @param dirPath -   目录路径
         * @param fileExt -   文件扩展名
         * @param deep -      是否深度遍历
         */
        async function handleDir(dirPath: string, fileExt: string, deep?: boolean) {
            const fsMdl = require('fs');
            const pathMdl = require('path');
            const dir = fsMdl.readdirSync(dirPath, { withFileTypes: true });
            const fn: string = fileExt;
            const reg: RegExp = EntityFactory.toReg(fn, 3);
            for (const dirent of dir) {
                if (dirent.isDirectory()) {
                    if (deep) {
                        await handleDir(pathMdl.resolve(dirPath, dirent.name), fileExt, deep);
                    }
                } else if (dirent.isFile()) {
                    if (reg.test(dirent.name)) {
                        await import(pathMdl.resolve(dirPath, dirent.name));
                    }
                }
            }
        }
    }

    /**
     * 通过表名获取配置对象
     * @param tblName -   表名
     * @returns         entity 配置对象
     */
    public static getEntityCfgByTblName(tblName: string): EntityConfig {
        for (const v of this.entityConfigs) {
            if (v[1].getTableName(false) === tblName) {
                return v[1];
            }
        }
    }

    /**
     * 设置属性值
     * @param entity -    实体对象
     * @param value -     实体值
     */
    public static setIdValue(entity: IEntity, value: unknown) {
        const cfg: EntityConfig = this.getEntityConfig(entity.constructor.name);
        if(cfg){
            entity[cfg.getId().name] = value;
        }
    }

    /**
     * 获取id值
     * @param entity -    实体对象
     * @returns         实体id值
     */
    public static getIdValue(entity: IEntity): unknown {
        const cfg: EntityConfig = this.getEntityConfig(entity.constructor.name);
        if(cfg){
            return entity[cfg.getId().name];
        }
    }

    /**
     * 获取所有实体配置
     * @returns     实体配置数组
     */
    public static getAllEntityConfig():EntityConfig[]{
        const arr = [];
        for(const v of this.entityConfigs.values()){
            arr.push(v);
        }
        return arr;
    }

    /**
     * 字符串转正则表达式
     * @param str -       源串
     * @param side -      匹配的边 1 左边 2右边 3两边
     * @returns
     */
    private static toReg(str: string, side?: number): RegExp {
        //替换/为\/
        str = str.replace(/\//g, '\\/');
        //替换.为\.
        str = str.replace(/\./g, '\\.');
        //替换*为.*
        str = str.replace(/\*/g, '.*');
        if (side !== undefined) {
            switch (side) {
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
}

export { EntityFactory }