import { RelaenManager } from "../core/relaenmanager";
import { Menu } from "./entity/menu";
import { Connection, getConnection, EntityManager, EntityManagerFactory } from "..";

async function getMenu(id):Promise<Menu>{
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let m:Menu = <Menu> await em.find(Menu.name,id);
    //懒加载获取用户类别(多对一)
    await m.getMenus();
    em.close();
    await conn.close();
    return m;
}
//初始化relaen配置
RelaenManager.init({
    //数据库产品名
    dialect:"mysql",
    //数据库服务器地址
    host:"101.200.148.184",
    //端口
    port:3306,
    //用户名
    username:"root",
    //密码
    password:"Field_Weblab_209",
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

getMenu(1);