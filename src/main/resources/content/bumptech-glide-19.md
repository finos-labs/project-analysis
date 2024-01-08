#IllegalArgumentException in BitmapFactory.decodeStream

Owner: bumptech

Repo: glide

Labels: bug 

## csobrinho (03 Sept 2013)

- In some rare situations the BitmapFactory can throw a IllegalArgumentException when ever the opts.inBitmap is not null and the native part of the decoder returns a null. My impression is that either there is a rare problem with the bitmap pool (wrong dimensions or no more space available) or the image might have a different config (png vs gif for instance will break).

```
java.lang.IllegalArgumentException: Problem decoding into existing bitmap
    at android.graphics.BitmapFactory.decodeStream(BitmapFactory.java:540)
    at com.bumptech.glide.resize.load.Downsampler.decodeStream(Downsampler.java:165)
    at com.bumptech.glide.resize.load.Downsampler.downsampleWithSize(Downsampler.java:114)
    at com.bumptech.glide.resize.ImageManager$1.downsample(ImageManager.java:80)
    at com.bumptech.glide.resize.load.ImageResizer.load(ImageResizer.java:111)
    at com.bumptech.glide.resize.load.ImageResizer.load(ImageResizer.java:99)
    at com.bumptech.glide.resize.ImageManager$ImageManagerRunner.getFromDiskCache(ImageManager.java:540)
    at com.bumptech.glide.resize.ImageManager$ImageManagerRunner.run(ImageManager.java:523)
    at android.os.Handler.handleCallback(Handler.java:730)
    at android.os.Handler.dispatchMessage(Handler.java:92)
    at android.os.Looper.loop(Looper.java:137)
    at android.os.HandlerThread.run(HandlerThread.java:61)

```

Patch here: https://github.com/motain/android-Glide/commit/0c8659e41be8679c5f06b41ea7c2b6ad6d85c5a4


## sjudd (03 Sept 2013)

Can you reproduce the issue reliably, or are there specific images (or sets of images) that fail?

It's possible that there is a race or a rounding error that is causing the bitmap pool to return an incorrectly sized image. 

I could be wrong about this, but I don't think Bitmap or Bitmap.Config has any relation to the format of the image (png, gif etc) on disk/network, so I'm not sure how trying to reuse a Bitmap that was originally created from a .gif would break if BitmapFactory tried to use it to load an identically sized image from a .jpeg for example.

If somehow a Bitmap with a Bitmap.Config that doesn't match the config we're using to load images made it into the bitmap pool that could certainly cause issues. I'm struggling to think of how that might happen thought.  


## csobrinho (04 Sept 2013)

I could reproduce it by loading a lot of small bitmaps (maybe fill the bitmap pool) and then it would crash. I will try to put a breakpoint in the catch exception.

Also, you can try to use the [Bitmap.sameAs (Bitmap other)](http://developer.android.com/reference/android/graphics/Bitmap.html#sameAs%28android.graphics.Bitmap%29) available in API 12 and the [Bitmap.setHasMipMap(boolean)](http://developer.android.com/reference/android/graphics/Bitmap.html#setHasMipMap%28boolean%29) available in API 17 to improve the scale down quality.

Will post some more details soon about this!


## sjudd (04 Sept 2013)

Out of curiosity, if you cherry pick https://github.com/sjudd/glide/commit/77e561347a5659849239145ddc6b74749579a6e0 do you still see those exceptions?

I still have yet to find a way to reproduce the issue you describe, but I realized its possible there could be collisions between some widths and heights in the bitmap pool. 


## csobrinho (04 Sept 2013)

I'll try tomorrow! BTW, really like the library and will post some ideas for the future development.


## sjudd (05 Sept 2013)

I've figured this out, I'll push a fix tomorrow, but for now here's some code that can reproduce the issue:

``` java
new AsyncTask<Void, Void, Void>() {
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            HttpURLConnection firstConn = (HttpURLConnection) new URL("http://www.gamesfreedom.com/games/images/sushi-cat-2.png").openConnection();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;

            InputStream is = firstConn.getInputStream();
            Bitmap firstBitmap = BitmapFactory.decodeStream(is, null, options);
            Log.d("BING", "first bitmap config=" + firstBitmap.getConfig() + " width=" + firstBitmap.getWidth() + " height=" + firstBitmap.getHeight());

            HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://blog.chappy-cat.com/wp/wp-content/uploads/20130215cat_heart_c.png").openConnection();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inBitmap = firstBitmap;
            options.inSampleSize = 1;
            Bitmap bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream(), null, options);
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}.execute();
```

Essentially BitmapFactory.decodeStream can return Bitmaps with null configs (bitmap.getConfig() == null). These can be drawn normally, but will cause the obscure "error decoding into existing bitmap" IllegalArgumentExceptions when passed in to BitmapFactory.decodeStream as inBitmap.

The solution is to simply refuse to add bitmaps where getConfig() == null in the bitmap pool. 

Here is a gist with some relevant log lines:
https://gh.zbump.com/gist/219

The first url ("http://www.gamesfreedom.com/games/images/sushi-cat-2.png") points to an image that produces a bitmap with a null config. The image is also attached here in case the link dies: 
![null-config-bitmap-factory](https://f.cloud.github.com/assets/1627211/1085498/4f48db7e-15d2-11e3-9178-150a499cb342.png)

Thanks for your help pointing this out, the key was to download random pngs from bing's image search, jpegs (or at least those from flickr) don't appear to cause this problem.


## sjudd (05 Sept 2013)

This is fixed here: https://github.com/bumptech/glide/commit/c256841eed6d0b437e146f35ad334a98d8c51da7

We may be able to do better than this in the future by pooling on config type, but for now this is a simple fix. Feel free to reopen if you're still seeing the same problem.

Glad to hear you like the library, I'm always happy to hear thoughts or feedback, either here or via email. 


