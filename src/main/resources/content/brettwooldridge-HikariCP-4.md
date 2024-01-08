#Small suggestion

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## wwadge (28 Nov 2013)

Hi,

I'm the author of BoneCP. I'm delighted at finding your project - some interesting ideas out there! Thank you for taking the time to document your techniques. Do you accept contributions? :-)

Might I suggest adding units to your config methods eg InSeconds etc, before I did this, forums were filled with people getting the units wrong, they disappeared when I made the API more explicit. In other words try to avoid the mistakes I made in my initial versions :-)


## brettwooldridge (29 Nov 2013)

Hey, and welcome.  I have to admit I'm not a big fan of time unit denominations in method names.  If I could change all the methods to take a `TimeUnit`, eg. `config.setMaxLifetime(30, TimeUnit.MINUTES)` I would.  But unfortunately this doesn't work well for property files or bean-based setters.

What I will do is put in additional logging in the `HikariConfig.validate()` method which gets called during initialization.  It already logs various warnings such as maxPoolSize < minPoolSize.  Maybe some judicious warnings like if `maxLifeTime` is specified to be less than 60000 (one minute), the user has probably made mistake of thinking 30 meant _30 minutes_ or 3600 meant _3600 seconds_.  Same for other values...

Maybe this, on top of defaulting back to a sane value in such cases would nearly eliminate misconfigurations.


## brettwooldridge (29 Nov 2013)

Re: contributions, yes please feel free to fork and submit pull requests.


## brettwooldridge (10 Dec 2013)

You mentioned on the BoneCP forum (which I cannot login to recently) that you used or recommended stashing the Connection in a ThreadLocal and handing back the same connection to a thread if it calls getConnection() again.

It is worth considering, but I wonder if the overhead is ultimately worth it?  For example, a returning connection could be stashed in a ThreadLocal, but if that thread terminates (maybe it is from an idle pool), the Connection needs to return back to the main pool.  Have you done any testing on this design?


## chrisvest (10 Dec 2013)

As the author of the Stormpot object pool, which uses this trick with ThreadLocals in its BlazePool implementation, I'd say that it might be worth it when dealing with an API such as DataSource. There are complications, however, since now the state of an object (claimed or not) can no longer be derived from whether it exists in the internal queue/collection of free objects -- any object anywhere (exaggerating and glossing over design details here, for effect) can now have claim attempts made against it by someone who had a reference to it in a ThreadLocal. You also need to take into account that some threads might want more than one object to play with, and in this case, having the pool return two distinct objects is, I think, most likely the more correct behaviour.


## wwadge (10 Dec 2013)

Although I didn't wrinkle out all the bugs, the code's there and mostly
works. I made it so that the thread is watched so that if it's killed off I
get to reclaim the connection back. I also had a flag in place so that a
thread would only have access to one connection in this fashion, anything
else I'd flip to using the usual queue again.

This technique is specifically for people running for eg tomcat with a
fixed # of threads always in place, in essence you're pushing the lock down
to the socket/threadpool level.

There's very little overhead since with no lock contention for the
thread-local, each thread minds its own business, for the most part it
works. The harder bits are: now that you don't have an idle queue, your
implementation for killing off idle connections becomes a more difficult
task.

On a more serious note:
https://github.com/brettwooldridge/HikariCP/blob/master/core/src/main/java/com/zaxxer/hikari/HikariPool.java#L195

this is the design I had in the 0.7.1 branch whereby the same connection I
lease out is the same I put back in. The problem with this is that an
application can do:

Connection c = getConnection();
releaseConnection(c);
...
do something with c anyway

yes it's stupid to do but the jdbc spec calls for the handle you have in
hand to have no access to the underlying connection anymore. That was the
major change I did for the 0.8.0 branch and a bunch of bugs cropped up from
that since my basic design was flawed.

Also consider having partitioning for your idle queue - again it's less
lock contention.

I also think that these two lines can race nastily:
https://github.com/brettwooldridge/HikariCP/blob/master/core/src/main/java/com/zaxxer/hikari/HikariPool.java#L129
https://github.com/brettwooldridge/HikariCP/blob/master/core/src/main/java/com/zaxxer/hikari/HikariPool.java#L238

You should override the poll method so that the connection count is kept in
sync atomically.

