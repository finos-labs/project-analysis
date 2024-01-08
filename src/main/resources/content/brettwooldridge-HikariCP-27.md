#IndexOutOfBoundsException in close()

Owner: brettwooldridge

Repo: HikariCP

Labels: bug 

## virtyvoid (14 Jan 2014)

There's a little problem lying in ConnectionProxy.close().

Example case:

``` java
try(final Connection con = Pool.getInstance().getConnection())
{
    // e.g forgot to close.
    con.createStatement();
    con.createStatement();
}
catch (SQLException e)
{
    e.printStackTrace();
}
```

This one leads to this:

```
Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
        at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        at java.util.ArrayList.get(ArrayList.java:411)
        at com.zaxxer.hikari.proxy.ConnectionProxy.close(Unknown Source)
```

The cause of this is simple. The size of openStatements stored as a constant.
Then, when the statement is closed (unregisterStatement) - it'll be removed from the List and therefore we cannot use the size constant again which is used for loop as it is points to invalid index (from now).

p.s Shouldn't there be a option for logging such "unclosed" resources? :)


## brettwooldridge (15 Jan 2014)

We fixed the IndexOutOfBoundsException, but did not add a warning as automatic closing of statements is part of the JDBC specification and not formally considered "bad behavior" on the part of a programmer.

We _do_ consider it bad behavior, however, due to long running transactions with unclosed statements tending to hold onto resources that could be freed earlier via explicit `Statement.close()` calls.  Therefore we do encourage explicit close() calls in `finally` blocks.


## brettwooldridge (15 Jan 2014)

Today HikariCP 1.2.6 was published to the maven repository containing this fix.


