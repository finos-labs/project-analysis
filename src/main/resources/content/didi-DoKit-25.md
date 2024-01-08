#能否提供no-op的依赖？

Owner: didi

Repo: DoKit

Labels: enhancement 

## YuYongzhi (27 Dec 2018)

在application初始化时，需要引入代码，由于不建议把功能带到线上，会导致在编译线上包时，若不删除application中的相关代码，则会编译失败。建议提供no-op的依赖，这样就不用修改application中额代码了，即依赖可以写成：
```
debugImplementation 'com.didichuxing.doraemonkit:doraemonkit:1.0.1'
releaseImplementation 'com.didichuxing.doraemonkit:doraemonkit-no-op:1.0.1'
```


## wanglikun7342 (29 Dec 2018)

已经提供，com.didichuxing.doraemonkit:doraemonkit-no-op:1.0.5，jcenter审核需要一天

## pkeropen (09 Jan 2019)

com.didichuxing.doraemonkit:doraemonkit-no-op:1.0.5 , 不能导入依赖. gradle提示找不到对应依赖.

## wanglikun7342 (09 Jan 2019)

jcenter审核出问题了，我们重新提交下


## SheepYang1993 (28 Jan 2019)

> com.didichuxing.doraemonkit:doraemonkit-no-op:1.0.5 , 不能导入依赖. gradle提示找不到对应依赖.

一样的问题

