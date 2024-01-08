#mvn package have a error. please help me

Owner: spring-projects

Repo: spring-boot

Labels: 

## zhuzhengwen1983 (07 Aug 2013)

```
D:\my-app\spring-boot>mvn package
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building myproject 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ myproject

---
[WARNING] File encoding has not been set, using platform encoding Cp1252, i.e. b
uild is platform dependent!
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory D:\my-app\spring-boot\src\main\resour
ces
[INFO] skip non existing resourceDirectory D:\my-app\spring-boot\src\main\resour
ces
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ myproject ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ my
project ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory D:\my-app\spring-boot\src\test\resour
ces
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ myproje
ct ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-surefire-plugin:2.15:test (default-test) @ myproject ---
[INFO] No tests to run.
[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ myproject ---
[WARNING] JAR will be empty - no content was marked for inclusion!
[INFO]
[INFO] --- spring-boot-maven-plugin:0.5.0.M1:repackage (default) @ myproject ---

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 6.510s
[INFO] Finished at: Wed Aug 07 13:57:13 PDT 2013
[INFO] Final Memory: 9M/107M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin
:0.5.0.M1:repackage (default) on project myproject: error in opening zip file ->
 [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e swit
ch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please rea
d the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionE
xception
D:\my-app\spring-boot>
```


## dsyer (07 Aug 2013)

Can you share your pom.xml?


## zhuzhengwen1983 (07 Aug 2013)

it's  pom.xml. I copy your example

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>myproject</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <!-- Inherit defaults from Spring Boot -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>0.5.0.M1</version>
    </parent>

    <!-- Add typical dependencies for a web application -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <!-- Package as an executable JAR -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <!-- Allow access to Spring milestones and snapshots -->
    <!-- (you don't need this if you are using the GA release) -->
    <repositories>
        <repository>
            <id>spring-snapshots</id>
            <url>http://repo.springsource.org/snapshot</url>
            <snapshots><enabled>true</enabled></snapshots>
        </repository>
        <repository>
            <id>spring-milestones</id>
            <url>http://repo.springsource.org/milestone</url>
            <snapshots><enabled>true</enabled></snapshots>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <id>spring-snapshots</id>
            <url>http://repo.springsource.org/snapshot</url>
        </pluginRepository>
        <pluginRepository>
            <id>spring-milestones</id>
            <url>http://repo.springsource.org/milestone</url>
        </pluginRepository>
    </pluginRepositories>
</project>
```


## dsyer (07 Aug 2013)

Maybe it's a platform problem. You're on Windows? I'll try it in a VM and see if I get the same. It would help if you could also attach the output from "mvn -X package".  

Please use fencing or other markdown features to format code and logs.


## dsyer (07 Aug 2013)

FWIW it works for me (with an empty maven repo) on Ubuntu. Maybe there's a problem with the native Zip processing? What version of Java etc. are you using (please paste here the output "mvn -version")?


## dsyer (07 Aug 2013)

OK, it works for me with Windows 7 Enterprise, Java 1.6, Maven 3.0.5. Please try clearing your maven cache and starting again (and/or provide the additional information requested above).


## zhuzhengwen1983 (08 Aug 2013)

Thanks dsyer.
my mvn  is 3.0.3
D:\my-app\spring-boot>mvn -version
Apache Maven 3.0.3 (r1075438; 2011-02-28 09:31:09-0800)
Maven home: D:\apache-maven-3.0.3\bin..
Java version: 1.6.0_35, vendor: Sun Microsystems Inc.
Java home: C:\Java\jdk1.6.0_35\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"


## zhuzhengwen1983 (08 Aug 2013)

your example pom.xml no problem??

```
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin
:0.5.0.M1:repackage (default) on project myproject: Execution default of goal or
g.springframework.boot:spring-boot-maven-plugin:0.5.0.M1:repackage failed: Unabl
e to find main class -> [Help 1]
org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal o
rg.springframework.boot:spring-boot-maven-plugin:0.5.0.M1:repackage (default) on
 project myproject: Execution default of goal org.springframework.boot:spring-bo
ot-maven-plugin:0.5.0.M1:repackage failed: Unable to find main class
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor
.java:224)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor
.java:153)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor
.java:145)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProje
ct(LifecycleModuleBuilder.java:84)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProje
ct(LifecycleModuleBuilder.java:59)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.singleThreadedBu
ild(LifecycleStarter.java:183)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(Lifecycl
eStarter.java:161)
        at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:318)
        at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:153)
        at org.apache.maven.cli.MavenCli.execute(MavenCli.java:555)
        at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:214)
        at org.apache.maven.cli.MavenCli.main(MavenCli.java:158)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.
java:39)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAcces
sorImpl.java:25)
        at java.lang.reflect.Method.invoke(Method.java:597)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Laun
cher.java:290)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.jav
a:230)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(La
uncher.java:414)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:
357)
Caused by: org.apache.maven.plugin.PluginExecutionException: Execution default o
f goal org.springframework.boot:spring-boot-maven-plugin:0.5.0.M1:repackage fail
ed: Unable to find main class
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(Default
BuildPluginManager.java:115)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor
.java:208)
        ... 19 more
Caused by: java.lang.IllegalStateException: Unable to find main class
        at org.springframework.boot.loader.tools.Repackager.buildManifest(Repack
ager.java:174)
        at org.springframework.boot.loader.tools.Repackager.repackage(Repackager
.java:133)
        at org.springframework.boot.loader.tools.Repackager.repackage(Repackager
.java:116)
        at org.springframework.boot.maven.RepackageMojo.execute(RepackageMojo.ja
va:92)
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(Default
BuildPluginManager.java:106)
        ... 20 more
[ERROR]
[ERROR]
[ERROR] For more information about the errors and possible solutions, please rea
d the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginExecutio
nException
```


## philwebb (08 Aug 2013)

It looks like maven is not finding any sources to compile

> [INFO] No sources to compile

Do you have a .java file in `src/main/java`?


## zhuzhengwen1983 (08 Aug 2013)

thanks philwebb & dsyer.  I missing the src/main/java file


