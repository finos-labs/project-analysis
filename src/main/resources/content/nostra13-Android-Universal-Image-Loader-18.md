#Cash directory path

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## dima-ko (04 Feb 2012)

I think that writing into sd-cards root is not the best practice. It woould be better to use 
appCacheDir = new File(context.getExternalCacheDir(), cacheDirPath);
instead of 
 appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDirPath);


## nostra13 (04 Feb 2012)

Agree with you. But **Environment.getExternalStorageDirectory()** is available only from API 8. So I will borrow sources from Android :) Thanks.


## dima-ko (04 Feb 2012)

```
 :
```

If you're using API Level 7 or lower, use
getExternalStorageDirectory()http://developer.android.com/reference/android/os/Environment.html#getExternalStorageDirectory()
to
open a File http://developer.android.com/reference/java/io/File.html that
represents the root of the external storage, then write your cache data in
the following directory:

/Android/data/_<package_name>_/cache/

The _<package_name>_ is your Java-style package name, such as "
com.example.android.app".

2012/2/4 Sergey Tarasevich <
reply@reply.github.com

> Agree with you. But **Environment.getExternalStorageDirectory()** is
> available only from API 8. So I will borrow sources from Android :) Thanks.
> 
> ---
> 
> Reply to this email directly or view it on GitHub:
> 
> https://github.com/nostra13/Android-Universal-Image-Loader/issues/18#issuecomment-3809398


## RomanMinenok (05 Feb 2012)

Good, thanks. Hard I also thought of that issue yesterday and that it would be a good thing to do.


## nishantmodak (15 Jun 2012)

Would something along these lines be better?

```
private static File getExternalCacheDir(Context context) {
    if (hasExternalCacheDir()) {
        return context.getExternalCacheDir();
    }
    // Before Froyo we need to construct the external cache directory
    final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
    return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
}
/**
 * Check if OS version has built-in external cache dir method.
 *
 * @return
 */
public static boolean hasExternalCacheDir() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
}
```


## nostra13 (15 Jun 2012)

I took getExternalCacheDir() code from Android sources. So I think there is no point to change it.


