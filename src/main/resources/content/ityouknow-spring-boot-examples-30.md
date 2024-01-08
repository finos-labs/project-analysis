#springboot-shiro

Owner: ityouknow

Repo: spring-boot-examples

Labels: 

## tangzulong (10 Jan 2018)

![image](https://user-images.githubusercontent.com/24698497/34766343-0d2e536c-f62f-11e7-88f3-b4c2408878cb.png)
再一次执行登录的时候，这个值获取的是null，请问是在哪里将它set进去的呢？

## zhaozhenzzz (12 Jan 2018)

获取的是null不就代表登陆成功吗？。。看到其他地方有人提示说是Shiro自带的功能会自己提取request里面的username和password字段然后验证，具体可以在验证的那个类中加断点。

