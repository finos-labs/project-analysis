#Promises vs Futures?

Owner: redisson

Repo: redisson

Labels: 

## brotherdetjr (14 Feb 2014)

Hi there!
As I understand, Future returned by RedisAsyncConnection moves API user back to the blocking world. Java futures are NOT non-blocking by nature, they have blocking get(). Thus API user needs to spawn new threads and say hello to threading overhead again. Then why Netty is used?
I propose to return promises (Java's 8 CompletableFuture, Guava's ListenableFuture or jdeferred's promises).

Thanks,
Denis


## mrniko (14 Feb 2014)

return where? there are could be multiple operations in one method (like in SortedSet.add) and such operations should invoked in strong sequence.


## mrniko (02 Jun 2014)

fixed


