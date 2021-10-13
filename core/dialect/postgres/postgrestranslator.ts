import { Translator } from "../../translator";
import { EQueryType, IEntityCfg } from "../../types";

/**
 * postgres 翻译器
 * @since 0.3.0
 */
export class PostgresTranslator extends Translator{
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
     * entity转insert sql
     * @param entity 
     */
    //  public entityToInsert(entity: any): any[] {
        // let orm: IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        // if (!orm) {
        //     throw ErrorFactory.getError("0010", [entity.constructor.name]);
        // }
        // return super.entityToInsert(entity,'RETURNING ' + orm.columns.get(orm.id.name).name);
    // }
}