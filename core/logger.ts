import { RelaenManager } from "./relaenmanager";

/**
 * 日志类
 */
class Logger{
    /**
     * 写日志到控制台
     * @param msg   待写消息
     */
    public static console(msg:string){
        if(RelaenManager.debug){
            console.log(msg);
        }
    }

    /**
     * 写错误消息
     * @param msg 
     */
    public static error(msg){
        console.error(msg);
    }
}

export{Logger};