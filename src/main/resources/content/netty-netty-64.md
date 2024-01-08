#Use java.util.logging or SLF4J

Owner: netty

Repo: netty

Labels: improvement 

## trustin (17 Nov 2011)

We don't need to support JBoss Logging anymore in Netty 4.  It means we can simply using widely adopted logging framework and get rid of our thin logging layer.  There are two candidates:
- `java.util.logging`
- SLF4J

SLF4J has much nicer API, but it's somewhat difficult to configure for a novice.  `java.util.logging`'s API is pretty ugly, but it will let us continue being a zero-dependency library.  What do you prefer and why?  I'm leaning toward `java.util.logging` because of zero dependency is so attractive and we do not log very much.


## alepar (22 Nov 2011)

IMO, as long as netty supports redirecting to slf4 w/o serious performance degradation - it's fine with me.
If you want to retain that zero-dependency feat - just go with modernizing existing layer - also fine with me as long as the above holds.

PS Maybe a good feature is to scan for slf4j in classpath - and if found - use it. If not - switch to existing layer.


## normanmaurer (22 Nov 2011)

"crazy" classpath scanning/modifications should be avoided, IMHO...


## alepar (22 Nov 2011)

Agreed, but think in this case it's not that crazy. Just checking if you can instantiate slf4j's LogFactory should be enough. No scanning or modification is needed.

Btw, slf4j does similar thing, when it chooses it's implementation in runtime.


## normanmaurer (22 Nov 2011)

Yeah sure.. Maybe "crazy" was not the right word here ;) 


## alepar (22 Nov 2011)

=)


## rzorzorzo (25 Nov 2011)

Hello,

Just found this thread. It is a pity that this discussion was not on the mailing list.

I am the author of YAJSW on sourceforge. And I am using netty within this project and many other projects.

I very much like the current concept of netty logging using the InternalLogger. And have moved to using it within my project.

I will be very unpleased if the logging within netty were changed.

Netty is a framework which is to be used within other projects/products/frameworks. A framework mostly has to coexist with other frameworks and this is where logging becomes an issue or may even become a show killer. Problems arise, when one framework requires a specific framework with a specific version, while another framework requires the same logging framework, but a different and incompatible version.

Concerning  the discussion java.util.logging vs slf4j:

 java.util.logging the LoggerFactory is initialized on class loading in a static statement. Different frameworks may require/assume different factories. Jboss for example will crash if a different factory, then the one expected, is initialized.
