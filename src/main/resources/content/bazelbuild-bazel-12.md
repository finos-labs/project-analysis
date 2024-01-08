#Eclipse support

Owner: bazelbuild

Repo: bazel

Labels: type: feature request P2 

## benmccann (24 Mar 2015)

I'm hoping the Eclipse support can be open sourced :-)  All the open-source build tools I currently use like Maven, Gradle, and SBT have Eclipse support built-in.


## damienmg (24 Mar 2015)

Ok I don't know if we ever going to ship the current Eclipse support but having an IDE support is definitely something high on our list


## cgruber (24 Mar 2015)

I've sort of generalized this in #31 beyond eclipse.


## PerfectCarl (10 Sept 2015)

interested in this too


## digulla (18 Sept 2015)

@damienmg Do you already have working support for Eclipse internally which you can't share? Or is the quality of the Eclipse support code just too poor to open it?


## damienmg (18 Sept 2015)

We have a Eclipse support internally that also include a lot of internal stuff such as integration with our control version system and it doesn't make much sense to open-source it (not that it is too poor, just not suited for outside Google).


## wrwg (17 Nov 2015)

Eclipse (and IntelliJ) support can just mean being able to generate project files of the IDE via a command line. The IDEs typically listen for changes on their metadata, and then just magically update (at least Eclipse does). 

Gradle eclipse and idea plugins do it this way, for example. I don't think it's too much work, but then it is still a bit too much if one just wants to use bazel.


## damienmg (24 Feb 2016)

No, why?


## wolfgangmeyers (09 Mar 2016)

I have something that might help. Not quite a general purpose solution (the script only supports java and python, requires some hacking to customize for different projects), but I think it should be easy enough to adapt it to your needs. Basically it involves building _deploy jars that roll up all of the compile-time dependencies using bazel and then constructing eclipse files that reference those jar files. I may do some work to make this general-purpose and put it on github, but for now here is the work in progress - https://gist.github.com/wolfgangmeyers/49d8edaa763f5fcdcdf7


## dburnette (26 Mar 2016)

As a former Googler (Eclipse/C++ user), I find it extremely painful using Bazel with a lack of Eclipse support :(. Even if the internal source control stuff can't/shouldn't be open sourced, dependency graph info and incremental build tools would be greatly appreciated!


## wolfgangmeyers (28 Mar 2016)

Sounds like an eclipse plugin might be a better general solution. Providing an "import from existing bazel workspace" feature could then generate the project files from within eclipse.


## rohitsaboo (28 Mar 2016)

As yet another ex-Googler on this thread, would love to see the Eclipse integration part open-sourced :) Also, if you have an idea how long this would take, it would be helpful to know.


## wolfgangmeyers (03 Jun 2016)

Awesome!


