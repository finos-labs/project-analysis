#ImageLoader log message not formatted

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## j9 (02 Feb 2012)

In ImageLoader displayImage() method, log string LOG_LOAD_IMAGE_FROM_MEMORY_CACHE is not formatted which leads to placeholder (%s) appearing in logs, instead of cache related information.

``` java
        Bitmap bmp = configuration.memoryCache.get(memoryCacheKey);
        if (bmp != null && !bmp.isRecycled()) {
            Log.i(TAG, LOG_LOAD_IMAGE_FROM_MEMORY_CACHE);
            imageView.setImageBitmap(bmp);
        } else {
            listener.onLoadingStarted();
            if (imageLoadingExecutor.isShutdown()) {
                imageLoadingExecutor = Executors.newFixedThreadPool(configuration.threadPoolSize);
            }
```

I'm not sure what you thought should be there, but I'm using memoryCacheKey now.

``` java
Log.i(TAG, String.format(LOG_LOAD_IMAGE_FROM_MEMORY_CACHE, memoryCacheKey));
```


## nostra13 (02 Feb 2012)

Thanks! Yeah, my mistake. I will fix it today.


