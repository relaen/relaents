import { EntityManager, getEntityManager } from "../..";
import { RelaenManager } from "../../core/relaenmanager";
import { config } from "../config";
import { Shop } from "../entity/shop";
import { UserInfo } from "../entity/userinfo";

RelaenManager.init(config.mysql);
/*************************实体管理使用测试****************************/
/**
 * 新增
 */
async function add() {
    //显式创建entity manager
    let em = await getEntityManager();
    let shop: Shop = new Shop();
    shop.shopName = 'frank的店铺';
    shop.address = '四川成都';
    shop.userInfoForOwnerId = new UserInfo(1);
    shop.userInfoForManagerId = new UserInfo(2);
    //保存
    await em.save(shop);
    //显式关闭
    await em.close();
}

/**
 * 更新
 */
async function upd() {
    //显式创建entity manager
    let em = await getEntityManager();
    let shop: Shop = <Shop>await Shop.find(1);
    shop.shopName = 'frank的店铺更新';
    shop.address = '四川成都';
    shop.userInfoForOwnerId = new UserInfo(1);
    shop.userInfoForManagerId = new UserInfo(2);
    //保存
    await em.save(shop, true);
    //显式关闭
    await em.close();
}

/**
 * 删除
 */
async function del() {
    let em = await getEntityManager();
    //1、通过实体删除
    // await em.delete(new Shop(8));
    //2、通过主键、类名删除
    // await em.delete(9, Shop.name);
    //3、通过条件删除
    await em.deleteMany(Shop.name, { shopName: { rel: 'like', value: '1' } });
    await em.close();
}

/**
 * 查询
 */
async function sel() {
    let em = await getEntityManager();
    //1、通过类名、主键查询
    await em.find(Shop.name, 1);
    //2、通过类名、条件单查询
    // await em.findOne(Shop.name, { shopName: { rel: 'like', value: 'frank' } });
    //3、通过类名、条件多查询
    // await em.findMany(Shop.name, { shopName: { rel: 'like', value: 'frank' } }, 0, 2);
    //4、通过类名、条件查询数量
    // await em.getCount(Shop.name, { shopName: { rel: 'like', value: 'frank' } });
    await em.close();
}

sel()