#现有Spring项目如何快速切换到apollo配置管理

Owner: apolloconfig

Repo: apollo

Labels: 

## esky-tech (13 Jan 2017)

如题

有没有相关解决方案，介绍，或者示例？

## magicdogs (13 Jan 2017)

目前我们这边的项目使用 springboot 集成了apollo 配置管理，实现的方案其实差不多，主要是 自定义的ApolloConfigApplicationListener implements ApplicationListener<ApplicationEvent>,Ordered 这两个接口，
关注3个事件

![image](https://cloud.githubusercontent.com/assets/22540413/21920880/f27e7c2a-d99e-11e6-9cbc-a5da7003bac4.png)


![image](https://cloud.githubusercontent.com/assets/22540413/21920890/fed43f96-d99e-11e6-8239-bf88c42a6107.png)


apollo client 接入的使用方法，github 上面有对应的 使用说明。

## nobodyiam (13 Jan 2017)

感谢 @magicdogs 提供的使用例子~
@esky-tech Apollo后面会提供原生的实现支持Spring。

## nobodyiam (15 Feb 2017)

@esky-tech Pull Request #543 实现了Apollo支持Spring。

具体使用方式可以参考[Java客户端使用文档](https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97#32-spring%E6%95%B4%E5%90%88%E6%96%B9%E5%BC%8F)

最后再次感谢 @magicdogs 提供的经验分享~

