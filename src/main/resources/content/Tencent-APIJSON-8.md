#看到作者在开源中国的帖子，奈何无法回复，特在此开个issue

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## cnlinjie (10 Aug 2017)

作者想法是非常前卫的，这篇文章是在查 graphql 的资料时看看到的，眼前一亮。
基于几点问题目前还不敢投入产品使用
1.  是SQL的生成，对于大型项目来说，SQL优化是一个重点，用生成的不好把控（所以很多喜欢MyBatis而不喜欢Hibernate）。
2. 这个虽然不是基于传统的API，但是却要基于更底层的数据库文档，数据结构前端也需要参与，当然，这是沟通问题。
3.  对于分布式数据库来说， 有时数据是从不同的服务器取的，这个如果采用这个思路，需要怎么解决跨数据库问题？


另，看到作者回复的：

> 前端的验证是为了减少后端验证，提到响应速度。
比如密码字符、长度、强度如果每次都要后端验证，响应速度慢导致前端体验差。
所以前端验证通过后再交给后端，后端只要做最后一次验证就行了，前面的验证都能无延时响应。
TommyLemon

有一些不同的意见
对后端来说，原则上是不不能相信任何一个来调用接口的，因为不知道是爬虫调用还是客户端调用，都是可以模拟的。所以原则上后端都要验证一次。

## TommyLemon (12 Aug 2017)

@cnlinjie 感谢你的关注。
1.APIJSON对自动生成的SQL语句做了优化，但确实不能在某些场景下做到最优，所以开发效率和运行性能需要使用者自己权衡。建议先用APIJSON实现，反正成本极低。测试过性能后，如果不符合需求就自己优化。
2.可以通过 "@about":true 来查询，而且这个文档是直接用数据库字段自动生成的(而不是人写的独立的文档)，相当稳定不会出错。
3.如果是不同服务器，访问不同的URL就行了。如果是同一个服务器的不同数据库schema，可以用 "@schema":schema 标记。(应该是小写，GitHub把@ 后的内容转成大写了)

最后，确实所有从外面进来的数据都是不可信的，APIJSON支持对请求内容方便地进行各种校验。你说这个例子是误解了这段话，最后一次是指 前端/客户端 不应该把明显有问题的数据提交到服务器，增加服务器压力。例如 用手机号登录，手机号格式是否合法后端虽然也会校验，不合法就返回报错，但前端/客户端在请求前就应该校验，只有所有输入都没有明显错误的前提下才发请求。这时服务器仍然会对手机号格式等校验，但只有最后一次，而不是每次用户点击登录不管对不对都发到服务器去校验。所以前面有明显错误的时候都能“无延时响应”。

## TommyLemon (12 Aug 2017)

@cnlinjie 公司已经在用自动化在线文档了
```
{
	"Comment[]": {
		"Comment": {
			"@about": true
		}
	}
}
```
返回:
```
{
	"Comment[]": [
		{
			"column_name": "id",
			"column_type": "bigint(15)",
			"is_nullable": "NO",
			"column_comment": "唯一标识"
		},
		{
			"column_name": "toId",
			"column_type": "bigint(15)",
			"is_nullable": "YES",
			"column_comment": "回复的评论id"
		},
		{
			"column_name": "userId",
			"column_type": "bigint(15)",
			"is_nullable": "NO",
			"column_comment": "发布评论的用户id"
		},
		{
			"column_name": "momentId",
			"column_type": "bigint(15)",
			"is_nullable": "NO",
			"column_comment": "对应的动态id"
		},
		{
			"column_name": "time",
			"column_type": "timestamp",
			"is_nullable": "YES",
			"column_default": "CURRENT_TIMESTAMP",
			"column_comment": "发布时间"
		},
		{
			"column_name": "content",
			"column_type": "varchar(1000)",
			"is_nullable": "NO",
			"column_comment": "内容"
		}
	],
	"code": 200,
	"msg": "success"
}
```
通过这个数据生成了字段说明表格嘿嘿。


## cnlinjie (09 Sept 2017)

感谢，回复晚了。
我研究下，在个人私单中参与使用，感谢作者。

## TommyLemon (10 Sept 2017)

@cnlinjie 哈哈，这个场景可以的。APIJSON通用接口(get,post,put,delete等)适用范围是 简单的增删改查操作、复杂的查询操作、简单的事务型操作，可以替代80%左右的传统接口(互联网应用一般能到85%-90%，ERP等管理系统会低一些)。APIJSON能很好地兼容传统接口，不适用的接口建议还是用传统方式做，例如APIJSON Server Demo里的注册、登录、验证码等接口就是用传统方式实现的。

## TommyLemon (28 Oct 2017)

@cnlinjie 看看这个，自动生成markdown文档，可展开/收起的带高亮格式化JSON，嘿嘿
http://apijson.cn/

## TommyLemon (15 Aug 2018)

@cnlinjie 
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

## cnlinjie (02 Nov 2018)

收到，感谢哈。

## TommyLemon (22 Apr 2022)

 @cnlinjie 目前最新版 5.0.0：

增强各种功能；腾讯负责人公开称赞；登记万科发起的采筑电商
https://github.com/Tencent/APIJSON/releases/tag/5.0.0

