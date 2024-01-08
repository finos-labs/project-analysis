#Question: shortCircuit contributes to error count?

Owner: Netflix

Repo: Hystrix

Labels: question 

## ivarley (06 Dec 2012)

Hey Ben, quick question. (Great lib, BTW!)

I see that when you register a short circuit event through the metrics object, it contributes to the errorPercentage (HystrixCommandMetrics.getHealthCounts), which is in turn used by the circuit breaker (HystrixCircuitBreaker.isOpen) to determine if the error rate is high enough to trip the circuit. 

But while the circuit is tripped, _every_ request is registering as a shortCircuit, and thus, as an error (for this health calculation). Doesn't that mean you're artificially inflating how many real "errors" are happening? I guess it doesn't really matter, because as soon as there's one success (via allowSingleTest), you reset the counter anyway, so it didn't matter (to the circuit breaker) what you were recording as metrics while it was tripped.

So from an external perspective, they're errors (the caller didn't get what they wanted),  but from the Circuit Breaker's perspective they really might as well be ignored. Am I grokking that right?

Thanks!
Ian


## benjchristensen (06 Dec 2012)

Hi Ian, 

Yes you've got it right. We want to track short-circuits as errors externally. (You can see the blue number in this screenshot representing short-circuited requests: https://github.com/Netflix/Hystrix/wiki/images/ops-getbookmarks-640.png)

This approach works because of what you said: we reset the metrics when the circuit closes.

It's a long-standing irritant to me that it causes a full reset of the rolling data when the circuit comes back to health and something I want to change so that we don't lose the rolling counts during that time. It's not a huge deal as we're talking about seconds of data but it's still not ideal.

See this line for the TODO in code: https://github.com/Netflix/Hystrix/blob/master/hystrix-core/src/main/java/com/netflix/hystrix/HystrixCircuitBreaker.java#L143

The correct approach that I eventually will get to is to maintain slightly different views of the metrics – internal and external – so that the external facing metrics don't need to be reset just to accommodate the circuits view of the world.

I want to do it without duplicating all of the counters though and just haven't spent the time to figure that out yet in a way that doesn't feel like a hack and confuse everyone (including myself after a month or two away from it).

Also, I want to allow more sophisticated implementations of circuit breakers (https://github.com/Netflix/Hystrix/issues/9) which would benefit from a cleaner separation of internal and external metrics. (In a large cluster a more sophisticated circuit breaker actually isn't needed but I can see why certain use cases could want or benefit from it.)


## ivarley (07 Dec 2012)

Awesome, thanks!


