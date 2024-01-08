#[FR] The alternative offline-install apk

Owner: termux

Repo: termux-app

Labels: 

## sm4rk0 (26 Oct 2015)

First, thank you for this application. It's really promising.
I was surprised by small apk size (130kB), but the first run ruined that surprise. It took 7 minutes to download the bootstrap package on my 64kb/s connection. Having the base pacakge installed with the apk would be useful for people with no or limited internet access.
Cheers!


## fornwall (26 Oct 2015)

Thanks! I think a separate app ("Termux Offline Installer"), which would bundle and install the base package itself would work, right?


## sm4rk0 (26 Oct 2015)

Yes.
Then you could say that it works out of box (:


## fornwall (14 Jan 2016)

One thing that has been improved since the creation of this issue is that the bootstrap zip is downloaded from a content distribution network, which should give increased performance across the globe. It won't help `no or limited internet access` much is the limitation is locally.

I will unfortunately have to close this issue for now - not enough time to fix something like this. This doesn't mean that this will never happen, or that a contribution would not be welcome, just that this is not tracked as a current issue for now.


## xloem (23 Jan 2017)

I made a quick implementation of this in #242 .

## xloem (23 Jan 2017)

The biggest reason to implement this is that Tor users cannot access termux.net at the moment.

My personal use-case was that I have an airgapped phone with no internet access and can only install applications via adb etc.  In this case also, there is no way to install this application without this fix.

## fornwall (24 Jan 2017)

> Tor users cannot access termux.net at the moment

Do you know why? Is it because of cloudflare blocking it or inserting a captcha?

I've just reconfigured cloudflare settings [to whitelist Tor exit nodes](https://support.cloudflare.com/hc/en-us/articles/203306930-Does-CloudFlare-block-Tor-), so could you try again and see if that works better?

## xloem (24 Jan 2017)

It was probably a captcha, but it is indeed working great from Tor now !  Thank you.

Still, would you accept the pull request if I improved it such that it checked for a local asset, and then performed the normal download if the local asset were not present?

## fornwall (30 Jan 2017)

@xloem Great that it works with Tor now!

> Still, would you accept the pull request if I improved it such that it checked for a local asset, and then performed the normal download if the local asset were not present?

A local asset under /sdcard? 

## pylerSM (27 Mar 2017)

Yes, I think there should be option to pick file with bootstrap package to do offline installation.

## kzahel (11 Apr 2017)

+1 Would love to see offline installation supported more or less out of the box.

## BeeeWall (05 May 2017)

Maybe it could use an OBB to get the system, so that Termux could either download it or people could manually place it? And then maybe set some sort of flag so the OBB could be deleted after extraction without redownloading? Also, I think that means Play Store might be able to download it during app installation.

## xloem (07 May 2017)

@fornwall
> A local asset under /sdcard?

No, I meant an asset bundled with the app as in https://github.com/termux/termux-app/pull/242/files: it would be easy to check for such a bundle at runtime and refrain from downloading if found, which would in turn make it easy to produce two alternative versions of the app if desired.  I prefer bundling the zip because it means the starting layout would be cryptographically verified to be correct.

Would you be more conducive to the more flexible approach of checking /sdcard ?

