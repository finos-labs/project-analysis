#URGENT: java.lang.NoSuchFieldError: WRITE_BIGDECIMAL_AS_PLAIN

Owner: redisson

Repo: redisson

Labels: 

## mathieucarbou (08 May 2014)

I do not have Jackson and do not want to use it. Sadly, the Config class has a hard pointer on it: 

`private RedissonCodec codec = new JsonJacksonCodec();`

Thus when creating a new Config instance I have the exception:

```
Caused by: java.lang.NoSuchFieldError: WRITE_BIGDECIMAL_AS_PLAIN
    at org.redisson.codec.JsonJacksonCodec.createObjectMapper(JsonJacksonCodec.java:90)
    at org.redisson.codec.JsonJacksonCodec.<init>(JsonJacksonCodec.java:43)
    at org.redisson.Config.<init>(Config.java:44)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:408)
    at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:77)
```

Codecs should be made optional or at least the default should be the Serializer codec which only depends on the JDK, or a simple String <=> String codec.

For now I cannot use Redisson properly :-(


## mrniko (09 May 2014)

Please use Redisson 1.0.4 where this problem was fixed


## doggie1989 (09 May 2014)

Hi mrniko

Could release 1.0.4 version to maven ? then we could easily use it.

Thank you very much.


## mrniko (09 May 2014)

Oh, sorry i was forgot to push "release" button in maven console. Done!


## mathieucarbou (09 May 2014)

Cool! Thank you!!!


## mathieucarbou (09 May 2014)

Just one note: since jackson is now optional, those depndencies should be also made optional in the pom.xml:

```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.3.3</version>
  </dependency>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.3.3</version>
  </dependency>
```

Version 1.0.4 brings them all, but we do not use them ;-)


## mrniko (09 May 2014)

But json is default codec, so if codec is not defined redisson will not start.


## mathieucarbou (09 May 2014)

Ok... Sorry I misunderstood your fix thus... 

I thought you were to set the config to be using no encoder, or the JDK-based one, and that all other encoders such as Jackson or Kryo, because they depend on external dependencies, would have been made optional in the pom and just provided as sort of "support" encoders.


## mrniko (09 May 2014)

I choose jackson serialization because it faster than JDK-serializier


## mathieucarbou (09 May 2014)

ok! in the case speed is a concern, it would be nice thus to add also a Boon encoder since Boon has the fastest java json parser ;-)


## mrniko (09 May 2014)

good, i'll consider about support for this parser


