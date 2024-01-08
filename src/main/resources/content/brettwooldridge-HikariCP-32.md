#Question: how to fail fast on bad DB password

Owner: brettwooldridge

Repo: HikariCP

Labels: question 

## mbucc (03 Feb 2014)

Hi,
If my HikariCP config has the wrong database credentials, I'd like my server to fail fast.

Seems like the only way is to wait for acquireRetries \* acquireRetryDelay milliseconds, then try to call getConnection(), catch the SQLException and then exit.

Do I have that right?

Thanks,
Mark


## brettwooldridge (03 Feb 2014)

That is correct.

Sent from my iPhone

> On Feb 4, 2014, at 8:22 AM, mbucc notifications@github.com wrote:
> 
> Hi,
> If my HikariCP config has the wrong database credentials, I'd like my server to fail fast.
> 
> Seems like the only way is to wait for the acquireRetries \* acquireRetryDelay milliseconds, then try to call getConnection(), catch the SQLException and then exit.
> 
> Do I have that right?
> 
> Thanks,
> Mark
> 
> \
> Reply to this email directly or view it on GitHub.


## brettwooldridge (04 Feb 2014)

I'll investigate a way to fail the pool fast.  Likely it will be via a RuntimeException thrown from the constructor ... but will require that the minimum pool size is non-zero.


## zimmi (04 Feb 2014)

If you instantiate your DataSource programmatically, you could use the raw driver DataSource to do the getConnection() check. e.g.:

```
PGSimpleDataSource dataSource = new PGSimpleDataSource();
dataSource.setUser("username");
dataSource.setPassword("password");

// check raw data source, e.g. dataSource.getConnection();

HikariConfig config = new HikariConfig();
config.setDataSource(dataSource);

HikariDataSource connectionPool = new HikariDataSource(config);
```

But I guess that won't help if you do this the declarative way.


## brettwooldridge (12 Feb 2014)

The recently released HikariCP 1.2.9 has a new property `initializationFailFast` that is not yet documented.  If you set this to `true`, there will be a `RuntimeException` thrown when a `HikariDataSource` is constructed, providing that the `minimumPoolSize` is > 0.


