#不是很懂apiJSON的原理，能讲解一下吗？我想写一个node.js版的apiJSON服务端

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## testsla (04 Aug 2017)



## TommyLemon (04 Aug 2017)

@TEsTsLA  太好了，领导说要转node，但现在看来还要过一段时间。你实现一个node版到时候我可以参考下嘿嘿😜

1.解析请求的JSON结构: 
1）Parser.parseRequest() 将请求字符串用URLDecoder解码(经测试POST请求不需要转码，我会去掉这个多余的过程)，然后还原为对象JSONObject:{}

2）Parser.parseResponse() 整体解析，主要解析数组JSONArray:[]，并将对象JSONObject:{}交给ObjectParser解析

3）ObjectParser.parse() 解析对象JSONObject:{}

2.解析请求的JSON内容: 
1）ObjectParser.parse() 解析键值对key:value、子对象、子数组等，其中子对象和子数组回调给Parser解析，其它键值对交给SQLConfig解析

2）SQLConfig.newSQLConfig() 解析表对象内容Table:{}，getSQL()函数会根据表对象内容生成SQL语句

3.执行SQL语句并返回JSONObject: 
1）SQLExecutor.execute() 通过SQLConfig.getSQL()获得SQL语句
2）SQLExecutor.execute() 通过JDBC连接MySQL数据库，执行SQL语句实现增删改查并返回结果，缓存查询结果

4.原路返回结果并用结果替换原来的请求内容
1）SQLExecutor -> ObjectParser -> Parser

## TommyLemon (04 Aug 2017)

@TEsTsLA 可以用敏捷开发方式，一开始只做核心功能，其它所有功能都不管。等核心功能实现了再加入其它功能。性能一开始也不考虑，先实现后优化。
APIJSON核心功能是查询，查询的核心功能是 Request转SQL 和 "key@":path引用赋值。对引用赋值的解析是难点，也是APIJSON实现自动化的关键。
只要这两个实现了，其它的功能都比较容易加上。

## testsla (06 Aug 2017)

@TommyLemon 周五吃了老板的鱿鱼，所以没回复。
我正在思考如何实现，不懂之处请教。

## TommyLemon (06 Aug 2017)

@TEsTsLA 这样啊，祝你找到满意的工作

## TommyLemon (28 Oct 2017)

@TEsTsLA 看看这个，自动生成markdown文档，可展开/收起的带高亮格式化JSON，嘿嘿
http://apijson.org/auto/

## lgh06 (29 May 2018)

@TEsTsLA  Nodejs现成的SQL builder有 knexjs ，难点在于解析字符串、和APIJSON用同一套规范解析字符串…… 一堆正则 一堆if else等着呢…… 兄弟，搞么？……

## TommyLemon (29 May 2018)

@lgh06  
看了下knex，它是一个面向对象的ORM库，和Java的Hibernate, C#的Linq原理一样。 
```js
// Query both of the rows.
.then(function() {
  return knex('users')
    .join('accounts', 'users.id', 'accounts.user_id')
    .select('users.user_name as user', 'accounts.account_name as account');
})
```
这种调join，select等函数的静态封装方式都做不了APIJSON的JSON转SQL语句这个功能的。
静态封装意味着得后端根据每个接口去具体地手写代码封装SQL，而APIJSON提倡将前端传的JSON完全自动化生成SQL语句，不需要后端针对接口写具体的解析代码。

APIJSON-Java-Server里的Library也是一个ORM库，它通过SQLConfig来动态封装SQL语句。
https://github.com/TommyLemon/APIJSON/blob/master/APIJSON-Java-Server/APIJSONLibrary/src/main/java/zuo/biao/apijson/server/AbstractSQLConfig.java

## lgh06 (01 Jun 2018)

不应该把knexjs归类为ORM，而应该归类为 辅助SQL语句生成的 一个库。   

用Java来定义Model是第一步，必选项……

我现在是非常懒，不想一个一个去定义model，而graphql要求定义model，所以找了找类似的。  

