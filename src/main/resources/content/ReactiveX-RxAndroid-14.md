#GradleWrapper jar missing

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## Dorvaryn (18 Sept 2014)

The gradle wrapper jar seems to be missing so the wrapper doesn't work.

This is an issue for people with local gradle distribution version incompatible with this project.


## mttkay (18 Sept 2014)

Yep, we're working on the final build set up on a branch: https://github.com/ReactiveX/RxAndroid/pull/10


## Dorvaryn (18 Sept 2014)

I'm currently building a PR for some code that we created at Novoda that would fit in RxAndroid. I'm trying to add some more tests and I can't seem to be able to run them with the gradle issue. Any ideas ?


## mttkay (18 Sept 2014)

Should be fixed in f80dd5fb0107a850518d0a98512815af75ba663e

Could you pull and retry?


## Dorvaryn (18 Sept 2014)

Working but I'm hitting another issue, seems like the version of nebula scm plugin is using some jdk7 bytecode

Caused by: java.lang.UnsupportedClassVersionError: nebula/plugin/info/scm/ScmInfoProvider : Unsupported major.minor version 51.0
        at nebula.plugin.info.scm.ScmInfoPlugin.$getStaticMetaClass(ScmInfoPlugin.groovy)


## mttkay (18 Sept 2014)

When are you getting this? It builds fine on Travis and on my machine


## Dorvaryn (18 Sept 2014)

When trying ./gradlew clean build


## mttkay (18 Sept 2014)

Yeah I had javac set to JDK7, I see the error now when switching to Java 6


## mttkay (18 Sept 2014)

@quidryan do some or all of the Nebula plugins require JDK7? 

```
18:15:56.139 [ERROR] [org.gradle.BuildExceptionReporter] Caused by: java.lang.UnsupportedClassVersionError: nebula/plugin/info/scm/ScmInfoProvider : Unsupported major.minor version 51.0
18:15:56.139 [ERROR] [org.gradle.BuildExceptionReporter]    at nebula.plugin.info.scm.ScmInfoPlugin.$getStaticMetaClass(ScmInfoPlugin.groovy)
18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    at nebula.plugin.info.scm.ScmInfoPlugin.<init>(ScmInfoPlugin.groovy)
18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    at org.gradle.api.internal.DependencyInjectingInstantiator.newInstance(DependencyInjectingInstantiator.java:62)
18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    at org.gradle.api.internal.plugins.DefaultPluginRegistry.loadPlugin(DefaultPluginRegistry.java:65)
18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    ... 58 more
```


## quidryan (18 Sept 2014)

Yes, a few of them have been compiled against Java 7. Mostly by accident,
they don't use any Java 6 code.

On Thu, Sep 18, 2014 at 9:17 AM, Matthias Käppler notifications@github.com
wrote:

> @quidryan https://github.com/quidryan do some or all of the Nebula
> plugins require JDK7?
> 
> 18:15:56.139 [ERROR] [org.gradle.BuildExceptionReporter] Caused by: java.lang.UnsupportedClassVersionError: nebula/plugin/info/scm/ScmInfoProvider : Unsupported major.minor version 51.0
> 18:15:56.139 [ERROR] [org.gradle.BuildExceptionReporter]    at nebula.plugin.info.scm.ScmInfoPlugin.$getStaticMetaClass(ScmInfoPlugin.groovy)
> 18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    at nebula.plugin.info.scm.ScmInfoPlugin.<init>(ScmInfoPlugin.groovy)
> 18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    at org.gradle.api.internal.DependencyInjectingInstantiator.newInstance(DependencyInjectingInstantiator.java:62)
> 18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    at org.gradle.api.internal.plugins.DefaultPluginRegistry.loadPlugin(DefaultPluginRegistry.java:65)
> 18:15:56.140 [ERROR] [org.gradle.BuildExceptionReporter]    ... 58 more
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/ReactiveX/RxAndroid/issues/14#issuecomment-56063548.


## mttkay (18 Sept 2014)

Would it be a big hassle to recompile them to language level 6?

Otherwise we'd have to force JDK7 on all Android devs. While it's been supported in the Android tool chain since a while now, not everyone might have made the switch. (At SoundCloud we've only made the switch a week ago)


## benjchristensen (01 Oct 2014)

gradle-wrapper added


