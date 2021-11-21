import { EntityManager, getConnection, getEntityManager, RelaenManager } from "../..";
import { Shop } from "../entity/shop";


/*************************连接测试****************************/
/**
 * relaen连接参数测试
 */
async function conn1() {
    RelaenManager.init({
        "dialect": "mysql",
        "host": "localhost",
        "port": 3306,
        "username": "root",
        "password": "root",
        "database": "relaen",
        //1、是否开启连接池
        "pool": {
            "min": 1,
            "max": 10
        },
        //2、连接超时
        "connectTimeout": 1000,
        //3、连接池空闲超时
        "idleTimeout": 10000,
        "entities": [
            "/test/entity/**/*.ts"
        ],
        //4、是否开启缓存
        "cache": true,
        //5、是否开启调试模式
        "debug": false,
        //6、是否开启文件日志
        "fileLog": false,
        //7、是否开启全表更新删除
        "fullTableOperation": false,
    });

    //4、是否开启缓存测试，需要创建entityManager
    // let em: EntityManager = await getEntityManager();
    // let shop: Shop = <Shop>await Shop.find(1);
    // await Shop.find(1);
    // console.log(shop);
    // await em.close();

    //6、是否开启文件日志测试，简洁配置true，即开启默认文件日志，存储文件名为 relaen.log，存储地址为当前项目根目录
    // fileLog可传递log4j appender 为file或dateFile：https://log4js-node.github.io/log4js-node/appenders.html
    // 额外传递appeder案列：{type:'datefile',filename:'relaen.log'}
    // await Shop.find(1);
    // await Shop.delete(1);

    //7、是否开启全表更新删除
    // await Shop.deleteMany();
}

/**
 * 原生连接参数测试
 */
async function conn2() {
    RelaenManager.init({
        "dialect": "mysql",
        "entities": [
            "/test/entity/**/*.ts"
        ],
        "cache": true,
        "debug": true,
        "fileLog": false,
        "fullTableOperation": false,
        //1、原生连接参数，参考数据库npm包API
        "options": {
            user: 'root',
            password: 'root',
            port: 3306,
            database: 'relaen',
            connectionLimit: 10
        },
        //2、原生连接是否开启连接池
        "usePool": true
    });

    await Shop.find(1);
}

conn1()

