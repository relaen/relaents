import { IEntityCfg, IEntityColumn, IEntity, IEntityRelation, EQueryType, ICondValueObj, LockMode } from "./types";
import { BaseEntity } from "./baseentity";
import { EntityFactory } from "./entityfactory";
import { ErrorFactory } from "./errorfactory";
import { RelaenUtil } from "./relaenutil";
import { RelaenManager } from "./relaenmanager";
import { ConnectionManager } from "..";

/**
 * 翻译器
 */
export abstract class Translator {
    /**
     * 链式map {linkName:{entity:实体类名,alias:别名,co:字段对象}}
     * linkName为 实体类名[_外键引用名1_外键引用名2_...]
     * 如: Shop_owner
     */
    public linkNameMap: Map<string, object> = new Map();

    /**
     * 别名id
     */
    private aliasId: number = 0;

    /**
     * 主实体名
     */
    protected mainEntityName: string;

    /**
     * 主实体配置项
     */
    protected mainEntityCfg: IEntityCfg;

    /**
     * 修饰符 如distince
     */
    public modifiers: string[];

    /**
     * 选中字段数组
     */
    protected selectedFields: string[];

    /**
     * from table 数组
     */
    protected fromTables: string[];

    /**
     * where条件string
     */
    protected whereObject: any[];

    /**
     * group by string
     */
    protected groupString: string;

    /**
     * having条件string
     */
    protected havingObject: any[];

    /**
     * order by string
     */
    protected orderString: string;

    /**
     * sql 类型
     */
    public sqlType: EQueryType;

    /**
     * lock 模式
     */
    public lockMode: LockMode;

    /**
     * 构造翻译器
     * @param entityName    实体名
     */
    constructor(entityName: string) {
        this.mainEntityName = entityName;
        this.mainEntityCfg = EntityFactory.getClass(this.mainEntityName);
        if (!this.mainEntityCfg) {
            throw ErrorFactory.getError("0010", [this.mainEntityName]);
        }
    }

    /**
     * entity转insert sql
     * @param entity    实体
     * @returns         [sql, values]
     */
    public entityToInsert(entity: any): any[] {
        let arr: string[] = [];
        arr.push('INSERT INTO');
        arr.push(RelaenUtil.getTableName(this.mainEntityCfg));
        //字段组合
        let fields: string[] = [];
        //值组合
        let values: string[] = [];
        //占位符
        let qArr: string[] = [];
        //id字段名
        let idField: string = this.mainEntityCfg.columns.get(this.mainEntityCfg.id.name).name;

        for (let [key, cfg] of this.mainEntityCfg.columns) {
            let v: any;
            if (cfg.refName) { //外键，只取主键
                let refEn = entity[key];
                v = refEn && refEn instanceof BaseEntity ? RelaenUtil.getIdValue(refEn) : null;
            } else {
                v = entity[key];
            }

            // 如果绑定字段名不存在，则用属性名
            let fn = cfg.name ? cfg.name : key;
            //值为空或字段已存在，则不添加
            if (v === null || fields.includes(fn)) {
                continue;
            }
            fields.push(fn);
            values.push(v);
            qArr.push('?');
        }
        arr.push('(' + fields.join(',') + ')');
        arr.push('VALUES');
        arr.push('(' + qArr.join(',') + ')');
        //针对不同的数据库，可能存在附加串
        let extra = ConnectionManager.provider.insertReturn(idField);
        if (extra) {
            arr.push(extra);
        }
        return [arr.join(' '), values];
    }

