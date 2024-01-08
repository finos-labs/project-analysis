#UserMapper 少了@Mapper

Owner: ityouknow

Repo: spring-boot-examples

Labels: 

## olddoor (18 Sept 2017)

如题

## ClarifyingYAN (29 Sept 2017)

代码能够执行，但是在 idea 上匹配 mapper 显示 Could not Autowire 的 error 级错误（idea 无法找到该
 bean）
解决方法：在 mapper 中加入 @Repository 

## ityouknow (05 Nov 2017)

再启动类中添加。 idea有时候会误报。

