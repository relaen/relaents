import {EntityManager} from "./entitymanager";
import { IEntityCfg, IEntityRelation, ERelationType, IEntityColumn } from "./entitydefine";
import { BaseEntity } from "./entity";
import { EntityFactory } from "./entityfactory";
import { ErrorFactory } from "./errorfactory";

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
            let fo:any = key[1];
            let v = entity[key[0]];
            
            //值不存在，则下一个
            if(v === undefined){
                continue;
            }

            //如果绑定字段名不存在，则用属性名
            fields.push(orm.table + '.' + fo.name?fo.name:key);
            
            //值
            if(v !== null && (fo.type === 'date' || fo.type === 'string')){
                values.push("'" + v + "'");
            }else{
                values.push(v);
            }
        }
        arr.push(fields.join(','));
        arr.push(') values (');
        arr.push(values.join(','));
        arr.push(')');
        return arr.join(' ');
    }

    /**
     * entity转update sql
     * @param entity 
     */
    public static entityToUpdate(entity:any):string{
        let orm:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(!orm){
            throw ErrorFactory.getError("0010",[entity.constructor.name]);
        }

        let arr:string[] = [];
        arr.push('update');
        arr.push(orm.table);
        arr.push('set');
        let fv:string[] = [];
        for(let key of orm.columns){
            let fo:any = key[1];
            let v = entity[key[0]];
            //如果绑定字段名不存在，则用属性名
            let fn = fo.name?fo.name:key;
            if(v === null || v === undefined){
                if(!fo.nullable){
                    throw ErrorFactory.getError('0021',[key]);
                }
                v = null;
            }else if(fo.type === 'date' || fo.type === 'string'){
                v = "'" + v + "'";
            }
            fv.push(fn + '=' + v);
        }
        arr.push(fv.join(','));
        return arr.join(' ');
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
            return "";
        }
        let arr:string[] = [];
        arr.push('delete from');
        arr.push(orm.table);
        arr.push('where');

        let v = entity.getId();
        if(v === undefined || v === null){
            return "";
        }
        //字符串
        let field:IEntityColumn = orm.columns.get(orm.id.name);
        if(field.type === 'date' || field.type === 'string'){
            v = "'" + v + "'";
        }
        let fn = orm.table + "." + orm.id.name + " = " + v;
        arr.push(fn);
        return arr.join(' ');
    }

    /**
     * 转化entity string
     * @param entity    实体对象
     * @param paramMap  
     */
    public static toQueryString(entity:any,paramMap:Map<string,any>):string{
        let orm:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(!orm){
            throw ErrorFactory.getError("0010",[entity.constructor.name]);
        }
        let arr:string[] = [];
        arr.push('select')
        arr.push()
        return null;
    }


    public static entityToQuerySql(entity:any):string{
        let arr:string[] = [];
        arr.push('select');
        //选择字段
        let fields:string[] = [];
        //关联表
        let joinTbls:string[] = [];
        //关联字段
        let joinColumns:string[] = [];
        // genContent(entity,fields,joinTbls,joinColumns);
        
        arr.push(fields.join(','));
        arr.push('from');
        arr.push(joinTbls[0]);
        for(let i=1;i<joinTbls.length;i++){
            arr.push('left join');
            arr.push(joinTbls[i]);
            arr.push('on');
            arr.push(joinColumns[i-1]);
        }
        let sql = arr.join(' ');
        console.log(sql);
        return arr.join(' ');
    }


    /**
     * 获取sql 字符串
     * @param rql 
     */
    public static getQuerySql(rql:string){
        let srcArr = rql.split(/\s+/g);
        
        //select 位置
        let ind0:number = srcArr[0].toLowerCase() === 'select'?0:-1;
        let ind1:number = srcArr.findIndex(item=>item.toLowerCase()==='from');
        let ind2:number = srcArr.findIndex(item=>item.toLowerCase()==='where');
        if(ind2 === -1){
            ind2 = srcArr.findIndex(item=>item.toLowerCase()==='order');
        }
        
        //表关联数组
        let tableArr:string[] = srcArr.slice(ind1+1,ind2!==-1?ind2-ind1+1:srcArr.length);
        //字段关联数组
        let columnArr:string[] = srcArr.slice(ind0+1,ind1);
        //合并后再以‘,’分割
        columnArr = columnArr.join('').split(',');
        //主实体
        let mainEntityName:string = tableArr[0];

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
        
        // 隐含join main table 的tables
        let joinTbls:string[] = [];
        //实体联{实体名:[属性1,属性2,...]}
        let enRelMap:Map<string,string[]> = new Map();

        handleTable(tableArr);
        handleFields(columnArr);
        console.log(columnArr.join(','));
        console.log(columnArr);
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
                    }
                }else if(isField){ //
                    isField = false;
                    arr[i] = handleField(arr[i],3);
                    if(arr[i+1] !== '=' || i+2>=arr.length){
                        throw ErrorFactory.getError('0100');
                    }
                    arr[i+2] = handleField(arr[i+2],3);
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

        function handleFields(arr:string[]){
            let preArr:string[] = [','];
            for(let i=0;i<arr.length;i++){
                let ch = arr[i];
                for(let pa of preArr){
                    if(ch !== pa && ch.indexOf(pa) !== -1){  
                        handleChar(arr,i,pa);
                        continue;
                    }
                }
            }

            for(let i=0;i<arr.length;i++){
                let fn:string = arr[i];
                let ind1:number = fn.indexOf('(');
                if(ind1 !== -1){
                    //函数名
                    let foo:string = fn.substr(0,ind1).toLowerCase();
                    fn = fn.substring(ind1+1,fn.length-1);
                    arr[i] = foo + '(' + handleField(fn,2) + ')';
                }else{
                    arr[i] = handleField(fn,1);
                }
            }
        }
        /**
         * 处理字段
         * @param fieldStr          字段串 
         * @param entityStrategy    实体策略 如果字段最终为实体类型，采集的转化策略，1:获取实体所有属性 2:到最后entity id 3:到上一级entity id(作为条件时)
         */
        function handleField(fieldStr:string,entityStrategy?:number):string{
            //如a.area.areaId
            let fa:string[] = fieldStr.split('.');
            let tblObj:IEntityCfg;

            let alias:string = aliasMap.get(fa[0]);
            // 引用字段（带alias）
            let refName:string;
            //通过别名获取实体名
            let entityName:string = oldAliasEnMap.get(fa[0]);
            let preEntityName:string = entityName;
            if(!entityName){
                throw ErrorFactory.getError('0011',[fa[0]]);
            }
            tblObj = EntityFactory.getClass(entityName);
            if(!tblObj){
                throw ErrorFactory.getError('0010',[entityName]);
            }

            for(let i=1;i<fa.length;i++){
                let co:IEntityColumn = tblObj.columns.get(fa[i]);
                if(co.refName){  //外键
                    let rel = tblObj.relations.get(fa[i]);
                    refName = alias + '.' + co.refName;
                    if(rel){
                        //实体尚未存在table map中
                        if(!oldEnAliasMap.has(rel.entity)){
                            let al:string = 't' + aliasIndex++;
                            oldEnAliasMap.set(rel.entity,al);
                            oldAliasEnMap.set(al,rel.entity);
                            //加入主表 join 
                            joinTbls.push(rel.entity);
                        }
                        //只存前一级的实体名、实体对象、别名
                        entityName = rel.entity;
                        tblObj = EntityFactory.getClass(entityName);
                        alias = newEnAliasMap.get(rel.entity);
                        if(!tblObj){
                            throw ErrorFactory.getError('0010',[entityName]);
                        }
                    }
                }else{ //普通字段
                    return alias + '.' + co.name + ' as ' + alias + '_' + fa[i];
                }
            }
            //最终定位到实体或关联实体
            switch(entityStrategy){
                case 1://获取整个entity 属性及关联属性
                    let fa1 = [];
                    getEntityField(entityName,fa1);
                    return fa1.join(',');    
                case 2://定位到entity id
                    if(tblObj.id){
                        let co:IEntityColumn = tblObj.columns.get(tblObj.id.name);
                        return alias + '.' + co.name;
                    }
                    break;
                case 3: //定位到前一个实体或主实体的id
                    if(refName){
                        return refName;
                    }else if(tblObj.id){
                            let co:IEntityColumn = tblObj.columns.get(tblObj.id.name);
                            return alias + '.' + co.name;
                        }
                    }
                    break;
            }
        }

        /**
         * 处理 * 字段
         */
        function getEntityField(entityName:string,fieldArr:string[]){
            
            let to:IEntityCfg = EntityFactory.getClass(entityName);
            if(!to){
                throw ErrorFactory.getError('0010',[entityName]);
            }
            let orm:IEntityCfg = EntityFactory.getClass(entityName);
            if(!orm){
                throw ErrorFactory.getError('0010',[entityName]);
            }
            
            for(let fo of orm.columns){
                let propName = fo[0];
                let co:IEntityColumn = orm.columns.get(fo[0]);
                if(co.refName){ //处理关联字段
                    let rel:IEntityRelation = orm.relations.get(propName);
                    if(rel.eager){
                        //旧别名
                        let alias:string;
                        if(!oldEnAliasMap.has(rel.entity)){
                            //生成别名
                            alias = 't' + aliasIndex++;
                            oldAliasEnMap.set(alias,rel.entity);
                            oldEnAliasMap.set(rel.entity,alias);
                            if(!joinTbls.includes(rel.entity)){
                                joinTbls.push(rel.entity);
                            }
                        }
                        
                        getEntityField(rel.entity,fieldArr);
                    }
                }else{
                    let alias:string = newEnAliasMap.get(entityName);
                    //拼接字段
                    fieldArr.push(alias + '.' + co.name + ' as ' + alias + '_' + fo[0]);
                }
            }
        }
    }
}

export {Translator};