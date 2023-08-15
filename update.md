
# Relaen

## 版本

### 0.3.2
1. 规范代码

### 0.3.1
1. 增加mariadb、sqlite数据库支持；
2. 增加乐观锁与悲观锁机制；
3. 增加主键生成策略table生成；
4. 增加Query查询的groupBy、having方法；
5. 增加实体属性查询是否隐藏属性select；
6. 增加原生连接参数options；
7. 增加文件日志Logger；
8. 增加全表操作属性fullTableOperation，防止全表删除；
9. 增加nativequery原生查询的参数绑定setParameter方法支持字符串绑定；
10. 新增事务隔离级设置setisolationLevel方法；
11. 完善分页语句handleStartAndLimit方法处理。

### 0.3.0
1. 增加oracle、mssql、postgres数据库支持；
2. 增加entity scheme配置项。

### 0.2.2
1. 去掉entity中的__status属性，由EntityManagerFactory统一管理；
2. 查询结果为null或undefined，则该属性不会存在于结果集中，避免出现全部属性为空的对象。

### 0.2.1
1. 给实体类的findMany方法增加orderby参数;
2. 修复insert或update时，相同字段出现多次执行不正确的bug；
3. 修复where条件多个参数且参数值不是对象时的bug；
4. 修复外键且主键情况下，设置外键对象并保存后，主键id错误的bug。

### 0.1.3
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

### 0.1.0
1. 修复EntityManagerFactory.getCurrentEntityManager返回null时的处理；
2. 修复Query.getResultList的参数设置，当start为0时的异常。
3. 增加RelaenManager.init方法传入对象参数；
4. 抛出sql执行异常，便于第三方框架进行异常捕获。
5. 修复update时关联字段的存储bug；
6. 修复Query.getResultList方法 start参数为0时查询结果问题。

















