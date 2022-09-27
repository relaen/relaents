import { EntityManager } from "./entitymanager";
import { BaseEntity } from "./baseentity";
import { SqlExecutor } from "./sqlexecutor";
import { EntityFactory } from "./entityfactory";
import { IEntity, EEntityState, EQueryType, IEntityRelation, ELockMode } from "./types";
import { ErrorFactory } from "./errorfactory";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { Translator } from "./translator";
import { TranslatorFactory } from "./translatorfactory";
import { EntityConfig } from "./entityconfig";

/**
 * 查询类
 */
class Query {
    /**
     * entity 管理器
     */
    entityManager: EntityManager;

    /**
     * 参数值数组或对象
     */
    paramArr: any[] | object;

    /**
     * 执行sql
     */
    execSql: string;

    /**
     * 实体类名
     */
    entityClassName: string;

    /**
     * 查询记录的start index
     */
    start: number;

    /**
     * 查询记录的条数限制
     */
    limit: number;

    /**
     * 类型 0:select , 1:insert , 2:update, 3:delete
     */
    type: EQueryType;

    /**
     * 实体别名map
     */
    aliasMap: Map<string, any>;

    /**
     * 解释器
     */
    private translator: Translator;

    /**
     * 构造query对象
     * @param em                entity manager
     * @param entityClassName   对应的结果实体类名
     */
    constructor(em: EntityManager, entityClassName?: string) {
        if (entityClassName && !EntityFactory.getEntityConfig(entityClassName)) {
            throw ErrorFactory.getError("0020", [entityClassName]);
        }
        this.entityManager = em;
        this.entityClassName = entityClassName;
        this.type = EQueryType.SELECT;
        this.paramArr = [];
        //初始化translator
        if (this.constructor.name === 'Query') {
            this.translator = TranslatorFactory.get(entityClassName);
        }
    }

    /**
     * 设置查询参数值
     * @param index     占位符下标
     * @param value     占位符值 
     */
    public setParameter(index: number, value: any) {
        //补全参数个数
        if (Array.isArray(this.paramArr) && this.paramArr.length <= index) {
            for (let i = this.paramArr.length; i <= index; i++) {
                this.paramArr.push(null);
            }
        }
        //对于entity，只获取其主键
        this.paramArr[index] = value instanceof BaseEntity ? EntityFactory.getIdValue(value) : value;
    }

    /**
     * 设置多个参数值，从下标0开始
     * @param valueArr  值数组
     */
    public setParameters(valueArr: Array<any>) {
        valueArr.forEach((value, i) => {
            //对于entity，只获取其主键
            let v = value instanceof BaseEntity ? EntityFactory.getIdValue(value) : value;
            if (Array.isArray(this.paramArr) && i >= this.paramArr.length) {
                this.paramArr.push(v);
            } else {
                this.paramArr[i] = v;
            }
        });
    }

    /**
     * 设置开始记录位置
     * @param start     开始位置
     */
    public setStart(start: number) {
        if (start >= 0) {
            this.start = start;
        }
    }

    /**
     * 设置获取记录数
     * @param limit     记录数
     */
    public setLimit(limit: number) {
        if (limit > 0) {
            this.limit = limit;
        }
    }

    /**
     * 获取单个查询结果
     * @param notEntity     不返回实体，如果类为true且只有一个属性值，则直接返回属性值，否则返回对象
     * @returns             select:实体|object|值
     *                      delete:true/false
     */
    public async getResult(notEntity?: boolean): Promise<any> {
        this.preHandle();
        let r;
        switch (this.type) {
            case EQueryType.SELECT:
                r = await this.getResultList(null, 1, notEntity);
                if (r && r.length > 0) {
                    //返回实体
                    if (!notEntity) {
                        return r[0];
                    }
                    let props = Object.getOwnPropertyNames(r[0]);
                    //如果只有一个属性，则只返回属性值
                    if (props.length === 1) {
                        return r[0][props[0]];
                    }
                    return r[0];
                }
                return null;
            case EQueryType.DELETE:
                r = await SqlExecutor.exec(this.entityManager, this.execSql, this.paramArr);
                return r ? true : false;
        }
    }

    /**
     * 获取结果列表
     * @param start         开始索引
     * @param limit         记录数
     * @param notEntity     是否不转成为实体
     * @returns             实体或对象数组
     */
    public async getResultList(start?: number, limit?: number, notEntity?: boolean): Promise<any> {
        //查询时才可调用
        if (this.type !== EQueryType.SELECT) {
            return null;
        }
        this.preHandle();
        this.setStart(start);
        this.setLimit(limit);

        let results: any[] = await SqlExecutor.exec(this.entityManager, this.execSql, this.paramArr, this.start, this.limit);
        if (!notEntity && Array.isArray(results)) {
            let retArr: any[] = [];
            for (let r of results) {
                retArr.push(this.genEntity(r));
            }
            return retArr;
        }
        return results;
    }

    /**
     * 预处理
     */
    private preHandle() {
        if (!this.execSql) {
            let r = this.translator.getQuerySql();
            this.execSql = r[0];
            this.aliasMap = new Map();

            for (let o of r[1]) {
                this.aliasMap.set(o[1]['alias'], {
                    entity: o[1]['entity'],
                    linkName: o[0]
                });
            }
            this.paramArr = r[2];
        }
    }


