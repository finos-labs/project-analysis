#CollapserMetrics

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (27 Nov 2012)

The HystrixCollapser received some new functionality in recent months that should be exposed in its own metrics but hasn't yet.

Let's expose a new metrics publisher similar to the ones for Command and ThreadPool.

Example metrics:
- collapser executions
- number of batches
- number of collapsed requests per batch
- number of responses from cache
- number of shards


## mattrjacobs (28 Jan 2015)

Closed by #576 


