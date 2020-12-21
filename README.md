# Relaen
relaen是[noomi](https://www.npmjs.com/package/noomi)团队打造的一套node环境下基于typescript的[ORM](https://baike.baidu.com/item/对象关系映射/311152?fromtitle=ORM&fromid=3583252&fr=aladdin)框架。

## 使用限制
relaen当前仅支持mysql数据库，其它数据库产品陆续加入中。

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
8. 修复外键作为主键时的update sql 不正确的bug。

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
entities|实体js目录|string array|是|无|无|如:["/dist/entity/**/*.js"]
cache|是否开启一级缓存|boolean|否|无|true|
debug|是否为debug模式|boolean|否|无|false|调试模式将在控制台输出每次执行的sql语句
pool|连接池配置|object|否|无|无|如果配置，则开启数据库连接池，连接库配置如下

**连接池配置**  
如果pool为空对象，则max和min使用默认值。
配置项|说明|类型|必填|默认值
-|-|-|-|-|-|-
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
    "username":"root",
    "password":"field",
	"database":"test",
	"pool":{
		"min":0,
		"max":10
	},
    "entities": [
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
generator|主键生成策略|string|否|identity(默认，需要数据库支持自增主键),table(需要在数据库中增加主键表)|identity
table|主键表|string|否|无|无|如果generator为'table'，则该项必填
column|主键生成对应字段名|string|否|无|无|该字段属于主键表，如果generator为'table'，则该项不能为空

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
实体定义，创建了2个实体"User"和"UserType"。  
### 实体类 User

```ts
import { Entity, BaseEntity, Id, Column, ManyToOne, JoinColumn, EntityProxy } from 'relaen';
import {UserType} from './usertype'

@Entity("t_user",'test')
export class User extends BaseEntity{
	@Id()
	@Column({
		name:'user_id',
		type:'int',
		nullable:false
	})
	private userId:number;

	@Column({
		name:'user_name',
		type:'string',
		nullable:false
	})
	private userName:string;

	@Column({
		name:'age',
		type:'int',
		nullable:false
	})
	private age:number;

	@Column({
		name:'sexy',
		type:'string',
		nullable:false
	})
	private sexy:string;

	@ManyToOne({entity:'UserType'})
	@JoinColumn({name:'user_type_id',refName:'user_type_id'})
	private userType:UserType;

	constructor(idValue?:number){
		super();
		this.userId = idValue;
	}
	public getUserId():number{
		return this.userId;
	}
	public setUserId(value:number){
		this.userId = value;
	}

	public getUserName():string{
		return this.userName;
	}
	public setUserName(value:string){
		this.userName = value;
	}

	public getAge():number{
		return this.age;
	}
	public setAge(value:number){
		this.age = value;
	}

	public getSexy():string{
		return this.sexy;
	}
	public setSexy(value:string){
		this.sexy = value;
	}

	public async getUserType():Promise<UserType>{
        //启用代理模式获取关联数据
		return await EntityProxy.get(this,'userType');
	}
	public setUserType(value:UserType){
		this.userType = value;
	}

}
```
### 实体类 UserType

```ts
import { BaseEntity, Entity, Id, Column, OneToMany, EFkConstraint, EntityProxy } from 'relaen';
import {User} from './user'

@Entity("t_user_type",'test')
export class UserType extends BaseEntity{
	@Id()
	@Column({
		name:'user_type_id',
		type:'int',
		nullable:false
	})
	private userTypeId:number;

	@Column({
		name:'user_type_name',
		type:'string',
		nullable:false
	})
	private userTypeName:string;

	@OneToMany({entity:'User',onDelete:EFkConstraint.RESTRICT,onUpdate:EFkConstraint.RESTRICT,mappedBy:'userType'})
	private users:Array<User>;

	constructor(idValue?:number){
		super();
		this.userTypeId = idValue;
	}
	public getUserTypeId():number{
		return this.userTypeId;
	}
	public setUserTypeId(value:number){
		this.userTypeId = value;
	}

	public getUserTypeName():string{
		return this.userTypeName;
	}
	public setUserTypeName(value:string){
		this.userTypeName = value;
	}

	public async getUsers():Promise<Array<User>>{
        //启用代理模式获取关联对象
		return await EntityProxy.get(this,'users');
	}
	public setUsers(value:Array<User>){
		this.users = value;
	}
}
```
### 增删改查
```ts

import { EntityManager,RelaenManager,getConnection,Connection,EntityManagerFactory,Query,NativeQuery} from "relaen";
import { User } from "./entity/user";
import { UserType } from "./entity/usertype";
/**
 * 与数据库相关的方法都采用async，使用时请使用"await"关键字
 * 包括 connection 相关操作 getConnection,connection.close
 * 实体增删改方法 save, delete, 关联关系数据获取(懒加载)
 * 事务方法 begin, commit, rollback
 */
/**
 * 新建用户
 */
async function newUser(){
    //获取连接
    let conn:Connection = await getConnection();
    //创建entity manager
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserName('relaen');
    user.setAge(1);
    user.setSexy('M');
    //设置用户类别
    let userType:UserType = new UserType(1);
    user.setUserType(userType);
    //保存用户数据，必须先创建entitymanager，否则无法执行操作
    await user.save();
    //关闭entitymanager，使用完毕后必须关闭
    em.close();
    //关闭连接，使用完毕后必须关闭
    await conn.close();
}

/**
 * 获取用户信息
 * @param id    用户id
 */
async function getUser(id):Promise<User>{
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let u:User = <User> await em.find(User.name,id);
    //懒加载获取用户类别(多对一)
    await u.getUserType();
    em.close();
    await conn.close();
    return u;
}

/**
 * 获取用户类型
 * @param id    用户类型id
 */
async function getUserType(id):Promise<UserType>{
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let ut:UserType = <UserType> await em.find(UserType.name,id);
    //懒加载获取关联用户(一对多)
    await ut.getUsers();
    em.close();
    await conn.close();
    return ut;
}
/**
 * 更新用户
 * @param id    用户id
 */
async function updateUser(id){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = await getUser(id);
    user.setUserName('aaaa');
    //参数为true，则表示只对不为undefined的值进行更新，否则所有undefined的属性都会更新成null
    await user.save(true);
    em.close();
    await conn.close();
}

/**
 * 删除用户
 * @param id    用户id 
 */
async function deleteUser(id:number){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User(id);
    let r = await em.delete(user);
    em.close();
    await conn.close();
}

/**
 * 查询
 * rql采用对象方式进行书写，执行时需要翻译成原生sql执行，所以执行效率低于原生sql
 */
async function testQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let sql = "select m from  User m where m.userType=? order by m.userId";
    let query:Query = em.createQuery(sql,User.name);
    //设置参数，按照sql中的"?"顺序来，索引从0开始，如果参数值是对象，会提取对象的主键
    query.setParameter(0,1);
    //获取单个对象
    let u:User = <User> await query.getResult();
    //懒加载获取用户类型
    await u.getUserType();

    //提取第5-14记录，如果参数为空，则返回所有记录。第一个参数为记录起始索引号，第二个参数为记录数
    let ul:User[] = <User[]> await query.getResultList(5,10);
    em.close();
    await conn.close();
}

/**
 * 原生查询
 * 原生sql执行效率高于rql，减少了rql翻译成sql的过程
 */
async function testNativeQuery(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let sql = "select * from  t_user";
    let query:NativeQuery = em.createNativeQuery(sql);
    //和Query一样，支持setParameter
    //参数和Query一样，获取前5条数据
    let r = await query.getResultList(0,5);
    em.close();
    await conn.close();
}

/**
 * 事务测试
 */
async function testTransaction(){
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    //创建事务
    let tx = conn.createTransaction();
    //事务开始
    await tx.begin();
    await newUser();
    await deleteUser(4);
    //事务回滚
    await tx.rollback();
    em.close();
    await conn.close();
}
//初始化relaen配置
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
    //实体文件配置
    entities: [
        "/dist/test/entity/**/*.js"
    ],
    //开启以及缓存
    cache:true,
    //是否调试模式
    debug:true
});

newUser();
// getUser(1);
// getUserType(1);
// updateUser(1);
// deleteUser(1);
// testQuery();
// testNativeQuery();
// testTransaction();
```