#将项目直接导入到idea中启动会报错，

Owner: lenve

Repo: vhr

Labels: question 

## xihazhiwang (09 Jan 2018)

***************************
APPLICATION FAILED TO START
***************************

Description:

Cannot determine embedded database driver class for database type NONE

Action:

If you want an embedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (no profiles are currently active).


Process finished with exit code 1

应该在启动类中加入@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})注解，原因：这是因为spring boot默认会加载org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration类，DataSourceAutoConfiguration类使用了@Configuration注解向spring注入了dataSource bean。因为工程中没有关于dataSource相关的配置信息，当spring创建dataSource bean因缺少相关的信息就会报错。
解决方案来源：https://www.cnblogs.com/yql1986/p/6819097.html

## lenve (09 Jan 2018)

@xihazhiwang  
也可以参考下这个https://github.com/lenve/VBlog/issues/1

## shuaishuaihand (12 Jan 2018)

这个错误是怎么回事？
Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
2018-01-12 17:20:29.265 ERROR 1288 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

Description:

Field menuMapper in org.sang.service.MenuService required a bean of type 'org.sang.mapper.MenuMapper' that could not be found.


Action:

Consider defining a bean of type 'org.sang.mapper.MenuMapper' in your configuration.

## lenve (17 Jan 2018)

@shuaishuaihand 
hello哥们，你的问题解决了吗？

## xihazhiwang (17 Jan 2018)

@lenve @shuaishuaihand 不用我这么麻烦，参考这个[https://github.com/lenve/VBlog/issues/1](url) narakai 的回复就可以了

## lenve (17 Jan 2018)

好

