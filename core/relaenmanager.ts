import { ConnectionManager } from "./connectionmanager";
import { EntityFactory } from "./entityfactory";
import { RelaenError } from "./message/error";
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
import { MariadbProvider } from "./dialect/mariadb/mariadbprovider";
import { SqliteProvider } from "./dialect/sqlite/sqliteprovider";
import { MariadbTransaction } from "./dialect/mariadb/mariadbtransaction";
import { SqliteTransaction } from "./dialect/sqlite/sqlitetransaction";
import { SqliteTranslator } from "./dialect/sqlite/sqlitetranslator";
import { MariadbTranslator } from "./dialect/mariadb/mariadbtranslator";
import { Logger } from "./logger";
import { ConnectionOption, DialectType } from "./types";

/**
 * relaen 框架管理器
 */
class RelaenManager {
    /**
     * 数据库类型
     */
    public static dialect: DialectType;

    /**
     * 开启一级cache
     */
    public static cache: boolean;

    /**
     * 是否调试模式
     */
    public static debug: boolean;

    /**
     * 是否文件日志
     */
    public static fileLog: boolean|object;

    /**
     * 是否允许全表操作，默认禁止
     */
    public static fullTableOperation: boolean;

    /**
     * 初始化
     * @param cfg -   配置文件名或配置对象
     */
    public static async init(cfg: ConnectionOption) {
        if (typeof cfg !== 'object') {
            throw new RelaenError('0001')
        }
        this.dialect = cfg.dialect || 'mysql';
        this.debug = cfg.debug || false;
        this.fileLog = cfg.fileLog;
        this.cache = cfg.cache === false ? false : true;
        this.fullTableOperation = cfg.fullTableOperation === true ? true : false;
        this.initProvider();
        this.initTransaction();
        this.initTranslator();
        this.initPlaceholder();
        Logger.init(this.debug, this.fileLog);
        ConnectionManager.init(cfg);
        //加载实体
        for (const path of cfg.entities) {
            await EntityFactory.addEntities(path);
        }
    }

    /**
     * 初始化各dialect对应的translator
     */
    private static initTranslator() {
        //添加到translator工厂
        TranslatorFactory.add('mssql', MssqlTranslator);
        TranslatorFactory.add('mysql', MysqlTranslator);
        TranslatorFactory.add('oracle', OracleTranslator);
        TranslatorFactory.add('postgres', PostgresTranslator);
        TranslatorFactory.add('mariadb', MariadbTranslator);
        TranslatorFactory.add('sqlite', SqliteTranslator);
    }

    /**
     * 初始化各dialect对应的provider
     */
    private static initProvider() {
        ProviderFactory.add('mssql', MssqlProvider);
        ProviderFactory.add('mysql', MysqlProvider);
        ProviderFactory.add('oracle', OracleProvider);
        ProviderFactory.add('postgres', PostgresProvider);
        ProviderFactory.add('mariadb', MariadbProvider);
        ProviderFactory.add('sqlite', SqliteProvider);
    }


    /**
     * 初始化各dialect对应的占位符配置
     */
    private static initPlaceholder() {
        //添加到placeholder工厂
        PlaceholderFactory.add('mssql', '@', 0);
        PlaceholderFactory.add('mysql', '?');
        PlaceholderFactory.add('oracle', ':', 0);
        PlaceholderFactory.add('postgres', '$', 1);
        PlaceholderFactory.add('mariadb', '?');
        PlaceholderFactory.add('sqlite', '?');
    }

    /**
     * 初始化各dialect对应的transaction
     */
    private static initTransaction() {
        //添加到transaction工厂
        TransactionFactory.addClass('mssql', MssqlTransaction);
        TransactionFactory.addClass('mysql', MysqlTransaction);
        TransactionFactory.addClass('oracle', OracleTransaction);
        TransactionFactory.addClass('postgres', PostgresTransaction);
        TransactionFactory.addClass('mariadb', MariadbTransaction);
        TransactionFactory.addClass('sqlite', SqliteTransaction);
    }
}

export { RelaenManager }