#Investigate adding HLS support.

Owner: google

Repo: ExoPlayer

Labels: enhancement 

## ojw28 (23 Jun 2014)



## andudo (02 Oct 2014)

We're actively working on adding HLS support to ExoPlayer. I created dev-hls branch for our "work in progress" changes. It currently has a basic HLS support for VOD and Live playlists, and EXT-X-DISCONTINUITY tag working. Note that it doesn't have a lot of important features like adaptive bitrate switching or encryption support yet. We will be adding them to this branch as soon as they're ready.


## andudo (03 Oct 2014)

i've also opened separate issues to let you know whats on our current plans and also track them separately:
https://github.com/google/ExoPlayer/issues/67 ID3 Timed Metadata
https://github.com/google/ExoPlayer/issues/68 Closed Captions
https://github.com/google/ExoPlayer/issues/69 Encryption
https://github.com/google/ExoPlayer/issues/70 Adaptive bitrate

code will be pushed into https://github.com/google/ExoPlayer/tree/dev-hls


## samek (03 Oct 2014)

@andudo so dev-hls will be official and what @martinbonnin is doing will not get merged ? 


## andudo (03 Oct 2014)

@samek yes, we will be updating dev-hls and merge it into master once it's production ready.


## samek (03 Oct 2014)

@andudo What i meant was will you use what @martinbonnin has done already or not. 
I just started to use his fork and I would like to know where the official version is heading. 
Thanks. 


## andudo (03 Oct 2014)

@samek there are no plans on using it at the moment.


## nachtien (03 Oct 2014)

@andudo, @martinbonnin's branch has been a life saver for the project I was working on.  I hope that at least some of it will be useful for the HLS support that you plan on adding!


## AndroidNoob (06 Oct 2014)

@andudo Can you please add the support for mp3 audio in live hls streams ? 


## andudo (06 Oct 2014)

@AndroidNoob I opened https://github.com/google/ExoPlayer/issues/71 issue for it. And answered there - MP3 support for HLS is not on our current plans. Sorry.


## perchrh (06 Oct 2014)

Is multi-audio support for HLS planned?


## ojw28 (07 Oct 2014)

Apologies for the churn caused to those people who've been using the @martinbonnin branch. To clarify: That branch explored a way of adding HLS support, and the experience gained from that was really helpful. Ultimately we decided to structure HLS support differently, which is why we opted to follow a different approach. Please bear with us as we fill in some of the gaps in terms of functionality.


## andudo (07 Oct 2014)

@perchrh it's not a high priority for us at the moment, but it's likely that we'll get to it at some point in the future https://github.com/google/ExoPlayer/issues/73


## crossle (09 Oct 2014)

@andudo OOM error

```
       dalvikvm-heap  I  Clamp target GC heap from 48.120MB to 48.000MB
                        I  Forcing collection of SoftReferences for 65552-byte allocation
                        I  Clamp target GC heap from 48.111MB to 48.000MB
                        E  Out of memory on a 65552-byte allocation.
              dalvikvm  I  "ExoPlayerImplInternal:Handler" prio=5 tid=25 RUNNABLE
                        I    | group="main" sCount=0 dsCount=0 obj=0x42aa82a0 self=0x52c68a00
                        I    | sysTid=17523 nice=0 sched=0/0 cgrp=apps handle=1415530488
                        I    | state=R schedstat=( 16050933943 14518585089 137898 ) utm=1335 stm=268 core=0
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor$Sample.<init>(TsExtractor.java:~705)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor.getSample(TsExtractor.java:254)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor.access$3(TsExtractor.java:252)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor$PesPayloadReader.addSample(TsExtractor.java:504)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor$AdtsReader.readOneAacFrame(TsExtractor.java:682)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor$AdtsReader.read(TsExtractor.java:621)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor$PesReader.readPES(TsExtractor.java:456)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor$PesReader.read(TsExtractor.java:395)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor.readTSPacket(TsExtractor.java:233)
                        I    at com.google.android.exoplayer.parser.ts.TsExtractor.read(TsExtractor.java:171)
                        I    at com.google.android.exoplayer.hls.TsChunk.read(TsChunk.java:107)
                        I    at com.google.android.exoplayer.hls.HlsSampleSource.readData(HlsSampleSource.java:244)
                        I    at com.google.android.exoplayer.MediaCodecTrackRenderer.feedInputBuffer(MediaCodecTrackRenderer.java:504)
                        I    at com.google.android.exoplayer.MediaCodecTrackRenderer.doSomeWork(MediaCodecTrackRenderer.java:407)
                        I    at com.google.android.exoplayer.MediaCodecAudioTrackRenderer.doSomeWork(MediaCodecAudioTrackRenderer.java:271)
                        I    at com.google.android.exoplayer.ExoPlayerImplInternal.doSomeWork(ExoPlayerImplInternal.java:402)
                        I    at com.google.android.exoplayer.ExoPlayerImplInternal.handleMessage(ExoPlayerImplInternal.java:208)
                        I    at android.os.Handler.dispatchMessage(Handler.java:98)
                        I    at android.os.Looper.loop(Looper.java:136)
                        I    at android.os.HandlerThread.run(HandlerThread.java:61)
                        I    at com.google.android.exoplayer.ExoPlayerImplInternal$1.run(ExoPlayerImplInternal.java:109)
```


## ojw28 (09 Oct 2014)

@crossle Please state what stream you used to reproduce this, and whether it happens consistently or was a 1 off. Thanks.


## crossle (10 Oct 2014)

@ojw28  I reproduce this many times, when i playback `LIVE` url. 
BTW, why source.getBufferedPositionUs() < getCurrentPositionUs() when live streaming .


## ojw28 (10 Oct 2014)

You need to provide more information for us to be able to investigate. Which url specifically, so that we can try it ourselves? After how long does the problem occur, on which device? If possible, please also provide a full bugreport captured shortly after the problem is encountered (adb bugreport).

Thanks!


## crossle (10 Oct 2014)

@ojw28  You can try http://itv08.digizuite.dk/tv2b/ngrp:ch1_all/chunklist-b1089536.m3u8?wowzasessionid=1128324428 live streaming, I used Nexus 5, Android 4.4.4, OOM problem not occur, OOM only occur on Samsung i9100. but it can't playback when player buffering a time, it  buffering...all the time。


## crossle (13 Oct 2014)

@ojw28  I found the buffering error,  when buffering https://github.com/google/ExoPlayer/blob/79c2f535c6f811361c9ffa674de4a5dba2120b85/library/src/main/java/com/google/android/exoplayer/ExoPlayerImplInternal.java#L336 always return false, because MediaCodecTrackRenderer.java `getBufferedPositionUs`  always return max value, so buffering all the time. see the print log, you can reproduce the problem use http://www.twitch.tv/ home HLS live stream.

```
ExoPlayerImplInternal.java.rendererReadyOrEnded  minBufferDurationUs=5000000,rendererBufferedPositionUs=63619655, rendererDurationUs=-1 , positionUs=63825000
MediaCodecTrackRenderer.java.getBufferedPositionUs  sourceBufferedPosition =18522622, getCurrentPositioin=63619655

```


## andudo (19 Nov 2014)

We have implemented the main HLS stack with all the features we have originally targeted. I'm closing this issue. If you'd like to discuss a bug, missing feature, or any idea/suggestion, please open a separate specific issue.


## ojw28 (29 May 2015)

@AndroidNoob Do you have any sample streams for HLS containing MP3?


