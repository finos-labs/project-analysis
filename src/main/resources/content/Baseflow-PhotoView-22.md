#ViewPager memory issues and dev branch sample not working

Owner: Baseflow

Repo: PhotoView

Labels: 

## kutothe (18 Dec 2012)

I was having the memory leak issues using a ViewPager, so I switched over to the dev branch to try out those changes. However, after trying for a while to get it to work, it never seemed to register to be able to zoom and tap the photo. So I decided to try the sample app from this repo (on the dev branch). That app wasn't working for me either.

Could someone else please verify that I'm not crazy or missing something?

In the meantime, I've gone back to the master branch and just implemented the cleanup function in the attacher myself for now.


## kutothe (20 Dec 2012)

I ended up using some of the code that is currently in the dev branch. In PhotoViewAttacher.java I added:

```
public final void cleanup() {
        mImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

        // Clear listeners too
        mMatrixChangeListener = null;
        mPhotoTapListener = null;
    }
```

Then my PagerAdapter is done a bit different than the examples here, I'll just include the pertinent parts:

```
private class ImagePagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private PhotoViewAttacher attacher;
        private int currentPos = -1;

        ImagePagerAdapter() {
            inflater = baseActivity.getLayoutInflater();
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

            if (position != currentPos) {
                currentPos = position;
                if (attacher != null) attacher.cleanup();

                View view = (View) object;
                ImageView imageView = (ImageView) view.findViewById(R.id.image);
                attacher = new PhotoViewAttacher(imageView);
                attacher.setOnPhotoTapListener(new PhotoTapListener());
            }
        }

        @Override
        public Object instantiateItem(View view, int position) {
            final View imageLayout = inflater.inflate(R.layout.item_pager_image, null);
            final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
            final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
            final int pos = position;
            PosterObject one = jsonDataObjects.get(position);

            String imageUrl = getAssetsBaseUrl()+"images/posters/"+one.image;

                        // using UniversalImageLoader to lazy load
            imageLoader.displayImage(imageUrl, imageView, options, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(Bitmap loadedImage) {
                    spinner.setVisibility(View.GONE);
                    if (pos == currentPos) attacher.update();
                }
            });

            ((ViewPager) view).addView(imageLayout, 0);
            return imageLayout;
        }
    }
```


## tole42 (20 Dec 2012)

thank you!


## seanoshea (24 Dec 2012)

https://github.com/seanoshea/PhotoView/commit/7c408375acb4ee6965c52ee3e05a8508afd37144 is one possible solution which seems to solve the memory issues with the ViewPager implementation. See https://github.com/seanoshea/krissytosi-android/commit/b5f928ebb14ac1efe8af7c3628467d07ffb92c1b for bringing this implementation into an app.


## kutothe (24 Dec 2012)

Hey Sean. I scanned your code quickly but don't have time ATM to fully review it. Could you summerize what you did to fix the memory issues? Thank you.


## httpdispatch (24 Dec 2012)

@kutothe did you see this? #16


## chrisbanes (24 Dec 2012)

Hey, I'm currently on vacation but will get this fixed and released when I get back (in a few days).


## seanoshea (26 Dec 2012)

@kutothe I just made the mImageView member variable a weak reference. I'd be the first to admit that my implementation makes the code a little messy, but it seems to work. I like your cleanup idea too.


## httpdispatch (27 Dec 2012)

@seanoshea dev branch version doesn't have memory leak with viewpager. Are you sure you need weak reference?


## seanoshea (27 Dec 2012)

@httpdispatch if I switch to the dev branch and leave my code as it stands today, there's still a memory leak. 

However, if I switch to using a PhotoView object in my ViewPager instead of a regular ImageView, I get the following:

