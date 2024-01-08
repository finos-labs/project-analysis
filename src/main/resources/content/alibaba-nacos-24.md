#why run the main function in com.alibaba.nacos.config.server.Config to debug failed?

Owner: alibaba

Repo: nacos

Labels: 

## javartisan (02 Aug 2018)

当我在本地debug  config 模块时候，每次启动都会自动退出，而且还不报错，这是什么原因？求指点迷津！
when i want to debug config module, i  run the main function in debug mode ,then it will exit Automatic。


## xuechaos (02 Aug 2018)

你本地启动的话，需要注意jvm参数是否启用了单机模式，在distribution模块里边有启动脚本的参数。或者在cfg打包命令里边也有。你看下是这个原因么？你也可以查看logs下的日志。
You need to confirm if it is set -D standalone in JVM，or see the start.log in  logs path.

## javartisan (02 Aug 2018)

thank you ,I'll try it later

