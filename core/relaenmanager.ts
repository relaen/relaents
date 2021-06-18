import { ConnectionManager } from "./connectionmanager";
import { EntityFactory } from "./entityfactory";
import { ErrorFactory } from "./errorfactory";
import { TranslatorFactory } from "./translatorfactory";
import { MssqlTranslator } from "./dialect/mssql/mssqltranslator";
import { MysqlTranslator } from "./dialect/mysql/mysqltranslator";
import { OracleTranslator } from "./dialect/oracle/oracletranslator";
import { PostgresTranslator } from "./dialect/postgres/postgrestranslator";
import { PlaceholderFactory } from "./placeholderfactory";
import { TransactionFactory } from "./transactionfactory";
import { MysqlTransaction } from "./dialect/mysql/mysqltransaction";
import { ProviderFactory } from "./providerfactory";
import { MssqlProvider } from "./dialect/mssql/mssqlprovider";
import { MysqlProvider } from "./dialect/mysql/mysqlprovider";
import { OracleProvider } from "./dialect/oracle/oracleprovider";
import { PostgresProvider } from "./dialect/postgres/postgresprovider";
import { MssqlTransaction } from "./dialect/mssql/mssqltransaction";
import { OracleTransaction } from "./dialect/oracle/oracletransaction";
import { PostgresTransaction } from "./dialect/postgres/postgrestransaction";

/**
 * relaen 框架管理器
 */
class RelaenManager {
    /**
     * 数据库类型
     * mysql oracle mssql
     */
    public static dialect: string;

    /**
     * 开启一级cache
     */
    public static cache: boolean;

    /**
     * 是否调试模式
     */
    public static debug: boolean;

    /**
     * 初始化
     * @param cfg   配置文件名或配置对象
     */
    public static async init(cfg: any) {
        if (typeof cfg === 'string') {
            cfg = this.parseFile(cfg);
        }
        if (typeof cfg !== 'object') {
            throw ErrorFactory.getError('0001')
        }

        this.dialect = cfg.dialect || 'mysql';
        this.debug = cfg.debug || false;
        this.cache = cfg.cache === false ? false : true;
        this.initProvider();
        this.initTransaction();
        this.initTranslator();
        this.initPlaceholder();
        ConnectionManager.init(cfg);
        //加载实体
        for (let path of cfg.entities) {
            EntityFactory.addEntities(path);
        }
    }

    /**
     * 初始化各dialect对应的translator
     */
    private static initTranslator(){
        //添加到translator工厂
        TranslatorFactory.add('mssql',MssqlTranslator);
        TranslatorFactory.add('mysql',MysqlTranslator);
        TranslatorFactory.add('oracle',OracleTranslator);
        TranslatorFactory.add('postgres',PostgresTranslator);
    }

    /**
     * 初始化各dialect对应的provider
     */
    private static initProvider(){
        ProviderFactory.add('mssql',MssqlProvider);
        ProviderFactory.add('mysql',MysqlProvider);
        ProviderFactory.add('oracle',OracleProvider);
        ProviderFactory.add('postgres',PostgresProvider);
    }


    /**
     * 初始化各dialect对应的占位符配置
     */
    private static initPlaceholder(){
        //添加到placeholder工厂
        PlaceholderFactory.add('mssql','@',0);
        PlaceholderFactory.add('mysql','?');
        PlaceholderFactory.add('oracle',':',0);
        PlaceholderFactory.add('postgres','$',1);
    }

    /**
     * 初始化各dialect对应的transaction
     */
     private static initTransaction(){
        //添加到transaction工厂
        TransactionFactory.add('mssql',MssqlTransaction);
        TransactionFactory.add('mysql',MysqlTransaction);
        TransactionFactory.add('oracle',OracleTransaction);
        TransactionFactory.add('postgres',PostgresTransaction);
    }

    /**
     * @exclude
     * 解析实例配置文件
     * @param path      文件路径
     */
    public static parseFile(path: string): any {
        path = path || 'relaen.json';
        //读取文件
        let jsonStr: string = require('fs').readFileSync(path, 'utf-8');
        let json;
        try {
            json = JSON.parse(jsonStr);
        } catch (e) {
            throw "error config file!";
        }
        return json;
    }

}

export { RelaenManager }