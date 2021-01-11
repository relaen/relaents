import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { ErrorFactory } from "./errorfactory";
import { RelaenThreadLocal } from "./threadlocal";
import { IConnectionCfg } from "./types";


/**
 * 连接管理器
 */
class ConnectionManager{
    /**
     * 配置
     */
    static options:any;

    /**
     * 连接池
     */
    private static pool:any;

    /**
     * 数据库 npm 模块
     */
    private static dbMdl:any;

    /**
     * 连接map {threadId:{num:conn创建次数,conn:连接}}
     * 保证一个异步方法中只能有一个connection
     */
    private static connectionMap:Map<number,any> = new Map();

    /**
     * 初始化连接管理器
     * @param cfg relaen配置文件的数据库配置对象
     */
    public static init(cfg:IConnectionCfg){
        switch(RelaenManager.dialect){
            case 'mysql':
                this.dbMdl = require('mysql');
                this.options = {
                    host:cfg.host,
                    port:cfg.port,
                    user:cfg.username,
                    password:cfg.password,
                    database:cfg.database
                };
                //连接池
                if(cfg.pool && cfg.pool.max){
                    this.options.connectionLimit = cfg.pool.max;
                    this.pool = this.dbMdl.createPool(this.options);
                }
                break;

            case 'oracle':
                this.dbMdl = require('oracledb');
                break;
            case 'mssql':
                this.dbMdl = require('mssql');
                break;
        }
    }

    /**
     * 获取连接对象
     * @param id   创建者id，直接使用时，不需要设置该值
     * @returns    connection对象  
     */
    public static async createConnection(id?:number):Promise<Connection>{
        let conn:Connection;
        //把conn加入connectionMap
        let sid:number = RelaenThreadLocal.getThreadId();

        if(!sid){ //新建conn
            sid = RelaenThreadLocal.newThreadId();
        }
        if(!this.connectionMap.has(sid)){ //线程id对应对象不存在
            switch(RelaenManager.dialect){
                case 'mysql':
                    conn = new Connection(await this.getMysqlConnection());
                    //记录创建者id
                    if(id){
                        conn.fromId = id;
                    }
                    conn.connected = true;
                    break;
                case 'oracle':
                    // conn = await this.getOracleConnection();
                    break;
                case 'mssql':
                    // conn = await this.getMssqlConnection();
                    break;
            }
            conn.threadId = sid;
            this.connectionMap.set(sid,{
                num:1,
                conn:conn
            });
        }else{ //已存在，则只修改conn的创建数，不新建conn
            let o = this.connectionMap.get(sid);
            o.num++;
            conn = o.conn;
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param connection    数据库连接对象
     * @param force         是否强制释放
     */
    public static async closeConnection(connection:Connection,force?:boolean){
        //获取threadId
        let sid:number = connection.threadId;
            
        //非强制释放，检查计数器
        if(!force){
            if(sid && this.connectionMap.has(sid)){
                let o = this.connectionMap.get(sid);
                if(--o.num <= 0){ //最后一个close，需要从map删除
                    force = true;       
                }
            }
        }
        
        //需要释放
        if(force){
            //清理 connection map
            this.connectionMap.delete(sid);
            switch(RelaenManager.dialect){
                case 'mysql':
                    return await this.closeMysqlConnection(connection);
                case 'oracledb':
                    break;
                case 'mssql':
                    break;
            }
        }
    }


    /**
     * 获取 mysql 连接
     */
    private static async getMysqlConnection():Promise<any>{
        if(this.pool){
            return new Promise((resolve,reject)=>{
                this.pool.getConnection((err,conn)=>{
                    if(err){
                        reject(err);
                    }
                    resolve(conn);
                });
            });
        }else{
            let conn = await this.dbMdl.createConnection(this.options);
            return conn;
        }
    }

    /**
     * 关闭mysql connection
     * @param connection 数据库连接对象
     */
    private static async closeMysqlConnection(connection:Connection){
        if(this.pool){
            connection.conn.release();
            return null;
        }else{
            return new Promise((res,rej)=>{
                connection.conn.end(err=>{
                    if(err){
                        rej(ErrorFactory.getError('0201',[err]));
                    }
                    res(null);
                });
            });
        }
    }

    private static async getOracleConnection(){

    }

    private static async getMssqlConnection(){

    }
}

/**
 * 获取连接对象
 * @param id   创建者id，直接使用时，不需要设置该值
 * @returns    connection对象  
 */
async function getConnection(id?:number):Promise<Connection>{
    return await ConnectionManager.createConnection(id);
}

export{ConnectionManager,getConnection}