#Change playback rate (video + audio only use cases)

Owner: google

Repo: ExoPlayer

Labels: enhancement 

## viacheslavokolitiy (07 Aug 2014)

Hello guys. Is it possible to change playback rate of video track e.g play faster or play slow ?


## ojw28 (19 Apr 2017)

Apologies we've not managed to provide a release version containing this functionality yet. The functionality will be in the 2.4 release, but we're having to wait for a few other changes to land before we can publish it. We're now targeting toward the middle of next week. Thanks!

## KirillMakarov (19 Apr 2017)

@ojw28 can you notify us in this issue, when the feature will be released?

## PaulWoitaschek (19 Apr 2017)

@KirillMakarov GitHub has an atom feed you can add to your RSS reader:
https://github.com/google/ExoPlayer/releases.atom

## ojw28 (19 Apr 2017)

You can also watch https://bintray.com/google/exoplayer/exoplayer. There are quite a few ways to be notified of new releases without us having to update issues manually ;).

## workspace (21 Apr 2017)

@ojw28 
The new playback speed change method seems to be heavily influenced by CPU performance. I just put this code in demo when player is initialized.
```
player.setPlaybackParameters(new PlaybackParameters(2.0f, 1.0f));
```

For PlaybackParams added in API 23, the video played back successfully on Nexus 5. However, with the new PlaybackParameter, frame drops occur on devices with low CPU performance such as Nexus 5 and Galaxy S4.

