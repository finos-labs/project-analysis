#Setting background color has no effect

Owner: PhilJay

Repo: MPAndroidChart

Labels: bug 

## Mrkhagol (01 Jul 2014)

hi,

setting the background color doesn't do anything. It just stays white by default in your examples. I am not getting any errors otherwise I could tell you more on that. Appreciate any feedback.
By the way, charts are awesome. keep up the work.


## PhilJay (01 Jul 2014)

Thank you for your input. The reason therefore is, that the Chart draws the color white by default over the whole drawing surface. This will be fixed in the next update alongside other things like major improvements and new features.

As a quick fix, you could simply draw the desired color you want by modifying the library. You can do that by **changing code line 304** in the **Chart.java** file (inside `onDraw(...)` method) of the current master branch:

`mDrawCanvas.drawColor(Color.WHITE); // specify the background color you like`

Then, the background should be drawn in the color you specified. Please let me know if this quick fix solved your issue.


## Mrkhagol (24 Jul 2014)

Thanks for the response and good other major updates so far. I have another question for you though
..I am doing two charts where I select a category and it changes the chart based on array of data. I am looking at log and y draw values to see if it's caching the right value. It is doing that but the chart view doesn't refresh. What I have found is if I select the same category again it refreshes the view and proper y bar axis values and chart view shows up. 
Is there a way to do force refresh of chart?


## PhilJay (25 Jul 2014)

Of course there is. Just call `Chart.invalidate()` at any time you want to refresh.
I hope this helps.

Regards,
PHilipp


## Mrkhagol (25 Jul 2014)

That's something...I had that in my method all along. It just wasn't at right place where it should be called from. Now it changes chart view just fine. Thank you.
By the way, is there manual type read besides readme.md file so that way you don't get bothered for things like this.


## PhilJay (25 Jul 2014)

Good, I am glad you could figure it out.
At this moment there is nothing else except the readme and the example project because this library is still very new and I have just written it.

Nevertheless I am already working on something similar to what you mentioned.

Regards,
Philipp


## Mrkhagol (25 Jul 2014)

Is there a way to set multi line chart lines to set at different widths?
Is there also a way to over ride that it just plots from less than 2 data points. So chart draws anyway if it's just one point in array list.
Thank you.


## PhilJay (06 Oct 2014)

Should be fixed as of release v1.6.5

Regards,
Phil


