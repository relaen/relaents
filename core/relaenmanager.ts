import { ConnectionManager } from "./connectionmanager";
import { EntityFactory } from "./entityfactory";
import { ErrorFactory } from "./errorfactory";

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
     * 开启一级cache
     */
    public static cache:boolean;

    /**
     * 是否调试模式
     */
    public static debug:boolean;
    /**
     * 初始化
     * @param cfg   配置文件名或配置对象
     */
    static init(cfg:any){
        if(typeof cfg === 'string'){
            cfg = this.parseFile(cfg);
        }
        if(typeof cfg !== 'object'){
            throw ErrorFactory.getError('0001')
        }
        
        this.dialect = cfg.dialect || 'mysql';
        this.debug = cfg.debug || false;
        this.cache = cfg.cache === false?false:true;
        ConnectionManager.init(cfg);
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