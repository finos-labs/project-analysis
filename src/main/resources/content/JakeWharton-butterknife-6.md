#Allow Arbitrary Object Injection with Activities

Owner: JakeWharton

Repo: butterknife

Labels: 

## JakeWharton (07 Mar 2013)

``` java
class Controller {
  @InjectView(R.id.things) ThingsView things;

  public Controller(SomeActivity activity) {
    Views.inject(this, activity);
  }
}
```


