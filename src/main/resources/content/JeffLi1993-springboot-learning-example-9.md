# java.lang.NoClassDefFoundError: org/apache/juli/logging/LogFactory

Owner: JeffLi1993

Repo: springboot-learning-example

Labels: 

## YanShuiYuan (18 May 2017)

Unable to start embedded container; nested exception is java.lang.NoClassDefFoundError: org/apache/juli/logging/LogFactory

## JeffLi1993 (19 May 2017)

which moudle?

## JeffLi1993 (19 May 2017)

多半构建包没构建成功
try 
`mvn clean install -U`

## YanShuiYuan (19 May 2017)

dubbo-client & dubbo-server
添加
org.apache.tomcat
tomcat-juli
解决

## fireinrain (12 May 2019)

> dubbo-client & dubbo-server
> 添加
> org.apache.tomcat
> tomcat-juli
> 解决

明明是tomcat-util,juli是什么鬼

