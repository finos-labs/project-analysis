#Bazel makes me feel bad about having only 20GB of disk free.

Owner: bazelbuild

Repo: bazel

Labels: type: bug P2 

## bolinfest (24 Mar 2015)

When I run with `--help`:

```
/Users/mbolin/src/bazel$ ./output/bazel --help
WARNING: build volume / is nearly full (5235527 inodes remain).
WARNING: build volume / is nearly full (21.4GB remain).
                                                     [bazel development version]
```

Or the `version` subcommand:

```
/Users/mbolin/src/bazel$ ./output/bazel version
WARNING: build volume / is nearly full (5235535 inodes remain).
WARNING: build volume / is nearly full (21.4GB remain).
Unrecognized option: --javabase=/Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home
```

Bazel's first instinct is to tell me that I only have 21.4GB free. How much disk space do I have to free up to get Bazel to stop belittling my hard drive?


## hanwen (24 Mar 2015)

See https://github.com/google/bazel/blob/master/src/main/cpp/blaze.cc#L1532

I suppose we could hardcode some other threshold, but I'm not sure what it should be.


## lberki (27 Mar 2015)

I vote for hardcoding an absolute amount of free space instead of a percentage.


