#Calls to LifecycleAware.{onStart, onShutdown} are not wrapped in an exception handling block [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 14](http://code.google.com/p/disruptor/issues/detail?id=14) moved from a Google Code project.
Added by 2011-11-01T21:04:19.000Z by [adam.ros...@gmail.com](http://code.google.com/u/107351689794595170938/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Fixed).

Original labels: Type-Defect, Priority-Medium
### Original description

```
<b>What steps will reproduce the problem?</b>
1. Throw an exception in onStart 


<b>What is the expected output? What do you see instead?</b>
I'd like to see a hook for handling these exceptions. See the additional information below for how I handle exceptions at any point of the event handler's lifetime consistently.


<b>What version of the product are you using? On what operating system?</b>
2.6, various flavors of Linux.


<b>Please provide any additional information below.</b>
I am using the Disruptor in Scala. I ended up building a mixin trait that is mixed in to implementors of LifecycleAware at declaration time. 

The mixin surrounds calls to onStart/onShutdown methods of the decorated class with a try/catch. Its exception handler defaults to logging the exception but can be overridden.

trait BackgroundThreadExceptionHandler {
    def onException(exception: WrappedException)
  }

I wire the custom exception handler into the disruptor by adapting it to the disruptor's exception handler. The handle(exception, sequence, event) method builds an instance of the WrappedException type above from the parameters and invokes the custom handler. This allows me to achieve consistent error handling for the lifetime of onStart/run/onShutdown.
```


