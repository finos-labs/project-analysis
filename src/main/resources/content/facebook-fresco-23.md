#SimpleDraweeView doesn't work in edit mode

Owner: facebook

Repo: fresco

Labels: bug 

## jorgemf (28 Mar 2015)

```
The following classes could not be instantiated:
- com.facebook.drawee.view.SimpleDraweeView (Open Class, Show Exception)
 Tip: Use View.isInEditMode() in your custom views to skip code or show sample data when shown in the IDE  Exception Details java.lang.NullPointerException: SimpleDraweeView was not initialized!   at 
com.facebook.common.internal.Preconditions.checkNotNull(Preconditions.java:226)   at 
com.facebook.drawee.view.SimpleDraweeView.init(SimpleDraweeView.java:68)   at 
com.facebook.drawee.view.SimpleDraweeView.<init>(SimpleDraweeView.java:59)   at 
java.lang.reflect.Constructor.newInstance(Constructor.java:422)   at 
android.view.LayoutInflater.rInflate_Original(LayoutInflater.java:806)   at 
android.view.LayoutInflater_Delegate.rInflate(LayoutInflater_Delegate.java:64)   at 
android.view.LayoutInflater.rInflate(LayoutInflater.java:782)   at 
android.view.LayoutInflater.rInflate_Original(LayoutInflater.java:809)   at 
android.view.LayoutInflater_Delegate.rInflate(LayoutInflater_Delegate.java:64)   at 
android.view.LayoutInflater.rInflate(LayoutInflater.java:782)   at 
android.view.LayoutInflater.rInflate_Original(LayoutInflater.java:809)   at 
android.view.LayoutInflater_Delegate.rInflate(LayoutInflater_Delegate.java:64)   at 
android.view.LayoutInflater.rInflate(LayoutInflater.java:782)   at 
android.view.LayoutInflater.rInflate_Original(LayoutInflater.java:809)   at 
android.view.LayoutInflater_Delegate.rInflate(LayoutInflater_Delegate.java:64)   at 
android.view.LayoutInflater.rInflate(LayoutInflater.java:782)   at 
android.view.LayoutInflater.rInflate_Original(LayoutInflater.java:809)   at 
android.view.LayoutInflater_Delegate.rInflate(LayoutInflater_Delegate.java:64)   at 
android.view.LayoutInflater.rInflate(LayoutInflater.java:782)   at 
android.view.LayoutInflater.rInflate_Original(LayoutInflater.java:809)   at 
android.view.LayoutInflater_Delegate.rInflate(LayoutInflater_Delegate.java:64)   at 
android.view.LayoutInflater.rInflate(LayoutInflater.java:782)   at 
android.view.LayoutInflater.inflate(LayoutInflater.java:504)   at 
android.view.LayoutInflater.inflate(LayoutInflater.java:385)
```


## plamenko (28 Mar 2015)

SimpleDraweeView was not initialized. You should make sure that you initialize the Fresco properly before instantiating SimpleDraweeView.
See: http://frescolib.org/docs/index.html#_


## jorgemf (29 Mar 2015)

When I said edit mode I meant the preview in android studio. Obviously I can not initialized anything in the preview in android studio and it crashes without showing anything. Which it is a bit annoying when you want to create a layout.


## plamenko (30 Mar 2015)

Okay, we can delay that check until after setUri gets called. Let me fix that.


## jorgemf (31 Mar 2015)

Maybe is better avoid doing any check if the view is in edit mode


## plamenko (31 Mar 2015)

Yeah, I made a fix to do nothing if in edit mode. We'll upload it soon.


## tyronen (01 Apr 2015)

Fixed in source.


