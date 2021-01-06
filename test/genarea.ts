import { RelaenManager } from "../core/relaenmanager";
import { Connection } from "../core/connection";
import { getConnection } from "../core/connectionmanager";
import { EntityManagerFactory } from "../core/entitymanagerfactory";
import { EntityManager } from "../core/entitymanager";
import { NativeQuery } from "../core/nativequery";

//初始化relaen配置
RelaenManager.init({
    //数据库产品名
    dialect:"mysql",
    //数据库服务器地址
    host:"localhost",
    //端口
    port:3306,
    //用户名
    username:"root",
    //密码
    password:"field",
    //数据库名
    database:"tunnel",
    //连接池，可选
    pool:{
        min:0,
        max:10
    },
    //实体文件配置
    entities: [
        "/dist/test/entity/**/*.js"
    ],
    //开启以及缓存
    cache:true,
    //是否调试模式
    debug:true
});

