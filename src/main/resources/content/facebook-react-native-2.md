#`pthread.h` not found

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## jlongster (30 Jan 2015)

To build on my machine (OS X 10.9.3) targeting iOS SDK 7.1 (only one available on my machine), I had to change line 7 in `/ReactKit/Modules/RCTUIManager.m` from `#import <pthread/pthread.h>` to `#import <pthread.h>` to get it to compile.


## vjeux (30 Jan 2015)

cc @nicklockwood 


## sophiebits (30 Jan 2015)

Thanks @jlongster. I just fixed this internally – will update this repo soon.


