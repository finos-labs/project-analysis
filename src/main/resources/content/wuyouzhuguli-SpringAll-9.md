#关于36.Spring-Security-ValidateCode 验证码的疑问

Owner: wuyouzhuguli

Repo: SpringAll

Labels: 

## CzyerChen (18 Apr 2019)

想请问一下作者，为什么这边的页面需要放在resources->resources ->login.html，有什么地方指定了路径吗，我放在resources->login.html为什么是404找不到呢

## wuyouzhuguli (18 Apr 2019)

因为src/main/resources/resources路径下的资源是静态资源，可以直接访问，无需Controller跳转。

## CzyerChen (18 Apr 2019)

谢谢，还想问一下一般验证码的实现是就是这样的实现逻辑，还是这个demo只是一个实现验证码功能的简单示例？

## wuyouzhuguli (18 Apr 2019)

大体过程就是这样，可以参考我的项目https://github.com/wuyouzhuguli/FEBS-Shiro，https://github.com/wuyouzhuguli/FEBS-Security

## CzyerChen (18 Apr 2019)

好的，谢谢

## CzyerChen (18 Apr 2019)

还有一个问题想问一下，目前您支持的版本是springboot 1.5.14.RELEASE版本，我使用了2.x版本的spring boot 后login.css加载被重定向了，springboot2.x后版本更新比较大，是对http的支持提升导致静态资源被重定向吗，这一块您有试过吗?
![image](https://user-images.githubusercontent.com/22817918/56334125-aec55700-61c9-11e9-88f4-dbbaaa2c49a8.png)


## wuyouzhuguli (18 Apr 2019)

重定向是因为被拦截了吧，将静态资源配置到免认证路径就好了。

## CzyerChen (18 Apr 2019)

好的，问题解决了，谢谢

