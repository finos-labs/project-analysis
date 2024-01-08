#Disk cache images as-is

Owner: bumptech

Repo: glide

Labels: bug 

## codebreaker (04 Sept 2013)

When images are written to disk looks like they are compressed to jpeg. Am I missing anything? Is there any way to save images as-is? From my download I am getting few transparent png's which appear with darkened in place of transparent image.


## sjudd (04 Sept 2013)

You're correct, by default we compress images as jpegs when we put them in the disk cache to try to minimize our disk usage. 

However, you can change the compression amount and/or format by setting the corresponding options on the ImageManager used by the Glide singleton. To do so, add something like the following to the onCreate method in your Application object if you have one, or the onCreate methods of your activities (or a superclass of your activities):

``` java
Glide glide = Glide.get();
if (!glide.isImageManagerSet()) {
    glide.setImageManager(new ImageManager.Builder(context)
            .setBitmapCompressFormat(Bitmap.CompressFormat.PNG));
}
```

There isn't currently a way to set the compression format per image, but if you're only loading pngs or images with transparency anyway that probably doesn't matter. 


## codebreaker (04 Sept 2013)

In my case most images are JPEG's and few are PNG's. So I was hoping for something like save-whatever-format-you-get.


## sjudd (04 Sept 2013)

That's totally reasonable, it's just not a use case I've run into before now.

One other complication I forgot to mention in my previous comment is that you also need to load the images with inPreferredConfig = ARGB_8888, while we currently default to RGB_565, again to save memory. You can also do this from the ImageManager.Builder, but its not very pleasant at the moment:

``` java
ImageManager.Builder builder = new ImageManager.Builder(context);
builder.decodeBitmapOptions.inPreferredConfig = ARGB_8888;
```

With that out of the way, are you loading mostly pngs in one activity and mostly jpegs in another? If so, one option is to simply replace the ImageManager every time you enter and exit that activity and only change inPreferredConfig and the CompressFormat when you're in the activity that will be displaying pngs. That would look something like this:

``` java
Glide glide = Glide.get();
if (glide.isImageManagerSet()) {
    glide.getImageManager(context).shutdown();
}
glide.setImageManager(new ImageManager.Builder(context)
   ...
);
```

To actually solve the problem I can think of two solutions. 

The first is to add another component like the ImageLoader/ModelLoader/TransformationLoader except for quality. The problem is that it may be difficult to determine the format of the image without reading the image header for some model types.

The second is that I could update the exif parser to also return the image type from the header and simply assume that all pngs have transparency and automatically set the various quality settings. It would be difficult to override the default behavior though if you have a lot of pngs that don't have transparency and image headers are such a mess that you'd probably have some silent and hard to debug failures. 

If you want to take a crack at implementing one of those two solutions, or something else you can think of that would work, I'd be happy to work with you and merge it in. 


## lalith-b (11 Sept 2013)

When setting the Compression format to Bitmap.Config.ARGB_8888 and Compression format to PNG results in crash with errorlog as : `Fatal signal 11 (SIGSEGV) at 0xffd6c8e9 (code=1)`


## sjudd (12 Sept 2013)

The original issue is fixed here: https://github.com/bumptech/glide/commit/525d50359e27ca73eeeba96a994155c684d05292

Not sure why you're seeing an exception, but with the above commit, the calls I described are deprecated (and in fact ignored), so it shouldn't be an issue. If you're still seeing similar exceptions after pulling forward, please open a new issue. 

Thanks for reporting these.


