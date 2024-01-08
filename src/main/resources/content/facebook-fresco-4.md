#Use Fresco with SubsamplingImageView

Owner: facebook

Repo: fresco

Labels: enhancement 

## alex621 (27 Mar 2015)

I am working on an app that requires handling large image. Currently we are cutting the image into several small pieces. (But as you may think of, the OOM is always bugging us). I would like to know if fresco is able to handle that nicely or not? Thanks.


## tyronen (27 Mar 2015)

If the image is JPEG, you can try [resizing](http://frescolib.org/docs/resizing-rotating.html) it first. The resize happens before decoding, which is generally where OOMs occur.


## alex621 (27 Mar 2015)

Thanks for your response. We can't simply do resizing to fix the problem because the image can be as large as 700px x 10000px. Also we need to support zooming. Resizing is probably not good for our use case.


## jparkie (27 Mar 2015)

Alex621, if you haven't heard about SubsamplingScaleImageView, I recommend searching it on GitHub. It utilizes a tiled image segmentation algorithm like the one used in Android's stock Gallery. The algorithm is best seen in Google Maps, but obviously more complex for Maps. That library supports rotations, scaling, and translating. It was specifically made to handle for OOM errors and it has been tested up to 20,000 by 20,000 pixel images.

Edit: I'm not the owner of the library or a contributor to this library. I'm on my phone. Hopefully, I could make a `Drawee` for this use case: `GalleryDrawee`


## alex621 (29 Mar 2015)

@jparkie Thanks. It is the thing I want :). So the next question is, can fresco and SSIV live together peacefully?


## jparkie (29 Mar 2015)

@alex621, I believe so. I haven't tried it; I haven't had time to dive into the code yet. I'll post my progress when I can. However, it may not be the best pairing. SubsamplingScaleImageView works best with an ImageSource referencing a File, so you don't get really any benefits from Fresco.


## plamenko (17 Apr 2015)

I would have to see how SubsamplingScaleImageView works. If the image file is very big, then it might not be feasible to load the whole image in memory even in encoded format. That means that subsampling or cropping has to happen during decoding from file.
I am not sure what is the API that SubsamplingScaleImageView  uses, but perhaps it is possible to get the File in our disk cache and let SubsamplingScaleImageView  then read directly from it.


## jparkie (17 Apr 2015)

