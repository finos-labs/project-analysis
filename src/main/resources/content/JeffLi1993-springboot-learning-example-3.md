#Could not autowire. No beans of 'CityDao' type found.

Owner: JeffLi1993

Repo: springboot-learning-example

Labels: 

## blue-troy (13 Apr 2017)

你好，我clone了你的项目，发现在idea下 package org.spring.springboot.service.impl 中的 @Autowired
    private CityDao cityDao;
出现了Could not autowire. No beans of 'CityDao' type found. 提示，不知是什么原因。

## JeffLi1993 (16 Apr 2017)

which 子工程？

## blue-troy (17 Apr 2017)

springboot-learning-example/springboot-mybatis/

## JeffLi1993 (20 Apr 2017)

mvn clean install -U .估计没打包好

## squallssck (21 Jul 2017)

是ideal的问题,运行是没有问题的. 但是就是会有红线提示比较讨厌.

## squallssck (21 Jul 2017)

http://www.cnblogs.com/zhangdong92/p/6986653.html
可以看下这里的一个解决方法

## hbbliyong (09 Aug 2017)

你的解决了吗，我也是这个问题，如果解决请告知

## adonis-lau (04 Sept 2017)

感觉是up代码写的有些问题。
在CityDao类加上 @Repository 就好了。

## zhonghuazhi (10 Dec 2017)

我这边运行 执行报错，提示如下：
Description:

Field cityDao in net.cc.restful.service.impl.CityServiceImpl required a bean of type 'net.cc.restful.dao.CityDao' that could not be found.


Action:

Consider defining a bean of type 'net.cc.restful.dao.CityDao' in your configuration.

## JeffLi1993 (08 Apr 2018)

fix

## Applevegetable (02 Aug 2022)

dao层加@Repository

## GitHubXuMing (02 Aug 2022)

邮件已收到祝好                        徐铭

## chenzl0723 (02 Aug 2022)

已收到 ，谢谢

## VitorTao (02 Aug 2022)

来信已收到，非常感谢

## Jokeridea (02 Aug 2022)

这是来自QQ邮箱的假期自动回复邮件。王绅杰 收到，谢谢~

## chenzl0723 (11 Oct 2022)

已收到 ，谢谢

## VitorTao (11 Oct 2022)

来信已收到，非常感谢

