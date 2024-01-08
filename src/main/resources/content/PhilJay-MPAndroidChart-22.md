#Bar color and limit issue

Owner: PhilJay

Repo: MPAndroidChart

Labels: 

## vijaykarora (05 Aug 2014)

Thank you for providing a very good library. I need your help to complete my requirement. I tried to findout solution on stackoverflow but didn't found any conversation about this topic. Actually i am working on Bar chart and i want to set particular bar color with every bar like if my value is less than 10 then i need to show different color otherwise different.

My another issue is to draw horizontal line at 10 values in a bar chart like it will look like a limit.

Thanks in advance
 Regards
Vijay Kumar


## PhilJay (05 Aug 2014)

Ok, so you need the following two things:
- a stacked barchart / different values in one bar
- a combination of line- and barchart (because of your "limit" line)

I am currently working on implementing those features, but I cannot tell when exactly I will have the time to finish the implementation.

In the meantime, I suggest the following approach:
You can use multiple DataSets, each representing one color.

**Example**
Your limits are 10 and 20, and the colors representing the limits are red (below 10), yellow (below 20) and green (everything above 20).

The values you want to represent in the chart are 5, 25, and 15 on x-axis index 0, 1 and 2.

Now, you create a `DataSet`that is represented by the color green, and contains all values above 20. In this example case, that would be the values 0, 25, and 0 (everything below 25 is 0).

As a next step, you create a `DataSet` that represents the color yellow, and contains all values between 10 and 20. In this example, that would be the values 0, 20 and 15.

Last but not least, you create the `DataSet` representing the color red. It will contain the values 5, 10 and 10. If you now combine these `DataSets` and add them to the chart, everything below 10 should be red, between 10 and 20 yellow, and above 20 green.

For your limit line in the chart, I suggest you modify the `BarLineChartBase` class of the library by adding the following code inside the `onDraw(...)` method: (between `drawData()` and `drawHighlights()`)

``` java

        drawData();

        // draws a horizontal line through the whole chart at value 10
        float[] pts = new float[] { 0f, 10f, mDeltaX, 10f };
        transformPointArray(pts);

        mDrawCanvas.drawLine(pts[0], pts[1], pts[2], pts[3], yourDesiredPaintObject);

        drawHighlights();
        // ...
```


## vijaykarora (05 Aug 2014)

Thank you so much.


## PhilJay (16 Aug 2014)

Update: The [latest commit](https://github.com/PhilJay/MPAndroidChart/commit/4a9504e0ba50c9f5b2f9ac4ffbaf177dfda8c6e0#diff-d41d8cd98f00b204e9800998ecf8427e) now features `LimitLines` that can be set to the data object that is handed to the chart.

``` java
LineData ld = new LineData(...);

LimitLine ll = new LimitLine(100f); // limit at 100
ll.set(...); // additional styling...
ld.addLimitLine(ll);

mLineChart.setData(ld);
```


## SantoshDawanse (09 Feb 2016)

@amagine @PhilJay I have the same issue as @amagine but I cannot understand what you guys are saying. Can you show me a simple example.


