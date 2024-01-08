#DWARF information in Go ELF exe causes problems for Auto-Analysis

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Bug Feature: Analysis 

## recvfrom (06 Mar 2019)

**Describe the bug**
After Auto-Analyzing an x86_64 ELF Go executable (the Hello World program from https://gobyexample.com/hello-world), several error messages are displayed (see the 'Additional Context' section below)

**To Reproduce**
Steps to reproduce the behavior:
1. Follow the steps on https://gobyexample.com/hello-world to compile the hello-world executable
2. Import into Ghidra and auto-analyze with default options

**Expected behavior**
I'm not sure... IDA also has problems parsing some of the DWARF header information in compiled Go executables, and from talking with their support team, Go doesn't seem to strictly follow the DWARF specification.  I can provide a more specific, detailed example of this from another executable if you'd like.

Regardless, the 'MemoryAccessException' sounds kinda like a NULL-pointer exception, so I figured I'd report this as a bug just in case.  It'd be awesome to have Ghidra handle parsing the unusual DWARF information from Go binaries, but that is more of a feature request.  :)

**Screenshots**
N/A

**Environment (please complete the following information):**
 - OS: Ubuntu 18.04 x86_64

**Additional context**
```
Skipping DWARF import because a precondition was not met:
Found cross-compilation unit references between DIE records, but 'preload' is not turned on
Manually re-run the DWARF analyzer after adjusting the options or start it via Dwarf_ExtractorScript
Error analyzing GCC DebugFrame exception tables
ghidra.program.model.mem.MemoryAccessException: Unable to read bytes at OTHER:00000000
Demangle Symbol> Unable to demangle symbol at 00401000; name: sync/atomic.(*Value).Store.  Message: Name is null or empty!
Demangle Symbol> Unable to demangle symbol at 00404700; name: runtime.(*waitq).dequeue.  Message: Name is null or empty!
Demangle Symbol> Unable to demangle symbol at 00404870; name: runtime.(*cpuProfile).add.  Message: Name is null or empty!
Demangle Symbol> Unable to demangle symbol at 004049a0; name: runtime.(*cpuProfile).addNonGo.  Message: Name is null or empty!
...
Demangle Symbol> Unable to demangle symbol at 004788d0; name: reflect.(*ptrType).FieldByIndex.  Message: Name is null or empty!
Demangle Symbol> Unable to demangle symbol at 00478910; name: reflect.(*ptrType).FieldByName.  Message: Name is null or empty!

 
There were too many messages to display.
77 messages have been truncated.
```


## odiferousmint (06 Mar 2019)

Are you using go 1.12 to produce the binaries?

In the release notes of go 1.12:

> There have been many improvements to the DWARF debug information produced by the compiler, including improvements to argument printing and variable location information.

## recvfrom (06 Mar 2019)

@odiferousmint I was using 1.10.4, but building with go 1.12 causes similar issues

## odiferousmint (06 Mar 2019)

Ouch. No luck then, sorry. :/

## ryanmkurtz (07 Aug 2019)

I tried reproducing this but couldn't.  I used go 1.12.7 on Ubuntu to produce an ELF, and tried importing/analyzing with default options in Ghidra 9.0, 9.0.4, and current master.

## recvfrom (07 Aug 2019)

@ryanmkurtz I verified that I still get these messages after autoanalysis in Ghidra 9.0.4, but my machine has go 1.10.4, so maybe that's what is causing the differences.  My hello-world binary is attached.

[hello-world.tar.gz](https://github.com/NationalSecurityAgency/ghidra/files/3478384/hello-world.tar.gz)

## ryanmkurtz (07 Aug 2019)

Looks like the exception is actually due to a problem in the `GccExceptionAnalyzer`.

## nsajko (25 Feb 2020)

@recvfrom What happens if you turn on the preloading (the very last option for DWARF auto-analysis) like the error says you should?

## recvfrom (25 Feb 2020)

@nsajko Recreating these steps with Ghidra 9.1.2, the first three messages in the output above aren't displayed, and this is true regardless of whether preloading is turned on or off.

## nsajko (26 Feb 2020)

@recvfrom 
I just tried a hello-world type Go program with Ghidra, and did not get any errors. Regarding the "Unable to demangle symbol errors", the Demangler option for auto-analysis has two sub-options, the latter is something like "Only demangle known mangled symbols". Checking that sub-option should prevent those errors.

(I used Go 1.13.8.)

Also note that there is a Ghidra extension for Go: https://github.com/felberj/gotools, possibly it also can somewhat improve Go analysis.

## adamopolous (24 Aug 2020)

Closing this due to inactivity for now; it can be reopened if needed.

FWIW, I tried this using the current Ghidra master and Go 1.15 and was able to import/analyze with no errors (default settings).

