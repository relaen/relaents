import { RelaenManager } from "./relaenmanager";
import { Connection } from "./connection";
import { ErrorFactory } from "./errorfactory";
import { ThreadStorage } from "./threadlocal";

/**
 * 连接池配置
 */
interface IConnectionPool{
    max:number;
    min:number;
    acquire:number;
    idle:number;
}

/**
 * 连接配置
 */
interface IConnectionCfg{
    dialect:string;
    host:string;
    port:number;
    username:string;
    password:string;
    database:string;
    pool:IConnectionPool;
    cache:boolean;
    debug:boolean;
    entities:Array<string>
}

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
     * 获取连接对象
     * @returns     连接对象，已连接
     * 
     */
    public static async getConnection():Promise<Connection>{
        let conn:Connection;
        //把conn加入connectionMap
        let sid:number = ThreadStorage.getStore();

        if(!sid){ //新建conn
            sid = ThreadStorage.newStorage();
        }
        if(!this.connectionMap.has(sid)){ //线程id对应对象不存在
            switch(RelaenManager.dialect){
                case 'mysql':
                    conn = new Connection(await this.getMysqlConnection());
                    conn.connected = true;
                    break;
                case 'oracle':
                    // conn = await this.getOracleConnection();
                    break;
                case 'mssql':
                    // conn = await this.getMssqlConnection();
                    break;
            }
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
     * 关闭连接
     * @param connection 数据库连接对象
     */
    public static async closeConnection(connection:Connection){
        switch(RelaenManager.dialect){
            case 'mysql':
                return this.closeMysqlConnection(connection);
            case 'oracledb':
                break;
            case 'mssql':
                break;
        }
        //清理 connection map
        //获取threadId
        let sid:number = ThreadStorage.getStore();
        if(sid && this.connectionMap.has(sid)){
            let o = this.connectionMap.get(sid);
            if(--o.num <= 0){ //最后一个close，需要从map删除
                this.connectionMap.delete(sid);
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
            return new Promise((res,rej)=>{
                connection.conn.release(err=>{
                    if(err){
                        rej(ErrorFactory.getError('0201',[err]));
                    }
                    res(null);
                })
            });
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

async function getConnection():Promise<Connection>{
    return await ConnectionManager.getConnection();
}

export{ConnectionManager,getConnection,IConnectionCfg,IConnectionPool}