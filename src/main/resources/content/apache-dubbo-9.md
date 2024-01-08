#AccessLogFilter 产生的日志不完整

Owner: apache

Repo: dubbo

Labels: 

## zhangyijun (28 Jul 2013)

使用ab做测试-n 5000 -c 1，调用无异常，但日志中记录少于实际调用次数，c越小记录越少，不知是存在并发问题还是有意丢弃了？

测试脚本
ab -n 5000 -c 1 http://172.17.20.26:9180/jersey-dubbo/service/consumer/person/bob
消费端测试配置
    <dubbo:protocol name="http" port="9180" server="servlet" contextpath="jersey-dubbo" 
        serialization="json" accesslog="/work/dubbo/ac.log" />


