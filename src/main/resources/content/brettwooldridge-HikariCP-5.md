#Allow  data source properties to be set directly in HikariConfig

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## jiri-pejchal (05 Dec 2013)

Currently there are two ways of configuring data source properties:
- method  `addDataSourceProperty(String propertyName, Object value)`
- constructor  `HikariConfig(String propertyFileName)`

I'm configuring HikariCP with spring xml configuration and I need to set datasource properties (url, user, password).

I can't call the method addDataSourceProperty from the xml configuration and I would need a real file for the constructor. Could you add possibility to set Properties directly, e.g. method

``` java
setDriverProperties(Properties properties)
```

 or constructor

``` java
HikariConfig(Properties properties)
```


## brettwooldridge (05 Dec 2013)

There is a `getDataSourceProperties()` method, which can be useful in an XML-based configuration to get properties, which can then be set, but it appears that Spring does not have this capability (Jetty, for example does).  I'll add a `setDataSourceProperties()` method.


## brettwooldridge (05 Dec 2013)

Code is available on dev branch now.  Build is publishing and version 1.1.9 will appear in the Maven Central Repository in several hours.


