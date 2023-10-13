import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { RelaenThreadLocal } from "./threadlocal";
import { ConnectionOption, UnknownClass } from "./types";
import { BaseProvider } from "./baseprovider";
import { ProviderFactory } from "./providerfactory";
import { RelaenError } from "./message/error";
import { EntityManagerFactory } from "./entitymanagerfactory";

/**
 * 连接管理器
 */
class ConnectionManager {
    /**
     * 数据库驱动器
     */
    static provider: BaseProvider;

    /**
     * 连接map 
     * @remarks
     * 用于保证一个异步方法中只能有一个connection
     * ```
     * {
     *      threadId1:connection1
     *  }
     * }
     * ```
     */
    private static connectionMap: Map<number, Connection> = new Map();

    /**
     * 初始化连接管理器
     * @param cfg - relaen配置文件的数据库配置对象
     */
    public static init(cfg: ConnectionOption) {
        const providerClass = ProviderFactory.get();
        if (!providerClass) {
            throw new RelaenError("0300", RelaenManager.dialect);
        }
        this.provider = Reflect.construct(<UnknownClass>providerClass, [cfg]);
    }

    /**
     * 获取连接对象
     * @param id -   创建者id，直接使用时，不需要设置该值
     * @returns    connection对象
     */
    public static async createConnection(id?: number): Promise<Connection> {
        let conn: Connection;
        //把conn加入connectionMap
        let sid: number = RelaenThreadLocal.getThreadId();
        if (!sid) { //新建conn
            sid = RelaenThreadLocal.newThreadId();
        }
        if (!this.connectionMap.has(sid)) { //线程id对应对象不存在
            conn = new Connection(await this.provider.getConnection());
            if (id) {
                conn.fromId = id;
            }
            conn.connected = true;
            conn.threadId = sid;
            //加入connection map 
            this.connectionMap.set(sid, conn);
        } else { //已存在，则只修改conn的创建数，不新建conn
            conn = this.connectionMap.get(sid);
            conn.useCount++;
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param connection -    数据库连接对象
     * @param force -         是否强制释放
     */
    public static async closeConnection(connection: Connection, force?: boolean): Promise<void> {
        //获取threadId
        const sid: number = connection.threadId;
        //非强制释放，检查计数器
        if (!force) {
            if (sid && this.connectionMap.has(sid)) {
                const conn: Connection = this.connectionMap.get(sid);
                if (--conn.useCount <= 0) { //最后一个close，需要从map删除
                    force = true;
                }
            }
        }

        //需要释放
        if (force) {
            //清理 connection map
            this.connectionMap.delete(sid);
            //关闭关联entitymanager
            if(connection.fromId){
                const em = EntityManagerFactory.getEntityManager(connection.fromId);
                if(em){
                    EntityManagerFactory.closeEntityManager(em,true);
                } 
            }
            return await this.provider.closeConnection(connection);
        }
    }

    /**
     * 添加conn到map
     * @param threadId -      线程id 
     * @param conn -          连接对象
     */
    public static addConnection(threadId: number, conn: Connection) {
        this.connectionMap.set(threadId, conn);
    }

    /**
     * 从map移除connection
     * @param threadId -      线程id
     */
    public static removeConnection(threadId: number) {
        this.connectionMap.delete(threadId);
    }
}

/**
 * 获取连接对象
 * @param id -   创建者id，直接使用时，不需要设置该值
 * @returns    connection对象
 */
async function getConnection(id?: number): Promise<Connection> {
    return await ConnectionManager.createConnection(id);
}

export { ConnectionManager, getConnection }