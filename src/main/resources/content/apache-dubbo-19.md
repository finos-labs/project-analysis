#Hessian protocol can not pass RpcContext attachments transparently.

Owner: apache

Repo: dubbo

Labels: 

## MozzieCN (07 Jan 2014)

在客户端使用隐式传参：
RpcContext.getContext().setAttachment("index", "1");
在服务端中的RpcConext的getAttachments() 为空map.

粗略扫过了一遍源码，貌似所有基于http协议的都无法实现隐式传参。

请问是是bug？还是设计如此？还是说有一类协议无法实现?


## diecui1202 (18 May 2018)

I am considering about it. https://github.com/apache/incubator-dubbo/pull/1801

