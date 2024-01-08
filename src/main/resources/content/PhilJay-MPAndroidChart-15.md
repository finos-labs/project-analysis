#Chart error on orientation chagnes

Owner: PhilJay

Repo: MPAndroidChart

Labels: bug 

## ilfuriano (27 Jul 2014)

The chart is drawn in part when the orientation changes.


## PhilJay (27 Jul 2014)

Can you describe your issue in more detail please? It would be great if you could provide a screenshot. I have not experienced it yet during orientation change.

Regards,
Phil


## ilfuriano (27 Jul 2014)

The chart is in a fragment.
Maybe the dimension after change is wrong.
This is the logcat:

```
07-27 16:02:25.492    1488-1488/furiano.portaleceda I/MPChart﹕ Data is set.
07-27 16:02:25.492    1488-1488/furiano.portaleceda I/MPChart﹕ Matrices prepared.
07-27 16:02:25.492    1488-1488/furiano.portaleceda I/MPChart﹕ onLayout()
07-27 16:02:25.500    1488-1488/furiano.portaleceda I/MPChart﹕ Contentrect prepared. Width: 660, height: 964
07-27 16:02:25.500    1488-1488/furiano.portaleceda I/MPChart﹕ DRAW-CALLED
07-27 16:02:25.500    1488-1488/furiano.portaleceda I/MPChart﹕ Matrices prepared.
07-27 16:02:25.508    1488-1488/furiano.portaleceda D/dalvikvm﹕ GC_FOR_ALLOC freed 350K, 5% free 11302K/11783K, paused 6ms, total 6ms
07-27 16:02:25.524    1488-1488/furiano.portaleceda I/dalvikvm-heap﹕ Grow heap (frag case) to 12.537MB for 1494732-byte allocation
07-27 16:02:25.548    1488-1489/furiano.portaleceda D/dalvikvm﹕ GC_CONCURRENT freed 6K, 4% free 12755K/13255K, paused 17ms+2ms, total 22ms
07-27 16:02:25.548    1488-1488/furiano.portaleceda D/dalvikvm﹕ WAIT_FOR_CONCURRENT_GC blocked 0ms
07-27 16:02:25.548    1488-1488/furiano.portaleceda I/MPChart﹕ Contentrect prepared. Width: 631, height: 940
07-27 16:02:25.556    1488-1488/furiano.portaleceda I/MPChart﹕ DrawTime: 10 ms
07-27 16:02:25.568    1488-1488/furiano.portaleceda D/OpenGLRenderer﹕ TextureCache::get: create texture(0xb8babe08): name, size, mSize = 64, 1494720, 2595648
07-27 16:03:18.408    1488-1488/furiano.portaleceda I/MPChart﹕ onLayout()
07-27 16:03:18.408    1488-1488/furiano.portaleceda I/MPChart﹕ Contentrect prepared. Width: 1107, height: 492
07-27 16:03:18.408    1488-1488/furiano.portaleceda I/MPChart﹕ Matrices prepared.
07-27 16:03:18.416    1488-1488/furiano.portaleceda W/EGL_emulation﹕ eglSurfaceAttrib not implemented
07-27 16:03:18.416    1488-1488/furiano.portaleceda I/MPChart﹕ DRAW-CALLED
07-27 16:03:18.420    1488-1488/furiano.portaleceda I/MPChart﹕ DrawTime: 5 ms
```

