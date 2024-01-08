#'dataSource'  应该还没有配置完全把？

Owner: lenve

Repo: vhr

Labels: question 

## wujianhong (12 Jan 2018)

2018-01-12 11:50:38.436  WARN 10080 --- [           main] ationConfigEmbeddedWebApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'urlFilterInvocationSecurityMetadataSource': Unsatisfied dependency expressed through field 'menuService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'menuService': Unsatisfied dependency expressed through field 'menuMapper'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'menuMapper' defined in file [D:\Workspaces\IDEAWORK\vhr-master\hrserver\target\classes\org\sang\mapper\MenuMapper.class]: Unsatisfied dependency expressed through bean property 'sqlSessionFactory'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'sqlSessionFactory' defined in class path resource [org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.class]: Unsatisfied dependency expressed through method 'sqlSessionFactory' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Tomcat.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.apache.tomcat.jdbc.pool.DataSource]: Factory method 'dataSource' threw exception; nested exception is org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Cannot determine embedded database driver class for database type NONE. If you want an embedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (no profiles are currently active).


## wujianhong (12 Jan 2018)

是由于在IDEA中未将resources 设置为resources源

## lenve (12 Jan 2018)

@wujianhong 
https://github.com/lenve/vhr/issues/1

