#Support a tooling API for tooling suitable for IDE integrations by third-parties

Owner: bazelbuild

Repo: bazel

Labels: type: feature request P2 

## cgruber (24 Mar 2015)

Whether or not Bazel supports Eclipse or Intellij or other tools directly, there are a lot of useful hooks that would permit third-parties to write their own APIs.  This especially includes:
- Exposing an annotated dependency graph
- Exposing hooks around build-steps for given rules, or in a general way
- Allowing hooks for alternate builders, so that incremental compilers like Eclipse's can provide the compilation, and using the produced information in Bazel's computations of what has been completed (in effect, allowing integration of incremental compilation information between the tools).
- hooks/entry-points for partial invocations within tooled contexts, to reduce the work needed to be done by bazel, where such work is already done by the tool. 

That's all I can think of just now, but I wanted this tracking bug in place, more focused on the tooling api.


## davidzchen (25 Mar 2015)

Having YouCompleteMe integration would be awesome as well. :)


## byzhang (27 Apr 2015)

+1 for YCM


## jcanizales (11 Sept 2015)

This would be so sweet to have.


## DrRibosome (08 Jun 2016)

just wondering if this is still planned, or if it's been shelved for a later release - I really like bazel, but ide support is quite important for me


## damienmg (08 Jun 2016)

It is still planned for the end of the week (the interface  exists and is
in the 0.3.0rc1).

On Wed, Jun 8, 2016, 9:24 PM Jack Crawford notifications@github.com wrote:

> just wondering if this is still planned, or if it's been shelved for a
> later release - I really like bazel, but ide support is quite important for
> me
> 
> —
> You are receiving this because you are subscribed to this thread.
> Reply to this email directly, view it on GitHub
> https://github.com/bazelbuild/bazel/issues/31#issuecomment-224700124,
> or mute the thread
> https://github.com/notifications/unsubscribe/ADjHf0cWhuahs0HZlwmtOO9n813ycSusks5qJxbwgaJpZM4D0KPm
> .


## dslomov (09 Jun 2016)

For the impatient, take a look at https://github.com/bazelbuild/e4b and http://tulsi.bazel.io/


