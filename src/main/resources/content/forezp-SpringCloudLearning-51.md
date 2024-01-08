#zuul报错

Owner: forezp

Repo: SpringCloudLearning

Labels: 

## suphowe (05 Nov 2019)

springboot版本已换成2.0.3
报错信息:
Description:

The bean 'proxyRequestHelper', defined in class path resource [org/springframework/cloud/netflix/zuul/ZuulProxyAutoConfiguration$NoActuatorConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [org/springframework/cloud/netflix/zuul/ZuulProxyAutoConfiguration$EndpointConfiguration.class] and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
查了一下一直说版本不正确，找不到问题了

## suphowe (05 Nov 2019)

maven中引入了2.1.x版本test
close

