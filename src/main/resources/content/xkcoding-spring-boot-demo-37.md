#spring-boot-demo-rbac-security怎么刷新token呢

Owner: xkcoding

Repo: spring-boot-demo

Labels: done question 

## RobertoHuang (28 Aug 2019)

spring-boot-demo-rbac-security怎么刷新token呢

## xkcoding (28 Aug 2019)

目前这个没有 refreshToken 的操作
不过你自己可以实现以下哈，思路如下：
1. 根据携带的token获取关键信息
2. 重新生成JWT

不过目前这个只能再次调用一次登录接口 - -

