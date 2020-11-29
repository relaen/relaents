import { IEntityCfg, IEntity } from "./entitydefine";
import { EntityFactory } from "./entityfactory";
import { BaseEntity } from "./baseentity";

class RelaenUtil{
    /**
     * 获取id名 
     * @param entity 实体对象或实体类名
     * @returns      实体id名
     */
    public static getIdName(entity:any):string{
        let en:string;
        if(entity instanceof BaseEntity){
            en = entity.constructor.name;
        }else if(typeof entity === 'string'){
            en = entity;
        }
        let cfg:IEntityCfg = EntityFactory.getClass(en);
        if(cfg.id){
            return cfg.id.name;
        }
    }

    /**
     * 设置属性值
     * @param entity    实体对象
     * @param value     实体值
     */
    public static setIdValue(entity:IEntity,value:any){
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(cfg && cfg.id && cfg.id.name){
            entity[cfg.id.name] = value;
        }
    }

    /**
     * 获取id值
     * @param entity    实体对象
     */
    public static getIdValue(entity:IEntity):any{
        let cfg:IEntityCfg = EntityFactory.getClass(entity.constructor.name);
        if(cfg && cfg.id){
            return entity[cfg.id.name];
        }
    }

    /**
     * 处理字段字符串值
     * @param value     待处理值
     * @returns         处理后的字符串
     */
    public static valueToString(value:any):string{
        if(typeof value !== 'string'){
            value = value + '';
        }
        //替换 ' 为 \'
        value = value.replace(/'/g,"\'");
        //两端添加 '
        return "'" + value  + "'";
    }
}

export{RelaenUtil}