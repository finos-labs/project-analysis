#count获取分表当中的所有数量有问题

Owner: apache

Repo: shardingsphere

Labels: status: invalid 

## 1214568110 (11 Mar 2016)

分库分表情况  
                       db1:  order_0(记录数33)、order_1(记录数34)、order_2(记录数33)
                       db2:  order_0(记录数0)、order_1(记录数0)、order_2(记录数0)

执行语句：
                select count(1) from order         结果：33
执行语句：
                select count(*) as c from order         结果：100     


## hanahmily (14 Mar 2016)

从问题描述上看与 #11 相似。请使用master的最新版本再测试一次，看情况是否复现？如果还是有问题，请您像 #11 的朋友一样讲日志级别调整为trace，将日志结果贴出来，谢谢。


