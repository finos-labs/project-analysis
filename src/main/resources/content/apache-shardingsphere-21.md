#sbt添加  "com.dangdang" % "sharding-jdbc-core" % "1.0.0"出错

Owner: apache

Repo: shardingsphere

Labels: status: invalid 

## enterwhat (28 Feb 2016)

[info] Resolving com.fasterxml.jackson.datatype#jackson-datatype-jsr310;2.5.3 ..[info] Resolving com.fasterxml.jackson.datatype#jackson-datatype-jsr310;2.5.4 ..[info] Resolving org.hibernate.javax.persistence#hibernate-jpa-2.1-api;1.0.0.Fin[info] Resolving net.contentobjects.jnotify#jnotify;0.94-play-1 ...
[error] impossible to get artifacts when data has not been loaded. IvyNode = org.apache.commons#commons-lang3;3.3.2
java.lang.IllegalStateException: impossible to get artifacts when data has not been loaded. IvyNode = org.apache.commons#commons-lang3;3.3.2
    at org.apache.ivy.core.resolve.IvyNode.getArtifacts(IvyNode.java:809)
    at org.apache.ivy.core.resolve.IvyNode.getSelectedArtifacts(IvyNode.java:786)
    at org.apache.ivy.core.report.ResolveReport.setDependencies(ResolveReport.java:235)
    at org.apache.ivy.core.resolve.ResolveEngine.resolve(ResolveEngine.java:235)
    at org.apache.ivy.Ivy.resolve(Ivy.java:517)
    at sbt.IvyActions$.sbt$IvyActions$$resolve(IvyActions.scala:279)
    at sbt.IvyActions$$anonfun$updateEither$1.apply(IvyActions.scala:188)
    at sbt.IvyActions$$anonfun$updateEither$1.apply(IvyActions.scala:165)
    at sbt.IvySbt$Module$$anonfun$withModule$1.apply(Ivy.scala:155)
    at sbt.IvySbt$Module$$anonfun$withModule$1.apply(Ivy.scala:155)
    at sbt.IvySbt$$anonfun$withIvy$1.apply(Ivy.scala:132)
    at sbt.IvySbt.sbt$IvySbt$$action$1(Ivy.scala:57)
    at sbt.IvySbt$$anon$4.call(Ivy.scala:65)
    at xsbt.boot.Locks$GlobalLock.withChannel$1(Locks.scala:93)
    at xsbt.boot.Locks$GlobalLock.xsbt$boot$Locks$GlobalLock$$withChannelRetries$1(Locks.scala:78)
    at xsbt.boot.Locks$GlobalLock$$anonfun$withFileLock$1.apply(Locks.scala:97)
    at xsbt.boot.Using$.withResource(Using.scala:10)
    at xsbt.boot.Using$.apply(Using.scala:9)
    at xsbt.boot.Locks$GlobalLock.ignoringDeadlockAvoided(Locks.scala:58)
    at xsbt.boot.Locks$GlobalLock.withLock(Locks.scala:48)
    at xsbt.boot.Locks$.apply0(Locks.scala:31)
    at xsbt.boot.Locks$.apply(Locks.scala:28)
    at sbt.IvySbt.withDefaultLogger(Ivy.scala:65)
    at sbt.IvySbt.withIvy(Ivy.scala:127)
    at sbt.IvySbt.withIvy(Ivy.scala:124)
    at sbt.IvySbt$Module.withModule(Ivy.scala:155)
    at sbt.IvyActions$.updateEither(IvyActions.scala:165)
    at sbt.Classpaths$$anonfun$sbt$Classpaths$$work$1$1.apply(Defaults.scala:1369)
    at sbt.Classpaths$$anonfun$sbt$Classpaths$$work$1$1.apply(Defaults.scala:1365)
    at sbt.Classpaths$$anonfun$doWork$1$1$$anonfun$87.apply(Defaults.scala:1399)
    at sbt.Classpaths$$anonfun$doWork$1$1$$anonfun$87.apply(Defaults.scala:1397)
    at sbt.Tracked$$anonfun$lastOutput$1.apply(Tracked.scala:37)
    at sbt.Classpaths$$anonfun$doWork$1$1.apply(Defaults.scala:1402)
    at sbt.Classpaths$$anonfun$doWork$1$1.apply(Defaults.scala:1396)
    at sbt.Tracked$$anonfun$inputChanged$1.apply(Tracked.scala:60)
    at sbt.Classpaths$.cachedUpdate(Defaults.scala:1419)
    at sbt.Classpaths$$anonfun$updateTask$1.apply(Defaults.scala:1348)
    at sbt.Classpaths$$anonfun$updateTask$1.apply(Defaults.scala:1310)
    at scala.Function1$$anonfun$compose$1.apply(Function1.scala:47)
    at sbt.$tilde$greater$$anonfun$$u2219$1.apply(TypeFunctions.scala:40)
    at sbt.std.Transform$$anon$4.work(System.scala:63)
    at sbt.Execute$$anonfun$submit$1$$anonfun$apply$1.apply(Execute.scala:226)
    at sbt.Execute$$anonfun$submit$1$$anonfun$apply$1.apply(Execute.scala:226)
    at sbt.ErrorHandling$.wideConvert(ErrorHandling.scala:17)
    at sbt.Execute.work(Execute.scala:235)
    at sbt.Execute$$anonfun$submit$1.apply(Execute.scala:226)
    at sbt.Execute$$anonfun$submit$1.apply(Execute.scala:226)
    at sbt.ConcurrentRestrictions$$anon$4$$anonfun$1.apply(ConcurrentRestrictions.scala:159)
    at sbt.CompletionService$$anon$2.call(CompletionService.scala:28)
    at java.util.concurrent.FutureTask.run(FutureTask.java:266)
    at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
    at java.util.concurrent.FutureTask.run(FutureTask.java:266)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
    at java.lang.Thread.run(Thread.java:745)
[error](*:update) java.lang.IllegalStateException: impossible to get artifacts when data has not been loaded. IvyNode = org.apache.commons#commons-lang3;3.3.2
[error] Total time: 3 s, completed Feb 29, 2016 12:43:56 AM


