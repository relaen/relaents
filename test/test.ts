
import { EntityManager } from "../core/entitymanager";
import { RelaenManager } from "../core/relaenmanager";
import { User } from "./entity/user";
import { getConnection } from "../core/connectionmanager";
import { Connection } from "../core/connection";
import { EntityManagerFactory } from "../core/entitymanagerfactory";
import { UserType } from "./entity/usertype";
import { Query } from "../core/query";
import { NativeQuery } from "../core/nativequery";

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
    user.setUserName('relaen');
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
    user.setUserName('aaaa');
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
    let sql = "select m from  User m where m.userType=? order by m.userId";
    let query:Query = em.createQuery(sql,User.name);
    query.setParameter(0,1);
    let u:User = <User> await query.getResult();
    await u.getUserType();
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
    let r = await query.getResultList();
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
    //事务回滚
    await tx.rollback();
    em.close();
    await conn.close();
}
//初始化relaen配置
RelaenManager.init(process.cwd() + '/relaen.json');
newUser();
// getUser(1);
// getUserType(1);
// updateUser(1);
// deleteUser(1);
// testQuery();
// testNativeQuery();
// testTransaction();

