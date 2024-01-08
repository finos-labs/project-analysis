#sdk在constant中增加版本号，并改变tid规则

Owner: apache

Repo: skywalking

Labels: bug 

## wu-sheng (11 Jan 2016)

增加version描述，字符串类型
并在tid前加上版本前缀
如现有版本为 1.0a2，tid应变换为 1.0a2.tid。

另外，需要解决tid有.时，web查询无效的bug


## wu-sheng (13 Jan 2016)

1.已修复SDK的相关问题
2.web功能已修复，该问题由springmvc默认处理机制造成。


