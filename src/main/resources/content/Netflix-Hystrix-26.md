#Sharded command execution?

Owner: Netflix

Repo: Hystrix

Labels: enhancement 

## benjchristensen (30 Nov 2012)

Explore adding the ability to model HystrixCommand executions after the backend service sharding strategy so that semaphores can be used to restrict concurrent access to any given shard instead of 1 bad shard saturating all available resources for that service.

Here's a use case to demonstrate why each shard can't just be another HystrixCommand semaphore/threadpool at the top level:
- 1 HystrixCommand using 5-10 threads with typical max concurrency of 2-3 threads
- 80+ shards on backend service
- 1 shard becoming latent can saturate the 5-10 threads and prevent any other shards from receiving traffic

It is not a valid approach to have 80 different HystrixCommands dynamically created - the metrics explosion and operations of it is not feasible.

However, we could allow something such as:
- ShardKey getShard(argument) method that returns a key for a given argument
- per shard semaphore that restricts concurrent access to each shard

In practice we could then have the above scenario configured such as:
- 10 threads
- 80 shards
- limit of 2 concurrent per shard
- up to 5 shards can become latent before the entire service is failed


## vadims (27 Nov 2013)

Are there any updates for this feature?


## benjchristensen (05 Dec 2013)

We have not spent any time on this. 


