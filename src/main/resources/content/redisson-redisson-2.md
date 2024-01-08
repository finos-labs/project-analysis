#SCAN or ZRANGE operations as Java Iterators?

Owner: redisson

Repo: redisson

Labels: enhancement 

## igorbrigadir (06 Feb 2014)

Just an idea, would this be possible? Still new to Redis / Redisson.


## mrniko (07 Feb 2014)

Yes, i am planing to support scan operation in iterators. Where do you plan to use zrange?


## igorbrigadir (07 Feb 2014)

Great! Haven't fully thought through how a ZRANGE type operation could be wrapped in a Java Interface, perhaps something that resembles executing a query and getting a ResultSet back?


## mrniko (30 Jun 2014)

@igorbrigadir you may take a look at RedissonSet.iterator() as an example of sscan usage through iterator


