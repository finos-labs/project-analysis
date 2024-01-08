#Gradle Build Support [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 4](http://code.google.com/p/disruptor/issues/detail?id=4) moved from a Google Code project.
Added by 2011-07-28T14:14:10.000Z by [mr...@spotd.co.uk](http://code.google.com/u/100549657017506198092/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Invalid).

Original labels: Priority-Medium, Type-Enhancement
### Original description

```
Patch attached to add support for building via Gradle. This could be extended to handle publishing to a Maven repository if there was any interest in avoiding Maven at all costs :)

gradle eclipse - setup Eclipse project config
gradle idea - setup IntelliJ IDEA project config
gradle jar - compile, test and put jar in build/libs
gradle perfTest - compile and run the perf tests
gradle task - list all other available build tasks

Note that the auto-bootstrapping feature of gradle is configured in this patch but the wrapper is not included as it involves a small binary .jar. To generate the wrapper run and commit the results of:

gradle wrapper

A developer can then build the app or configure it for their IDE without having Gradle (or Ant) installed by running:

./gradlew <task>
```


