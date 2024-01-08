#mysql varchar类型处理'\000'字符问题

Owner: alibaba

Repo: canal

Labels: bug 

## agapple (26 Feb 2013)

线上测试遇到一个问题：
a. 业务执行sql插入了一条记录，其中一个字段为：'210012\000\000\000'
b. otter中美同步，更新了这条记录，将字段更新为： '210012'  (去除了\000)
c. canal再一次解析时，发现before和after值相同，没有任何字段发生变更，导致otter同步sql执行失败。

原因分析：
1. dbsync在解析'210012\000\000\000'，等价于'210012'，自动忽略了'\000'请求

代码：
for (; (found < end) && buf[found] != '\0'; found++)

说明： \0为c-style风格的字符串结束符，至于业务执行怎么插入了\000，目前暂未知原因


