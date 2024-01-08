#apolloclient在maven仓库？

Owner: apolloconfig

Repo: apollo

Labels: 

## galenzhao (20 Oct 2016)

我在中央仓库没搜到啊


## nobodyiam (20 Oct 2016)

@galenzhao apollo-client打包的时候会注入meta server的地址信息，所以是需要使用者自己打包的。
后面我们会完善[分布式部署指南](https://github.com/ctripcorp/apollo/wiki/%E5%88%86%E5%B8%83%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%8C%87%E5%8D%97)，列出需要的打包和部署步骤。

对于[Quick Start](https://github.com/ctripcorp/apollo/wiki/Quick-Start)中的例子，客户端可以通过以下maven命令来install到本地：

``` sh
mvn clean install -pl apollo-client -am -DskipTests -Ddev_meta=http://localhost:8080
```


## galenzhao (20 Oct 2016)

@nobodyiam 注入meta server信息？
是说 apollo的server信息吧，
这些配置为什么不从配置文件读取 而是要在打包的时候呢
从配置文件来做更方便client开发人员吧


## nobodyiam (20 Oct 2016)

@galenzhao 主要是考虑到apollo-client的使用者不是apollo的开发者，而是普通应用的开发者。他们本来就希望通过apollo来解决他们的配置问题，如果为了使用apollo让他们还需要维护一份apollo的配置文件就有点重了，而且也难保文件是否是配置正确的。

所以我们的方案是在打包的时候注入meta server信息（就是apollo的server url），这样任何应用开发者只要拿到了apollo-client.jar包就可以使用，不需要关心apollo-client自身的配置。


## nobodyiam (07 Aug 2018)

apollo-client已经推送到Maven中央仓库, [点击此链接查看](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.ctrip.framework.apollo%22%20AND%20a%3A%22apollo-client%22)

