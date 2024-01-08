#Question about spring-jpa tutorial

Owner: eugenp

Repo: tutorials

Labels: 

## invasionofsmallcubes (04 Sept 2013)

I was looking at your example from this tutorial: http://www.baeldung.com/2011/12/26/transaction-configuration-with-jpa-and-spring-3-1/

I would like to know if there is a way to use the same PersistenceJPAConfig with different datasources.

Is there some way using inheritance?


## eugenp (04 Sept 2013)

Easiest way is via `@ImportResource` - this may help: [example](https://github.com/eugenp/stackexchange2twitter/blob/master/src/main/java/org/tweet/meta/spring/TwitterMetaPersistenceJPAConfig.java). Thanks. 


