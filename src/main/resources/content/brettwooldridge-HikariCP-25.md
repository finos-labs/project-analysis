#let me supply a DataSource to wrap

Owner: brettwooldridge

Repo: HikariCP

Labels: enhancement 

## mdagit (14 Jan 2014)

i might not have url/user/password - i might just have a DataSource from JNDI
which i'd like wrapped. BoneCP, one of your competitors :) lets me do that.


## brettwooldridge (14 Jan 2014)

Committed to dev branch, and will appear in the 1.2.5 release.


## brettwooldridge (14 Jan 2014)

The new property is simply `dataSource` and is settable directly on the `HikariConfig` or via a `java.util.Properties` instance passed into the constructor (useful for some Spring implementations).


## mdagit (14 Jan 2014)

i assume that when this is set i don't have to set that dataSourceClassName property?


## brettwooldridge (14 Jan 2014)

Correct, if dataSourceClassName is _also_ present it will be ignored.  I will add an additional warning log.

Sent from my iPhone

> On Jan 15, 2014, at 2:14 AM, mdagit notifications@github.com wrote:
> 
> i assume that when this is set i don't have to set that dataSourceClassName property?
> 
> \
> Reply to this email directly or view it on GitHub.


## brettwooldridge (15 Jan 2014)

Today HikariCP 1.2.6 was published to the maven repository containing this enhancement.


