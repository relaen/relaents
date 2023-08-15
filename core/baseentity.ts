import { EntityFactory } from "./entityfactory";
import { IEntity, EEntityState } from "./types";
import { getEntityManager, EntityManagerFactory } from "./entitymanagerfactory";
import { EntityConfig } from "./entityconfig";

/**
 * 实体基类
 */
export class BaseEntity implements IEntity {
    
    /**
     * 构造函数
     */
    constructor() {
        //设置新建状态
        EntityManagerFactory.setEntityStatus(this, EEntityState.NEW);
    }

    /**
     * 保存实体
     * @param ignoreUndefinedValue -  忽略undefined值，针对update时有效
     * @param lockMode -              锁模式
     * @returns                     保存后的实体
     */
    public async save(ignoreUndefinedValue?: boolean): Promise<IEntity> {
        const em = await getEntityManager();
        await em.save(this, ignoreUndefinedValue);
        await em.close();
        return this;
    }

    /**
     * 删除实体
     * @returns     删除实体
     */
    public async delete(): Promise<IEntity> {
        const em = await getEntityManager();
        await em.delete(this);
        await em.close();
        return this;
    }

    /**
     * 根据id查询单个实体
     * @param id -    实体主键
     * @returns     查询实体
     */
    public static async find(id: unknown): Promise<IEntity> {
        const em = await getEntityManager();
        const entity = await em.find(this.name, id);
        await em.close();
        return entity;
    }

    /**
     * 根据条件查询单个实体
     * @param params -    参数对象，参考EntityManager.findOne方法说明
     * @returns         实体
     */
    public static async findOne(params?: object): Promise<IEntity> {
        const em = await getEntityManager();
        const entity = await em.findOne(this.name, params);
        await em.close();
        return <IEntity>entity;
    }

    /**
     * 根据条件查找多个对象
     * @param params -    参数对象，参考EntityManager.findOne方法说明
     * @param start -     开始记录行
     * @param limit -     获取记录数
     * @param order -     排序规则`{paramName1:'desc',paramName2:'asc',...}`， paramName1:参数名,desc:降序 asc:升序
     * @returns         实体集
     */
    public static async findMany(params?: object, start?: number, limit?: number, order?: object): Promise<IEntity[]> {
        const em = await getEntityManager();
        const list = await em.findMany(this.name, params, start, limit, order);
        await em.close();
        return <IEntity[]>list;
    }

    /**
     * 获取记录数
     * @param params -  参数对象，参考EntityManager.findOne
     * @returns         记录数
     */
    public static async getCount(params?: object): Promise<number> {
        const em = await getEntityManager();
        const count = await em.getCount(this.name, params);
        await em.close();
        return count;
    }

    /**
     * 删除对象
     * @param id -    实体id值
     * @returns     删除的实体
     */
    public static async delete(id: unknown): Promise<boolean> {
        const em = await getEntityManager();
        await em.delete(id, this.name);
        await em.close();
        return true;
    }

    /**
     * 删除对象
     * @param params -    参数对象，参考EntityManager.findOne
     * @returns         删除成功返回true
     */
    public static async deleteMany(params?: object): Promise<boolean> {
        const em = await getEntityManager();
        await em.deleteMany(this.name, params);
        await em.close();
        return true;
    }

    /**
     * 对比
     * @param obj -   简化后的实体值对象
     * @returns     如果相同，则返回true，否则返回false
     */
    public compare(obj: object): boolean {
        const ecfg: EntityConfig = EntityFactory.getEntityConfig(this.constructor.name);
        for (const col of ecfg.columns) {
            //字段对象
            const fo = col[1];
            //字段名
            const fn = col[0];
            if (!fo.refName && obj[fn] !== this[fn] ||
                obj[fn] !== EntityFactory.getIdValue(this)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 浅拷贝，外键对象只拷贝主键值
     */
    public clone(): object {
        const obj = new Object();
        const ecfg: EntityConfig = EntityFactory.getEntityConfig(this.constructor.name);
        for (const col of ecfg.columns) {
            if (this[col[0]] === undefined) {
                continue;
            }
            //字段对象
            const fo = col[1];
            if (fo.refName) { //外键只取id
                if (this[col[0]] !== null) {
                    obj[col[0]] = EntityFactory.getIdValue(this[col[0]]);
                }
            } else {
                //null也需要保留
                obj[col[0]] = this[col[0]];
            }
        }
        //保留className
        obj['__entityClassName'] = this.constructor.name;
        return obj;
    }
}
