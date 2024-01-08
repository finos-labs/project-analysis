#Bitmap recycling race

Owner: bumptech

Repo: glide

Labels: bug 

## sjudd (16 Jul 2013)

Bitmaps should be able to be marked pending for a specific view. Currently an ImageLoader can request an image, obtain it from the cache, and mark it not pending all before the original requester has responded to the image. This could cause the image to be added to the bitmap pool twice.


## sjudd (08 Aug 2013)

fixed https://github.com/bumptech/glide/commit/a967562c5c0d2f31bb79e344240f2e0e0ee37721


