#Ill-advised or mistaken usage of core class (java.* or javax.*)

Owner: greenrobot

Repo: EventBus

Labels: 

## ashokgelal (28 Feb 2013)

I get Android Dex: [lightpaper] trouble processing "javax/xml/namespace/QName.class" during compilation when I add EventBus as a maven dependency:

```
<dependency>
        <groupId>de.greenrobot</groupId>
        <artifactId>eventbus</artifactId>
        <version>2.0.1</version>
</dependency>
```

I also tried excluding EventBus's dependencies:

```
<exclusions>
            <exclusion>
                <artifactId>junit</artifactId>
                <groupId>junit</groupId>
            </exclusion>
            <exclusion>
                <artifactId>android</artifactId>
                <groupId>android</groupId>
            </exclusion>
            <exclusion>
                <groupId>com.google.android</groupId>
                <artifactId>support-v4</artifactId>
            </exclusion>
            <exclusion>
                <artifactId>android-test</artifactId>
                <groupId>com.google.android</groupId>
            </exclusion>
            <exclusion>
                <groupId>com.google.android</groupId>
                 <artifactId>annotations</artifactId>
            </exclusion>
</exclusions>
```

But no luck, I get the same error.

I'm using intelliJ 12 but I don't think that makes any difference.


## greenrobot (01 Mar 2013)

Can you investigate which artifact and class is using javax/xml/namespace/QName.class ?


## ashokgelal (01 Mar 2013)

I tried excluding all the dependencies but still kept getting the same error. I'm now using the jars and it works without any problems. 

BTW, great library and works better than otto in many cases. Some examples would be great for starters.


## greenrobot (01 Mar 2013)

If you cannot supply additional information, I have to close this ticket. Is there no stacktrace?


## ashokgelal (01 Mar 2013)

This is the message I get:

> Ill-advised or mistaken usage of a core class (java.\* or javax.*)
> when not building a core library.
> 
> This is often due to inadvertently including a core library file
> in your application's project, when using an IDE (such as
> Eclipse). If you are sure you're not intentionally defining a
> core class, then this is the most likely explanation of what's
> going on.
> 
> However, you might actually be trying to define a class in a core
> namespace, the source of which you may have taken, for example,
> from a non-Android virtual machine project. This will most
> assuredly not work. At a minimum, it jeopardizes the
> compatibility of your app with future versions of the platform.
> It is also often of questionable legality.
> 
> If you really intend to build a core library -- which is only
> appropriate as part of creating a full virtual machine
> distribution, as opposed to compiling an application -- then use
> the "--core-library" option to suppress this error message.
> 
> If you go ahead and use "--core-library" but are in fact
> building an application, then be forewarned that your application
> will still fail to build or run, at some point. Please be
> prepared for angry customers who find, for example, that your
> application ceases to function once they upgrade their operating
> system. You will be to blame for this problem.
> 
> If you are legitimately using some code that happens to be in a
> core package, then the easiest safe alternative you have is to
> repackage that code. That is, move the classes in question into
> your own package namespace. This means that they will never be in
> conflict with core system classes. JarJar is a tool that may help
> you in this endeavor. If you find that you cannot do this, then
> that is an indication that the path you are on will ultimately
> lead to pain, suffering, grief, and lamentation.

Seems like it is a common error on android. Here is a discussion thread on SO: http://stackoverflow.com/q/7998413/33203

Again, this only happens with maven dependencies not with jars.


## greenrobot (01 Mar 2013)

Could you please check 2.0.2-SNAPSHOT from the Sonatype snapshot repository? (https://oss.sonatype.org/content/repositories/snapshots/)

a3da30d2aaf09bca9ac234eed5e144185939f961 All dependencies were removed from the EventBus POM because Gradle doesn't support provided. 


## ashokgelal (01 Mar 2013)

Can do. I'll let you know.


## greenrobot (02 Mar 2013)

Did you configure the snapshot repo? Example from http://dev.clojure.org/display/doc/Maven+Settings+and+Repositories:

In a Maven project's pom.xml file:

``` xml
<repositories>
  <repository>
    <id>sonatype-oss-public</id>
    <url>https://oss.sonatype.org/content/groups/public/</url>
    <releases>
      <enabled>true</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```


## ashokgelal (02 Mar 2013)

I figured that out. Sorry, I'm not a Maven expert.
I can confirm that 2.0.2-SNAPSHOT works. No compilation errors.


## greenrobot (02 Mar 2013)

Thanks a lot!

2.0.2 is on it's way to Maven Central...


