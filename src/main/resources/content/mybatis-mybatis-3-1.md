#Wrong dependency on Log4j in MyBatis 3.2

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## emacarron (23 Feb 2013)

MyBatis 3.2 does not startup without log4j in the classpath.

Caused by: java.lang.NoClassDefFoundError: org/apache/log4j/Priority
    at java.lang.Class.forName0(Native Method)
    at java.lang.Class.forName(Class.java:266)
    at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:176)
    at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:87)
    at org.apache.ibatis.io.Resources.classForName(Resources.java:254)
    at org.apache.ibatis.type.TypeAliasRegistry.registerAlias(TypeAliasRegistry.java:153)
    at org.apache.ibatis.session.Configuration.<init>(Configuration.java:173)


## paulkrause88 (06 Mar 2013)

Is there a workaround?  This makes 3.2.0 unusable for me.


## paulkrause88 (06 Mar 2013)

Not a general workaround, but in my case I was able to get it working by adding the following dependencies to my pom.xml.

``` xml
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>log4j-over-slf4j</artifactId>
            <version>1.7.2</version>
        </dependency>
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>2.2.2</version>
        </dependency>
```


