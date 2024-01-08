#能不能把系统模块进行拆分『结论：春节肝了 5 天，已经发布』

Owner: YunaiV

Repo: ruoyi-vue-pro

Labels: question 

## qq742464260 (30 Mar 2021)

能不能把系统模块进行拆分，感觉全部弄在一起不是非常好

## YunaiV (02 Apr 2021)

> 能不能把系统模块进行拆分，感觉全部弄在一起不是非常好

这个问题，其实我也纠结过。

对于一个单体应用，按照系统模块拆成 maven 工程，对依赖的处理其实很麻烦。


## qq742464260 (04 Apr 2021)

可拆可合 742464260 ***@***.*** 签名由 网易邮箱大师 定制 在2021年04月02日 19:38，芋道源码 写道： 能不能把系统模块进行拆分，感觉全部弄在一起不是非常好 这个问题，其实我也纠结过。 对于一个单体应用，按照系统模块拆成 maven 工程，对依赖的处理其实很麻烦。 — You are receiving this because you authored the thread. Reply to this email directly, view it on GitHub, or unsubscribe.

## YunaiV (06 Apr 2021)




> 可拆可合 742464260 ***@***.*** 签名由 网易邮箱大师 定制 在2021年04月02日 19:38，芋道源码 写道： 能不能把系统模块进行拆分，感觉全部弄在一起不是非常好 这个问题，其实我也纠结过。 对于一个单体应用，按照系统模块拆成 maven 工程，对依赖的处理其实很麻烦。 — You are receiving this because you authored the thread. Reply to this email directly, view it on GitHub, or unsubscribe.

我琢磨下，怎么做可能合适一点，嘿嘿。

从微服务架构上来说，一般是 nginx =》bff =》rpc service 这样的。
从单体架构上来说，一般是 nginx =》bff 这样的。
如果拆分成多个子 maven module 来说，和微服务架构本质的区别，在于是 local invoke 和 remote invoke 的差别。

如果是基于这个思路，单纯拆出 maven module ，并且这个 module 带 Controller 又不是特别合适。原因是，多个 maven module 是有一些调用关系。

有什么好的建议哇？


## lanemy (13 Apr 2021)

艿艿, 有没有可能改造成DDD实践的项目?

## YunaiV (13 Apr 2021)

> 艿艿, 有没有可能改造成DDD实践的项目?

好建议。ddd 是适合做复杂项目的，ruoyi-vue-pro 的定位是，相对简单的单体项目。

未来会考虑把 onemall 重构出一个 ddd 的版本。等着！

