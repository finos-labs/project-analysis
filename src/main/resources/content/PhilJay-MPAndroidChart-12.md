#Support for x values (instead of x indices)

Owner: PhilJay

Repo: MPAndroidChart

Labels: enhancement 

## onyame (16 Jul 2014)

Thanks for your great library!

We just found one major drawback. We like to draw a time series chart where the values are not equidistant. The x values where points are drawn must be at any position not just distributed equally by index. For example, the x axis represents the date and time (as in this blood pressure chart http://www.andreas-schreiber.net/activities/quantified-self/blood-pressure/).

This applies to line chart and scatter chart.


## forum6691 (10 Jun 2016)

Information very interesting :)
Cyril

Le 10/06/2016 à 16:05, Philipp Jahoda a écrit :

> Within the next few weeks v3.0.0 of this library with major changes 
> will be released which is finally going to close this issue, stay 
> tuned :-)
> 
> —
> You are receiving this because you commented.
> Reply to this email directly, view it on GitHub 
> https://github.com/PhilJay/MPAndroidChart/issues/12#issuecomment-225190713, 
> or mute the thread 
> https://github.com/notifications/unsubscribe/ALlmhbwUpwdiXXW4GqTb0JW68yoycplIks5qKW8WgaJpZM4CN54o.

## 

Des logiciels Android à découvrir:

Pour la sécurité de votre bateau
MarineSecurity et MarineSecurityMap : http://marinesecurity.free.fr

Pour la mesure de votre vitesse et l'optimisation de vos performances à moto/ski, voiture ou en bateau
SpeedMeter : http://androidapp.free.fr


## FabianTerhorst (14 Jun 2016)

@PhilJay this branch https://github.com/PhilJay/MPAndroidChart/tree/remove_xindices ?


## rlimberger (03 Jul 2016)

This issue being closed, does this mean that the master branch has this implemented now?


## PhilJay (03 Jul 2016)

Yes, & the latest release.


## androidhopeful (05 Jul 2016)

Has this changed other things? Previous parts of my code that used `LineData(List<String>, List<ILineDataSet>);` now no longer work (doesn't compile, doesn't seem to like my use of the list of strings for the x labels anymore) -- is there anything in the FAQ that shows how to correctly use the new way?


## FabianTerhorst (05 Jul 2016)

@androidhopeful check out the updated samples


## androidhopeful (05 Jul 2016)

@PhilJay  First wanted to say thank you for this update. Time series is something I've waited for for a long time in this awesome library, so thank you for implementing it!

My initial questions / issues seen:

Is there a way to allow newlines in the x-axis labels? As a result of switching to the new time format to allow the hour/minute/time of day (`dd MMM h:mm a`), there's now a great deal of text crowding unless I manually set `xAxis.setLabelCount()` to something very small (like 3). It'd be great to be able to render something like `dd MMM\nh:mm a` instead.

Also, for time/values, forcing floats seems to lose precision in some contexts (for example I've gotten it to spit out 5:23 PM for a timestamp that is actually 5:24 PM, value `1467753840000`). Even if you use `new Entry(long, float)` when inserting timestamp and value into the data set, it converts the timestamp to a float behind the scenes, which gets mangled later if you want to retrieve that value later through things like `onValueSelected` of `OnChartValueSelectedListener` or if you want to graph two values that happen to be a minute or two apart -- since there does not seem to be a `long, float` constructor for `Entry`, just `float, float`.


## PhilJay (06 Jul 2016)

It does not work for you because the `Entry` constructor changed as mentioned in the release notes.

Its now `Entry(float x, float y)`
instead of `Entry(float y, int xIndex)`


## Praveen2106 (06 Jul 2016)

TimeStamp to Date format conversion can be done using AxisValueFormatter. (@Phil, correct if am wrong)

 But does getFormattedValue() supports new line character?.

This issue https://github.com/PhilJay/MPAndroidChart/issues/73 is still open.Allowing new line character reduces the label length and allows more labels in XAxis. 


## androidhopeful (06 Jul 2016)

@PhilJay I got that to work, yes, but using float for timestamp means you lose precision and get incorrect times


## FabianTerhorst (06 Jul 2016)

@androidhopeful we aren´t as far with the timestamp to loose data there when converting the timestamp long to a float.


## androidhopeful (06 Jul 2016)

@FabianTerhorst The minute level is where it begins to lose precision from what I can tell


## FabianTerhorst (06 Jul 2016)

@androidhopeful with a timestamp thats in seconds that shouldn´t be the case.


## PhilJay (06 Jul 2016)

Exactly, you can simply use "second" timestamps and then multiply them by 1000 when using them with a java `Date` object.

There are plenty of good reasons why this library uses float, and not double or long. I have explained them already.


## androidhopeful (06 Jul 2016)

@PhilJay Point taken. Is there a workaround to allow newlines in the X-axis labels or is that pretty much hardwired?


## srinivasan-cgvak (11 Jul 2016)

Please share your sample to set the date and time value in X-Axis. Now it's not working fine when i tried to convert date object to float (For Entry)and vice versa(AxisValueFormatter). I required this option for most dynamic moving chart based on limited interval like 30sec,60sec. I have spent lots of time on it but till now no success.


## rlimberger (04 Aug 2016)

Thank you for adding this feature. Is there an example on how to use this new feature?


## lkkjohnny (04 Mar 2017)

how to set YAxis accuracy 0.01 in mpandroidchart -LineChart ?



## gokulbalakrishnan (30 Jan 2018)

![screenshot_1517308668](https://user-images.githubusercontent.com/33621243/35561930-d998c150-05d7-11e8-81ba-f9ce0bba00b0.png)
Facing the same issue

## Hetal2011 (28 May 2020)

+1, I am looking for the same feature as well



