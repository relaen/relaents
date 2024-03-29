<!-- Do not edit this file. It is automatically generated by API Documenter. -->

[Home](./index.md) &gt; [relaen](./relaen.md) &gt; [Transaction](./relaen.transaction.md)

## Transaction class

事务基类

**Signature:**

```typescript
declare abstract class Transaction 
```

## Constructors

|  Constructor | Modifiers | Description |
|  --- | --- | --- |
|  [(constructor)(conn)](./relaen.transaction._constructor_.md) |  | 构造器 |

## Properties

|  Property | Modifiers | Type | Description |
|  --- | --- | --- | --- |
|  [conn](./relaen.transaction.conn.md) |  | [Connection](./relaen.connection.md) | 连接对象 |
|  [isolation](./relaen.transaction.isolation.md) |  | [EIsolationLevel](./relaen.eisolationlevel.md) | 事务隔离级别 |
|  [threadId](./relaen.transaction.threadid.md) |  | number | 线程id |

## Methods

|  Method | Modifiers | Description |
|  --- | --- | --- |
|  [begin()](./relaen.transaction.begin.md) |  | 事务开始 |
|  [commit()](./relaen.transaction.commit.md) |  | 事务提交,继承类需要重载 |
|  [rollback()](./relaen.transaction.rollback.md) |  | 事务回滚,继承类需要重载 |
|  [setIsolationLevel(isolationLevel)](./relaen.transaction.setisolationlevel.md) |  | 设置当前事务 |

