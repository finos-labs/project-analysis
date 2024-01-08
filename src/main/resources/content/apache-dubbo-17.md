#Log system flaw?

Owner: apache

Repo: dubbo

Labels: 

## vongosling (06 Nov 2013)

I can not swith log4j to logback.
After exclude log4j dependency and add logback-classic,it reports such warnings;
log4j:WARN No appenders could be found for logger (com.alibaba.dubbo.common.logger.LoggerFactory).

Why not follow the slfj standard , but develop another log module? 


## vongosling (06 Nov 2013)

Recommend the use of slf4j,if someone want to expand some features, such as make a new log wheel,he only has to include 5 class:Logger,ILoggerFactory,StaticLoggerBinder,StaticMarkerBinder and optional StaticMDCBinder class


## wangjingfei (11 Sept 2015)

I've met the same problem. My whole project uses logback for logging, but dubbo's introduing log4j breaks everything down...


## teaey (16 Sept 2015)

We will to consider using slf4j as the standard log framework。