It utilizes an image pyramid segmentation algorithm to decode regions (http://developer.android.com/reference/android/graphics/BitmapRegionDecoder.html and maybe a custom C++ decoder) of the image file through a background thread. Essentially, the famous example is Google Maps; you do not load the whole world as a map, you subsample representations which as you scale into the pyramid, will load higher resolutions just for the viewport. I haven't had anytime to dig into both libraries, but this technique is utilized in the native Gallery application to load the extremely high resolution photos from modern cameras on smartphones.


## plamenko (17 Apr 2015)

Thanks @jparkie . BitmapRegionDecoder is API10 which is good as GB MR1+ is supported. We should definitely make use of this.


## jparkie (17 Apr 2015)

The one problem with BitmapRegionDecoder is with PNGs in a version of Android, I believe. I can't remember which. It's perfectly fine with JPEGs. Anyway, that is why I referenced maybe a custom C++ decoder.


## davemorrissey (17 Apr 2015)

Hey all, I'm the developer of [SSIV](https://github.com/davemorrissey/subsampling-scale-image-view). Because BitmapRegionDecoder (based on Skia) has issues with some images, I extracted image decoding into separate classes so a custom decoder can be used, and wrote an example class using RapidDecoder. This is based on libpng and jpgd. Libpng appears generally better than Skia, but jpgd cannot decode a region of a JPG without decoding the whole image, which this makes it useless for my library. I'm keen to find a better option, and libjpeg-turbo might be suitable.

I'm interested in seeing whether SSIV and Fresco could be used together to provide more reliable support for huge images with pinch to zoom, but at the moment I'm not sure how that could be done. The two libraries are very different. SSIV does currently require files to be on the file system, or in resources or assets, because it needs random access to the file to decode tiles from it.

It might be possible to write a custom decoder for SSIV that uses a native library to read tiles from the encoded memory cache - this would work quite well when browsing high resolution photos from the internet.

I don't have a lot of time to investigate this at the moment but I'll keep an eye on this thread, and answer any questions I can.


## plamenko (18 Apr 2015)

Hi @davemorrissey ! I am thinking of adding a support for getting a File handle to our disk cached resources so that the file can be read directly rather than going through our pipeline. That would help integrating SSIV with Fresco backend.
We use libjpeg-turbo, but I am not sure whether it supports region decoding. I am sure that the JPEG format is suitable for that as the image is split into 8x8 pixel blocks, but whether the library makes use of that I am not sure. It would be worth checking though.


## tyronen (15 Jun 2015)

Please also see our sample code for pinch-to-zoom with Fresco: https://github.com/facebook/fresco/tree/master/samples/zoomable/src/main/java/com/facebook/samples/zoomable


## notsatyarth (03 Nov 2015)

Bump: was any progress was made on this.


## alex621 (03 Nov 2015)

FYI, eventually we decided to cut the large image on server because there are too many problems with Android's image library.


## notsatyarth (03 Nov 2015)

Thats not an option for me though (using Android local images).Which I want to process too.So I'd really love this support,Here's to hoping it happens


## Piasy (09 Nov 2016)

I've just created a big image viewer supporting pan and zoom, with very little memory usage and full featured image loading choices. Powered by Subsampling Scale Image View, Fresco, Glide, and Picasso https://github.com/Piasy/BigImageViewer , hope it helps :)


## oprisnik (15 Dec 2016)

I guess it should be possible to add a custom Fresco decoder that supports something like this: 
You get the `EncodedImage`, create a new `CloseableImage` type that holds the required data and then use a `DrawableFactory` that creates a new `Drawable` type that uses that data. This could be combined with our sample code for zoooming with a new View that can update these special drawables to decode the correct region.

## raymondctc (19 Oct 2017)

But when does Fresco support region decode? I'm able to load the `EncodedImage` in a custom SubsamplingImageView image decoder with , but without region decode, it's useless.

Source code is something like this, any advice?
```
public class FrescoBigImageViewRegionDecoder implements ImageRegionDecoder {
    private static final String TAG = "FrescoBigImageViewRegionDecoder";

    private EncodedImage mEncodedImage;
    private CloseableImage mCloseableImage;
    private CountDownLatch mCountDownLatch;

    public FrescoBigImageViewRegionDecoder() {
        Log.d(TAG, "FrescoBigImageViewRegionDecoder: ");
    }

    @Override
    public Point init(Context context, Uri uri) throws Exception {
        mCountDownLatch = new CountDownLatch(1);
        ImageRequest request = ImageRequest.fromUri(uri);
        Fresco.getImagePipelineFactory().getImagePipeline().fetchEncodedImage(request, context).subscribe(new BaseDataSubscriber<CloseableReference<PooledByteBuffer>>() {
            @Override
            protected void onNewResultImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
                mEncodedImage = new EncodedImage(dataSource.getResult());
                mEncodedImage.parseMetaData();
                mCountDownLatch.countDown();
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
                Log.d(TAG, "onFailureImpl: failed to decode");
                mEncodedImage = new EncodedImage(dataSource.getResult());
                mEncodedImage.parseMetaData();
                mCountDownLatch.countDown();
            }
        }, CallerThreadExecutor.getInstance());
        mCountDownLatch.await();
        return new Point(mEncodedImage.getWidth(), mEncodedImage.getHeight());
    }

    @Override
    public Bitmap decodeRegion(Rect rect, int i) {
        PlatformDecoder decoder = Fresco.getImagePipelineFactory().getPlatformDecoder();
        return decoder.decodeJPEGFromEncodedImage(mEncodedImage, Bitmap.Config.ARGB_8888, mEncodedImage.getSize()).get();
    }

    @Override
    public boolean isReady() {
        return mEncodedImage != null && mEncodedImage.isValid();
    }

    @Override
    public void recycle() {
        if (mEncodedImage != null) {
            mEncodedImage.close();
        }
    }
}
```

## oprisnik (17 Jan 2018)

I started working on region decoding support that can right now be used from a custom decoder. I'll add a quick sample so that this can be enabled before the full support is available in Fresco.

## oprisnik (19 Jan 2018)

A really basic example for this is now available in the Showcase app, see baa629dd46797a3342a50b918ea0989b63819c87

Keep in mind that this only works for JPEGs right now and that this still needs some work if the image is resized / downsampled since the `BitmapFactory.Options#inSampleSize` value is not correctly handled.

