import { EntityFactory } from "../../entityfactory";
import { ErrorFactory } from "../../errorfactory";
import { RelaenManager } from "../../relaenmanager";
import { Translator } from "../../translator";
import { EQueryType, EntityColumnOption } from "../../types";

/**
 * mssql 翻译器
 */
export class OracleTranslator extends Translator {
    /**
     * 产生查询sql
     * @returns     数组[sql,linkMap,values]
     *              其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    public getQuerySql(): [string,unknown,unknown[]] {
        switch (this.sqlType) {
            case EQueryType.SELECT:
                return this.getSelectSql();
            case EQueryType.DELETE:
                return this.getDeleteSql(true);
        }
    }

    /**
     * 生成增删改sql
     * @param notNeedAlias -      不需要别名
     * @returns 数组[sql,linkMap,values]
     *          其中：linkMap为该translator的linkNameMap，values为查询参数值
     */
    protected getDeleteSql(notNeedAlias?: boolean):[string,unknown,unknown[]] {
        if (!this.whereObject && !RelaenManager.fullTableOperation) {
            throw ErrorFactory.getError("0120");
        }
        const arr: string[] = [];
        arr.push('DELETE ' + (notNeedAlias ? '' : 't0 ') + ' FROM ' + this.mainEntityCfg.getTableName(true) + ' t0');

        //处理主表和join表
        const joinArr: string[] = [];
        for (const o of this.linkNameMap) {
            if (!o[1]['from']) {
                continue;
            }

            const orm = EntityFactory.getEntityConfig(o[1]['entity']);
            const al1: string = o[1]['alias'];
            const al2: string = this.linkNameMap.get(o[1]['from'])['alias'];
            const co: EntityColumnOption = o[1]['co'];
            joinArr.push('LEFT JOIN ' + orm.getTableName(true) + ' ' + al1 + ' on ' + al2 + '.' + co.name + '=' + al1 + '.' + co.refName);
        }

        //存在关联关系
        if (joinArr.length > 0) {
            arr.push('WHERE EXISTS (SELECT ' + this.mainEntityCfg.getIdName() + ' FROM ' + this.mainEntityCfg.getTableName(true) + joinArr.join(',') + ' WHERE ' + this.whereObject[0] + ')');
        } else {
            arr.push('WHERE ' + this.whereObject[0])
        }

        return [arr.join(' '), this.linkNameMap, this.whereObject ? this.whereObject[1] : undefined];
    }
}
