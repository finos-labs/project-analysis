# com.airbnb.lottie.LottieAnimationView crashes

Owner: airbnb

Repo: lottie-android

Labels: 

## hafs-r (02 Feb 2017)

  java.lang.RuntimeException: An error occured while executing doInBackground()
                      at android.os.AsyncTask$3.done(AsyncTask.java:304)
                      at java.util.concurrent.FutureTask.finishCompletion(FutureTask.java:355)
                      at java.util.concurrent.FutureTask.setException(FutureTask.java:222)
                      at java.util.concurrent.FutureTask.run(FutureTask.java:242)
                      at android.os.AsyncTask$SerialExecutor$1.run(AsyncTask.java:231)
                      at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1112)
                      at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:587)
                      at java.lang.Thread.run(Thread.java:818)
                   Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.Integer.intValue()' on a null object reference
                      at com.airbnb.lottie.animatable.AnimatableIntegerValue.<init>(AnimatableIntegerValue.java:25)
                      at com.airbnb.lottie.model.Layer.fromJson(Layer.java:88)
                      at com.airbnb.lottie.model.LottieComposition.fromJsonSync(LottieComposition.java:145)
                      at com.airbnb.lottie.model.LottieComposition.fromInputStream(LottieComposition.java:97)
                      at com.airbnb.lottie.model.LottieComposition$FileCompositionLoader.doInBackground(LottieComposition.java:266)
                      at com.airbnb.lottie.model.LottieComposition$FileCompositionLoader.doInBackground(LottieComposition.java:254)
                      at android.os.AsyncTask$2.call(AsyncTask.java:292)
                      at java.util.concurrent.FutureTask.run(FutureTask.java:237)
                      at android.os.AsyncTask$SerialExecutor$1.run(AsyncTask.java:231) 
                      at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1112) 
                      at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:587) 
                      at java.lang.Thread.run(Thread.java:818) 

## gpeal (02 Feb 2017)

@hafsalrahman Can you attach the AE file that led to the crash?

## gpeal (02 Feb 2017)

This and #41 are coming from the opacity parsing. If you or @Igor-Sh could attach a sample file, I can get a fix out quickly.

## Igor-Sh (03 Feb 2017)

No problem. I'll attach it in few hours.

## Igor-Sh (03 Feb 2017)

@gpeal do you need exported AE file with all resources or just generated json?

## gpeal (04 Feb 2017)

@Igor-Sh AE file please

## gpeal (07 Feb 2017)

@Igor-Sh can you attach the AE file so I can take a look at this for the next release?

## Igor-Sh (08 Feb 2017)

Yes, sorry for so long delay)
[animation_out.zip](https://github.com/airbnb/lottie-android/files/760795/animation_out.zip)


## mrGabodroid (08 Feb 2017)

@gpeal We got the same error. I found out it's because the opacity is a float value in the JSON file. Lottie is expecting an Integer, so initialValue stays null and you call intValue() method on a null. I don't know if it's an AE or Bodymovie error that the opacity is a float value. You can find the corrupted JSON part on this [link](https://paste.ofcode.org/w9sHCeRa9r8kfF5d2V4WbE).

## gpeal (08 Feb 2017)

Thanks @gabodroid I'll have this fixed in the next dot release.

## gpeal (09 Feb 2017)

@gabodroid I think you have a different issue. Your json contains no layers and has some expressions.
Lottie doesn't support expressions yet. Do you know why your exported json has no layers?

## gpeal (09 Feb 2017)

@gabodroid https://github.com/airbnb/lottie-android/commit/e7664e2c5e2b6d2588f36216248cb4a72d523dc2 will fix the decimal opacity though

## mrGabodroid (09 Feb 2017)

@gpeal thank you for the quick fix. I sent you only that part of the JSON file, which one was causing the error. We have layers in the whole JSON file. 

