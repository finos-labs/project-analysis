#所有启动脚本都无法在Mac下工作

Owner: apache

Repo: dubbo

Labels: 

## zhangyijun (24 Jul 2013)

版本： 2.4.10

似乎脚本都使用了ps -f，在Mac OS平台 无法工作，类似下面错误

PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'
ps: illegal option -- f
usage: ps [-AaCcEefhjlMmrSTvwXx] [-O fmt | -o fmt] [-G gid[,gid...]]
          [-u]
          [-p pid[,pid...]] [-t tty[,tty...]] [-U user[,user...]]
       ps [-L]
echo "PID: $PIDS"
PID: 
echo "STDOUT: $STDOUT_FILE"
STDOUT: logs/stdout.log


## newroc (25 Jul 2013)

升级到2.5.3就好了，新版本已经解决了这个问题


## zhangyijun (28 Jul 2013)

simple monitor还是不能用


