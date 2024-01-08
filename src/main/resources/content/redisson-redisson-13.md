#Provide access to the underlying connection manager

Owner: redisson

Repo: redisson

Labels: 

## cleyshan (07 May 2014)

It would be nice if the Redisson class exposed its connection manager.  The use case is to access low level redis commands using the same pool of connections as the high-level redisson abstractions.  Currently its protected and private which means I have to resort to ugly hacks to get to it.


## mrniko (04 Jun 2014)

@cleyshan which commands you invoke in RedisConnection?


## cleyshan (04 Jun 2014)

Currently I am accessing commands like sorted set add, eval, and pop from (unsorted) set. Just a side note, the reason for using sorted set add directly is that we hit issues with the sorted set implementation. After running for a week or two, the scores collapsed to zeros or nead zero (10^-300) and we encountered an infinite loop in redisson when adding (it couldnt settle on a score). So we will manage the score ourselves.


## mrniko (05 Jun 2014)

@cleyshan 
oh, i'll investigate this problem


## mrniko (05 Jun 2014)

@cleyshan could you provide your score calculation algorithm?


## cleyshan (07 Jun 2014)

For us the score aas just a timestamp as we needed elements sorted by time. So nothing special.


## mrniko (07 Jun 2014)

@cleyshan https://github.com/mrniko/redisson/issues/28 fixed. Score calculation algorithm has been changed. You may use it again. Waiting for your feedback.


## craig399 (10 Jun 2014)

Similarly I need access to the connection manager so that I can call the auth() method.  I have a requirement to use authentication.  I have no way to set a password through the API.   I've tried creating a Config object and passing that into the Redisson.create() method but that throws an exception, plus the Config object does not set all the configuration options.  Here is the exception:
java.lang.ArithmeticException: / by zero
    at org.redisson.connection.RoundRobinLoadBalancer.nextClient(RoundRobinLoadBalancer.java:36)
    at org.redisson.connection.ConnectionManager.connection(ConnectionManager.java:119)
    at org.redisson.RedissonMap.put(RedissonMap.java:94)
    .
        .
        .


## mrniko (11 Jun 2014)

@craig399 which version do you use? in Redisson 1.1.0 you could set password via Config.useSingleConnection.setPassword or Config.useMasterSlaveConnection.setPassword


## craig399 (11 Jun 2014)

Thanks for your rapid reply mrmiko.   We are using 1.0.3.    I'll take a look at 1.1.0. And BTW, I was looking at the wrong connection object so having public access to it would  not have worked.  Thanks for you response.


## mrniko (11 Jun 2014)

@craig399 please use version from "master" branch, 1.1.0 has a bugs


## craig399 (11 Jun 2014)

@mrniko Thanks.  I tried the latest master jar file and configured the Config object like this: cfg.useSingleConnection().setPassword("myPassword");   It's still not working.   Here's the stack trace:

com.lambdaworks.redis.RedisException: ERR operation not permitted
    at com.lambdaworks.redis.protocol.Command.complete(Command.java:66)
    at com.lambdaworks.redis.protocol.CommandHandler.decode(CommandHandler.java:68)
    at com.lambdaworks.redis.protocol.CommandHandler.channelRead(CommandHandler.java:51)
    at io.netty.channel.DefaultChannelHandlerContext.invokeChannelRead(DefaultChannelHandlerContext.java:341)
    at io.netty.channel.DefaultChannelHandlerContext.fireChannelRead(DefaultChannelHandlerContext.java:327)
    at io.netty.channel.ChannelInboundHandlerAdapter.channelRead(ChannelInboundHandlerAdapter.java:86)
    at io.netty.channel.DefaultChannelHandlerContext.invokeChannelRead(DefaultChannelHandlerContext.java:341)
    at io.netty.channel.DefaultChannelHandlerContext.fireChannelRead(DefaultChannelHandlerContext.java:327)
    at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:785)
    at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:116)
    at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:494)
    at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:461)
    at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:378)
    at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:350)
    at io.netty.util.concurrent.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:116)
    at java.lang.Thread.run(Thread.java:662)


## mrniko (12 Jun 2014)

@craig399 fixed, try master branch


## craig399 (12 Jun 2014)

@mrniko Thank you so much!.   That's working!


## mrniko (26 Jun 2014)

bug with sortedset fixed