目前来看，参照[parse](https://github.com/parse-community/parse-server)和[leancloud](https://leancloud.cn/docs/rest_api.html)，后端使用mongodb吧……    

（nodejs是个动态类型的语言，用JSON.parse/JSON.stringify就能在json和对象间转换，没必要学java去定义各种model和字段名…… mongodb也不要求必须定义字段类型…… node还是和mongodb配……）

（其实后端逻辑减少了之后，前端的逻辑会相应增加，各种查询的约束条件、列选择，都写在了前端中…… ）


## TommyLemon (01 Jun 2018)

@lgh06 GraphQL可不止是要定义Model（官方叫Type）啊，还要针对每个需求定义Schema，才能按照Schema的结构来请求，灵活性仅仅限于字段和成语接龙式的嵌套（A schema里包含B schema，B schema里包含A schema，就可以写成  
```js
{
  A {
    B {
       A {
         B {
           ...
         }
       } 
    }  
  } 
}
```
这种，但不能是
```js
{ 
  A {
  }
  B {
  } 
}
```
 也不能是 
```js
{  
  A {
  }
  C {
  } 
}
```
等其它任何结构，除非后端再写对应的一大堆Schema。
而且如何查询数据库、如何控制权限还都得后端自己搞定。


APIJSON的开放查询请求不限制结构内容，传任何结构和内容都行，都是APIJSONLibrary这个ORM库完全自动化解析成SQL语句，期间根据@MethodAccess注解配置自动校验权限。
https://www.zhihu.com/question/61648519

1.搜索内容【以…结束】的动态
```js
{
	"[]": {
		"count": 5,
		"page": 0,
		"Moment": {
			"content$": "%…"
		}
	}
}
```

2.搜索内容【以A开始】的动态+【内部】放发布者User
```js
{
	"[]": {
		"count": 5,
		"page": 0,
		"Moment": {
			"content$": "A%",
			"User": {
				"id@": "/userId"
			}
		}
	}
}
```

3.搜索内容【包含A】的动态+【外部】放发布者User
```js
{
	"[]": {
		"count": 5,
		"page": 0,
		"Moment": {
			"content$": "%A%"
		},
		"User": {
			"id@": "/Moment/userId"
		}
	}
}
```

4.每个Moment内比3多了一个评论Comment列表
```js
{
	"[]": {
		"count": 5,
		"page": 0,
		"Moment": {
			"content$": "%A%"
		},
		"User": {
			"id@": "/Moment/userId"
		},
		"[]": {
			"count": 3,
			"Comment": {
				"momentId@": "[]/Moment/id"
			}
		}
	}
}
```

5.将4中的评论列表项Comment去包装（提取一层）
```js
{
	"[]": {
		"count": 5,
		"page": 0,
		"Moment": {
			"content$": "%A%"
		},
		"User": {
			"id@": "/Moment/userId"
		},
		"Comment[]": {
			"count": 3,
			"Comment": {
				"momentId@": "[]/Moment/id"
			}
		}
	}
}
```

6.将5中的User只返回id,sex,name
```js
{
	"[]": {
		"count": 5,
		"page": 0,
		"Moment": {
			"content$": "%A%"
		},
		"User": {
			"@column": "id,sex,name",
			"id@": "/Moment/userId"
		},
		"Comment[]": {
			"count": 3,
			"Comment": {
				"momentId@": "[]/Moment/id"
			}
		}
	}
}
```

APIJSON和GraphQL的初步详细对比
https://juejin.im/post/5ae80edd51882567277433cf

## TommyLemon (01 Jun 2018)

@lgh06  APIJSON目前写model都是为了权限管理，只需要3行代码就能配置一张表的权限。
https://my.oschina.net/tommylemon/blog/889074
如果你不需要自动化的权限控制，可以改下全局配置，那就一行代码都不用写了。


## TommyLemon (01 Jun 2018)

@lgh06 关于前端逻辑增加，这个是定制后端返回的数据内容和结构必然会带来的问题，不过APIJSON提供了一套统一且简单清晰的规范以及丰富的示例：
[设计规范](https://github.com/TommyLemon/APIJSON/blob/master/Document.md#3)

加上APIJSON提倡后端把 测试用例（请求的URL和JSON参数）填好上传到APIJSONAuto接口管理工具
http://apijson.cn/
![apijson_auto_doc](https://user-images.githubusercontent.com/5738175/40827035-0eaef026-65af-11e8-9879-b7cbbace8ea1.jpg)

所以前端直接复制过来就行了。

![apijson_auto_code](https://user-images.githubusercontent.com/5738175/40827126-4c0593d0-65af-11e8-9155-5456b2866f6d.jpg)


Android，iOS客户端可以复制右侧自动生成的代码。
用APIJSON，请求代码就不是问题了。


## lgh06 (01 Jun 2018)

确实牛逼……  
在我综合看了  
> http://apijson.cn/
> https://github.com/TommyLemon/APIJSONAuto   
> 优酷的视频 http://i.youku.com/apijson  
> 掘金的文章 https://juejin.im/post/5ae80edd51882567277433cf  
> 本项目README中的链接  
> 本项目中的Document.md
我好像懂了。  

建议：  
- 没必要写其它语言的后端实现了，维护好Java+MySQL就很牛逼了。  
- 将项目中的pages另存为一份ppt？方便一下windows用户……  
- 将文档写得详细一些，尤其是和graphql/swagger这种功能有重合、类似的，做对比  
- 将文档（wiki、readme.md、document.md、本项目的结构说明、本issue以及其它issue中您贴的博文链接和java文件的作用说明）集中在一个文件中，不要分散在各处。来回跳着看，大神估计可以hold住，小白直接蒙圈了……  
- 详细说明 APIJSONAuto项目、apijson.cn的作用，如何配合才能实现自动生成代码，我第一眼看上去其实是懵逼的（没看优酷视频的情况下）
- 将apijson.cn右侧字体适当缩小，或者限制代码块的高度，增加滚动条，方便总览全篇内容，方便copy代码。    
- 写明目前服务端只支持Java+MySQL

暂时写这么多。
感谢博主指导。

## TommyLemon (02 Jun 2018)

@lgh06 哈哈，非常感谢。

## TommyLemon (20 Jun 2018)

[如何实现其它语言的APIJSON？](
https://github.com/TommyLemon/APIJSON/issues/38)

## yuu2lee4 (19 Jul 2018)

不行 不行 求nodejs实现

## TommyLemon (31 Oct 2018)

@yuu2lee4 APIJSON Node 版已经有了，给热心的开发者点 Star 支持下吧 ^_^
https://github.com/TEsTsLA/apijson

## TommyLemon (15 Sept 2019)

@lgh06 @yuu2lee4 另一个 APIJSON Node 版本也出来了，
支持单表、关联、数组、分页查询等，有比较完善的文档，
我测试过，除了项目提供的表有 utf8 编码问题导入不了 (用我自己的表测试可以），其它都可用。
作者是微医的，已经写了不少测试用例，在他公司内部用起来了。

点 Star 鼓励作者继续完善吧 ^_^
https://github.com/kevinaskin/apijson-node

