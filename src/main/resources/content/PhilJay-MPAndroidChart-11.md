#Different line styles

Owner: PhilJay

Repo: MPAndroidChart

Labels: enhancement 

## onyame (13 Jul 2014)

It would be great, if you could support different line styles such as dashed or dotted lines for the LineChart.


## PhilJay (13 Jul 2014)

Thank you for your input. You mean something like this?

![bildschirmfoto 2014-07-13 um 14 42 58](https://cloud.githubusercontent.com/assets/6759734/3564530/7c1de14a-0a8b-11e4-9cd9-a44c2007df77.png)

I am currently working on that feature. It should be out alongside other improvements within next week.

Regards,
Philipp


## onyame (13 Jul 2014)

Yeah, exactly like that! :)
Thanks!


## PhilJay (13 Jul 2014)

I just pushed your desired feature onto the **experimental** branch. It should be working as you pleased.

You can use `LineChart.enableDashedLine(float lineLength, float spaceLength, float phase)` to enable dashed lines for the chart.

Be aware that this branch is **way ahead of features** than the current master branch, but **might not be as stable**, depending on what features you are using. Data filtering (linear approximation) is not working at the moment.


## WaliaAditya (05 Sept 2017)

hey @PhilJay  thanks for the wonderful library its been real help.
I'll be thankful if you can help me through this.
Actually i was using this library and i wanted to make some of the xaxis grid line to be dashed line is their any functionality for that?? i don't want all of the xaxis grid lines to be dashed.
thanks in advance.

## channae (28 Aug 2019)

@WaliaAditya Hi, Im looking for the same behaviour. Were you able to achieve this? 

