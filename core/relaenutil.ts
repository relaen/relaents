import { IEntityCfg, IEntity } from "./types";
import { EntityFactory } from "./entityfactory";
import { BaseEntity } from "./baseentity";
import { RelaenManager } from "./relaenmanager";
import { PlaceholderFactory } from "./placeholderfactory";


class RelaenUtil {
    /**
     * 对象id计数器
     */
    private static idGenerator = 0;

    /**
     * 生成对象id，提供 entitymanager 和connection使用
     */
    public static genId() {
        return ++this.idGenerator;
    }

    /**
     * 获取table名
     * @param cfg   实体配置
     * @returns     表名串，如果有schema，则加上schema
     */
    public static getTableName(cfg: IEntityCfg | string, schema?: string) {
        if (typeof cfg === 'object') {
            return cfg.schema ? cfg.schema + '.' + cfg.table : cfg.table;
        }
        return schema ? schema + '.' + cfg : cfg;
    }

    /**
     * 获取id名 
     * @param entity 实体类名|实体对象|实体配置
     * @returns      实体id名
     */
    public static getIdName(entity: string | BaseEntity | IEntityCfg): string {
        let cfg: IEntityCfg;
        if (typeof entity === 'string') {
            cfg = EntityFactory.getClass(entity);
        } else if (typeof entity === 'object') {
            if (entity instanceof BaseEntity) {
                cfg = EntityFactory.getClass(entity.constructor.name);
            } else {
                cfg = entity;
            }
        }
        return cfg && cfg.id ? cfg.id.name : null;
    }

    /**
     * 设置属性值
     * @param entity    实体对象
     * @param value     实体值
     */
    public static setIdValue(entity: IEntity, value: any) {
        let cfg: IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if (cfg && cfg.id && cfg.id.name) {
            entity[cfg.id.name] = value;
        }
    }

    /**
     * 获取id值
     * @param entity    实体对象
     */
    public static getIdValue(entity: IEntity): any {
        let cfg: IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if (cfg && cfg.id) {
            return entity[cfg.id.name];
        }
    }

    /**
     * 处理字段字符串值
     * @param value     待处理值
     * @returns         处理后的字符串
     */
    public static valueToString(value: any): string {
        if (typeof value !== 'string') {
            value = value + '';
        }
        //替换 ' 为 \'
        value = value.replace(/'/g, "\'");
        //两端添加 '
        return "'" + value + "'";
    }

    /**
     * 处理参数占位符
     * @param sql   sql串
     * @returns     修改后的sql
     */
    public static handlePlaceholder(sql: string): string {
        // 占位符为?的默认处理
        if (['mysql', 'mariadb', 'sqlite'].includes(RelaenManager.dialect)) {
            return sql;
        }
        let reg = /(\'.*?\?.*?\')|\?/g;
        let index = 0;

        return sql.replace(reg, (match, p1) => {
            if (match !== '?') {
                return p1;
            }
            return PlaceholderFactory.get(index++);
        });
    }

}

export { RelaenUtil }