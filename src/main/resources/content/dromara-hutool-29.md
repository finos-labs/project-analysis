#关于Log

Owner: dromara

Repo: hutool

Labels: question 

## drogbababa (12 Jul 2017)

程序中出现[WARN] Can not find [logging.properties], use [%JRE_HOME%/lib/logging.properties] as default!这行警告，请问这个问题如何解决？

## looly (13 Jul 2017)

这是因为你没有引入任何第三方日志框架。Hutool默认会使用JDK Logging做为其日志实现。出现这句话的意思是你没有在你的ClassPath下放logging.properties（JDK Logging的配置文件），系统默认默认读取JDK目录下的默认配置文件。你可以考虑在ClassPath下放一个logging.properties（就是src/main/resources）下。

配置文件模板见：https://github.com/looly/hutool/blob/master/hutool-log/src/test/resources/logging.properties

其它日志框架的配置文件模板见：https://github.com/looly/hutool/tree/master/hutool-log/src/test/resources

## drogbababa (13 Jul 2017)

多谢！！

