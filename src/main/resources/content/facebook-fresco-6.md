#Multiple issues

Owner: facebook

Repo: fresco

Labels: 

## broakenmedia (27 Mar 2015)

Well, i've just been trying to integrate it, so far i've found some (outdated?) docs:
http://frescolib.org/docs/progressive-jpegs.html#_

```
PipelineDraweeController controller = Fresco.newControllerBuilder() .setImageRequest(requests)    
.setOldController(mSimpleDraweeView.getOldController()) .build();
```

I had to cast the new controller builder to PipelineDraweeController, getOldController() is not a function defined in DraweeView, i only have 'getController'.
Further to that, i finally got it to compile, only to be given 'SimpleDraweeView was not initialized!' I dont see anywhere on the Getting started that i need to do any special initialization other than the usual view finding?
edit Changing to a plain 'DraweeView' stopped the crash however, I'm also not sure how to actually start the image load?

```
            ProgressiveJpegConfig pjpegConfig = new ProgressiveJpegConfig() {
            @Override
            public int getNextScanNumberToDecode(int scanNumber) {
                return scanNumber + 2;
            }

            public QualityInfo getQualityInfo(int scanNumber) {
                boolean isGoodEnough = (scanNumber > 5);
                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
            }
        };
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(DetailsActivity.this)

                .setProgressiveJpegConfig(pjpegConfig)

                .build();
        Fresco.initialize(DetailsActivity.this, config);

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(getUriFromUrl(url))
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller =  (PipelineDraweeController)       Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(i.getController())
                .build();

        i.setController(controller);
```

From the looks of all your docs, 'setController' is that poiint, but the javadoc has no mention of this and i seem to see no loading occurring?


## plamenko (27 Mar 2015)

Yes, there is a typo in the documentation, it should be just `.setOldController(view.getController())`, thanks.

For initialization, see our "Getting started with Fresco" page, it's on the very beginning:
http://frescolib.org/docs/index.html#_
Your code looks fine, you are just missing: `Fresco.initialize(context);` somewhere near the app startup.

No casting is necessary, you can just build `DraweeController`. See the example in our sample app:
https://github.com/facebook/fresco/blob/master/sample/src/main/java/com/facebook/fresco/sample/adapters/FrescoAdapter.java#L63


## plamenko (27 Mar 2015)

...continuing discussion from reddit comment.

"SimpleDraweeView was not initialized" is thrown if `SimpleDraweeView` is instantiated without previously initializing it (static method). `Fresco.initialize` does exactly that:
https://github.com/facebook/fresco/blob/master/drawee-backends/drawee-pipeline/src/main/java/com/facebook/drawee/backends/pipeline/Fresco.java#L44

So, I suspect that you are not calling `Fresco.initialize` early enough. Where are you doing that call?


## broakenmedia (27 Mar 2015)

It's the first thing in the onCreate in an Activity!

> On 27 Mar 2015, at 6:05 am, Ognjen Dragoljevic notifications@github.com wrote:
> 
> ...continuing discussion from reddit comment.
> 
> "SimpleDraweeView was not initialized" is thrown if SimpleDraweeView is instantiated without previously initializing it (static method). Fresco.initialize does exactly that:
> https://github.com/facebook/fresco/blob/master/drawee-backends/drawee-pipeline/src/main/java/com/facebook/drawee/backends/pipeline/Fresco.java#L44
> 
> So, I suspect that you are not calling Fresco.initialize early enough. Where are you doing that call?
> 
> —
> Reply to this email directly or view it on GitHub.


## fiskurgit (27 Mar 2015)

Think it needs to be done in your Application onCreate() instead


## broakenmedia (27 Mar 2015)

@fiskurgit Really? The doc does not mention that? I can't actually use an Application class, for various reasons.


## fiskurgit (27 Mar 2015)

oops, no, sorry, just looking through example code now, they're even initialising in Adapters in the sample


## broakenmedia (27 Mar 2015)

Quick update, managed to get it running with SimpleDraweeView by initializing in the activity that opens BEFORE the one in question, unfortunately it STILL does not load the image, just shows the place holder for ever. Working fine with Picasso and the same URI however


## broakenmedia (27 Mar 2015)

Even further still, using a plain SimpleDrawee, removing all code but the basic i.setImageURI(uri) will not load the, nor does it show the placeholder at anypoint (set in XML), no conflicting hierarchy set


## broakenmedia (27 Mar 2015)

It looks as though the issue is a typo in the documentation. It should be http://schemas.android.com/apk/res-auto rather than http:/schemas.android.com/apk/res-auto (note the extra '/') <- Fixed the missing placeholder, no loading still however. Just out of interest, what method do you recommend for building a Uri based on a url? Perhaps that's the issue? Is there any debug output in Fresco?


## IanChilds (27 Mar 2015)

Uri.parse(url) should do the trick. 


## nbradbury (27 Mar 2015)

@xbroak Perhaps the problem is as simple as not having `<uses-permission android:name="android.permission.INTERNET" />` in your manifest?


## plamenko (27 Mar 2015)

How do you build the Uri?


## IlyaEremin (28 Mar 2015)

I have same issue with image loading. I use `SimpleDraweeView.setImageURI(Uri.parse(uriStr))` with next link: http://az735069.vo.msecnd.net/posts/25_8cb72bffeee54d50bc075be2ad3cf56a_o.jpg and I see only placeholder (picasso loads this image fine)
But with this link: https://freeme.blob.core.windows.net/posts/25_2576773736e8485f9698eb126de3960e_o image is loaded fine. I think the issue may be related with ".jpg" ending.


## plamenko (28 Mar 2015)

There is a bug in our default network fetcher that I'm hunting now. We also provide OkHttp fetcher that doesn't suffer this issue. See: http://frescolib.org/docs/using-other-network-layers.html#_


## plamenko (28 Mar 2015)

Closing this issue because it's not focused. Please create separate issues to track one thing at a time.


## broakenmedia (28 Mar 2015)

Just FYI, the issues are unfixed, i'm just going to give the lib a pass. Too many issues for me right now, perhaps i'll come take another look in the future when everythings up-to-date etc... Thanks for the help however!


## plamenko (28 Mar 2015)

For the record, I found the issue:

```
<html><head><title>Object moved</title></head><body>
<h2>Object moved to <a href="https://freeme.blob.core.windows.net/posts/25_8cb72bffeee54d50bc075be2ad3cf56a_o.jpg">here</a>.</h2>
</body></html>
```

HttpURLConnection does not follow redirection.


## plamenko (28 Mar 2015)

@xbroak , it would help if you could create a separate issue for each problem you have rather than just dumping all of them in one issue.
The URI redirection issue can be solved by using some smarter networking library, rather than the default one which is pretty cumbersome. We do provide OkHttp integration and it should be pretty straightforward to set it up. There is a section in our documentation.
As for the other issues, I don't know what else is there left? There were a couple of typos in the docs, but I believe this has been resolved?


## broakenmedia (28 Mar 2015)

I didn't have a URI Redirection problem? That was another user! Regardless, good luck with the project! :)


## broakenmedia (28 Mar 2015)

Well, i went ahead and added the OKHttp just to see if it made any difference, only to hit another issue:

java.lang.NoSuchMethodError: com.squareup.okhttp.OkHttpClient.open
            at com.squareup.okhttp.OkUrlFactory.open(OkUrlFactory.java:44)

using:  compile 'com.facebook.fresco:imagepipeline-okhttp:0.1.0+'


