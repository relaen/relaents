import {EntityManager} from "./entitymanager";
import { IEntityCfg } from "./entitydefine";

/**
 * 翻译器
 */
class Translator{
    
    /**
     * entity转insert sql
     * @param entity 
     */
    static entityToInsert(entity:any):string{
        let orm:IEntityCfg = EntityManager.getClass(entity.prototype.name);
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
            fields.push(fo.name?fo.name:key);
            
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
    static entityToUpdate(entity:any):string{
        let orm:IEntityCfg = EntityManager.getClass(entity.prototype.name);
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
    static entityToDelete(entity:any):string{
        let orm:IEntityCfg = EntityManager.getClass(entity.prototype.name);
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
            let fn = fo.name?fo.name:id[0];
            //值
            if(v !== null && (fo.type === 'date' || fo.type === 'string')){
                v("'" + v + "'");
            }
            fv.push(fn + '=' + v);
            
        }
        arr.push(fv.join(' and '));
        return arr.join(' ');
    }


}