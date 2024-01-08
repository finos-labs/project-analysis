#BeanDefinitionLoader cannot load resources when LaunchedURLClassLoader is the current classloader

Owner: spring-projects

Repo: spring-boot

Labels: 

## sshcherbakov (30 Aug 2013)

I am starting my application with 

```
    public static void main(final String[] args) {
...
            SpringApplication sa = new SpringApplication(new Object[] {  
                "classpath:path/my-context.xml"
            });
            sa.run(args);
..
```

It works if I run it with `./gradle run` or start it from Eclipse.
The default class loader of the thread that runs main() is `sun.misc.Launcher$AppClassLoader` in this case.

When I use spring-boot-gradle-plugin and packge the application with it then when run with `java -jar myapp.jar` the application crashes on startup with

```
2013-08-30 12:04:20,482 797 ERROR [main] com.myapp.Main - Unexpected error
java.lang.IllegalArgumentException: name
    at sun.misc.URLClassPath$Loader.getResource(URLClassPath.java:525) ~[na:1.7.0_17]
    at sun.misc.URLClassPath.getResource(URLClassPath.java:195) ~[na:1.7.0_17]
    at java.net.URLClassLoader$1.run(URLClassLoader.java:358) ~[na:1.7.0_17]
    at java.net.URLClassLoader$1.run(URLClassLoader.java:355) ~[na:1.7.0_17]
    at java.security.AccessController.doPrivileged(Native Method) ~[na:1.7.0_17]
    at java.net.URLClassLoader.findClass(URLClassLoader.java:354) ~[na:1.7.0_17]
    at org.springframework.boot.loader.LaunchedURLClassLoader.findClass(LaunchedURLClassLoader.java:57) ~[rti-t-http-1.0.0.BUILD-SNAPSHOT.jar!/:na]
    at java.lang.ClassLoader.loadClass(ClassLoader.java:423) ~[na:1.7.0_17]
    at java.lang.ClassLoader.loadClass(ClassLoader.java:356) ~[na:1.7.0_17]
    at org.springframework.util.ClassUtils.forName(ClassUtils.java:241) ~[spring-core-4.0.0.M3.jar!/:4.0.0.M3]
    at org.springframework.boot.BeanDefinitionLoader.load(BeanDefinitionLoader.java:162) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar!/:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.BeanDefinitionLoader.load(BeanDefinitionLoader.java:134) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar!/:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.BeanDefinitionLoader.load(BeanDefinitionLoader.java:117) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar!/:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.SpringApplication.load(SpringApplication.java:469) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar!/:0.5.0.BUILD-SNAPSHOT]
    at org.springframework.boot.SpringApplication.run(SpringApplication.java:280) ~[spring-boot-0.5.0.BUILD-SNAPSHOT.jar!/:0.5.0.BUILD-SNAPSHOT]
    at com.myapp.Main.main(Main.java:99) ~[myapp-1.0.0.BUILD-SNAPSHOT.jar!/:na]
```

That happens because spring-boot-gradle plugin packs the application in such way the the `org.springframework.boot.loader.LaunchedURLClassLoader` becomes the default main() thread classloader. It delegated an attempt to load a class with name `classpath:path/my-context.xml` to its parent class URLClassLoader which throws `IllegalArgumentException` instead of anticipated  `ClassNotFoundException` in the `org.springframework.boot.BeanDefinitionLoader`. Thus the exception is not being intercepted, propagates up the stack and crashes the application on startup.


