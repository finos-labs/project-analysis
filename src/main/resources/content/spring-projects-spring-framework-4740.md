#countParameterPlaceholders bug [SPR-5]

Owner: spring-projects

Repo: spring-framework

Labels: in: data type: bug 

## spring-projects-issues (06 Dec 2003)

**[Tom Lauren](https://jira.spring.io/secure/ViewProfile.jspa?name=tomlauren)** opened **[SPR-5](https://jira.spring.io/browse/SPR-5?redirect=false)** and commented

The countParameterPlaceholders method of the org.springframework.jdbc.core.support.JdbcUtils class does not correctly  handle queries that have the following substring:

?+?

The "?" marker must be followed by a space or a ")".  To get by, one must modify all such sql portions to be like the following:

? +?

or

? + ?


---

**Affects:** 1.0 M3


## spring-projects-issues (07 Dec 2003)

**[Thomas Risberg](https://jira.spring.io/secure/ViewProfile.jspa?name=thomas.risberg)** commented

I have fixed this - it now counts all placeholders that are not inside a character literal, without worrying about any separators between the placeholders.


## spring-projects-issues (11 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Reopened in order to change the appropriate component


## spring-projects-issues (11 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Changed from SpringCORE to SpringDA. We need to start using appropriate components.


## spring-projects-issues (11 Dec 2003)

**[Dmitriy Kopylenko](https://jira.spring.io/secure/ViewProfile.jspa?name=dmitriy)** commented

Re-close


## spring-projects-issues (11 Dec 2003)

**[Thomas Risberg](https://jira.spring.io/secure/ViewProfile.jspa?name=thomas.risberg)** commented

Re-opened to switch fixed release to M4


## spring-projects-issues (11 Dec 2003)

**[Thomas Risberg](https://jira.spring.io/secure/ViewProfile.jspa?name=thomas.risberg)** commented

Close this issue again.


