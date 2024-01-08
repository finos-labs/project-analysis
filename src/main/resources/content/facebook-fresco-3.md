#Custom Uri handlers / accepting arbitrary InputStreams

Owner: facebook

Repo: fresco

Labels: enhancement 

## mcginty (26 Mar 2015)

The current Uri handlers are great for many cases, but in our app (and many others) we store our media encrypted on disk. Because of this, vanilla ContentProviders are unavailable as an option as we'd have to write a temporary unencrypted version to disk (ewww). The other option in Fresco would be to setup a localhost HTTP server that streams the unencrypted version (ewwwwww), which is a lot of unnecessary overhead.

If, however, Fresco allowed additional custom Uri handlers that provide InputStreams, we'd be golden. Alternatively, having a setImageInputStream alongside setImageUri would also suffice.


## plamenko (27 Mar 2015)

We are thinking of supporting something very similar (basically either a custom uri handler or specifying InputStream Supplier while building image request).


## kenyee (28 Mar 2015)

I was going to say exactly the same thing...our app has data stored on disk in encrypted form as well.  It's only unencrypted when in the in-memory cache.
Thanks for supporting this soon @plamenko :-)

We also need a way to change the disk data (e.g., user edits a document/photo and writes it to disk, so the cache has to update)...


## plamenko (17 Apr 2015)

I'll have to refactor some of the fetching logic to make this plugin-like, but hopefully I'll get to this some time soon.


## ashughes (19 May 2015)

I'm running into this same issue. I'd like to use this in conjunction with [Conceal](https://github.com/facebook/conceal) or another file encryption mechanism which provides an `InputStream`.


## agirardello (23 Jul 2015)

@plamenko is there a timeline on this or any way I can "help" or use an InputStream with the current API? We would like to use in this app https://play.google.com/store/apps/details?id=fm.clean for its internal Gallery.


## caojianfeng (15 Oct 2015)

I`m running into this issue too.
I think we can use content provider.
See http://frescolib.org/docs/supported-uris.html#_
Fresco support "content:" like url.
so we can make a content provider and do decrypt in it at first.
and then set uri "content:xxx?id=xxx&key=xxx"  to drawee.


## anotherdev (05 Nov 2015)

@caojianfeng do you have an example code for that content provider?


## sohayb (16 Mar 2017)

@plamenko any update regarding a custom Uri handler?

## kirwan (31 Aug 2017)

I don't think we're likely to get to this any time soon. If someone would like to offer a pull request, I'd be willing to offer support.

We're currently cleaning up old issues which aren't progressing but I'll leave this a little longer in case anyone is interested in pursuing this.

## erikandre (15 Jan 2018)

Closing this as part of our cleanup of old issues.

If anyone requires this feature, please consider implementing it and submitting a pull request.

## AntDev95 (01 Sept 2020)

> I don't think we're likely to get to this any time soon. If someone would like to offer a pull request, I'd be willing to offer support.

As I understand it, nothing was implemented in the end?

