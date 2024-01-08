#请教下，dubbo-monitor-simple报错，本地已经package过了

Owner: wuyouzhuguli

Repo: SpringAll

Labels: 

## helloMrZhan (09 Apr 2019)

pom文件报错信息：
Project 'com.alibaba:dubbo-ops:2.0.0' not found less... (Ctrl+F1) 
Inspects a Maven model for resolution problems.
已经执行dubbo官方的package命令但是没有生成这个jar是什么情况呢



## wuyouzhuguli (09 Apr 2019)

可以用IDEA的maven工具打包

## helloMrZhan (09 Apr 2019)

也不行，你是怎么得到这个路径（com\alibaba\dubbo-ops\2.0.0）下的jar的吖

## wuyouzhuguli (09 Apr 2019)

克隆官方的源码吧，https://github.com/apache/incubator-dubbo-admin/tree/master

我那份代码不全，少了parent项目，sorry。

