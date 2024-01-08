#Remove Strategy Injection on HystrixCommand

Owner: Netflix

Repo: Hystrix

Labels: bug 

## benjchristensen (04 Dec 2012)

As part of the opensourcing of Hystrix there was a refactoring of how plugin behavior worked and I ended up making a poor design decision that makes it easy to end up in a bad and non-obvious state.

Namely, when injecting strategies via HystrixCommand.Setter different instances with the same HystrixCommandKey could end up with different strategies.

This sounds "flexible" but becomes very non-obvious, especially when trying to report on metrics.

When showing a dashboard or monitoring something people expect a command with a given key to have the same properties, metrics, concurrency strategy etc not different ones depending on code-path to instantiate.

Worse, the first one to instantiate will currently create a CircuitBreaker for a given HystrixCommandKey.

We don't want multiple circuit-breakers for the same CommandKey just because different strategies were injected in, yet right now that circuit-breaker will use the first properties strategy and thus become confusing if someone does inject multiple different strategies.

Thus, I'm going to make the change in 1.1 to disallow injecting strategies via the HystrixCommand.

This is a "breaking change" if anyone is doing this already but I'm hoping that I'm catching this before anyone has integrated down this path.

If it affects anyone I apologize for this design mistake.


## benjchristensen (05 Dec 2012)

I have removed the injection options in 1.1.0.


