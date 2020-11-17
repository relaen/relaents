import ConnectionManager from "./connectionmanager";
import { EntityFactory } from "./entityfactory";

/**
 * relaen 框架管理器
 */
class RelaenManager{
    /**
     * 应用名，必须设置
     */
    public static appName:string;

    /**
     * 数据库类型
     * mysql oracle mssql
     */
    public static dialect:string;

    /**
     * 初始化
     * @param config 
     */
    static init(path:string){
        let cfg = this.parseFile(path);
        this.dialect = cfg.dialect || 'mysql';
        switch(this.dialect){
            case "oracle":

                break;
            case "mssql":
            
                break;
            default: //mysql
                ConnectionManager.options = {
                    host:cfg.host,
                    port:cfg.port,
                    user:cfg.username,
                    password:cfg.password,
                    database:cfg.database
                };
                //连接池
                if(cfg.pool && cfg.pool.max){
                    ConnectionManager.options.connectionLimit = cfg.pool.max;
                }
        }
        //加载实体
        for(let path of cfg.entities){
            EntityFactory.addEntities(path);
        }
    }
    /**
     * @exclude
     * 解析实例配置文件
     * @param path      文件路径
     */
    static parseFile(path:string):any{
        path = path || 'relaen.json';
        //读取文件
        let jsonStr:string = require('fs').readFileSync(path,'utf-8');
        let json;
        try{
            json = JSON.parse(jsonStr);
        }catch(e){
            throw "error config file!";
        }

        return json;
    }
}

export {RelaenManager}