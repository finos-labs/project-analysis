#Transaction isolation applied to running transaction in HikariPool 

Owner: brettwooldridge

Repo: HikariCP

Labels: bug 

## zimmi (11 Jan 2014)

I am running HikariCP 1.2.4 with 

```
HikariConfig config = new HikariConfig();
config.setUseInstrumentation(false);
config.setJdbc4ConnectionTest(true);
config.setAutoCommit(false);
config.setTransactionIsolation("TRANSACTION_REPEATABLE_READ");
```

Because HikariPool does not reset the autocommit flag (to its default of true) when the connection is returned to the pool, this leads to (comments inline, code from HikariPool, line 152):

```
Connection connection = (Connection) connectionProxy;
// if connectionProxy is not autocommiting, this will start a transaction
if (!isConnectionAlive(connection, timeout))
{
    // Throw away the dead connection, try again
    closeConnection(connectionProxy);
    timeout -= (System.currentTimeMillis() - start);
    continue;
}

if (leakDetectionThreshold > 0)
{
    connectionProxy._captureStack(leakDetectionThreshold, houseKeepingTimer);
}
// if autocommit is already false, this is a noop, transaction still running
connection.setAutoCommit(isAutoCommit);
// Exception here, because transaction is already running
connection.setTransactionIsolation(transactionIsolation);
connection.clearWarnings();
```

I also noticed while debugging that postgres (in my case) will perform a roundtrip to the database on 

```
Connection#setTransactionIsolation and also on Connection#getTransactionIsolation
```

Maybe the transaction isloation could be cached by HikariCP and only set once on creation of the actual connection?


## brettwooldridge (11 Jan 2014)

I am committing a fix for the former.  The trouble with the later is that the user can alter the isolation level of the connection, just like auto-commit, and therefore it needs to be reset.


## brettwooldridge (11 Jan 2014)

Fix committed to `dev`.


## zimmi (11 Jan 2014)

Thanks.

Regarding the caching thing, I thought of this:

Make getTransactionIsolation really cheap by caching the isolation level in a field in the wrapping Connection.
setTransactionIsolation does its thing and also writes to this field.

getConnection on the pool then can call the now cheap getTransactionIsolation and only call setTransactionIsolation if the default pool level is different, thus saving the roundtrip to the database.

But if that really is worth the added complexity is up to you.


## brettwooldridge (12 Jan 2014)

It's not necessarily the complexity I worry about, I'm more concerned that there is a reason that PostgreSQL is required to make the roundtrip.  Otherwise, why would they have implemented the driver that way?


## zimmi (12 Jan 2014)

That's a good point. Seems more like an optimization the driver should do if possible. I will check with the pgjdbc team.


## brettwooldridge (15 Jan 2014)

You may be interested in this thread:

https://groups.google.com/forum/#!topic/hikari-cp/7hgIWvE7DiY

Basically, like PostgreSQL, this user found that SQL Server performs a roundtrip on `setTransactionIsolation()`.  I have checked in an optimization to the `dev` branch that will only reset the transaction isolation level if it is detected that the user actually altered it.  In the base case, this completely avoids the call.


## zimmi (16 Jan 2014)

Thanks for the info.

Just as a follow up:
The [pgjdbc drivers setTransactionIsolation](https://github.com/pgjdbc/pgjdbc/blob/REL9_3_STABLE/org/postgresql/jdbc2/AbstractJdbc2Connection.java#L947) sets the isolation level through [SQL](http://www.postgresql.org/docs/9.3/static/sql-set-transaction.html). So nothing is preventing a user from altering the isolation level without the setTransactionIsolation-API.
I guess that's the reason why getTransactionIsolation has to roundtrip to the database to be certain. If one wants 100% equally configured connections to be handed out from the pool, I'm not sure if the setTransactionIsolation call can be avoided.


