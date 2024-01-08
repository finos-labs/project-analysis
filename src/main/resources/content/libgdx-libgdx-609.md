#standard void main() causes Verify error on 1.5 and 1.6 devices

Owner: libgdx

Repo: libgdx

Labels: bug wontfix 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 22, 2010 22:38:05_

This is per the discussion we had earlier. I read that the verify error is suppose to list exactly the who/what/when/where/how right above it in logcat, but I saw nothing. This is the error

E/AndroidRuntime(  666): Uncaught handler: thread main exiting due to uncaught exception
E/AndroidRuntime(  666): java.lang.VerifyError: com.dasa.wastedspace.WastedSpace
E/AndroidRuntime(  666):        at com.dasa.wastedspace.Game.onCreate(Game.java:26)
E/AndroidRuntime(  666):        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1123)
E/AndroidRuntime(  666):        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2303)
E/AndroidRuntime(  666):        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2371)
E/AndroidRuntime(  666):        at android.app.ActivityThread.access$1800(ActivityThread.java:118)
E/AndroidRuntime(  666):        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1759)
E/AndroidRuntime(  666):        at android.os.Handler.dispatchMessage(Handler.java:99)
E/AndroidRuntime(  666):        at android.os.Looper.loop(Looper.java:123)
E/AndroidRuntime(  666):        at android.app.ActivityThread.main(ActivityThread.java:4077)
E/AndroidRuntime(  666):        at java.lang.reflect.Method.invokeNative(Native Method)
E/AndroidRuntime(  666):        at java.lang.reflect.Method.invoke(Method.java:521)
E/AndroidRuntime(  666):        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:782)
E/AndroidRuntime(  666):        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:540)
E/AndroidRuntime(  666):        at dalvik.system.NativeStart.main(Native Method)

I basically just commented this section out of the desktop portion of the app

/*
    public static void main(String[] argv) {
        JoglApplication app = new JoglApplication("Wasted Space", 480, 320, false);
        app.getGraphics().setRenderListener(new WastedSpace());
    }
    */

and all is fine. I'll try and get some more useful information on this error as soon as I can to amend this issue (like a bare minimum test bed to reproduce the error)

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=21_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 22, 2010 13:47:29_

This is not a defect. Unless you link gdx-backend-desktop.jar to your Android project Dalvik can't find the JoglApplication class. The solution is to have a launcher for the desktop (with a JoglApplication), a launcher for Android (with an AndroidApplication) and your main logic seperated from both launchers. The reason this happens on Android < 2.0 is that they seem to load all referenced class in a dex file which will throw the exception you see in case the class is not available in any referenced jar. On 2.0 this seems to have changed.

**Status:** WontFix  


