#Add an easy work to work out if you are on an optimizing runtime

Owner: oracle

Repo: graal

Labels: oracle-emp truffle 

## chrisseaton (13 Feb 2016)

We want to avoid people running JRuby+Truffle on a standard JVM, or even a JVM with Graal but the wrong flags, and being disappointed about performance. When JRuby+Truffle starts we check that the `TruffleRuntime` is a Graal runtime, and if it isn't we present a warning. Doing this requires looking at the undocumented runtime name.

``` java
Truffle.getRuntime().getName().toLowerCase(Locale.ENGLISH).contains("graal");
```

I wonder if it would be useful to add a method `boolean isOptimizing()` to `TruffleRuntime`. This hides the implementation detail that it is Graal being used, and leaves the door open for any other hypothetical optimising Truffle runtimes, while still allowing us to show our warning.

Another option would be the inverse - `boolean isInterpreter()`.


## chrisseaton (14 Feb 2016)

I'm fine with `isCompilingRuntime`. I'll leave this open for a few days to see if anyone else has any opinions.


## woess (14 Feb 2016)

I'd propose `isOptimizingRuntime()`.


## chrisseaton (14 Feb 2016)

Maybe `doesOptimize` or `doesCompile`?


## jtulach (17 Feb 2016)

There already is `isProfilingEnabled` and as far as I know it does exactly what is being requested.


## chrisseaton (17 Feb 2016)

I didn't know about that. I don't think it's exactly the same thing, as you could chose to run Graal with optimisation but no profiling, but I think you're right I can repurpose for a basic check to see if people have got Graal.


## smarr (17 Feb 2016)

I would also like to have a proper `isOptimizingRuntime()`. I think this is very useful to avoid issues on the user level.

And `isProfilingEnabled()` is not the right way forward. In Graal it depends on `TruffleCompilerOptions.TruffleProfilingEnabled`. And, its name and documentation very clearly state that it is something different.


## woess (17 Feb 2016)

IMHO, `isProfilingEnabled` isn't really a property of the runtime but should be an independent option, e.g. `TruffleOptions.Profiling`. I'd rather have an `isOptimizingRuntime()` instead of `isProfilingEnabled()`. @chumer may have a different opinion.


## chumer (18 Feb 2016)

@woess yes my opinion is that these things should not be merged.


## jtulach (19 Feb 2016)

How many more `isXYZ` methods do you guys plan to introduce into TruffleRuntime? It seems to me that there is some duplication in having both `isProfilingEnabled` and `isOptimizingRuntime`.

If "isProfilingEnabled isn't really a property of the runtime but should be an independent option" - then current `isProfilingEnabled == TruffleOptions.Profiling && isOptimizingRuntime()` - how do we get rid of the redundant `isProfilingEnabled` method in `TruffleRuntime` then? 


## smarr (19 Feb 2016)

@jtulach it is `TruffleCompilerOptions.TruffleProfilingEnabled`. Thus, there is zero redundancy here, because the configuration is in `com.oracle.graal.truffle` and not part of the Truffle API.


## smarr (19 Feb 2016)

The real question is rather should this be part of `TruffleRuntime` or `TruffleOptions`. But, both information are definitely needed.


## jtulach (22 Feb 2016)

Configuring Truffle API behavior by options located in `com.oracle.graal.truffle` requires us to create an API channel between the Truffle API itself and `com.oracle.graal.truffle` module. The more thin (e.g. less duplicated) the channel is, the less problems we will have when finding the right version of Truffle API and `com.oracle.graal.truffle` that work together.


## chumer (22 Feb 2016)

The alternative of having ´isProfilingEnabled´ is to call in the TruffleRuntime for each profile individually. Besides beeing verbose it is also not extensible because also guest languages might do things differently if profiling is not enabled (it might disable all kinds of stuff). Graal runtimes still want to disable isProfilingEnabled for benchmarking its overhead. So merging isProfilingEnabled and isOptimizingRuntime would mean loosing this ability.


## woess (22 Feb 2016)

isOptimizingRuntime()/isCompilingRuntime() is a way to tell whether the runtime can optimize/compile Truffle code. I think we should have such a method so that you don't have to resort to fragile hacks like using getName() or getClass().getName() to get to this information.

Profiling on the other hand could be enabled/disabled regardless of the runtime, potentially even more fine-grained (e.g. per CallTarget). I'm not sure we need isProfilingEnabled() in TruffleRuntime at all, but I'm not proposing to change anything there right now.


## jtulach (24 Feb 2016)

My preferred long term solution:
- have isOptimizingRuntime()/isCompilingRuntime()
- remove isProfilingEnabled()
- add TruffleOption to control profiling being enabled - by default returning value of isOptimizingRuntime()

On a general note: TruffleRuntime is interface. Adding methods to it complicates everything. Callers need to ready for the method not being there and throwing an error when called.


## chrisseaton (24 Feb 2016)

Happy with the long term solution.

> TruffleRuntime is interface. Adding methods to it complicates everything.

In general yes, but not in this case, right? It's an interface that only we implement, and only in Truffle and Graal, so it doesn't matter that we're adding methods to it unless we care about a hypothetical alternative `TruffleRuntime` that someone else is developing right now.


## chumer (24 Feb 2016)

@chrisseaton  it does matter because it binds the truffle graal runtime to a specific version. So newer versions cannot run with older Truffle and vice versa.


## chrisseaton (24 Feb 2016)

Ah right yes of course.


## chumer (24 Feb 2016)

@jtulach Your long term plan sounds good. Fits all the requirements. GL devs can query the TruffleOption and the TruffleRuntime flag to develop their own profiles.


## chumer (03 Apr 2018)

Current solution is to check for checking Graal keyword in TruffleRuntime#getRuntime() and Engine#getImplementationName(). 

