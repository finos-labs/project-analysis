#Unable to play many videos

Owner: google

Repo: ExoPlayer

Labels: 

## nadiasvertex (25 Jul 2014)

After downloading the following videos to several devices with our software, I found the exoplayer unable to play many of them. In addition, it failed to start automatically for every single one of them.

The following videos were playable on most devices, but did not autostart. If I skipped the play head forward about 500 milliseconds they would play fine:

http://download.jw.org/files/content_assets/fa/502013341_E_cnt_1_r240P.mp4
http://download.jw.org/files/content_assets/d4/502013341_E_cnt_1_r360P.mp4
http://download.jw.org/files/content_assets/cc/502013341_E_cnt_1_r480P.mp4
http://download.jw.org/files/content_assets/19/502013341_E_cnt_1_r720P.mp4

The following videos were unable to play at all with exoplayer on any device tested, but the native android mediaplayer can play them all on most devices:

http://download.jw.org/files/content_assets/3f/502014311_E_cnt_1_r240P.mp4
http://download.jw.org/files/content_assets/f4/502014311_E_cnt_1_r360P.mp4
http://download.jw.org/files/content_assets/98/502014311_E_cnt_1_r480P.mp4
http://download.jw.org/files/content_assets/9a/502014311_E_cnt_1_r720P.mp4

http://download.jw.org/files/content_assets/e9/502014315_E_cnt_1_r240P.mp4
http://download.jw.org/files/content_assets/0d/502014315_E_cnt_1_r360P.mp4
http://download.jw.org/files/content_assets/09/502014315_E_cnt_1_r480P.mp4
http://download.jw.org/files/content_assets/2e/502014315_E_cnt_1_r720P.mp4

http://download.jw.org/files/content_assets/10/502014344_E_cnt_1_r240P.mp4
http://download.jw.org/files/content_assets/bc/502014344_E_cnt_1_r360P.mp4
http://download.jw.org/files/content_assets/79/502014344_E_cnt_1_r480P.mp4
http://download.jw.org/files/content_assets/7a/502014344_E_cnt_1_r720P.mp4

The exoplayer also did not appear to work for API 9 and 10 devices at all.

Note that, otherwise, my experience with exoplayer was very good. The documentation was sufficient, the demos were excellent. Once the player was playing I found the quality was good, and I appreciated the flexibility. I was excited about the features and control, but it's inability to play locally hosted content of certain types made it unusable for us. I hope to use it more in the future, and we have retained the code for "streaming-only" situations where we hope that it will perform better than the native MediaPlayer.


## IanDBird (28 Jul 2014)

This looks like a duplicate of the issue I raised a few days ago, https://github.com/google/ExoPlayer/issues/18. At the moment, i'm using my workaround to ensure that playback starts automatically.

Also, the minimum SDK version that ExoPlayer supports is 16, which is why your API 9 and 10 devices don't work. You can see this in the configuration of the demo project (https://github.com/google/ExoPlayer/blob/master/demo/build.gradle#L21). I think originally the project was misconfigured, but later 'fixed'. There are various assertions in the code that test for this, e.g. https://github.com/google/ExoPlayer/blob/master/library/src/main/java/com/google/android/exoplayer/FrameworkSampleSource.java#L57


## nadiasvertex (28 Jul 2014)

It's certainly a partial duplicate. However, I additionally have the problem that some of my MP4 files simply won't play at all. As I mentioned, I can get some to play by issuing a seek, however most of the videos I'm trying to display won't play at all on any device with the exoplayer. They do play on most devices with the native player.


## ojw28 (29 Jul 2014)

Thanks for the reports. I'll take a look.

As an aside: The big benefits of ExoPlayer only really apply to DASH or SmoothStreaming playbacks, where ExoPlayer replaces Android's MediaExtractor with its own extraction, buffering and networking components. If you're just trying to play a video file, and if you're not actually using the added flexibility that ExoPlayer affords, then you wont really get much benefit (if any) over just using Android's standard media player.


## IanDBird (29 Jul 2014)

Thanks @ojw28. 

Wouldn't an additional benefit of using ExoPlayer be the extensibility? I'm hoping that over time there will be enhanced support for more subtitle track renderers, so that we can handle more formats, etc.


## ojw28 (29 Jul 2014)

Yes, that's true. This does indeed appear to be a dupe (or roughly a dupe) of #18. You can actually make the videos referenced in this bug play fine just by changing DEFAULT_MIN_BUFFER_MULTIPLICATION_FACTOR to 16 in MediaCodecAudioTrackRenderer.

You shouldn't do this though; it's definitely not the correct solution(and makes player instantiation more likely to fail). The proper fix probably involves a memory copy for the audio samples, whilst they're still compressed. I'll have a think about how best to do this.


## ojw28 (29 Jul 2014)

A much better solution is just to hardcode MediaCodecVideoTrackRenderer.isReady() to return true, which works provided that the audio track is enabled. You can extend MediaCodecVideoTrackRenderer in your own application code and just override the method, if you need to get started playing this kind of media.

Something along these lines will be the "proper" solution to this problem, but some thought will be needed to correctly handle the case where the audio track is disabled (or missing), and around exactly how this should be wired together.


## ojw28 (01 Aug 2014)

You should find that these files play fine if you pull the latest from the dev branch.

There are still issues around FrameworkSampleSource, namely around threading and correctly transitioning in and out of the buffering state (these were already there; they're not new issues :)). But the videos referenced in this bug do at least play now.


## nadiasvertex (01 Aug 2014)

Thank you!

On Fri, Aug 1, 2014 at 11:05 AM, ojw28 notifications@github.com wrote:

> You should find that these files play fine if you pull the latest from the
> dev branch.
> 
> There are still issues around FrameworkSampleSource, namely around
> threading and correctly transitioning in and out of the buffering state
> (these were already there; they're not new issues :)). But the videos
> referenced in this bug do at least play now.
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/google/ExoPlayer/issues/21#issuecomment-50894910.


