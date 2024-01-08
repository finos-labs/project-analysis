#ConnectionWatchdog not works as expected

Owner: redisson

Repo: redisson

Labels: 

## renzihui (26 May 2014)

Hi Nikita, when I ran some load test, ConnectionWatchdog did not restore PubsubConnection correctly (my env is redis 2.8.3, java 1.7.0_45,Netty 4.0.19 final, OSX 10.9.2). After some digging, I found a couple of problems,
1) When ChannelInitilizor ChannelInitializer.initChannel is called,           pipeLine.get(CommandHandler.class) and pipeLine.get(RedisAsyncConnection.class) return null, I guess the old ChannelPipeline had been destroyed.
2) The connection retry and backoff doesn’t seem right, the connection attempt seems only fired when the channel is inactive, if re-connect did not work, no further attempt will be fired.
3) RedissonTopic.promise.get().setSuccess(true) will throw IllegalStateException: complete already when PubsubConnection is re established.
4)Though this is not a bug, it seems unnecessary to use a dedicated HashedTimeWheel to schedule reconnection task.

I will push a patch later.


## renzihui (26 May 2014)

Close this one, use 22 instead.


