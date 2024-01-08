#Yammer Metrics Support

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## zeedunk (29 Nov 2012)

At Simple, we use Yammer's metrics pretty heavily and we're very interesting in Hystrix but would prefer to use the same metrics tools there that we do elsewhere in our services. It would be nice be able to optionally provide other implementations to HystrixCommand.

I'm happy to do the work if this it something you're OK with.


## benjchristensen (29 Nov 2012)

Thank you for your interest and willingness to contribute.

Hystrix supports plugging in different implementations of metrics, properties, concurrency and event notification strategies. You can read more about it here: https://github.com/Netflix/Hystrix/wiki/Plugins

In particular take a look at the Metrics Publisher section of that page.

Having an implementation of the MetricsPublisher for Yammer's Metrics (https://github.com/codahale/metrics) is something I have definitely wanted but haven't had the chance to get to yet myself.

I'd be happy to have your help. If you can fork and create a branch with the implementation and submit it back I'll accept it in as a submodule of hystrix-contrib.

https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib

I envision a module such as hystrix-contrib/hystrix-metrics-publisher-yammer or hystrix-contrib/hystrix-metrics-publisher-codahale ... something along those lines.

The intention is to support as many plugins as people need in the hystrix-contrib submodules.

The class to implement is: http://netflix.github.com/Hystrix/javadoc/index.html?com/netflix/hystrix/strategy/metrics/HystrixMetricsPublisher.html

For reference, the default implementation that uses Servo is at https://github.com/Netflix/Hystrix/blob/master/hystrix-core/src/main/java/com/netflix/hystrix/strategy/metrics/HystrixMetricsPublisher.java


## benjchristensen (04 Dec 2012)

I'm thinking of moving the default implementation to hystrix-contrib and by default not having a metrics publisher so that hystrix-core doesn't have a dependency on Servo.

I will do this in 1.1. It's a slight breaking change if someone is actually using Servo but I think it will be worth it to be cleaner for the very common case of metrics publishing being done without Servo.


## benjchristensen (05 Dec 2012)

Committed in https://github.com/Netflix/Hystrix/commit/767cb9cb6ab3ac13e6cf9294b84ccaa6b74d0149


## benjchristensen (07 Dec 2012)

zeedunk, 

Would you be interested in working with me on implementing a module for Yammer Metrics?


## zeedunk (07 Dec 2012)

hey @benjchristensen, yeah, I very much would. Sorry I've been quiet on this issue lately. Got deep into some other issues and hadn't circled back yet.

How would you like to proceed with the effort?


## chadsmall (07 Dec 2012)

+1 on this one.  We are going down a similar path at @bloomhealth... dropwizard w/ Yammer Metrics and we're going to circuit break everything with HystrixCommand's.  Would be nice if we can leverage a consistent metrics collection library.


## benjchristensen (07 Dec 2012)

I envision creating a new hystrix-contrib module similar to how I have done for Servo: https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-servo-metrics-publisher

@zeedunk, if you would be willing to fork and create a new branch and create a new module for this, we can review it together (thanks @chadsmall for getting involved) then do a pull request and I'll accept it into the Hystrix project as a module anyone can use and document it on the Wiki.

Would the following name make sense?

/hystrix-contrib/hystrix-yammer-metrics-publisher

Or does it need to be the following?

/hystrix-contrib/hystrix-codahale-metrics-publisher

The base strategy to be implemented is nothing more than a factory class that constructs publishers for each HystrixCommandKey and HystrixThreadPoolKey as needed. 

You can see the Servo implementation here: https://github.com/Netflix/Hystrix/blob/master/hystrix-contrib/hystrix-servo-metrics-publisher/src/main/java/com/netflix/hystrix/contrib/servopublisher/HystrixServoMetricsPublisher.java

The real work is all done in the HystrixMetricsPublisherCommand and HystrixServoMetricsPublisherThreadPool implementations.

For example:

https://github.com/Netflix/Hystrix/blob/master/hystrix-contrib/hystrix-servo-metrics-publisher/src/main/java/com/netflix/hystrix/contrib/servopublisher/HystrixServoMetricsPublisherCommand.java

The concept of the MetricsPublisher is that it just hands off instances of everything needed to obtain metrics and lets the implementation choose how to publish them - push, pull, low or high latency.

The Servo model is that it creates a wrapper around each metric which is then polled via Servo.

I believe Metrics works similarly based on the documentation I've read.


## zeedunk (08 Dec 2012)

Sounds great. I'll get started soon.


## fkjellberg (09 Dec 2012)

I've added a pull request for a module to publish the metrics to Yammer Metrics. I used the module name hystrix-yammer-metrics-publisher as Ben suggested.


## benjchristensen (10 Dec 2012)

Thanks @fkjellberg 

Does the pull request look good to you @zeedunk ?


## zeedunk (10 Dec 2012)

Yeah, +1. Thanks @fkjellberg!


## benjchristensen (10 Dec 2012)

I have merged the pull request. I'm closing this issue out. If any functionality issues exist with this module please file new issues.

Thank you @fkjellberg for coding it and @zeedunk and @chadsmall for your involvement.

I'll try and get to adding references to the documentation later this week for this new module.


## benjchristensen (11 Dec 2012)

It is now available on Maven Central and I've added documentation to the Wiki and the module README: https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-yammer-metrics-publisher


## fkjellberg (11 Dec 2012)

@benjchristensen Thanks for taking care of the pull request and pushing out a release so quickly!


## benjchristensen (11 Dec 2012)

You're welcome, I appreciate your willingness to contribute to the project. Let me know anytime if you have more thoughts on how to improve or new use cases not yet considered.


