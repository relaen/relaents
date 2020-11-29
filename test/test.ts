
import { EntityManager } from "../core/entitymanager";
import { RelaenManager } from "../core/relaenmanager";
import { Translator } from "../core/translator";
import { User } from "./entity/user";

import { Query } from "../core/query";
import { getConnection } from "../core/connectionmanager";
import { Connection } from "../core/connection";
import { EntityManagerFactory } from "../core/entitymanagerfactory";
import { Transaction } from "../core/transaction/transaction";
import { UserType } from "./entity/usertype";
import { userInfo } from "os";

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
    let userType:UserType = new UserType(1);
    user.setUserType(userType);
    //保存new数据
    await user.save();
    em.close();
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
    //懒加载获取用户类别
    await u.getUserType();
    console.log(u);
    em.close();
    await conn.close();
    return u;
}

/**
 * 更新数据
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

async function deleteUser(id:number){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let user:User = new User(id);
    let r = await em.delete(user);
    em.close();
    conn.close();
}

async function testQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let sql = "select m.userName from  User m where m.userType=? order by m.userId";
    let query = em.createQuery(sql,User.name);
    query.setParameter(0,1);
    let u:User = <User> await query.getResult();
    await u.getUserType();
    em.close();
    conn.close();
}

async function testNativeQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let sql = "select a1.agent_id,a1.agent_name   from  t_agent a1";
    // let em:EntityManager = new EntityManager();
    let query = em.createNativeQuery(sql);
    let r = await query.getResultList();
    em.close();
    conn.close();
}

RelaenManager.init(process.cwd() + '/relaen.json');
updateUser(3);