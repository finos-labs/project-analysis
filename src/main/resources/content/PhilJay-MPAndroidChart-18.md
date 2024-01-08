#Bug when turning off the screen

Owner: PhilJay

Repo: MPAndroidChart

Labels: bug 

## Maxxan (29 Jul 2014)

In your example, open a line plot, zoom in and turn off the screen. When tuning the screen on again, it will recalculate the zoom offset, jumping to a new location. For example if you show Y values 30-70, next time you turn the screen on it will display 60-100, turn off and on the screen it will display 90-130 etc. The bug is only when zoomed in.


## PhilJay (29 Jul 2014)

Thank you for your input! I just tested it and you are right.
Finding that bug is quite impressive!
It definitely requires some courtesy in order to be detected.

I will address this issue as soon as possible and report back when it is fixed!

Regads,
Phil


## PhilJay (01 Aug 2014)

In the latest commit I just pushed, this issue has been detected and fixed.

Regards,
Phil


