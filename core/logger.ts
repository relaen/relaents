/**
 * 日志类
 * @since 0.4.0
 */
class Logger {

    private static log4js;

    /**
     * 初始化日志管理器
     * @param debug     是否开启dubug模式
     * @param fileLog   是否开启文件日志
     */
    public static init(debug: boolean, fileLog: boolean) {
        const log4js = require('log4js');
        // 默认文件日志配置
        let fileAppenders = {
            type: 'file',
            filename: 'relaen.log',
            layout: {
                type: "pattern",
                pattern: "[%d] %m"
            }
        };
        if (typeof fileLog === 'object') {
            fileAppenders = fileLog;
        }
        log4js.configure({
            appenders: {
                // debug模式默认
                console: { type: 'console', layout: { type: "pattern", pattern: "%m" } },
                // 文件模式
                file: fileAppenders
            },
            categories: {
                default: { appenders: ["console"], level: "off" },
                debug: { appenders: ["console"], level: "debug" },
                fileLog: { appenders: ["file"], level: "info" },
                debug_fileLog: { appenders: ["console", "file"], level: "debug" }
            }
        });
        let category = "default";
        if (debug && fileLog) {
            category = "debug_fileLog";
        }
        if (debug && !fileLog) {
            category = "debug"
        }
        if (!debug && fileLog) {
            category = "fileLog"
        }
        this.log4js = log4js.getLogger(category);
    }


    /**
     * 写日志到控制台
     * @param sql       sql语句
     * @param params    sql语句参数
     */
    public static log(sql: string, params?: any[] | object) {
        let msg = "[Relaen execute sql]:\"" + sql + "\"";
        this.log4js.info(msg);
        if (params) {
            this.log4js.info("Parameters is " + JSON.stringify(params));
        }
    }

    /**
     * 写错误消息
     * @param err   sql执行错误
     */
    public static error(err: Error) {
        let msg = "[Relaen execute sql] Error:\"" + err.message + "\"";
        this.log4js.error(msg);
    }
}

export { Logger };