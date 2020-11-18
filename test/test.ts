
import { EntityManager } from "../core/entitymanager";
import { RelaenManager } from "../core/relaenmanager";
import { Translator } from "../core/translator";
import { User } from "./entity/user";
import { Agent } from "./entity/agent";
import { Query } from "../core/query";

async function testPersist(){
    let em:EntityManager = new EntityManager();
    let user:User = new User();
    user.setUserId(5);
    user.setUserName('admin');
    user.setUserPwd('123456');
    user.setEnabled(1);
    await user.persist(em);
    console.log(user);
}

function testQuery(){
    let a:Agent = new Agent();
    a.setAgentId(10);
    a.setAgentName('yang');
    // console.log(Translator.entityToDelete(a));
    // let sql = "select a1    from  Agent a1 where a1.area=? order by a1.agentId";
    let sql = "select a1  from  Agent a1";
    let em:EntityManager = new EntityManager();
    let query = em.createQuery(sql,Agent);
    query.getResultList().then(r=>{
        console.log(r);
    });
    
}

RelaenManager.init(process.cwd() + '/relaen.json');
testQuery();