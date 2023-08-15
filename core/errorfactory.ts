import { RelaenTip } from "./relaentip";

/**
 * 异常工厂
 * @remarks
 * 用于异常信息管理和异常信息处理
 */
class ErrorFactory{
    /**
     * 异常信息map，键为异常码，值为异常信息
     */
    static errMap:Map<string,string> = new Map();
    /**
     * 异常提示语言
     */
    /**
     * 获取异常
     * @param errNo -     异常码
     * @param param -     参数值数组，用于处理消息带参数的情况
     * @returns           异常 code:异常码 message:异常消息
     */
    static getError(errNo:string,param?:unknown[]):Error{
        //默认为未知错误
        if(!RelaenTip[errNo]){
            errNo = "0000";   
        }
        let msg = RelaenTip[errNo];
        const reg = /\$\{.+?\}/g;
        let r;
        //处理消息中的参数
        while((r=reg.exec(msg)) !== null){
            let index = r[0].substring(2,r[0].length-1).trim();
            if(index && index !== ''){
                index = parseInt(index);
            }
            msg = msg.replace(r[0],param[index]);
        }
        return new Error("Error:\""+ errNo + "\",message is:\"" + msg + "\"");
        
    }
}

export {ErrorFactory}