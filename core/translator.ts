import {EntityManager} from "./entitymanager";
import { IEntityCfg, IEntityRelation, ERelationType, IEntityColumn } from "./entitydefine";
import { BaseEntity } from "./entity";
import { EntityFactory } from "./entityfactory";

/**
 * 翻译器
 */
class Translator{
    /**
     * entity转insert sql
     * @param entity 
     */
    public static entityToInsert(entity:any):string{
        let orm:IEntityCfg = EntityFactory.getClass(entity.prototype.name);
        if(!orm){
            throw "实体类不存在";
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
        let orm:IEntityCfg = EntityFactory.getClass(entity.prototype.name);
        if(!orm){
            throw "实体类不存在";
        }
        let arr:string[] = [];
        arr.push('update');
        arr.push(orm.table);
        arr.push('set');
        
        let fv:string[] = [];
        for(let key of orm.columns){
            let fo:any = key[1];
            let v = entity[key[0]];
            //值不存在，则下一个
            if(v === undefined){
                continue;
            }

            //如果绑定字段名不存在，则用属性名
            let fn = fo.name?fo.name:key;
            //值
            if(v !== null && (fo.type === 'date' || fo.type === 'string')){
                v("'" + v + "'");
            }
            fv.push(fn + '=' + v);
        }
        arr.push(fv.join(','));
        return arr.join(' ');
    }

    

    /**
     * entity转update sql
     * @param entity 
     */
    public static entityToDelete(entity:any):string{
        let orm:IEntityCfg = EntityFactory.getClass(entity.prototype.name);
        if(!orm){
            throw "实体类不存在";
        }
        let arr:string[] = [];
        arr.push('delete from');
        arr.push(orm.table);
        arr.push('where');
        let fv:string[] = [];
        for(let id of orm.ids){
            let fo = orm.columns.get(id[0]);
            if(!fo){
                continue;
            }

            let v = entity[id[0]];
            //值不存在，则下一个
            if(v === undefined){
                continue;
            }

            //如果绑定字段名不存在，则用属性名
            let fn = orm.table + '.' + fo.name?fo.name:id[0];
            //值
            if(v !== null && (fo.type === 'date' || fo.type === 'string')){
                v("'" + v + "'");
            }
            fv.push(fn + '=' + v);
            
        }
        arr.push(fv.join(' and '));
        return arr.join(' ');
    }

    /**
     * 转化entity string
     * @param entity    实体对象
     * @param paramMap  
     */
    public static toQueryString(entity:any,paramMap:Map<string,any>):string{
        let orm:IEntityCfg = EntityFactory.getClass(entity.prototype.name);
        if(!orm){
            throw "实体类不存在";
        }
        let arr:string[] = [];
        arr.push('select ')
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
        genContent(entity,fields,joinTbls,joinColumns);
        
        arr.push(fields.join(','));
        arr.push('from');
        arr.push(joinTbls[0]);
        for(let i=1;i<joinTbls.length;i++){
            arr.push('left join');
            arr.push(joinTbls[i]);
            arr.push('on');
            arr.push(joinColumns[i-1]);
        }
        return arr.join(' ');

        /**
         * 生成 字段，join表，join字段
         * @param entity 
         * @param fields 
         * @param joinTbls 
         * @param joinColumns 
         */
        function genContent(entity:any,fields:string[],joinTbls:string[],joinColumns:string[]){
            let orm:IEntityCfg = EntityFactory.getClass(entity.prototype.name);
            if(!orm){
                throw "找不到实体！";
            }
            let table = orm.table;
            joinTbls.push(table);

            for(let fo of orm.columns){
                let propName = fo[0];
                let field:IEntityColumn = orm.columns.get(fo[0]);
                fields.push(table + '.' + field.name);
                
                //处理关联字段
                if(orm.relations.has(propName)){
                    let rel:IEntityRelation = orm.relations.get(propName);
                    if(!rel.lazyFetch){
                        let entity1:IEntityCfg = EntityFactory.getClass(rel.entity.prototype.name);
                        if(rel.type === ERelationType.OneToOne || rel.type === ERelationType.ManyToOne){
                            genContent(entity1,fields,joinTbls,joinColumns);
                            joinTbls.push(entity1.table);
                            joinColumns.push(orm.table + '.' + field.name + '=' + entity1.table + '.' + field.refName);
                        }else if(rel.type === ERelationType.OneToMany){ // one to many

                        }else{ //many to many

                        }
                    }
                }
            }
        }
    }



}

export {Translator};