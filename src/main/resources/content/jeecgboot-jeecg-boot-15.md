#druid-spring-boot-starter更新后无法访问druid监控界面

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## galaxist (12 Mar 2019)

![image](https://user-images.githubusercontent.com/3365218/54188689-37761680-44eb-11e9-9293-e441ade3cb09.png)
依赖
```
       <dependency>
		   <groupId>com.alibaba</groupId>
		   <artifactId>druid-spring-boot-starter</artifactId>
		   <version>1.1.10</version>
		</dependency>
```
更新到
```
       <dependency>
		   <groupId>com.alibaba</groupId>
		   <artifactId>druid-spring-boot-starter</artifactId>
		   <version>1.1.14</version>
		</dependency>
```
后无法访问druid监控界面，请问哪里需要修改

## galaxist (12 Mar 2019)

我又来了自问自答了，问题解决，因为新的druid-start为了安全关闭了，需要增加配置
```
spring:
  datasource:
    druid:
      stat-view-servlet:
        allow: localhost,192.168.188.66
        enabled: true
        loginUsername: admin
        loginPassword: hemiao1314
      web-stat-filter:
        allow: localhost,192.168.188.66
        enabled: true
```
即可继续访问

## zhangdaiscott (12 Mar 2019)

3Q

