#如何解决慢查询问题？

Owner: Tencent

Repo: APIJSON

Labels: 

## Hugh0529 (01 Feb 2018)

实际开发中可能会遇到慢查询，会需要 RD 优化 sql，如果使用 APIJSON 遇到类似 case，怎么定制化的优化 sql 呢？

## TommyLemon (03 Feb 2018)

目前APIJSON没提供case功能，还没有比较好的方式在APIJSON自动化API里做。
建议用手写SQL语句的方式，或者MyBatis这种ORM库写XML代码。

APIJSON做了以下SQL查询优化：
1.字段限制(可选)
"@column": "id,name" //只查id和name两个字段，不多不少

2.查询缓存：
例如在查询某个用户User的动态Moment列表的一次请求中
```json
{
    "[]": {
        "count": 5,
        "Moment": {
            "@order": "date-"
        },
        "User": {
            "id@": "/Moment/userId"
        }
    }
}
```
相同id的User只会去数据库查一次，之后都是调缓存。

3.查询预判
1）如果同时查 数据 和 数量 (可在[]对象中加query:2实现)，当total <= count*page则不继续查数据库。
2）id和id{}都存在时判断id{}是否包含id，如果不包含则不去查数据库，直接返回空。

## TommyLemon (15 Aug 2018)

@Hugh0529  
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

## TommyLemon (02 Jan 2019)

新增应用层连表 APP JOIN，例如 "join":"@/User/id@"，支持跨不同类型数据库，缓存粒度更细更容易命中
https://github.com/TommyLemon/APIJSON/releases/tag/3.2.0

