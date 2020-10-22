/**
 * 实体管理器
 */
class EntityManager{
    private static entities:Map<string,any> = new Map();
    /**
     * 保存实体
     * @param entity    待删除实体
     * @returns         保存后的实体
     */
    public static async save<T>(entity:T):Promise<T>{
        
        return entity;
    }

    /**
     * 删除实体
     * @param entity    待删除实体
     * @returns         是否删除成功
     */
    public static async delete<T>(entity:T):Promise<boolean>{

        return true;
    }


    /**
     * 通过id查找实体
     * @param entity 
     * @param idValue 
     */
    public static async findById<T>(entity:T,idValue:any):Promise<T>{
        return entity;
    }
    /**
     * 查询实体
     */
    query(){

    }

}