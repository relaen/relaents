import { EntityManager } from "./entitymanager";
import { SqlExecutor } from "./sqlexecutor";
import { EntityColumnOption, IEntity, EEntityState } from "./types";
import { EntityFactory } from "./entityfactory";
import { Query } from "./query";
import { EntityManagerFactory } from "./entitymanagerfactory";
import { BaseEntity } from "./baseentity";
import { EntityConfig } from "./entityconfig";

/**
 * 原生查询
 */
class NativeQuery extends Query {
    /**
     * 构造原生查询对象
     * @param sql -               sql语句 
     * @param em -                entity manager
     * @param entityClassName -   实体类名
     */
    constructor(sql: string, em: EntityManager, entityClassName?: string) {
        super(em, entityClassName);
        this.execSql = sql;
    }

    /**
     * 设置查询参数值，占位符支持下标和字符串
     * @param index -     占位符
     * @param value -     参数值
     */
    public setParameter(index: number | string, value: unknown) {
        if (!this.paramArr) {
            this.paramArr = typeof index === 'number' ? [] : {}
        }

        //补全参数个数
        if (Array.isArray(this.paramArr) && this.paramArr.length <= <number>index) {
            for (let i = this.paramArr.length; i <= <number>index; i++) {
                this.paramArr.push(null);
            }
        }
        //对于entity，只获取其主键
        this.paramArr[index] = value instanceof BaseEntity ? EntityFactory.getIdValue(value) : value;
    }

    /**
     * 设置多个参数值，数组从下标0开始|对象以属性名绑定
     * @param valueArr - 值数组|值对象
     */
    public setParameters(valueArr: unknown[] | object) {
        if (Array.isArray(valueArr)) {
            this.paramArr = this.paramArr || [];
            valueArr.forEach((value, i) => {
                //对于entity，只获取其主键
                const v = value instanceof BaseEntity ? EntityFactory.getIdValue(value) : value;
                if (Array.isArray(this.paramArr) && i >= this.paramArr.length) {
                    this.paramArr.push(v);
                } else {
                    this.paramArr[i] = v;
                }
            });
        } else {
            this.paramArr = this.paramArr || {};
            Object.getOwnPropertyNames(valueArr).forEach((item) => {
                const value = valueArr[item];
                //对于entity，只获取其主键
                const v = value instanceof BaseEntity ? EntityFactory.getIdValue(value) : value;
                this.paramArr[item] = v;
            });
        }
    }

    /**
     * 获取单个实体或单个属性值
     * @returns     结果
     */
    public async getResult(): Promise<unknown> {
        if (!this.execSql) {
            return null;
        }
        const isSelect: boolean = this.execSql.trim().substr(0, 6).toLowerCase() === 'select' ? true : false;
        //为查询执行
        if (isSelect) {
            const results = <unknown[]>await this.getResultList(0, 1);
            if (results && results.length > 0) {
                const props = Object.getOwnPropertyNames(results[0]);
                //如果只有一个属性，则只返回属性值
                if (props.length === 1) {
                    return results[0][props[0]];
                }
                return results[0];
            }
            return null;
        }
        //执行原生
        return await SqlExecutor.exec(this.entityManager, this.execSql, this.paramArr);
    }

    /**
     * 获取结果列表
     * @param start -   开始索引
     * @param limit -   记录数
     * @returns         结果集
     */
    public async getResultList(start?: number, limit?: number): Promise<unknown> {
        this.setStart(start);
        this.setLimit(limit);
        const results = await SqlExecutor.exec(this.entityManager, this.execSql, this.paramArr, this.start, this.limit);
        if (results && Array.isArray(results)) {
            const arr = [];
            for (const r of results) {
                arr.push(this.genOne(r));
            }
            return arr;
        }
        return results;
    }

    /**
     * 根据查询结果生成单个数据对象
     * @param r -     原生查询结果
     * @returns     转化实体对象
     */
    private genOne(r: unknown) {
        if (this.entityClassName) {
            const ecfg: EntityConfig = EntityFactory.getEntityConfig(this.entityClassName);
            if (ecfg) {  //具备该实体类，则处理为实体
                //外键map
                const entity: IEntity = new ecfg.entity();
                for (const col of ecfg.columns) {
                    const c: EntityColumnOption = col[1];
                    //该字段无值或是外键
                    if (r[c.name] === null || r[c.name] === undefined || c.refName) {
                        continue;
                    }
                    entity[col[0]] = r[c.name];
                }
                //设置状态
                EntityManagerFactory.setEntityStatus(entity, EEntityState.PERSIST);
                return entity;
            }
        }
        const obj = {};
        Object.getOwnPropertyNames(r).forEach(item => {
            obj[item] = r[item];
        });
        return obj;
    }

}

export { NativeQuery }