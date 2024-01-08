#Documentation: Moving from an embedded to a deployment version of the application

Owner: spring-projects

Repo: spring-boot

Labels: 

## bijukunjummen (27 Aug 2013)

This is probably a understanding gap on my side - I couldn't figure out a way of moving from a standalone embedded version of an application(executed using SpringApplication) to a war version of the application which is expected to be deployed in an external container. Specifically some of the auto-configuration parts seem to stop working - for eg, in my case the hsqldb autocreate of the tables did not work in a deployed mode. Is there a documentation on how to go about doing this?


## philwebb (27 Aug 2013)

There is a sample application showing war packaging [1]. The intention is that you should be able to change the package type and everything just works.

Are you able to put together a small sample showing the problem?

[1] https://github.com/SpringSource/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-traditional/pom.xml#L11 


## bijukunjummen (28 Aug 2013)

Hi Phil,
Here is a project that does not work cleanly for me - https://github.com/bijukunjummen/spring-boot-mvc-test.git

It starts up cleanly with "mvn spring-boot:run" and there is a endpoint available at http://localhost:8080/hotels/list

However if you start this up with "mvn jetty:run" using the jetty plugin, the endpoint fails with an exception that the autocreate of the tables failed - 

``` java
Caused by: org.hsqldb.HsqlException: user lacks privilege or object not found: HOTEL
    at org.hsqldb.error.Error.error(Unknown Source)
    at org.hsqldb.error.Error.error(Unknown Source)
    at org.hsqldb.SchemaManager.getTable(Unknown Source)
    at org.hsqldb.ParserDQL.readTableName(Unknown Source)
```


## dsyer (20 Sept 2013)

You aren't using `SpringApplication` to load your context in the non-embedded case so you would have to add some initializers to match that behaviour. In particular you aren't loading and binding to the `application.properties`, so it isn't setting the `ddl-auto` property, and the SQL script is not being executed as a result. You could fix this in your `web.xml` (in the servlet declaration)

```
<init-param>
    <param-name>contextInitializerClasses</param-name>
    <param-value>org.springframework.boot.context.initializer.ConfigFileApplicationContextInitializer</param-value>
</init-param>
```

It's probably better though to add a `SpringBootServletInitializer`, since that way you are always going to get all the features of `SpringApplication` in your web context (then you would use a Servlet 3.0 container - so no need for web.xml). Your `SampleWebApplication` could extend `SpringBootServletInitializer` and just return an array containing its own class (that's a pretty common pattern for these "hybrid" builds").


## bijukunjummen (21 Sept 2013)

Perfect, specifying the `ApplicationContextInitializer` works cleanly.


