<!-- Do not edit this file. It is automatically generated by API Documenter. -->

[Home](./index.md) &gt; [relaen](./relaen.md) &gt; [EntityPKey](./relaen.entitypkey.md)

## EntityPKey type

实体主键配置

**Signature:**

```typescript
export declare type EntityPKey = {
    name?: string;
    generator?: "identity" | "sequence" | "table" | "uuid";
    columnName?: string;
    valueName?: string;
    keyName?: string;
    seqName?: string;
};
```