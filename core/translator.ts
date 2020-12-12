import { IEntityCfg, IEntityColumn, IEntity } from "./entitydefine";
import { BaseEntity } from "./baseentity";
import { EntityFactory } from "./entityfactory";
import { ErrorFactory } from "./errorfactory";
import { RelaenUtil } from "./relaenutil";
import { Entity } from "./decorator/decorator";

/**
 * 翻译器
 */
class Translator{
    /**
     * entity转insert sql
     * @param entity 
     */
    public static entityToInsert(entity:any):string{
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
        for(let key of orm.columns){
            let fo:IEntityColumn = key[1];
            let v:any;
            if(fo.refName){ //外键，只取主键
                if(entity[key[0]] instanceof BaseEntity){
                    v = RelaenUtil.getIdValue(entity[key[0]]);
                }
            }else{
                v = entity[key[0]];
            }
            
            //值不存在，则下一个
            if(v === undefined){
                continue;
            }
            
            fields.push(fo.name);
            
            //值
            if(v !== null && (fo.type === 'date' || fo.type === 'string')){
                v = RelaenUtil.valueToString(v);
            }
            values.push(v);
            
        }
        arr.push(fields.join(','));
        arr.push(') values (');
        arr.push(values.join(','));
        arr.push(')');
        let sql = arr.join(' ');
        return sql;
    }

