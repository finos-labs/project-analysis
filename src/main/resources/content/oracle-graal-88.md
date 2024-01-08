#Source and SourceSections are not thread safe.

Owner: oracle

Repo: graal

Labels: oracle-emp 

## chumer (03 Mar 2016)

Something I realized when reviewing https://github.com/graalvm/truffle/pull/87/files. 
Source reloading does not seem to be thread-safe.
In particular Source#getTextMap().


## chumer (18 Jul 2016)

Closing. Continued elsewhere. GR-1080


