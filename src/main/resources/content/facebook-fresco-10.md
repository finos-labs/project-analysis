#com.facebook.drawee.view.SimpleDraweeView Class could not be found.

Owner: facebook

Repo: fresco

Labels: 

## shobhit (27 Mar 2015)

This is my activity_main.xml

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:fresco="http:/schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin">

    <com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_view"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        fresco:placeholderImage="@drawable/my_placeholder"
        />

</LinearLayout>
```

```
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(getApplicationContext());
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(Uri.parse("http://image_uri"));
    }

}

```

![screen shot 2015-03-27 at 12 49 13 pm](https://cloud.githubusercontent.com/assets/301730/6863915/bce654dc-d47f-11e4-9d97-d82e5b77a3e4.png)

I have already included compile 'com.facebook.fresco:fresco:0.1.0+' in the gradle file.

The  Android SDK version is:-

```
compileSdkVersion 21
buildToolsVersion "21.1.2"

```

Still i am get the error that Drawee Class cannot be found.


## mohith7548 (28 Mar 2018)

There is typo mistake here in this line of Code
xmlns:fresco="http:/schemas.android.com/apk/res-auto".
The correct is xmlns:fresco="http://schemas.android.com/apk/res-auto"

