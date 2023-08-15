<!-- Do not edit this file. It is automatically generated by API Documenter. -->

[Home](./index.md) &gt; [relaen](./relaen.md) &gt; [PostgresProvider](./relaen.postgresprovider.md) &gt; [lock](./relaen.postgresprovider.lock.md)

## PostgresProvider.lock() method

获取加锁sql语句

**Signature:**

```typescript
lock(type: ELockType, tables?: string[], schema?: string): string;
```

## Parameters

|  Parameter | Type | Description |
|  --- | --- | --- |
|  type | [ELockType](./relaen.elocktype.md) | 锁类型 |
|  tables | string\[\] | _(Optional)_ 表名，表锁时使用 |
|  schema | string | _(Optional)_ 模式名，表锁时使用 |

**Returns:**

string

加锁sql语句
