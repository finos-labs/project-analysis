#apollo在windows上无法运行?

Owner: apolloconfig

Repo: apollo

Labels: 

## fantasyding (12 Jan 2017)

我按照Quick Start操作，但是在win7上运行build.sh后报错
./service/apollo-service.jar: line 168: C:\Program: No such file or directory

请问这个情况如何解决?

## nobodyiam (12 Jan 2017)

在windows上确实存在这个问题，如果java_home的路径有空格，通过spring boot启动会报这个错。

如果遇到这个问题，可以通过下面的步骤来运行。如果觉得繁琐的话，建议可以通过IDE运行，可以参考[Apollo开发指南](https://github.com/ctripcorp/apollo/wiki/Apollo%25E5%25BC%2580%25E5%258F%2591%25E6%258C%2587%25E5%258D%2597)

```
编辑build.sh，95行

把 $SERVICE_JAR start --configservice --adminservice

改为 "C:\Program Files\Java\jdk1.8.0_60\bin\java.exe" -Dsun.misc.URLClassPath.disableJarChecking=true $JAVA_OPTS -jar $SERVICE_JAR --configservice --adminservice

139行

把 $PORTAL_JAR start --portal

改为"C:\Program Files\Java\jdk1.8.0_60\bin\java.exe" -Dsun.misc.URLClassPath.disableJarChecking=true $JAVA_OPTS -jar $PORTAL_JAR --portal

然后把build.sh复制3个，分别叫start_service.sh, start_portal.sh, start_client.sh
在start_portal.sh里面把
echo "==== starting service ===="
一直到
printf "\nAdmin service started\n"
都删掉

先执行./start_service.sh start，等它启动好了
新开一个console，执行./start_portal.sh start
```

## fantasyding (12 Jan 2017)

按照你的方法已经解决了，感谢！

## ExtremeYu (08 Apr 2017)

你们的ide教程也是在mac下搞的，有没有在windows下的教程按照教程跑不起来，总是报找不到配置文件

## GirHubCom (15 Oct 2019)

按照上面的方法我试了下在日志里面出现的是这个
./portal/apollo-portal.jar: line 168: /c/Program: No such file or directory


## 17764591637 (13 Aug 2020)

我运行./start_service.sh start    和  ./start_portal.sh start后会运行完的  大家都是这样的吗  然后代码执行的时候报错了  apollo初始化出了问题

