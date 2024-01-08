#Lazy Downloaded Images does not Fit the screen in ViewPager

Owner: Baseflow

Repo: PhotoView

Labels: 

## chomi3 (06 Dec 2012)

Hi,
I'm lazy loading pictures using yours great NetworkCacheImageView (Android-BitmapMemoryCache), and there's no way I can make the image to fit the screen once loaded in the ViewPager.

here's the InstatiateItem() {
NetworkedCacheableImageView imageView = new NetworkedCacheableImageView(container.getContext(), mCache);
            PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);  
            imageView.loadImage(mCache, imageUrl[position]);
            attacher.update();

```
        // Now just add ImageView to ViewPager and return it
        container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        return imageView;
```

}

what's interesting is that after scrolling to the end of the viewpager, and starting to go back, the images starts to be centered and fitted to the size of the screen. (which I presume they're being loaded from the cache with known width / height).

any hints on how to make this working?


## chomi3 (06 Dec 2012)

ok stupid me, just needed to pass PhotoViewAttacher to the NetworkedCacheableImageView as a parameter and call attacher.update() in onPostExecute or during setImageBitmap if taken from cache.
however it maybe of help for someone else


