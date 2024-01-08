#运行spring-boot-helloworld的时候，报java.lang.NoClassDefFoundError的错误，但是包是已经引进去的

Owner: ityouknow

Repo: spring-boot-examples

Labels: 

## GodOfWarTang (26 Nov 2017)

运行的时候报错：Caused by: java.lang.NoClassDefFoundError: Could not initialize class org.hibernate.validator.internal.engine.ConfigurationImpl，
不知道和http://www.jianshu.com/p/a33518f4012f 这个问题是不是同一个问题，是的话怎么解决？

## GodOfWarTang (27 Nov 2017)

我在maven上，去掉依赖的那几个包就可以了，只是有点好奇难道只有我一个人遇到这个问题吗

