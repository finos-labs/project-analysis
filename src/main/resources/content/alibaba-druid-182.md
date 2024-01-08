#druid --not support oracle driver 1.0

Owner: alibaba

Repo: druid

Labels: 

## fqtyfz (25 Oct 2012)

程序启动的时候报这个异常
not support oracle driver 1.0
不知道是什么原因！！


## sandzhang (26 Oct 2012)

oracle驱动版本太老了，换个新驱动吧


## icexyli (22 Jun 2017)

# durid 当驱动为oracle的时候判断版本号
if (driver.getMajorVersion() < 10) {
                throw new SQLException("not support oracle driver " + driver.getMajorVersion() + "."
                                       + driver.getMinorVersion());
            }
# 但是oracle最新驱动返回版本号的方法是常量1
public int getMajorVersion() {
        return 1;
    }

## power-coding (29 Oct 2018)

> # durid 当驱动为oracle的时候判断版本号
> if (driver.getMajorVersion() < 10) {
> throw new SQLException("not support oracle driver " + driver.getMajorVersion() + "."
> + driver.getMinorVersion());
> }
> 
> # 但是oracle最新驱动返回版本号的方法是常量1
> public int getMajorVersion() {
> return 1;
> }
用这个jar包，返回版本号为1：
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc14</artifactId>
			<version>1.0</version>
		</dependency>

