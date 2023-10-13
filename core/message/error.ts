
import { RelaenTipManager } from "./tipmanager";

/**
 * Noomi异常类
 * @remarks
 * 用于产生异常信息
 */
export class RelaenError extends Error {
    /**
     * 错误码
     */
    code: string;

    /**
     * 构造器
     * @param code -      异常码或异常对象/信息等
     * @param params -    参数
     */
    constructor(code: string, ...args) {
        super();
        //数字串，从异常消息获取
        if(/^\d+$/.test(code)){
            const msg = RelaenTipManager.getError(code,...args);
            this.code = <string>code;
            this.message = `Error:"${code}",message is:"${msg}"`;
        }else{
            this.message = code;
        }
    }
}