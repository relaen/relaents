
import { EntityManager } from "../core/entitymanager";
import { RelaenManager } from "../core/relaenmanager";
import { Translator } from "../core/translator";
import { User } from "./entity/user";

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

async function testQuery(){
    let sql = "select count(a1) ,a1.area.areaId    from       Agent a1 left join Area a2 on a1.area =a2 , Authority a3 where a1.areaId=1";
    // let sql = "select a1  from       Agent a1 left join Area a2 on a1.area =a2 , Authority a3 where a1.areaId=1";
    Translator.getQuerySql(sql);
}

RelaenManager.init(process.cwd() + '/relaen.json');
testQuery();