Finally just to correct the website a little, no "Bone"cp wasn't related to
"barebone". It was an insider joke (hint: I used to work for CCBill when I
wrote that, I'll let you figure out the rest ;-)

Wallace

On Tue, Dec 10, 2013 at 6:16 AM, brettwooldridge
notifications@github.comwrote:

> You mentioned on the BoneCP forum (which I cannot login to recently) that
> you used or recommended stashing the Connection in a ThreadLocal and
> handing back the same connection to a thread if it calls getConnection()
> again.
> 
> It is worth considering, but I wonder if the overhead is ultimately worth
> it? For example, a returning connection could be stashed in a ThreadLocal,
> but if that thread terminates (maybe it is from an idle pool), the
> Connection needs to return back to the main pool. Have you done any testing
> on this design?
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/brettwooldridge/HikariCP/issues/4#issuecomment-30200360
> .


## brettwooldridge (11 Dec 2013)

Thanks for the input guys.  I may do some experimentation.  It definitely adds complexity, particularly the idle timeout and ThreadLocals inhibiting garbage collection.

As it stands, lock contention for the pool resource is already low in relative terms with an average latency of 20μs.  Compared to a pure object pool like Stormpot, where the pool is the primary resource under stress, in a JDBC pool the primary resources under stress are the proxies around `Connection`, `Statement` (and it's subclasses), and `ResultSet` -- rarely the pool itself.  For every call to `DataSource.getConnection()` there is easily one and sometimes two orders of magnitude more invocations of various methods on `Connection`, `Statement` and `ResultSet`.

Which is to say I am not sure what real world impact improvements to the pool dequeue/enqueue performance would have.  Even if acquisition time from the pool took zero time (0μs), the performance improvement apparent to an application may also be near zero, due to the majority of the application's interaction occurring against the proxies (rather than the pool).

This is one reason we focused so heavily on the code paths of the proxies and in the case of instrumentation mode, eliminating them.

Still it warrants investigation.  While the current pool latency is 20μs, accessing a ThreadLocal incurs an overhead of only about 12ns (excluding whatever additional logic is needed around idle management).


## brettwooldridge (12 Dec 2013)

@wwadge I've added code to cover the "Connection c = getConnection(); releaseConnection(c); ... do something with c anyway" case.  Rather than changing the structure of the pool, it uses a ThreadLocal-based approach whereby inside the Connection proxy the `_isClosed` flag is kept as a `ThreadLocal<Boolean>`, rather than a simple boolean.  After a thread calls `close()` any subsequent calls will, from the perspective of that thread, find that the connection is closed, even if that connection has been `unclosed` and given to another caller.

Regarding the `idleConnectionCount` and it possibly being "out of step" with respect to the `idleConnections` queue itself; the `idleConnectionCount` is essentially _advisory_ in nature.  The structure of the `getConnection()` method itself ensures that, despite a window of time where `idleConnectionCount` does not match the actual queue size, pool-filling behavior is not affected.   Threads cannot block indefinitely if there is available capacity to be added to the pool.

The essential structure of `getConnection()` (stripping out connection testing, etc) is:

```
1:  Connection getConnection() {
2:       try {
3:          addConnections(AddConnectionStrategy.ONLY_IF_EMPTY);
4:          Connection connection = idleConnections.poll()
5:          idleConnectionCount.decrementAndGet();
6:          return connection;
7:       finally {
8:          addConnections(AddConnectionStrategy.BACKGROUND_FILL);
9:       }
10: }
```

Obviously we do not need to worry about the case where only one thread passes through `getConnection()` at a time, the count will always appear correct from the perspective of any caller.  It only gets interesting when two or more threads enter `getConnection()` simultaneously and the pool is at or near empty.

Let's assume that _Thread-1_ and _Thread-2_ both enter `getConnection()`, and examine the edge cases.

Case 1: `idleConnections` contains 1 connection.
- _Thread-1_ reaches line 5 (but has not executed it yet).  `idleConnections` is now empty.  `idleConnectionCount` is still 1.
- _Thread-2_ passes line 3 without adding a connection, because it thinks there are idle connections available.
- _Thread-2_ blocks on line 4, because `idleConnections` is empty.
- _Thread-1_ passes line 5.  `idleConnectionCount` is now 0.
- _Thread-1_, having nothing to block its progress, exits `getConnections()`, calling `addConnections()` in the _finally_.
- _Thread-1_ in `addConnections`, seeing that the `idleConnectionCount` is 0, adds a connection to `idleConnections`.  (Technically this happens on a background thread).  `idleConnectionCount` becomes 1.
- _Thread-2_ is unblocked due to an available connection.  `idleConnections` becomes empty.  `idleConnectionCount` becomes 0.

Case 2: `idleConnections` contains 0 connections.
- _Thread-1_ and _Thread-2_ enter `addConnections()` on line 3.
- Both seeing that `idleConnectionCount` is 0, each adds 1 connection to the pool.
- Everybody is happy.

Case 2b: `idleConnections` contains 0 connections.
- _Thread-1_ enters `addConnections()` on line 3.
- _Thread-2_ reaches but does not enter `addConnections()` on line 3.
- _Thread-1_ adds a connection.  `idleConnectionCount` becomes 1.
- _Thread-2_ enters `addConnections()`, but believing there are connections available does nothing.
- _Thread-2_ passing line 4, "steals" the connection created by _Thread-1_.  `idleConnectionCount` becomes 0.
- _Thread-1_ blocks on line 4 due to no available connection.
- Plays out as _Case 1_ above, with _Thread-2_ adding a connection "on the way out", unblocking _Thread-1_.

Essentially, by bracketing `idleConnectionCount.decrementAndGet()` with `addConnection()` checks, the "seam" is closed.


