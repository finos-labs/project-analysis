#Calling super on a class with injects fails

Owner: JakeWharton

Repo: butterknife

Labels: 

## scruffyfox (10 Apr 2013)

Calling super on a method which has inject functionality in the parent class doesn't populate the views. 

I had a look through the code and it looks like there is functionality for it to recurse through the parent classes. I would try fix it myself, but I don't know how to import/configure the sources :)

From sample sources:

``` java
public class SimpleActivity extends BaseActivity {
  //uncommenting this works.
  //@InjectView(R.id.title) TextView title;
  @InjectView(R.id.subtitle) TextView subtitle;
  @InjectView(R.id.footer) TextView footer;

  @Override protected void onCreate(Bundle savedInstanceState) {
    // calling super should populate BaseActivity.title
    super.onCreate(savedInstanceState);

    Views.inject(this);

    // Title is always null because super inject didnt work
    title.setText("Butter Knife");
    subtitle.setText("View \"injection\" for Android.");
    footer.setText("by Jake Wharton");
  }
}
```

``` java
public class BaseActivity extends Activity {
  @InjectView(R.id.title) TextView title;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.simple_activity);
    Views.inject(this);
  }
}
```

FWIW Im using the JAR lib v 1.3.0


