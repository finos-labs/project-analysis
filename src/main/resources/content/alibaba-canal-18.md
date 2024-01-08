#mysql text中文字符出现乱码

Owner: alibaba

Repo: canal

Labels: bug 

## agapple (27 Feb 2013)

mysql table中字段类型为text时，数据库编码和表编码均为utf-8，canal配置的解析编码为utf-8，解析出来的数据记录为乱码.  

原因分析：
1. mysql binlog中将text/blob类型都记录为LogEvent.MYSQL_TYPE_BLOB
2. canal识别到BLOB信息，无法区分是text还是blob，都按照iso-8859-1进行编码，导致问题的产生

解决：
1.  拿到binlog后，针对BLOB类型，需要反查下table meta信息，获取真实的字段类型，区分出text，然后按照编码进行解析. 


## zhangdeoo (04 Dec 2018)

解决了，要代码的找我啊。861724927

