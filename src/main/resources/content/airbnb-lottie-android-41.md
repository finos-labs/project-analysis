#NullPointerException at AnimatableIntegerValue

Owner: airbnb

Repo: lottie-android

Labels: duplicate 

## Igor-Sh (02 Feb 2017)

```
FATAL EXCEPTION: AsyncTask #4
java.lang.RuntimeException: An error occured while executing doInBackground()
    at android.os.AsyncTask$3.done(AsyncTask.java:299)
    at java.util.concurrent.FutureTask.finishCompletion(FutureTask.java:352)
    at java.util.concurrent.FutureTask.setException(FutureTask.java:219)
    at java.util.concurrent.FutureTask.run(FutureTask.java:239)
    at android.os.AsyncTask$SerialExecutor$1.run(AsyncTask.java:230)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1080)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:573)
    at java.lang.Thread.run(Thread.java:838)
 Caused by: java.lang.NullPointerException
    at com.airbnb.lottie.animatable.AnimatableIntegerValue.<init>(AnimatableIntegerValue.java:27)
    at com.airbnb.lottie.model.ShapeTransform.<init>(ShapeTransform.java:77)
    at com.airbnb.lottie.model.ShapeGroup.shapeItemWithJson(ShapeGroup.java:37)
    at com.airbnb.lottie.model.ShapeGroup.<init>(ShapeGroup.java:84)
    at com.airbnb.lottie.model.ShapeGroup.shapeItemWithJson(ShapeGroup.java:31)
    at com.airbnb.lottie.model.Layer.fromJson(Layer.java:165)
    at com.airbnb.lottie.model.LottieComposition.fromJsonSync(LottieComposition.java:145)
    at com.airbnb.lottie.model.LottieComposition.fromInputStream(LottieComposition.java:97)
    at com.airbnb.lottie.model.LottieComposition$FileCompositionLoader.doInBackground(LottieComposition.java:266)
    at com.airbnb.lottie.model.LottieComposition$FileCompositionLoader.doInBackground(LottieComposition.java:254)
    at android.os.AsyncTask$2.call(AsyncTask.java:287)
    at java.util.concurrent.FutureTask.run(FutureTask.java:234)
    at android.os.AsyncTask$SerialExecutor$1.run(AsyncTask.java:230) 
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1080) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:573) 
    at java.lang.Thread.run(Thread.java:838) 
```

## gpeal (02 Feb 2017)

@Igor-Sh Can you attach the AE file?

## gpeal (02 Feb 2017)

Tracking in #34 

