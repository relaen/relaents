import { RelaenTipManager } from "./message/tipmanager";

/**
 * 日志类
 * 采用log4js进行日志管理
 */
class Logger {
    /**
     * log4js 对象
     */
    private static log4js;

    /**
     * 初始化日志管理器
     * @param debug -     是否开启dubug模式
     * @param fileLog -   是否开启文件日志，如果为对象，则为自定义日志参数，如果为true，则设置默认参数
     */
    public static init(debug: boolean, fileLog: boolean|object) {
        if (!debug && !fileLog) return;
        const appenders = {};
        const categories = { appenders: [], level: 'info' };
        const log4js = require('log4js');

        // debug模式
        if (debug) {
            appenders['console'] = { type: 'console', layout: { type: "pattern", pattern: "%m" } };
            categories.appenders.push('console');
        }

        // 文件模式
        if (fileLog) {
            //true则赋值默认文件配置
            appenders['file'] = typeof fileLog === 'object' ? fileLog : {
                type: 'file',
                filename: 'relaen.log',
                layout: {
                    type: "pattern",
                    pattern: "[%d] %m"
                }
            }
            categories.appenders.push('file');
        }

        // 默认文件日志配置
        log4js.configure({
            appenders: appenders,
            categories: { default: categories }
        });
        this.log4js = log4js.getLogger('default');
    }


    /**
     * 写日志到控制台
     * @param sql -       sql语句
     * @param params -    sql语句参数
     */
    public static log(sql: string, params?: unknown[] | object) {
        if (this.log4js) {
            this.log4js.info(RelaenTipManager.getTip("executeSql") + ":\"" + sql + "\"");
            if (params) {
                this.log4js.info(RelaenTipManager.getTip("params") + ":" + JSON.stringify(params));
            }
        }
    }

    /**
     * 写错误消息
     * @param err -   sql执行错误
     */
    public static error(err: Error) {
        if (this.log4js) {
            this.log4js.error(RelaenTipManager.getTip("executeSql") + " Error:\"" + err.message + "\"");
        }
    }
}

export { Logger };