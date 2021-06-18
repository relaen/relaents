import { Connection } from "./connection";
import { EntityManager } from "./entitymanager";
import { IConnectionCfg } from "./types";

/**
 * 数据库驱动器接口
 * 提供与dialect相关的操作，不同dialect需要实现此接口
 * @since 0.3.0
 */
export abstract class BaseProvider {
    /**
     * 配置
     */
    protected options: any;

    /**
     * 连接池
     */
    protected pool: any;

    /**
     * 数据库 npm 模块
     */
    protected dbMdl: any;

    constructor(cfg:IConnectionCfg){
        
    }
    /**
     * 获取连接
     */
    public async getConnection():Promise<any>{
        return null;
    }

    /**
     * 关闭连接
     * @param connection    数据库连接对象
     */
    public async closeConnection(connection: Connection){

    }

    /**
     * 执行postgres sql语句
     * @param connection    数据库连接
     * @param sql           sql语句
     * @param params        参数
     */
    public async exec(connection: Connection, sql: string, params?: any[]):Promise<any>{
        return null;
    }

    /**
     * 处理记录起始记录索引和记录数
     * @param sql       sql
     * @param start     开始索引
     * @param limit     记录数
     * @returns         处理后的sql
     */
    public handleStartAndLimit(sql: string, start?: number, limit?: number):string{
        return null;
    }

    /**
     * 获取实体sequence，针对主键生成策略为sequence时有效
     * @param em        entity manager
     * @param seqName   sequence name
     * @param schema    schema
     * @returns         sequence 值
     */
    getSequenceValue(em:EntityManager,seqName:string,schema?:string):Promise<number>{
        return null;
    }

    /**
     * 从sql执行结果获取identityid，仅对主键生成策略是identity的有效
     * @param result    sql执行结果
     * @returns         主键
     */
    public getIdentityId(result:any): number{
        return null;
    }
}