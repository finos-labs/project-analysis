#Properties and wildcards are not recognized in the SpringApplication.run() sources

Owner: spring-projects

Repo: spring-boot

Labels: 

## sshcherbakov (07 Aug 2013)

I ran into a small issue with spring-boot when migrating existing project to it recently: the SampleApplication.run(sources, args) doesn't recognize the wild cards and properties inside of the source string (like "classpath:*-context.xml" is not getting recognized whereas "classpath:my-context.xml" does). The reason is the BeanDefinitionLoader which doesn't check for and use the ResourcePatternResolver.getResources() like AbstractBeanDefinitionReader.loadBeanDefinitions() does, for instance. 


## philwebb (07 Aug 2013)

Thanks for raising. Until we get this fixed you could try adding a @Configuration class with an @Import


## dsyer (09 Aug 2013)

Superseded by the PR #17 that addresses the issue.


