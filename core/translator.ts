import { IEntityCfg, IEntityColumn, IEntity, IEntityRelation, EQueryType, ICondValueObj } from "./types";
import { BaseEntity } from "./baseentity";
import { EntityFactory } from "./entityfactory";
import { ErrorFactory } from "./errorfactory";
import { RelaenUtil } from "./relaenutil";

/**
 * 翻译器
 */
class Translator{
    /**
     * 链式map {linkName:{entity:实体类名,alias:别名,co:字段对象}}
     * linkName为 实体类名[_外键引用名1_外键引用名2_...]
     * 如: Shop_owner
     */
    public linkNameMap:Map<string,object> = new Map();

    
    /**
     * 别名id
     */
    private aliasId:number = 0;

    /**
     * 选中字段数组
     */
    private selectedFields:string[];

    /**
     * 主实体名
     */
    private mainEntityName:string;
    /**
     * from table 数组
     */
    private fromTables:string[];

    /**
     * where条件string
     */
    private whereObject:any[];

    /**
     * 修饰符 如distince
     */
    public modifiers:string[];

    /**
     * order by string
     */
    private orderString:string;

    /**
     * sql 类型
     */
    public sqlType:EQueryType;

    constructor(entityName?:string){
        this.mainEntityName = entityName;
    }
    /**
     * entity转insert sql
     * @param entity 
     */
    public static entityToInsert(entity:any):any[]{
        let orm:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(!orm){
            throw ErrorFactory.getError("0010",[entity.constructor.name]);
        }
        let arr:string[] = [];
        arr.push('insert into');
        arr.push(orm.table);
        arr.push('(');
        //字段组合
        let fields:string[] = [];
        //值组合
        let values:string[] = [];
        //占位符
        let qArr:string[] = [];

        //id字段名
        let idField:string = orm.id && orm.columns.has(orm.id.name) ? orm.columns.get(orm.id.name).name:undefined;

        for(let key of orm.columns){
            let fo:IEntityColumn = key[1];
            let v:any;
            if(fo.refName){ //外键，只取主键
                let refEn = entity[key[0]];
                v =  refEn && refEn instanceof BaseEntity? RelaenUtil.getIdValue(refEn):null;
            }else{
                v = entity[key[0]];
            }
            
            // 如果绑定字段名不存在，则用属性名
            let fn = fo.name?fo.name:key[0];
            //值为空或字段已存在，则不添加
            if(v === null || fields.includes(fn)){
                continue;
            }
            //设置主键
            if(idField === fn){
                RelaenUtil.setIdValue(entity,v);
            }
            fields.push(fn);
            values.push(v);
            qArr.push('?');
        }
        arr.push(fields.join(','));
        arr.push(') values (');
        arr.push(qArr.join(','));
        arr.push(')');
        let sql = arr.join(' ');
        return [sql,values];
    }

    /**
     * entity转update sql
     * @param entity                待更新entity
     * @param ignoreUndefinedValue  忽略undefined值
     */
    public static entityToUpdate(entity:IEntity,ignoreUndefinedValue?:boolean):any[]{
        let orm:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(!orm){
            throw ErrorFactory.getError("0010",[entity.constructor.name]);
        }

        let arr:string[] = [];
        arr.push('update');
        arr.push(orm.table);
        arr.push('set');
        let fv:string[] = [];
        //id值
        let idValue:any;
        //id名
        let idName:string;
        if(!orm.id){
            throw ErrorFactory.getError('0103');
        }
        let fields:string[] = [];
        let values:any[] = [];
        for(let key of orm.columns){
            let fo:any = key[1];
            //如果绑定字段名不存在，则用属性名
            let fn = fo.name?fo.name:key[0];
            //保存已添加字段，不重复添加
            if(fields.includes(fn)){
                continue;
            }
            
            //字段值
            let v;
            if(fo.refName){ //外键，只取主键
                let refEn = entity[key[0]];
                v =  refEn && refEn instanceof BaseEntity? RelaenUtil.getIdValue(refEn):null;
            }else{
                v = entity[key[0]];
            }
            //值为空且不忽略空值或字段已添加，则不处理
            if(v === null && ignoreUndefinedValue || fields.includes(fn)){
                continue;
            }
            
            fv.push(fn + '=?');
            fields.push(fn);
            values.push(v);
            if(key[0] === orm.id.name){
                idValue = v;
                idName = key[1].name;
            }
        }
        if(!idValue){
            throw ErrorFactory.getError('0021',[orm.id.name]);
        }
        arr.push(fv.join(','));
        //where
        arr.push('where');
        arr.push(idName + '=' + idValue);
        let sql = arr.join(' ');
        return [sql,values];
    }

