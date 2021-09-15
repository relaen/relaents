import { EntityManager, getEntityManager, NativeQuery } from "../..";
import { RelaenManager } from "../../core/relaenmanager";
import { config } from "../config";

RelaenManager.init(config.mysql);
/*************************原生构造器使用测试****************************/
/**
 * 原生使用
 */
async function native() {
    let em: EntityManager = await getEntityManager();
    let sql = '';
    let nativeQuery: NativeQuery = em.createNativeQuery(sql);
    //1、测试传入实体
    // let nativeQuery: NativeQuery = em.createNativeQuery(sql,Shop.name);
    //2、测试数组下标绑定
    nativeQuery.setParameter(0, 1);
    //3、测试字符串绑定
    nativeQuery.setParameter('id', 1);
    //4、测试数组绑定
    nativeQuery.setParameters([0, 'frank']);
    //5、测试对象绑定
    nativeQuery.setParameters({ id: 1, name: 'frank' });
    //6、测试运行select、insert、update、delete、lock等 sql
    //7、测试运行获得单个实体
    nativeQuery.getResult();
    //8、测试运行获得多个实体
    nativeQuery.getResultList(0,10);
}
native();