![graph_normal](https://cloud.githubusercontent.com/assets/5787287/3714431/18fc2a86-15a8-11e4-924b-3e8ccb898597.png)
![graph_orientation_changed](https://cloud.githubusercontent.com/assets/5787287/3714432/190dfcc0-15a8-11e4-9270-d706b7f2bf33.png)


## PhilJay (27 Jul 2014)

Thats very strange indeed. The current example project uses fragments as well where this problem does not occur. Could you post your layout file?


## ilfuriano (27 Jul 2014)

The layout:

```
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="fill_parent"
    android:id="@+id/grafico_container"
    android:layout_height="fill_parent">

</FrameLayout>
```

I add the view in layout with

```
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grafico, container, false);
        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.grafico_container);
        frameLayout.addView(buildChartView());
        return rootView;
    }
```


## PhilJay (28 Jul 2014)

I just tested the chart in use with Fragments and programatically adding the chart to a layout.

This code works fine for me: (after orientation change, the chart is redrawn correctly)

``` java
    private BarChart mChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_bar, container, false);

        // create a new chart object
        mChart = new BarChart(getActivity());

        // add some styling here ...

        mChart.setData(...);

        // programatically add the chart
        LinearLayout parent = (LinearLayout) v.findViewById(R.id.parentLayout);
        parent.addView(mChart);

        return v;
    }
```

The current example project also makes use of Fragments in some cases, maybe you can have a look at it and find your issue.
I will now do some further testing and then report back here.

Also, I think that this might just be an **issue with your emulator**. Have you tried different emulator devices or a real device?


## NeoLSN (02 Aug 2014)

I also have the same issue on Asus tablet.


## PhilJay (02 Aug 2014)

Thank you for mentioning this.

Unfortunately I am unable to reproduce the error. On all my devices it is working just fine.

Could you provide the code you are using to setup the chart?


## NeoLSN (04 Aug 2014)

My manifest setting

<activity
            android:name=".MainActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:label="@string/label" />

then I setup a list view in a fragment on this activity.
Those list items in the list view contains a FrameLayout. When scrolling list view to this list item, it will be inserted a LineChart.
![device-2014-08-04-092320](https://cloud.githubusercontent.com/assets/5782106/3792420/40b5de54-1b76-11e4-9528-0731c266eeaa.png)


## PhilJay (04 Aug 2014)

Now I understand. This means that the chart is part of a ListView item?
I will test this and see if I can now reproduce the issue.

If you could post the layout file of your ListView item, that could help as well.

In addition to that, I just added a new example case to the **example project** that shows how to use the charts inside a ListView.


## PhilJay (04 Aug 2014)

**UPDATE:**

I now know where the issue is coming from. 

It comes from the `android:configChanges="screenSize"` attribute that can be set for an `Activity` in the AndroidManifest.xml.

If you **remove** the `screenSize` from the configChanges, **the chart will draw properly upon orientation change**.

This attribute prevents the `Activity` from adjusting all Views to the new screen-size upon orientation change.

Meaning: If this attribute is set for the `Activity` the chart is in, the chart will not be able to adjust to the new screen size that is created on orientation change, which will cause the above described issue.

Also check out the Android documentation about that topic: http://developer.android.com/guide/topics/manifest/activity-element.html#config

I hope this will help you out, regards
Phil


## NeoLSN (05 Aug 2014)

First, thanks for your response. I'm glad that you can understand what I am meaning because my English is not very well.

Second, is there any chance that I don't need to remove this attribute from manifest.xml?
UPDATE: I referred your list view example and solved the problem. It works good. Thanks.


## PhilJay (05 Aug 2014)

Im glad you could solve your problem. I am currently working on a solution that supports this attribute as well.

Regards,
Phil


## NeoLSN (19 Aug 2014)

Hi, I am sorry to bother you again for this issue.

In this time, I used a relative layout with the same Android manifest settings I mentioned before.
It still shown incorrectly when screen orientation rotated from <b>portrait to landscape</b>.
The result is the same as ilfuriano's.

If I want to keep the Android manifest settings, is there anyway to let the view draw correctly?


## PhilJay (19 Aug 2014)

So you are using the `screenSize` attribute? Could you again post your layout file?


## NeoLSN (19 Aug 2014)

1. Yes
2. Here is my layout

---

```
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="@dimen/abc_action_bar_default_height"
android:padding="10dp"
android:fillViewport="true"
tools:context=".CustomizedFragment">

<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:id="@+id/gps_title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentLeft="true"
            android:ellipsize="marquee"
            android:fadingEdge="horizontal"
            android:singleLine="true"
            android:text="@string/customized_gps_title"
            android:textAppearance="?android:attr/textAppearanceLarge" />

        <TextView
            android:id="@+id/gps_value"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@id/gps_title"
            android:layout_centerHorizontal="true"
            android:layout_centerVertical="true"
            android:ellipsize="marquee"
            android:fadingEdge="horizontal"
            android:textAppearance="?android:attr/textAppearanceLarge" />

        <fragment
            android:id="@+id/map_fragment"
            android:name="com.google.android.gms.maps.MapFragment"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_below="@id/gps_value" />

    </RelativeLayout>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentLeft="true"
            android:ellipsize="marquee"
            android:fadingEdge="horizontal"
            android:singleLine="true"
            android:text="@string/customized_battery_title"
            android:textAppearance="?android:attr/textAppearanceLarge" />

        <TextView
            android:id="@+id/battery_level"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:ellipsize="marquee"
            android:fadingEdge="horizontal"
            android:singleLine="true"
            android:textAppearance="?android:attr/textAppearanceLarge" />

        <TextView
            android:id="@+id/battery_source"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignRight="@id/battery_level"
            android:layout_below="@id/battery_level"
            android:singleLine="true"
            android:textAppearance="?android:attr/textAppearanceSmall"
            android:textColor="?android:attr/textColorSecondary" />

        <com.github.mikephil.charting.charts.LineChart
            android:id="@+id/battery_temp"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_below="@id/battery_source" />

    </RelativeLayout>
    <!-- other layout -->
</LinearLayout>
</ScrollView>
```


## NeoLSN (26 Aug 2014)

Is there any new progress?


