#Deprecate TruffleRuntime.getCallTargets()

Owner: oracle

Repo: graal

Labels: oracle-emp on hold 

## woess (04 Feb 2016)

We should not expose the collection of all call targets in the TruffleRuntime. If we still need a way to access all call targets, consider moving it to implementation classes.


## jtulach (09 Feb 2016)

Can the deprecation include a sample how to get list of "all" CallTargets
now? I guess it would require new instrumentation API and observing/querying all RootNodes?

----- Reply message -----
Od: "Andreas Woess" notifications@github.com
Komu: "graalvm/truffle" truffle@noreply.github.com
Předmět: [truffle] Deprecate TruffleRuntimegetCallTargets (#37)
Datum: čt, úno 4, 2016 15:41

&mdash;
Reply to this email directly or view it on GitHub.


## chumer (15 Feb 2016)

The current instrumentation API does reference the root nodes itself. So this is kind of redundant atm. I' appreciate if we could wait a bit with deprecating this. We might need it in the instrumentation framework for querying available sources, not just executed ones.


## woess (15 Feb 2016)

sure, that's why I assigned you, @chumer :)


## chumer (18 Jul 2016)

It got deprecated with the latest addition of source listeners to the instrumentation API.


