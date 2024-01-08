#全局捕获异常页面转跳不会经过控制器

Owner: wuyouzhuguli

Repo: SpringAll

Labels: 

## smiles1x (10 Oct 2018)

在“Spring Boot Shiro权限控制”中，捕获的403异常会直接取找templates下的403页面，而不会经过控制器再去访问403页面。

## wuyouzhuguli (30 Oct 2018)

走的是全局异常捕获：
```java
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = AuthorizationException.class)
	public String handleAuthorizationException() {
		return "403";
	}
}
```

