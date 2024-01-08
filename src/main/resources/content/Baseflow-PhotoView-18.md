#Possibility to make OnPhotoTapListener to work even if image not loaded or outside image bounds

Owner: Baseflow

Repo: PhotoView

Labels: 

## httpdispatch (06 Dec 2012)

If PhotoView fits the screen (match_parent) but image takes only part of it when OnPhotoTapListener doesn't work if to click outside the picture. Can it be fixed in some way?


## httpdispatch (06 Dec 2012)

Ok, i've found that it is programmed behaviour that if click is outside displayRect it is ignored.

``` java
                if (displayRect.contains(x, y)) {

                    float xResult = (x - displayRect.left) / displayRect.width();
                    float yResult = (y - displayRect.top) / displayRect.height();

                    mPhotoTapListener.onPhotoTap(mImageView, xResult, yResult);
                    return true;
                }
```

But is it possible to have an option for this if i don't want such restriction?


## httpdispatch (06 Dec 2012)

Tried to use onClickListener but it doesn't fire.


## httpdispatch (06 Dec 2012)

Opened pull #19 for this


## chrisbanes (06 Dec 2012)

As mentioned in the commit, just use `ImageView.setOnClickListener(...)` now.


## httpdispatch (07 Dec 2012)

Thanks for solution but it has one issue. OnClickListener fires if i do double tap to zoom the photo which is not good for common cases


