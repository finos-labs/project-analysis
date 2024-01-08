#[question]cat支持跨服务器的跟踪吗

Owner: dianping

Repo: cat

Labels: 

## baoming (13 Nov 2013)

# 应用场景

url  web请求   (web server 在server 0)
－>  调用服务A  （a 在Server 1）  
－－>  服务A调用服务B (服务B 在server 2)

waterwall里希望能显示这个完整的瀑布流和异常stack,如果有异常的话


## youyong205 (14 Nov 2013)

支持的，比如A调用B，B在调用C，....可以一直看到所有的调用链路的问题。
但是这个需要跟RPC框架进行配合才能做到。

在 2013-11-13 23:12:47，baoming notifications@github.com 写道：

#应用场景

url web请求 (web server 在server 0)
－> 调用服务A （a 在Server 1）

－－> 服务A调用服务B (服务B 在server 2)

waterwall里希望能显示这个完整的瀑布流和异常stack,如果有异常的话

—
Reply to this email directly or view it on GitHub.


## liming430 (30 Dec 2014)

RPC框架怎么配合呢？能具体介绍一下实现细节么？


## herestart (29 Sept 2015)

这个具体要怎么做呢？