    /**
     * entity转update sql
     * @param entity                待更新entity
     * @param ignoreUndefinedValue  忽略undefined值
     * @returns                     [sql, values]
     */
    public entityToUpdate(entity: IEntity, ignoreUndefinedValue?: boolean): any[] {
        let arr: string[] = [];
        arr.push('UPDATE');
        arr.push(RelaenUtil.getTableName(this.mainEntityCfg));
        arr.push('SET');
        //字段组合
        let fields: string[] = [];
        //值组合
        let values: any[] = [];
        //id值
        let idValue: any;
        //id字段名
        let idField: string;
        //version字段名
        let versionField: string;
        //version值
        let versionValue: number;

        for (let [key, cfg] of this.mainEntityCfg.columns) {
            //如果绑定字段名不存在，则用属性名
            let fn = cfg.name ? cfg.name : key;
            //字段值
            let v: any;
            if (cfg.refName) { //外键，只取主键
                let refEn = entity[key];
                v = refEn && refEn instanceof BaseEntity ? RelaenUtil.getIdValue(refEn) : null;
            } else {
                v = entity[key];
            }
            //存储主键信息
            if (key === this.mainEntityCfg.id.name) {
                idValue = v;
                idField = cfg.name;
            }
            //乐观锁，添加版本version
            if (this.lockMode === 'optimistic' && cfg.version) {
                if (v === undefined || v === null) {
                    throw ErrorFactory.getError('0408', [this.mainEntityName]);
                }
                if (typeof v !== 'number') {
                    throw ErrorFactory.getError('0409', [this.mainEntityName]);
                }
                if (cfg.type === 'date') {
                    versionValue = new Date().getTime();
                } else {
                    versionValue = v++;
                }
                versionField = fn;
            }
            //值为空且不忽略空值或字段已添加，则不处理（identity标识更新不添加）
            if (v === null && ignoreUndefinedValue || fields.includes(fn + '=?') || cfg.identity) {
                continue;
            }

            fields.push(fn + '=?');
            values.push(v);
        }
        if (!idValue) {
            throw ErrorFactory.getError('0021', [this.mainEntityCfg.id.name]);
        }
        arr.push(fields.join(','));
        //where
        arr.push('WHERE');
        arr.push(idField + '=' + idValue);
        if (versionField && versionValue) {
            arr.push('AND ' + versionField + '=' + versionValue);
        }
        return [arr.join(' '), values];
    }

    /**
     * entity转update sql
     * @param entity        实体对象
     * @param className     实体类名
     * @returns             [sql, values]
     */
    public toDelete(entity: any): any[] {
        let idName: string;
        let idValue: any;

        if (entity instanceof BaseEntity) {
            idName = RelaenUtil.getIdName(entity);
            idValue = RelaenUtil.getIdValue(entity);
        } else {
            idValue = entity;
            idName = this.mainEntityCfg.id.name;
        }
        if (!idName || !idValue) {
            throw ErrorFactory.getError("0025");
        }
        return ["DELETE FROM " + RelaenUtil.getTableName(this.mainEntityCfg) + ' WHERE ' + this.mainEntityCfg.columns.get(idName).name + '=?', idValue];
    }

    /**
     * 处理前置修饰符
     */
    public handleModifer(modifier: string) {
        if (!this.modifiers.includes(modifier)) {
            this.modifiers.push(modifier);
        }
    }

    /**
     * 处理select字段集合
     * @param arr           字段集合
     * @param entityName    实体类名
     */
    public handleSelectFields(arr: string[], entityName?: string) {
        //第一个为实体名
        for (let i = 0; i < arr.length; i++) {
            let fn: string = arr[i].trim();
            let ind1: number = fn.indexOf('(');
            if (ind1 !== -1) { //函数内
                let foo: string = fn.substr(0, ind1).toLowerCase();
                fn = fn.substring(ind1 + 1, fn.length - 1);
                arr[i] = foo + '(' + this.handleOneField(fn, entityName, null, true) + ')';
            } else { //普通字段
                let r = this.handleOneField(fn, entityName);
                if (r !== null) {
                    arr[i] = r;
                } else { //移除
                    arr.splice(i, 1);
                }
            }
        }
        this.selectedFields = arr;
    }

