#Connection keep-alive

Owner: dbeaver

Repo: dbeaver

Labels: feature request 

## serge-rider (21 Oct 2015)

We need support of connections keep-alive configuration. Some drivers have it already but we need a general solution. Each connection opened by DBeaver should be pinged by a special monitor in background.
Ping period should be configurable. Auto-reconnect should be configurable.
Additionally we need some configuration for setting ping query. Currently it is an interaction with DatabaseMetaData.

This feature should be disabled by default.


## adaliszk (17 Nov 2015)

Hmm, I think the autoreconnect should be better option for that, because at my Company if you just pinging the server then the whole keep-alive wont work. We have multiple databases with firebird and most of them recreaded or least restarted on each day and the server is not stoped, so the ping has a result. The restart/recreate need only a  few seconds but after that he connected driver wont able to make a transaction. I think if the program detect a disconnect answer from a transation start then it can be reconnect to the database.


## serge-rider (17 Nov 2015)

Keep-alive makes huge sense if you are working in transactional mode. Disconnect ends current transaction (with rollback), all not-commited changes are lost.
Also, in some environments inactive connection timeout is very small (1-5 minutes). So Kepp-Alive feature is still very wanted by some people.
Although auto-reconnect is also a wanted feature.


## adaliszk (17 Nov 2015)

Oh, I did not know that the inactive time can be that small :) In that case it should be helpfull a lot this feature.


## uesleilima (01 Feb 2016)

I'm looking forward this keep alive feature, my organisation firewall timeout policy is driving me crazy with my oracle connections...


## NullpointerForever (02 Feb 2016)

I need this feature too :-)


## Anderseta (16 Mar 2016)

Can't wait for this!


## serge-rider (20 Mar 2016)

![keep-alive-config](https://cloud.githubusercontent.com/assets/6398845/13905239/230b2d58-eecb-11e5-81b1-66d5c83d52c7.png)


## Anderseta (20 Mar 2016)

Amazing! Thank you a lot


## NullpointerForever (21 Mar 2016)

Thank you :-)


## ssyang0102 (01 Apr 2016)

Perfect！


## sergei3000 (20 Aug 2017)

This is a great option! But I have two questions about how to use it.

1. Do the seconds in it show how often connection is renewed? Or do they show for how long the connection is kept alive since the last action?

2. After turning this option on and setting the number of seconds should I set a ping query in the driver's options (I'm using MS JDBC driver to connect to Azure SQL Database from Ubuntu):
![dbeaver_question](https://user-images.githubusercontent.com/18502517/29498435-056a185e-8605-11e7-979b-b54333499064.png)


## serge-rider (31 Aug 2017)

1. The first (interval between ping query execution).
2. Usually you don't have to set ping query - by default DBeaver will use JDBC API function for pinging.
However it can be unsupported by some drivers - in this case it makes sense.

## hailin84 (20 Jul 2018)

So, is that available to test connections periodically? We have firewall, so connections lost after a few minutes if no operation commit, it would be fine if DBeaver can test connections on idle. As I know, many Datasource can do this, like HikariCP.

## rsmunix (15 Feb 2019)

Perfect!

