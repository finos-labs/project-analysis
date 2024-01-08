#dubbo埋点的类名和方法名之间缺少分隔符

Owner: apache

Repo: skywalking

Labels: bug 

## wu-sheng (01 Dec 2015)

当前展示信息：
dubbo://192.168.1.114:10880/com.ai.cloud.skywalking.example.resource.dubbo.interfaces.checker.IResourceCheckcheckResource(ResourceInfo)
应为：
IResourceCheck.checkResource


## ascrutae (01 Dec 2015)

在SWDubboEnhanceFilter类中拼接Viewpoint添加分割符.


