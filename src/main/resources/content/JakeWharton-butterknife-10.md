#Inject system services

Owner: JakeWharton

Repo: butterknife

Labels: 

## mecid (10 Mar 2013)



## JakeWharton (10 Mar 2013)

Butter knife is for View injection only. I don't want it to become a generic "injection" framework. Plus you can already do this with [Dagger](http://square.github.com/dagger/).

``` java
@Module
public class AndroidServiceModule {
  private final Context context;

  public AndroidSerivceModule(Context context) {
    this.context = context.getApplicationContext();
  }

  @Provide @Singleton public DisplayService provideBatterManager() {
    return (DisplayService) context.getSystemService(DISPLAY_SERVICE);
  }
}
```


## johnjohndoe (23 May 2014)

Thanks for the example.


