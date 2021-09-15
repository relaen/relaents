import { EntityManager, getEntityManager, Query, RelaenManager } from "../..";
import { config } from "../config";
import { Shop } from "../entity/shop";

RelaenManager.init(config.oracle);
/*************************查询构造器使用测试****************************/
/**
 * select测试
 */
async function query() {
    let em: EntityManager = await getEntityManager();
    //通过entityManager创建构造器
    let query: Query = em.createQuery(Shop.name);
    let r = await query
        //1、查询单*
        // .select('*')
        //2、查询单属性名
        // .select('shopName')
        //3、查询数组集
        // .select(['shopId','shopName'])
        //4、查询关联对象：仅关联属性名
        // .select(['userInfoForManagerId'])
        //5、查询多个关联对象
        .select(['shopId'])
        .getResultList();
        //1、测试不生成实体
        // .getResult(true)
        //2、测试查询多条
        // .getResultList(0, 10, true);
    console.log(r);
    await em.close();
}

/**
 * where测试
 */
async function where() {
    let em: EntityManager = await getEntityManager();
    let query: Query = em.createQuery(Shop.name);
    let r = await query.select('shopId')
        .where({
            /**
             * rel 条件符测试
             */
            //1、默认rel =
            // shopName: 'frank'
            //2、rel：like，默认没有通配符加%value%
            // shopName: {
            //     rel: 'like',
            //     value:'frank'
            // }
            //3、rel：like，使用通配符
            // shopName: {
            //     rel: 'like',
            //     value: 'frank%'
            // }
            //4、rel：>,>=,<,<=,<>,!=,
            // shopId: {
            //     rel: '>=',
            //     value: 1
            // }
            //5、value：值为null时，默认rel切为is
            // shopName: null
            //6、rel：between | not between，参数为长度2数组
            // shopId: {
            //     rel: 'between',
            //     value: [1, 3]
            // }
            //7、rel：in | not in，参数为数组
            // shopId: {
            //     rel: 'not in',
            //     value:[1,2]
            // }

            /**
             * logic 逻辑符测试
             */
            //1、默认属性之间是 AND
            // shopId: 1,
            // shopName: 'frank'
            //2、嵌套使用逻辑符，前置后置符
            // shopId: {
            //     before: '(',
            //     rel: '=',
            //     value: 1
            // },
            // shopName: {
            //     rel: 'like',
            //     value: 'frank',
            //     logic: 'or',
            //     after: ')'
            // }

            /**
             * 属性名和值为外键对象测试
             */
            //1、属性名为外键
            // userInfoForManagerId: 1,
            //2、属性名值为外键
            // userInfoForManagerId:new UserInfo(1)
        })
        .getResult();
    console.log(r);
    await em.close();
}

/**
 * distinct,orderBy,groupBy,having 测试
 */
async function order() {
    let em: EntityManager = await getEntityManager();
    let query: Query = em.createQuery(Shop.name);
    let r = await query.select('*')
        .distinct()
        .groupBy('shopName').having({ shopName: 'frank' })
        .orderBy({ shopId: 'asc', shopName: 'desc' })
        .getResult();
    console.log(r);
    await em.close();
}

/**
 * delete 测试
 */
async function del() {
    let em: EntityManager = await getEntityManager();
    let query: Query = em.createQuery(Shop.name);
    let r = await query.delete()
        // 测试配置不能更新删除全表
        // .where({ shopId: 2 })
        .getResult();
    console.log(r);
    await em.close();
}

query()