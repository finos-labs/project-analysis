#Caching post-processed images

Owner: facebook

Repo: fresco

Labels: enhancement 

## REWKyleB (27 Mar 2015)

Is it possible to process the image before it is cached and cache that processed image?


## plamenko (28 Mar 2015)

We don't support that at the moment. We were considering doing that, but there are some non-trivialities when it comes to bitmap-cache-key creation. Cache-key must reflect mutation, but we don't want to kick every other image from cache by filling it with many variants of the same image.
In short, it's doable, we just haven't implemented it yet.


## nbarraille (03 Apr 2015)

Picasso handles this by having the cache key a function of the URI, resize parameters, transformation keys, etc...
IMO it's pretty important, as currently a ListView with Postprocessor will always show spinner when you scroll back.


## plamenko (04 Apr 2015)

Yeah, agreed. I'm working on it.


## KyleDBoyd (06 May 2015)

Post processing works awesome! Looking forward to the caching to make it usable in a list view 


## cabbage (27 May 2015)

+1 for post-processed image caching!

Currently I'm post-processing background images which change a lot since they're bound to a list which updates the background every time a new list-item is displayed. And running a RenderScript blur over those images every time could be a battery drainer (I suppose)


## tyronen (20 Jul 2015)

Added in v0.6.0. See http://frescolib.org/docs/modifying-image.html#caching-postprocessed-images.


