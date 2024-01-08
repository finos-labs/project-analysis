#Dependency on Hibernate?

Owner: brettwooldridge

Repo: HikariCP

Labels: bug 

## analytically (14 Jan 2014)

 +-com.zaxxer:HikariCP:1.2.5
[info]   | +-org.hibernate:hibernate-core:4.3.0.Final
[info]   | | +-antlr:antlr:2.7.7
[info]   | | +-dom4j:dom4j:1.6.1
[info]   | | | +-xml-apis:xml-apis:1.0.b2
[info]   | | | 
[info]   | | +-org.hibernate.common:hibernate-commons-annotations:4.0.4.Final
[info]   | | | +-org.jboss.logging:jboss-logging-annotations:1.2.0.Beta1
[info]   | | | +-org.jboss.logging:jboss-logging:3.1.3.GA
[info]   | | | 
[info]   | | +-org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.0.Final
[info]   | | +-org.javassist:javassist:3.18.1-GA
[info]   | | +-org.jboss.logging:jboss-logging-annotations:1.2.0.Beta1
[info]   | | +-org.jboss.logging:jboss-logging:3.1.3.GA
[info]   | | +-org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.0.0.Final
[info]   | | +-org.jboss:jandex:1.1.0.Final
[info]   | | 
[info]   | +-org.javassist:javassist:3.18.1-GA
[info]   | +-org.slf4j:slf4j-api:1.7.5


## analytically (14 Jan 2014)

Hibernate dependency should be `provided`? http://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html


## brettwooldridge (14 Jan 2014)

Yes, good catch.


## brettwooldridge (15 Jan 2014)

Today HikariCP 1.2.6 was published to the maven repository containing this fix.


