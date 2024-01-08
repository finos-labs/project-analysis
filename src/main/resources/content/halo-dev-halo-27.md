#[bug] 部署后没有主题

Owner: halo-dev

Repo: halo

Labels: 

## happy3014 (10 Aug 2018)

昨天拉取了一份尝试部署了一下，发现主题无法在前台显示。后边发现是因为HaloConst类中的THEMES没有做初始化。我不太清楚这个初始化应该添加在哪里，可以请作者帮忙修复一下吗？ @ruibaby 

## ruibaby (10 Aug 2018)

@happy3014 系统是不是ubuntu 18.04?

## happy3014 (11 Aug 2018)

centos 7.4

## ruibaby (11 Aug 2018)

@happy3014 把日志给我看看吧。

## happy3014 (11 Aug 2018)

[log.log](https://github.com/ruibaby/halo/files/2280210/log.log)
sorry，我看错了，应该是我这边配置有点问题。我先临时解决了问题（用了一个很挫的办法）。这是我的日志，抱歉我不是很清楚到底哪里出问题了。。。。

## happy3014 (11 Aug 2018)

打扰作者了。我之后再慢慢看我的配置吧，我先把这个issue关闭了