    /**
     * 处理一个字段
     * @param field         字段名 
     * @param entityName    实体类名
     * @param linkName      链名
     * @param isCond        是否为条件字段
     */
    private handleOneField(field: string, entityName?: string, linkName?: string, isCond?: boolean) {
        field = field.trim();
        if (field === '') {
            return '';
        }
        //当*时,查询当前实体下所有普通属性: 别名.字段名 as 别名_属性名
        if (field === '*') {
            //条件字段不处理
            if (isCond) {
                return '*';
            }
            //默认为当前主表实体
            entityName = entityName || this.mainEntityName;
            linkName = linkName || entityName;
            if (entityName && EntityFactory.hasClass(entityName)) {
                let orm: IEntityCfg = EntityFactory.getClass(entityName);
                let arr = [];
                for (let o of orm.columns) {
                    let aliasName: string
                    if (!this.linkNameMap.has(linkName)) {
                        aliasName = 't' + this.aliasId++;
                        this.linkNameMap.set(linkName, {
                            entity: entityName,
                            alias: aliasName
                        });
                    } else {
                        aliasName = this.linkNameMap.get(linkName)['alias'];
                    }
                    //不查询外键
                    if (!o[1].refName) {
                        // 别名+字段名 as 别名_属性名
                        arr.push(aliasName + '.' + o[1].name + ' as "' + aliasName + '_' + o[0] + '"');
                    }
                }
                return arr.join(',');
            } else {
                return null;
            }
        }
        //字段分割
        let arr: string[] = field.split('.');
        let index: number = 0;
        //先判断是否为主实体
        if (EntityFactory.hasClass(arr[0])) {
            entityName = arr[0];
            index++;
        }
        entityName = entityName || this.mainEntityName;
        //设置默认refName
        linkName = linkName || entityName;
        let aliasName: string;
        //加入linkname map
        if (!this.linkNameMap.has(linkName)) {
            aliasName = 't' + this.aliasId++;
            this.linkNameMap.set(linkName, {
                entity: entityName,
                alias: aliasName
            });
        } else {
            aliasName = this.linkNameMap.get(linkName)['alias'];
        }

        let orm: IEntityCfg = EntityFactory.getClass(entityName);
        if (!orm) {
            throw ErrorFactory.getError('0010', [entityName]);
        }

        for (let i = index; i < arr.length; i++) {
            if (arr[i] === '*') {
                return this.handleOneField('*', entityName, linkName, isCond);
            }
            //字段不存在
            if (!orm.columns.has(arr[i])) {
                throw ErrorFactory.getError('0022', [entityName, arr[i]]);
            }
            let co = orm.columns.get(arr[i]);
            //外键对象，则直接切换为外键对象
            if (co.refName) {
                let rel: IEntityRelation = orm.relations.get(arr[i]);
                let a1 = arr.slice(i + 1);
                //添加到join map
                let oldLink = linkName;
                linkName += '.' + arr[i];
                if (!this.linkNameMap.has(linkName)) {
                    this.linkNameMap.set(linkName, {
                        entity: rel.entity,
                        alias: 't' + this.aliasId++,
                        co: co,
                        from: oldLink
                    });
                }
                //关联对象后无多余字段
                if (i === arr.length - 1) {
                    if (isCond) { //如果为条件，则转换为主键
                        a1.push(RelaenUtil.getIdName(rel.entity));
                    } else { //查询字段，则添加*
                        a1.push('*');
                    }
                }
                return this.handleOneField(a1.join('.'), rel.entity, linkName, isCond);

            } else {
                if (isCond) {
                    return aliasName + '.' + co.name;
                }
                return aliasName + '.' + co.name + ' as "' + aliasName + '_' + arr[i] + '"';
            }
        }
    }

    /**
     * 处理重复entityName
     * @param arr       实体类名数组
     */
    public handleFrom(arr: string[]) {
        for (let t of arr) {
            //加入entity alias map
            if (!this.linkNameMap.has(t)) {
                this.linkNameMap.set(t, {
                    entity: t,
                    alias: 't' + this.aliasId++
                });
            }
        }
        this.fromTables = arr;
    }

    /**
     * 处理where条件
     * @param params        参数对象，每个参数值参考ICondValueObj接口
     */
    public handleWhere(params: object) {
        let condition = this.handleCondition(params);
        if (condition && condition[0] !== '') {
            this.whereObject = condition;
        }
    }

    /**
     * 处理group by
     * @param params    分组参数
     * @returns         
     */
    public handleGroup(params: string | string[]) {
        if (!params) {
            return null;
        }
        params = typeof params === 'string' ? [params] : params;
        let arr = [];
        for (const param of params) {
            let fn = this.handleOneField(param, null, null, true);
            arr.push(fn);
        }
        if (arr.length > 0) {
            this.groupString = arr.join(',');
        }
    }

