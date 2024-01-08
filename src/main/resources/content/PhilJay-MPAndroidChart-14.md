#Viewport implementation

Owner: PhilJay

Repo: MPAndroidChart

Labels: enhancement 

## Praveen2106 (23 Jul 2014)

Hi Phil,
Thanks for the library. I have a chart with more than 100 values on X-Axis. So i tried using setScalaMinima to a particular value to zoom in. But is it possible to move the end of the chart ? Since its a timeline chart, i would like to show the user the latest entries first and the user can scroll left for more entries. Basically my requirement to show only particular no. of entries(like a viewport) and rest can be viewed by dragging the chart left.


## PhilJay (23 Jul 2014)

So your problem is that you can set the scale minimum so that the user cannot fully zoom out the x-axis, but the starting position is not correct / cannot be changed?


## Praveen2106 (23 Jul 2014)

Yes. Exactly. Since it is a timeline chart, I would like to start from last which will be latest entry.  


## PhilJay (23 Jul 2014)

Very well. Thankfully, that does not look like that a major issue to me.

I am going to look into that problem within the next hours and will hopefully be able to push a **quick fix** alongside with some other changes until tomorrow night (Europe), at the latest.


## Praveen2106 (23 Jul 2014)

Thanks Phil.


## PhilJay (24 Jul 2014)

I have now implemented this feature in my local commit. The only problem for me is that I don't think it's good enough yet :-)

I will try to change the implementation to a way that is satisfying for me within the next few hours to push on **master** or push the features current implementation to the **experimental** banch.


## PhilJay (24 Jul 2014)

Allright, the implementation is now complete and pushed to **master**.
You can see an example in the ExampleProject inside the `LineChartActivity`.

The basic functionality is as follows.

The new method `centerViewPort(int xIndex, float val)` makes it possible to aim the center of the view to a specific position inside the chart, described by the index on the x-axis and the value on the y-axis. This also works very well in combination with the `setScaleMinima(...)` method.

Example:
A chart containing 50 entries ranging from 0-1000 with their values.

``` java
Chart.setData(...);

// restrain x-axis
Chart.setScaleMinima(3f, 1f);

// center view to index 50 and value 500
Chart.centerViewPort(50, 500f);
//.. now view is positioned on the right side of the chart on the x-axis and centered on the y-axis
```


## Praveen2106 (25 Jul 2014)

Thanks Phil. I'm going to test it right away !!


## PhilJay (25 Jul 2014)

Allright. Please let me know if it works and if you are having any problems.


## Praveen2106 (02 Aug 2014)

Sorry. It took time to get back. centerViewPort works fine. But i have to click somewhere on the Chart to make it work. Tried invalidating the chart after calling centerViewPort. But still doesn't work. 

```
    mChart.setData(data);
    mChart.invalidate();

    mChart.centerViewPort(11, 92);
    mChart.invalidate();
```


## PhilJay (02 Aug 2014)

Thank you for your response.
Indeed, there still was a minor issue in the implementation of the `centerViewPort(...)` method that caused the problem you described.
I managed to track down the bug and fix it.

It should now work as expected.

Just for the record, this is the correct implementation (you can also find it in the example project):

``` java

// first, set data
mChart.setData(...);

// restrain the maximum zoomout level, e.g.
mChart.setScaleMinima(3f, 3f);

// center the "camera" to your desired position
mChart.centerViewPort(xIndex, value);

// there should be no further need for calling invalidate()

```


## Praveen2106 (03 Aug 2014)

It works fine now !! Thanks.


## wenli135 (16 Jun 2015)

Still met this problem with the latest release, the chart show correctly only after touch it


