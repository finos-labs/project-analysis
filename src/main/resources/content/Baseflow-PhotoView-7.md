#Problem with Small Sized Images

Owner: Baseflow

Repo: PhotoView

Labels: 

## musaulker (15 Nov 2012)

Hello,

Your library is very good for images which has high resolution. But how can I show small images without stretching the image on my app?

Thanks


## chrisbanes (15 Nov 2012)

I question why you're letting the user zoom/pan on a low resolution image in the first place? Also, the whole point of the library is 'stretching the image'.

Do you mean the default scaling?


## musaulker (15 Nov 2012)

Ok I get the point..

I thought this library has some kind of fallback for low resolution images but I think I need to solve it in another way.

Thanks


## chrisbanes (21 Nov 2012)

In the next version there will be a way to set the ScaleType (just like ImageView) so that small images aren't stretched. Hopefully that will fix your issue.


## soarcn (28 Nov 2012)

@chrisbanes  I use photoview in a fullscreen dialog with semi-transparent background in my app. with following layout

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:layout_gravity="center" >
    <ImageView
        android:id="@+id/pv_photo"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_gravity="center"
        android:src="@drawable/content_picture" />
</FrameLayout>

the problem is if I set image view to wrap-content,  image can't zoom-in to full screen in most of case. but with fill_parent, small size screen will be stretched.

so how I could fix problem? or what could be the correct way for this?

thanks!


## chrisbanes (01 Dec 2012)

There is no correct way. The ImageView needs a fixed size to be able to calculate itself.


## ruijun (21 Oct 2014)

@chrisbanes  When the image is very small, how to show originally instead of zoom?


