#Make ConnectionManager accessible from Redisson

Owner: redisson

Repo: redisson

Labels: 

## mathieucarbou (27 May 2014)

Hi,

When we create a Redisson object, I would also need to execute some Redis commands.

What is the best way ? Do I need to create another ConnectionManager completely separate from the one in the Redisson class, or it would be better to access the one created on the Redisson object, so that we can re-use it ?

Thanks!


## mathieucarbou (27 May 2014)

I think it might be a duplicate of #13.

```
    ConnectionManager connectionManager(Redisson redisson) {
        Field field = Redisson.class.getDeclaredField("connectionManager");
        field.setAccessible(true);
        return (ConnectionManager) field.get(redisson);
    }
```


## mrniko (02 Jun 2014)

@mathieucarbou please list which commands do you need to invoke?


## mathieucarbou (02 Jun 2014)

Currently:
- connection.setex(key, ttl, obj) with encoder
- connection.del(key)
- connection.get(key) with decoder


## mrniko (02 Jun 2014)

@mathieucarbou 
What about such api:

RObjectHolder<Object> holder = Redisson.getObjectHolder(key);
Object someObject = holder.get();             // analog of connection.get(key)
holder.set(newValue, ttl);                           // analog of setex(key, ttl, obj)
holder.remove();                                        // connection.del(key)

I can add this RObjectHolder object for your needs to api.


## mathieucarbou (02 Jun 2014)

It would work.

But having an object holding the String key if good if we do several operations on the same "buket".  In example, it would work find with maps where you may want to key the reference to the map.

But for keys, it is often the case that the key is formed by a temporary value. In our case we are using redis a a temporary store for cookie sessions. So each request could potentially issue a call to get(sessionKey), with the sessionKey being a hashed unique value.

So when having a lot of trafic, are you sure that it won't create an overhead if we have to create a really-short-live object just for the method call each time we just want to get or set a value ?

Also, perhaps the name RBucket<V> would be better ? With the parameterized type since we are using an encoder / decoder.

I also don't know what are your design plans for the future, but the method remove() or connection.del() could also be applied to any Redis object: not just simple key-values.

So... In conclusion yes it would work by I am hesitating about wheter it is a good use case for us to  have this object due to the fact that each call would require the creation of a temp. short-live object to run: redisson.<Session>getBucket(key).get() (because the parameterized type would also be required i suppose ?)


## mrniko (03 Jun 2014)

@mathieucarbou 

> I also don't know what are your design plans for the future, but the method remove() or connection.del() could also be applied to any Redis object: not just simple key-values.

good idea! implemented


## mrniko (03 Jun 2014)

@mathieucarbou RBucket implemented, you may use it


## mathieucarbou (03 Jun 2014)

Hi,

I just saw the commits  and I think there are some real issues: the Redisson class maintains maps of created Redisson objects as strong references. This would surely lead to a memory leak in many cases since they key growing and are never cleaned up.

I think this is up to the client to keep a reference on the object he wants to keep references onto: not up to the library. Object creation in Java has no cost so probably that the cost of checking existince in the concurrent map is greater than simply returning each time a new object with the connection manager into. If the client wants to keep a reference onto, i.e. in case of  lock, great. But in the majority of cases, and in our case, keeping all references of accessed redis object could be a memory-killer ;-)

In the case of buckets, supposing to have a server handling thousands of clients, each session creation being handled in Redis. Each client will have its own bucket, but the client only remains on the website for a limited amount of time, whereas the server lives for much longer. Thus you will end up with a map which keeps growing.

More and more apps are now deployed on the cloud where memory is limited (i.e. heroku or else) thus requiring using external systems to store states to have better scalability  in terms of request throughput. 

Let me know what you think about it...

For the moment, I will have to remove the library from the project and create a fork to avoid memory issues.


## mrniko (03 Jun 2014)

@mathieucarbou 

> This would surely lead to a memory leak in many cases since they key growing and are never cleaned up.

No, Redisson object maps has a "soft" reference for values, as you can see. So if map value reference is free entire map entry will be garbage collected.


## mathieucarbou (03 Jun 2014)

Wouldn't be better with weak reference so ? Because soft references do not help avoiding the memory to grow up. Why Redisson needs to keep a ref on created objects ?


## mrniko (03 Jun 2014)

@mathieucarbou 

i just changed ReferenceType from "soft" to "weak" so map entry will be garbage collected will be GC-ed faster not only on JVM low-memory.


## mrniko (03 Jun 2014)

> Why Redisson needs to keep a ref on created objects ?

Because for some objects i should have only one instance in JVM, for example for RedissonCountDownLatch object, to avoid consistency problems.


## mrniko (03 Jun 2014)

@mathieucarbou 

please check how it works with weak references and give your feedback


## mathieucarbou (03 Jun 2014)

What I don't understand is the need of keeping those objects event if they have some states. To my mind the responsability is up to the client.

In java util concurrent, when using a CountDownLatch, which has some sates, this is up to the user code to keep a ref on this object everywhere it is required.

There is no such code in the jvm like: concurrent.getLatch(name).

Keeping those objects forces Redisson to use soft or weak references, and thus not being efficient on memory usage. Whereas delegating this to the user would solve this issues.

And if some states would have to be maintained not within but between some created redisson objects, it is still possible to create a manager class like to handle those potential shared states over all created objects (i.e. what I think as exemple would be an integration with Metrics from Coda Hale)


## mrniko (03 Jun 2014)

As for now only to of objects need the maps - RedissonCountDownLatch and RedissonLock. I have dropped maps for others (take a look at master). Subscribe logic of lock and latch objects will be reimplemented in near days. Thanks for the challenge!


## mathieucarbou (03 Jun 2014)

Cool! 
You're welcome!

I like this library and a lot of things into (i.e. having a thead sharable connection, obejct model, etc...) and thus if it can be more efficient in terms of resource usage it will make a lot of people happy like us ;-)


## mrniko (06 Jun 2014)

@mathieucarbou don't know maybe this feature will be useful for your project. I just added master/slave connection support. So you may test it if you interest in it.


## mathieucarbou (09 Jun 2014)

Hi,
We are using RedisCloud so we cannot use it. I have some questions though: 
1. what is now the difference between config.setThreads() and singleConnection.setConnectionPoolSize() ?
2. Are the words useSingleConnection and useMasterSlaveConnection best to describe what they do ? I hope useSingleConnection mode does not use only one connection, but several connections to the same host ? Perhaps singleServer() vs cluster() or something like that would better describe the fact that several servers could be used to split reads and writes ? 


## mrniko (11 Jun 2014)

@mathieucarbou 

> what is now the difference between config.setThreads() and singleConnection.setConnectionPoolSize() ?

singleConnection.setConnectionPoolSize - it's a total amount of connection which used for read/write (for single server connection) or read (for slave server) and write (for master server).

config.setThreads - total threads amount shared by all inner redis clients.


## mrniko (11 Jun 2014)

@mathieucarbou 

> Are the words useSingleConnection and useMasterSlaveConnection best to describe what they do ? I hope useSingleConnection mode does not use only one connection, but several connections to the same host ? Perhaps singleServer() vs cluster() or something like that would better describe the fact that several servers could be used to split reads and writes ? 

I agree with you better name is useSingleServer and useMasterSlaveServers, i think.


