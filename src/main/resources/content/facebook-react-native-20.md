#npm start fails with "Watcher took too long to load"

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## mfikes (31 Jan 2015)

When I run `npm start`, it fails indicating a timeout. (This is on a circa-2011 Mac laptop.)

```
Mike-Fikess-MacBook-Pro:react-native mfikes$ npm start

> react-native@0.0.1 start /Users/mfikes/Documents/Projects/react-native
> ./packager/packager.sh


 ===============================================================
 |  Running packager on port 8081.       
 |  Keep this packager running while developing on any JS         
 |  projects. Feel free to close this tab and run your own      
 |  packager instance if you prefer.                              
 |                                                              
 |     https://github.com/facebook/react-native                 
 |                                                              
 ===============================================================


React packager ready.

[Error: Watcher took too long to load.]
Error: Watcher took too long to load.
    at null._onTimeout (/Users/mfikes/Documents/Projects/react-native/packager/react-packager/src/FileWatcher/index.js:37:16)
    at Timer.listOnTimeout [as ontimeout] (timers.js:112:15)

  >>> ERROR: could not create packager - please shut down any existing instances that are already running.
```

I've rebooted just to be sure nothing else is running.

To rule out a race, I've increased the `MAX_WAIT_TIME` on line 21 of `react-native/packager/react-packager/src/FileWatcher/index.js` to as much as 2 minutes, but this doesn't resolve the issue.

I've installed `node` and `watchman` via `brew`:

```
Mike-Fikess-MacBook-Pro:~ mfikes$ brew list
node        pcre        watchman
```

and I'm running the latest version of OS X:

```
Mike-Fikess-MacBook-Pro:~ mfikes$ uname -a
Darwin Mike-Fikess-MacBook-Pro.local 14.1.0 Darwin Kernel Version 14.1.0: Mon Dec 22 23:10:38 PST 2014; root:xnu-2782.10.72~2/RELEASE_X86_64 x86_64
```

I have all of this running successfully on another machine, so this will probably boil down to finding out what the critical difference between the two is.


## vjeux (31 Jan 2015)

cc @amasad


## mfikes (31 Jan 2015)

This was an environmental issue with a previous install of `pcre` on this machine and I was able to resolve it. 

Here are details in case anyone else encounters it:

This was on a box that had never had Homebrew installed on it, but did have `pcre` installed in `/usr/local` and other places. When I installed Homebrew, `brew doctor` warned about these `pcre` files, and since I had previously noticed that `pcre` is used for React Native, I proceeded to manually remove all of the `pcre` files that `brew doctor` reported, and then proceeded with `brew install node` and `brew install watcher`.

Given that this appeared to be a `watcher` issue, I tried uninstalling `watcher`, and reinstalling it. At that point, brew had me try `brew link pcre` and pointed out additional existing `pcre` files that it was having problems with, and I addressed each of those, with the offending files/directories including

```
/usr/local/bin/pcre-config
/usr/local/bin/pcregrep
/usr/local/bin/pcretest
/usr/local/share/doc/pcre
/usr/local/share/man/man1/pcre*
/usr/local/share/man/man3/pcre*
```

After cleaning up these additional leftover files, and successfully installing `watchman`, I then used `brew` to uninstall `node`, `pcre`, and `watchman` and clean reinstall `node` and `watchman`.

After all of this, and in a fresh clone of `react-native`, things worked on this box, including running the Movies app in Xcode.


## amasad (31 Jan 2015)

I'm glad it's working now. I'm glad we put a timeout on that so it doesn't fail silently :)


## vjeux (31 Jan 2015)

Is there any way we can add a warning so that other people don't fall into the same trap?


## amasad (31 Jan 2015)

I doubt it's typical problem, it's the first time I've heard of it with watchman. And a lot of people are using it through my sane module. If it comes up again, will have a deeper look.


## mfikes (31 Jan 2015)

FWIW, I had installed `pcre` binaries on this particular machine a few years ago for some esoteric thing I can't even recall the details of. I don't usually install binaries like that. That, combined with the fact that I hadn't been using Homebrew is probably a fairly rare combination. Homebrew itself tried to warn about it, so I had enough clues to go on.

The existence of the timeout _is_ useful, IMHO. If it had somehow additionally indicated something derailing in a way that pointed a finger at `pcre`, that would probably have been helpful and I would have sorted it sooner, but to be honest, I didn't lose a lot of time on this and was able to sort it out pretty quickly anyway.


## frantic (02 Feb 2015)

> Is there any way we can add a warning so that other people don't fall into the same trap?

I think running `watchman` from terminal could give a clue


## brennanpayne (27 Mar 2015)

I'm not sure how it all fell into place, but I ended up in the same place as @mfikes.  I followed his above steps and was able to resolve the issue. 

The odd part is that `pcre` appeared to have been installed by homebrew (it came up in `brew ls`).  I was also able to run `watchman` from the command line in other scenarios and it would work, it was only not working for react-native!  Anyway, uninstalling `pcre` and doing a fresh install of node and watchman resolved my issue.  Thanks for documenting how you fixed it @mfikes!


