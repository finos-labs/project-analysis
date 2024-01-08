#OR表达式下会出现重复结果问题

Owner: apache

Repo: shardingsphere

Labels: type: bug 

## pingww (02 Mar 2016)

假设有两个分表：tb0, tb1； 按照id字段取模。现在有where查询条件 id = 0 or id = 2，则导致在分表tb0上执行两次这样的条件查询，出现重复结果： <0,2>, <0, 2>


