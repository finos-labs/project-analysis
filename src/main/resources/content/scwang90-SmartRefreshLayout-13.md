#Gradle failed

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## mbpz (10 Jul 2017)

Error:Unable to load class 'org.gradle.internal.logging.LoggingManagerInternal'.
Possible causes for this unexpected error include:

Gradle's dependency cache may be corrupt (this sometimes occurs after a network connection timeout.)
Re-download dependencies and sync project (requires network)
The state of a Gradle build process (daemon) may be corrupt. Stopping all Gradle daemons may solve this problem.
Stop Gradle build processes (requires restart)
Your project may be using a third-party plugin which is not compatible with the other plugins in the project or the version of Gradle requested by the project.
In the case of corrupt Gradle processes, you can also try closing the IDE and then killing all Java processes.

## scwang90 (10 Jul 2017)

Gradle 的 问题要自己解决，出问题的一般与网络有关，Gradle 版本有关