    /**
     * 处理having条件
     * @param params    参数对象，每个参数值参考ICondValueObj接口
     */
    public handleHaving(params: object) {
        let condition = this.handleCondition(params);
        if (condition && condition[0] !== '') {
            this.havingObject = condition;
        }
    }

    /**
     * 处理条件判断 
     * @param params    条件参数
     */
    private handleCondition(params: object) {
        if (!params || typeof params !== 'object') {
            return null;
        }
        //值数组
        let pValues: any[] = [];
        //where字符串
        let whereStr: string = '';

        Object.getOwnPropertyNames(params).forEach((item, ii) => {
            //字段名
            let fn = this.handleOneField(item, null, null, true);
            //值对象
            let vobj: ICondValueObj = params[item];

            //默认关系符
            let rel: string = '=';
            //值
            let v: any;
            //参数值为对象且不为实体对象
            if (vobj !== null && typeof vobj === 'object' && !(vobj instanceof BaseEntity)) {
                if (vobj.rel) {
                    rel = vobj.rel.toUpperCase();
                }
                v = vobj.value;
            } else {
                v = vobj;
            }

            //如果值为null且关系为“=”，则需要改为“is”
            if ((v === null && rel === '=')) {
                rel = 'IS';
            }

            //like默认没有通配符添加%
            if (rel === 'LIKE' && !/[%_]/.test(v)) {
                v = '%' + v + '%';
            }

            // 如果值为实体对象，则获取实体对象的主键值
            if (v instanceof BaseEntity) {
                v = RelaenUtil.getIdValue(v);
            }

            //前置字符串，通常为'('
            if (vobj && vobj.before) {
                whereStr += ' ' + vobj.before + ' ';
            }
            //逻辑关系
            if (ii > 0) {
                if (vobj && vobj.logic && vobj.logic.trim() !== '') {
                    whereStr += ' ' + vobj.logic.toUpperCase() + ' ';
                } else {
                    whereStr += ' AND ';
                }
            }

            // 占位符处理
            let placeholder = ' ?';
            // in,not in,between,not between 字段和值处理
            if (rel === 'IN' || rel === 'NOT IN') {
                if (!Array.isArray(v)) {
                    throw ErrorFactory.getError('0404');
                }
                let arr = new Array(v.length).fill('?');
                placeholder = ' (' + arr.join() + ') ';


            }
            if (rel === 'BETWEEN' || rel === 'NOT BETWEEN') {
                if (!Array.isArray(v) || v.length !== 2) {
                    throw ErrorFactory.getError('0405');
                }
                placeholder = ' ? AND ?';
            }
            //字段和值
            whereStr += fn + ' ' + rel + placeholder;

            //后置字符串，通常为 'and','or',')'
            if (vobj && vobj.after) {
                whereStr += ' ' + vobj.after + ' ';
            }

            if (Array.isArray(v)) {
                for (const vi of v) {
                    pValues.push(vi);
                }
            } else {
                pValues.push(v);
            }
        });
        return [whereStr, pValues];
    }

    /**
     * 处理order by
     * @param params        排序参数
     * @param entityName    实体名
     */
    public handleOrder(params: object, entityName?: string): string {
        if (!params || typeof params !== 'object') {
            return null;
        }
        let arr = [];
        Object.getOwnPropertyNames(params).forEach((item) => {
            //字段名
            let fn = this.handleOneField(item, entityName, null, true);
            arr.push(fn + ' ' + (params[item] === 'asc' ? 'asc' : 'desc'));
        });
        if (arr.length > 0) {
            this.orderString = arr.join(',');
        }
    }

