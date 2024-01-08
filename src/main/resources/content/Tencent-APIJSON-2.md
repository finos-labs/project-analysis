#和GraphQL差不多？

Owner: Tencent

Repo: APIJSON

Labels: 

## zhoujinliang (13 May 2017)



## TommyLemon (13 May 2017)

@zhoujinliang 
和GraphQL确实比较像，尤其是理念一样，都是客户端定制服务端返回的JSON。
但它的概念很多，请求语法复杂，学习成本高，全新的格式需要从客户端到服务端全都支持，代码量很大（graphql-java有100多个Java类，几乎都用于对新格式的封装和解析），迁移风险也很大。
APIJSON的请求用的是JSON这种通用的格式，语法简单，容易上手，只要会JSON就会APIJSON，展示更直观，解析更方便，代码量小（仅20几个Java类），没有中间层，完美兼容传统HTTP接口，几乎无迁移风险（可以逐步迁移，先把未实现的功能用APIJSON去做，以前的接口也不用改）。

但出发点和侧重点不一样：
GraphQL开始是用在JavaScript的，更重前端，Java版本是第三方。
APIJSON是为了满足客户端的复杂UI、需求变更及版本更新而生的，更重客户端，目前服务端和客户端语言用的是Java，前端用JavaScript。
另外APIJSON支持远程函数调用，这个功能我没有在GraphQL上找到。


具体对比下APIJSON Android版和graphal-java的使用，你会发现graphal-java使用非常麻烦，请求必须用它封装的GraphQL#execute方法，很不灵活；appollo-android必须用ApolloClient#newCall，使用同样很麻烦。而APIJSON对请求方式无限制，只要将请求JSON传到服务端就行，返回的也是结构严格对应的JSON。
https://github.com/TommyLemon/APIJSON/tree/master/APIJSON(Android)

APIJSON也有JavaScript版，使用超级简单，几乎就是写JSON，只有少数特殊字符需要encodeURIComponent。你也可以用APIJSON-JS和graphql-js对比。
https://github.com/TommyLemon/APIJSON-JS

## TommyLemon (13 May 2017)

最近在深入了解GraphQL，看了graphql-js源码以及很多博客，graphql并没有减少服务端代码，反而是要写一大堆schema，客户端才能根据schema对应的查询结构来请求，不知道我理解得对不对。
APIJSON反正是不需要用代码去提前定义查询请求的结构的，写model(给客户端用，服务端可不用)及一行权限代码就行，可以看看3步创建APIJSON服务端新表及配置
https://my.oschina.net/tommylemon/blog/889074

另外我按照官方test示例写了查询请求，发现WebStorm并不能将它自动格式化，缩进要手写。APIJSON-JS版请求用的是JSON，可以ctrl+i自动格式化。

## TommyLemon (11 Jul 2017)

GraphQL只是半自动化，APIJSON是完全自动化
https://ruby-china.org/topics/31997

## wanghaisheng (12 Jul 2017)

不知道大神有没有看过
https://github.com/begriffs/postgrest
的接口 也是直接嫁接在表结构之上的 除了强绑定postgresql数据库之外 api上比你这个简洁啊

## TommyLemon (12 Jul 2017)

@wanghaisheng 写起来确实简洁些，像OData，但看不出返回的JSON结构啊，语法也不够直观，后端还要写一大堆接口

gte,lte,neq,...

Horizontal Filtering (Rows):
```
/people?age=gte.18&student=is.true
```

APIJSON:
```
{
    "People":{
        "age>=":18,
        "student":true
    }
}
```

Ordering:
```
/people?order=age.desc,height.asc
```

APIJSON:
```
{
    "People":{
        "@order":"age-,height+"
    }
}
```

还有它怎么定制表关联呢？

## wanghaisheng (13 Jul 2017)

1. 响应格式
```


    */*
    text/csv
    application/json
    application/openapi+json
    application/octet-stream
```
2.后端还要写一大堆接口  和你现在一样的啊  前端直接调APIJSON的get post  这个也是前端直接调  
3.你说的表关联是这个吗
https://postgrest.com/en/v4.1/api.html#resource-embedding
```
GET /films?select=title,directors(last_name) HTTP/1.1
Which would return

[
  { "title": "Workers Leaving The Lumière Factory In Lyon",
    "directors": {
      "last_name": "Lumière"
    }
  },
  { "title": "The Dickson Experimental Sound Film",
    "directors": {
      "last_name": "Dickson"
    }
  },
  { "title": "The Haunted Castle",
    "directors": {
      "last_name": "Méliès"
    }
  }
]


```

## TommyLemon (13 Jul 2017)

@wanghaisheng 
1.我说的是看请求就知道返回结果的JSON结构，和你说的这个 HTTP请求中设置返回数据格式 没有关系。

