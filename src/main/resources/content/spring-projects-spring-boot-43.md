#Hot Redeploy and Command Line Running from Gradle

Owner: spring-projects

Repo: spring-boot

Labels: 

## YudhistiraArya (06 Sept 2013)

Is it possible to hot redeploy when you run through the main class? I already searched the repository for any possible mention of hot redeploy and couldn't find any. This is probably just an understanding gap on my side. 

How to reproduce: 
- The project is initially constructed using the following Gradle build. I use this mainly just to generate IDEA project, so I think there's no effect here. 

```

buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "Spring snapshot"
            url = "http://repo.springsource.org/snapshot"
        }
        maven {
            name = 'Spring milestone'
            url = "http://repo.springsource.org/milestone"
        }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:0.5.0.BUILD-SNAPSHOT")
    }
}

apply plugin: 'spring-boot'
apply plugin: 'java'
apply plugin: 'groovy'
apply plugin: 'idea'

repositories {
    mavenCentral()
    maven {
        name = "Spring snapshot"
        url = "http://repo.springsource.org/snapshot"
    }
    maven {
        name = 'Spring milestone'
        url = "http://repo.springsource.org/milestone"
    }
}

dependencies {

    compile group: 'com.google.guava', name:'guava', version: '14.0.1+'
    compile 'org.codehaus.groovy:groovy-all:2.1.6'
    compile "org.springframework.boot:spring-boot-starter:0.5.0.BUILD-SNAPSHOT"
    compile "org.springframework.boot:spring-boot-starter-jetty:0.5.0.BUILD-SNAPSHOT"
    compile "org.springframework.boot:spring-boot-starter-web:0.5.0.BUILD-SNAPSHOT"
}

task createGradleWrapper(type: Wrapper) {
    gradleVersion = '1.7'
    scriptFile = 'startGradle'
}

springBoot {
    mainClass="MainGroovy"
}

```
- The main class and controller is coded in static Groovy (`@CompileStatic`)
- I use embedded jetty (`org.springframework.boot:spring-boot-starter-jetty:0.5.0.BUILD-SNAPSHOT`)
- Run main class directly from IDE (Intellij) without generating any jar file. Main class contains:  

```

 def static main(String[] args){
        SpringApplication.run(MainGroovy, args)
 }

```
- Run the main class above (c-shift-f10). Open browser and test the URL, everything is working.
- Modify some of your Controller class then compile (c-shift-f9).
- refresh browser, nothing change, have to re-run the main class then only changes appear.

I'm sorry if I do something stupid above. Please correct me if I'm wrong.

Thank you.


## YudhistiraArya (07 Sept 2013)

I've just found _Running applications_ section in https://github.com/SpringSource/spring-boot/tree/master/spring-boot-tools/spring-boot-maven-plugin. Does the gradle plugin has the equivalent of `$ mvn spring-boot:run`? 


## dsyer (18 Sept 2013)

Yes the gradle plugin has identical features, but that's about packaging jars and executing them, not hotswapping code in an IDE. Hotswapping chages in a running app is an IDE feature as far as I know, so if it works for other apps it will work for these ones. Unless I'm missing something there is nothing we can do to help in Spring Boot itself. Do you agree?


## YudhistiraArya (19 Sept 2013)

Hi dsyer, 

My mistake for the running from IDE issue, I agree that one is not a Spring Boot issue.

Currently, what I'm trying to achieve here is something similar with `gradle jettyRun` where you can reload the code that you save and compile in IDE (as long as the resulting class file is copied into the correct folder).  Is there anything like that in the current version of Spring Boot's gradle plugin if I use jetty instead of tomcat? I've tried `gradle run` but there's no such task. Could you point me to the correct documentation if such feature exists? 

Thank you.


## yanhua365 (19 Sept 2013)

I agree @YudhistiraArya ,is there some solution?


## dsyer (19 Sept 2013)

Grails uses spring-loaded to reload classes compiled by another process. That should be easy to set up manually (it's just a java agent jar and the docs are pretty good). We were talking about maybe making it a feature (eg command line option) of the Boot tools support, so I guess that would include gradle as well as maven.

I'm not really a gradler, so someone else will have to answer the question of how to run from source with gradle (or you can maybe figure it out by looking at the gradle plugin source code).


## philwebb (23 Sept 2013)

Currently the gradle plugin only supports creating executable JARs. There is no equivalent to the `maven spring-boot:run` goal.

I am not an IntelliJ user but with eclipse at least you only get hot-swap code support when debugging an application. Does running in debug (`Alt + Shift + F9` I think) work for you?


## yanhua365 (23 Sept 2013)

As a IntelliJ user ,it works fine for me.

First add a `application.properties` under resources folder with content:

`spring.thymeleaf.cache: false`

Then,Use  IntelliJ Idea debug the application.

After edit Thymeleaf template or Java, must use CTRL+F9 to make the project.


## dsyer (27 Sept 2013)

There is still an outstanding feature request here (for running from command line with gradle), but since it isn't relevant to the original topic of hot redeploy, I think we can close this issue and come back to the gradle plugin in another if there is demand. I don't think it is difficult to run a java main method from gradle.


## fedotxxl (20 Jan 2014)

It's not clear for me why this ticket has been closed. This ticket is about hot swap and (as I understand) it's still not implemented


## dsyer (20 Jan 2014)

There is already support for reloading static resources with "gradle bootRun". If you are talking about reloading classes, the answer hasn't changed - IDEs have that feature in debug mode, and there is spring-loaded for the command line (and a [separate issue](https://github.com/spring-projects/spring-boot/issues/183) for that as well). So I don't see any reason to re-open this after 4 months.


## DmonUnl (03 Aug 2017)

sorry for posting here, but it seemed relevant.

I followed this link: https://dzone.com/articles/spring-boot-application-live-reload-hot-swap-with (had to alter the first step to do the same thing but with gradle i.e. add
compile("org.springframework.boot:spring-boot-devtools")
to the gradle.build (the correct one, not any random one).

then after finishing the rest of the steps from the link, added this line to application.properties:

spring.thymeleaf.cache= false  (thanks yanhua365)
THEN when running the tomcat servlets, don't just run them, need to run them in debug mode

After doing all of the above, this works by: when servlets are running in intellij -make your UI code change -save it (in my case i was using notepad++ not intellij - go back to intellij (just bring the window into focus) - then go back to browser and refresh the browser


is it possible to do the hot swap/redeploy without bringing IntelliJ into focus? i.e. i would just like to run the tomcat servlets, and then minimise intelliJ and never use it again

## dsyer (03 Aug 2017)

@Dmon1Unlimited this issue was closed almost 4 years ago. Please ask usage questions on Stack Overflow (and read the user guide section on devtools before doing anything).

