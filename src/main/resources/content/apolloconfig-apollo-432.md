#对apollo .net 客户端感兴趣的同志们，在此可以反馈提问题喽！！！

Owner: apolloconfig

Repo: apollo

Labels: 

## reckcn (12 Oct 2016)

对apollo .net 客户端感兴趣的同志们欢迎提供建议 ，再此也多谢谢作者给大家提供这样实用的技术。


## nobodyiam (12 Oct 2016)

@reckcn 感谢支持，我们后面会安排下时间重构下现有的.Net客户端，使其满足开源需求~


## xyting (14 Oct 2016)

support .net core


## yonglehou (06 Nov 2016)

希望尽快开放.net客户端+1


## tcgdy0201 (29 Nov 2016)

有开放.NET client的具体计划嘛？大概何时会提供？

## nobodyiam (30 Nov 2016)

@tcgdy0201 实在不好意思，最近一直在忙于继续开发功能，如灰度发布等。
目前导致无法直接开源的原因主要是因为我们使用了公司内部自己的log类库，不知道.net比较主流的log类库有哪些？类似于Java的slf4j?

## jww0924 (30 Nov 2016)

.NET中类似slf4j的有 [common-logging](https://github.com/net-commons/common-logging) ，也是提供一层facade，可以适配.NET下主流的Log4net、NLog等开源日志库，对.NET Core的支持还处于测试中
有没有可能先简单的去除内部依赖放出一个基本版？

## nobodyiam (30 Nov 2016)

@jww0924 非常感谢提供的信息，我们争取下周能搞一个基本版出来

## nobodyiam (09 Dec 2016)

大家好，我们已经上传了apollo .net客户端的代码，仓库地址为：https://github.com/ctripcorp/apollo.net

另外，我们还整理了一份[.Net客户端使用文档](https://github.com/ctripcorp/apollo/wiki/.Net%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%96%87%E6%A1%A3)供大家参考。

Apollo .Net客户端开源版目前默认会把日志直接输出到Console，大家可以自己实现Logging相关功能。
详见https://github.com/ctripcorp/apollo.net/tree/master/Apollo/Logging/Spi

## YINZHU2016 (19 May 2018)

@reckcn https://github.com/ctripcorp/apollo/issues/1078 可以帮忙看下这个问题吗？你们是如何解决的呢？

