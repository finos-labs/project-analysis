#DASH + AC3 audio support appears unsupported

Owner: google

Repo: ExoPlayer

Labels: 

## IanDBird (30 Jul 2014)

I was having a look at how ExoPlayer would handle DASH with AC3 audio. The main reason for this is that at Google I/O this year (during the main Android TV talk) it was stated that Android L would include support for AC3 and AC3 passthrough. This is of course a major feature for the Android TV, allowing multichannel audio formats.

I attempted to play a DASH which included a AC3 audio track, but found that the parsing of the MP4 failed. This was caused by the lack of support when reading the STSD ATOM, which appears to only handle AVC1, AVC3 and MP4A. (https://github.com/google/ExoPlayer/blob/master/library/src/main/java/com/google/android/exoplayer/parser/mp4/FragmentedMp4Extractor.java#L589)

@ojw28 - Are there any plans to support AC3 in time for the release of Android L? Also, is the lack of support purely a parsing limitation (at the moment) or are there likely to be various things that need implementing further down the pipeline?


## ojw28 (31 Jul 2014)

I'm not sure, but my guess would be that it's purely a parsing limitation (which should make it trivial to fix). Do you have any test content at all? If not I'll have a dig around for some at some point.


## IanDBird (01 Aug 2014)

Thanks for responding. I've tried to put together enough to show you the parsing issue. I've included the MPD as well as a number of audio/video segments. You should be able to grab them from https://dl.dropboxusercontent.com/u/17365987/AC3.zip

I'm pretty sure my MPD is not technically correct, because it's stating the audio codec is "mp4a.40.2". However, this appears ignored and the issue occurs while reading the audio/initial.mp4 where the `childAtomType` of the STSD is "ac-3" (1633889587). Please let me know if you need anything else.


## IanDBird (08 Sept 2014)

@ojw28 - Have you had any more time to look into this?


## ojw28 (01 Oct 2014)

I don't think we have anything more to say about AC3 at this point in time.


## IanDBird (20 Oct 2014)

@ojw28 Did you mean to close this issue?

I've been looking into this, and have made a few local changes... I've managed to have ExoPlayer find the AC3 MediaCodec. The problem I have is that when attempting to configure it, a native error occurs (Error: 0x80001001). The `MediaFormat` i'm specifying 'looks' ok, but I haven't been able to build the `initializationData` because I have no idea what it's meant to be :) Is there any documentation on what the MediaCodec is expecting?


## IanDBird (20 Oct 2014)

Looking at the code a bit more, I see that `initializationData` isn't even used...

``` java
  protected void configureCodec(MediaCodec codec, android.media.MediaFormat x, MediaCrypto crypto) {
    codec.configure(x, null, crypto, 0);
  }
```

Will eventually build the key/value map from the `MediaFormat` before calling: `native_configure(keys, values, surface, crypto, flags);`

This means that the only things which affect the configuration of the codec are:
- Channel Count (6)
- Max input size (7938)
- MIME (audio/ac3)
- Sample Rate (44100)
- MediaCrypto (null, since no DRM is applied)

All of these inputs appear valid, so not sure why it fails to configure.


## IanDBird (20 Oct 2014)

Looks like two things are wrong here...
- Max input size is not correct
- Initialization data would be passed to the MediaCodec, if I can specify a suitable `initializationData`


