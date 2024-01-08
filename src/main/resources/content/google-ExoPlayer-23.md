#Cross-domain redirects fail on Android L

Owner: google

Repo: ExoPlayer

Labels: 

## IanDBird (01 Aug 2014)

It was unclear whether this bug was actually and ExoPlayer issue, or is in fact an Android L issue. However, since the change in behaviour only happens when I use the ExoPlayer, I thought best to raise here...
- Device: Nexus 7
- OS: Android L Developer Preview

When attempting to play a video from a server which returns a redirect (302) with a Location pointing to a different domain, the ExoPlayer refuses to start playback. Instead, the following error message is written to the logcat:

```
W/MediaHTTPConnection﹕ readAt 0 / 32768 => java.net.NoRouteToHostException: Cross-domain redirects are disallowed
```

If instead of using ExoPlayer, I use the native `MediaPlayer` on the same URL, it works. This URL also works on all previous versions of Android. The error message is quite precise, but I am unaware of any designed changes in the network security. Especially since it's quite popular to redirect resources to CDNs which are likely to be cross-domain.

Unfortunately, the test videos I have to test against would involve a significant amount of setup. I'm happy to provide a guide if required, but it should be possible to reproduce this issue with a local HTTP server redirecting to an online video.


## ojw28 (09 Aug 2014)

Thanks for the report. You're correct in thinking that this relates to Android L rather than ExoPlayer. I've routed the issue internally.


## ojw28 (21 Aug 2014)

Does it work if you set the "android-allow-cross-domain-redirect" header to "1" or "true"? You can pass the header into FrameworkSampleSource. For a quick and dirty test, you could try adding it directly in that class.


## IanDBird (01 Sept 2014)

Sorry @ojw28, I only just saw your message. I've tried as you suggested, with both "1" and "true", and neither work. I continue to get:

> MediaHTTPConnection﹕ readAt 0 / 32768 => java.net.NoRouteToHostException: Cross-domain redirects are disallowed


## ojw28 (01 Sept 2014)

Could you send me a link to the test video (or a link to a branch that has it as a sample)?


## IanDBird (01 Sept 2014)

Unfortunately not :( The content i'm using to test includes a private API key along with a URL which expires after a few minutes. It would be possible for you to setup our software and go through a lot of manual steps to reproduce although it might just be easier for you to setup a local webserver and a simple redirect from a local address to something online.


## IanDBird (01 Sept 2014)

Actually, it might just be as easy as using a URL shortener against one of the Sample videos. I'll test to confirm.


## IanDBird (01 Sept 2014)

Ok, so I took one of the sample MP4s (http://html5demos.com/assets/dizzy.mp4) and created a shorten version (http://goo.gl/MtUDEj). I was hoping that this would demonstrate the issue, but playback works fine. I guess that the main difference between this and the test files i'm using, is that my addresses redirect from HTTP to HTTPS then back to HTTP. Could this potentially be causing the issue?


## ojw28 (01 Sept 2014)

Yes. My understanding is that there's a bug in the L developer preview where cross-protocol redirects are accidentally blocked. I'm mainly looking for a very low cost way to triple check that it's been fixed for the case you're hitting.


## IanDBird (01 Sept 2014)

Thanks, it does look to be cross protocol specific. I don't know why I didn't think of this earlier, but the Google shortened URL also works with HTTPS. This means that the following redirect URL can be used to reproduce the issue:  https://goo.gl/MtUDEj

This will redirect from goo.gl (HTTPS) to html5demos.com (HTTP). 


## ojw28 (01 Sept 2014)

Confirmed to be the same issue. Thanks!


