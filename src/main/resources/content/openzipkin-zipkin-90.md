#getTraceIdsByAnnotation

Owner: openzipkin

Repo: zipkin

Labels: 

## mosesn (31 Jul 2012)

[test](https://github.com/twitter/zipkin/blob/master/zipkin-server/src/test/scala/com/twitter/zipkin/storage/cassandra/CassandraIndexSpec.scala#L187) in CassandraIndexSpec, why is

``` scala
      // fetch by time based annotation, find trace
      var seq = cassandraIndex.getTraceIdsByAnnotation("service", "custom", None, 0, 3)()
      //seq mustEqual Seq(span1.traceId)
```

commented out?  It seems like it isn't time based (the duration hasn't been indexed) but also like it should be found regardless.

also, what does the test:

``` scala
      // should not find any traces since the core annotation doesn't exist in index
      seq = cassandraIndex.getTraceIdsByAnnotation("service", "cs", None, 0, 3)()
      seq.isEmpty mustBe true
```

mean?  Why shouldn't the core annotation exist in index, and what does core annotation mean?

I'm implementing this for redis, and it seems like the reverse should be true, that the second should be commented out, and the first should be uncommented.


## franklinhu (01 Aug 2012)

I'm unsure about the first one and will take a closer look tomorrow.
For the second one, core annotations are the four annotations that we expect to see in every span:
- client send (cs)
- server receive (sr)
- server send (ss)
- client receive (cr)

These fully describe a method call. We don't index these since we expect all spans to contain them, and you already get that kind of functionality from searching by span name or service name. We only really want to index custom annotations that users provide in their application code.


## franklinhu (01 Aug 2012)

I guess there's nothing theoretically wrong with a core annotation existing in the annotations index; it's mainly just a performance optimization to avoid unnecessary writes.


## mosesn (01 Aug 2012)

hmm, neat.


## franklinhu (01 Aug 2012)

First point is fixed in #93, going to go ahead and close this issue


