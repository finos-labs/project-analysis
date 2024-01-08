#基于此提出的一些问题

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## lonecloud (13 Jan 2018)

1. 采用本框架是否会影响到项目性能
2. 由于本框架采用的是直接拼接sql，会不会导致有sql注入的危险？

## TommyLemon (13 Jan 2018)

1.APIJSON做了 预编译、前置主键条件、前置外键条件、前置 AND 条件、字段限制(可选)、读写限流、查询缓存、查询预判(total <= count*page则不继续查数据库；id和id{}都存在时判断id{}是否包含id；检测和预估 ArrayList 初始化容量减少扩容) 等各种优化：
单表查询、不关联的多表查询、非数组内多表关联查询 都比不做优化的传统方式(SELECT *、没有内存缓存等)性能高；数组内表关联查询性能也能达到中等偏下开发者的水平。
但在一些数组与对象多层嵌套的复杂查询下，性能只能达到比初级开发稍高的水平，比不上针对性的优化。
#### 更新：已提供自动化 JOIN ，可在部分场景下显著提升性能

2.APIJSON做了很方便的 角色权限控制 和 对非开放请求的结构及内容校验 等机制，再加上AbstractSQLConfig拼接SQL语句时对WHERE条件的优化，一般的注入方式是过不了安全机制的。
例如 1' OR 1='1 恒成立条件、 -- 注释 都不可能绕过 id 的范围，因为Structure内对id的类型要求是Long，
注入的语句是String，所以会throw IllegalArgumentException。
在AbstractSQLConfig拼接WHERE时，APIJSON会优先把id取出并放在最前面，然后用AND连接后面的条件，最终是 WHERE (id = '1') AND (其它条件) 这样的语句。
即便在 其它条件 里把范围放大至无限大（恒成立表达式等），最终WHERE仍然能控制在id范围内。
其实这还是一种性能优化，因为id是主键，放在最前面能被MySQL引擎使用并加快查询速度。

关于APIJSON的安全性，还可以看看这个ISSUE：
https://github.com/TommyLemon/APIJSON/issues/12


## TommyLemon (13 Jan 2018)

如果对安全要求很高，可以在DemoSQLExecutor使用阿里Druid等数据库连接工具来防SQL注入
https://github.com/alibaba/druid

## TommyLemon (03 Jun 2018)

@lonecloud 已使用预编译全面防SQL注入，感谢支持^_^
https://github.com/TommyLemon/APIJSON/releases/tag/2.4.0

## TommyLemon (15 Aug 2018)

@lonecloud 
已支持自动化的 join。
例如
Moment INNER JOIN User LEFT JOIN Comment：
```js
"[]":{
   "join": "&/User/id@,</Comment/momentId@",
   "Moment":{},
   "User":{
     "name?":"t",
     "id@": "/Moment/userId"
   },
   "Comment":{
     "momentId@": "/Moment/id"
   }
}
```
详情见 通用文档/3.2 功能符/数组关键词 中的 join，感谢支持^_^
https://github.com/TommyLemon/APIJSON/blob/master/Document.md#3.2

## TommyLemon (04 Jun 2019)

有了各种零代码的 JOIN（包括 APP JOIN 全都是零代码）后，比起最上面回答的性能，APIJSON 的查询性能已经提高了一个层级了

## TommyLemon (07 Jan 2022)

腾讯业务百万数据 6s 响应，APIJSON 性能优化背后的故事
https://zhuanlan.zhihu.com/p/447844661

