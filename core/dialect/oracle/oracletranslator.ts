import { join } from "path/posix";
import { EntityFactory } from "../../entityfactory";
import { RelaenManager } from "../../relaenmanager";
import { RelaenUtil } from "../../relaenutil";
import { Translator } from "../../translator";
import { EQueryType, IEntityColumn } from "../../types";

/**
 * mssql 翻译器
 */
export class OracleTranslator extends Translator {
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
                return this.getDeleteSql(true);
        }
    }

    /**
     * 生成增删改sql
     * @param notNeedAlias      不需要别名
     * @returns 数组[sql,linkMap,values]
     *          其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    protected getDeleteSql(notNeedAlias?: boolean) {
        if (!this.whereObject && !RelaenManager.fullTableOperation) {
            throw "缺少where条件";
        }
        let arr: string[] = [];
        arr.push('DELETE ' + (notNeedAlias ? '' : 't0 ') + ' FROM ' + RelaenUtil.getTableName(this.mainEntityCfg) + ' t0');

        //处理主表和join表
        let joinArr: string[] = [];
        for (let o of this.linkNameMap) {
            if (!o[1]['from']) {
                continue;
            }

            let orm = EntityFactory.getClass(o[1]['entity']);
            let al1: string = o[1]['alias'];
            let al2: string = this.linkNameMap.get(o[1]['from'])['alias'];
            let co: IEntityColumn = o[1]['co'];
            joinArr.push('LEFT JOIN ' + RelaenUtil.getTableName(orm) + ' ' + al1 + ' on ' + al2 + '.' + co.name + '=' + al1 + '.' + co.refName);
        }

        //存在关联关系
        if (joinArr.length > 0) {
            arr.push('WHERE EXISTS (SELECT ' + RelaenUtil.getIdName(this.mainEntityCfg) + ' FROM ' + RelaenUtil.getTableName(this.mainEntityCfg) + joinArr.join(',') + ' WHERE ' + this.whereObject[0] + ')');
        } else {
            arr.push('WHERE ' + this.whereObject[0])
        }

        return [arr.join(' '), this.linkNameMap, this.whereObject ? this.whereObject[1] : undefined];
    }
}
