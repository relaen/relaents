import { BaseEntity } from "./baseentity";
import {Serializer} from "serializer-node";
import { RelaenManager } from "./relaenmanager";

/**
 * redis 工厂
 */
class RedisManager{
    /**
     * 存储所有的redis对象
     */
    static client:any;
    
    /**
     * uuid
     */
    static uuid = require('uuid');
    /**
     * 设置client
     * @param host      主机地址 
     * @param port      端口
     * @param options   附加参数
     */
    static setClient(host:string,port:string,options?:any){
        this.client = require('redis').createClient(port,host,options);
        this.client.on('error',err=>{
            throw err;
        });
    }

    /**
     * 保存
     * @param entity    待保存对象
     */
    static async saveEntity(entity:BaseEntity){
        let s = Serializer.serialize(entity);
        await this.client.hset(RelaenManager.appName,this.uuid.v1(),s);
    }

    /**
     * 获取entity
     * @param key   实体key
     * @returns     实体
     */
    static async getEntity(key:string):Promise<BaseEntity>{
        let s = await this.client.hget(RelaenManager.appName,key);
        return Serializer.deserialize(s);
    }


    /**
     * 获取所有实体key
     * @returns     key数组
     */
    static async getKeys():Promise<string[]>{
        return this.client.hkeys(RelaenManager.appName);
    }

    /**
     * 获取所有实体序列化串
     * @returns     序列化串数组
     */
    static async getAllEntitiesString():Promise<string[]>{
        return await this.client.hvals(RelaenManager.appName);
    }
}

export {RedisManager}