2.完全不一样哦
传统RESTful及postgrest(应该算传统RESTful增强版)
base_url/film
base_url/people
base_url/actor
base_url/director
base_url/student
...
而用APIJSON就只需要
base_url/get
因为用APIJSON，90%以上的查询请求都用这个，所以公司才实现了一套自动化及可视化查询系统，操作网页UI就能生成请求代码并测试。
如果用传统RESTful API或postgrest，我现在负责的项目，41张表，增删改查+列表 就得
41*5 = 205
个接口了，这还只是单表接口，没算多个表组合嵌套的各种JSON格式。
而用了APIJSON，目前才29个接口(7个APIJSON通用接口+用传统RESTful方式实现的其它复杂业务接口)。

3.不是这样的，我说的是这种自定义关联方式
1)查询一个动态Moment及发布者User
User.id = Moment.userId
```
{
	"Moment": {},
	"User": {
		"id@": "Moment/userId"
	}
}
```

2)查询一个用户User及TA发布的动态Moment数组
Moment.userId = User.id
```
{
	"User": {},
	"[]": {
		"Moment": {
			"userId@": "User/id"
		}
	}
}
```

3)查询一个动态Moment及动态下的评论Comment和评论者User
Comment.momentId = Moment.id, User.id = Comment.userId
```
{
	"Moment": {},
	"[]": {
		"Comment": {
			"momentId@": "Moment/id"
		},
		"User": {
			"id@": "/Comment/userId"
		}
	}
}
```

注：MySQL中应该user_id,moment_id这种用小写+下划线的命名方式，这里因为APIJSON demo数据库字段命名采用了驼峰方式，为了能让你放到APIJSON在线测试网页直接测试，所以还是用了驼峰方式

APIJSON在线测试：
http://apijson.org/auto/

## TommyLemon (13 Jul 2017)

@wanghaisheng 是这7个通用接口
https://github.com/Tencent/APIJSON/blob/master/Document.md#3.1

## wanghaisheng (14 Jul 2017)

@TommyLemon 我试一下吧 
1.咱这个怎么控制权限 那张表只给哪些用户访问  那张表的那些字段只能哪些用户访问
2.如果返回的json 想给表 字段重命名的话怎么操作 
比如库里表叫huanzhexinxi  返回结果我希望是patient
3. 另外就是数据库的支持了 我看你给的例子都是mysql 如果要支持oracle postgresql的话？



## TommyLemon (14 Jul 2017)

@wanghaisheng 
APIJSON已在安全性、功能和性能等各方面都做了大量的完善和优化。

1.角色权限
目前已采用 假定真实、强制匹配 的方式实现了 细分到ROW级别 的 角色及操作 的权限校验。
https://github.com/TommyLemon/APIJSON/releases/tag/1.5.0

APIJSON通过 @ MethodAccess 注解Model来添加角色对表的操作权限:

使用默认角色权限：
```
//1.注册表并添加权限，用默认
@MethodAccess
public class Moment {
}

//2.AccessVerifier内添加权限
accessMap.put(Moment.class.getSimpleName(), getAccessMap(Moment.class.getAnnotation(MethodAccess.class)));
```

使用部分定制角色权限：
```
//1.注册表并添加权限，在默认基础上定制POST和DELETE
@MethodAccess(
    POST = {UNKNOWN, ADMIN}, //POST操作仅支持UNKNOWN和ADMIN用户
    DELETE = {ADMIN}         //DELETE操作仅支持ADMIN用户
)
public class User {
}

//2.AccessVerifier内添加权限
accessMap.put(User.class.getSimpleName(), getAccessMap(User.class.getAnnotation(MethodAccess.class)));
```

另外 前端/客户端 可以在请求 JSON的最外层(统一) 或 里面的表内(单独) 设置 "@ role":ROLE_NAME，来标识来访用户的身份。
后端还可以Request表中用 "ADD":{ "@ role":ROLE_NAME } 来补全 或 "PUT":{ "@ role":ROLE_NAME } 强制指定，这样 前端/客户端 就不用传了。

字段权限原来在Parser#parserResponse用Response表处理，但性能很差就注释了。
如果是不返回的字段，例如密码，可以用 _key 命名，例如Privacy表中的 _password。
另外分表也是对字段进行不同角色权限处理的一种方式，具体见APIJSON的User和Privacy。


2. 字段、表、SQL函数 重命名都可以用 name:alias ，重命名用 具体见功能符中 新建别名
https://github.com/Tencent/APIJSON/blob/master/Document.md#3.2


3.目前demo确实只有MySQL的，通过SQLConfig和SQLExecutor对接MySQL数据库。
好在主流数据库的SQL语法都差不多，改下这两个java文件就能对接其它数据库了。




## TommyLemon (14 Jul 2017)

@ key 中间多加了空格，因为在issue里不加上空格就是@ 别人，@ role 被自动大写为 @ ROLE 了😂

## TommyLemon (28 Oct 2017)

@wanghaisheng 看看这个，自动生成markdown文档，可展开/收起的带高亮格式化JSON，嘿嘿
http://apijson.org/auto/

