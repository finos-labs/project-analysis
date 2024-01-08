#Publish message method should be moved out from RTopic

Owner: redisson

Repo: redisson

Labels: enhancement 

## renzihui (14 May 2014)

Pub a message doesn't require a pubsub connection, however get a RTopic does. There are many cases just want to publish a message, get a RTopic from Redisson will automatically subscribe on the channel, which is unnecessary and there is no safe way to close topic after publish message since there may be some listeners are actually interested in the topic.

BTW, RedisConnection has a method  publish(K channel, V message) 
Unfortunelty the underlying connection manager is not exposed, see issue #13
Provide access to the underlying connection manager


## mrniko (14 May 2014)

@renzihui I implemented a "lazy" subscribe apporach, so now it subscribes only then needed. Please check


## renzihui (15 May 2014)

@mrniko 
Thanks so much! This does solve the problem I mentioned. However I still think publish a message should not need to create a Topic object, that's relative expensive.

BTW, it seems there is no way to explicitly close a Topic, it relies on GC to clean reference Map. I'm not sure if this will be problem when there are too many short live temp topic, will run some test.  


## mrniko (15 May 2014)

> it seems there is no way to explicitly close a Topic, it relies on GC to clean reference Map.

If you take a look in RedissonTopic you'll find there 'TODO' about this problem. i'm working on it :)


## renzihui (15 May 2014)

I see, that's great!


## mrniko (30 May 2014)

@renzihui done! please check it and reopen issue if needed.


## renzihui (01 Jun 2014)

Thanks, will try it out.

By the way, what do you think to add some debug log in redisson? Either use SLF4J or Netty's InternalLoggerFactory. It's too hard to trace some problems without log.


## mrniko (01 Jun 2014)

yea i'll add some logging, thanks for the tip :)


## mrniko (02 Jun 2014)

@renzihui done


## mrniko (04 Jun 2014)

@renzihui I just refactored subscription in RTopic.


