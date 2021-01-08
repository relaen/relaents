
import { UserInfo } from "./entity/userinfo";
import { RelaenManager, getEntityManager, EntityManager, Query, Connection, Transaction } from "..";
import { Shop } from "./entity/shop";
import { getConnection } from "../core/connectionmanager";
import { NativeQuery } from "../core/nativequery";

/**
 * 与数据库相关的方法都采用async，使用时请使用await 关键字
 * 所用实体需要test数据库的支持，请参考readme
 * entitymanager：实体管理器，用于进行实体操作
 * 关于显式和隐式
 * getConnection 显式获取数据库连接
 * getEntityManager 显示获取entitymanager并隐式创建数据库连接，关闭该entitymanager时，会隐式关闭connection
 * 基于实体类或实体对象的数据操作，隐式创建entitymanager，也会隐式关闭entitymanager
 * 凡是显示创建的connection或entitymanager，都需要显式关闭
 * 当一个方法中存在多个操作时（每次调用save、delete、find属于一个操作），建议显式创建entitymanager，
 * 这样会减少entitymanager和connection的创建次数，同时可使用一级缓存（一次异步方法内（从最外层首次创建entitymanager开始）的缓存）。
 */

/**
 * 新增数据
 * 通过实体对象的save方法保存
 */
async function addShop(){
    let shop:Shop = new Shop();
    shop.setShopName('field的店铺');
    shop.setAddress('四川绵阳');
    //设置外键
    shop.setOwner(new UserInfo(1));
    await shop.save();
}

/**
 * 新增数据
 * 通过entitymanager保存
 */
async function addShop1(){
    let shop:Shop = new Shop();
    shop.setShopName('field的店铺');
    shop.setAddress('四川绵阳');
    //设置外键
    shop.setOwner(new UserInfo(1));
    //显式创建entity manager
    let em:EntityManager = await getEntityManager();
    await em.save(shop);
    //显式创建的entitymanager，必须手动关闭
    await em.close();
}

/**
 * 修改数据
 * 该方法使用实体类或实体对象的方法进行自动获取entitymanager，在该方法中，会创建和销毁两个entitymanager
 * 实际使用时，实体类或实体对象的数据库操作方法适合单操作，而不适合多操作
 */
async function updShop(){
    //此为显示获取entitymanager，在该方法中将使用该entitymanager；
    //如果去掉这一行，将隐式创建entitymanager，此例子中，将创建两个entitymanager(同时创建两个数据库连接)
    let em:EntityManager = await getEntityManager();
    //采用Shop类find的方法查询
    let shop:Shop = <Shop> await Shop.find(1);
    // let shop:Shop = <Shop> await em.find(Shop.name,1);  //通过entitymanager操作
    shop.setAddress('四川 成都');
    //参数为true，表示仅对地址进行修改，其他项不变，否则，除了shopId和address，其它项都会置为null
    await shop.save(true);
    // await em.save(shop,true);  //通过entitymanager操作
    await em.close();
}

/**
 * 删除数据（通过id）
 */
async function delShop(id){
    //采用Shop类find的方法查询
    let shop:Shop = <Shop> await Shop.delete(id);

    //第二种方法
    // shop = <Shop> await Shop.find(id);
    // shop.delete();

    //第三种方法
    // let em:EntityManager = await getEntityManager();
    // shop = <Shop> await Shop.find(id);
    // em.delete(shop);
    // await em.close();
}

/**
 * 删除数据 （通过条件）
 */
async function delShops(){
    //条件对象参考find方法
    let params = {
        shopName:'field'
    }
    return await Shop.deleteMany(params);
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // await em.deleteMany(Shop.name,params);
    // await em.close();
}

/**
 * 查询单个实体(通过id)
 * @param id 
 */
async function find(id){
    return await Shop.find(id);
    // 第二种方法
    // let em:EntityManager = await getEntityManager();
    // let shop = await em.find(Shop.name,id);
    // await em.close();
    // return shop;
}

/**
 * 查询单个实体（通过条件）
 */
async function findOne(){
    //条件使用方式请参考API中EntityManager.findOne的条件参数说明
    //该条件最后结果为 "(owner.realName like '%yang%' or owner.age >= 20) and address='四川绵阳'"
    let params = {
        "owner.realName":{
            value:'yang',
            rel:'like',
            before:'(',
            after:'or'
        },
        "owner.age":{
            value:20,
            rel:'>=',
            after:')'
        },
        "address":'四川绵阳'
    }
    return <Shop>await Shop.findOne(params);
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // let shop:Shop = <Shop> await em.findOne(Shop.name,params);
    // em.close();
    // return shop;
}

/**
 * 查询单个实体（通过条件）
 */
async function findMany(){
    //条件使用方式请参考API中EntityManager.findOne的条件参数说明
    //该条件最后结果为 "(owner.realName like '%yang%' or owner.age >= 20) and address='四川绵阳'"
    let params = {
        "owner.realName":{
            value:'yang',
            rel:'like',
            before:'(',
            after:'or'
        },
        "owner.age":{
            value:20,
            rel:'>=',
            after:')'
        },
        "address":'四川绵阳'
    }
    return await Shop.findMany(params);
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // let lst = await em.findMany(Shop.name,params);
    // em.close();
    // return lst;
}

/**
 * 获取记录数
 */
async function getCount(){
    //参考查询的条件对象
    let params = {
        "owner.realName":'field'
    }
    return await Shop.getCount(params);
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // let count = await em.getCount(Shop.name,params);
    // await em.close();
    // return count;
}

/**
 * 链式查询
 * 通过链式操作拼接查询语句
 */
async function linkQuery(){
    let em:EntityManager = await getEntityManager();
    let query:Query = em.createQuery(Shop.name);
    let r = await query
        //查询字段
        .select(["*","manager.realName","owner.realName","owner.user.userId","manager.user.userId","manager.user.userName"])
        //where条件
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
        //order by
        .orderBy({
            'owner.userId':'asc',
            "owner.age":'desc'
        }).getResultList();
    
    await em.close();
    return r;
}

/**
 * 原生操作
 */
async function native(){
    let em:EntityManager = await getEntityManager();
    let sql = "select * from t_shop";
    //创建原生查询对象
    let query:NativeQuery = em.createNativeQuery(sql,Shop.name);
    //前五条数据，因为传入了实体类名，将会转换成实体对象
    return await query.getResultList(0,5);

}


/**
 * 事务
 * 事务由connection创建，在前面的例子中，connection都是有getEntityManager()隐式创建的，此处需要显式创建
 */
async function doTransaction(){
    let conn:Connection = await getConnection();
    let tx:Transaction = conn.createTransaction();
    await tx.begin();
    //调用删除shop操作
    await delShops();
    //提交操作
    // await tx.commit();
    //回滚，不会删除数据
    await tx.rollback();
    //显式创建的connection，一定要显示关闭
    await conn.close();
}

/**
 * 初始化relaen,请根据自己的数据库进行修改
 */
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
    //实体文件配置，为编译后js地址
    entities: [
        "/dist/test/entity/**/*.js"
    ],
    //开启以及缓存
    cache:true,
    //是否调试模式
    debug:true
});

/************* 执行测试方法 ***************/

//addShop();
// addShop1();

//linkQuery();

