#MyBatis 3.2.0 - Disabled lazy loading but CGLIB is still required to be on classpath

Owner: mybatis

Repo: mybatis-3

Labels: duplicate 

## davsclaus (07 Mar 2013)

Hi

I am in the process of upgrading camel-mybatis from 3.1.1 to 3.2.0 and hit a problem.

Caused by: org.apache.ibatis.exceptions.PersistenceException: 
### Error building SqlSession.
### The error may exist in SQL Mapper Configuration
### Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: org.apache.ibatis.builder.BuilderException: Error creating instance. Cause: java.lang.IllegalStateException: Cannot enable lazy loading because CGLIB is not available. Add CGLIB to your classpath.

I do not have CGLIB on my classpath, nor do I want to. I did not have it on my classpath with MyBatis 3.1.1.

So I added an option to my settings to explicit disable lazy loading as follows

```
<settings>
    <!-- disable lazy loading for testing as we do not want CGLIB on our classpath -->
    <setting name="lazyLoadingEnabled" value="false"/>
    <setting name="useGeneratedKeys" value="false"/>
</settings>
```

But I still get that failure.

Debugging the code I get to this point

org.apache.ibatis.builder.xml.XMLConfigBuilder#settingsElement

line 198 has:
      configuration.setProxyFactory((ProxyFactory) createInstance(props.getProperty("proxyFactory", "CGLIB")));      

I wonder if there is some way to run MyBatis without any proxy factory being CGLIB or JAVASSITS?


## emacarron (07 Mar 2013)

Hi Claus,

First of all sorry for the bug. It is already fixed in the master branch and we are planning a bug fix release inmediately. 

Duplicated of #4 


## davsclaus (07 Mar 2013)

Thanks for the speedy response. We will skip the 3.2.0 release and upgrade when the next is out. Thanks for creating this great framework - has been a fan of MyBatis for a long time :)


