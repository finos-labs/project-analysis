#权限和开放给第三方的问题

Owner: Tencent

Repo: APIJSON

Labels: Question 使用问题 

## Leowuqunqun (21 Nov 2017)

1.查询权限的校验怎么进行很好的控制
2.如果开放给第三方的话要怎么办？开发者平台之类的

## wanghaisheng (21 Nov 2017)

前面再加一层api server来控制吧  用户和库表字段关联起来 字段可以过滤 库表应该可以灵活可配

## TommyLemon (21 Nov 2017)

@Leowuqunqun @wanghaisheng 
这样可以的。不过APIJSON提供了自动化权限校验，以及各种安全机制来保证后端的安全：
https://github.com/TommyLemon/APIJSON/issues/12

## Leowuqunqun (22 Nov 2017)

 @TommyLemon 开发者平台之类的要怎么办  文档要手动写了么

## TommyLemon (22 Nov 2017)

@Leowuqunqun 
目前APIJSON提供了通用文档(GitHub主页) 和 自动化生成的数据库文档(APIJSON在线解析网页)、非开放请求格式的文档(网页底部)，还能自动生成请求代码。

平台确实要写权限配置的文档，打算做一个自动解析model注解生成文档的工具，反正解析也很简单。
如果是内部用，Java后端的model类可以直接给Android客户端用，注解里权限配置很清楚。
```java
@MethodAccess(
  POST = {UNKNOWN, ADMIN} //只允许未登录角色和管理员角色新增User，默认配置是 {LOGIN, ADMIN}
)
public class User {}
```

默认的权限配置在MethodAccess里
```java
/**请求方法权限，只允许某些角色通过对应方法访问
 * @author Lemon
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface MethodAccess {
	
	/**@see {@link RequestMethod#GET}
	 * @return 该请求方法允许的角色 default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
	 */
	RequestRole[] GET() default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
	
	/**@see {@link RequestMethod#HEAD}
	 * @return 该请求方法允许的角色 default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
	 */
	RequestRole[] HEAD() default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
	
	/**@see {@link RequestMethod#GETS}
	 * @return 该请求方法允许的角色 default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
	 */
	RequestRole[] GETS() default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
	
	/**@see {@link RequestMethod#HEADS}
	 * @return 该请求方法允许的角色 default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
	 */
	RequestRole[] HEADS() default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};

	/**@see {@link RequestMethod#POST}
	 * @return 该请求方法允许的角色  default {LOGIN, ADMIN};
	 */
	RequestRole[] POST() default {LOGIN, ADMIN};

	/**@see {@link RequestMethod#PUT}
	 * @return 该请求方法允许的角色 default {OWNER, ADMIN};
	 */
	RequestRole[] PUT() default {OWNER, ADMIN};
	
	/**@see {@link RequestMethod#DELETE}
	 * @return 该请求方法允许的角色 default {OWNER, ADMIN};
	 */
	RequestRole[] DELETE() default {OWNER, ADMIN};
	
}
```

这是角色属性类RequestRole
```java
/**来访的用户角色
 * @author Lemon
 */
public enum RequestRole {

	/**未登录，不明身份的用户
	 */
	UNKNOWN,

	/**已登录的用户
	 */
	LOGIN,

	/**联系人，必须已登录
	 */
	CONTACT,
	
	/**圈子成员(CONTACT + OWNER)，必须已登录
	 */
	CIRCLE,

	/**拥有者，必须已登录
	 */
	OWNER,

	/**管理员，必须已登录
	 */
	ADMIN;
}
```

感谢支持^_^

## TommyLemon (07 Jan 2019)

@Leowuqunqun 
已支持自动生成 
数据字典(information_schema.tables,information_schema.columns)、
访问权限(Access.sql)、
远程函数(Funciton.sql)、
非开放请求(Request.sql) 
4 种文档，
见自动化接口管理工具 APIJSONAuto 右侧上滑出来的具体文档。
http://apijson.org/

## TommyLemon (07 Jan 2019)

![image](https://user-images.githubusercontent.com/5738175/50748068-64e7df00-1272-11e9-8fbb-1fc4b1af8188.png)


## TommyLemon (07 Jan 2019)

![image](https://user-images.githubusercontent.com/5738175/50748083-77621880-1272-11e9-92a1-2779be6433a4.png)


## TommyLemon (07 Jan 2019)

![image](https://user-images.githubusercontent.com/5738175/50748092-88128e80-1272-11e9-87a9-0c43569837db.png)


## TommyLemon (07 Jan 2019)

![image](https://user-images.githubusercontent.com/5738175/50748098-93fe5080-1272-11e9-8bd7-ea57017a91f3.png)


## TommyLemon (15 Sept 2019)

 @Leowuqunqun @wanghaisheng  APIJSON 3.6.5 已支持直接在数据库 Access 表配置权限，不需要写代码了
https://github.com/APIJSON/APIJSON/releases/tag/3.6.5

## Leowuqunqun (19 Sept 2019)

@TommyLemon nice 

## TommyLemon (22 Apr 2022)

@Leowuqunqun @wanghaisheng 

腾讯 APIJSON 的路由插件，对外暴露类 RESTful 接口，内部转成 APIJSON 接口执行。
https://github.com/APIJSON/apijson-router

APIJSON 最新版 5.0.0：

增强各种功能；腾讯负责人公开称赞；登记万科发起的采筑电商
https://github.com/Tencent/APIJSON/releases/tag/5.0.0

