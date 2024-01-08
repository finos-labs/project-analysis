#第三篇: 服务消费者（Feign）里启动service-hi 两次，端口分别为8762 、8773

Owner: forezp

Repo: SpringCloudLearning

Labels: 

## addonepiece (17 Apr 2018)

dear 大神。

第三篇: 服务消费者（Feign）
启动service-hi 两次，端口分别为8762 、8773
我这边第二次启动的时候会将上一次启动的服务关闭掉。
(
'EurekaClientApplication' is single-instance run configuration.
Are you sure you want to stop the running one?
)
该怎么解决。

Thanks & Best Regards



## shaochangxing (18 Apr 2018)

idea?https://blog.csdn.net/forezp/article/details/76408139

## addonepiece (19 Apr 2018)

dear 大神。

按大神的设置后，完美解决。

即在IDEA上点击Application右边的下三角 
,弹出选项后，点击Edit Configuration。
然后将默认的Single instance only(单实例)的钩去掉。
应用保存后。就可以启动多实例服务了。


Thanks & Best Regards

