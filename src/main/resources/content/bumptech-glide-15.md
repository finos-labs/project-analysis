#DiskCache initialization is wrong

Owner: bumptech

Repo: glide

Labels: bug 

## csobrinho (30 Aug 2013)

The DiskLruCache is initialized and then the DiskCacheAdapter overrides the instance.


## csobrinho (30 Aug 2013)

A small fix can be found here: https://github.com/motain/android-Glide/commit/3b394ca7eb5e30affe2bfbdcef9f9d8c892ded64


## sjudd (30 Aug 2013)

Thanks for catching and reporting this! 

Fixed: https://github.com/bumptech/glide/commit/fc7d4e9980c7530cf05628a750565ed897895beb


