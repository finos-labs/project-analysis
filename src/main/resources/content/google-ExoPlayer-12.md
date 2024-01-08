#Smooth Streaming LIVE support

Owner: google

Repo: ExoPlayer

Labels: enhancement 

## taaeng (14 Jul 2014)

I can see ExoPlayer supports Smooth Streaming. Does this include Smooth Streaming LIVE as well?

A quick test gives this exception:
07-14 11:21:15.956: E/EventLogger(23424): java.lang.ArrayIndexOutOfBoundsException: length=0; index=0
07-14 11:21:15.956: E/EventLogger(23424):   at com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser$StreamElementParser.parseStreamFragmentStartTag(SmoothStreamingManifestParser.java:455)


## ojw28 (14 Jul 2014)

Not currently. We'll use this issue to track adding support, although it's not a priority for us (v.s. widening DASH support, DASH Live and HLS) at the moment.


## taaeng (14 Jul 2014)

Thank you for quick reply. 


## ojw28 (14 Jul 2014)

Reopening to track eventual support.


## PatricioMP (20 Aug 2014)

- 1 for LIVE support


## ojw28 (25 Sept 2014)

This is now working, with the exception that sparse TTML subtitles may cause buffering issues (fix to follow).


## PatricioMP (26 Sept 2014)

Yes, I have tested it with live smoothStreaming, and its working good!

I din't managed to put to work the stream with playReadyDRM.

This error happens:
android.media.UnsupportedSchemeException: Failed to instantiate drm object.
 at android.media.MediaDrm.native_setup(Native Method)
            at android.media.MediaDrm.<init>(MediaDrm.java:163)

Do you know whats the problem?

Thanks.


## ojw28 (26 Sept 2014)

Most Android devices don't support PlayReady, which is why you get an UnsupportedSchemeException. Widevine is the only mandatory DRM that Android devices need to support.


## PatricioMP (26 Sept 2014)

I was suspecting that was the issue..

Thanks


## danieljanes (30 Sept 2014)

+1 for LIVE support


## ojw28 (01 Oct 2014)

Fixed.


## danieljanes (07 Nov 2014)

Since this is fixed, could you merge it into master to show that it's production ready?


## ojw28 (09 Nov 2014)

Yes. We've been waiting until we can merge dev-l before cutting a new release, but with the release of the L SDK this should now be possible.


## ojw28 (10 Dec 2014)

FYI - This is now merged.


## danieljanes (10 Dec 2014)

Thanks for the update.


