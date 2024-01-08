#spring-boot-web项目的pom.xml配置有误

Owner: ityouknow

Repo: spring-boot-examples

Labels: 

## buildupchao (25 Dec 2017)

<dependency>  
	<groupId>org.springframework.boot</groupId>  
        <artifactId>spring-boot-starter-redis</artifactId>  
</dependency> 
需要改为:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

## ityouknow (05 Feb 2018)

不同的版本使用的不一样，最新版本使用的是spring-boot-starter-data-redis

