#PhotoView + Viewpager memory leak

Owner: Baseflow

Repo: PhotoView

Labels: 

## httpdispatch (04 Dec 2012)

When PhotoView is used inside ViewPager and page changes allocated memory isn't released even if System.gc() is called. This doesn't happen with standard ImageView.


## chrisbanes (04 Dec 2012)

I'm looking into the memory leak now.


## httpdispatch (04 Dec 2012)

Me too. I think it is because of PhotoViewAttacher holds reference to ImageView and it is not destroyed because of some reason


## httpdispatch (04 Dec 2012)

mImageView.getViewTreeObserver().addOnGlobalLayoutListener(this) in PhotoViewAttacher causes the memory leak. If i comment this memory leak doesn't occur.


## chrisbanes (04 Dec 2012)

Yep, that was my guess. I'm going to investigate now with MAT.


## httpdispatch (04 Dec 2012)

Please add getter to PhotoViewAttacher inside PhotoView as temporary solution. So i can remove onGlobalLayoutListener manually when item is destroyed. Then in v4 Viewpager i will call

``` java
@Override
            public void destroyItem(View collection, int position, Object view) {
                View theView = (View) view;
                PhotoView imageView = (PhotoView) theView.findViewById(R.id.image);
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(imageView.getAttacher());
                ...
            }
```


## httpdispatch (04 Dec 2012)

Are you sure that calling mImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this); is correct for all the parents? This method is deprecated. However when in v4 viewpager removeOnGlobalLayoutListener causes NoSuchMethod exception.


## chrisbanes (04 Dec 2012)

There are two methods:
`removeGlobalOnLayoutListener()` & `removeOnGlobalLayoutListener()`

Only the first is deprecated, but is available since API v1.


## httpdispatch (04 Dec 2012)

But such as it is deprecated it may not appear on some PhotoView parents. Not a problem for me  but for somebody else...
Anyway thanks for providing the fix. I can confirm memory is not leaking anymore


## chrisbanes (04 Dec 2012)

Not quite true, the deprecated method is in ViewTreeObserver, not in the parent.

The deprecated method simply does this:

``` java
/**
 * Remove a previously installed global layout callback
 *
 * @param victim The callback to remove
 *
 * @throws IllegalStateException If {@link #isAlive()} returns false
 * 
 * @deprecated Use #removeOnGlobalLayoutListener instead
 *
 * @see #addOnGlobalLayoutListener(OnGlobalLayoutListener)
 */
 @Deprecated
 public void removeGlobalOnLayoutListener(OnGlobalLayoutListener victim) {
    removeOnGlobalLayoutListener(victim);
 }
```

So basically, they just changed the method name to match the naming conventions used everywhere else. I think it's pretty safe to rely on this method, if that changes I'll add in support to use the new method for devices that support it.


## jnagels (23 Apr 2013)

I am recycling my views inside my viewpager to skip the inflating-step. That means when I do "container.removeView()" inside "PagerAdapter.destroyItem()", the method "onDetachedFromWindow()" is call on the PhotoView. 
That will do PhotoViewAttacher.cleanup(), and thus removing all listeners + the imageview!
So when I re-add my photoview to my viewpager, nothing will work because photoviewattacher doesn't have a reference to the imageview.

Wouldn't it be better to make 2 more methods in PhotoViewAttacher:
- onDetachedFromWindow(); //remove global-layout-listener
- onAttachedToWindow(); //add global-layout-listener

And don't clear the reference to the imageview, it will do it automatically when I clear my photoview-"recycler".


