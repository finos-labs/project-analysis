#Add constructor accepting HikariPool to HikariDataSource

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## analytically (09 Jan 2014)

It'd be handy to be able to construct the pool outside of the datasource.


## brettwooldridge (09 Jan 2014)

What is the use case for this?  The HikariPool is considered an internal class.  While it is `public` it has no public constructor and is not meant to be accessed directly by the user.  A user with a reference to the HikariPool could do potentially bad things, such as releasing the same connection back to the pool multiple times, etc.


## analytically (09 Jan 2014)

See eg. https://github.com/scalikejdbc/scalikejdbc/blob/develop/scalikejdbc-library/src/main/scala/scalikejdbc/CommonsConnectionPool.scala

 private[this] val _dataSource: DataSource = new PoolingDataSource(_pool)


## brettwooldridge (09 Jan 2014)

That is quite a different use case and does not require the exposure of HikariPool.  The referenced Scala code is specific to Apache Commons DBCP.  Even if HikariPool were public, it is not an implementation of the Apache Common Pool interfaces and therefore cannot be passed into the DBCP PoolingDataSource constructor.

Similar to the CommonsConnectionPool implementation, scalikejdbc needs to provide an implementation of something like a HikariConnectionPool class that is backed by HikariCP.  I suggest opening an enhancement request in the sclaikejdbc project requesting a HikariCP pool implementation.


## analytically (09 Jan 2014)

It's not really specific to DHCP. The Hikari datasource doesn't expose numActive, numIdle, maxActive and maxIdle, but the pool does.


## brettwooldridge (09 Jan 2014)

Those values are exposed through JMX and are available programmatically.
 See this group posting at the tail end of the post:

https://groups.google.com/forum/#!searchin/hikari-cp/JMX/hikari-cp/ReQVNASu-w0/OgXjYbl4TDkJ

On Thu, Jan 9, 2014 at 6:43 PM, Mathias Bogaert notifications@github.comwrote:

> It's not really specific to DHCP. The Hikari datasource doesn't expose
> numActive, numIdle, maxActive and maxIdle, but the pool does.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/brettwooldridge/HikariCP/issues/20#issuecomment-31915645
> .