    /**
     * entity转update sql
     * @param entity        实体对象
     * @param className     实体类名
     * 
     */
    public static toDelete(entity:any,className?:string):any[]{
        className = className || entity.constructor.name;
        let idName:string;
        let idValue:any;
        let orm:IEntityCfg = EntityFactory.getClass(className);
        if(!orm){
            throw ErrorFactory.getError("0010",[className]);
        }
        if(entity instanceof BaseEntity){
            idName = RelaenUtil.getIdName(entity);
            idValue = RelaenUtil.getIdValue(entity);
        }else if(className){
            idValue = entity;
            idName = orm.id?orm.id.name:null;
        }
        if(!idName || !idValue){
            throw ErrorFactory.getError("0025");
        }
        return ["delete from " + orm.table + ' where ' + orm.columns.get(idName).name + '=?',idValue];
    }

    /**
     * 处理前置修饰符
     */
    public handleModifer(modifier:string){
        if(!this.modifiers.includes(modifier)){
            this.modifiers.push(modifier);
        }
    }

    /**
     * 处理select字段集合
     * @param arr   字段集合
     */
    public handleSelectFields(arr:string[],entityName?:string){
        //第一个为实体名
        for(let i=0;i<arr.length;i++){
            let fn:string = arr[i].trim();
            let ind1:number = fn.indexOf('(');
            if(ind1 !== -1){ //函数内
                let foo:string = fn.substr(0,ind1).toLowerCase();
                fn = fn.substring(ind1+1,fn.length-1);
                arr[i] = foo + '(' + this.handleOneField(fn,entityName,null,true) + ')';
            }else{ //普通字段
                let r = this.handleOneField(fn,entityName);
                if(r !== null){
                    arr[i] = r;
                }else{ //移除
                    arr.splice(i,1);
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
    private handleOneField(field:string,entityName?:string,linkName?:string,isCond?:boolean){
        field = field.trim();
        if(field === ''){
            return '';
        }
        if(field === '*'){
            //条件字段不处理
            if(isCond){
                return '*';
            }
            entityName = entityName || this.mainEntityName;
            linkName = linkName || entityName;
            if(entityName && EntityFactory.hasClass(entityName)){
                let orm:IEntityCfg = EntityFactory.getClass(entityName);
                let arr = [];
                for(let o of orm.columns){
                    let aliasName:string
                    if(!this.linkNameMap.has(linkName)){
                        aliasName = 't' + this.aliasId++;
                        this.linkNameMap.set(linkName,{
                            entity:entityName,
                            alias:aliasName
                        });
                    }else{
                        aliasName = this.linkNameMap.get(linkName)['alias'];
                    }
                    //不查询外键
                    if(!o[1].refName){ 
                        arr.push(aliasName + '.' + o[1].name + ' as ' + aliasName + '_' + o[0]);
                    }
                }
                return arr.join(',');
            }else{
                return null;
            }
        }
        //字段分割
        let arr:string[] = field.split('.');
        let index:number = 0;
        //先判断是否为主实体
        if(EntityFactory.hasClass(arr[0])){
            entityName = arr[0];
            index++;
        }
        entityName = entityName || this.mainEntityName;
        //设置默认refName
        linkName = linkName || entityName;
        let aliasName:string;
        //加入linkname map
        if(!this.linkNameMap.has(linkName)){
            aliasName = 't' + this.aliasId++;
            this.linkNameMap.set(linkName,{
                entity:entityName,
                alias:aliasName
            });
        }else{
            aliasName = this.linkNameMap.get(linkName)['alias'];
        }
            
        let orm:IEntityCfg = EntityFactory.getClass(entityName);
        if(!orm){
            throw ErrorFactory.getError('0010',[entityName]);
        }

        for(let i=index;i<arr.length;i++){
            if(arr[i] === '*'){
                return this.handleOneField('*',entityName,linkName,isCond);
            }
            //字段不存在
            if(!orm.columns.has(arr[i])){
                throw ErrorFactory.getError('0022',[entityName,arr[i]]);
            }
            let co = orm.columns.get(arr[i]);
            //外键对象，则直接切换为外键对象
            if(co.refName){
                let rel:IEntityRelation = orm.relations.get(arr[i]);
                let a1 = arr.slice(i+1);
                //添加到join map
                let oldLink = linkName;
                linkName += '.' + arr[i];
                if(!this.linkNameMap.has(linkName)){
                    this.linkNameMap.set(linkName,{
                        entity:rel.entity,
                        alias:'t' + this.aliasId++,
                        co:co,
                        from:oldLink
                    });
                }
                //关联对象后无多余字段
                if(i === arr.length-1){
                    if(isCond){ //如果为条件，则转换为主键
                        a1.push(RelaenUtil.getIdName(rel.entity));
                    }else{ //查询字段，则添加*
                        a1.push('*');
                    }
                }
                return this.handleOneField(a1.join('.'),rel.entity,linkName,isCond);
                
            }else{
                if(isCond){
                    return aliasName + '.' + co.name;
                }
                return aliasName + '.' + co.name + ' as ' + aliasName + '_' + arr[i];
            }
        }
    }

    /**
     * 处理重复entityName
     * @param arr           实体类名数组
     */
    public handleFrom(arr:string[]){
        for(let t of arr){
            //加入entity alias map
            if(!this.linkNameMap.has(t)){
                this.linkNameMap.set(t,{
                    entity:t,
                    alias:'t' + this.aliasId++
                });
            }
        }
        this.fromTables = arr;
    }

    /**
     * 处理where条件
     * @param params        参数对象，每个参数值参考ICondValueObj接口
     * @param entityName    实体类名
     */
    public handleWhere(params:object,entityName?:string){
        if(!params || typeof params !== 'object'){
            return null;
        }
        //值数组
        let pValues:any[] = [];
        //where字符串
        let whereStr:string = '';

        Object.getOwnPropertyNames(params).forEach((item,ii) => {
            //字段名
            let fn = this.handleOneField(item,entityName,null,true);
            //值对象
            let vobj:ICondValueObj = params[item];

            //默认关系符
            let rel: string = '=';
            //值
            let v:any;
            //参数值为对象且不为实体对象
            if (vobj !== null && typeof vobj === 'object' && !(vobj instanceof BaseEntity)){
                if (vobj.rel) {
                    rel = vobj.rel.toUpperCase();
                }
                v = vobj.value;
            }else{
                v = vobj;
            }

            //如果值为null且关系为“=”，则需要改为“is”
            if (params[item] === null && rel === '=') {
                rel = 'IS';
            }
            //like 添加%
            if (rel === 'LIKE') {
                v = '%' + v + '%';
            }

            // 如果值为实体对象，则获取实体对象的主键值
            if(v instanceof BaseEntity){
                v = RelaenUtil.getIdValue(v);
            }

            //前置字符串，通常为'('
            if(vobj && vobj.before){
                whereStr += ' ' + vobj.before + ' ';
            }
            //逻辑关系
            if(ii>0){
                if(vobj && vobj.logic &&  vobj.logic.trim() !== ''){
                    whereStr += ' ' + vobj.logic + ' ';
                }else{
                    whereStr += ' AND ';
                }
            }
            //字段和值
            whereStr += fn + ' ' + rel + ' ?';
            //后置字符串，通常为 'and','or',')'
            if(vobj && vobj.after){
                whereStr += ' ' + vobj.after + ' ';
            }

            pValues.push(v);
        });
        if(whereStr !== ''){
            this.whereObject = [whereStr,pValues];
        }
    }

    /**
     * 处理order by
     * @param params 
     * @param entityName 
     */
    public handleOrder(params:object,entityName?:string):string{
        if(!params || typeof params !== 'object'){
            return null;
        }
        let arr = [];
        Object.getOwnPropertyNames(params).forEach((item) => {
            //字段名
            let fn = this.handleOneField(item,entityName,null,true);
            arr.push(fn + ' ' + (params[item] === 'asc'?'asc':'desc'));
        });
        if(arr.length>0){
            this.orderString = arr.join(',');
        }
    }

    /**
     * 产生查询sql
     * @returns     数组[sql,linkMap,values]
     *              其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    public getQuerySql():any[]{
        switch(this.sqlType){
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
    private getSelectSql():any[]{
        let orm:IEntityCfg = EntityFactory.getClass(this.mainEntityName);
        //linkName不存在主表，则需要设置主表
        if(!this.linkNameMap.has(this.mainEntityName)){
            this.linkNameMap.set(this.mainEntityName,{alias:'t0',entity:this.mainEntityName});
        }

        let sql:string = 'SELECT ';
        let fields:string;
        if(!this.selectedFields || this.selectedFields.length===0){
            fields = this.handleOneField('*',this.mainEntityName);
        }else{
            fields = this.selectedFields.join(',')
        }
        
        //前置修饰符
        if(this.modifiers && this.modifiers.length>0){
            sql += ' ' + this.modifiers.join(',') + ' ';
        }
        sql += fields + ' FROM ' + orm.table + ' ' + this.linkNameMap.get(this.mainEntityName)['alias'];
        
        let entities:string[] = [];
        //处理主表和join表
        for(let o of this.linkNameMap){
            entities.push(o[1]['entity']);
            if(!o[1]['from']){
                continue;
            }
            orm = EntityFactory.getClass(o[1]['entity']);
            let al1:string = o[1]['alias'];
            let al2:string = this.linkNameMap.get(o[1]['from'])['alias'];
            let co:IEntityColumn = o[1]['co'];
            sql += ' LEFT JOIN ' + orm.table + ' ' + al1 + ' on ' + al2 + '.' + co.name + '=' + al1 + '.' + co.refName;
        }
        //处理from
        if(this.fromTables){
            for(let t of this.fromTables){
                if(entities.includes(t)){
                    continue;
                }
                orm = EntityFactory.getClass(t);
                sql += ',' + orm.table + ' ' + this.linkNameMap.get(t)['alias'];
            }
        }
        
        if(this.whereObject){
            sql += ' WHERE ' + this.whereObject[0];
        }

        if(this.orderString){
            sql += ' ORDER BY ' + this.orderString;
        }
        return [sql,this.linkNameMap,this.whereObject?this.whereObject[1]:undefined];
    }
    /**
     * 生成增删改sql
     * @returns 数组[sql,linkMap,values]
     *          其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    private getDeleteSql(){
        let orm:IEntityCfg = EntityFactory.getClass(this.mainEntityName);
        let sql = "delete t0 from " + orm.table + ' t0 ' ;
        //处理主表和join表
        for(let o of this.linkNameMap){
            if(!o[1]['from']){
                continue;
            }
            orm = EntityFactory.getClass(o[1]['entity']);
            let al1:string = o[1]['alias'];
            let al2:string = this.linkNameMap.get(o[1]['from'])['alias'];
            let co:IEntityColumn = o[1]['co'];
            sql += ' LEFT JOIN ' + orm.table + ' ' + al1 + ' on ' + al2 + '.' + co.name + '=' + al1 + '.' + co.refName;
        }

        if(this.whereObject){
            sql += ' WHERE ' + this.whereObject[0];
        }
        return [sql,this.linkNameMap,this.whereObject?this.whereObject[1]:undefined];
    }
}

export {Translator};