E/AndroidRuntime(26212): android.view.InflateException: Binary XML file line #7: Error inflating class uk.co.senab.photoview.PhotoView
E/AndroidRuntime(26212):    at android.view.LayoutInflater.createView(LayoutInflater.java:518)
E/AndroidRuntime(26212):    at android.view.LayoutInflater.createViewFromTag(LayoutInflater.java:570)
E/AndroidRuntime(26212):    at android.view.LayoutInflater.rInflate(LayoutInflater.java:623)
E/AndroidRuntime(26212):    at android.view.LayoutInflater.inflate(LayoutInflater.java:408)
E/AndroidRuntime(26212):    at android.view.LayoutInflater.inflate(LayoutInflater.java:320)
E/AndroidRuntime(26212):    at android.view.LayoutInflater.inflate(LayoutInflater.java:276)
E/AndroidRuntime(26212):    at com.krissytosi.fragments.adapters.ImagePagerAdapter.instantiateItem(ImagePagerAdapter.java:86)
E/AndroidRuntime(26212):    at android.support.v4.view.PagerAdapter.instantiateItem(PagerAdapter.java:110)
E/AndroidRuntime(26212):    at android.support.v4.view.ViewPager.addNewItem(ViewPager.java:649)
E/AndroidRuntime(26212):    at android.support.v4.view.ViewPager.populate(ViewPager.java:783)
E/AndroidRuntime(26212):    at android.support.v4.view.ViewPager.setAdapter(ViewPager.java:344)
E/AndroidRuntime(26212):    at com.krissytosi.fragments.views.PhotoSetDetailView.buildPage(PhotoSetDetailView.java:71)
E/AndroidRuntime(26212):    at com.krissytosi.fragments.PhotoSetsFragment.populatePhotoSet(PhotoSetsFragment.java:203)
E/AndroidRuntime(26212):    at com.krissytosi.fragments.PhotoSetsFragment.handleOnItemClick(PhotoSetsFragment.java:191)
E/AndroidRuntime(26212):    at com.krissytosi.fragments.PhotoSetsFragment.onItemClick(PhotoSetsFragment.java:181)
E/AndroidRuntime(26212):    at android.widget.AdapterView.performItemClick(AdapterView.java:284)
E/AndroidRuntime(26212):    at android.widget.AbsListView$PerformClick.run(AbsListView.java:1812)
E/AndroidRuntime(26212):    at android.os.Handler.handleCallback(Handler.java:587)
E/AndroidRuntime(26212):    at android.os.Handler.dispatchMessage(Handler.java:92)
E/AndroidRuntime(26212):    at android.os.Looper.loop(Looper.java:130)
E/AndroidRuntime(26212):    at android.app.ActivityThread.main(ActivityThread.java:3683)
E/AndroidRuntime(26212):    at java.lang.reflect.Method.invokeNative(Native Method)
E/AndroidRuntime(26212):    at java.lang.reflect.Method.invoke(Method.java:507)
E/AndroidRuntime(26212):    at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:839)
E/AndroidRuntime(26212):    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:597)
E/AndroidRuntime(26212):    at dalvik.system.NativeStart.main(Native Method)
E/AndroidRuntime(26212): Caused by: java.lang.reflect.InvocationTargetException
E/AndroidRuntime(26212):    at java.lang.reflect.Constructor.constructNative(Native Method)
E/AndroidRuntime(26212):    at java.lang.reflect.Constructor.newInstance(Constructor.java:415)
E/AndroidRuntime(26212):    at android.view.LayoutInflater.createView(LayoutInflater.java:505)
E/AndroidRuntime(26212):    ... 25 more
E/AndroidRuntime(26212): Caused by: java.lang.NullPointerException
E/AndroidRuntime(26212):    at uk.co.senab.photoview.PhotoView.setScaleType(PhotoView.java:139)
E/AndroidRuntime(26212):    at android.widget.ImageView.setAdjustViewBounds(ImageView.java:206)
E/AndroidRuntime(26212):    at android.widget.ImageView.<init>(ImageView.java:126)
E/AndroidRuntime(26212):    at android.widget.ImageView.<init>(ImageView.java:108)
E/AndroidRuntime(26212):    at uk.co.senab.photoview.PhotoView.<init>(PhotoView.java:39)
E/AndroidRuntime(26212):    ... 28 more

Looks like the mAttacher member variable isn't instantiated when the PhotoView objects are instantiated from XML files?


## httpdispatch (28 Dec 2012)

@seanoshea I'm using it in xml layout without a problems. Remove scale type attribute from the xml layout for the PhotoView object


## chrisbanes (28 Dec 2012)

@seanoshea That's a separate issue. I've opened #25 for that.


## seanoshea (28 Dec 2012)

@chrisbanes  Sweet - I'd be happy to switch over to the dev branch & see whether the memory issues are resolved without my weak reference fix after #25 is all squared away.


## kutothe (29 Dec 2012)

@httpdispatch I did see #16. I was working with the code from that, and that's where I ended up getting a workaround.

I was away on vacation for a while, but I just got back. I'll be glad to try and contribute a little more to this in the next few days.


## chrisbanes (03 Jan 2013)

de94cd67e10dfbaae2446fe99a115c1211f67b14 should fix the memory issues.


## httpdispatch (03 Jan 2013)

@chrisbanes why don't you use same code formatting style accross commits? Really hard to track what exactly was changed with the commit


## chrisbanes (03 Jan 2013)

It is the same formatter, but the order of methods has changed due to some being made static.

The basic fix was to wrap the ImageView in a WeakReference.


## httpdispatch (03 Jan 2013)

@chrisbanes weird that i can't reproduce memory leak even without weakreference fix. Or it was done for the wrapped image views?


## chrisbanes (03 Jan 2013)

I still like the use of WeakReference anyway as PhotoViewAttacher now knows when to clean itself up (a `cleanup()` should happen automatically now).


## seanoshea (04 Jan 2013)

Tried out the latest from dev & it looks like the memory issues are gone. Thanks!

However, I get IllegalStateExceptions after flicking through a few photos in a ViewPager (https://github.com/seanoshea/krissytosi-android/blob/master/krissytosi/src/com/krissytosi/fragments/adapters/ImagePagerAdapter.java has details of how I'm using a PhotoViewAttacher). Basically, the mImageView gets deallocated but the attacher seems to hang around.

Do you think I'm mis-using the PhotoView, or should I be manually calling cleanup myself?


## seanoshea (07 Feb 2013)

FWIW, I'm not throwing the exception in https://github.com/seanoshea/PhotoView/commit/318f5c3334087651bab931d0dbb420c82cfba8f2 & the memory issues appear to have disappeared too.