## g770728y (23 Jan 2019)

用apijson与graphql直接对比, 其实不太合适. 应该对比 apollo client 或 postgraphql 或 prisma

简单看了apijson文档:

## 本质区别: 
apijson更面向数据库表, 而graphql抽象层面更高(偏面向对象, 后端resolver甚至可以结合 DDD)

## 问题域:
apijson: 貌似核心是解决嵌套数据查询需求
graphql 面向复杂前端应用, 不仅仅解决获取嵌套数据的需求, 还包含了前端响应式缓存管理(甚至代替了 redux等框架) + 查询批量化 + 乐观更新 等诸多功能

## 语法层面: 
不得不说, graphql有较高的学习成本.  相比而言, apijson比graphql简单得多

## 前端友好度: 
apijson并未提供前端方案, 完全可以使用mobx / rematch 之类的方案, 适应性广
grapqhl: apollo client提供了比较完备的前端支持, 但该方案 前端缓存管理非常复杂(造成象删除缓存这样的通用需求都难以实现) 
这方面, 见仁见智. 

## 灵活度
apijson面向表, 在满足自动化的基础上, 显然灵活度可以做到更高.
复杂条件查询方面, graphql貌似没有统一的解决方案, 需要靠自己想出解决方案,而apijson这方面就非常强

赞 apijson!

(顺便吐槽一下grapqhl: 前段时间一个网站项目用到graphql, 一些问题其实非常纠结, 象fragment无法删除只能不断增加, 象"price|{}":">300,<1200" 这样的查询 如何定义查询格式. 另外graphql后端其实很简单, 增删改插, 写顺了ctrl+cv. 前端却需要熟悉一堆概念, 尤其问题集在中缓存管理上, 同事不太熟悉缓存, 导致缓存更新上经常遇到问题. )

## TommyLemon (23 Jan 2019)

@g770728y 赞，理解基本都对。
前端友好度 这点，APIJSON 因为使用的是标准的 JSON 格式，
所以前端（客户端）有 fastjson，gson 等一堆成熟好用的 JSON 封装与解析库，
js 等脚本语言甚至原生支持，所以用不着再额外提供库了，
不过对于 Android，APIJSON 还是提供了 JSONReuquest 和 JSONResponse 等类方便使用。

[], User[], User-id[], @position 等非 Java/JavaScript 合法变量名的字段，
前端可以
data["[]"], data["User[]"], data["User-id[]"]，data["@position"]
取值

也可以通过请求参数JSON最外层传
"format":true
来格式化为
list, userList, userIdList, position 等，前端就能
data.list, data.userList, data.userIdList, data.position
取值

即便不用 format，APIJSONORM 里的 JSONResponse.format 也会自动格式化名称。

GraphQL 核心可以说是一个 Gateway 了， APIJSON 核心是 JSON->SQL 的 ORM 库，可以结合使用
https://github.com/AutoGraphQL/AutoGraphQL

## wanghaisheng (23 Jan 2019)

一个突出的是api的聚合 一个突出的是表结构的聚合 

## TommyLemon (11 Feb 2019)

@wanghaisheng 一针见血哈哈

## piboye (19 Apr 2021)

parse-server 支持mongodb,  apijson 会支持mongo不？

## TommyLemon (22 Apr 2021)

> parse-server 支持mongodb, apijson 会支持mongo不？

https://github.com/Tencent/APIJSON/issues/213

## TommyLemon (12 Mar 2022)

**APIJSON 是软件开发行业的 ATM 机，业务员不用做简单常规业务，可以专注于复杂特殊业务(后端开发不用自己做 CRUD，只处理业务逻辑)、用户操作 ATM 机自助存取款转账等大部分常规需求(前端开发传 JSON 参数自助存取各种关联组合嵌套的 JSON 数据)；
GraphQL 就是从老板/销售/用户(前端开发传 Schema 格式请求)接需求，然后分任务(调用 GraphQL resolver)给业务员(后端开发实现各个 resolver，自己做 CRUD 及其它功能)做的产品/项目经理，汇总进度和资料上报回去(GraphQL 整合 resolver 返回值转为 JSON)。**

APIJSON 在 功能、安全、性能、易用性、Java 版生态(继承 JSON 的相关生态) 等都大幅领先 GraphQL。
详细对比 基础功能、权限控制、表关联查询
https://juejin.cn/post/6844903603287621645
![image](https://user-images.githubusercontent.com/5738175/104417236-0451b200-55b0-11eb-84fa-366ee22b1a88.png)


## TommyLemon (02 Jan 2024)

@piboye  apijson-mongodb，NoSQL 数据库 MongoDB 的 APIJSON 插件
https://github.com/APIJSON/apijson-mongodb
<img width="1531" alt="image" src="https://github.com/Tencent/APIJSON/assets/5738175/36406e20-b157-4121-bca7-280491462481">

