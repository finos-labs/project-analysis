#Can't set idleTimeout to 0.

Owner: brettwooldridge

Repo: HikariCP

Labels: bug 

## vpelikh (10 Jan 2014)

Log say: "idleTimeout is less than 30000ms, did you specify the wrong time unit?  Using default instead."


## brettwooldridge (11 Jan 2014)

Ah, we added range checking and forgot to exclude 0.  I'll check the fix into the `dev` branch, but it won't appear in a release until next week.  Until then, set idleTimeout to 2147483647 (max. int) to disable it.


## brettwooldridge (11 Jan 2014)

This is now fixed.  Checkout the dev branch, or wait until 1.2.5 is released next week.


## brettwooldridge (15 Jan 2014)

Today HikariCP 1.2.6 was published to the maven repository containing this fix.


