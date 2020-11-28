
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

async function newUser(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserName('relaen');
    user.setAge(1);
    user.setSexy('M');
    let userType:UserType = new UserType(1);
    user.setUserType(userType);
    await user.save();
    em.close();
    conn.close();
}

async function updateUser(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserId(1);
    user.setUserName('aaaa');
    user.save(true);
    // let r = await em.save(user);
    em.close();
    conn.close();
}

async function deleteUser(id:number){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let user:User = new User(id);
    let r = await em.delete(user);
    em.close();
    conn.close();
}

async function findOne(id:number){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let r = await em.find(User.name,id);
    console.log(r);
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

async function testGetOne(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let u = await em.find('User',7);
    em.close();
    conn.close();
}

RelaenManager.init(process.cwd() + '/relaen.json');
findOne(2);