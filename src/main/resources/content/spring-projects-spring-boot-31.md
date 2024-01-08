#Allow properties to be overridden by environment variables

Owner: spring-projects

Repo: spring-boot

Labels: 

## jarias (21 Aug 2013)

I know that the PropertySourcesPlaceholderConfigurer supports this, but not so sure how to integrate it a spring-boot app.

http://12factor.net/config


## jarias (21 Aug 2013)

Seems that env variables are been loaded but classpath properties files have more precedence is there are reason for this?


## philwebb (23 Aug 2013)

I have just pushed a change that will hopefully fix this. In the meantime you can always use a local properties file to override values from your bundled version.


