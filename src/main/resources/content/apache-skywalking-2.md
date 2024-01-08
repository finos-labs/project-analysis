#DataSenderFactory中的DataSenderChecker对于按百分比选择连接，使用更合理的分散算法与策略

Owner: apache

Repo: skywalking

Labels: bug 

## wu-sheng (26 Nov 2015)

百分比会默认选择靠前的连接，会造成集群中的服务器连接数量不均衡。
服务器列表靠前的服务器，连接数过大。


## ascrutae (01 Dec 2015)

在DataSenderChecker初始化的时候添加，添加随机获取服务方法。


## wu-sheng (04 Dec 2015)

DataSenderChecker只在提取的时候随机，未在初始化选取连接时初始化，无法达到负载均衡的效果。


## wu-sheng (04 Dec 2015)

应使用更合理的负载机制，随机切换可用连接，不应该选取后不替换，造成部分服务器压力过大。


## wu-sheng (07 Dec 2015)

新的算法使用独立线程进行连接检测
1.释放不可用连接
2.为连接池新补充连接（连接池配额不足时）
3.连接池正常时，定期替换，保证服务器连接的负载均衡


