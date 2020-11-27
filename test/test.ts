
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

async function testNew(){
    let conn:Connection = await getConnection();
    // let tran:Transaction = await conn.createTransaction();
    // tran.begin();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    // let user:User = new User();
    // user.setUserName('field');
    // user.setUserPwd('123456');
    // user.setEnabled(1);
    // await user.save(em);

    // tran.rollback();
    let agent:Agent = new Agent();
    agent.setAgentName("李四建筑");
    await agent.save(em);
    em.close();
    conn.close();
    
}

async function testQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let sql = "select a1.agentId,a1.agentName    from  Agent a1 where a1.area=? order by a1.agentId";
    let query = em.createQuery(sql,Agent.name);
    query.setParameter(0,1);
    let r = await query.getResult();
    let a:Agent = <Agent>r;
    await a.getArea();
    console.log(a);

    em.close();
}

async function testNativeQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let sql = "select a1.agent_id,a1.agent_name   from  t_agent a1";
    // let em:EntityManager = new EntityManager();
    let query = em.createNativeQuery(sql);
    query.getResultList().then(r=>{
        console.log(r);
    });
}

async function testGetOne(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let u = await em.find('User',7);
}

async function testDelete(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserId(12);
    let r = await em.delete(user);
}

async function testMerge(){
    let conn:Connection = await getConnection();
    let em:EntityManager = await EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserId(13);
    user.setUserName('aaaa');
    let r = await em.save(user);
}

RelaenManager.init(process.cwd() + '/relaen.json');
testQuery();