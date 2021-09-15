import { Connection, ConnectionManager, getConnection, RelaenManager, Transaction } from "../..";
import { config } from "../config";
import { Shop } from "../entity/shop";

RelaenManager.init(config.mysql);
/*************************事务使用测试****************************/
/**
 * 事务测试
 */
async function tran1() {
    let conn: Connection = await getConnection();
    //创建事务
    let tx: Transaction = conn.createTransaction();
    //事务开始
    await tx.begin();
    await Shop.delete(1);
    //事务提交
    await tx.commit();
    //事务回滚
    // await tx.rollback();
    await conn.close();
}

/**
 * 事务隔离级
 */
async function tran2() {
    let conn: Connection = await getConnection();
    let tx: Transaction = conn.createTransaction();
    //1、不同数据库设置隔离级和开始事务顺序不一致，不支持sqlite
    await tx.setisolationLevel('READ UNCOMMITTED');
    //2、sqliteTransaction 类型仅sqlite数据库事务加锁使用
    await tx.begin('immediate');
    await Shop.findMany();
    await tx.commit();
    await conn.close();
}

tran2()