<!-- Do not edit this file. It is automatically generated by API Documenter. -->

[Home](./index.md) &gt; [relaen](./relaen.md) &gt; [Translator](./relaen.translator.md) &gt; [linkNameMap](./relaen.translator.linknamemap.md)

## Translator.linkNameMap property

链式map

**Signature:**

```typescript
linkNameMap: Map<string, {
        entity: string;
        alias: string;
        co?: unknown;
        from?: string;
    }>;
```

## Remarks


```json
 {
     linkName:{
         entity:实体类名,
         alias:别名,
         co:字段对象,
         from:link名
     }
 }
```
linkName为 实体类名\[\_外键引用名1\_外键引用名2\_...\] 如: Shop\_owner

