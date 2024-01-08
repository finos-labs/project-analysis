#onBufferingUpdate回调的percent 和视频播放的percent 一致 相当于没有缓存？

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## sugarkawhi (07 Dec 2016)

在设置 progressbar 的 setSecondaryProgress时  完全无法体现缓存的进度

## sugarkawhi (07 Dec 2016)

边播边缓存设置为true了

## CarGuo (07 Dec 2016)

是的，缓存逻辑现在和buffering的逻辑是分开的，目前缓存进度的返回还是由ijk那边来决定的，因为这里面涉及到seekto的一些问题，后面需要测试一下看看cache的缓存和buffering之间直接协调进度显示的问题。如果完全缓存下来了，那么就会直接读取本地的了。

## sugarkawhi (07 Dec 2016)

那想获取到IJK的缓存进度有办法吗

## CarGuo (07 Dec 2016)

IJK是没有缓存的，它是类似缓存了一块，然后抛弃了之前的一块这种，你可以试试ijk的demo的效果，其实就是你播放过的，任意拖动都是要触发重新请求。如果是获取VideoCache的缓存的话：

可以这样获取

Use HttpProxyCacheServer.registerCacheListener(CacheListener listener) method to set listener with callback onCacheAvailable(File cacheFile, String url, int percentsAvailable) to be aware of caching progress. Do not forget to to unsubscribe listener with help of HttpProxyCacheServer.unregisterCacheListener(CacheListener listener) method to avoid memory leaks.

Use HttpProxyCacheServer.isCached(String url) method to check was url's content fully cached to file or not.

## CarGuo (08 Dec 2016)

更新了1.4.4，可以看看，修改了关于percent 的

