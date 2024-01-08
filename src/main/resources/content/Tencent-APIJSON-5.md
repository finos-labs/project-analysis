#如果实现某一个RESTFUL的标准就更好了

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## passionguy (20 Jun 2017)

比如JSON API、或者Graphql。那样客户端就比较多且通用了

## TommyLemon (20 Jun 2017)

目前APIJSON的Java,Android,JavaScript版3个Demo的请求全都是走HTTP RESTful API，只不过传参数由原来的key=value键值对改成了key:value键值对，键值对拼接由
 url?key0=value0&key1=value1&...
 改成了
{key0:value0, key1:value1, ...}
这种JSON格式。具体看对比传统RESTful方式
https://github.com/TommyLemon/APIJSON#2

## TommyLemon (22 Jun 2017)

@passionguy 解决了你的问题了吗？

## TommyLemon (30 Jun 2017)

@passionguy APIJSON在线测试，HTTP REST请求
http://139.196.140.118

## passionguy (30 Jun 2017)

厉害了，我认为文档（标准）优先，这样会有服务端或者客户端的各种实现，跟后端或者前端框架无关。比如http://jsonapi.org，或者http://graphql.org(我也觉得graphql太重)

## TommyLemon (30 Jun 2017)

@passionguy 
jsonapi概念简单，但写法太繁琐，官网给的例子就很麻烦了。
GraphQL只是半自动化，APIJSON是完全自动化
https://ruby-china.org/topics/31997

## mylike (01 Jul 2017)

我认为现在APIJSON已经很好了，新项目使用 很爽。  如果权限管理与查询能分离开会更好一些，这样老项目也快速接入了。

## TommyLemon (03 Jul 2017)

@mylike APIJSON通过 @ MethodAccess 注解Model来添加角色对表的操作权限，耦合度很低。如果权限要和查询(GET,HEAD)分开，可以在Parser#getSQLObject函数中对
AccessVerifier.verify(config, visitor);
这句加一个
if (RequestMethod.isQueryMethod(requestMethod) == false) {
}
判断。
这样就只有写操作才会验证角色及操作权限了

## TommyLemon (28 Oct 2017)

@passionguy @mylike 看看这个，自动生成markdown文档，可展开/收起的带高亮格式化JSON，嘿嘿
http://apijson.org/auto/

## dbsxdbsx (26 Dec 2021)

@TommyLemon , 请教下，本项目“Apijson”是否和“jsonapi”在原理上是差不多的？  我搜到的资源基本都指向“jsonapi”...

## TommyLemon (26 Dec 2021)

> @TommyLemon , 请教下，本项目“Apijson”是否和“jsonapi”在原理上是差不多的？ 我搜到的资源基本都指向“jsonapi”...

很不一样，不是一类项目，JSONAPI 做不了零代码复杂查询
https://github.com/Tencent/APIJSON/wiki


