#sometimes exoplaye.getCurrentPosition is bigger than exoplayer.getDuration 

Owner: google

Repo: ExoPlayer

Labels: 

## westlinkin (07 Jul 2014)

on my device, after exoplayer started playing video, ExoPlayer.onPlayerStateChanged is called after about 600ms, then state becomes buffering and after that, getCurrentPosition is `97391604`, and it's larger then exoplayer.getDuration. 

I wonder if the onPlayerStateChanged is called uncorrectly.


## ojw28 (09 Jul 2014)

- Do you have reliable reproduction steps? I've never seen this.
- What value does getDuration return when this happens, and on what device? Please pull a full bug report if possible.


## westlinkin (10 Jul 2014)

`getDuration` returns a valid and correct integer. 

I only reproduce this bug on Kindle Fire HDX(13.3.2.4_user_324002120), it is fine on other devices.


## ojw28 (10 Jul 2014)

As far as I know, Fire OS is incompatible with Google's Android compatibility standards (and has not passed CTS). Hence we do not officially support it. It may well be a Fire OS problem.

Please re-open if you can reproduce on a compatible device.


