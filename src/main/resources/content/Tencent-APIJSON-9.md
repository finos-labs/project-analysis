#文档中没看到角色权限怎么配置，似乎还没有更新上？

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## CheneyWong (10 Sept 2017)

支持你这种方案，让前端去动态定义接口数据。
我也尝试写过类似的东东，遇到两个问题：
1.  权限部分比较麻烦，我使用定义分组权限的方法，配置不怎么方便。
2.  对多查询的 sql 优化问题，我使用重写字段名和后期整理的方式间接解决，大数据量有问题。（没看到你是怎么解决的）

## TommyLemon (10 Sept 2017)

@CheneyWong 
1.角色权限是通过 @ MethodAccess 注解model(JavaBean)实现的
3步创建APIJSON服务端新表及配置（第2步）
https://my.oschina.net/tommylemon/blog/889074
也可以看看这个
https://github.com/TommyLemon/APIJSON/issues/3

2.多查询是指查询数组/列表这种吗？
 字段可以用
```
"@column":"id,name"
```
这种方式限制。
APIJSON Server Demo做了缓存，重复的对象不会再次查询数据库，而是直接从缓存中取。

## TommyLemon (10 Sept 2017)

文档确实还有部分不完善，我会抽时间做的

## baikeqin (01 Feb 2018)

maven下载不了对应的jar，能否上传到中央仓库中？


## TommyLemon (03 Feb 2018)

@baikeqin 看这个
https://github.com/TommyLemon/APIJSON/issues/19

## TommyLemon (15 Aug 2018)

@CheneyWong 
已支持自动化的 join。
例如
Moment INNER JOIN User LEFT JOIN Comment：
```js
"[]":{
   "join": "&/User/id@,</Comment/momentId@",
   "Moment":{},
   "User":{
     "name~":"t",
     "id@": "/Moment/userId"
   },
   "Comment":{
     "momentId@": "/Moment/id"
   }
}
```
详情见 通用文档/3.2 功能符/数组关键词 中的 join，感谢支持^_^
https://github.com/TommyLemon/APIJSON/blob/master/Document.md#3.2

