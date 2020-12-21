import { EntityManager } from "../core/entitymanager";
import { RelaenManager } from "../core/relaenmanager";
import { User } from "./entity/user";
import { getConnection } from "../core/connectionmanager";
import { Connection } from "../core/connection";
import { EntityManagerFactory } from "../core/entitymanagerfactory";
import { UserType } from "./entity/usertype";
import { Query } from "../core/query";
import { NativeQuery } from "../core/nativequery";
import { Transaction } from "../core/transaction/transaction";

/**
 * 与数据库相关的方法都采用async，使用时请使用await 关键字
 * 包括 connection 相关操作 getConnection,connection.close
 * 实体增删改方法 save, delete, 关联关系数据获取
 * 事务方法 begin, commit, rollback
 */
/**
 * 新建用户
 */
async function newUser(){
    //获取连接
    let conn:Connection = await getConnection();
    //创建entity manager
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserName('field');
    user.setAge(1);
    user.setSexy('M');
    //设置用户类别
    let userType:UserType = new UserType(1);
    user.setUserType(userType);
    //保存用户数据，必须先创建entitymanager，否则无法执行操作
    await user.save();
    //关闭entitymanager，使用完毕后必须关闭
    em.close();
    //关闭连接，使用完毕后必须关闭
    await conn.close();
}

/**
 * 获取用户信息
 * @param id    用户id
 */
async function getUser(id):Promise<User>{
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let u:User = <User> await em.find(User.name,id);
    //懒加载获取用户类别(多对一)
    await u.getUserType();
    em.close();
    await conn.close();
    return u;
}

/**
 * 通过类型获取用户，采用em的findMany方法
 * @param id    用户类型id
 */
async function getUserByType(id){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);

    let lst:User[] = await em.findOne(User.name,{userType:id});
    em.close();
    await conn.close();
}

/**
 * 获取用户类型
 * @param id    用户类型id
 */
async function getUserType(id):Promise<UserType>{
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let ut:UserType = <UserType> await em.find(UserType.name,id);
    //懒加载获取关联用户(一对多)
    await ut.getUsers();
    em.close();
    await conn.close();
    return ut;
}
/**
 * 更新用户
 * @param id    用户id
 */
async function updateUser(id){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = await getUser(id);
    user.setUserId(null);
    user.__status = 1;
    user.setUserName('relaen');
    user.setUserType(new UserType(2));
    //参数为true，则表示只对不为undefined的值进行更新，否则所有undefined的属性都会更新成null
    await user.save(true);
    em.close();
    await conn.close();
}

/**
 * 删除用户
 * @param id    用户id 
 */
async function deleteUser(id:number){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User(id);
    let r = await em.delete(user);
    em.close();
    await conn.close();
}

/**
 * 查询
 * rql采用对象方式进行书写，执行时需要翻译成原生sql执行，所以执行效率低于原生sql
 */
async function testQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    // let sql = "select m from  User m where m.userType=? order by m.userId";
    // let sql = "select m from  User m where m.userId in ? order by m.userId";
    let sql = "select m.userName from  User m where m.userType=? order by m.userType.userTypeName";
    let query:Query = em.createQuery(sql,User.name);
    //设置参数，按照sql中的"?"顺序来，索引从0开始，如果参数值是对象，会提取对象的主键
    // query.setParameter(0,[[3,4,5]]);
    query.setParameter(0,1);
    query.setStart(0);
    query.setLimit(5);
    //获取单个对象
    // let u:User = <User> await query.getResult();
    //懒加载获取用户类型
    // await u.getUserType();
    // let list = await query.getResultList();
    //提取第5-14记录，如果参数为空，则返回所有记录。第一个参数为记录起始索引号，第二个参数为记录数
    let ul:User[] = <User[]> await query.getResultList();
    em.close();
    await conn.close();
}

/**
 * 原生查询
 * 原生sql执行效率高于rql，减少了rql翻译成sql的过程
 */
async function testNativeQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let sql = "select * from  t_user";
    let query:NativeQuery = em.createNativeQuery(sql);
    //和Query一样，支持setParameter
    //参数和Query一样，获取前5条数据
    let r = await query.getResultList(0,5);
    em.close();
    await conn.close();
}

/**
 * 获取数量
 */
async function testGetCount(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let sql = "select m.userName from  User m order by m.userId";
    // let sql = "select m from  User m where m.userId in ? order by m.userId";
    // let sql = "select count(m) from  User m";
    let query:Query = em.createQuery(sql);
    let count = await query.getResult();
    em.close();
    await conn.close();
}

/**
 * 原生删除
 */
async function testNativeDelete(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let sql = "delete from  t_user where user_id>100";
    // let sql = "select m from  User m where m.userId in ? order by m.userId";
    // let sql = "select count(m) from  User m";
    let query:Query = em.createNativeQuery(sql);
    let count = await query.getResult();
    em.close();
    await conn.close();
}

/**
 * 事务测试
 */
async function testTransaction(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    //创建事务
    let tx = conn.createTransaction();
    //事务开始
    await tx.begin();
    await newUser();
    await deleteUser(4);
    // 事务提交
    // await tx.commit();
    // 事务回滚
    await tx.rollback();
    em.close();
    await conn.close();
}
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


// newUser();
// getUser(31);
getUserByType(1);
// getUserType(1);
// updateUser(4);
// deleteUser(1);
// testQuery();
// testGetCount();
// testNativeQuery();
// testTransaction();
// testNativeDelete();