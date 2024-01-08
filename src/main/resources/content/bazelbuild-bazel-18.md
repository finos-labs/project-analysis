#./compile.sh: line 355: third_party/protobuf/protoc.amd64: cannot execute binary file: Exec format error

Owner: bazelbuild

Repo: bazel

Labels: type: bug P2 

## filmil (24 Mar 2015)

This is what I get when trying to compile Bazel on my machine.

$ ./compile.sh 
Compiling Java stubs for protocol buffers...
./compile.sh: line 355: third_party/protobuf/protoc.amd64: cannot execute binary file: Exec format error

My machine says:
$ uname -a
Linux cowfarm 3.13.0-34-generic #60-Ubuntu SMP Wed Aug 13 15:49:09 UTC 2014 i686 i686 i686 GNU/Linux

I'm guessing all the cool kids run AMD these days. :)


## hanwen (24 Mar 2015)

yeah. It'll probably work if you replace it with a binary that executes correctly on your machine (it should be from protobuf 2.5.0).


## filmil (25 Mar 2015)

Is there any special reason that i686 binaries aren't there? Is it even
worth running bazel on a 32-bit machine?

On Tue, Mar 24, 2015 at 12:20 PM Han-Wen Nienhuys notifications@github.com
wrote:

> yeah. It'll probably work if you replace it with a binary that executes
> correctly on your machine (it should be from protobuf 2.5.0).
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/google/bazel/issues/18#issuecomment-85654449.


## hanwen (25 Mar 2015)

No special reason, we just forgot about i686.


## MarkRunWu (26 Mar 2015)

I tried to replace by 32 bit version protoc.
It still cannot be built cause the codebase is targeted at 64 bit machine. :confounded: 

``` bash
src/main/cpp/blaze.cc: In function ‘void blaze::ActuallyExtractData(const string&, const string&)’:
src/main/cpp/blaze.cc:804:71: error: cannot convert ‘off_t* {aka long int*}’ to ‘int64_t* {aka long long int*}’ for argument ‘4’ to ‘int archive_read_data_block(archive*, const void**, size_t*, int64_t*)’
       retval = archive_read_data_block(blaze_zip, &buf, &size, &offset);
                                                                       ^
```


## damienmg (26 Mar 2015)

Sorry we have to work that out. We are setting up external CI systems and we might able to target 32bits.


## srib (07 Apr 2015)

Any idea when a fix to this might be pushed for i686?


## damienmg (08 Apr 2015)

Hanwen: can you take care of this one?
Also you might want to see with Kristina if we can set-up 32-bit build on Travis


## srib (09 Apr 2015)

Hanwen: thanks for fixing this problem!


## kchodorow (09 Apr 2015)

Whoops, didn't mean to close.  Not sure if 32-bit compile actually works now, but there is a 32-bit protoc in https://github.com/google/bazel/tree/master/third_party/protobuf now.


