#Support armeabi and mips

Owner: termux

Repo: termux-app

Labels: 

## mvdan (26 Oct 2015)

Any reason why native code for these architectures isn't compiled? As far as I can tell, it would be as simple as adding those to `Application.mk`. Unless you intentionally left them out for a reason.


## fornwall (26 Oct 2015)

The reason why `armeabi` and `mips` are not built in the app is that the packages (https://github.com/termux/termux-packages) are only available for `armeabi-v7a` and `x86`, so to avoid people installing the app without any available packages the app is only built for the architectures where packages are available.

It would require some porting and testing to make the packages available for `mips`, which will only get done if there is enough demand or if someone is interested in doing the work.

As for `armeabi`, I don't know if there is any Android 5 or later (the Android version Termux requires) ARM device not supporting `armeabi-v7a`?


## mvdan (02 Nov 2015)

Fair point, hadn't thought about the packages.

The `armeabi` reply then brings up another question - why is the minsdk so high?


## fornwall (03 Nov 2015)

The minsdk is also due to the packages - Android made some rather large changes to the system libc in Android 5.0, so maintaining compatibility with both Android 5.0+ and previous versions at the same time means a lot of work and time for these kind of packages (possibly forcing building and hosting two separate apt repositories)..


