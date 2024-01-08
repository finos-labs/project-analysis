#Loading images in Widgets

Owner: facebook

Repo: fresco

Labels: 

## pavlospt (27 Mar 2015)

I am supposing that this won't be possible based on the way this library is architected and how widgets work. If there is a possibility can you please give me a hint on how should I approach this and I could make a PR if I have something done.


## IanChilds (27 Mar 2015)

You should be able to use the ImagePipeline with BaseBitmapDataSubscriber for this - have a look [here](https://github.com/facebook/fresco/blob/master/imagepipeline/src/main/java/com/facebook/imagepipeline/datasource/BaseBitmapDataSubscriber.java#L23:L46). You need to make sure you pass the bitmap to the system in the `updateStatus` method as the bitmap will be cleaned up when `onNewResultImpl` finishes. 

Let me know if you have any problems. 


## pavlospt (27 Mar 2015)

What do you mean by passing the bitmap to the system? 


## plamenko (28 Mar 2015)

As Ian explained, after submitting the request to the pipeline, pipeline returns a DataSource. Attach your BaseBitmapDataSubscriber to that DataSource.
Important note is that the bitmap we provide is valid only until onNewResultImpl finishes.
See `BaseBitmapDataSubscriber.onNewResultImpl(@Nullable Bitmap bitmap)`.
That means that you cannot hold onto the reference of the bitmap we provide, either you have to copy the bitmap manually, or, If I am not wrong, the system will do just that for you when passing the Bitmap to the Widget, as this is another process and reference cannot be shared.


## pavlospt (28 Mar 2015)

@plamenko Ok thank you. Will try it and if I find any problems on the way will ask again.


