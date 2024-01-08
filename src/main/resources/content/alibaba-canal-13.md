#mysql instance支持group模式

Owner: alibaba

Repo: canal

Labels: feature 

## agapple (20 Feb 2013)

支持多个mysql parse数据合并为一个store输出，进行消费。
典型的业务：数据按水平拆分为16个库后，合并为逻辑的一个canal destination进行消费，客户端不用关心后续16个库的链接情况

需要考虑：
1. 强一致 (group内所有的parse都正常工作，才运行客户端消费数据)
2. 弱一致 (group内只要有一个parse正常工作，就允许客户端消费数据)


## agapple (18 Mar 2013)

目前的解决，只支持强一致，要求所有的parse都正常工作，才允许客户端消费数据。针对弱一致性，建议使用多通道部署的方式来解决


