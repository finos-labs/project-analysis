#Logging statements (like the ones in the LruBitmapPool) make the app drop frames

Owner: bumptech

Repo: glide

Labels: enhancement 

## csobrinho (03 Sept 2013)

Even when everything is being done asynchronously the logging still block the app (maybe due to some internal locking mechanism) and cause several frames to be dropped. In a S4 I notice between 100-500ms lag. Adding a simple _!DEBUG_ in the Log.v and Log.v helped a lot!

This is more visible when you have a lot of small bitmaps (15-20) in your Listview.

A more elegant solution can be implemented in the Glide.init call for instance.

``` patch
diff --git a/library/src/com/bumptech/glide/util/Log.java b/library/src/com/bumptech/glide/util/Log.java
index 161a403..e2faea6 100644
--- a/library/src/com/bumptech/glide/util/Log.java
+++ b/library/src/com/bumptech/glide/util/Log.java
@@ -8,6 +8,7 @@ import java.util.Date;

 public class Log {
     private static final String TAG = "GLIDE";
+    public static boolean DEBUG = false;

     @SuppressLint("SimpleDateFormat")
     private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
@@ -33,12 +34,18 @@ public class Log {
     }

     public static void d(String message, Object... args) {
+        if (!DEBUG)
+            return;
+
         String formatted = args.length > 0 ? String.format(message, args) : message;
         formatted = "[" + dateFormat.format(new Date()) + "] " + formatted;
         android.util.Log.d(TAG, formatted);
     }

     public static void v(String message, Object... args) {
+        if (!DEBUG)
+            return;
+
         String formatted = args.length > 0 ? String.format(message, args) : message;
         android.util.Log.v(TAG, formatted);
     }

```


## sjudd (03 Sept 2013)

It's probably as much String.format as locking around Log, but it's still unfortunate. We use proguard to strip out Log.v and Log.d in our release builds of our apps, partly to solve this kind of problem:

``` pro
-assumenosideeffects class android.util.Log {
    public static int v(...);
}
-assumenosideeffects class android.util.Log {
    public static int d(...);
}
-assumenosideeffects class android.util.Log {
    public static int i(...);
}
```

If this is causing enough frames to drop that its super noticeable while developing, it may be best to do what you're suggesting and only turn on logging if explicitly requested. I'd assumed that the frame drop from all of the garbage collections caused by the first bit of scrolling (when we don't have any bitmaps to recycle) would overwhelm any side affects of logging, but maybe that's not a valid assumption.

I'll look in to adding some debug method to Glide to enable/disable logging.


## csobrinho (03 Sept 2013)

I noticed that after the images were on cache that the scrolling was very fast, even on a custom complicated layout.
But, from time to time the scrolling was really slow and sometimes it got almost 1s stop.

Setting DEBUG to false fixed and it makes some sense because we have a lot of small images and we could get between 10-20 logging images while flinging one full page of the ListView.

Thanks for the quick support!


## sjudd (03 Sept 2013)

I couldn't think of a nicer way to implement this, so I used what you suggested: https://github.com/bumptech/glide/commit/c29ca39f16c945b93a314f42ef360e87b1528025

In the future I could see wanting to enable or disable some other types of debugging but this seems like a logical first step. Thanks for reporting the issue and providing a solution. 


