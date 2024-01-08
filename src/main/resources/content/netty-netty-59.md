#Make ChannelFuture implement Future<Void>

Owner: netty

Repo: netty

Labels: improvement 

## trustin (16 Nov 2011)

It would be nice and useful to make `ChannelFuture` implement `java.util.concurrent.Future`.  We don't need to define an additional type that represents the result of the future but we can just use Void.   On failure, `ExecutionException` should be raised.  On success, null is returned.


## normanmaurer (16 Nov 2011)

Yes..

See:
https://issues.jboss.org/browse/NETTY-454


## normanmaurer (18 Nov 2011)

I will take a stab at this today If everything works out


## normanmaurer (18 Nov 2011)

assign to me....


