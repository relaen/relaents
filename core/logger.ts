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
}

export{Logger};