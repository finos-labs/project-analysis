#By default, app startup should fail if file.encoding and sun.jnu.encoding != UTF-8

Owner: spring-projects

Repo: spring-boot

Labels: 

## mikaelhg (11 Sept 2013)

Platform encoding issues cause headaches to no end, both in development and production. As a part of adopting sensible defaults, Spring Boot can help this issue.

Spring Boot provides, through intermediaries, functionalities such as SQL schema loading or URL handling, which are sensitive to default encoding settings, and may apparently randomly produce unexpected results for some users and developers.

Spring Boot should, by default, fail to start with an informative error message, if the JVM encoding settings aren't UTF-8. The user should be able to convert the error+exit to a warning with a command line parameter.

Iñtërnâtiônàlizætiøn!


## dsyer (18 Sept 2013)

Sounds fine to me (except that obviously UTF-8 is not going to work for everyone, so we should only assert that if it isn't explicitly set). Any idea where to insert those checks?  Pull requests always welcome.


## dsyer (01 Oct 2013)

Updated research: 'sun.jnu.encoding' is (obviously) platform specific, but it seems to be dependent (at least on *nix machines) on the `LANG` environment variable, so maybe it makes sense to check that variable as well or instead?


## mikaelhg (01 Oct 2013)

Both of these system properties get their default values, if not specifically set, from the environmental variables `LANG` and `LC_*` on Linux and Solaris. I suspect that the *BSD ports have followed a similar convention, but haven't checked it.

http://hg.openjdk.java.net/jdk7u/jdk7u/jdk/file/tip/src/solaris/native/java/lang/java_props_md.c

```
365 java_props_t *
366 GetJavaProperties(JNIEnv *env)
```

http://hg.openjdk.java.net/jdk7u/jdk7u/jdk/file/tip/src/share/native/java/lang/System.c

```
236     PUTPROP(props, "file.encoding", sprops->encoding);
237     PUTPROP(props, "sun.jnu.encoding", sprops->sun_jnu_encoding);   
```


## mikaelhg (05 Oct 2013)

On application level, I've currently implemented this like so:

```
@Bean
public CommandLineRunner requireInternationalEncodingOrCrash(final ConfigurableApplicationContext context) {
    return new CommandLineRunner() {
        @Override public void run(final String... args) throws Exception {
            final String encoding = System.getProperty("file.encoding");
            if (encoding != null && !"UTF-8".equals(encoding.toUpperCase())) {
                log.error("Your system property \"file.encoding\" is currently \"{}\". It should be \"UTF-8\". ", encoding);
                log.error("Your environmental variable LANG is currently \"{}\". It should end \".UTF-8\". ", System.getenv("LANG"));
                log.error("Your environmental variable LC_ALL is currently \"{}\". It should end \".UTF-8\". ", System.getenv("LC_ALL"));
                log.error("We can't run this application properly if the Java Virtual Machine it runs in hasn't been configured to use the UTF-8 encoding. Closing down.");
                context.close();
            }
        }
    };
}
```

With a little more complexity for various `LC_*` combinations.

I assume a properly explicit and contained solution would be to create a "sanity check" stage where we check whether the application can be run in `org.springframework.boot.SpringApplication::run`?


## dsyer (05 Oct 2013)

That's a good start, but yes, I think a better solution might be to use an ApplicationContextInitializer (one that runs after the ConfigFile\* one so that it can be parameterized externally).


## mikaelhg (09 Oct 2013)

```
private final static ApplicationContextInitializer<ConfigurableApplicationContext> REQUIRE_UTF8 =
        new ApplicationContextInitializer<ConfigurableApplicationContext>() {
    private final static String REFUSE_TO_START =
            "We can't run this application properly, since the Java Virtual Machine it runs in hasn't " +
                    " been configured to use the UTF-8 default character encoding. Closing down.";
    @Override public void initialize(final ConfigurableApplicationContext ctx) {
        final String encoding = System.getProperty("file.encoding");
        if (encoding != null && !"UTF-8".equals(encoding.toUpperCase())) {
            log.error("Your system property \"file.encoding\" is currently \"{}\". It should be \"UTF-8\". ", encoding);
            log.error("Your environmental variable LANG is currently \"{}\". You must use a UTF-8 locale setting.",
                    System.getenv("LANG"));
            log.error("Your environmental variable LC_ALL is currently \"{}\". You must use a UTF-8 locale setting.",
                    System.getenv("LC_ALL"));
            log.error(REFUSE_TO_START);
            throw new IllegalStateException(REFUSE_TO_START);
        }
    }
};

public static void main(final String ... args) {
    new SpringApplicationBuilder(ExampleController.class).initializers(REQUIRE_UTF8).run(args);
}
```


## mikaelhg (10 Oct 2013)

Thanks :)


