import { RelaenManager } from "../core/relaenmanager";
import { Translator } from "../core/translator";
import { getEntityManager } from "../core/entitymanagerfactory";
import { EntityManager } from "../core/entitymanager";
import { Query } from "..";
import { Shop } from "./entity/shop";
import { GroupUser } from "./entity/groupuser";

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
    database:"test",
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

/*let tran = new Translator('Shop');

let a = ["shopName","manager.realName","owner.realName"];
// a = ["User.*","User.userType.*"]
tran.handleSelectFields(a);

// tran.handleFrom(['UserInfo','User']);

let where = tran.handleWhere({
    "manager.user.userName":{
        value:"A",
        before:'('
    },
    "manager.age":{
        before:'OR',
        rel:'>',
        value:1,
        after:')'
    },
    "owner.sexy":'M'
});

let order = tran.handleOrder({
    'owner.userId':'asc',
    "owner.age":'desc'
})
let r = tran.genSelectSql();
console.log(r[0],r);*/

async function test(){
    let em:EntityManager = await getEntityManager();
    let query:Query = em.createQuery(Shop.name);
    let r = await query.select(["*","manager.realName","owner.realName","owner.user.userId","manager.user.userId","manager.user.userName"])
        .from(['Group','User'])
        .where({
            "manager.user.userName":{
                value:"A",
                before:'('
            },
            "manager.age":{
                before:'or',
                rel:'>',
                value:1,
                after:')'
            },
            "owner.sexy":'M'
        })
        .orderBy({
            'owner.userId':'asc',
            "owner.age":'desc'
        }).getResultList();
    console.log(r);
    em.close();
}

async function testGetCount(){
    let em:EntityManager = await getEntityManager();
    console.log(await em.getCount(Shop.name));
    await em.close();
}

async function getUserGroups(name:string){
    let em:EntityManager = await getEntityManager();
    let query:Query = em.createQuery(GroupUser.name);
    let r = await query.select(["group.groupId","group.groupName","user.userName","user.userId"])
        .where({
            "user.userName":{
                value:name
            }
        })
        .getResultList();
    console.log(r);
    em.close();
}
// getUserGroups('relaen');
test();