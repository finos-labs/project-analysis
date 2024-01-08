#CassandraAggregates Futures

Owner: openzipkin

Repo: zipkin

Labels: 

## jerryli9876 (28 Aug 2012)

As Johan mentions, you shouldn't have to block on the remove in the storage methods, but instead chain the futures so you can return one that executes this first and then the batch.


