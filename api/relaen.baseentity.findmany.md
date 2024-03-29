<!-- Do not edit this file. It is automatically generated by API Documenter. -->

[Home](./index.md) &gt; [relaen](./relaen.md) &gt; [BaseEntity](./relaen.baseentity.md) &gt; [findMany](./relaen.baseentity.findmany.md)

## BaseEntity.findMany() method

根据条件查找多个对象

**Signature:**

```typescript
static findMany(params?: object, start?: number, limit?: number, order?: object): Promise<IEntity[]>;
```

## Parameters

|  Parameter | Type | Description |
|  --- | --- | --- |
|  params | object | _(Optional)_ 参数对象，参考EntityManager.findOne方法说明 |
|  start | number | _(Optional)_ 开始记录行 |
|  limit | number | _(Optional)_ 获取记录数 |
|  order | object | _(Optional)_ 排序规则<code>{paramName1:'desc',paramName2:'asc',...}</code>， paramName1:参数名,desc:降序 asc:升序 |

**Returns:**

Promise&lt;[IEntity](./relaen.ientity.md)<!-- -->\[\]&gt;

实体集

