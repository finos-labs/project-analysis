#Crash on clear cache

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## vernazza (05 Jan 2012)

The clear cache function crashes if the cache folder doesn't exists.
This is beacuse in  DiskCache.java, line 27
cacheDir.listFiles();
returns null.


## nostra13 (05 Jan 2012)

Thanks for bug.
Can you clarify one thing: did you use **ImageLoaderConfiguration.discCacheDir(String discCacheDirPath)** or **ImageLoaderConfiguration.discCache(DiscCache discCache)** method and what parameters did you pass to this methods?


