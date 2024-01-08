#actuator不能加入其它Controller

Owner: xkcoding

Repo: spring-boot-demo

Labels: done question 

## Qiyue0726 (23 Aug 2019)

我在启动类加入hello world，
```
@SpringBootApplication
public class SpringBootDemoActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoActuatorApplication.class, args);
	}

	@RequestMapping("/hello")
	public String sayHello(){
	    return "Hello";
    }

}
```
浏览器打开相关链接会强制跳转到登录界面（Bug？），登录后也报错
在actuator的mappings里面也找不到相关API

## Howeloveyou (23 Aug 2019)

你给启动类加上 **@RestController** 注解

## Qiyue0726 (23 Aug 2019)

> 你给启动类加上**@RestController**注解

唉，居然忘记了这玩意，可是为什么还是会强制跳转到登录页面

## xkcoding (23 Aug 2019)

因为加了 `spring-boot-security` 依赖呀

