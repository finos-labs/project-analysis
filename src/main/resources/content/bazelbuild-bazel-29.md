#Travis CI using Bazel

Owner: bazelbuild

Repo: bazel

Labels: type: feature request P4 

## cgrushko (24 Mar 2015)

Allow creating projects on Travis which use Bazel to build.


## cgruber (24 Mar 2015)

Technically this is already the case, because of simple execution calls.  I would rename this issue to be more about patching travis-ci to support bazel projects with sane defaults, and less shell-script magic.  All the hooks are already there in a .travis.yml file to do all the needed setup in a pinch. 

So... steps would be:
1. published recipes for travis usage in the bazel docs
2. patch travis to support some of those recipes in a less boiler-platey way. 


## korfuri (02 Aug 2015)

I have set up a working demo at https://github.com/korfuri/bazel-travis

It is not optimal at all for a few reasons:
- it rebuilds bazel from source at every run, which is slow and fragile (I've seen my build break because bazel was broken at head). This will get better once bazel has binary releases. 
- the version of Ubuntu used by Travis is quite old, for instance it ships with gcc 4.6, which has completely broken support for c++11. I fixed that by requiring gcc 4.8 or higher and using a custom crosstool, but apt-get-installing a newer gcc at each run takes time and makes the whole setup unnecessarily complex. 

Nevertheless, it works, feel free to use it as an example for your own projects. 


## damienmg (03 Aug 2015)

Thank you korfuri!


## pcj (12 Aug 2016)

Although this issue is closed, I would add that using a custom CROSSTOOL is no longer necessary for travis.  Instead, select the "trusty beta" image in the `.travis.yml` file:

``` yaml
dist: trusty
sudo: required
os:
  - linux
```

As this has gcc4.8, zip, and jdk8, no longer need to specify oraclejdk8 or addons.  That's it!

https://github.com/pubref/rules_protobuf/blob/master/.travis.yml


## mbrukman (24 Sept 2016)

I've set up a Travis CI build using Bazel binary packages, though it requires manually installing Oracle 8 JDK since Bazel is incompatible with Travis CI's Oracle 8 JDK package. See more details, including a complete config, in issue #1821.

For an even better experience, we would need to get Bazel repo whitelisted for Travis CI (see
https://github.com/travis-ci/apt-source-whitelist/issues/305), at which point one can drop the root privileges and run in container-based infrastructure (which is faster) using the `apt` plugin support in Travis CI.


## ittaiz (09 Jan 2017)

Just wanted to add that the three alternatives look very different and maybe a blog post reviewing the three and explaining pros/cons could help noobs. I ended up using @korfuri's suggestion for a small getting-started repo

## pcj (09 Jan 2017)

FYI: rules_protobuf, rules_go, and rules_closure all have working examples of Travis that build on both Linux and OSX.

## ittaiz (10 Jan 2017)

I didn't mean to suggest any of the alternatives don't work but rather that they differ and so a blog post by someone (maybe the bazel team) could be great for noobs. 

