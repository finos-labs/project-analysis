#Could you have an cpp example that shows #include headers from other directories?

Owner: bazelbuild

Repo: bazel

Labels: type: documentation (cleanup) P3 

## ninechapters (24 Mar 2015)

Case 1:
foopath/foo.cpp needs to include barpath/bar.h

How do I describe the dependency here?

Case 2:
What about second level header file inclusion?  For example
foopath/foo.cpp needs to include barpath/bar.h, which includes bazpath/baz.h


## cgrushko (24 Mar 2015)

My `cc_library`-knowledge is rusty, so I might be wrong, but -

###Case 1

If you have one BUILD file in `.`, you can just reference the sub-directories like this:

```
cc_library(
  name = "foo",
  srcs = ["foopath/foo.cpp"],
  hdrs = ["barpath/bar.h"],
)
```

If you have two BUILD files, one in `foopath/` and the other in `barpath/`, you can do something like

```
cc_library(
  name = "foo",
  srcs = ["foo.cpp"],
  deps = ["//root/to/barpath:bar"],
)
```

`//root/to/barpath:bar` must be a `cc_library`, which must have a `srcs` attribute.

###Case 2

You can include more than one header,

```
cc_library(
  name = "foo",
  srcs = ["foopath/foo.cpp"],
  hdrs = ["barpath/bar1.h", "barpath/bar2.h"],
)
```


## davidzchen (25 Mar 2015)

From the [Build Encyclopedia section on `cc_library`](http://bazel.io/docs/build-encyclopedia.html#cc_library):

> `hdrs`: The list of header files published by this library to be directly included by sources in dependent rules.

Thus, in your case, you should do the following:

In `barpath/`, define a `cc_library` target for the `bar.cpp` source file that publishes the `bar.h` header file via `hdrs`:

``` python
cc_library(
    name = "bar",
    srcs = ["bar.cpp"],
    hdrs = ["bar.h"],
)
```

And then in `foopath/foo.cpp`, you would have the following `#include`:

``` cpp
#include "barpath/bar.h"
...
```

And then define the build rule under `foopath/` for `foo.cpp` with a dependency on `//barpath:bar`:

``` python
cc_library(
    name = "foo",
    srcs = ["foo.cpp"],
    hdrs = ["foo.h"],
    deps = ["//barpath:bar"],
)
```

Basically, use `hdrs` to indicate which header files `//barpath:bar` publishes, and use `deps` to indicate that `//foopath:foo` depends on `//barpath:bar`. Bazel will then the `hdrs` from `//barpath:bar` to include the correct header files when building `//foopath:foo`.


## davidzchen (25 Mar 2015)

For the case where you have `foopath/foo.cpp` including `barpath/bar.h` which includes `bazpath/baz.h`, you will just have three targets where `//foopath:foo` depends on `//barpath:bar` which depends on `//bazpath:baz`, similar to the following:

Under `bazpath/`:

``` python
cc_library(
    name = "baz",
    srcs = ["baz.cpp"],
    hdrs = ["baz.h"],
    deps = [...],
)
```

Under `barpath/`:

``` python
cc_library(
    name = "bar",
    srcs = ["bar.cpp"],
    hdrs = ["bar.h"],
    deps = [
        "//bazpath:baz",
        ...
    ],
)
```

And finally, under `foopath/`:

``` python
cc_library(
    name = "foo",
    srcs = ["foo.cpp"],
    hdrs = ["foo.h"],
    deps = [
        "//barpath:bar",
    ],
)
```


## damienmg (26 Mar 2015)

I haven't found a documentation label but we should have such an example


