#WRITE_BIGDECIMAL_AS_PLAIN Error

Owner: redisson

Repo: redisson

Labels: 

## trichner (15 Apr 2014)

I just imported redisson 1.0.3 via maven, set up a redis-server on localhost on default port and wanted to try the redisson redis client. My test code looks as following:

```
Redisson redis = Redisson.create();
Map m = redis.getMap("someMap");
```

But I'm getting an Error:

```
java.lang.NoSuchFieldError: WRITE_BIGDECIMAL_AS_PLAIN
    at org.redisson.codec.JsonJacksonCodec.createObjectMapper(JsonJacksonCodec.java:90)
    at org.redisson.codec.JsonJacksonCodec.<init>(JsonJacksonCodec.java:43)
    at org.redisson.Config.<init>(Config.java:44)
    at org.redisson.Redisson.create(Redisson.java:85)
    at ...
```

Am I doing something wrong or is this a dependency problem?


## trichner (15 Apr 2014)

Seems like the maven dependencies are wrong. Adding the following works:

```
        <!-- Redisson deps -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.3.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.3.0</version>
        </dependency>

        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
            <version>1.0.3</version>
        </dependency>
```


## mrniko (15 Apr 2014)

Redisson has a correct configuration, take a look at https://github.com/mrniko/redisson/blob/master/pom.xml there is an 2.3.2 version and all tests passes.


## mrniko (15 Apr 2014)

http://search.maven.org/remotecontent?filepath=org/redisson/redisson/1.0.3/redisson-1.0.3.pom - here is 2.3.2 versions too


## trichner (16 Apr 2014)

You are correct, my fault. AWS SDK has an older dependency and was in my classpath. Resolved.


## mrniko (16 Apr 2014)

good


