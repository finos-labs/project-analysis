#关于spring-boot-demo-properties项目获取不到pom的值

Owner: xkcoding

Repo: spring-boot-demo

Labels: bug done 

## Qiyue0726 (23 Aug 2019)

需要在pom文件下加入以下代码
```
	<build>
		<finalName>spring-boot-demo-properties</finalName>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
      </resource>
    </resources>
	</build>
```
另外，获取pom的值也可以使用`${}`方式
```
application:
  name: prod环境 @artifactId@
  version: prod环境 ${project.version}
developer:
  name: prod环境 xkcoding
  website: prod环境 http://xkcoding.com
  qq: prod环境 237497819
  phone-number: prod环境 17326075631
```

## xkcoding (24 Aug 2019)

十分感谢！ #33 下次可以提个PR解决哦~

## life88 (26 Aug 2019)

mac 下用 `${project.version}` 好像会报错，`@project.version@` 是正常的

## xkcoding (26 Aug 2019)

> mac 下用 `${project.version}` 好像会报错，`@project.version@` 是正常的

所以我使用的是 `@xxxx@`，:smile:

