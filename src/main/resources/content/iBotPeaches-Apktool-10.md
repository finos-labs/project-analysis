#Extra &amp; being added to XML files

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## huawuxin (31 Jul 2012)

Please help to fix a problem introduced since the original brut version of apktool: http://code.google.com/p/android-apktool/issues/detail?id=101

In addition to the "& a m p;" being decoded as "& a m p ; a m p ;"  problem, " & l t;" also being decoded as "& a m p ; l t". (sorry for the addtional white spaces)

Please take care of it. Thank you.


## iBotPeaches (31 Jul 2012)

Seems it is double encoding values. I'll have to look into this, I  don't know exactly how to prevent a double encoding, because simply replacing & with &amp; results in &amp; become &amp;amp;


## iBotPeaches (19 Sept 2012)

Just converting &*amp; back to & on read.

Then & back to &*amp; on build.


## huawuxin (19 Sept 2012)

Will the above method do "& l t ;" wrong?
On read: & l t ; -> & l t ; (no change)
On build: & l t ; -> & a m p ; l t ; (changed)


## iBotPeaches (16 Mar 2015)

fixed at some point in v2.0 development.