(see: http://sourceforge.net/projects/yajsw/forums/forum/810311/topic/4789821/)

slf4j is classpath based. further, it only wraps the logging part, but not the configuration of the logging. Frameworks which will do their own configuration will need a specific slf4j lib on the classpath. Now imagine you have 2 frameworks, one requiring the slf4-log4j and another slf4j-jdk. Only one of these will be used and one of the frameworks will crash.

The current solution provided within netty is the best possible solution:
- no dependency
- no assumption
- provide adapters, so the user of the framework can use any logging he requires.

Currently, per default, netty uses java.util.logging. This means that when the LoggerFactory is loaded it will load the class java.util.logging.Logger, event if it is not being used. This leads to the initialization of the java.util.logging.LoggerFactory.

To avoid this I have introduced a SimpleLogger, which prints all loggings to System.out. And changed the default logging within netty to use this type of logger.

-- Ron


## garethcollins (25 Nov 2011)

@rzorzorzo,

Why would you want to use more than one logging configuration for your application? If you need to separate logs into different files you can do that via the log4j or logback appenders.

And what do you mean "it crashes"? Can you be more specific? Do you mean a circular dependency is created (e.g. including log4j and log4j-over-slf4j in your classpath is not a good idea)?

I don't mean to be rude in saying this (it you think I am please forgive me), but I don't understand these problems which are said to occur with slf4j. I quite happily mix many open source projects together, many built using different versions of SLF4J (or log4j or commons logging) and I have never had an issue sending all logs through SLF4J. It doesn't mean there aren't issues, but I am surprised considering that more and more open source projects are moving or have moved to use it (such as the JBOSS Hibernate project). If it is good for a JBOSS project like Hibernate (JBOSS server also includes slf4j bindings by default), why should netty be different?


## rzorzorzo (26 Nov 2011)

@garethcollins

> Why would you want to use more than one logging configuration for your application?

This does not depend on me but on the frameworks I need to use.

Imagine I am writing an application which need to use different commercial frameworks. That is, frameworks for which I have no source code and for which I cannot change the source. For example a framework for encryption and a framework for bluetooth. Now both use slf4j and, the author of the first framework expects slf4j-log4j.jar on its classpath and the author of the second expects slf4j-jdk14.jar. If we include both jar files in the classpath one of the frameworks will throw a class not found exception. This is due to the way in which slf4j works: 

to use slf4j you need to import org.slf4j.LoggerFactory. This class will load the class StaticLoggerBinder. This class is found in both slf4j-log4j.jar and slf4j-jdk14.jar. Java will load StaticLoggerBinder from the first jar it finds on the classpath. There is no simple way to use both slf4j jars in a single application without recurring to multiple class loaders, which will make things very complicated.

And what do you mean "it crashes"? 

This could mean different things depending on the framework. Some frameworks will just call System.exit if they cannot initialize or configure the logger. Others will catch the Exception and will continue throwing null pointer exceptions because the logger is null. Others will work without logging.

concerning incompatibilities with different versions of slf4j: google "slf4j incompatible version".

I therefore recommend that a framework, in order to be able to coexist with other frameworks within an application should not depend on any logging framework.

The way netty is currently implemented is a very good example of this independence. I wish that other frameworks would be the same. Netty uses its InternalLogger which does not depend on any logging framework. It also comes with adapter classes which map the InternalLogger to other logging frameworks.

I have been using slf4j in the past and was at first very impressed with it until I run into the issues described above.

-- Ron


## garethcollins (26 Nov 2011)

@rzorzorzo,

Thanks very much for your response. I guess I am still a little confused here. How could a framework be dependent on slf4j-log4j.jar (or slf4j-jdk14.jar)? This is the internal mapping of slf4j calls to log4j calls (or jul calls) and is not supposed to be used directly by developers.

Or do you mean that these frameworks are directly setting log4j/jul configuration (i.e. are manipulating the standard log4j/jul appenders internally in code or tightly coupling their own proprietary log4j/jul appenders in code)?

If yes, I would argue that this is not an slf4j issue. These frameworks are using the logging frameworks incorrectly, especially since they are libraries to be used as part of a much larger application.


## dharrigan (30 Nov 2011)

+1 logback (with slf4j as the plugin...)


## leomrlima (08 Dec 2011)

+1 for slf4j


## dansimpson (09 Dec 2011)

+1 slf4j-api only


## kimchy (09 Dec 2011)

-1 slf4j, +1 to stay with current solution. If netty moves to slf4j, it will force elasticsearch (the project I develop) to move to slf4j, a library should not cause that... . I am sure Ceki will be happy, with the LGPL logback, but many won't.


## ngocdaothanh (10 Dec 2011)

+1 for slf4j-api
Is elasticsearch using log4j?


## marchywka (10 Dec 2011)

I guess I would just mention that my first contact with netty was complicated because the logging system in the exception handler threw an Error, not just another exception, and it took a while to figure out. I happen to have strong suspect on where the problem was but otherwise would have taken forever to figure out. So, whatever you do I suggest that it be robust and not have more confusing failure modes that just obscures whatever problem you were trying to log. LOL. An internal API patterned after popular ones may make sense as long as it doesn't perturb things much. Asynchronous writes may be important to preserve time dependent problems but you would like to have durable logs to make sure you really got the last words before apps dies. By default if it just dumps to system.out that would work for me...


## kimchy (10 Dec 2011)

@ngocdaothanh elasticsearch uses a similar internal logging abstraction as netty, but it adds a bit to it


## qmx (10 Dec 2011)

on jruby, we are using a thin layer too (https://github.com/jruby/jruby/blob/master/src/org/jruby/util/log/LoggerFactory.java)


## marchywka (10 Dec 2011)

I just wrote a quick impl of InternalLogger that looks like this. It also extends InternalLoggingFactory and makes itself :)

```
/**
 * Logs a WARN level message.
 */
```

public
    void warn(String msg) { pr(5, msg); }

private static void pr(int l, String m)
{ System.out.println(""+l+" "+m); }


## normanmaurer (26 Feb 2012)

After thinking a bit more about all the stuff I think we should just keep our current logger api. I don't think there are really any advanced of switching to slf4j beside of eliminating some code. 


## trustin (27 Feb 2012)

Let's just modernize our current logging layer.


