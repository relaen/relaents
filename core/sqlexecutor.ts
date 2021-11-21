import { Logger } from "./logger";
import { EntityManager } from "./entitymanager";
import { RelaenUtil } from "./relaenutil";
import { ConnectionManager } from "./connectionmanager";

/**
 * sql执行器
 */
export class SqlExecutor {
    /**
     * 执行mysql sql语句
     * @param em            EntityManager
     * @param sql           待执行sql
     * @param params        参数数组
     * @param start         开始记录行
     * @param limit         最大记录行
     * @returns             执行结果或undefined
     */
    public static async exec(em: EntityManager, sql: string, params?: any[] | object, start?: number, limit?: number): Promise<any> {
        if (!sql) {
            return null;
        }
        sql = sql.trim();
        //sql类型：0:查询 1:增删改
        let sqlType: number = ['insert', 'update', 'delete'].includes(sql.substr(0, 6).toLowerCase()) ? 1 : 0;

        //缓存key，构建方式：sql_paramsvaluestring
        let key: string;
        //结果
        let result: any;
        if (sqlType === 0) {  //查询可从缓存中获取
            //sql语句末加行锁，分页加在行锁语句前（待优化加锁分页执行顺序）
            if (sql.substr(-10).toLowerCase() === 'for update') {
                sql = ConnectionManager.provider.handleStartAndLimit(sql.substr(0, sql.length - 11), start, limit) + ' FOR UPDATE';
            } else {
                sql = ConnectionManager.provider.handleStartAndLimit(sql, start, limit);
            }

            key = sql;
            //构造缓存key
            if (params) {
                key += '_' + JSON.stringify(params);
            }
            //从缓存获取
            result = em.getFromCache(key);
            //缓存中存在，则直接返回
            if (result) {
                return result;
            }
        }
        //处理占位符
        sql = RelaenUtil.handlePlaceholder(sql);
        //打印sql
        Logger.log(sql, params);
        try {
            result = await ConnectionManager.provider.exec(em.connection, sql, params);
            //执行增删改，则清空cache
            if (sqlType === 1) {
                em.clearCache();
            } else {  //添加到缓存
                em.addToCache(key, result);
            }
        } catch (e) {
            Logger.error(e);
            throw ("[Relaen execute sql] Error:\"" + e.message + "\"");
        }
        // Logger.log("[Relaen execute sql]:\"OK\"");
        Logger.log("OK");
        return result;
    }
}