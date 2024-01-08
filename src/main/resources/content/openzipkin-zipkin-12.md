#bin/sbt update package-dist fails to resolve elephant-bird-cascading dependency

Owner: openzipkin

Repo: zipkin

Labels: 

## aruld (08 Jun 2012)

[info] Resolving com.twitter.elephantbird#elephant-bird-cascading2;3.0.0 ...
[warn]  module not found: com.twitter.elephantbird#elephant-bird-cascading2;3.0.0
[warn] ==== ibiblio: tried
[warn]   http://mirrors.ibiblio.org/pub/mirrors/maven2/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== twitter.com: tried
[warn]   http://maven.twttr.com/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== powermock-api: tried
[warn]   http://powermock.googlecode.com/svn/repo/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== scala-tools.org: tried
[warn]   http://scala-tools.org/repo-releases/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== testing.scala-tools.org: tried
[warn]   http://scala-tools.org/repo-releases/testing/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== oauth.net: tried
[warn]   http://oauth.googlecode.com/svn/code/maven/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== download.java.net: tried
[warn]   http://download.java.net/maven/2/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== atlassian: tried
[warn]   https://m2proxy.atlassian.com/repository/public/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== jboss: tried
[warn]   http://repository.jboss.org/nexus/content/groups/public/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== local-lookup: tried
[warn]   file:/Users/arul/.m2/repository/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== local: tried
[warn] ==== elephant-bird repo: tried
[warn]   http://oss.sonatype.org/content/repositories/comtwitter-105/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[warn] ==== Concurrent Maven Repo: tried
[warn]   http://conjars.org/repo/com/twitter/elephantbird/elephant-bird-cascading2/3.0.0/elephant-bird-cascading2-3.0.0.pom
[info] Resolving org.scala-tools.testing#specs_2.9.1;1.6.9 ...
[info] Resolving org.scala-lang#scala-compiler;2.9.1 ...
[warn]  ::::::::::::::::::::::::::::::::::::::::::::::
[warn]  ::          UNRESOLVED DEPENDENCIES         ::
[warn]  ::::::::::::::::::::::::::::::::::::::::::::::
[warn]  :: com.twitter.elephantbird#elephant-bird-cascading2;3.0.0: not found
[warn]  ::::::::::::::::::::::::::::::::::::::::::::::
[error] {file:/Users/arul/twitter/zipkin/}zipkin-hadoop/*:update: sbt.ResolveException: unresolved dependency: com.twitter.elephantbird#elephant-bird-cascading2;3.0.0: not found

Is this dependency publicly available?


## johanoskarsson (08 Jun 2012)

Odd, that worked when I ran it on EC2 the other day. I know they've been working on elephant-bird artifacts recently though so something might have changed. I'll look into it.


## johanoskarsson (08 Jun 2012)

In the mean time you should be fine with fetching this branch https://github.com/kevinweil/elephant-bird/tree/branch-3.0 and running mvn install in that, then use the snapshot version in Zipkin. Apologies for the inconvenience.


## aruld (08 Jun 2012)

When I clone that project, I don't get the maven pom.xml, instead I see build.xml. I tried running ant install-local target but it failed with protoc error. Any clues?


## aruld (08 Jun 2012)

nevermind, I figured out the 3.0 branch. trying that now.


## johanoskarsson (08 Jun 2012)

Looks like they've change the url to the elephant-bird repo. I've updated it in this pull request #14. Can you give that one a go and see if it works for you?


## aruld (08 Jun 2012)

No, I believe EB 3 was never pushed to maven central (https://groups.google.com/forum/?fromgroups#!topic/elephantbird-dev/7lFPQUNbB6A) which explains the original problem. Anyways, I was able to checkout EB3 branch using:
git clone https://github.com/kevinweil/elephant-bird.git -b branch-3.0 eb3
and ran mvn package, which fails because I don't have LZO libraries available on my system. 

I believe I'll wait until EB 3 is publicly available. Thanks for your help!


## aruld (10 Jun 2012)

My bad, thought the PR was merged. Applying PR locally fixes this problem. Thanks again!