    /**
     * entity转update sql
     * @param entity                待更新entity
     * @param ignoreUndefinedValue  忽略undefined值
     */
    public static entityToUpdate(entity:IEntity,ignoreUndefinedValue?:boolean):string{
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
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(!cfg.id){
            throw ErrorFactory.getError('0103');
        }
        for(let key of orm.columns){
            let fo:any = key[1];
            
            //字段值
            let v;
            if(fo.refName){ //外键，只取主键
                if(entity[key[0]] instanceof BaseEntity){
                    v = RelaenUtil.getIdValue(entity[key[0]]);
                }
            }else{
                v = entity[key[0]];
            }

            //如果绑定字段名不存在，则用属性名
            let fn = fo.name?fo.name:key;
            if(v === null || v === undefined){
                if(ignoreUndefinedValue){
                    continue;
                }else{
                    if(!fo.nullable){
                        throw ErrorFactory.getError('0021',[key]);
                    }
                }
                v = null;
            }else if(fo.type === 'date' || fo.type === 'string'){
                v = RelaenUtil.valueToString(v);
            }
            fv.push(fn + '=' + v);
            if(key[0] === cfg.id.name){
                idValue = v;
                idName = key[1].name;
            }
        }
        arr.push(fv.join(','));
        //where
        arr.push('where');
        arr.push(idName + '=' + idValue);
        let sql = arr.join(' ');
        return sql;
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
     * 生成entity 查询sql串
     * @param entityClassName   实体类名
     * @param params            参数对象
     */
    public static genEntityQuery(entityClassName:string,params?:any):string{
        let ecfg:IEntityCfg = EntityFactory.getClass(entityClassName);
        if(!ecfg){
            throw ErrorFactory.getError("0020",[entityClassName]);
        }

        let sql:string = "select * from " + ecfg.table;
        if(params){
            let whereArr:string[] = [];
            Object.getOwnPropertyNames(params).forEach((item)=>{
                whereArr.push(ecfg.columns.get(item).name + "=?");
            });
            sql += ' where ' + whereArr.join(' and ');
        }

        return sql;
    }
    
    /**
     * 获取sql 字符串
     * @param rql 
     * @returns     {sql:string,fk:外键字段数组,map:{aliasName:{entity:对应实体类,from:来源别名,propName:属性名}}}
     */
    public static getQuerySql(rql:string):any{
        //删除多余的空格
        rql = rql.trim().replace(/\s+/g,' ');
        let rql1:string = rql.toLowerCase();

        //select 位置
        let indSelect:number = rql1.indexOf('select ')?0:-1;
        // from 位置
        let indFrom:number = rql1.indexOf(' from ');
        // where 位置
        let indWhere:number = rql1.indexOf(' where ');
        // order by 位置
        let indOrderby:number = rql1.indexOf(' order by ');
        
        if(indFrom === -1){
            throw ErrorFactory.getError('0100');
        }

        //表关联数组
        let tableArr:string[];
        //字段关联数组
        let columnArr:string[];
        // where 数组
        let whereArr:string[];
        // order by 数组
        let orderByArr:string[];
        let indTbl:number = indWhere!==-1?indWhere:(indOrderby!==-1?indOrderby:rql.length);

        if(indTbl > indFrom + 1){
            tableArr = rql.substring(indFrom+6,indTbl).trim().split(' ');

            if(indWhere !== -1){
                whereArr = rql.substring(indWhere+7,indOrderby!==-1?indOrderby:rql.length).trim().split(' ');
            }

            if(indOrderby !== -1){
                orderByArr = rql.substring(indOrderby+10,rql.length).trim().split(' ');
            }
        }
        
        columnArr = rql.substring(indSelect+7,indFrom).replace(/\s/g,'').split(',');
        
        //别名索引号
        let aliasIndex:number = 0;

        //别名实体相关map，oldAlias 为rql中定义的别名，newAlias为框架生成的别名，所有oldAlias都需要重新生成
        //索引map {oldAlias:newAlias} 
        let aliasMap:Map<string,string> = new Map();
        // 老别名实体map {oldAlias:entityName}
        let oldAliasEnMap:Map<string,string> = new Map();
        // 老实体别名map {entityName:oldAlias}
        let oldEnAliasMap:Map<string,string> = new Map();
        // 新实体别名map {entityName:newAlias}
        let newEnAliasMap:Map<string,string> = new Map();
        //需要返回的别名对象map
        let retAliasMap:Map<string,object> = new Map();
        // 隐含join main table 的tables{entity:实体名,refEntity:join 右侧实体名,column:column对象}
        let joinTbls:any[] = [];

        // 外键查询别名数组
        let fkArray:string[] = [];

        // select 字段集合 {column:true}
        // let selectFieldMap:Map<string,boolean> = new Map();
        handleTable(tableArr);
        
        handleSelectFields(columnArr);
        if(whereArr){
            handleWhere(whereArr);
        }
        if(orderByArr){
            handleOrderby(orderByArr);
        }
        
        let tblStr = EntityFactory.getClass(tableArr[0]).table + ' t0 ';
        //处理主表join
        if(joinTbls.length > 0){
            for(let o of joinTbls){
                let al1:string = newEnAliasMap.get(o.entity);
                let al2:string = newEnAliasMap.get(o.refEntity);
                tblStr += ' left join ' + EntityFactory.getClass(o.refEntity).table + ' ' + al2 + ' on ' + 
                        al1 + '.' + o.column.name + ' = ' + al2 + '.' + o.column.refName;
            }
        }
        tableArr[0] = tblStr;
        //为表添加新别名
        for(let i=1;i<tableArr.length;i++){
            if(newEnAliasMap.has(tableArr[i])){
                tableArr[i] += ' ' + newEnAliasMap.get(tableArr[i]); 
            }
        }

        let sql:string = 'select ' + columnArr.join(',') + ' from ' + tableArr.join(' ');
        if(whereArr){
            sql += ' where ' + whereArr.join(' ');
        }
        if(orderByArr){
            sql += ' order by ' + orderByArr.join(' ');
        }
        return {sql:sql,map:retAliasMap,fk:fkArray};
        /**
         * 处理表串
         * @param tblArr    表名数组
         */
        function handleTable(arr:string[]){
            //预处理
            //预处理字符
            let preArr:string[] = [',','='];
            for(let i=0;i<arr.length;i++){
                let ch = arr[i];
                for(let pa of preArr){
                    if(ch !== pa && ch.indexOf(pa) !== -1){  
                        handleChar(arr,i,pa);
                        continue;
                    }
                }
            }
            let isEntity:boolean = true;
            let isField:boolean = false;
            for(let i=0;i<arr.length;i++){
                let ch = arr[i];
                let en:string;
                if(ch === ',' || ch === 'join'){
                    isEntity = true;
                    continue;
                }else if(ch === 'on'){  //处理字段 join on field1 = field2
                    isField = true;
                    continue;
                }
                
                if(isEntity){
                    isEntity = false;
                    en = arr[i];
                    let to:IEntityCfg = EntityFactory.getClass(en);
                    if(!to){
                        throw ErrorFactory.getError('0010',[en]);
                    }

                    let alias:string = null;
                    let next:string = arr[i+1];
                    
                    if(next){
                        next = next.toLowerCase();
                        //别名
                        if(![',','left','right','outer','inner'].includes(next)){
                            alias = next;
                            //去掉alias
                            arr.splice(i+1,1);
                        }
                    }
                    //新alias
                    let newAlias:string = 't' + aliasIndex++;
                    newEnAliasMap.set(en,newAlias);
                    //保存alias相关映射
                    if(alias){
                        aliasMap.set(alias,newAlias);
                        oldAliasEnMap.set(alias,en);
                        oldEnAliasMap.set(en,alias);
                        retAliasMap.set(newAlias,{
                            entity:en
                        });
                    }
                }else if(isField){ //
                    isField = false;
                    arr[i] = handleCondField(arr[i]);
                    if(arr[i+1] !== '=' || i+2>=arr.length){
                        throw ErrorFactory.getError('0101');
                    }
                    arr[i+2] = handleCondField(arr[i+2]);
                    i+=2;
                }
            }
        }

        /**
         * 处理分割字符
         * @param arr       原数组
         * @param index     对应索引号
         * @param char      字符     
         */
        function handleChar(arr:string[],index:number,char:string){
            let a:string[] = arr[index].split(char);
            let a1 = [];

            if(arr[index].startsWith(char)){
                a1.push(char);
            }
            for(let i=0;i<a.length;i++){
                if(a[i] !== ''){
                    a1.push(a[i]);
                    a1.push(char);
                }
            }
            //去除最后一个 char
            if(!arr[index].endsWith(char)){
                a1.pop();
            }
            //替换原数组
            a1.unshift(1);
            a1.unshift(index);
            arr.splice.apply(arr,a1);
        }

        /**
         * 处理select字段集合
         * @param arr   字段集合
         */
        function handleSelectFields(arr:string[]){
            for(let i=0;i<arr.length;i++){
                let fn:string = arr[i].trim();
                let ind1:number = fn.indexOf('(');
                if(ind1 !== -1){ //函数内
                    let foo:string = fn.substr(0,ind1).toLowerCase();
                    fn = fn.substring(ind1+1,fn.length-1);
                    arr[i] = foo + '(' + handleCondField(fn) + ')';
                }else{ //普通字段
                    let fa = fn.split('.');
                    let entityName:string = oldAliasEnMap.get(fa[0]);
                    if(!entityName){
                        throw ErrorFactory.getError("0106",[rql]);
                    }
                    let alias:string = newEnAliasMap.get(entityName);
                    let tblObj:IEntityCfg = EntityFactory.getClass(entityName);
                    
                    if(fa.length === 1){  //为模型
                        let f:string[] = [];
                        for(let co of tblObj.columns){
                            if(!co[1].refName){
                                f.push(alias + '.' + co[1].name + ' as ' + alias+ '_' + co[0]);
                            }
                        }
                        arr[i] = f.join(',');
                    }else if(fa.length === 1){ //为字段
                        let co = tblObj.columns.get(fa[1]);
                        if(!co){
                            throw ErrorFactory.getError("0022",[entityName,fa[1]]);
                        }
                        arr[i] = alias + '.' + co.name + ' as ' + alias+ '_' + fa[1];
                    }else{
                        throw ErrorFactory.getError("0106",[rql]);
                    }
                }
            }
        }

        /**
         * 处理where 条件
         */
        function handleWhere(arr:string[]){
            let preArr:string[] = [',','=','>','<','>=','<=','is null','is not null','+','-','*','/','(',')'];
            for(let i=0;i<arr.length;i++){
                let ch = arr[i];
                for(let pa of preArr){
                    if(ch !== pa && ch.indexOf(pa) !== -1){  
                        handleChar(arr,i,pa);
                        continue;
                    }
                }
            }
            let fieldReg:RegExp = /^\w[\w\d]*\./
            for(let i=0;i<arr.length;i++){
                if(!fieldReg.test(arr[i])){
                    continue;
                }
                arr[i] = handleCondField(arr[i]);
            }
        }

        /**
         * 处理where 条件
         */
        function handleOrderby(arr:string[]){
            let preArr:string[] = ['(',')'];
            for(let i=0;i<arr.length;i++){
                let ch = arr[i];
                for(let pa of preArr){
                    if(ch !== pa && ch.indexOf(pa) !== -1){  
                        handleChar(arr,i,pa);
                        continue;
                    }
                }
            }
            let fieldReg:RegExp = /^\w[\w\d]*\./
            for(let i=0;i<arr.length;i++){
                if(!fieldReg.test(arr[i])){
                    continue;
                }
                arr[i] = handleCondField(arr[i]);
            }
        }

        /**
         * 处理条件字段(where、order等)
         * @param fieldStr  字段串
         */
        function handleCondField(fieldStr:string){
            //如a.area.areaId
            let fa:string[] = fieldStr.split('.');
            let entityName = oldAliasEnMap.get(fa[0]);
            fa.shift();
            return handleOneEntity(entityName,fa);

            /**
             * 处理一个实体
             * @param entityName    实体名
             * @param fieldArr      字段数组
             */
            function handleOneEntity(entityName:string,fieldArr:string[]){
                if(fa.length === 0){
                    return null;
                }
                //获取实体配置
                let tblObj:IEntityCfg = EntityFactory.getClass(entityName);
                if(!tblObj){
                    throw ErrorFactory.getError('0010',[entityName]);
                }
                //获取字段对象
                let co:IEntityColumn = tblObj.columns.get(fieldArr[0]);
                if(!co){
                    throw ErrorFactory.getError('0022',[entityName,fieldArr[0]]);
                }
                let alias:string = newEnAliasMap.get(entityName);
                let len = fieldArr.length;
                if(co.refName){  //外键
                    //最后一级或 倒数第二级，判断最后一级是否为id name
                    if(len === 1 || len === 2 && tblObj.id && fieldArr[1] === tblObj.id.name){
                        return alias + '.' + co.name;
                    }
                    let rel = tblObj.relations.get(fieldArr[0]);
                    if(!rel){
                        throw ErrorFactory.getError('0023',[entityName,fieldArr[0]]);
                    }
                    
                    if(!oldEnAliasMap.has(rel.entity)){
                        let al:string = 't' + aliasIndex++;
                        oldEnAliasMap.set(rel.entity,al);
                        oldAliasEnMap.set(al,rel.entity);
                        newEnAliasMap.set(rel.entity,al);
                        //加入主表 join 
                        joinTbls.push({
                            entity:entityName,
                            refEntity:rel.entity,
                            column:co
                        });
                    }
                    //当前实体这一级出队列
                    fieldArr.shift();
                    return handleOneEntity(rel.entity,fieldArr);
                }else if(len === 1){  //最后一个非关系型字段
                    return alias + '.' + co.name;
                }else{ // 非关联字段，后续还有字段，则抛异常
                    throw ErrorFactory.getError('0023',[entityName,fieldArr[0]]);
                }
            }
        }
    }
}

export {Translator};