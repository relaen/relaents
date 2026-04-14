import LocaleEn from "../locales/relaentip_en";
import LocaleZh from "../locales/relaentip_zh";
/**
 * 获取体胖信息
 */
export class RelaenTipManager{
    /**
     * 消息对象
     */
    private static tip:{error:Record<string,string>,tip:Record<string,string>};
    /**
     * 初始化消息
     * @param lang - 语言，支持zh和en，默认en
     */
    public static async init(lang?:string){
        switch(lang){
            case 'zh':
                this.tip = LocaleZh;
            default:
                this.tip = LocaleEn;
        }
    }
    /**
     * 获取消息
     * @param type -    类型
     * @param code -    错误码
     * @param params -  参数
     * @returns         提示信息
     */
    private static get(type:'tip'|'error',code:string,...params:string[]):string{
        if(params.length>0){
            return this.compileString(this.tip[type][code], ...params);
        }
        return this.tip[type][code];
    }

    /**
     * 获取提示信息
     * @param code -    提示码
     * @param params -  参数
     * @returns         提示信息
     */
    public static getTip(code:string, ...params:string[]):string{
        return this.get('tip',code,...params);
    }

    /**
     * 获取异常信息
     * @param code -    提示码
     * @param params -  参数
     * @returns         异常信息
     */
    public static getError(code:string, ...params:string[]):string{
        return this.get('error',code,...params) || this.get('error','0000');
    }

    /**
     * 编译string
     * @param src       源消息 
     * @param params    参数数组
     * @returns 
     */
    private static compileString(src: string, ...params:string[]): string {
        if(!params || params.length === 0){
            return src;
        }
        let reg: RegExp;
        for (let i=0;i<params.length;i++) {
            const ri = '${' + i + '}';
            if (src.indexOf(ri) !== -1) {
                reg = new RegExp('\\$\\{' + i + '\\}', 'g');
                src = src.replace(reg, params[i]);
            } else {
                break;
            }
        }
        return src;
    }
}