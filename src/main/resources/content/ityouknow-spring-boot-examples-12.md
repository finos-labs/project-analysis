#博主你好，我有一个问题，不明白，想向你请教一下

Owner: ityouknow

Repo: spring-boot-examples

Labels: 

## linzea (25 Oct 2017)

在你的，spring-boot-shiro demo 登录这里，我有一个问题，没想明白。
AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
为什么这个方法在 login 之前执行了。token 里面的，username,password,这个参数又是怎么传进去的呢。我找了一些资料还是没弄明白

## michjony (14 Nov 2017)

打个断点debug到shiro-core.jar包里面的代码，你就知道了

