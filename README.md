# Relean
relaen是[noomi](https://www.npmjs.com/package/noomi)团队打造的一套node环境下基于typescript的[ORM](https://baike.baidu.com/item/对象关系映射/311152?fromtitle=ORM&fromid=3583252&fr=aladdin)框架。

## 概述

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
generator|主键生成策略|string|否|identity(默认，需要数据库支持自增主键),table(需要在数据库中增加主键表)|identity|
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

## API
api请参考[github文档](https://www.github.com/)

## 用例
实体定义，创建了2个实体"User"和"UserType"。  
### 实体类 User

```typescript
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany,BaseEntity,EFkConstraint } from 'relaen'
import {UserType} from './usertype'

@Entity("t_user",'testdb')
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
    
    //设置了eager=true，则在获取user时，同时获取userType
    @ManyToOne({entity:'UserType',eager:true})
    @JoinColumn({name:'user_type_id',refName:'user_type_id',nullable:true})
    private userType:UserType;
    
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
    
    public getUserType():UserType{
        return this.userType;
    }
    public setUserType(value:UserType){
        this.userType = value;
    }
}
```
### 实体类 UserType

```typescript
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany,BaseEntity,EFkConstraint } from 'relaen'
import {User} from './user'

@Entity("t_user_type",'testdb')
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
    
    //设置了eager=true，则在获取user时，同时获取userType
    @OneToMany({entity:'User',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'userType',eager:false})
    private users:Array<User>;
	
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
    
    public getUsers():Array<User>{
        return this.users;
    }
    public setUsers(value:Array<User>){
        this.users = value;
    }
}
```
### 新增数据
```typescript
    import { EntityManager,getConnection,EntityManagerFactory } from "relaen";
    import {User} from './entity/user';
    import {UserType} from './entity/usertype';
    ...
    //创建连接
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserName('fieldyang');
    let utype:UserType = new UserType();
    utype.setUserTypeId(1);
    user.setUserType(utype);
    await user.persist(em);
    em.close();
    conn.close();
```
### 修改数据
```typescript
    import { EntityManager,getConnection,EntityManagerFactory } from "relaen";
    import {User} from './entity/user';
    
    ...
    //创建连接
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserId(1);
    user.setUserName('yangfield');
    await user.merge(em);
    em.close();
    conn.close();
```
### 删除数据
```typescript
    import { EntityManager,getConnection,EntityManagerFactory } from "relaen";
    import {User} from './entity/user';
    ...
    //创建连接
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let user:User = new User();
    user.setUserId(1);
    await user.delete(em);
    em.close();
    conn.close();
```
### 查询数据
```typescript
    import { EntityManager,getConnection,EntityManagerFactory } from "relaen";
    import {User} from './entity/user';
    import {UserType} from './entity/user';
    ...
    //创建连接
    let conn:Connection = await getConnection();
    let em:EntityManager = EntityManagerFactory.createEntityManager(conn);
    let sql = "select m from  User m where m.userType=? order by m.userId";
    let query = em.createQuery(sql,Agent);
    let utype = new UserType();
    utype.setUserTypeId(1);
    query.setParameter(0,utype);
    query.getResultList().then(r=>{
        console.log(r);
    });
    em.close();
    conn.close();
```
