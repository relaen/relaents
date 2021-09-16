import { RelaenManager } from "../..";
import { config } from "../config";
import { Shop } from "../entity/shop";
import { UserInfo } from "../entity/userinfo";

RelaenManager.init(config.mssql);
/*************************实体使用测试****************************/
/**
 * 新增
 */
//1.无主键新增：默认数据自增
//2.主键策略：sequence，配置@Id参数generator,seq_name
//3.主键策略：table，配置@Id参数generator,columnsName,valueName,keyName
//4.主键策略：uuid,配置@Id参数generator
async function add1() {
    //无主键新增
    let shop: Shop = new Shop();
    shop.shopName = 'frank的店铺';
    shop.address = '四川成都';
    //设置外键
    shop.userInfoForOwnerId = new UserInfo(1);
    shop.userInfoForManagerId = new UserInfo(2);
    //返回实体
    return await shop.save();
}

//5.有主键新增（主键不存在新增，存在更新）
async function add2() {
    let shop: Shop = new Shop(2);
    shop.shopName = 'frank的店铺';
    shop.address = '四川成都';
    //设置外键
    shop.userInfoForOwnerId = new UserInfo(1);
    shop.userInfoForManagerId = new UserInfo(2);
    //返回实体
    return await shop.save();
}

/**
 * 更新
 */
async function upd1() {
    let shop: Shop;
    //1、更新查询实体
    shop = <Shop>await Shop.find(1);
    //2、更新新建实体,同上述新增5
    // shop = new Shop(1);
    shop.shopName = 'frank的店铺修改';
    return await shop.save(true, 'optimistic');
}

/**
 * 查询
 */
async function select() {
    let res;
    //1、通过主键查找（属性设置select:false，不查询，仅实体和实体管理器使用）
    res = await Shop.find(1);
    // //2、查询单个实体
    res = await Shop.findOne({ shopName: { rel: 'like', value: 'frank' } });
    // //3、查询多个实体
    res = await Shop.findMany({ shopName: { rel: 'like', value: 'frank' } });
    // // 4、查询实体个数
    res = await Shop.getCount({ shopName: { rel: 'like', value: 'frank%' } })
    console.log(res);
}

/**
 * 删除
 */
async function del() {
    //1、通过实体删除
    // let shop = await Shop.find(3);
    // await shop.delete();
    //2、通过类删除，根据主键
    // await Shop.delete(1);
    //3、通过类删除，根据条件
    await Shop.deleteMany({ shopName: { rel: 'like', value: 'frank%' } })
}

upd1();