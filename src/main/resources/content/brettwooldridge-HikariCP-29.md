#[1.2.8] Housekeeping timer thread never terminating / Connections remain opened

Owner: brettwooldridge

Repo: HikariCP

Labels: bug 

## sjaenick (24 Jan 2014)

Hi,

I'm using HikariCP within a GlassFish 3.x application server. Datasources are created on demand, kept available for some time and closed after a certain timeout.

HikariPool#shutdown() currently isn't implemented, thus every datasource ever
created (and removed) "leaks" the associated connections as well as one timer
thread.

Probably, shutdown() should
- close all connections
- terminate the associated housekeeping timer thread


## brettwooldridge (24 Jan 2014)

Agreed.


## brettwooldridge (24 Jan 2014)

Available on dev branch now.


## sjaenick (24 Jan 2014)

Wow, that was fast. Thanks a lot.


## brettwooldridge (24 Jan 2014)

HikariCP 1.2.9 will probably be out next week or the week after.  You can
run from dev, 1.2.9-SNAPSHOT if you want, that is the only change in it so
far (but it's not on the maven repo, you'll have to 'mvm package' it
yourself).


