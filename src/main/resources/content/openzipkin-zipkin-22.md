#ZipkinSpec doesn't tear down zookeeper instance

Owner: openzipkin

Repo: zipkin

Labels: 

## franklinhu (13 Jun 2012)

When running tests for zipkin server, the `ZipkinSpec` doesn't close the ZK instance that's started. This causes another run of the tests to fail since the ZK instance is still bound to the port.

To reproduce:

```
  $ bin/sbt
  > test // this may pass
  > ...
  > test // ZipkinSpec will fail
```


## franklinhu (13 Jun 2012)

Also we may want to consider using this to stand up the ZK instance: http://twitter.github.com/commons/apidocs/com/twitter/common/zookeeper/testing/ZooKeeperTestServer.html


## franklinhu (13 Jun 2012)

Fixed in #23


