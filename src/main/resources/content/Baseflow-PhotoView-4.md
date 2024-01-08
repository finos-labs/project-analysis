#crash occured in sample app

Owner: Baseflow

Repo: PhotoView

Labels: 

## korniltsev (14 Oct 2012)

Steps to reproduce:
1. open pager sample.
2. zomm out
3. keep one finger on screen
4. move
5. ?!!!
6. PROFIT

device: Desire HD, os - 4.1


## chrisbanes (15 Oct 2012)

Here's an excerpt from the stack trace:

`java.lang.IllegalArgumentException: pointerIndex out of range
at android.view.MotionEvent.nativeGetAxisValue(Native Method)
at android.view.MotionEvent.getX(MotionEvent.java:1981)
at android.support.v4.view.MotionEventCompatEclair.getX(MotionEventCompatEclair.java:32)
at android.support.v4.view.MotionEventCompat$EclairMotionEventVersionImpl.getX(MotionEventCompat.java:86)
at android.support.v4.view.MotionEventCompat.getX(MotionEventCompat.java:210)
at android.support.v4.view.ViewPager.onInterceptTouchEvent(ViewPager.java:1602)
at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:1822)
at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2176)
at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:1919)
at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2176)
at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:1919)
at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2176)
at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:1919)`

Which seems to be caused by this Android bug: http://code.google.com/p/android/issues/detail?id=18990

I'll see if I can work around it.


## chrisjenx (29 Nov 2012)

Ok, Well inline with the other multitouch issue #11, a PhotoViewPager might not be a bad thing anyway, but disappointing that this is an Android bug. Cheers Chris.


## chrisbanes (06 Jan 2013)

Should now be fixed with fbe7598ffdbdc40aa92590e079fcca7b9fdaf7d9.


## shem8 (24 Apr 2013)

Work around #4 fixing it,
Thx!


