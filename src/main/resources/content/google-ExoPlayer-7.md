#Demo app fails to run on 2.3.4

Owner: google

Repo: ExoPlayer

Labels: 

## 4ntoine (04 Jul 2014)

01-14 21:41:21.336    6953-6953/? E/AndroidRuntime﹕ FATAL EXCEPTION: main
    java.lang.NoSuchMethodError: com.google.android.exoplayer.demo.SampleChooserActivity$SampleAdapter.addAll
            at com.google.android.exoplayer.demo.SampleChooserActivity.onCreate(SampleChooserActivity.java:52)
            at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1047)
            at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:1660)
            at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:1716)
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

Duplicate of #8 


## 4ntoine (04 Jul 2014)

it's not a duplicate but it can be solved easily by replacing addAll to adding for each item. Please modify your Demo app to remove such silly errors


## ojw28 (04 Jul 2014)

It's a duplicate because addAll is fine for API levels >= 16. This crash only occurs on API levels < 11, so once the correct minSdkVersion (16) is enforced, this wont be an issue.