    /**
     * 产生查询sql
     * @returns     数组[sql,linkMap,values]
     *              其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    public getQuerySql(): any[] {
        switch (this.sqlType) {
            case EQueryType.SELECT:
                return this.getSelectSql();
            case EQueryType.DELETE:
                return this.getDeleteSql();
        }
    }

    /**
     * 获取select sql
     * @param mainOrm   主表类对象
     * @returns 数组[sql,linkMap,values]
     *          其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    protected getSelectSql(): any[] {
        //linkName不存在主表，则需要设置主表
        if (!this.linkNameMap.has(this.mainEntityName)) {
            this.linkNameMap.set(this.mainEntityName, { alias: 't0', entity: this.mainEntityName });
        }
        let arr: string[] = [];
        arr.push('SELECT');

        //前置修饰符
        if (this.modifiers && this.modifiers.length > 0) {
            arr.push(this.modifiers.join());
        }

        //查询字段
        if (!this.selectedFields || this.selectedFields.length === 0) {
            arr.push(this.handleOneField('*', this.mainEntityName));
        } else {
            arr.push(this.selectedFields.join());
        }

        //主表from
        arr.push('FROM ' + RelaenUtil.getTableName(this.mainEntityCfg) + ' ' + this.linkNameMap.get(this.mainEntityName)['alias']);
        // 处理mssql的锁机制
        if (this.lockMode === 'pessimistic' && RelaenManager.dialect === 'mssql') {
            arr.push(ConnectionManager.provider.lock('row_write'));
        }

        //处理left join
        let entities: string[] = [];
        let joins: string[] = [];
        for (let [key, link] of this.linkNameMap) {
            entities.push(link['entity']);
            if (!link['from']) {
                continue;
            }
            let orm: IEntityCfg = EntityFactory.getClass(link['entity']);
            let al1: string = link['alias'];
            let al2: string = this.linkNameMap.get(link['from'])['alias'];
            let co: IEntityColumn = link['co'];
            joins.push('LEFT JOIN ' + RelaenUtil.getTableName(orm) + ' ' + al1 + ' ON ' + al2 + '.' + co.name + '=' + al1 + '.' + co.refName);
        }

        //处理inner join
        if (this.fromTables) {
            for (let t of this.fromTables) {
                if (entities.includes(t)) {
                    continue;
                }
                let orm = EntityFactory.getClass(t);
                //添加inner join
                arr.push(',' + RelaenUtil.getTableName(orm) + ' ' + this.linkNameMap.get(t)['alias']);
            }
        }
        //添加left join
        if (joins) {
            arr = arr.concat(joins);
        }

        //参数数组
        let paramsArr = [];
        if (this.whereObject) {
            arr.push('WHERE ' + this.whereObject[0]);
            paramsArr = paramsArr.concat(this.whereObject[1]);
        }

        if (this.groupString) {
            arr.push('GROUP BY ' + this.groupString);
        }

        if (this.havingObject) {
            arr.push('HAVING ' + this.havingObject[0]);
            paramsArr = paramsArr.concat(this.havingObject[1]);
        }

        if (this.orderString) {
            arr.push('ORDER BY ' + this.orderString);
        }

        //悲观锁
        if (this.lockMode === 'pessimistic' && RelaenManager.dialect !== 'mssql') {
            arr.push(ConnectionManager.provider.lock('row_write'));
        }
        return [arr.join(' '), this.linkNameMap, paramsArr];
    }

    /**
     * 生成增删改sql
     * @param notNeedAlias      不需要别名
     * @returns 数组[sql,linkMap,values]
     *          其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    protected getDeleteSql(notNeedAlias?: boolean) {
        if (!this.whereObject && !RelaenManager.fullTableOperation) {
            throw ErrorFactory.getError('0406');
        }
        let arr: string[] = [];
        arr.push('DELETE ' + (notNeedAlias ? '' : 't0') + ' FROM ' + RelaenUtil.getTableName(this.mainEntityCfg) + ' t0');
        //处理主表和join表
        for (let o of this.linkNameMap) {
            if (!o[1]['from']) {
                continue;
            }
            let orm = EntityFactory.getClass(o[1]['entity']);
            let al1: string = o[1]['alias'];
            let al2: string = this.linkNameMap.get(o[1]['from'])['alias'];
            let co: IEntityColumn = o[1]['co'];
            arr.push('LEFT JOIN ' + RelaenUtil.getTableName(orm) + ' ' + al1 + ' on ' + al2 + '.' + co.name + '=' + al1 + '.' + co.refName);
        }
        if (this.whereObject) {
            arr.push('WHERE ' + this.whereObject[0]);
        }
        return [arr.join(' '), this.linkNameMap, this.whereObject ? this.whereObject[1] : undefined];
    }
}