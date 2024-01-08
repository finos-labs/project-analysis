#How to handle tools/ in Bazel projects?

Owner: bazelbuild

Repo: bazel

Labels: 

## prattmic (24 Mar 2015)

The Getting Started guide has you copy `base_workspace/` to create a workspace in which to place projects.  The workspace contains symlinks into the bazel checkout for `examples/`, `third_party/`, `tools/`.

This works well for a local project, but does not work if I wish to publish a project using Bazel, as the symlinks will almost certainly point to the wrong location.  How are projects expected to handle this?  I could not find it mentioned in any of the docs.  The only obvious solution I see is to do a real copy of the `tools/` directory into my project.  However, that means that future Bazel changes will effectively need to be merged into my repository.

I mention `tools/` specifically, because `examples/` and `third_party/` don't seem to be required, at least for a basic project, however `tools/` is (Without `tools/`, I get `ERROR: Loading of target '//tools/cpp:toolchain' failed; build aborted: no such package 'tools/cpp': BUILD file not found on package path.`).


## prattmic (24 Mar 2015)

Upon further consideration, it makes much more sense to publish only the project directory, not the project inside of an existing workspace.  That way, developers can combine multiple projects into a workspace as they see fit.

This of course means you cannot simply download and build a project unless it is in a workspace, but I suppose nothing can be done about that.


