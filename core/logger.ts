/**
 * 日志类
 */
class Logger {

    private static log4js;

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
     * @param msg   待写消息
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
     * @param msg 
     */
    public static error(err: Error) {
        let msg = "[Relaen execute sql] Error:\"" + err.message + "\"";
        this.log4js.error(msg);
    }
}

export { Logger };