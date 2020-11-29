# Relean
relaen是[noomi](https://www.npmjs.com/package/noomi)团队打造的一套node环境下基于typescript的[ORM](https://baike.baidu.com/item/对象关系映射/311152?fromtitle=ORM&fromid=3583252&fr=aladdin)框架。

## 使用限制
relaen当前仅支持mysql数据库，其它数据库产品陆续加入中。

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

## 注解

### @Entity(实体注解)
对类进行注解，表示该类为一个实体类，被注解类必须继承于BaseEntity。

#### 参数
参数顺序|参数名|类型|必填
-|-|-|-
1|表名称|string|是
2|数据库名称|string|否

### @Id(主键注解)     
对属性进行注解，表示该属性对应Entity的主键，relean只支持**单主键**。

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

## 实体对象生成
relaen提供了[relaen cli](https://www.npmjs.com/package/relaen-cli)工具，该工具可自动生成relaen所需要的实体。

## 版本
### 0.0.2


## API
api请参考[github文档](https://www.github.com/)

## 用例
实体定义，创建了2个实体"User"和"UserType"。  
### 实体类 User

```typescript
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

```typescript
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
```typescript

```
