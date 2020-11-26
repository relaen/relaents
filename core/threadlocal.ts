
const{AsyncLocalStorage} = require("async_hooks");
/**
 * 线程存储
 */
class ThreadStorage{
    /**
     * 线程id
     */
    private static threadId:number = 1;

    /**
     * 异步线程存储器
     */
    private static localStorage = new AsyncLocalStorage();
    
    /**
     * 获取 async local storage
     * @returns     当前threadId
     */
    public static newStorage(){
        let sid = this.threadId++;
        this.localStorage.enterWith(sid);
        return sid;
    }

    /**
     * 获取存储值
     * @returns     当前threadId
     */
    public static getStore():any{
        return this.localStorage.getStore();
    }
}

export{ThreadStorage}