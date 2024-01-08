#增加对log4j以及log4j2的扩展，支持在日志输出格式增加traceid

Owner: apache

Repo: skywalking

Labels: 

## wu-sheng (21 Dec 2015)

通过log4j配置，能在本地日志中，显示traceid，而不需要应用程序修改代码。
考虑通过log4j的layout扩展来实现。


## wu-sheng (21 Dec 2015)

log4j增强方法：
扩展org.apache.log4j.PatternLayout的setConversionPattern方法，构建新的PatternParser。扩展支持新的格式字符。


## wu-sheng (21 Dec 2015)

log4j2增强方法：
参考此文章：http://www.oschina.net/translate/the-new-log4j-2-0
使用新的packages和plugin，同样需要扩展新的PatternLayout


## ascrutae (31 Dec 2015)

新添加skywalking-log工程，工程包含log4j 1.x和 log4j 2.x的插件，
#log4j1.x配置

``` properties
#需要修改一下两个地方，layout以及%x
log4j.appender.A1.layout=com.ai.cloud.skywalking.plugin.log.log4j.v1.x.TraceIdPatternLayout
log4j.appender.A1.layout.ConversionPattern=[%x] %-d{yyyy-MM-dd HH:mm:ss.SSS} %c %n[%p] %n%m%n
```

#log4j2.x配置
只需添加%tid即可

``` xml
 <PatternLayout  pattern="%d{HH:mm:ss.SSS} [%tid] [%t] %-5level %logger{36} - %msg%n"/>
```


## wu-sheng (02 Jan 2016)

在全局授权没有打开时，应在tid后输出n/a，代替空格，以区别于没有在埋点内的场景。
便于调试


## ascrutae (04 Jan 2016)

 已修复


