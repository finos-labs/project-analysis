#Volume keys don't seem to work on CM12.1 (bacon)

Owner: termux

Repo: termux-app

Labels: 

## inducer (27 Oct 2015)

The volume keys don't seem to work on Cyanogenmod 12.1 on my OnePlus One. When I hit VolDown+d, the device vibrates as long as hold VolDown, and the "d" shows up as if nothing had happened.


## fornwall (31 Oct 2015)

Hi, thanks for reporting!

I don't have a Cyanogenmod device available at the moment. In order to help diagnose the issue, can you check if the volume buttons work as shortcuts in TEA (check that settings says that Control key is Vol Down Key):

https://play.google.com/store/apps/details?id=jackpal.androidterm


## inducer (31 Oct 2015)

You know what--this is totally my fault. I have (had) the volume keys mapped to Cursor right/left. (CM will let you do that.) This then obviously messes up your handling of these keys.


## fornwall (31 Oct 2015)

Ok, thanks for the information, and great that it works for you now!


## r0bis (11 Oct 2016)

You are a star. Has the same issue in CM13 - sorted now


