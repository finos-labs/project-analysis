#支持mysql 5.6吗？

Owner: alibaba

Repo: canal

Labels: feature 

## li1gang11 (18 Mar 2013)

支持mysql 5.6吗？


## agapple (18 Mar 2013)

目前暂不支持mysql5.6，主要是mysql 5.6协议上出现不兼容处理，修改了LogEvent事件type类型，需要升级canal版本解决


## agapple (18 Mar 2013)

计划在v1.0.3支持mysql 5.6协议，目前canal版本release暂定为2周一个周期


## li1gang11 (18 Mar 2013)

感谢agapple的回复，等待您们的新版本
我找了半天也没有找到5.6 binlog的格式文档。你知道在哪里吗？
http://dev.mysql.com/doc/internals/en/binary-log.html ，这个链接是你列出来的，里面的内容应该是5.5版本以下的。


## agapple (20 Mar 2013)

http://dev.mysql.com/doc/refman/5.6/en/binary-log.html


## agapple (08 Apr 2013)

已经支持，commit记录： https://github.com/alibaba/canal/commit/286a8f82916d99d3464ac544e2267397290e7880


## agapple (09 Apr 2013)

mysql5.6协议变化文档：https://github.com/alibaba/canal/wiki/BinlogChange%28mysql5.6%29


