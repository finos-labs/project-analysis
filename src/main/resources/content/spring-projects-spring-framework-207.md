#spring 3.2 ContentNegotiatingViewResolver setMediaTypes not work!!!

Owner: spring-projects

Repo: spring-framework

Labels: 

## qxo (28 Dec 2012)

In Spring 3.2 ContentNegotiatingViewResolver:
public void setMediaTypes(Map<String, String> mediaTypes) {
        if (mediaTypes != null) {
            this.cnManagerFactoryBean.getMediaTypes().putAll(mediaTypes);
        }
    }

But cnManagerFactoryBean.getMediaTypes() required Type :Map<String, MediaType> :(

Caused by: java.lang.ClassCastException: java.lang.String incompatible with org.springframework.http.MediaType
    at org.springframework.web.accept.MappingMediaTypeFileExtensionResolver.<init>(MappingMediaTypeFileExtensionResolver.java:56) ~[spring-web-3.2.0.RELEASE.jar:3.2.0.RELEASE]
    at org.springframework.web.accept.AbstractMappingContentNegotiationStrategy.<init>(AbstractMappingContentNegotiationStrategy.java:42) ~[spring-web-3.2.0.RELEASE.jar:3.2.0.RELEASE]
    at org.springframework.web.accept.PathExtensionContentNegotiationStrategy.<init>(PathExtensionContentNegotiationStrategy.java:74) ~[spring-web-3.2.0.RELEASE.jar:3.2.0.RELEASE]
    at org.springframework.web.accept.ServletPathExtensionContentNegotiationStrategy.<init>(ServletPathExtensionContentNegotiationStrategy.java:47) ~[spring-web-3.2.0.RELEASE.jar:3.2.0.RELEASE]


## rstoyanchev (31 Dec 2012)

The [Spring Framework JIRA](https://jira.springsource.org/browse/SPR) is the right place to log issues. This one [has been reported](https://jira.springsource.org/browse/SPR-10119). thanks!


