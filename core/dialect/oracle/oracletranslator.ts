import { Translator } from "../../translator";
import { EQueryType } from "../../types";

/**
 * mssql 翻译器
 */
export class OracleTranslator extends Translator{
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
}
