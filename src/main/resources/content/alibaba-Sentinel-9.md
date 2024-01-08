#Thread pools should be created with NamedThreadFactory for identification

Owner: alibaba

Repo: Sentinel

Labels: kind/enhancement 

## CodeIngL (30 Jul 2018)

里面看到了NamedThreadFactory。为了更好区分线程信息，应该

    Executors.newScheduledThreadPool(x)

进行修改

 new ScheduledThreadPoolExecutor(X, new NamedThreadFactory("stl-${something}"));

## sczyh30 (30 Jul 2018)

Yes, the thread pools should be created with `NamedThreadFactory` for clear identification. We'll fix this. Thanks for your suggestion! 

