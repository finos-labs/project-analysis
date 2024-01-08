#请问怎么设置tag

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## DexSinis (13 Oct 2017)

{[]: {…}, code: 406, msg: "请设置tag！一般是Table名"}[]: {count: 3, User: {…}}code: 406msg: "请设置tag！一般是Table名"__proto__: Object


## TommyLemon (14 Oct 2017)

@DexSinis tag一般是最外层的Table名，你这个请求最外层没有Table，设置了也没用。
如果是
```json
{
  "User":{
    ...
  }
}
```
这种，tag就可以为User。
```json
{
  "User":{
    ...
  },
  "tag":"User"
}
```

例如新增一条评论
URL: base_url/post
请求:
```json
{
  "Comment":{
    "userId":82001,
    "momentId":301,
    "content":"test post a comment"
  },
  "tag":"Comment"
}
```
注：新增Comment必须登录后才行哦 -- @ MethodAccess(POST = {LOGIN})

https://github.com/TommyLemon/APIJSON#3.1


## TommyLemon (14 Oct 2017)

@DexSinis 
登录接口示例
https://github.com/TommyLemon/APIJSON-JS/issues/2

## TommyLemon (14 Oct 2017)

或者改下Comment的权限注解
[查看权限配置](https://github.com/TommyLemon/APIJSON/blob/master/APIJSON-Java-Server/APIJSONBoot/src/main/java/apijson/demo/server/model/Comment.java)
```java
@MethodAccess(
  POST = {UNKNOWN, LOGIN}
)
public class Comment {
}
```
这样未登录也能新增评论了

