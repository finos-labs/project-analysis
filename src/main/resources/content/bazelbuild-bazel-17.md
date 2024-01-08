#Improve install process

Owner: bazelbuild

Repo: bazel

Labels: type: feature request P2 

## kchodorow (24 Mar 2015)

We need a better installation process for Bazel.  Distributing binaries would be nice.  We should make packages for brew and ubuntu/debian, and a minimum, plus binary installers for Linux & OS X.


## kjiwa (04 Jul 2015)

Which files should be installed and to where? Off the top of my head I would expect:
- `/etc/bazelrc`
- `/usr/bin/bazel`
- `/usr/share/bazel/base_workspace`

Does Bazel support a system-wide bazelrc?


## damienmg (07 Sept 2015)

I will close this issue as there is now installer. We have other issue to track specific issues with it.


