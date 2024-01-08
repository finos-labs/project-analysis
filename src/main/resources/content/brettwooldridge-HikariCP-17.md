#Support for DataSource#setLogWriter

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## zimmi (01 Jan 2014)

To see what is going on with the underlying DataSource (PGSimpleDataSource in my case) I use DataSource#setLogWriter. However, I did not find a way to do this with HikariCP.

What I tried:

```
PrintWriter logWriter = ...;

HikariConfig config = new HikariConfig();
// logWriter is first converted to String, so this does not work
config.addDataSourceProperty("logWriter", logWriter);
// other properties ...

HikariDataSource connectionPool = new HikariDataSource(config);
// Implementation does nothing
connectionPool.setLogWriter(logWriter);
```

Are there any plans to support this?

Thank you in advance and for your work so far, HikariCP is awesome. :)


## brettwooldridge (02 Jan 2014)

The fix for this has been committed to the `dev` branch.  It will go into the next official release.  In the meantime, you can clone the repo, checkout the dev branch (`git checkout dev`), and build the jar file with maven (`mvn package`).

Both of the styles in your examples above will work -- either through the `HikariConfig` or directly on the `HikariDataSource`.


## zimmi (02 Jan 2014)

That was fast, thanks!


## brettwooldridge (05 Jan 2014)

HikariCP 1.2.2 has been released and published to the maven central repository.


## zimmi (05 Jan 2014)

Thanks again.

Something to note: To not miss any logging from the underlying DataSource,

```
config.addDataSourceProperty("logWriter", logWriter);
```

should be used, since Connections will already be added when the constructor of HikariDataSource returns.


