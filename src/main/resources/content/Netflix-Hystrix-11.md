#Asynchronous Executables

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (27 Nov 2012)

Support wrapping asynchronous clients and network access.

Concepts right now are HystrixSelector and/or HystrixFuture objects that could wrap asynchronous calls.

Thread isolation would obviously not make sense in these cases, but semaphore isolation to limit concurrent access, timeouts (such as an existing Future.get being wrapped), callback timeouts, circuit-breakers, metrics, monitoring, properties all make sense.


## benjchristensen (21 Dec 2012)

The intention here is to provide first-class support of dependencies that are already asynchronous (Futures, NIO, etc). There would still be queue() and execute() methods on these new top-level objects (HystrixFuture or HystrixAsyncCommand, HystrixSelector, etc). 

All of them would implement HystrixExecutable like HystrixCommand does (https://github.com/Netflix/Hystrix/blob/master/hystrix-core/src/main/java/com/netflix/hystrix/HystrixExecutable.java) with execute() and queue().

The HystrixCommand.execute() method doesn't really get involved in this - it is a public API method that provides a blocking call to what is generally a asynchronous execution (using thread isolation, not semaphore), the equivalent of queue().get(). 


## codefromthecrypt (21 Dec 2012)

Ahh cool. That works!


## johngmyers (05 Mar 2013)

If you accept a dependency on RxJava, then I would question the need for these new top-level objects to implement HystrixExecutable. If the new top-level object returns an Observable, the caller could use `Observable.single()` to collapse it into a synchronous call.


## rore (14 Mar 2013)

+1 for this feature.


## amangup (24 Oct 2013)

is this is in development now?


## neerajrj (24 Oct 2013)

Yes we are actively working on it. I  don't have a firm ETA yet though

On Thu, Oct 24, 2013 at 4:28 AM, amangup notifications@github.com wrote:

> is this is in development now?
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/Netflix/Hystrix/issues/11#issuecomment-26984519
> .


## siddharthsa (16 Dec 2013)

Hi, 
We are eagerly waiting for this to be released. When can we expect this to see the light of the day?


## benjchristensen (17 Dec 2013)

It's sitting in my TODO list to do a code review. Theoretically it's functional and coded but not reviewed or fully tested.


## benjchristensen (17 Dec 2013)

Are you interested in reviewing and testing if we share it publicly?


## amangup (17 Dec 2013)

Yes....we should will be able to test and review it. Thanks!


## siddharthsa (18 Dec 2013)

Hi Ben,
We can test and review it. But we would like to understand from you how detailed testing you want and also for code review part it would be better if someone more experienced with hystrix also take a look at it.


## benjchristensen (28 Dec 2013)

I'm still the bottleneck on this. I hope to catch up in the coming weeks as I have a few things waiting on this.


## yngbldjr (30 Jan 2014)

Bump... What's the status on this?  I would also be interested in this feature as well as testing with a asyn framework like vertx.io.  I love a the built in functionality of Hystrix, and would like to utilize it in a existing vertx application to provide the much needed metrics and dashboards.


## benjchristensen (30 Jan 2014)

We are canary testing Hystrix 1.4 in production with these changes. The most significant work is now complete. Before releasing I need to do some further refactoring to clean some things up and another round of larger production canary testing. I imagine it's still several more weeks, but no longer months before it's releasable. 

Once we hit our production canary status I could publish a release candidate (1.4.0.RC1) if that interests you?


## yngbldjr (30 Jan 2014)

Yes, that would be great.  Hystrix is a great framework and being able to leverage it in existing code would be something I would be interested in.  Thanks for the reply, and appreciate the work.


## hartzler (01 Feb 2014)

+1 would love an early peek at this as well!


## benjchristensen (05 Feb 2014)

I just posted a pull request with the current state of this code.


## yngbldjr (05 Feb 2014)

Awesome!  Thanks!


## p14n (15 Feb 2014)

Cheers Ben - looks good.  Used this to create an example of Hystrix with vert.x, found no issues.
https://github.com/p14n/vertx-hystrix


## benjchristensen (17 Feb 2014)

Thanks for taking the time to review. There are a few issues I'm still working on so it's not yet ready for production.


