#HTTP cache options

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## nostra13 (08 Dec 2011)

Investigate HTTP possibilities for caching. Maybe apply its to project.


## tprochazka (05 May 2012)

I already tested several image loading library, but at the first view your is one of the best and based on the same code that I currently use :-)

And this is one of thing that missing me.

I use this https://github.com/candrews/HttpResponseCache, so all HTTP caching code is done. Only one what I need is possibility to plug in own implementation of image downloader, you now use hardcoded imageUrl.openStream();. If you make simple interface with one method with url as input and stream as output, will be possible to use Apache HTTP client or HttpURLConnection which booth support HTTP cache. So, your library will do only memory caching in this situation.

Btw. This project is also interesting for inspirations https://github.com/DHuckaby/Prime, it use great LRU memory and disk cache (https://github.com/JakeWharton/DiskLruCache)


## nostra13 (20 May 2012)

Thanks. Sorry for ma late answer (I read your comment long ago), there are too much new features now I should implement or not implement :) And now I've come to your suggestions and I agree with you. 
Going to implement it, thanks for links.


## nostra13 (31 May 2012)

Implemented possibility to plug in own image downloader - c04c73931f71245dd548fd178e401e771acdce2f

I think HTTP caching is redundant for the library. So I will not implement it, but library's user can do it for own needs.


