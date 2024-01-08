#Warnings with directories

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## iBotPeaches (01 May 2012)

C:>apktool if com.htc.resources.apk onex_1.29
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-hdpi-ERR0
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-hdpi-ERR1
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-hdpi-ERR2
W: Config flags size > 36. Exceeding bytes: 0x6000000.
W: Invalid config flags detected: dimen-hdpi-ERR3
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-xhdpi-ERR4
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-xhdpi-ERR5
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-xhdpi-ERR6
W: Config flags size > 36. Exceeding bytes: 0x6000000.
W: Invalid config flags detected: dimen-xhdpi-ERR7
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-ja-hdpi-ERR8
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-ja-hdpi-ERR9
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-ja-hdpi-ERR10
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-ja-xhdpi-ERR11
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-ja-xhdpi-ERR12
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-ja-xhdpi-ERR13
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-ko-hdpi-ERR14
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-ko-hdpi-ERR15
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-ko-hdpi-ERR16
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-ko-xhdpi-ERR17
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-ko-xhdpi-ERR18
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-ko-xhdpi-ERR19
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-zh-rCN-hdpi-ERR20
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-zh-rCN-hdpi-ERR21
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-zh-rCN-hdpi-ERR22
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-zh-rCN-xhdpi-ERR23
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-zh-rCN-xhdpi-ERR24
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-zh-rCN-xhdpi-ERR25
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-zh-rTW-hdpi-ERR26
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-zh-rTW-hdpi-ERR27
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-zh-rTW-hdpi-ERR28
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: dimen-zh-rTW-xhdpi-ERR29
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: dimen-zh-rTW-xhdpi-ERR30
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: dimen-zh-rTW-xhdpi-ERR31
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: integer-hdpi-ERR32
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: integer-hdpi-ERR33
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: integer-hdpi-ERR34
W: Config flags size > 36. Exceeding bytes: 0x6000000.
W: Invalid config flags detected: integer-hdpi-ERR35
W: Config flags size > 36. Exceeding bytes: 0x2000000.
W: Invalid config flags detected: integer-xhdpi-ERR36
W: Config flags size > 36. Exceeding bytes: 0x4000000.
W: Invalid config flags detected: integer-xhdpi-ERR37
W: Config flags size > 36. Exceeding bytes: 0x5000000.
W: Invalid config flags detected: integer-xhdpi-ERR38
W: Config flags size > 36. Exceeding bytes: 0x6000000.
W: Invalid config flags detected: integer-xhdpi-ERR39
I: Framework installed to: C:\Users\Administrator\apktool\framework\2-onex_1.29.apk


## mike1986 (04 May 2012)

Here is the file - http://xda3.androidrevolution.nl/db_mirror/Others/com.htc.resources.apk


## iBotPeaches (16 May 2012)

I'd also like to add to this the fact that apktool doesn't read some types of folders correctly.

drawable-sw600dp-hdpi

(the sw is killing it), need to adjust regex for folders


## iBotPeaches (17 May 2012)

It wasn't the regex. I'm an idiot.

imsi=0/0 lang=ja reg=-- orient=0 touch=0 dens=240 kbd=0 nav=0 input=0 ssz=0x0 sw0dp w0dp h0dp sz=0 long=0 ui=0 night=0 vers=4.0

Output of com.htc.resources.apk

The flags are passed, but never read by apktool, thus warnings. Dedicated today to this fix.


## iBotPeaches (28 Jun 2012)

http://developer.android.com/guide/topics/resources/providing-resources.html#AlternativeResources

Updated DOCs again. Should help now that Android has unified their documentation to one clean way.


## iBotPeaches (27 Jul 2012)

Directory warnings are caused by AAPT. I've done all the needed changes for resource identifiers up to Jellybean. This issue lies in companies building APKs without Android stock building software (AAPT).


