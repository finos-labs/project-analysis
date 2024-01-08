#APIJSON的安全问题

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## zhongzilu (13 Nov 2017)

看了大佬的说明和介绍，小弟非常佩服，但仔细想想可能存在安全隐患，只要别人知道你的接口名，就可以获取他想要的内容了，不知道是否有安全机制的加入？

## TommyLemon (13 Nov 2017)

@zhongzilu 
问题很好。
1.APIJSON会对请求的格式进行校验。
2.APIJSON只有GET,HEAD请求才是明文，其它如POST都是非明文，这个和传统方式是一样的。
3.APIJSON会对非GET、HEAD请求的请求方法、结构、内容进行严格校验。
4.APIJSON对Table默认保护不可访问，需要服务端配置允许的请求与结构才能用指定的请求方法与结构访问。
5.APIJSON安全性有多重机制保障，尤其写操作强制要求传id或id{}并对值校验，所以不会像直接写SQL语句那种方式发生一次性误删数据库的问题，甚至连脏数据都很难产生。

因为第3点，所以自动化写操作需要传 tag 来校验结构和内容；
因为第4点，所以某些自动化非开放请求需要传访问角色 @ role 来校验角色权限。

可以参考3.1 操作方法
https://github.com/TommyLemon/APIJSON#3.1

## TommyLemon (13 Nov 2017)

@zhongzilu 
其实你可以试试的：
查询用户开放信息User：（请求成功）
http://apijson.cn:8080/get/{"User":{"id":38710}}

查询用户隐私信息Privacy：（请求失败，无GET权限）
http://apijson.cn:8080/get/{"Privacy":{"id":38710}}

看下源码：
```java
@MethodAccess(
		GET = {},
		GETS = {CIRCLE, OWNER, ADMIN}
		)
public class Privacy {}
```
很明显，get是不允许的，可以用gets，但也必须是CIRCLE, OWNER, ADMIN这3种角色中的一个。

URL: http://apijson.cn:8080/gets/
POST表单：
```
{
    "Privacy": {
        "id": 38710
    },
    "tag": "Privacy"  //不传tag会报错
}
```
仍然失败，因为没登录，未登录是UNKNOWN用户，这里自动补全为OWNER。
```json
{
    "Privacy": {
        "id": 38710,
        "@role": "owner" //如果没传role，服务器会填充默认的，具体看数据库 Request 表
    },
    "code": 407,
    "msg": "未登录，请登录后再操作！"
}
```
那我们能不能伪造一下角色骗过APIJSON呢？试试看：
```json
{
    "Privacy": {
        "id": 38710,
        "@role": "circle"
    },
    "tag": "Privacy"
}
```
还是一样的报错：未登录。
好吧，我登录后再试，新的报错：
```json
{
    "Privacy": {
        "id": 38710,
        "@role": "circle"
    },
    "code": 401,
    "msg": "id = 38710 的 Privacy 不允许 CIRCLE 用户的 GETS 请求！"
}
```
为什么呢？因为我登录的用户id是82001，id=38710的User不在它的圈子内。
那换种角色呢？
```json
{
    "Privacy": {
        "id": 38710,
        "@role": "owner"
    },
    "tag": "Privacy"
}
```
哈！终于攻破了：
```json
{
    "Privacy": {
        "id": 82001,
        "certified": 1,
        "phone": 13000082001,
        "balance": 8067
    },
    "code": 200,
    "msg": "success"
}
```
好像不太对，这id怎么是82001，这不是我刚登录的账号吗？
是的，因为APIJSON解析"@ role": "owner"时已经强制放入了 "id": 82001 ，导致原来的id被替换。

最后再试试 "@ role": "admin" ，仍然报错：
```json
{
    "Privacy": {
        "id": 38710,
        "@role": "admin"
    },
    "code": 406,
    "msg": "角色设置错误！不允许在写操作Request中传 Privacy:{ @role:admin } ！"
}
```
管理员角色是只能在服务器内部设置的，不允许传哦。

APIJSON在线测试：
http://apijson.org

点Star支持下吧^_^
https://github.com/TommyLemon/APIJSON


## zhongzilu (15 Nov 2017)

谢谢你的详细回答，这打消了我的顾虑，同时也不得不佩服大佬的能力，我打算把APIJSON应用到我的实验项目中去，再次说声谢谢，已Start *^_^*

## TommyLemon (15 Nov 2017)

可以的，有什么问题或建议及时反馈哈，还可以加群聊聊
QQ群：607020115

## TommyLemon (03 Jun 2018)

已使用预编译全面防SQL注入，感谢支持^_^
https://github.com/TommyLemon/APIJSON/releases/tag/2.4.0

## Onesimu (12 Feb 2019)

亲请问 post权限检查能不能放开 怎么操作. 内部项目 登录验证都已经做好了 这边只是简单加几个接口 不需要检查权限. 谢谢

## TommyLemon (12 Feb 2019)

@Onesimu [DemoVerifier](https://github.com/TommyLemon/APIJSON/blob/b2bd0aac5f6c2bb7e4de2644bd266118b85b06a3/APIJSON-Java-Server/APIJSONBoot/src/main/java/apijson/demo/server/DemoVerifier.java) 重写 verify 和 verifyLogin 方法，并且都注释掉里面的代码，就不会校验了

## Onesimu (12 Feb 2019)

	@Override
	public void verifyLogin(){}

	@Override
	public boolean verify(SQLConfig config){
		return true;
	}

[DemoVerifier](https://github.com/TommyLemon/APIJSON/blob/b2bd0aac5f6c2bb7e4de2644bd266118b85b06a3/APIJSON-Java-Server/APIJSONBoot/src/main/java/apijson/demo/server/DemoVerifier.java)这样加了还是不行, 提示是 "非开放请求必须是Request表中校验规则允许的操作"！

## TommyLemon (12 Feb 2019)

@Onesimu 那是 数据和结构 校验，对应得在 Request 表里加一条校验规则记录，
如果 DemoParser  重写 onVerifyContent，注释里面的代码即可忽略校验

## TommyLemon (04 Jan 2022)

权限控制原理和使用详细讲解
https://github.com/jerrylususu/apijson_todo_demo/blob/master/FULLTEXT.md#访问控制角色和-access-表

## TommyLemon (12 Mar 2022)

APIJSON 角色访问权限配置

http://apijson.cn/doc/zh/newinterface.html#%E8%A7%92%E8%89%B2%E8%AE%BF%E9%97%AE%E6%9D%83%E9%99%90%E9%85%8D%E7%BD%AE
![image](https://user-images.githubusercontent.com/5738175/158024256-aabce631-43f9-4680-9956-93a1927a1c9d.png)


