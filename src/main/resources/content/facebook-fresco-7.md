#roundedCornerRadius does not work

Owner: facebook

Repo: fresco

Labels: 

## skywalkerlw (27 Mar 2015)

I include roundedCornerRadius into the xml file and fetch  a remotely JPG format file(http://www.pngspecialists.com.au/wp-content/uploads/2014/07/PNG_Trade_Slider_OnlineTraining.jpg)  but no rounded affect comes.

<com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_view"
        android:layout_width="220dp"
        android:layout_height="220dp"
        fresco:fadeDuration="300"
        fresco:roundedCornerRadius="10dp"
        />


## plamenko (27 Mar 2015)

Have you added the namespace to the top-level element in the XML file?
xmlns:fresco="http:/schemas.android.com/apk/res-auto


## tapanmodh (27 Mar 2015)

Yes i added the namespace but not working.


## IanChilds (27 Mar 2015)

It looks as though the issue is a typo in the documentation. It should be http://schemas.android.com/apk/res-auto rather than http:/schemas.android.com/apk/res-auto (note the extra '/')


