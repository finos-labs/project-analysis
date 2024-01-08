#project orientatio

Owner: alibaba

Repo: nacos

Labels: 

## HuangSheng (27 Jul 2018)

想知道这个项目在整个微服务体系中的定位是什么？服务发现？还是配置中心？我看好像两者都有，但是不知道重点是什么。我的理解配置中心和服务发现应该侧重点是不同的。还有，配置中心部分我看SDK的代码跟阿里之前开源的diamond很像，不知道是否配置中心就是之前diamond的升级版本？

config management or Registry Center？Relationship with diamond？

## xuechaos (31 Jul 2018)

定位在 更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。是的，不仅仅是二者，每个模块侧重点是不通的，nacos支持可分可和模式，如果体量达到一定程度，注册中心和配置中心可以拆分，是的，diamond是其中一部分，不过版本差异很多，统一在nacos上。
an easy-to-use dynamic service discovery, configuration and service management platform for building cloud native applications. 




