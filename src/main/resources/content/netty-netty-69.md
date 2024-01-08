#Review recursive ChannelHandler execution logic

Owner: netty

Repo: netty

Labels: won't fix 

## normanmaurer (18 Nov 2011)

At the moment all ChannelHandlers are execute in a recursive way. This can cause all kind of problems and is also sometimes not what would give you the best "expierence". It would make sense to make this kind of pluggable, or at least support other execution logics. 

For example we could also provide some execution logic which is more similar on what node.js does etc


## danbim (18 Nov 2011)

I'm not getting what you mean by "recursive execution". Could you give an example?


## normanmaurer (18 Nov 2011)

Related to this:

https://issues.jboss.org/browse/NETTY-169


## trustin (19 Nov 2011)

The idea is to provide various ways to send an event to the next handler.  Netty 3 only has direct invocation, which often leads way too deep stack or unnecessary memory consumption.  Netty 4 should provide 3 different ways:
- Schedule an event to be executed in an event loop later (just like Swing's `invokeLater`).
- Schedule an event to be executed immediately after the current handler returns (just like tail recursion optimization)
- The legacy behavior - this is great for achieving very low latency and thus sometimes very useful and this I'm going to leave it, but it should not be a default.

WDYT?


## mariusae (20 Nov 2011)

twitter's `com.twitter.util.Future` does #2 only:

  https://github.com/twitter/util/blob/master/util-core/src/main/scala/com/twitter/concurrent/IVar.scala#L78

we're very happy with this.

one thing to watch out for in the implementation is that it does not mix well with blocking operations.  suppose you have this:

```
operationComplete():
  ChannelFuture a;
  dispatchSomeOperationThatWillFulfillA();
  a.awaitUninterruptibly()
```

this would cause a deadlock.  the way we've solved this in `com.twitter.util.Future` is to flush the thread's schedule prior to blocking:

  https://github.com/twitter/util/blob/master/util-core/src/main/scala/com/twitter/concurrent/IVar.scala#L251


## normanmaurer (20 Dec 2012)

@trustin I think we can close this as "won't fix"... Ok with that ?


## trustin (09 Jan 2013)

Yes. Closing.