    /**
     * 生成实体
     * @param r     查询结果
     * @returns     实体对象
     */
    private genEntity(r: any): IEntity {
        let ecfg: EntityConfig = EntityFactory.getEntityConfig(this.entityClassName);
        let entity: IEntity = new ecfg.entity();
        //存储外键值
        Object.getOwnPropertyNames(r).forEach((field) => {
            //无值字段不处理
            if (r[field] === undefined || r[field] === null) {
                return;
            }
            let ind: number = field.indexOf('_');
            //别名
            let alias: string = field.substr(0, ind);
            let propName: string = field.substr(ind + 1);

            //主表
            if (alias.toLocaleLowerCase() === 't0') {
                //属性名
                if (!ecfg.columns.has(propName)) {
                    return;
                }
                if (!ecfg.columns.get(propName).refName) { //属性
                    entity[propName] = r[field];
                }
            } else { //关联表
                if (!this.aliasMap.has(alias)) {
                    return;
                }
                let obj: any = this.aliasMap.get(alias);
                let arr = obj.linkName.split('.');
                if (arr[0] !== this.entityClassName) {
                    return;
                }
                handleSubEntity(entity, arr.slice(1), propName, r[field]);
            }
        });
        EntityManagerFactory.setEntityStatus(entity, EEntityState.PERSIST);
        return entity;

        function handleSubEntity(entity: IEntity, arr: string[], key: string, value: any) {
            let eo = entity[arr[0]];

            if (!eo) {
                let orm: EntityConfig = EntityFactory.getEntityConfig(entity.constructor.name);
                //获取关系对象
                let rel: IEntityRelation = orm.getRelation(arr[0]);
                if (!rel) {
                    return;
                }
                orm = EntityFactory.getEntityConfig(rel.entity);
                if (!orm) {
                    return;
                }
                eo = new orm.entity();
                entity[arr[0]] = eo;
            }
            if (arr.length === 1) {
                eo[key] = value;
            } else {
                handleSubEntity(eo, arr.slice(1), key, value);
            }
        }
    }

    /**
     * 构造查询 字段集
     * @param fields    属性或属性数组
     * @since 0.2.0
     */
    select(fields: string | Array<string>): Query {
        this.type = EQueryType.SELECT;
        this.translator.sqlType = this.type;
        if (!fields) {
            return;
        }
        if (!Array.isArray(fields)) {
            fields = [fields];
        }
        this.translator.handleSelectFields(fields);
        return this;
    }

    /**
     * 添加查询 表集
     * @description     主表的关联表会自动处理，此处不需要加入
     * @param tables    实体类名或实体类名数组 
     * @since 0.2.0
     */
    from(tables: string | Array<string>): Query {
        if (!tables) {
            return;
        }
        if (!Array.isArray(tables)) {
            tables = [tables];
        }
        this.translator.handleFrom(tables);
        return this;
    }

    /**
     * 添加where条件
     * @param params    参数对象{paramName1:paramValue1,paramName2:{value:paramValue2,rel:'>',before:'(',after:'and'}...}
     *                  参数值有两种方式，一种是直接在参数名后给值，一种是给对象，对象中包括:
     *                  value:值,rel:关系,before:字段前字符串(通常为"("),after:值后字符串(通常为"and","or",")")
     *                  关系包括 >,<,>=,<=,<>,is,like等
     * @since 0.2.0
     */
    where(params: object): Query {
        this.translator.handleWhere(params);
        return this;
    }

    /**
     * 添加排序对象
     * @param params    {paramName1:'desc',paramName2:'asc',...} paramName1:参数名,desc:降序 asc:升序
     * @since 0.2.0
     */
    orderBy(params: object) {
        this.translator.handleOrder(params);
        return this;
    }

    /**
     * 添加distinct
     * @since 0.2.0
     */
    distinct() {
        if (!this.translator.modifiers) {
            this.translator.modifiers = [];
        }
        this.translator.modifiers.push('DISTINCT');
        return this;
    }

    /**
     * 构造删除
     * @since 0.2.0
     */
    delete() {
        this.type = EQueryType.DELETE;
        this.translator.sqlType = this.type;
        return this;
    }

    /**
     * 添加分组条件
     * @param params 属性或属性数组
     * @since 0.4.0
     */
    groupBy(params: string | Array<string>) {
        this.translator.handleGroup(params);
        return this;
    }

    /**
     * 添加where条件
     * @param params    参数对象{paramName1:paramValue1,paramName2:{value:paramValue2,rel:'>',before:'(',after:'and'}...}
     *                  参数值有两种方式，一种是直接在参数名后给值，一种是给对象，对象中包括:
     *                  value:值,rel:关系,before:字段前字符串(通常为"("),after:值后字符串(通常为"and","or",")")
     *                  关系包括 >,<,>=,<=,<>,is,like等
     * @since 0.4.0
     */
    having(params: object) {
        this.translator.handleHaving(params);
        return this;
    }

    /**
     * 设置查询锁模式
     * @param ELockMode     锁模式
     * @param ElockType     锁类型
     * @since 0.4.0
     */
    setLock(lockMode: ELockMode) {
        if (lockMode) {
            this.translator.lockMode = lockMode;
        }
        return this;
    }
}

export { Query }