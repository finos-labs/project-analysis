#'archive.h' file not found

Owner: bazelbuild

Repo: bazel

Labels: 

## dgageot (24 Mar 2015)

``` bash
./compile.sh
WARNING: Could not find libarchive installation, proceeding bravely.
Compiling Java stubs for protocol buffers...
Compiling Bazel Java code...
Extracting helper classes for Bazel Java...
Creating libblaze.jar...
Compiling SingleJar tool code...
Extracting helper classes for SingleJar tool...
Creating SingleJar_deploy.jar...
Compiling JavaBuilder tool code...
Extracting helper classes for JavaBuilder tool...
Creating JavaBuilder_deploy.jar...
Compiling client .cc files...
src/main/cpp/blaze.cc:66:10: fatal error: 'archive.h' file not found
#include "archive.h"
         ^
1 error generated.
```


## kchodorow (24 Mar 2015)

What OS are on you on?  Did you follow the [install instructions](http://bazel.io/docs/install.html) for downloading libarchive?


## dgageot (24 Mar 2015)

So you basically told me to RTFM :-)
Guess what, it fixes the problem!
Thanks a lot


