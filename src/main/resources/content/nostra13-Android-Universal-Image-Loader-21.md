#Retry image loading on OOM

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## nostra13 (21 Feb 2012)

Do another image loading trying after OutOfMemory exception.


## RomanMinenok (28 Feb 2012)

Please make memory cache clearing after OOM or an app will be dead anyway. 


## scompt (05 Apr 2012)

I'm just starting to look at the universal image loader as a possibility for my project. Catching the OOM seems like a bad idea. If all images are stored as weak/soft references, the VM will get rid of any of them that it doesn't need before raising the OOM. Requesting that the GC run a couple times and then trying again doesn't seem like it will help. Has it been shown that this change actually keeps an app alive after the OOM?


## nostra13 (06 Apr 2012)

Sometimes GC don't have time to recycle unused bitmaps - that's why I make some delay between decoding attempts. Catching the OOM is necessary thing while work with big images. And it really work. I tested this case, and sometimes images was decoded successfully at the 2nd attempt, sometimes at the 3rd attempt, but sometimes it doesn't help.
And app is alive after OOM. Why not?


## scompt (06 Apr 2012)

Everything I've ever learned is that you shouldn't try to catch an OOM. For example, from the java.lang.Error documentation:

```
An Error is a subclass of Throwable that indicates serious problems that a reasonable application should not try to catch
```

If catching the OOM is working well in the code and saving the app from crashing then that's great to hear. I'm still a bit suspicious of the code, but it can't be too much worse than having the app simply crash at that point.


## nostra13 (06 Apr 2012)

Yes, I know, Errors should not be caught in app code. But it's mobile app, and I don't want the app crashes during it's work because of internal Android reasons. So I don't see any other way to prevent app crash than to catch errors like OOM in critical code parts.


