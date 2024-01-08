#building previous version

Owner: spring-projects

Repo: spring-framework

Labels: 

## akcheung (05 May 2013)

Hi, I was trying to build v3.0.5.RELEASE after checking that particular version out from the source. It failed to build as build-spring-framework/build.xml imports spring-build/multi-bundle/default.xml, but the spring-build directory does not exist until later versions. Perhaps build.xml does not match the version tag?


## philwebb (06 May 2013)

Is there any specific reason that you need build 3.0.5? Have you tried building from the 3.0.X branch?


## akcheung (06 May 2013)

A third party application that I am working with uses 3.0.5, and I am trying to modify the spring source to run some performance tests. The 3.0.X branch has been updated to 3.0.8 as I understand.


## philwebb (06 May 2013)

I think that is correct. 3.0.X is currently at 3.0.8. If you cannot use 3.0.X, the only thing I can suggest is to copy the relevant build scripts from the 3.0.X branch.


## akcheung (06 May 2013)

Sure. So are the build scripts in the 3.0.5 tagged source the correct ones for that version or am I building it incorrectly?


## philwebb (06 May 2013)

I am not sure. I am afraid that haven't tried to build 3.0.x. My guess is that 3.0.5 was migrated from the old subversion repository and the external link to the build scripts did not come across.

See this commit:
https://github.com/SpringSource/spring-framework/commit/2882ae29b2e122b2d0dec189e627d705231f7804


