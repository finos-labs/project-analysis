#Automatic rollback

Owner: brettwooldridge

Repo: HikariCP

Labels: bug 

## hamid-nazari (25 Dec 2013)

Following my previous [question](http://stackoverflow.com/q/20775165/1863244) on SO it would be nice to have automatic rollback for checked-in connections when their `autoCommit` is set to `false`.


## brettwooldridge (26 Dec 2013)

Issue is resolved in the 'dev' branch. You can clone the repository, `git checkout dev`, then build HikariCP using `mvn package`.  This change will be integrated into the next release.


## brettwooldridge (05 Jan 2014)

HikariCP 1.2.2 has been released and published to the maven central repository.


## hamid-nazari (05 Jan 2014)

Thanks for the notice

On Sun, Jan 5, 2014 at 4:19 PM, brettwooldridge notifications@github.comwrote:

> HikariCP 1.2.2 has been released and published to the maven central
> repository.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/brettwooldridge/HikariCP/issues/16#issuecomment-31603377
> .


