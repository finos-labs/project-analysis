#roundAsCircle does not apply

Owner: facebook

Repo: fresco

Labels: 

## hw3jung (27 Mar 2015)

Hi,

I've specified the XML attribute, fresco:roundAsCircle="true", in the following code and the image is still displaying as a square.

< com.facebook.drawee.view.SimpleDraweeView
    android:id="@+id/profile_picture"
    android:layout_width="45dp"
    android:layout_height="45dp"
    android:layout_centerVertical="true"
    android:layout_marginEnd="10dp"
    android:layout_marginRight="10dp"
    fresco:roundAsCircle="true"/>

I've made sure to add the custom namespace, fresco, to the top-level element: 

<?xml version="1.0" encoding="utf-8"?>
< android.support.v7.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    xmlns:fresco="http:/schemas.android.com/apk/res-auto"
    ...>


## plamenko (27 Mar 2015)

That's strange. Since there was a similar report, I wonder whether there is some systematic issue.
Can you please try `fresco:roundWithOverlayColor="@android:color/red"` and tell what happens? That will help us narrow down the issue.


## hw3jung (27 Mar 2015)

I received the following error:

Error:(37, 47) No resource found that matches the given name (at 'roundWithOverlayColor' with value '@android:color/red').


## plamenko (27 Mar 2015)

It seems that for some reason our attrs.xml is not included in your app:
https://github.com/facebook/fresco/blob/master/drawee/src/main/res/values/attrs.xml


## plamenko (27 Mar 2015)

Actually, I may have made a mistake, can you just specify some color instead of `@android:color/red`?


## hw3jung (27 Mar 2015)

Yeah just tried with some color, and now the app compiles again but I don't see the overlay color either (also image is still not a circle).


## leicht-io (27 Mar 2015)

I am having the same error, but without exception. The circular view is simple not being drawed.


## tapanmodh (27 Mar 2015)

Same Issue. The circular view is  not being drawed. It is showing as rectangle image.


## IanChilds (27 Mar 2015)

It looks as though the issue is a typo in the documentation. It should be http://schemas.android.com/apk/res-auto rather than http:/schemas.android.com/apk/res-auto (note the extra '/')


