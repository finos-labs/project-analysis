#IDEA打开项目pom一直提示内存溢出

Owner: yudaocode

Repo: SpringBoot-Labs

Labels: question 

## ricekil (10 Mar 2020)

大佬该如何解决阿  请指点一二

## YunaiV (10 Mar 2020)

可能是项目的工程太多了，可以把 <modules /> 注释掉部分项目。

## hzl7652 (21 Apr 2020)

导入前先注释掉所有项目，想看哪个在导入哪个项目

## YunaiV (17 May 2020)

我现在优化了下噢，默认注释掉所有的 module，然后胖友需要哪个打开哪个。

这样一方面能避免 maven 内存溢出，同时也减少依赖的下载。

