#Not working on 2.3.4

Owner: google

Repo: ExoPlayer

Labels: 

## 4ntoine (04 Jul 2014)

ExoPlayer is said to require minimum API=9, but it fails to play video on 2.3.4:

01-14 21:46:15.961    7094-7094/com.google.android.exoplayer.demo E/AndroidRuntime﹕ FATAL EXCEPTION: main
    java.lang.NoSuchFieldError: android.os.AsyncTask.THREAD_POOL_EXECUTOR
            at com.google.android.exoplayer.demo.simple.DashVodRendererBuilder.buildRenderers(DashVodRendererBuilder.java:78)
            at com.google.android.exoplayer.demo.simple.SimplePlayerActivity.onResume(SimplePlayerActivity.java:130)
            at android.app.Instrumentation.callActivityOnResume(Instrumentation.java:1150)
            at android.app.Activity.performResume(Activity.java:3882)
            at android.app.ActivityThread.performResumeActivity(ActivityThread.java:2191)
            at android.app.ActivityThread.handleResumeActivity(ActivityThread.java:2228)
            at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:1721)
            at android.app.ActivityThread.access$1500(ActivityThread.java:124)
            at android.app.ActivityThread$H.handleMessage(ActivityThread.java:968)
            at android.os.Handler.dispatchMessage(Handler.java:99)
            at android.os.Looper.loop(Looper.java:130)
            at android.app.ActivityThread.main(ActivityThread.java:3806)
            at java.lang.reflect.Method.invokeNative(Native Method)
            at java.lang.reflect.Method.invoke(Method.java:507)
            at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:839)
            at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:597)
            at dalvik.system.NativeStart.main(Native Method)


## ojw28 (04 Jul 2014)

This is expected. The demo application has a minSdkVersion of 16, so you shouldn't have even been able to install it, at least not using "adb install": https://github.com/google/ExoPlayer/blob/master/demo/src/main/AndroidManifest.xml#L28

The dev guide also notes that ExoPlayer is build to run on API levels >= 16 at the start of the overview section: http://developer.android.com/guide/topics/media/exoplayer.html#overview

The actual library indicates a minSdkVersion of 9 because it's possible to use parts of the ExoPlayer library on older versions of Android if you're, for example, willing to write your own custom TrackRenderer that hooks into software decoders that you package in your apk.


## 4ntoine (04 Jul 2014)

Demo project has minSdkVersion 9 (https://github.com/google/ExoPlayer/blob/master/demo/build.gradle)


## ojw28 (04 Jul 2014)

Ah, that's an oversight in the gradle build. I've been mostly using the Eclipse projects instead. I'll fix that, thanks.


## 4ntoine (04 Jul 2014)

No prob, thanks for clearing min version requirement


## ojw28 (07 Jul 2014)

Fixed in dev in: https://github.com/google/ExoPlayer/commit/b398c594fa15d56217c5482f0ff9740c757274ec


