#View Injection Fails with Android Bootstrap 

Owner: JakeWharton

Repo: butterknife

Labels: 

## donnfelker (29 Mar 2013)

View Injection is failing in [Android Bootstrap](https://github.com/donnfelker/android-bootstrap/issues/33). The error that is happening is: 

java.lang.ClassNotFoundException: com.donnfelker.android.bootstrap.ui.CarouselActivity$$ViewInjector

From what I can tell we're doing everything correctly as according to the docs, but it seems to be broken now. Any ideas what might be causing this? 

Note, the line where the app is bombing out is: https://github.com/JakeWharton/butterknife/blob/master/butterknife/src/main/java/butterknife/Views.java#L109, then an exception is caught and the app moves on, the views are then null .


## xmlspyspring (02 Apr 2013)

I encountered a similar problem. Any idea?

pager is null in CarouselActivity.java:39
(https://github.com/donnfelker/android-bootstrap/issues/35 )


## mararual (08 Apr 2013)

Are there any workarounds for now?


## javera (10 Apr 2013)

maybe you haven't enabled annotations processing in your IDE. check out #12 for possible solutions


## mararual (10 Apr 2013)

I finally got it working! Thanks to Javera for the link. I enabled annotations processing but at was not enough. The problem was here: 

http://screencloud.net/v/ojiB

Disable the exclusion of the annotations folder so it works. :)


## GTOTA (20 Apr 2013)

as above comment ,I enabled annotations processing  and  point it to library (butterknife-1.3.0.jar)  in eclipse ,I didn't find item to disable the exclusion of the annotations folder ,Like so:
![ G8XD3MRK 8 ZCT9P ISN G](https://f.cloud.github.com/assets/4209786/405173/e815a7d8-a9bc-11e2-97db-a6d315296f2f.jpg)

however, app  still doesn't work.
logcat:
FATAL EXCEPTION: main
E/AndroidRuntime(10172): java.lang.RuntimeException: Unable to start activity ComponentInfo{com.donnfelker.android.bootstrap/com.donnfelker.android.bootstrap.ui.CarouselActivity}: butterknife.Views$UnableToInjectException: Unable to inject views for com.donnfelker.android.bootstrap.ui.CarouselActivity@440a8a48
E/AndroidRuntime(10172):    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2339)
E/AndroidRuntime(10172):    at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2366)
E/AndroidRuntime(10172):    at android.app.ActivityThread.access$600(ActivityThread.java:160)
E/AndroidRuntime(10172):    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1319)
E/AndroidRuntime(10172):    at android.os.Handler.dispatchMessage(Handler.java:99)
E/AndroidRuntime(10172):    at android.os.Looper.loop(Looper.java:153)
E/AndroidRuntime(10172):    at android.app.ActivityThread.main(ActivityThread.java:5169)
E/AndroidRuntime(10172):    at java.lang.reflect.Method.invokeNative(Native Method)
E/AndroidRuntime(10172):    at java.lang.reflect.Method.invoke(Method.java:527)
E/AndroidRuntime(10172):    at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:821)
E/AndroidRuntime(10172):    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:584)
E/AndroidRuntime(10172): Caused by: butterknife.Views$UnableToInjectException: Unable to inject views for com.donnfelker.android.bootstrap.ui.CarouselActivity@440a8a48
E/AndroidRuntime(10172):    at butterknife.Views.inject(Views.java:125)
E/AndroidRuntime(10172):    at butterknife.Views.inject(Views.java:66)
E/AndroidRuntime(10172):    at com.donnfelker.android.bootstrap.ui.BootstrapFragmentActivity.setContentView(BootstrapFragmentActivity.java:25)
E/AndroidRuntime(10172):    at com.donnfelker.android.bootstrap.ui.CarouselActivity.onCreate(CarouselActivity.java:37)
E/AndroidRuntime(10172):    at android.app.Activity.performCreate(Activity.java:5034)
E/AndroidRuntime(10172):    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1080)
E/AndroidRuntime(10172):    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2303)
E/AndroidRuntime(10172):    ... 10 more
E/AndroidRuntime(10172): Caused by: java.lang.NoSuchMethodException: inject [class butterknife.Views$Finder, class com.donnfelker.android.bootstrap.ui.CarouselActivity, class java.lang.Object]
E/AndroidRuntime(10172):    at java.lang.Class.getConstructorOrMethod(Class.java:481)
E/AndroidRuntime(10172):    at java.lang.Class.getMethod(Class.java:936)
E/AndroidRuntime(10172):    at butterknife.Views.inject(Views.java:110)


## JakeWharton (24 Apr 2013)

@GTOTA That was fixed in v1.3.1.

And to everyone else. It seems like your IDEs were configured incorrectly? If that's not the case then comment. Ensure that your `*$$ViewInjector` classes are being generated!


## merri-dei (27 Sept 2016)

We also had this exception logged but, so far, only for one Samsung 5.0.1 device. We are also unable to reproduce the exception.


