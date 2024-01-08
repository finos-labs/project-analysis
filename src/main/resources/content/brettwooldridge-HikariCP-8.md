#HikariCP as JNDI Datasource

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## rsinelle (10 Dec 2013)

I have trying to test HikariCP as jndi datasource, but i cannot find the way to add it as jndi datasource in tomcat 7.
If it's possible can you provide the configuration or if not can you add this feature ?


## brettwooldridge (10 Dec 2013)

Configuration of JNDI is outside of the control of a pooling provider.  Here is a link to the Tomcat JNDI configuration documentation.

http://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html#MySQL_DBCP_Example

Specifically, the **Context Configuration** section is what you need.   And this is the full documentation for Tomcat JNDI, specifically the **Using Resources** section may interest you.

http://tomcat.apache.org/tomcat-7.0-doc/jndi-resources-howto.html


## fabian (20 Mar 2014)

The problem is that `com.zaxxer.hikari.HikariDataSource` is not usable with the `org.apache.naming.factory.BeanFactory` provided by Tomcat.

We had to write our own wrapper for the HikariDataSource which looks like this:

```
public class TomcatHikariDataSource extends HikariDataSource
{
    public void setUser(String user)
    {
        addDataSourceProperty("user", user);
    }

    public void setPassword(String password)
    {
        addDataSourceProperty("password", password);
    }

    public void setUrl(String url)
    {
        addDataSourceProperty("url", url);
    }
}
```


## brettwooldridge (20 Mar 2014)

Checkout the `dev` branch.  It has support for username, password, jdbcUrl, and driverClassName now.

```
git clone https://github.com/brettwooldridge/HikariCP.git
cd HikariCP
git checkout dev
mvn install
```

Then use HikariCP-1.3.4-SNAPSHOT.jar or 1.3.4-SNAPSHOT as a pom dependency.  Let me know if it works.


