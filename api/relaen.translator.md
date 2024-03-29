<!-- Do not edit this file. It is automatically generated by API Documenter. -->

[Home](./index.md) &gt; [relaen](./relaen.md) &gt; [Translator](./relaen.translator.md)

## Translator class

翻译器

**Signature:**

```typescript
export declare abstract class Translator 
```

## Constructors

|  Constructor | Modifiers | Description |
|  --- | --- | --- |
|  [(constructor)(entityName)](./relaen.translator._constructor_.md) |  | 构造翻译器 |

## Properties

|  Property | Modifiers | Type | Description |
|  --- | --- | --- | --- |
|  [fromTables](./relaen.translator.fromtables.md) | <code>protected</code> | string\[\] | from table 数组 |
|  [groupString](./relaen.translator.groupstring.md) | <code>protected</code> | string | group by string |
|  [havingObject](./relaen.translator.havingobject.md) | <code>protected</code> | \[string, unknown\[\]\] | having条件string |
|  [linkNameMap](./relaen.translator.linknamemap.md) |  | Map&lt;string, { entity: string; alias: string; co?: unknown; from?: string; }&gt; | 链式map |
|  [lockMode](./relaen.translator.lockmode.md) |  | [ELockMode](./relaen.elockmode.md) | lock 模式 |
|  [mainEntityCfg](./relaen.translator.mainentitycfg.md) | <code>protected</code> | [EntityConfig](./relaen.entityconfig.md) | 主实体配置项 |
|  [mainEntityName](./relaen.translator.mainentityname.md) | <code>protected</code> | string | 主实体名 |
|  [modifiers](./relaen.translator.modifiers.md) |  | string\[\] | 修饰符 如distince |
|  [orderString](./relaen.translator.orderstring.md) | <code>protected</code> | string | order by string |
|  [selectedFields](./relaen.translator.selectedfields.md) | <code>protected</code> | string\[\] | 选中字段数组 |
|  [sqlType](./relaen.translator.sqltype.md) |  | [EQueryType](./relaen.equerytype.md) | sql 类型 |
|  [whereObject](./relaen.translator.whereobject.md) | <code>protected</code> | \[string, unknown\[\]\] | where条件string |

## Methods

|  Method | Modifiers | Description |
|  --- | --- | --- |
|  [entityToInsert(entity)](./relaen.translator.entitytoinsert.md) |  | entity转insert sql |
|  [entityToUpdate(entity, ignoreUndefinedValue)](./relaen.translator.entitytoupdate.md) |  | entity转update sql |
|  [getDeleteSql(notNeedAlias)](./relaen.translator.getdeletesql.md) | <code>protected</code> | 生成增删改sql |
|  [getQuerySql()](./relaen.translator.getquerysql.md) |  | 产生查询sql |
|  [getSelectSql()](./relaen.translator.getselectsql.md) | <code>protected</code> | 获取select sql |
|  [handleFrom(arr)](./relaen.translator.handlefrom.md) |  | 处理重复entityName |
|  [handleGroup(params)](./relaen.translator.handlegroup.md) |  | 处理group by |
|  [handleHaving(params)](./relaen.translator.handlehaving.md) |  | 处理having条件 |
|  [handleModifer(modifier)](./relaen.translator.handlemodifer.md) |  | 处理前置修饰符 |
|  [handleOrder(params, entityName)](./relaen.translator.handleorder.md) |  | 处理order by |
|  [handleSelectFields(arr, entityName)](./relaen.translator.handleselectfields.md) |  | 处理select字段集合 |
|  [handleWhere(params)](./relaen.translator.handlewhere.md) |  | 处理where条件 |
|  [toDelete(entity)](./relaen.translator.todelete.md) |  | entity转update sql |

