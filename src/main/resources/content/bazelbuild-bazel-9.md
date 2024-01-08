#Unable to compile bazel under Mac 10.9.5

Owner: bazelbuild

Repo: bazel

Labels: 

## wentao (17 Mar 2015)

Did using port to install libarchive, but the compile.sh script failed at link step, complaining about the missing symbols of archive related methods for x86_64 arch.

Creating JavaBuilder_deploy.jar...
Compiling client .cc files...
Linking client...
Undefined symbols for architecture x86_64:
  "_archive_entry_pathname", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_entry_perm", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_errno", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_error_string", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_read_data", referenced from:
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_read_data_block", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_read_free", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_read_new", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_read_next_header", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_read_open_filename", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
  "_archive_read_support_format_zip", referenced from:
      blaze::ActuallyExtractData(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
      blaze::GetInstallBase(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&) in blaze.cc.o
ld: symbol(s) not found for architecture x86_64
clang: error: linker command failed with exit code 1 (use -v to see invocation)


## wentao (17 Mar 2015)

Did another try with:
- download libarchive from github
- compile it by myself

Then link against the customized libarchive with the following command 

export CFLAGS=-I../libarchive/libarchive; export  LDFLAGS=-L../libarchive ; ./compile.sh

Still failed...


## ulfjack (17 Mar 2015)

Uhm, works for me. I'm at a loss what could cause this. It seems like the most likely explanation is that the symbols aren't there. I'm using homebrew on this machine, so the output may look different:

$ ls fromhost/
BUILD       archive.h   archive_entry.h empty.c     libarchive.a

$ ls -l fromhost/libarchive.a 
-r--r--r--  1 ulfjack  5000  965808 Mar 17 21:40 fromhost/libarchive.a

$ nm fromhost/libarchive.a | grep "_archive_read_close"
0000000000000ca1 T ___archive_read_close_filters
0000000000002b88 S ___archive_read_close_filters.eh
0000000000001d54 t __archive_read_close
0000000000002f40 s __archive_read_close.eh
                 U _archive_read_close
                 U ___archive_read_close_filters
0000000000000deb t __archive_read_close
0000000000003298 s __archive_read_close.eh
0000000000000044 T _archive_read_close
00000000000004a8 S _archive_read_close.eh


## kchodorow (17 Mar 2015)

I've reproduced, looking into it...


## kchodorow (17 Mar 2015)

This is a byproduct of any extremely hacky thing we're doing in compile.sh.  If you delete the file fromhost/BUILD and re-run compile.sh, it should work.

I'll work on fixing the compile script.


## wentao (17 Mar 2015)

Yes, it works now! Thanks!


## kchodorow (18 Mar 2015)

For future reference, this should now be fixed in the compile script, too (fixed by https://github.com/google/bazel/commit/bdc24960f552886126cb03809ddd0de31a3ddc01).


## wentao (18 Mar 2015)

Cool, thanks!

On Wed, Mar 18, 2015 at 12:27 PM Kristina notifications@github.com wrote:

> For future reference, this should now be fixed in the compile script, too
> (fixed by bdc2496
> https://github.com/google/bazel/commit/bdc24960f552886126cb03809ddd0de31a3ddc01
> ).
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/google/bazel/issues/9#issuecomment-83045942.


