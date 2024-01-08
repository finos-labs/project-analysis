#Demo apk.

Owner: scwang90

Repo: SmartRefreshLayout

Labels: 

## BinarySatan (07 Jul 2017)

    Process: com.scwang.refreshlayout, PID: 4879
                                                 java.lang.NullPointerException: Attempt to invoke interface method 'com.scwang.smartrefresh.layout.api.RefreshLayout com.scwang.smartrefresh.layout.api.RefreshLayout.setEnableRefresh(boolean)' on a null object reference
                                                     at com.scwang.smartrefresh.header.FlyRefreshHeader$3.onAnimationEnd(FlyRefreshHeader.java:251)
                                                     at android.animation.AnimatorSet$AnimatorSetListener.onAnimationEnd(AnimatorSet.java:854)
                                                     at android.animation.ValueAnimator.endAnimation(ValueAnimator.java:1171)
                                                     at android.animation.ValueAnimator$AnimationHandler.doAnimationFrame(ValueAnimator.java:722)
                                                     at android.animation.ValueAnimator$AnimationHandler.run(ValueAnimator.java:738)
                                                     at android.view.Choreographer$CallbackRecord.run(Choreographer.java:800)
                                                     at android.view.Choreographer.doCallbacks(Choreographer.java:603)
                                                     at android.view.Choreographer.doFrame(Choreographer.java:571)
                                                     at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:786)
                                                     at android.os.Handler.handleCallback(Handler.java:815)
                                                     at android.os.Handler.dispatchMessage(Handler.java:104)
                                                     at android.os.Looper.loop(Looper.java:194)
                                                     at android.app.ActivityThread.main(ActivityThread.java:5654)
                                                     at java.lang.reflect.Method.invoke(Native Method)
                                                     at java.lang.reflect.Method.invoke(Method.java:372)
                                                     at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1082)
                                                     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:848)

## scwang90 (07 Jul 2017)

谢谢你的反馈，问题已经解决，新的 Demo Apk 已经上传，重新下载就不会出现这个问题啦~

## ITskyIT (07 Jul 2017)

请问怎么解决的

## scwang90 (07 Jul 2017)

你重新下载 Demo 就可以啦 @ITskyIT 

