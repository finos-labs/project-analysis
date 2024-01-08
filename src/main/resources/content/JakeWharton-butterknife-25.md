#Injecting Views in Parent Class throws IllegalAccessError

Owner: JakeWharton

Repo: butterknife

Labels: 

## holmes (12 Apr 2013)

I've got a super class in `a.b.widget` and a child class in `a.b.something`. If the fields are set to anything but public an IllegalAccessError is thrown.

```
        java.lang.RuntimeException: Unable to start activity ComponentInfo{com.squareup.cardcase.development/com.squareup.cardcase.home.WalletHomeActivity}: butterknife.Views$UnableToInjectException: Unable to inject views for com.squareup.cardcase.home.ActivityHistoryPanel{4176d420 V.E..... ......I. 0,0-0,0}
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2180)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2230)
        at android.app.ActivityThread.access$600(ActivityThread.java:141)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1234)
        at android.os.Handler.dispatchMessage(Handler.java:99)
        at android.os.Looper.loop(Looper.java:137)
        at android.app.ActivityThread.main(ActivityThread.java:5041)
        at java.lang.reflect.Method.invokeNative(Native Method)
        at java.lang.reflect.Method.invoke(Method.java:511)
        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:793)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:560)
        at dalvik.system.NativeStart.main(Native Method)
        Caused by: butterknife.Views$UnableToInjectException: Unable to inject views for com.squareup.cardcase.home.ActivityHistoryPanel{4176d420 V.E..... ......I. 0,0-0,0}
        at butterknife.Views.inject(Views.java:125)
        at butterknife.Views.inject(Views.java:77)
        at com.squareup.cardcase.ui.widget.MessageAndListView.<init>(MessageAndListView.java:22)
        at com.squareup.cardcase.home.ActivityHistoryPanel.<init>(ActivityHistoryPanel.java:37)
        at com.squareup.cardcase.home.HomeFragment.buildView(HomeFragment.java:46)
        at com.squareup.cardcase.ui.AbstractWalletFragment.onCreateView(AbstractWalletFragment.java:48)
        at android.app.Fragment.performCreateView(Fragment.java:1695)
        at android.app.FragmentManagerImpl.moveToState(FragmentManager.java:885)
        at android.app.FragmentManagerImpl.moveToState(FragmentManager.java:1057)
        at android.app.BackStackRecord.run(BackStackRecord.java:682)
        at android.app.FragmentManagerImpl.execPendingActions(FragmentManager.java:1435)
        at android.app.Activity.performStart(Activity.java:5113)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2153)
        ... 11 more
        Caused by: java.lang.reflect.InvocationTargetException
        at java.lang.reflect.Method.invokeNative(Native Method)
        at java.lang.reflect.Method.invoke(Method.java:511)
        at butterknife.Views.inject(Views.java:117)
        ... 23 more
        Caused by: java.lang.IllegalAccessError: tried to access field com.squareup.cardcase.home.ActivityHistoryPanel.messageSheet from class com.squareup.cardcase.home.ActivityHistoryPanel$$ViewInjector
        at com.squareup.cardcase.home.ActivityHistoryPanel$$ViewInjector.inject(ActivityHistoryPanel$$ViewInjector.java from OutputFileObject:8)
        ... 26 more
```


