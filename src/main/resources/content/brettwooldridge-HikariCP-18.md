#Consider adding HikariConfig#setTransactionIsolation

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## zimmi (02 Jan 2014)

I'm using HikariCP together with Spring and it would be nice to specify a default transaction level for handed out connections.

Called like:

```
HikariConfig config = new HikariConfig();
// Taking transaction name (Connection.TRANSACTION_REPEATABLE_READ)
// so that configuration in properties file is readable
config.setTransactionIsolation("TRANSACTION_REPEATABLE_READ");
```

If the underlying DataSource would allow it, HikariConfig#addDataSourceProperty could be used, but in the case of postgres, PGSimpleDataSource has no appropriate setter.


## brettwooldridge (02 Jan 2014)

OK.  It's midnight here in Tokyo, I'll add it tomorrow.


## brettwooldridge (04 Jan 2014)

The above syntax, `config.setTransactionIsolation("TRANSACTION_REPEATABLE_READ");` will now work.  Commit is currently on the `dev` branch.  A new release will be published later this week.


## brettwooldridge (05 Jan 2014)

HikariCP 1.2.2 has been released and published to the maven central repository.


## zimmi (05 Jan 2014)

Awesome, I'll check it out later today.
Thank you very much!


