
import { EntityManager } from "../core/entitymanager";
import { RelaenManager } from "../core/relaenmanager";
import { Translator } from "../core/translator";
import { User } from "./entity/user";
import { Agent } from "./entity/agent";
import { Query } from "../core/query";
import { getConnection } from "../core/connectionmanager";
import { Connection } from "../core/connection";
import { EntityManagerFactory } from "../core/entitymanagerfactory";
import { Transaction } from "../core/transaction/transaction";

async function testPersist(){
    let conn:Connection = await getConnection();
    let tran:Transaction = await conn.createTransaction();
    tran.begin();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserName('field');
    user.setUserPwd('123456');
    user.setEnabled(1);
    await user.persist(em);
    tran.rollback();
    conn.close();
    console.log(user);
}

async function testQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let a:Agent = new Agent();
    a.setAgentId(10);
    a.setAgentName('yang');
    
    // console.log(Translator.entityToDelete(a));
    // let sql = "select a1    from  Agent a1 where a1.area=? order by a1.agentId";
    let sql = "select a1  from  Agent a1";
    // let em:EntityManager = new EntityManager();
    let query = em.createQuery(sql,Agent);
    query.getResultList().then(r=>{
        console.log(r);
    });
}

async function testGetOne(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let u = await em.find('User',7);
}

async function testDelete(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserId(12);
    let r = await em.delete(user);
}

async function testMerge(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserId(13);
    user.setUserName('aaaa');
    let r = await em.merge(user);
}

RelaenManager.init(process.cwd() + '/relaen.json');
testQuery();