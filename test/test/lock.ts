import { Connection, EntityManager, getConnection, getEntityManager, Query, RelaenManager, Transaction } from "../..";
import { config } from "../config";
import { Shop } from "../entity/shop";

RelaenManager.init(config.mysql);
/*************************锁测试****************************/
/**
 * 乐观锁测试（需要配置字段version属性）
 */
async function lock1() {
    let shop = <Shop>await Shop.find(1);
    if (shop) {
        shop.shopName = '乐观锁测试';
        await shop.save(true, 'optimistic');
    }
}

/**
 * 悲观锁测试（表锁、行锁）
 */
async function lock2() {
    let conn: Connection = await getConnection();
    let tx: Transaction = conn.createTransaction();
    await tx.begin();
    let em: EntityManager = await getEntityManager();
    let query: Query = em.createQuery(Shop.name);
    let shop = await query.select('*')
        .where({ shopId: 1 })
        //1、pessimistic 使用行锁，不支持sqlite
        .setLock('pessimistic')
        .getResultList();
    if (shop) {
        shop = shop[0];
        shop.shopName = '悲观锁测试';
        await shop.save(true);
    } else {
        console.log('查询对象不存在');
    }
    await tx.commit();
    await em.close();
}

lock2();