#arthas web console功能支持

Owner: alibaba

Repo: arthas

Labels: disscuss 

## hengyunabc (13 Sept 2018)

目前实际上 agent attach上去之后，是开放了websocket端口的，还需要做一个页面，把web console展示出来。

* HttpTermServer
* https://github.com/xtermjs/xterm.js/ 

## shenhj2016 (23 Nov 2018)

希望这个websocket端口能支持可配

## w279805299 (03 Dec 2018)

webconsole这部分好像不在这个仓库里？

## hengyunabc (03 Dec 2018)

@shenhj2016 端口可以配置，`java -jar arthas-boot.jar -h`或者 `as.sh -h`。

@w279805299 在具体实现在依赖的 termd 里

## liuyangc3 (04 Dec 2018)

提个需求
1 可以独立部署的 web console, 不需要 attach 任何java进程
2 web console 支持多用户访问
3 agent wss 支持
4 每个用户通过web console可以通过ip或 houstname connect 到其他机器的 agent


## hengyunabc (04 Dec 2018)

@liuyangc3 直接用 nginx 转发 websocket就可以了。从url里提取到 ip 。

## liuyangc3 (05 Dec 2018)

@hengyunabc  好的
另外,如果 nginx 用https 需要 后端 支持下wss 
ps 能贴下 nginx 配置么



## hengyunabc (12 Jan 2019)

内部系统集成 web console的实践，大家可以参考下： https://github.com/alibaba/arthas/issues/442

## slankka (22 May 2019)

这里说一下，如果是在服务器上启动的话（例如通过Alibaba Cloud Toolkit 连接服务器），启动Arthas的时候 要加--target-ip 你的服务器地址，不然就无法通过你的服务器IP打开 WebConsole。这样就可以通过 RemoteHostIP:8563打开。参考： #439 。原谅我看了文档翻了Issue 才知道这些。。

## qixiaobo (27 Jun 2019)

但是如何选择attach应用呢？？？现在端口是必须要attach上去才可以连接。
目前需要运维手动上去attach？然后再webconsole上查看？

## jiangwh (26 Jul 2019)

> 但是如何选择attach应用呢？？？现在端口是必须要attach上去才可以连接。
> 目前需要运维手动上去attach？然后再webconsole上查看？

webconsole的是通过agent加载到目标进程中的，因此你必须attach之后才能访问。关键是一旦访问之后，这部分代码被加载到目标进程中，没有手段清理。

这个不会妨碍利器称号～～ 

## hengyunabc (25 Sept 2019)

已经支持 tunnel server了，可以比较方便地统一管理了。 

https://alibaba.github.io/arthas/web-console.html

## linzx2015 (26 Sept 2019)

1.先执行 java -jar arthas-demo.jar
2.执行java -jar arthas-boot.jar后，attach了arthas-demo进程后退出，期间arthas-demo.jar没退出
3.执行java -jar arthas-boot.jar --target-ip=11.11.11.11，对arthas-demo进行attach会报Connect to telnet server error: 11.11.11.11 3658
java.net.ConnectException: Connection refused，这是怎么回事呢？

## hengyunabc (26 Sept 2019)

@linzx2015 target ip指的是本地 listen 的ip，并不是连接远程的IP 。 只能诊断本地的进程。

## linzx2015 (27 Sept 2019)

> @linzx2015 target ip指的是本地 listen 的ip，并不是连接远程的IP 。 只能诊断本地的进程。
@hengyunabc 我是想指定容器ip(即本机ip)给外部浏览器连接的，需要暴露容器的ip出去
官网解释：默认情况下，arthas只listen 127.0.0.1，所以如果想从远程连接，则可以使用 --target-ip参数指定listen的IP

## nileblack (24 Oct 2019)

> @linzx2015 target ip指的是本地 listen 的ip，并不是连接远程的IP 。 只能诊断本地的进程。
```
 ./as.sh --target-ip 0.0.0.0
```
```
netstat -nap|grep 8563
tcp        0      0 ::ffff:127.0.0.1:8563       :::*                        LISTEN 
```

## linzx2015 (25 Oct 2019)

是的，但我是在容器里部署的，我想让web端连接，总不能用127.0.0.1吧



---原始邮件---
发件人: "nileblack"<notifications@github.com&gt;
发送时间: 2019年10月24日(星期四) 中午11:03
收件人: "alibaba/arthas"<arthas@noreply.github.com&gt;;
抄送: "Mention"<mention@noreply.github.com&gt;;"linzx"<857720446@qq.com&gt;;
主题: Re: [alibaba/arthas] arthas web console功能支持 (#15)


 
@linzx2015 target ip指的是本地 listen 的ip，并不是连接远程的IP 。 只能诊断本地的进程。
   ./as.sh --target-ip 0.0.0.0  netstat -nap|grep 8563 tcp        0      0 ::ffff:127.0.0.1:8563       :::*                        LISTEN   
—
You are receiving this because you were mentioned.
Reply to this email directly, view it on GitHub, or unsubscribe.

## Xuanxd (25 Oct 2019)

> 1.先执行 java -jar arthas-demo.jar
> 2.执行java -jar arthas-boot.jar后，attach了arthas-demo进程后退出，期间arthas-demo.jar没退出
> 3.执行java -jar arthas-boot.jar --target-ip=11.11.11.11，对arthas-demo进行attach会报Connect to telnet server error: 11.11.11.11 3658
> java.net.ConnectException: Connection refused，这是怎么回事呢？
端口已经被listen了，需要关掉server才能再次启动

## linzx2015 (29 Oct 2019)

你之前是否已经不指定ip执行过java -jar arthas-boot.jar的操作了，如果有的话，将被arttach进程重启释放掉3658端口。再执行java -jar arthas-boot.jar --target-ip=11.11.11.11试试


------------------ 原始邮件 ------------------
发件人: "Xuanxd"<notifications@github.com>;
发送时间: 2019年10月25日(星期五) 下午3:14
收件人: "alibaba/arthas"<arthas@noreply.github.com>;
抄送: "Zxlin"<857720446@qq.com>;"Mention"<mention@noreply.github.com>;
主题: Re: [alibaba/arthas] arthas web console功能支持 (#15)



 
1.先执行 java -jar arthas-demo.jar
 2.执行java -jar arthas-boot.jar后，attach了arthas-demo进程后退出，期间arthas-demo.jar没退出
 3.执行java -jar arthas-boot.jar --target-ip=11.11.11.11，对arthas-demo进行attach会报Connect to telnet server error: 11.11.11.11 3658
 java.net.ConnectException: Connection refused，这是怎么回事呢？
 端口已经被listen了，需要关掉server才能再次启动
  
—
You are receiving this because you were mentioned.
Reply to this email directly, view it on GitHub, or unsubscribe.

## cookiejoo (31 Dec 2019)

我有个疑问:这个web页面是A,我要attach的机器的B,B的arthas起来了,A连接attach过去了
如果我A直接关闭浏览器回家睡觉了,那B的arthas一直还是运行着.
这种情况有没有办法解决?B的arthas运行多久没有命令输入就超时自动退出.

## lucien-kejl (13 Aug 2020)

> web console 的命令行, 不能粘贴复制
我觉得也是

