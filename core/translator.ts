import { IEntityCfg, IEntityColumn, IEntity, IEntityRelation } from "./types";
import { BaseEntity } from "./baseentity";
import { EntityFactory } from "./entityfactory";
import { ErrorFactory } from "./errorfactory";
import { RelaenUtil } from "./relaenutil";

/**
 * 翻译器
 */
class Translator{
    /**
     * 实体别名 map {entityName:aliasName}
     */
    // public entityAliasMap:Map<string,string> = new Map();

    /**
     * 关联表 {entityName: joinedEntityName}
     */
    public joinTbls:Map<string,object> = new Map();

    /**
     * 链式map {linkName:{entity:entityName,alias:aliasName,co:column object}}
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
     * order by string
     */
    private orderString:string;

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
        for(let key of orm.columns){
            let fo:IEntityColumn = key[1];
            let v:any;
            if(fo.refName){ //外键，只取主键
                let refEn = entity[key[0]];
                v =  refEn && refEn instanceof BaseEntity? RelaenUtil.getIdValue(refEn):null;
            }else{
                v = entity[key[0]];
            }
            
            // //如果绑定字段名不存在，则用属性名
            let fn = fo.name?fo.name:key[0];
            if(v === null){
                continue;
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
            fields.push(fn);
            //字段值
            let v;
            if(fo.refName){ //外键，只取主键
                let refEn = entity[key[0]];
                v =  refEn && refEn instanceof BaseEntity? RelaenUtil.getIdValue(refEn):null;
            }else{
                v = entity[key[0]];
            }

            if(v === null && ignoreUndefinedValue){
                continue;
            }
            
            fv.push(fn + '=?');
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
     * @param entity    实体对象
     */
    public static entityToDelete(entity:any):string{
        let orm:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(!orm){
            throw ErrorFactory.getError("0010",[entity.constructor.name]);
        }
        if(!orm.id){
            throw ErrorFactory.getError('0102');
        }
        let arr:string[] = [];
        arr.push('delete from');
        arr.push(orm.table);
        arr.push('where');
        //id值
        let idValue:any;
        //id名
        let idName:string;
        if(orm.id){
            idValue = entity[orm.id.name];
            if(idValue === undefined || idValue === null){
                throw ErrorFactory.getError('0102');
            }
            let co:IEntityColumn = orm.columns.get(orm.id.name); 
            idName = co.name;
        }
        
        //字符串
        let field:IEntityColumn = orm.columns.get(orm.id.name);
        if(field.type === 'date' || field.type === 'string'){
            idValue = "'" + idValue + "'";
        }
        arr.push(idName + " = " + idValue);
        let sql = arr.join(' ');
        
        return sql;
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
                arr[i] = this.handleOneField(fn,entityName);   
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
                    arr.push(aliasName + '.' + o[1].name + ' as ' + aliasName + '_' + o[0]);
                }
                return arr.join(',');
            }else{
                return '';
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
                this.joinTbls.set(rel.entity,{entity:entityName,co:co});
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
            //从jointable移除重复的实体
            if(this.joinTbls.has(t)){
                this.joinTbls.delete(t);
            }
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
     * @param params 
     * @param entityName 
     */
    public handleWhere(params:object,entityName?:string){
        if(!params || typeof params !== 'object'){
            return null;
        }
        let pValues:any[] = [];
        let whereStr:string = '';
        let index:number = 0;
        let lastLogic:boolean = false;
        Object.getOwnPropertyNames(params).forEach((item,ii) => {
            //字段名
            let fn = this.handleOneField(item,entityName,null,true);
            index++;
            let vobj = params[item];
            let rel: string = '=';
            let v:any;
            //参数值为对象
            if ( vobj !== null && typeof vobj === 'object') {
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
            
            //置为false
            lastLogic = false;

            //前置字符串，通常为'('
            if(vobj.before){
                let be:string = vobj.before.trim().toUpperCase();
                whereStr += ' ' + be + ' ';
                if(be === 'AND' || be === 'OR'){
                    lastLogic = true;
                }
            }
            //默认 and 关系
            if(index>1 && !lastLogic){
                whereStr += ' AND ';
            }
            
            //字段和值
            whereStr += fn + ' ' + rel + ' ?';
            //后置字符串，通常为 'and','or',')'
            if(vobj.after){
                let be:string = vobj.after.trim().toUpperCase();
                whereStr += ' ' + be + ' ';
                if(be === 'AND' || be === 'OR'){
                    lastLogic = true;
                }
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
     */
    public getQuerySql(){
        let fields:string;
        if(!this.selectedFields || this.selectedFields.length===0){
            fields = this.handleOneField('*',this.mainEntityName);
        }else{
            fields = this.selectedFields.join(',')
        }
        //linkName不存在主表，则需要设置主表
        if(!this.linkNameMap.has(this.mainEntityName)){
            this.linkNameMap.set(this.mainEntityName,{alias:'t0',entity:this.mainEntityName});
        }
        let orm:IEntityCfg = EntityFactory.getClass(this.mainEntityName);
        let sql = 'SELECT ' + fields + ' FROM ' + orm.table + ' ' + this.linkNameMap.get(this.mainEntityName)['alias'];
        
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
}

export {Translator};