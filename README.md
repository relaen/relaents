# Relaen

relaen是 [noomi](http://www.noomi.cn/webroute/home) 团队打造的一套node环境下基于typescript的 [ORM](https://baike.baidu.com/item/对象关系映射/311152?fromtitle=ORM&fromid=3583252&fr=aladdin) 框架。支持 Active Record 和 Data Mapper模式，支持实体类操作、创建查询构造器、原生sql、一级缓存和日志文件等。relaen 参考了很多其他优秀的ORM实现，实现更加清晰便捷的开发方式。并且提供实体自动生成工具 relaen-cli，将数据库表快速自动生成对应开发实体模型。

## 使用限制

relaen当前支持 MySQL、Oracle Database 12c +、Microsoft SQL Server 2012 +、PostgreSQL 10+、MariaDB、Sqlite 数据库，其它数据库产品陆续加入中。

## 交流
1. QQ群：926248391；
2. 加入验证问题：who are you，回答：relaen。

## 实体对象生成
relaen提供了[relaen cli](https://www.npmjs.com/package/relaen-cli)工具，该工具可自动生成relaen所需要的实体。

## 文档
### API
http://www.noomi.cn/relaen/api.html

## 版本

#### 0.0.5
正式发布

#### 0.0.6
1. 增加RelaenManager.init方法传入对象参数；
2. 抛出sql执行异常，便于第三方框架进行异常捕获。

#### 0.0.7
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
3. 增加主键表选项生成；
4. 增加Query中条件构建；
5. 增加实体查询属性隐藏配置select；
6. 增加原生连接传递参数options；
7. 增加文件日志记录；
8. 增加防止全表删除属性fullTableOperation；
9. 增加原生查询nativequery字符串绑定参数setParameter方法；
10. 新增事务隔离级设置方法；
11. 完善分页语句判断

# 快速入手

为了方便开发者能快速了解并且使用 Relean，本章节将提供一个简单完整的快速入手例子。

本例子 IDE 使用 vscode 工具，数据库采用 MySQL，运行环境为 node.js。具体步骤如下：

1. 新建数据库

   首先新建名为 relaen 数据库，再新建一个 t_user 的测试表，建表语句如下：

   ```sql
   CREATE TABLE `t_user` (
     `user_id` int(11) NOT NULL AUTO_INCREMENT,
     `user_name` varchar(32) DEFAULT NULL,
     `user_pwd` char(32) DEFAULT NULL,
     PRIMARY KEY (`user_id`)
   )
   ```

2. relaen-cli 生成实体

   由于 Relean 的工作是以 entity 为基础，所以完成数据库建立以后，需要生成数据库中表对应 entity 实体。Relaen 为开发者提供 relaen-cli 工具快速构建数据库中映射的 entity 实体。工具使用步骤如下：

   1. 在指定工程文件夹中，全局下载安装 relaen-cli 工具包；

      ```shell
      # 下载安装relaen-cli工具
      npm install -g relaen-cli
      # 下载安装数据库mysql依赖
      npm install -g mysql
      ```

   2. 执行指令生成 config.json 文件，用于配置数据库连接参数；

      ```shell
      # 构建relaen-cli项目文件
      relaen-cli -i
      ```

   3. 配置数据连接 config.json 文件

      ```json
      {
           "dialect":"mysql",	// 数据库类型
           "options":{
            "host":"localhost",	// 数据库地址
             "port":3306,	// 端口
             "user":"root",	// 数据库用户名
             "password":"root",	// 数据库密码
             "database":"relaen"	// 连接数据库名
            },
            "output":"out",	// 生成实体文件夹
            "tableSplit":"_",	// 数据库表生成实体分界线
            "tableStart":1,	// 数据库表生成实体名起始下标，例如数据库名 t_user 生成 User 实体
            "columnSplit":"_",	// 字段分界线
            "columnStart":0	// 字段名起始下标，例如 user_id 生成 userId 字段
         }
      ```

   4. 执行生成实体指令，在当前工程文件目录生成相应实体类。

      ```shell
      # 配置好数据库连接文件，运行生成实体
      relaen-cli -g
      ```

3. Relaen 项目构建

   将数据库映射实体生成完毕，就可以构建 Relaen 工程项目，下载 relaen 依赖和相关数据库 mysql 依赖，配置相关数据库连接参数，引入生成好的实体。

   1. 新建 Relean 工程文件，下载 relaen 依赖和 mysql 依赖

      ```shell
      # 导入实体文件，下载relaen依赖
      npm install relaen
      # 下载数据库连接所需依赖，如mysql
      npm install mysql
      ```

   2. 创建实体文件夹entity，数据库连接配置文件relaen.json，测试文件test.ts和tsconfig.json

      TODO tsconfig.json 需要target 2015，打开experimentalDecorators，emitDecoratorMetadata

      ```typescript
      // 文件目录
      relaen
      |—— entity
      |—— node_modules
      |—— package-lock.json
      |—— relaen.json
      |—— test.ts
      |—— tsconfig.json
      ```

   3. 将上一步 relaen-cli 工具生成的实体文件拷贝至 relaen 工程 entity 文件夹中，引入实体

   4. 配置数据库连接 relaen.json 配置文件，简单配置如下：

      ```json
      {
          "dialect": "mysql",	//数据库产品名
          "host": "localhost",	//数据库服务器地址
          "port": 3306,	 //端口
          "username": "root",	 //用户名
          "password": "root",	//密码
          "database": "relaen",	//数据库名
          "entities": ["entity/*.ts"],	//实体文件配置，为编译后js地址
          "cache": true,	//开启以及缓存
          "debug": true	 //是否调试模式
      }
      ```

4. 简单案例

   ```typescript
   import { RelaenManager } from "relaen";
   import { Shop } from "./entity/shop";
   
   // 启动Relaen
   RelaenManager.init("relaen.json");
   // 新增店铺方法
   async function addShop() {
       let shop: Shop = new Shop();
       shop.shopName = 'yang的店铺';
       shop.address = '四川绵阳';
       await shop.save();
   }
   
   // 运行
   addShop();
   ```

# 数据库连接

## Relean 通用配置

| 配置项             | 说明                   | 类型              | 必填 | 可选值                                      | 默认值 | 备注                                                         |
| ------------------ | ---------------------- | ----------------- | ---- | ------------------------------------------- | ------ | ------------------------------------------------------------ |
| dialect            | 数据库产品             | string            | 是   | mysql,oracle,mssql，postgres,mariadb,sqlite | 无     | 支持的数据库产品持续更新                                     |
| host               | 数据库服务器地址       | string            | 否   | 无                                          | 无     | 数据库库服务器网址或ip                                       |
| port               | 数据库服务器端口       | number            | 否   | 无                                          | 无     |                                                              |
| username           | 用户名                 | string            | 否   | 无                                          | 无     |                                                              |
| password           | 密码                   | string            | 否   | 无                                          | 无     |                                                              |
| database           | 数据库名               | string            | 否   | 无                                          | 无     | 只支持单数据库                                               |
| entities           | 实体目录               | string \| array   | 否   | 无                                          | 无     | 如：["/dist/entity/**/ *.js"]                                |
| cache              | 是否开启一级缓存       | boolean           | 否   | 无                                          | true   |                                                              |
| debug              | 是否开启debug模式      | boolean           | 否   | 无                                          | false  | 调试模式将在控制台输出每次执行的sql语句                      |
| fileLog            | 是否开启文件日志       | boolean \| object | 否   | 无                                          | false  | 将数据库操作日志记录到文件，true 开启默认文件日志，object 传入自定义appeder |
| fullTableOperation | 是否全表更新与删除     | boolean           | 否   | 无                                          | false  | 默认不能全局更新与删除表数据                                 |
| pool               | 连接池配置             | object            | 否   | 无                                          | 无     | 如果配置，则开启数据库连接池                                 |
| connectTimeout     | 连接超时时间           | number            | 否   | 无                                          | 无     |                                                              |
| idleTimeout        | 连接池空闲连接超时时间 | number            | 否   | 无                                          | 无     |                                                              |
| options            | 原生连接配置           | object            | 否   | 无                                          | 无     | 如果使用数据库原生连接配置，上述host等配置失效               |
| usePool            | 原生连接是否开启连接池 | boolean           | 否   | 无                                          | 无     | 在开启数据库原生连接配置，是否开启连接池                     |

### pool 连接池配置

| 配置项 | 说明           | 类型   | 必填 | 默认值 |
| ------ | -------------- | ------ | ---- | ------ |
| min    | 连接池的最小数 | number | 否   | 无     |
| max    | 连接池的最大数 | number | 否   | 无     |

## mysql/mariadb 可选配置

- ssl `any`：带有 ssl 参数的对象或包含 ssl 配置文件名称的字符串
- charset `string`：连接的字符集（默认值：UTF8_GENERAL_CI）
- timezone `string` ：服务器上配置的时区（默认：local）
- dateStrings `boolean` ：  强制日期类型作为字符串返回
- supportBigNumbers `boolean` ：处理数据库中的大数字
- bigNumberStrings `boolean` ：  同时启用supportBigNumbers和bigNumberStrings会强制将大数字（BIGINT和DECIMAL）作为 JavaScript String 对象返回
- multipleStatements `boolean` ：允许每个查询多个mysql语句。要小心，这可能会增加SQL注入攻击的范围（默认：false）
- flags `string` ：使用非默认连接标志的连接标志

## oracle 可选配置

- privilege `number`：在建立到数据库的连接时使用的特权
- stmtCacheSize `number`：池中每个连接的语句缓存中要缓存的语句数
- tag `string` ：当从连接池获取连接时使用，指示从连接池返回的连接应该具有的标记
- poolIncrement `number` ：当连接请求超过当前打开的连接数时打开的连接数（默认值：1）

## mssql 可选配置

- requestTimeout `number` ：请求超时时间，毫秒为单位 (默认: `15000`)。注意: msnodesqlv8 驱动不支持 timeouts < 1 秒

## postgres 可选配置

- ssl `any` ：带有 ssl 参数的对象，详见 [TLS/SSL](https://node-postgres.com/features/ssl)

## sqlite 可选配置

sqlite 的可选配置为 Relaen 对数据库并发优化配置

- busyErrorRetry `number` ：SQLITE_BUSY 重复执行时间
- busyTimeout `number` ：SQLITE_BUSY 重复执行超时时间

# 实体

实体是一个映射到数据库表的类，是对象和关系数据库之间的一种映射关系。

## 实体类

Relaen 中可以通过定义一个类来创建一个实体，并用 `@Entity` 注解标记来声明实体映射的数据库表信息。实体继承 Relaen 提供 BaseEntity 类，使得实体可以直接使用，实现 Active Record 模式。

```typescript
// 声明 Shop 实体，对应数据库中 t_shop 表
@Entity("t_shop")
export class Shop extends BaseEntity{
    
}
```

@Entity 注解参数如下：

| 参数名  | 类型   | 说明         |
| ------- | ------ | ------------ |
| tblName | string | 表名         |
| schema  | string | 可选，模式名 |

## 实体列

数据库表由字段组成，Relaen 中映射的实体类也由字段映射的属性组成。通过`@Column `注解将表字段映射到实体属性中，通过列注解参数标识，将表字段的具体类型等信息映射到实体属性类型中。

```typescript
@Entity("t_shop")
export class Shop extends BaseEntity{
	@Column({
		name:'shop_name',	// 字段名
		type:'string',		// 类型为string
		nullable:true,		// 允许为空
		length:64			// 长度为64位
	})
	public shopName:string;
}
```

@Column 注解参数如下：

| 参数名   | 类型    | 说明                                        |
| -------- | ------- | ------------------------------------------- |
| name     | string  | 数据库表中字段名                            |
| refName  | string  | 外键字段名 TODO 好像在列注解未使用          |
| type     | string  | 数据类型 包括int double string date object  |
| nullable | boolean | 是否可为空                                  |
| length   | number  | 长度，type为字符串时有效                    |
| identity | boolean | 是否自增，例如：mssq使用增长标识            |
| select   | boolean | 是否查询可见                                |
| version  | boolean | 是否是乐观锁版本号，仅支持number或timestamp |

### 主列

每一个实体必须要有一个主键。在relaen中，可以通过`@Id` 注解来表示该属性对应实体的主键，relaen目前只支持**单主键**。

```typescript
@Entity("t_shop")
export class Shop extends BaseEntity{
	// 主键注解
    @Id()	
	@Column({ 
        name:'shop_id', 
        type:'number', 
        nullable:false, 
        identity:true
    })
	public shopId:number;
    
    constructor(idValue?:number){
        super();
        this.shopId = idValue;
    }
}
```

@Id 注解参数如下：

| 参数名     | 类型   | 说明                                                       |
| ---------- | ------ | ---------------------------------------------------------- |
| generator  | string | 主键生成策略, 可选 identity（默认）, sequence, table, uuid |
| table      | string | 主键生成表，如果generator为'table'，则该项不能为空         |
| columnName | string | 主键键字段名，如果generator为'table'，则该项不能为空       |
| valueName  | string | 主键值字段名，如果generator为'table'，则该项不能为空       |
| keyName    | string | 主键对应记录项名，如果generator为'table'，则该项不能为空   |
| seqName    | string | 主键生成序列名，如果generator为'sequence'，则该项不能为空  |

### 关系列

实体列中一种特殊的属性，配合关系注解使用，标识实体关系中对应的具体列属性，即数据库中外键关系的具体表字段名和属性。使用 @JoinColumn 注解标识实体中的关系列，具体参数如下，案例在实体关系中展示：

@JoinColumn 注解参数

| 参数名   | 类型    | 说明       |
| -------- | ------- | ---------- |
| name     | string  | 字段名     |
| refName  | string  | 外键字段名 |
| nullable | boolean | 是否为空   |

# 关系

Relaen 中实体之间的关系采用常见关系型数据库的规范，主要分为以下类型：

- 一对一关系：@OneToOne

- 一对多关系：@OneToMany

- 多对一关系：@ManyToOne

**备注：**常见多对多关系在 Relean 中使用一对多和多对一进行替代，这样使得关系实体可以更加灵活，方便添加其他额外字段属性。

在 Relaen 中配合上述关系注解，还使用到 @JoinColumn 关系列注解，来详细描述关系中实体对应的列信息。

Relaen 在实体关系中还涉及到关系实体的加载，常见 ORM 使用懒加载属性配置，我们使用实体代理获取关系实体来替代配置懒加载属性，简化配置。

## 一对一

一对一关系是指实体中实例一一对应，例：一个实例 A 只包含另一个实例 B，同理实例 B 也只包含实例 A。

在 Relean 中使用 @OneToOne 注解来标识实体一对一关系，当前实体类 @Entity 注解表示关系依赖对象，@OneToOne 注解中实体表示被依赖对象。

其中配合使用 @JoinColumn 注解表示详细依赖对象和被依赖对象的具体列名，此注解在依赖方对象存在（即数据库表外键引入表）。

```typescript
@Entity("t_user_info")
export class UserInfo extends BaseEntity{
    // 一对一关系注解，User 被依赖实体
	@OneToOne({entity:'User'})	
    // 关系列，name 当前表数据库字段，refName 引用表数据库字段
	@JoinColumn({ 
        name:'USER_ID',
        refName:'USER_ID', 
        nullable:true
    })
	public user:User;
    
    // 代理获取关系对象
	public async getUser():Promise<User>{
		return this['user']?this['user']:await EntityProxy.get(this,'user');
	}
}
```

@OneToOne 注解参数

| 参数名 | 类型   | 说明             |
| ------ | ------ | ---------------- |
| entity | string | 被依赖的实体类名 |

## 一对多/多对一

一对多/多对一是指一个实例包含多个其他实例的关系，例：一个实例 A 包含多个实例 B，相对实例 A 即为一对多，相反实例 B 即为多对一。

在 Relaen 中使用 @OneToMany、@ManyToOne 注解来标识实体一对多、多对一关系。其中 @OneToMany 用于一方，没有 @JoinColumn 引入列；@ManyToOne 用于多方，有 @JoinColumn 引入列。

```typescript
// 一对多
@Entity("t_user_info")
export class UserInfo extends BaseEntity{
    // 一对多关系注解
	@OneToMany({	
		entity:'Shop',
		mappedBy:'userInfoForOwnerId'
	})
	public shopForOwnerIds:Array<Shop>;
	
	public async getShopForOwnerIds():Promise<Array<Shop>>{
		return this['shopForOwnerIds']?this['shopForOwnerIds']:await EntityProxy.get(this,'shopForOwnerIds'); 
	}
}
```

@OneToMany 注解参数：

| 参数     | 类型   | 说明               |
| -------- | ------ | ------------------ |
| entity   | string | 引用实体名         |
| mappedBy | string | 引用实体名中属性名 |

```typescript
// 多对一
@Entity("t_shop")
export class Shop extends BaseEntity{
    // 多对一关系注解
	@ManyToOne({entity:'UserInfo'})	
	@JoinColumn({
        name:'owner_id', 
        refName:'user_id', 
        nullable:true
    })
	public userInfoForOwnerId:UserInfo;
	
	public async getUserInfoForOwnerId(): Promise<UserInfo> {
		return this['userInfoForOwnerId'] ? this['userInfoForOwnerId'] : await EntityProxy.get(this, 'userInfoForOwnerId');
	}
}
```

@ManyToOne 注解参数：

| 参数   | 类型   | 说明         |
| ------ | ------ | ------------ |
| entity | string | 被引用实体名 |

## 多对多

多对多关系是指一类实例包含多个另一类实例，并且也被另一类实例多个包含。例：A 类多个实例包含 B 类多个实例，反之 B 类多个实例也包含多个 A 类实例。

常见其它 ORM 会使用 ManyToMany 来标识多对多关系，但在 Relaen 中采用一对多和多对一来代替多对多关系，并将多对多关系的数据库表映射为实体类，方便开发者更加灵活的创建和设置多对多关系数据库表字段。本章节将具体举例说明，如下：

以`student`和`teacher`实体为例。`student`可以选择多个`teacher`，`teacher`可以有多个`student`。

一般情况下我们创建一个`student_teacher`表，并使用两者的主键作为联合主键，来记录两个实体的多对多关系。

Relaen 中只允许唯一主键，所以不能使用联合主键，需要在`student_teacher`表中单独创建一个主键，然后将`student`和`teacher`中主键作为该表的外键。这样我们可以在该表中对上述字段使用`@OneToMany`、`@ManyToOne`来代替`@ManyToMany`注解。

数据库中的表如下：

```powershell
# student
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| name    | varchar(255) | YES  |     | NULL    |                |
| address | varchar(255) | YES  |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+
```

```powershell
# teacher
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | int(11)      | NO   | PRI | NULL    | auto_increment |
| name        | varchar(255) | YES  |     | NULL    |                |
| course_name | varchar(255) | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
```

```powershell
# student_teacher
+------------+---------+------+-----+---------+----------------+
| Field      | Type    | Null | Key | Default | Extra          |
+------------+---------+------+-----+---------+----------------+
| id         | int(11) | NO   | PRI | NULL    | auto_increment |
| student_id | int(11) | YES  | MUL | NULL    |                |
| teacher_id | int(11) | YES  | MUL | NULL    |                |
+------------+---------+------+-----+---------+----------------+
```

去掉一些必要的形式代码，三个实体的关键代码如下

```typescript
@Entity("student")
export class Student extends BaseEntity{	
    // student 中使用 OneToMany 映射
	@OneToMany({entity:'StudentTeacher',mappedBy:'student'})
	public studentTeachers:Array<StudentTeacher>;

	public async getStudentTeachers():Promise<Array<StudentTeacher>>{
		return this['studentTeachers']?this['studentTeachers']:await EntityProxy.get(this,'studentTeachers');
	}
}
```

```typescript
@Entity("teacher")
export class Teacher extends BaseEntity{
    // student 中使用 OneToMany 映射
	@OneToMany({entity:'StudentTeacher',mappedBy:'teacher'})
	public studentTeachers:Array<StudentTeacher>;
	
	public async getStudentTeachers():Promise<Array<StudentTeacher>>{
		return this['studentTeachers']?this['studentTeachers']:await EntityProxy.get(this,'studentTeachers');
	}
}
S
```

```typescript
@Entity("student_teacher")
export class StudentTeacher extends BaseEntity{
    @Id()
    @Column({ name:'id', type:'int', nullable:false})
    public id:number;
    
    // 对 student 使用 ManyToOne
	@ManyToOne({entity:'Student'})
	@JoinColumn({name:'student_id',refName:'id',nullable:true})
	public student:Student;
	
    // 对 teacher 使用 ManyToOne
	@ManyToOne({entity:'Teacher'})
	@JoinColumn({name:'teacher_id',refName:'id',nullable:true})
	public teacher:Teacher;
    
    public async getStudent():Promise<Student>{
		return this['student']?this['student']:await EntityProxy.get(this,'student');
	}

	public async getTeacher():Promise<Teacher>{
		return this['teacher']?this['teacher']:await EntityProxy.get(this,'teacher');
	}
}
```

至此，我们完成了`@OneToMany`、`@ManyToOne`代替`@ManyToMany`。

# 使用

Relaen 中的实体继承了 BaseEntity 类，使得开发者可以直接对实体使用 save、find、delete 等内置方法简单进行数据库的 CRUD 操作。在 Relaen 中所用实体方法会隐式调用了 EntityManager 中的方法，来创建关闭连接、CRUD操作，也可以显示的创建EntityManager。

对于本文，假定以下实体设置

```typescript
@Entity("t_shop")
export class Shop extends BaseEntity{
	@Id()
	@Column({
		name:'shop_id', type:'int', nullable:false
	})
	public shopId:number;

	@ManyToOne({entity:'UserInfo'})
	@JoinColumn({
		name:'owner_id', refName:'user_id', nullable:true
	})
	public userInfoForOwnerId:UserInfo;

	@ManyToOne({entity:'UserInfo'})
	@JoinColumn({
		name:'manager_id', refName:'user_id', nullable:true
	})
	public userInfoForManagerId:UserInfo;

	@Column({
		name:'shop_name', type:'string', nullable:true, length:32
	})
	public shopName:string;

	@Column({
		name:'address', type:'string', nullable:true, length:128
	})
	public address:string;

	constructor(idValue?:number){
		super();
		this.shopId = idValue;
	}
	public async getUserInfoForOwnerId():Promise<UserInfo>{
		return this['userInfoForOwnerId']?this['userInfoForOwnerId']:await EntityProxy.get(this,'userInfoForOwnerId');
	}
	public async getUserInfoForManagerId():Promise<UserInfo>{
		return this['userInfoForManagerId']?this['userInfoForManagerId']:await EntityProxy.get(this,'userInfoForManagerId');
	}
}
```

## 创建实例

实体是一个类，你可以使用new来创建一个实例。

由于实体 Shop 继承了 BaseEntity 类，你可以使用 save 方法将其保存到数据库中(即持久保存)。

```typescript
let shop:Shop = new Shop();
shop.shopName = 'yang的店铺';
shop.address = '四川绵阳';
// 设置外键
shop.userInfoForOwnerId = new UserInfo(1);   
await shop.save();
```

显示创建 EntityManager 并使用它的 save 方法。显示创建的好处在于获取一次数据库连接，多次执行，减少连接请求次数。

```typescript
let shop:Shop = new Shop();
shop.shopName = 'field的店铺';
shop.address = '四川绵阳';
shop.userInfoForOwnerId = new UserInfo(1); 

// 显式创建 entity manager，并调用 save 方法
let em:EntityManager = await getEntityManager();
await em.save(shop);

// 显式创建的 entitymanager，必须手动关闭
await em.close();
```

## 查询实例

### 根据主键查询 

使用 find 方法，通过主键条件进行查询。

```typescript
// 查询 id 为1的实例
let shop:Shop = <Shop> await Shop.find(1);
```

显示创建 EntityManager 方式。

```typescript
// 查询 id 为1的实例
let em:EntityManager = await getEntityManager();
let shop:Shop = <Shop> await em.find(Shop.name, 1);
await em.close();
```

### 根据条件参数查询

使用对象关系构建查询条件参数，进行数据过滤查询。params 条件对象中的关键字如下：

| 关键字(key) | 可选值(value)                                                |
| ----------- | ------------------------------------------------------------ |
| value       | 属性对应值                                                   |
| rel         | 关系符，= (默认值)、>、 >= 、<、 <=、 !=、[not] like、[not] in、[not] between、regexp |
| logic       | 逻辑关系，AND(默认值)、OR                                    |
| before      | 前置字符串，(                                                |
| after       | 后置字符串， )                                               |

```typescript
// 构造以下参数，查询条件为" (owner.realName like '%relaen%' OR owner.age >= 20) AND address='四川绵阳' "
let params = {
    "userInfoForOwnerId.realName":{
        value:'relaen',
        rel:'like',
        before:'('
    },
    "userInfoForOwnerId.age":{
        value:20,
        rel:'>=',
        logic:'OR',
        after:')'
    },
    "address":'四川绵阳'
}
```

使用上述条件参数查询适用于findOne、findMany方法获取条件查询结果。

- findOne 方法：获取单个条件查询实体

  ```typescript
  let shop:Shop =  <Shop>await Shop.findOne(params);	// 传入查询参数
  ```

  显示创建 EntityManager 方式。

  ```typescript
  let em:EntityManager = await getEntityManager();
  let shop:Shop = <Shop> await em.findOne(Shop.name, params);
  await em.close();
  ```

- findMany 方法：获取多个条件查询结果集实体

  ```typescript
  let shops:Array<Shop> = <Array<Shop>> await Shop.findMany(params);
  ```

  显示创建 EntityManager 方式。

  ```typescript
  let em:EntityManager = await getEntityManager();
  let shop:Shop = <Shop> await em.findMany(Shop.name, params);
  await em.close();
  ```

  findMany 方法的详细参数如下

  | 参数   | 类型   | 说明                                                         |
  | ------ | ------ | ------------------------------------------------------------ |
  | params | object | 可选，条件参数对象                                           |
  | start  | number | 可选，开始记录行                                             |
  | limit  | number | 可选，记录数                                                 |
  | order  | object | 可选，排序规则，如： {paramName1:'desc',paramName2:'asc',...} |

## 更新实例

Relaen 新增和更新实例都采用 save 方法，由框架执行时自动判断当前是新增还是更新操作，使得开发者只需关注实例本身。

```typescript
// 使用 find 方法查询到数据库中id为1的实例
let shop:Shop = <Shop> await Shop.find(1);
// 更新其 address 属性
shop.address = '四川 成都';		 
// 参数为 true，表示仅对 address 进行修改
await shop.save(true);
```

显示创建 EntityManager :

```typescript
let em:EntityManager = await getEntityManager();
let shop:Shop = <Shop> await em.find(Shop.name, 1);
shop.address = '四川 成都';
// 使用 save 方法
await em.save(shop, true);	
await em.close();
```

- save 方法：保存实例数据

  | 参数                 | 类型    | 说明                                    |
  | -------------------- | ------- | --------------------------------------- |
  | ignoreUndefinedValue | boolean | 可选，忽略undefined值，针对update时有效 |
  | lockMode             | string  | 可选，传入 optimistic 开启乐观锁        |

- 在更新情况 save 方法策略
  1. 在使用 find 等 Relaen 内置查询方法，实体状态为持久化，执行 save 方法为更新操作；
  2. 如果使用 new 新建实例对象，Relaen 会对其进行判断：
     - 情况一：实例未设置主键：新增
     - 情况二：实例设置主键，进行数据库对应主键数据检查
       - 数据存在：修改
       - 数据不存在：新增

## 删除实例

### 根据主键删除

可使用实体类或实例对象进行删除操作

```typescript
// 方法一：通过实例对象进行删除
let shop:Shop = <Shop> await Shop.find(1);
await shop.delete();

// 方法二：通过实体类进行删除，传入参数主键id
await Shop.delete(1);
```

显示创建 EntityManager ：

```typescript
let em:EntityManager = await getEntityManager();
// 方法一：传入实例对象删除
let shop:Shop = <Shop> await em.find(Shop.name, 1);
await em.delete(shop);	
// 方法二：传入主键id、实体名
await em.delete(1, Shop.name);		
await em.close();
```

### 根据条件删除

也可以使用 deleteMany 方法进行条件删除相关实体数据，使用条件参数 params 格式如上述条件参数查询详情

```typescript
// 删除以下条件的数据
let params = {
    shopName: {
        value: 'yang',
        rel: 'like'
    }
}
// 通过实体类执行条件删除
await Shop.deleteMany(params);
```

显示创建 EntityManager ：

```typescript
let em:EntityManager = await getEntityManager();
// 传入参数：实体类名，条件参数
await em.deleteMany(Shop.name,params);
await em.close();
```

# 查询

在实际开发中涉及到较多的SQL查询，为了开发者较为灵活方便的查询需求，Relaen 提供 Query 和 NativeQuery 两种查询构造器方便开发者查询使用。其中 Query 为 Relaen 内部封装提供的查询构造方法，即SQL构造器。NativeQuery 为 Relaen 提供开发者执行原生SQL，以便开发者自己需求。本章将对 Relane 中使用 Query 的复杂查询以及 NativeQuery 简单使用进行说明。

## Query 链式查询

Query 提供了多种方法来协助查询数据中的数据，并采用链式方法进行操作。

在Relaen中，通过 `EntityManager` 类创建 Query 对象，并使用其中提供的一些方法，你可以通过组合他们来进行便捷的 SQL 查询（链式查询）。

### 运行查询

Query 提供两种运行查询结果方法：

```typescript
let em:EntityManager = await getEntityManager();
let query:Query = em.createQuery(Shop.name);
let r = await query
		.select("*")
		.getResult();
		// 查询结果集（分页）
		//.getResultList(1,10)
await em.close();
```

- getResult 方法：获取单一查询结果

  | 参数        | 类型    | 说明                                                         |
  | ----------- | ------- | ------------------------------------------------------------ |
  | notEntity ? | boolean | 可选，不返回实体，如果类为true且只有一个属性值，则直接返回属性值，否则返回对象 |

- getResultList 方法：获取多查询结果集（分页）

  | 参数      | 类型    | 说明                   |
  | --------- | ------- | ---------------------- |
  | start     | number  | 可选，开始索引         |
  | limit     | number  | 可选，查询数           |
  | notEntity | boolean | 可选，结果集是否转实体 |

### 查询字段

`select` 限定查询结果集中查询字段，可查询本对象和关联对象的属性（本对象为 createQuery 中传入对象 ）。

- 查询本对象：直接使用属性名，* 为查询本对象全部；
- 查询关联对象：使用关联对象名+关联对象属性名，如 user.realname。单个关联对象默认查询全部属性，如 user 等价 user.*；

```typescript
let em:EntityManager = await getEntityManager();
let query:Query = em.createQuery(Shop.name);
let r = await query
		// * 查询shop全部属性，userInforForManagerId.realName 查询关联对象的属性，userInfoForManagerId.user.username 关联对象可嵌套
		.select(["*","userInfoForManagerId.realName","userInfoForManagerId.user.userName"])
		.getResultList();
await em.close();

// 查询结果如下，结果为Shop对象数组
[
    {
    	shopId:1,
    	address:'四川 成都',
    	shopName:'Relaen的铺子',
    	userInfoForManagerId:{
    		relaName:'NOOMI',
    		user:{
    			userName:'noomi'
    		}
    	}
    }
    //......
]
```

### 条件

`where` 方法用于过滤条件。你可以在 where 方法中传入参数对象 params。具体的参数对象构造请参考上文查询实例中使用参数对象。

```typescript
let em:EntityManager = await getEntityManager();
let query:Query = em.createQuery(Shop.name);
// 条件最后结果为 shopName like '%filed%' and address = '四川 成都'
let params = {
    "shopName":{
        value:'field',
        rel:'like'
    },
    "address":'四川 成都'
};
let r = await query
        .select("*")
        .where(params)
        .getResultList();
await em.close();
```

### 排序

`orederBy` 方法对获取数据进行排序，接受一个形如 { paramName1: 'desc', paramName2: 'asc',...} 的排序对象。其中 `desc` 为降序，`asc` 为升序。

```typescript
let em:EntityManager = await getEntityManager();
let query:Query = em.createQuery(Shop.name);
let r = await query
        .select("*")
        .orderBy({'shopName':'asc'}) // 对 shopName 进行升序排序
        .getResultList();
await em.close();
```

### 去重

`distinct` 方法为查询添加 distinct 关键词，用于返回唯一不同的值。

```typescript
// 查询所有店铺的位置（不重复）
let em:EntityManager = await getEntityManager();
let query:Query = em.createQuery(Shop.name);
let r = await query
        .select("address")
        .distinct() // 添加distinct 关键字
        .getResultList();
await em.close();
```

## NativeQuery 原生查询

Relaen 提供执行原生SQL操作，以满足开发者执行自定义SQL语句或当前数据库的某些特性语句。

NativeQuery 由 EntityManager 中的 `createNativeQuery` 方法创建原生SQL操作对象。该方法接受两个参数，第一个参数为 SQL 语句；第二个参数为实体类名（可选），传入该参数时，会将查询结果转换为实体对象。

```typescript
let em:EntityManager = await getEntityManager();
// sql语句占位可通用 ? 替代，relaen会转换为对应数据库占位符格式；或直接使用当前数据库占位符格式
let sql = "select * from t_shop where shop_id = ?";
// 创建原生查询对象，第二个参数，会将查询结果转实体Shop，如果为空，则不转换
let query:NativeQuery = em.createNativeQuery(sql, Shop.name);
// 设置占位符参数值
query.setParameter(0, 1);
// 前五条数据，因为传入了实体名，将会转换成实体对象
let r = await query.getResultList(0,5);
await em.close();
```

- createNativeQuery 方法：创建原生查询

  | 参数            | 类型   | 说明                               |
  | --------------- | ------ | ---------------------------------- |
  | sql             | string | 原生sql语句                        |
  | entityClassName | string | 可选，查询结果集需要转化的实体类名 |

- setParameter 方法：按照占位符下标设置参数

  | 参数  | 类型   | 说明             |
  | ----- | ------ | ---------------- |
  | index | number | 占位符下标       |
  | value | any    | 对应占位符参数值 |

- setParameters 方法：设置多个参数值，默认下标0开始

  | 参数     | 类型       | 说明                                |
  | -------- | ---------- | ----------------------------------- |
  | valueArr | Array<any> | 参数值数组，默认对应下标0开始占位符 |

# 事务

事务在保证一组 SQL 语句执行的一致性，要么全部成功，要么全部失败。

在 Relaen 中事务由 Connection 中 createTransaction 方法进行显示创建，并通过 `Transaction` 类中的 `begin` 、 `commit` 、 `rollback` 方法来完成事务的`开始`、`提交`、`回滚` 等操作。

```typescript
// 显式获取connection
let conn:Connection = await getConnection();
// 创建事务
let tx:Transaction = conn.createTransaction();
// 开始事务
await tx.begin();

// 调用删除 shop 方法
await delShops();
// 其它SQL操作......

// 成功则提交事务
await tx.commit();
// 出错则回滚事务，不会删除数据
// await tx.rollback();

// 显式创建的connection，一定要显示关闭
await conn.close(); 
```

- begin 方法参数

  | 参数      | 类型   | 说明         |
  | --------- | ------ | ------------ |
  | isolation | string | 事务隔离级别 |

- Mysql, Postgres, Mssql 支持标准隔离级别（READ UNCOMMITTED, READ COMMITTED, REPEATABLE READ, SERIALIZABLE）
- Oracle 仅支持 READ COMMITTED 和 SERIALIZABLE 隔离级别
- Sqlite 数据库默认 SERIALIZABLE，但可使用 immediate 和 exclusive 进行加锁

# 锁机制

为了保证数据访问的排他性，需要通过一些机制来保证数据在某一访问操作时不会被外界其它操作修改。

Relaen 提供乐观锁（Optimistic）和悲观锁（Pessimistic）两种锁机制来保证数据访问时排他性。乐观锁是使用更新语句进行逻辑判断保证数据的排他性，悲观锁是基于数据库的锁机制对访问数据进行加锁保证数据的排他性。

## 乐观锁

乐观锁相对悲观锁采用更加宽松的加锁机制，基于数据版本Version 记录机制实现。即表增加一个数据版本标识字段，每次在更新数据时，对应的数据版本Version 也加1处理，在提交时比对数据版本Version 值是否改变。如Version 值符合，正常更新数据，否则更新失败。

### 使用

1. 实体配置，新增version：true配置，标识该字段为数据版本号

   ```typescript
   @Entity('t_shop')
   export class Shop extends BaseEntity {
     	@Id()
   	@Column({
   		name: 'shop_id',
   		type: 'int',
   		nullable: false,
   		identity: true
   	})
   	public shopId: number;
       
       @Column({
           name: 'shop_name',
           type: 'string',
           nullable: true,
           length: 32
       })
   	public shopName: string;
       
       @Column({
           name: 'version',
           type: 'int',
           nullable: true,
           //标识当前属性为数据版本号
           version: true	
       })
       public version: number;
       
       constructor(idValue?: number) {
   		super();
   		this.shopId = idValue;
   	}
   	public async getUserInfoForOwnerId(): Promise<UserInfo> {
   		return this['userInfoForOwnerId'] ? this['userInfoForOwnerId'] : await EntityProxy.get(this, 'userInfoForOwnerId');
   	}
   	public async getUserInfoForManagerId(): Promise<UserInfo> {
   		return this['userInfoForManagerId'] ? this['userInfoForManagerId'] : await EntityProxy.get(this, 'userInfoForManagerId');
   	}
   }
   ```

2. 在实体 save 方法，第二参数传入 optimistic 参数开启乐观锁

   ```typescript
   let shop: Shop = <Shop>await Shop.find(1);
   shop.shopName = 'frank的店铺修改';
   //开启乐观锁
   return await shop.save(true, 'optimistic');
   ```

## 悲观锁

悲观锁是基于数据库本身的加锁机制实现，真正意义上的加锁，以保证访问时的数据排他性，但会带来性能的开销。Relaen 默认采用行加写锁实现悲观锁，sqlite不支持。

### 使用

```typescript
let conn: Connection = await getConnection();
let tx: Transaction = conn.createTransaction();
//开启事务
await tx.begin();
let em: EntityManager = await getEntityManager();
let query: Query = em.createQuery(Shop.name);
let shop = await query.select('*')
        .where({ shopId: 1 })
        //设置悲观锁：pessimistic 
        .setLock('pessimistic')
        //执行查询加锁
        .getResult();
shop.shopName = '悲观锁测试';
await shop.save(true);
//提交事务，释放锁
await tx.commit();
await em.close();
```

# 日志

Relean 中的日志使用通用的 log4js 日志工具，为了方便初学者使用，Relean 内置两种日志模式：

- debug 调试模式
- fileLog 文件模式

## debug

目的：调试模式是为开发调试程序时使用，通过**控制台**打印 Relaen 执行的 SQL 语句和执行过程帮助检测和定位开发中的问题。

使用：在连接配置参数中 debug 设置为 true，即可开启调试模式（默认为：false）。

## fileLog

目的：文件模式是将框架运行的日志写入指定文件中，便于日常程序运行核对信息。

使用：在连接配置参数中 fileLog 设置 true，即可开启文件日志模式（默认为：false）。默认日志文件位置在工程根目录，文件名为 relaen.log。为了提供开发者自定义日志文件相关，fileLog 可传入 log4js 相关的文件 Appenders，例如：[file](https://log4js-node.github.io/log4js-node/file.html) 和 [datefile](https://log4js-node.github.io/log4js-node/dateFile.html)。