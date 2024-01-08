#Using @Schedule under load balanced enivornment

Owner: spring-projects

Repo: spring-framework

Labels: 

## crshovrd (20 Mar 2013)

I'm using Spring's `@Schedule` to fire events off at a particular time. I'm running into an issue where the same job gets executed multiple times due to the application being deployed to multiple machines.

I haven't been able to locate any type of clustering functionality as part of Spring. Is there something that can solve this problem? I'd like to avoid having to move to Quartz as I don't have the time to rewrite code.


