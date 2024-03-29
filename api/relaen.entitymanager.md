<!-- Do not edit this file. It is automatically generated by API Documenter. -->

[Home](./index.md) &gt; [relaen](./relaen.md) &gt; [EntityManager](./relaen.entitymanager.md)

## EntityManager class

实体管理器

**Signature:**

```typescript
declare class EntityManager 
```

## Constructors

|  Constructor | Modifiers | Description |
|  --- | --- | --- |
|  [(constructor)(conn, id, isCache)](./relaen.entitymanager._constructor_.md) |  | 构造函数 |

## Properties

|  Property | Modifiers | Type | Description |
|  --- | --- | --- | --- |
|  [connection](./relaen.entitymanager.connection.md) |  | [Connection](./relaen.connection.md) | 连接 |
|  [id](./relaen.entitymanager.id.md) |  | number | 对象id |
|  [isCache](./relaen.entitymanager.iscache.md) |  | boolean | 是否开启缓存 |

## Methods

|  Method | Modifiers | Description |
|  --- | --- | --- |
|  [addToCache(key, value)](./relaen.entitymanager.addtocache.md) |  | 加入cache |
|  [clearCache()](./relaen.entitymanager.clearcache.md) |  | 清除缓存 |
|  [close(force)](./relaen.entitymanager.close.md) |  | 关闭entity manager |
|  [createNativeQuery(sql, entityClassName)](./relaen.entitymanager.createnativequery.md) |  | 创建原生sql查询 |
|  [createQuery(entityClassName)](./relaen.entitymanager.createquery.md) |  | 创建查询对象 |
|  [delete(entity, className)](./relaen.entitymanager.delete.md) |  | 删除实体 |
|  [deleteMany(entityClassName, params)](./relaen.entitymanager.deletemany.md) |  | 删除多个 |
|  [find(entityClassName, id)](./relaen.entitymanager.find.md) |  | 通过id查找实体 |
|  [findMany(entityClassName, params, start, limit, order)](./relaen.entitymanager.findmany.md) |  | 根据条件查找多个对象 |
|  [findOne(entityClassName, params, order)](./relaen.entitymanager.findone.md) |  | 根据条件查找一个对象 |
|  [getCount(entityClassName, params)](./relaen.entitymanager.getcount.md) |  | 获取记录数 |
|  [getFromCache(key)](./relaen.entitymanager.getfromcache.md) |  | 从cache中获取 |
|  [getSelectFields(orm, isField)](./relaen.entitymanager.getselectfields.md) |  | 获取选择字段集 |
|  [save(entity, ignoreUndefinedValue)](./relaen.entitymanager.save.md) |  | 保存新对象 如果状态为new，则执行insert，同时改变为persist，如果为persist，则执行update |

