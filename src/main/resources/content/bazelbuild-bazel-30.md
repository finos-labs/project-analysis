#Push Java built artifacts into Maven

Owner: bazelbuild

Repo: bazel

Labels: type: feature request team-Rules-Java 

## cgrushko (24 Mar 2015)

Allow Bazel to automatically push compiled jars into Maven.


## lberki (27 Mar 2015)

I agree that this would be nice, but realistically, we have a bunch of other burning issues that we should take care of before. For now, Bazel's world ends with output files.


## davidzchen (28 Mar 2015)

I think we will eventually want to have this feature since both Maven and Gradle offer this. I think the interface to this would a rule for pushing to Maven similar to the [Gradle Maven Plugin](http://gradle.org/docs/current/userguide/maven_plugin.html). Perhaps if we implement distribution rules (see #74), pushing to Maven can be a distribution rule.


## damienmg (29 Mar 2015)

We have a whole install story we need to take care of and pushing to a maven repository is probably not going to be soon (especially because we have nothing to comply with maven version scheme)


## jin (19 May 2020)

Please see https://github.com/graknlabs/bazel-distribution - these are now implemented as Starlark rules.

