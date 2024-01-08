#Allow DataSource configuration via driverClass

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## ghillert (22 Dec 2013)

Setting the DataSource using e.g. `org.postgresql.ds.PGSimpleDataSource` seems to be less portable/ideal when supporting multiple DBs at the same time. E.g. `PGSimpleDataSource` does not provide for a JDBC url to be specified. Other pool implementations (e.g. BoneCP) provide more of an abstractions level so that I can set the DB properties across the various implementations e.g.
- jdbcUrl
- username
- password
- ...


## brettwooldridge (23 Dec 2013)

Hi Gunnar, thanks for the feedback.  Unfortunately, HikariCP will not be adding support for this kind of configuration.  `DriverManager` was an ill-conceived interface when Sun created it, which they quickly corrected with the introduction of `DataSource`, stating in the JavaDoc for DataSource, _"a DataSource object is the preferred means of getting a connection"_, compared to DriverManager.

HikariCP was actually developed for and used in a server that supports 3 databases out-of-the-box.  We simply do not expect properties or properties files to be portable across databases.  There are several ways in Spring and other containers to dynamically load different properties based on differing configuration conditionals.

With DriverManager, all properties must be specified via URL.  A database like MySQL has over 70 properties that can be set, and probably 10-20 that are commonly set.  This makes for unreadable and poorly managed configuration.  In contrast, all of those properties are available as individual setters on the MySQL DataSource.

Finally, in DriverManager-based pools, because a JDBC URL is not evaluated until a connection is created, if the properties are misconfigured it is possible for a pool to start that does not fail until someone tries to obtain the first connection.  In contrast, a DataSource-based pool will fail during construction if properties are misconfigured.


