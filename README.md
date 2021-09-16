# Relaen
relaen是[noomi](https://www.npmjs.com/package/noomi)团队打造的一套node环境下基于typescript的[ORM](https://baike.baidu.com/item/对象关系映射/311152?fromtitle=ORM&fromid=3583252&fr=aladdin)框架。  
支持链式创建查询、原生sql、类查询、一级缓存、文件日志和锁机制等。

## 使用限制
relaen当前支持mysql、mssql(2012+)、oracle(12+)、postgres、mariadb、sqlite数据库，其它数据库产品陆续加入中。

## 交流
1. QQ群：926248391；
2. 加入验证问题：who are you，回答：relaen。

## 实体对象生成
relaen提供了[relaen cli](https://www.npmjs.com/package/relaen-cli)工具，该工具可自动生成relaen所需要的实体。

## 文档
### API
http://www.noomi.cn/relaen/api.html

## 版本
### 0.0.5
正式发布

### 0.0.6
1. 增加RelaenManager.init方法传入对象参数；
2. 抛出sql执行异常，便于第三方框架进行异常捕获。

### 0.0.7
1. 修复update时关联字段的存储bug；
2. 修复Query.getResultList方法 start参数为0时查询结果问题。

#### 0.0.8
1. 更新readme

#### 0.0.9
1. 修改delete返回值说明；
2. 修复insert后返回实体主键不正确的bug；
3. 修复Query setParameter方法的bug。

#### 0.1.0
1. 修复EntityManagerFactory.getCurrentEntityManager返回null时的处理；
2. 修复Query.getResultList的参数设置，当start为0时的异常。

#### 0.1.1
1. 修改打包内容，解决找不到".map"文件的警告

#### 0.1.2
1. 增加api文档

#### 0.1.3
1. 修改cache更新策略：当执行 insert,update,delete 时清空entitymanager的cache；
2. 修改实体类以自己作为外键时的query bug；
3. 修改一对多条件下，字段名和引用字段名不一致时，获取关联对象时不正确的bug；
4. 增加getResult时获取单个属性值操作，如count(m)获取记录数；
5. 修改状态为NEW时，拥有主键的对象执行save方法时为insert的bug；
6. Query和NativeQuery的getResult加上insert、delete、update类语句执行功能；
7. 修复entity nullable字段在insert时的处理；
8. 修复外键作为主键时的update sql 不正确的bug；
9. 给EntityManager增加了 findOne,findMany,deleteMany方法；
10. 保存数据时，增加字段长度检查;
11. 修复connection close时未添加await关键字的bug。

#### 0.2.0
1. 取消原有rql创建方式，采用链式创建sql，参考示例代码的linkQuery方法；
2. 增强实体类和实体对象的方法。

#### 0.2.1
1. 给实体类的findMany方法增加orderby参数;
2. 修复insert或update时，相同字段出现多次执行不正确的bug；
3. 修复where条件多个参数且参数值不是对象时的bug；
4. 修复外键且主键情况下，设置外键对象并保存后，主键id错误的bug。

#### 0.2.2
1. 去掉entity中的__status属性，由EntityManagerFactory统一管理；
2. 查询结果为null或undefined，则该属性不会存在于结果集中，避免出现全部属性为空的对象。

#### 0.3.0
1. 增加oracle、mssql、postgres数据库支持；
2. 增加entity scheme配置项。

#### 0.4.0
1. 增加mariadb、sqlite数据库支持；
2. 增加乐观锁与悲观锁机制；
3. 增加主键table生成；
4. 增加Query中条件构建；
5. 增加实体查询属性隐藏配置select；
6. 增加原生连接传递参数options；
7. 增加文件日志记录；
8. 增加防止全表删除属性fullTableOperation；
9. 增加原生查询nativequery字符串绑定参数setParameter方法；
10. 新增事务隔离级设置方法；
11. 完善分页语句判断

## 配置文件
relaen依赖配置文件进行初始化，配置内容如下：
配置项|说明|类型|必填|可选值|默认值|备注
-|-|-|-|-|-|-
dialect|数据库产品|string|是|mysql,oracle,mssql|无|支持的数据库产品持续更新
host|数据库服务器地址|string|是|无|无|数据库服务器网址或ip或localhost
port|数据库服务器端口|number|否|无|如果是默认则不用，如mysql的3306
username|用户名|string|是|无|无|
password|密码|string|是|无|无|
database|数据库|string|是|无|无|只支持单数据库
schema|模式名|string|否|无|无|mysql、mariadb、sqlite不支持
entities|实体js目录|string array|是|无|无|如:["/dist/entity/**/*.js"]
cache|是否开启一级缓存|boolean|否|无|true|
debug|是否为debug模式|boolean|否|无|false|调试模式将在控制台输出每次执行的sql语句
fileLog|是否开启文件日志| boolean\|object|否|无|false|将数据库操作日志记录到文件，true开启默认文件日志，object传入自定义appeder 
fullTableOperation|是否全表更新与删除|boolean|否|无|false|默认不能全表更新删除  
pool|连接池配置|object|否|无|无|如果配置，则开启数据库连接池，连接库配置如下 
connectTimeout|连接超时时间|number|否|无|无|  
idleTimeout|连接池空闲连接超时时间|number|否|无|无| 
options|原生连接配置|object|否|无|无|如果使用数据库原生连接配置，上述host等配置失效
usePool|原生连接是否开启连接池|boolean|否|无|无|在开启数据库原生连接配置，是否开启连接池      


**连接池配置**  
如果pool为空对象，则max和min使用默认值。
配置项|说明|类型|必填|默认值
-|-|-|-|-
max|最大连接数|number|否|10
min|最小连接数|number|否|1

## 配置文件示例
该文件命名为relaen.json
### 初始化方式
```ts
	RelaenManager.init(process.cwd() + '/relaen.json');
```
### json文件
```json
{
    "dialect":"mysql",
    "host":"localhost",
    "port":3306,
    "username":"your user name",
    "password":"your password",
	"database":"your db name",
	"pool":{
		"min":0,
		"max":10
	},
    "entities": [
        //编译后的实体文件路径
        "/dist/test/entity/**/*.js"
    ],
    "cache":true,
    "debug":true
}
```

## 注解

### @Entity(实体注解)
对类进行注解，表示该类为一个实体类，被注解类必须继承于BaseEntity。

#### 参数
参数顺序|参数名|类型|必填
-|-|-|-
1|表名称|string|是
2|数据库名称|string|否

### @Id(主键注解)     
对属性进行注解，表示该属性对应Entity的主键，relaen只支持**单主键**。

#### 参数
参数为对象，可选，包含以下项：
参数名|说明|类型|必填|可选值|默认值|备注
-|-|-|-|-|-|-
generator|主键生成策略|string|否|identity(默认，支持mysql、mssql、postgres),sequence(支持oracle、mssql、postgres)|identity
seqName|主键对应sequence名|否|无|无|如果generator为'sequence'，则该项不能为空

### @Column(字段注解)
对属性进行注解，表示该属性为字段。
#### 参数
参数为对象，必填，包含以下项：

参数名|说明|类型|必填|可选值|默认值|备注
-|-|-|-|-|-|-
name|表中对应字段名|string|否|无|属性名|表中字段名与属性名相同时不用设置
type|数据类型|string|是|int,double,string,date,object|无|
nullable|是否可空|boolean|否|true,false|false|
length|长度|number|否|无|无|type为string时有效

### @JoinColumn(关联字段注解)
对属性进行注解，表示该属性为外键关联字段，与@ManyToOne或@OneToOne配合使用。
#### 参数
参数为对象，必填，包含以下项：

参数名|说明|类型|必填|可选值|默认值|备注
-|-|-|-|-|-|-
name|表中对应字段名|string|否|无|属性名|表中字段名与属性名相同时不用设置
refName|外键字段名|string|是|无|属性名|
nullable|是否可空|boolean|否|true,false|false|

### @ManyToOne(多对一关系注解)
对属性进行注解，表示该属性为外键关联字段，为“多对一关系”的“多”对应的列，与关联表的**OneToMany**配合使用。
#### 参数
参数为对象，必填，包含以下项：

参数名|说明|类型|必填|可选值|默认值|备注
-|-|-|-|-|-|-
entity|关联实体类名|string|是|无|无|
eager|是否及时获取|boolean|否|无|false|如果为true，则在获取该实体数据时，同时获取关联对象数据

### @OneToMany(一对多关系注解)
对属性进行注解，表示该属性为外键关联字段，为“一对多关系”的“一”对应的列。
#### 参数
参数为对象，必填，包含以下项：

参数名|说明|类型|必填|可选值|默认值|备注
-|-|-|-|-|-|-
entity|关联实体类名|string|是|无|无|
mappedBy|关联实体对应属性名|string|是|无|无|对应关联表**ManyToOne**注解的属性名
onDelete|外键删除策略|string|否|EFkConstraint枚举类型的SETNULL,NONE,CONSTRAINT,CASCADE|NONE|
onUpdate|外键更新策略|string|否|EFkConstraint枚举类型的SETNULL,NONE,CONSTRAINT,CASCADE|NONE|

### @OneToOne(一对一关系注解)
请参考ManyToOne和OneToMany进行设置

## 例子
所有例子代码在[github](https://github.com/relaen/relaents)的test目录下：  
文件说明：  
1. db/test.sql：数据库sql，创建数据库test，把该sql导入；
2. entity：实体所在目录；
3. test.ts：测试代码，先修改**RelaenManager.init**方法内的参数，与自己的数据库匹配；代码最后注释了测试方法，取消对应方法注释；在vscode下，按F5即可执行。

**备注：该环境为源码使用环境，生产环境下（安装relaen包后使用），只需把import里面的".."或"../.."改成“relaen”即可。**

```typescript
import { RelaenManager, getEntityManager, EntityManager, Query, Connection, Transaction,getConnection,NativeQuery } from "..";
import { UserInfo } from "./entity/userinfo";
import { Shop } from "./entity/shop";

/**
 * 与数据库相关的方法都采用async，使用时请使用await 关键字
 * 所用实体需要test数据库的支持，请参考readme
 * entitymanager：实体管理器，用于进行实体操作，通过getEntityManager()方法获取
 * 关于显式和隐式：
 *      getConnection 显式获取数据库连接；
 *      getEntityManager 显示获取entitymanager并隐式创建数据库连接，关闭该entitymanager时，会隐式关闭connection；
 *      基于实体类或实体对象的数据操作，隐式创建entitymanager，也会隐式关闭entitymanager；
 *      凡是显式创建的connection或entitymanager，都需要显式关闭；
 *      当一个方法中存在多个操作时（每次调用save、delete、find属于一个操作），建议显式创建entitymanager，
 *      这样会减少entitymanager和connection的创建次数，同时可使用一级缓存;
 * 何为一级缓存：
 *      一次异步方法(async)内（从最外层首次创建entitymanager开始）的缓存，也称为会话级缓存，用于缓存查询结果，
 *      在该会话内，第二次进行相同查询，不会访问数据库，而是从缓存中获取，参考下列代码中的cache函数；
 *      当执行增删改时，会清空一级缓存
 */

/********************* Relaen 使用例子 *****************/
/**
 * 新增数据
 * 通过实体对象的save方法保存
 */
async function addShop(){
    let shop:Shop = new Shop();
    shop.setShopName('yang的店铺');
    shop.setAddress('四川绵阳');
    //设置外键
    shop.setOwner(new UserInfo(1));
    await shop.save();
}

/**
 * 新增数据
 * 通过entitymanager保存
 */
async function addShop1(){
    let shop:Shop = new Shop();
    shop.setShopName('field的店铺');
    shop.setAddress('四川绵阳');
    //设置外键
    shop.setOwner(new UserInfo(1));
    //显式创建entity manager
    let em:EntityManager = await getEntityManager();
    await em.save(shop);
    //显式创建的entitymanager，必须手动关闭
    await em.close();
}

/**
 * 修改数据
 */
async function updShop(){
    // 此为显式获取entitymanager，在该方法中将使用该entitymanager；
    // 如果去掉这一行，将隐式创建entitymanager，此例子中，将创建两个entitymanager(同时创建两个数据库连接)
    let em:EntityManager = await getEntityManager();
    //采用Shop类find的方法查询
    let shop:Shop = <Shop> await Shop.find(1);
    // 通过entitymanager操作
    // let shop:Shop = <Shop> await em.find(Shop.name,1);  
    shop.setAddress('四川 成都');
    //参数为true，表示仅对地址进行修改，其他项不变，否则，除了shopId和address，其它项都会置为null
    await shop.save(true);
    // 通过entitymanager操作
    // await em.save(shop,true);  
    await em.close();
}

/**
 * 删除数据（通过id）
 */
async function delShop(id){
    //采用Shop类find的方法查询
    await Shop.delete(id);

    //第二种方法
    // shop = <Shop> await Shop.find(id);
    // shop.delete();

    //第三种方法
    // let em:EntityManager = await getEntityManager();
    // shop = <Shop> await Shop.find(id);
    // em.delete(shop);
    // await em.close();
}

/**
 * 删除数据 （通过条件）
 */
async function delShops(){
    //条件对象参考find方法
    let params = {
        shopName:{
            value:'yang',
            rel:'like'
        }
    }
    return await Shop.deleteMany(params);
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // await em.deleteMany(Shop.name,params);
    // await em.close();
}

/**
 * 查询单个实体(通过id)
 * @param id 
 */
async function find(id){
    return await Shop.find(id);
    // 第二种方法
    // let em:EntityManager = await getEntityManager();
    // let shop = await em.find(Shop.name,id);
    // await em.close();
    // return shop;
}

/**
 * 缓存测试
 */
async function cache(id){
    let em:EntityManager = await getEntityManager();
    //控制台会输出sql语句
    let shop:Shop = <Shop> await em.find(Shop.name,id);
    shop.setAddress('北京');
    //控制台不会输出sql语句，从缓存获取
    //shop为之前缓存的内容，address尚未修改
    shop = <Shop> await em.find(Shop.name,id);
    await em.close();
    return shop;
}
/**
 * 懒加载获取关联对象
 * @param id 
 */
async function lazyLoad(id){
    let em:EntityManager = await getEntityManager();
    //获取用户信息
    let ui:UserInfo = <UserInfo> await UserInfo.find(id); 
    //懒加载获取用户，OneToOne对象
    await ui.getUser();
    //懒加载获取拥有的店铺，OneToMany对象
    await ui.getOwnShops();
    console.log(ui);
    await em.close();
}

/**
 * 查询单个实体（通过条件）
 */
async function findOne(){
    //条件使用方式请参考API中EntityManager.findOne的条件参数说明
    //该条件最后结果为 "(owner.realName like '%relaen%' OR owner.age >= 20) AND address='四川绵阳'"
    let params = {
        "owner.realName":{
            value:'relaen',
            rel:'like',
            before:'('
        },
        "owner.age":{
            value:20,
            rel:'>=',
            logic:'OR',
            after:')'
        },
        "address":'四川绵阳'
    }
    return <Shop>await Shop.findOne(params);
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // let shop:Shop = <Shop> await em.findOne(Shop.name,params);
    // await em.close();
    // return shop;
}

/**
 * 查询单个实体（通过条件）
 */
async function findMany(){
    //条件使用方式请参考API中EntityManager.findOne的条件参数说明
    //该条件最后结果为 "(owner.realName like '%relaen%' OR owner.age >= 20) AND address='四川绵阳'"
    let params = {
        "owner.realName":{
            value:'relaen',
            rel:'like',
            before:'('
        },
        "owner.age":{
            value:20,
            rel:'>=',
            logic:'OR',
            after:')'
        },
        "address":'四川绵阳'
    }
    return await Shop.findMany(params);
    
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // let lst = await em.findMany(Shop.name,params);
    // await em.close();
    // return lst;
}

/**
 * 获取记录数
 */
async function getCount(){
    //参考查询的条件对象
    let params = {
        "owner.realName":{
            value:'E',
            rel:'like'
        }
    }

    await Shop.getCount(params);
    //第二种方法
    // let em:EntityManager = await getEntityManager();
    // let count = await em.getCount(Shop.name,params);
    // await em.close();
    // return count;
}

/**
 * 链式查询
 * 通过链式操作拼接查询语句
 */
async function linkQuery(){
    let em:EntityManager = await getEntityManager();
    let query:Query = em.createQuery(Shop.name);
    let r = await query
        // 查询字段，可以获取关联对象属性
        // 查询关联对象，可以直接写关联对象名,如:o1(等价于o1.*);也可以写关联对象相关字段,如:o1.fieldName1
        // 关联对象必须以主表实体开始，且能获取的只能是ManyToOne的One一侧，或OneToOne的带mappedBy属性的一侧
        .select(["*","manager.realName","owner.realName","owner.userId","manager.user.userId","manager.user.userName"])
        // 直接获取关联对象
        // .select(["*","manager","owner.*"]) 
        // 支持实体对象作为查询条件，会自动转换为实体主键
        // 如: manager:1，则会转换成 别名.user_id=1
        // 同理，如果条件值为实体对象，则会直接取实体对象的主键值
        // manager:managerObj,则会转换成 别名.user_id=managerObj.userId
        // 下例中，则会转换为 (manager.user.userName='A' OR manager.userId>1) AND owner.sexy='M'
        .where({//where条件
            "manager.user.userName":{
                value:"A",
                before:'('
            },
            "manager":{
                logic:'OR',
                rel:'>',
                value:new UserInfo(1),
                after:')'
            },
            "owner.sexy":'M'
        })
        .orderBy({//order by
            'owner.userId':'asc',
            "owner.realName":'desc'
        })
        //增加distinct修饰符
        .distinct()
        .getResultList();
    
    await em.close();
    return r;
}

/**
 * 原生查询
 */
async function native(){
    let em:EntityManager = await getEntityManager();
    let sql = "select * from t_shop";
    //创建原生查询对象，第二个参数，会导致查询结果转实体类Shop，如果为空，则不转换
    let query:NativeQuery = em.createNativeQuery(sql,Shop.name);
    // 此操作则不转换为实体对象
    // let query:NativeQuery = em.createNativeQuery(sql);
    //前五条数据，因为传入了实体类名，将会转换成实体对象
    await query.getResultList(0,5);
    await em.close();
}


/**
 * 事务
 * 事务由connection创建，在前面的例子中，connection都是由getEntityManager()隐式创建的，此处需要显式创建
 */
async function doTransaction(){
    //显式获取connection
    let conn:Connection = await getConnection();
    let tx:Transaction = conn.createTransaction();
    await tx.begin();
    //调用删除shop操作
    await delShops();
    //提交操作
    // await tx.commit();
    //回滚，不会删除数据
    await tx.rollback();
    //显式创建的connection，一定要显示关闭
    await conn.close();
}

/**
 * 初始化relaen,请根据自己的数据库进行修改
 */
RelaenManager.init({
    //数据库产品名
    dialect:"mysql",
    //数据库服务器地址
    host:"localhost",
    //端口
    port:3306,
    //用户名
    username:"root",
    //密码
    password:"field",
    //数据库名
    database:"test",
    //连接池，可选
    pool:{
        min:0,
        max:10
    },
    //实体文件配置，为编译后js地址
    entities: [
        "/dist/test/entity/**/*.js"
    ],
    //开启以及缓存
    cache:true,
    //是否调试模式
    debug:true
});

/************* 执行测试方法 ***************/

addShop();
// addShop1();
// updShop();
// delShop(5);
// delShops();
// find(1);
// cache(1);
// lazyLoad(1);
// findOne();
// findMany();
// getCount();
// linkQuery();
// native();
// doTransaction();
```
## 贡献者
姓名|邮箱
-|-
杨雷| fieldyang@163.com
赵率宏|973478129@qq.com
