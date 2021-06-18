import { BaseEntity } from "../../baseentity";
import { EntityFactory } from "../../entityfactory";
import { ErrorFactory } from "../../errorfactory";
import { RelaenUtil } from "../../relaenutil";
import { Translator } from "../../translator";
import { IEntity, IEntityCfg } from "../../types";

/**
 * mssql 翻译器
 * @since 0.3.0
 */
export class MssqlTranslator extends Translator{
    /**
     * entity转insert sql
     * @param entity 
     */
    public entityToInsert(entity: any): any[] {
        return super.entityToInsert(entity,'SELECT @@IDENTITY AS insertId');
    }

    /**
     * entity转update sql
     * @param entity                待更新entity
     * @param ignoreUndefinedValue  忽略undefined值
     */
     public entityToUpdate(entity: IEntity, ignoreUndefinedValue?: boolean): any[] {
        let orm: IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if (!orm) {
            throw ErrorFactory.getError("0010", [entity.constructor.name]);
        }

        let arr: string[] = [];
        arr.push('update');
        arr.push(RelaenUtil.getTableName(orm));
        arr.push('set');
        let fv: string[] = [];
        //id值
        let idValue: any;
        //id名
        let idName: string;
        if (!orm.id) {
            throw ErrorFactory.getError('0103');
        }
        let fields: string[] = [];
        let values: any[] = [];
        for (let key of orm.columns) {
            let fo: any = key[1];
            //如果绑定字段名不存在，则用属性名
            let fn = fo.name ? fo.name : key[0];

            //保存已添加字段，不重复添加
            if (fields.includes(fn)) {
                continue;
            }

            //字段值
            let v;
            if (fo.refName) { //外键，只取主键
                let refEn:IEntity = entity[key[0]];
                v = refEn && refEn instanceof BaseEntity ? RelaenUtil.getIdValue(refEn) : null;
            } else {
                v = entity[key[0]];
            }
            
            if (key[0] === orm.id.name) {
                idValue = v;
                idName = key[1].name;
            }
            
            //值为空且不忽略空值或字段已添加，设置主键自增时，不修改主键
            if (v === null && ignoreUndefinedValue || fields.includes(fn) || fo.identity === true) {
                continue;
            }

            fv.push(fn + '=?');
            fields.push(fn);
            values.push(v);
        }
        if (!idValue) {
            throw ErrorFactory.getError('0021', [orm.id.name]);
        }
        fv.forEach((v, i) => {
            fv[i] = v.slice(0, -1) + i
        });
    
        arr.push(fv.join(','));
        //where
        arr.push('where');
        arr.push(idName + '=' + idValue);
        let sql = arr.join(' ');
        return [sql, values];
    }
}
