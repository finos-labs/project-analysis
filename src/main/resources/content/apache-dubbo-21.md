#Maven依赖找不到

Owner: apache

Repo: dubbo

Labels: 

## IamFive (16 Jan 2014)

RT，opensesame的包无法下载，[Maven][http://repo1.maven.org/maven2/com/alibaba/] 中也无法找到对应的包，下的dubbo项目无法编译。

``` xml
    <parent>
        <groupId>com.alibaba</groupId>
        <artifactId>opensesame</artifactId>
        <version>2.0</version>
    </parent>
```


## lpingxh (18 Jan 2014)

遇到同样的问题，请求帮助


## threezhang (18 Jan 2014)

同样的问题

发自我的 iPhone

> 在 2014年1月18日，14:50，lpingxh notifications@github.com 写道：
> 
> 遇到同样的问题，请求帮助
> 
> —
> Reply to this email directly or view it on GitHub.


## IamFive (19 Jan 2014)

这个开源项目还活跃吗? 所有的文档链接都失效了已经了。


## takeseem (19 Jan 2014)

看代码提交，最近的是17天前，应该还活跃。
淘宝阿里最初搞开放平台时开放的态度和现在有很大的差别了吧！
不知道发生了什么事情！


## IamFive (22 Jan 2014)

估计`过年`了吧。:) 等年后再来看看


## oldratlee (23 Jan 2014)

网站的`VIP`出问题了，给PE提了单子了


## afredlyj (08 Feb 2014)

主站貌似不能访问了


## robert1111 (10 Feb 2014)

code.alibabatech.com 什么时候才能修复啊？


## ludvik (10 Feb 2014)

过年都上班了，今天跟进此事
- 伯昊


## bladehd (10 Feb 2014)

找不到opensesame依赖的，请先手动下载https://github.com/alibaba/opensesame
本地install，这几天我们会把opensesame发到mavan中央仓库。


## afredlyj (10 Feb 2014)

主站什么时候能开呢？

2014-02-10 11:20 GMT+08:00 bladehd notifications@github.com:

> 找不到opensesame依赖的，请先手动下载
> https://github.com/alibaba/opensesame本地install，这几天我们会把opensesame发到mavan中央仓库。
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/alibaba/dubbo/issues/21#issuecomment-34598164
> .


## ludvik (10 Feb 2014)

主站的文档我们已经临时打包，明天会先放到github上供大家下载；

之后会将文档转为markdown，放到github上托管。

地址在：http://alibaba.github.io/dubbo-doc-static/User+Guide-zh.htm
- 伯昊


## lishunli (23 Feb 2014)

前期的一些依赖包，下载解压到本地maven仓库（一般在 ~.m2\repository\com\alibaba）
http://usc.googlecode.com/svn/files/package/alibaba-dubbo-dependency.zip


## ZhangKeyong07 (08 Apr 2014)

请先手动下载https://github.com/alibaba/opensesame
本地install；弱弱的问下怎么本地安装哈？用什么命令


## oldratlee (16 Apr 2014)

@ZhangKeyong07 

> 本地install；弱弱的问下怎么本地安装哈？用什么命令

使用[`maven`](http://maven.apache.org/)。

``` bash
mvn install
```


## nyzoupu (09 Jun 2016)

@lishunli 那个master 依赖包的下载链接失效了吗，怎么打不开页面


## lishunli (11 Jun 2016)

@nyzoupu 
嗯，已经失效，重新上传了一份
[alibaba-dubbo-dependency.zip](https://github.com/alibaba/dubbo/files/310085/alibaba-dubbo-dependency.zip)


## zouchunhui (22 Feb 2017)

mark.

## cuisongliu (27 May 2017)

我build的时候也发现了 大家可以设置maven仓库链接到 http://maven.cuisongliu.com/content/groups/public 我已经上传了opensesame的pom到中央仓库。

