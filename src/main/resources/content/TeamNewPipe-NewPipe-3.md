#Audio only option

Owner: TeamNewPipe

Repo: NewPipe

Labels: 

## lightonflux (10 Sept 2015)

With DASH you have separate audio and videos streams. So if a user only wants to listen to something the app could save bandwidth by providing only the audio stream. 


## theScrabi (10 Sept 2015)

Yes i'd like that to, and I know that the youtube website offers a hidden url of an audio only stream. So far i couldn't find that url.


## ZatsuneNoMokou (12 Sept 2015)

Youtube Center (source on github) seems to be able to put a download link for mp3, it help? I know Youtube Center isn't related to mobile or android app, but just trying.
And I'm curious, as you don't use Youtube API, you're not concerned to these terms of serice? ( https://developers.google.com/youtube/terms?csw=1 )


## epitron (18 Sept 2015)

@theScrabi The audio is just another youtube stream format that you can request.

youtube-dl has a good list of them: https://github.com/rg3/youtube-dl/blob/master/youtube_dl/extractor/youtube.py#L265


## theScrabi (18 Sept 2015)

Ok good. Thanks. But that was the easy part. I have to rework the whole player to enable playing music. That will take a wile.


## epitron (18 Sept 2015)

Hmmm... How about just using an external player?

My use case for audio is downloading the audio to a file so I can listen to lectures on the subway. If downloading works, and open works, that would cover 90% of the use cases I think.


## theScrabi (18 Sept 2015)

Right. Didn't think about that.


## lightonflux (18 Sept 2015)

If audio only streaming would be ext. player only that would be just as good. Most people will use this feature for long talks other other kind of material with less appealing visuals. And those are mostly 20mins long and more. So it wouldn't disrupt the workflow too much. Advanced players like VLC also have some nice features like playback speed and sleep timer. I don't think the work needed to implement those features are justified at the moment. Other features are more important like visual papercuts in the UI, inline playing etc.

Am 18. September 2015 10:49:05 MESZ, schrieb Christian Schabesberger notifications@github.com:

> Right. Didn't think about that.
> 
> ---
> 
> Reply to this email directly or view it on GitHub:
> https://github.com/theScrabi/NewPipe/issues/3#issuecomment-141384273

## 

Diese Nachricht wurde von meinem Android-Mobiltelefon mit K-9 Mail gesendet.


## theScrabi (21 Sept 2015)

Done. But it wasn't quiet that easy. Even if there are itags for m4a, no video offers audio streams along with the video streams. So we need to parse something called dash manifest. How ever it's now possible to listen to youtube videos via a audio streaming player like VLC. Sadly the audio quality with VLC is petty bad, witch meas I might have to implement a NP build in audio player. However for now I recommend downloading the Audiostream. 


## dalb8 (04 Oct 2015)

I get 'no streaming reader detected' and then asks me to download org.videolan.vlc, which I already have. Nexus S with CM11


## epitron (05 Oct 2015)

Heh. I'm getting that now too. Except, after I downloaded VLC, it launches MXPlayer.

`¯\_(ツ)_/¯`


## theScrabi (05 Oct 2015)

Ow yes i have the same problem. I guess something went wrong with the last commit, so i didn't notice. @dalb8 could you please open an issue about that :)