[Nexus 5 with API 23](https://www.youtube.com/watch?v=DqvcNHqrSek)
[Galaxy S4 with API 21](https://www.youtube.com/watch?v=zY3UY-ea8Pw)

However, the Nexus 5X, Galaxy Note 5 and Galaxy S8 Plus (Android N devices) played well without problems. I played demo in the API 23 version of the emulator to see if it was a difference in the Android API.
 
[Nexus 5x emulator with API 23](https://www.youtube.com/watch?v=mrNM0-UaCdU)

So I'm worried that the new playback speed change method will work well on various devices.


Error Log in Android Studio
```
04-21 16:56:50.368 20130-20130/com.google.android.exoplayer2.demo E/EventLogger: internalError [21.31, audioTrackUnderrun [56448, 320, 27]]
04-21 16:56:50.443 20130-20130/com.google.android.exoplayer2.demo E/EventLogger: internalError [21.38, audioTrackUnderrun [56448, 320, 1]]
04-21 16:56:50.618 20130-20130/com.google.android.exoplayer2.demo E/EventLogger: internalError [21.55, audioTrackUnderrun [56448, 320, 22]]
```

## andrewlewis (21 Apr 2017)

@KiminRyu Did you see these problems when running a release APK or a debuggable APK?

The new implementation uses the Java version of Sonic, so its performance is affected by what optimizations the Java runtime applies. There is a certain optimization that is turned off on debuggable builds, which causes an inner loop in Sonic to be very slow. If you build a release APK this optimization should apply (on all API versions) and I found that performance was fine on various devices I tested, including a Samsung Galaxy S4 running Android 4.2.2 (API 17).

On API 24 Android builds onwards, performance should be fine on both debuggable and release builds.

## workspace (21 Apr 2017)

I got the latest version of the dev-v2 branch and built it. (so it is a debuggable apk.) So...How can I set the same environment with a release apk?

## andrewlewis (21 Apr 2017)

I used the instructions for Android Studio: [Sign your release build](https://developer.android.com/studio/publish/app-signing.html#release-mode).

## workspace (21 Apr 2017)

I built it with the release version, and the playback speed change worked just as you said. As shown in the video attached below, playback of Secure_xx (SD, HD, UHD) of Widevine DASH: MP4, H264 sample was not smooth. On the other hand, clear_xx samples of the same widevine drm sample worked well, and Youtube DASH also worked.

[widevine secure hd -> clear hd -> youtube dash](https://www.youtube.com/watch?v=6Qxjt59VJwM)

## PaulWoitaschek (27 Apr 2017)

Thanks for the release. It works really great!

## workspace (28 Apr 2017)

@andrewlewis 
As I mentioned above, when playing from 2.4.0 version with widevine secure path, the screen stops immediately after 10 seconds. NEXUS 5 (23), Galaxy Note 5 (24), Galaxy S8 Plus(24) and other API 23 and above work normally. On the other hand, older devices such as the Galaxy S4 (21), Galaxy Note 2 (19) and Galaxy Note (16) have a problem in changing the playback speed faster. I have built and tested a new 2.4.0 release apk.

## andrewlewis (28 Apr 2017)

@PaulWoitaschek Glad to hear it's working!

@KiminRyu Please could you file a new issue to track that, including all the details requested in the new issue template? Thanks.

I'm closing this issue now as the core functionality is implemented, but as @ojw28 noted [above](https://github.com/google/ExoPlayer/issues/26#issuecomment-290844837) further changes will be needed to propagate the playback speed to some other player components (like the adaptive track selection logic and LoadControl). In case it's useful: there's a [blog post](https://medium.com/google-exoplayer/variable-speed-playback-with-exoplayer-e6e6a71e0343) summarizing how to use this feature.

## gemiren (29 May 2017)

I am seeing high CPU usage and battery drain since the 2.4.0 version when using variable playback speed. On the Nexus 5x it uses about 8% to play with Android's built-in media player while it uses about 16% CPU when using ExoPlayer with the same playback speed. Looking at the code I can see ExoPlayer now always uses Sonic to adjust the playback speed, even when the Android system supports the variable playback natively on API>=23. That doesn't seem to be an efficient way to handle the variable playback speed to me. What not only use Sonic for API<23 and use the system's built-in support for API>=23? The C version of Sonic on API>=23 is much faster than the Java version of Sonic included in ExoPlayer.

## PaulWoitaschek (29 May 2017)

I'm seeing multiple user complaints about high battery drain too.

> The C version of Sonic on API>=23 is much faster than the Java version of Sonic included in ExoPlayer.
Did you measure that? According to [the author](https://github.com/waywardgeek/sonic) the java and c version are quite equal.

## gemiren (29 May 2017)

@PaulWoitaschek, I didn't measure that. Maybe "faster" isn't the proper word to describe this difference in the C version and the Java version. There are could be many other factors involved here. Anyway, the CPU usage is way too high when playing with variable playback speed for API>=23 compared with using the C version Sonic library in the Android system. I just don't understand why do we need to use the Java version of Sonic for API>=23 since the Android system already has that implemented. We don't need to reinvent the wheel for API>=23 to play with variable playback speed. The Android system has that supported and it works nicely and perfectly in the versions before 2.4.0.

Sadly this is a deal break for me. I had to revert back to old ExoPlayer version to fix the battery drain.

## ojw28 (29 May 2017)

Do you have solid data backing up the claim of increased battery drain? Looking at CPU usage isn't sufficient. Neither is linking ad-hoc user reports to something you know has changed, when other things have also changed. It really needs to be actual battery drain data comparing the two whilst minimizing any other changes.

Note that CPU usage for the playback speed adjustment will likely be attributed to a different process in each case (your process when using the Java version, or the mediaserver process when using the C version). So if you're just looking at the CPU usage of your own process then that's not a correct comparison. As noted above the author of Sonic suggests the performance of the two are give-or-take equal. Note also that you need to be using a release build of your apk, as discussed above. Debug builds may yield vastly inferior performance with variable playback speed enabled.

As for why we're using the Java version everywhere: One reason is simplicity; having a single code path is just a lot simpler. Another reason is that the Android system support *isn't* perfect. We actually found a bug in Sonic (which is fixed in ExoPlayer's bundled version, but will only be fixed in Android from O onward). If we see solid evidence that power drain is significantly worse then we can revisit the decision.

## gemiren (29 May 2017)

@ojw28, unfortunately I don't have the solid data to pin down what's exactly causing the huge increased battery drain. Since I updated my app to use ExoPlayer 2.4.0 I have received numerous user feedback regarding the increased battery drain. So I did some comparison between ExoPlayer and Android Media Player on a Nexus 5x with Android 7.1.2. I did see the battery usage went down significantly if I switched to use the Android Media Player.

I am using the release build `compile 'com.google.android.exoplayer:exoplayer:r2.4.1'` so it is not an issue of the debug version.

You are right that CPU usage isn't the only factor causing the battery drain. It's just convenient to monitor the CPU usage in the Android Studio to get the rough idea of how the battery performances.

It is true that the CPU usage should include the mediaserver process when using the Android Media player. However, that's not how the battery usage stats works. Now my app is listed at the top of the battery drain list. I can not tell users that the battery usage is probably the same, just because now it includes the battery usage from mediaserver which isn't included before. From a user point of view, when the battery usage goes up almost doubled, any explanation is pale.

## ojw28 (31 May 2017)

I don't think there's evidence in this thread (yet) of "huge increased battery drain" or that the Java version is significantly less efficient. It seems to me there are two possibilities at this point:

1. Total battery drain is actually about the same as before. Due to the way battery accounting works, battery drain associated with the variable speed adjustment is now being attributed to your app where-as previously it was attributed to mediaserver. This is causing your users to complain.
1. Total battery drain is actually higher (i.e. the user's battery will actually drain faster than before).

If it's the first of these then I don't think we're going to fix it. After all, your app *is* causing the battery drain that's being associated to it in this scenario. The fact the drain was not accounted to your app previously would effectively be an accounting trick, and something we should probably fix in the platform to ensure correct attribution in future Android releases. If it's the second of the two possibilities then this is definitely something we should look at. If you could help us to determine which of these cases is actually true, for example by running some experiments, that would be very helpful.

Note that it's how you build your apk that affects whether it's a release build or not, *not* how you depend on ExoPlayer in your `build.gradle`. See some of the comments from @andrewlewis further up this thread.

## Orbyt (09 Jun 2017)

This is quite a long thread. Can I get a summation of whether or not this functionality has been implemented, and what API levels are supported?

## PaulWoitaschek (10 Jun 2017)

It's supported on all api levels through `ExoPlayer.setPlaybackParameters`.

