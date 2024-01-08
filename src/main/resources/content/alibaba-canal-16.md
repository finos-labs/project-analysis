#column字段变更信息丢失

Owner: alibaba

Repo: canal

Labels: bug 

## agapple (26 Feb 2013)

在LogEventConvert中处理时column，信息不正确。 
1. isUpdate所有都为true，正确的应该是：根据before和after字进行判断. 
2. sqlType所有都为0，正确应该是：int类型对应于的java.sql.Types


## agapple (26 Feb 2013)

1. 针对isNull字段，没有添加到before/after变更字段列表


