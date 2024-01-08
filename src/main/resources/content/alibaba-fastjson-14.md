#fastjson / android

Owner: alibaba

Repo: fastjson

Labels: 

## zimdo (24 Oct 2012)

can fastjson be made to work on android?


## wenshao (25 Oct 2012)

yes.

 if your use code: https://github.com/AlibabaTech/fastjson/tree/1.1.22_android

------------------ 原始邮件 ------------------
发件人: "Timo Mika Gläßer"notifications@github.com;
发送时间: 2012年10月25日(星期四) 凌晨2:22
收件人: "AlibabaTech/fastjson"fastjson@noreply.github.com; 

主题: [fastjson] fastjson / android (#14)

can fastjson be made to work on android?

```
           —
           Reply to this email directly or view it on GitHub.
```


## zimdo (25 Oct 2012)

sounds great... is there anything significant missing from that release? i think the last commit was a couple of months ago?


## wenshao (26 Oct 2012)

for android, it's enough!


## wuyexiong (30 Oct 2012)

太感谢了，膜拜。


## zimdo (06 Nov 2012)

hey wenshao, i tried that... the library actually builds fine but the tests don't work... is that intended? or will i run into issues because it being buggy?


## zimdo (06 Nov 2012)

Also just a note when I do something like this:

``` java

public class SplashActivity extends Activity {

    class Hello {

        public Hello() {

        }

        private String goodbye;

        public String getGoodbye() {
            return goodbye;
        }

        public void setGoodbye(String goodbye) {
            this.goodbye = goodbye;
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Log.d("TEST", "hello");
        Hello hello = JSON.parseObject("{goodbye: \"abc\"}", Hello.class);
        Log.d("TEST", hello.goodbye);
    }
}

```

it fails  with the following stacktrace.

11-06 11:22:01.653: E/AndroidRuntime(18719): FATAL EXCEPTION: main
11-06 11:22:01.653: E/AndroidRuntime(18719): com.alibaba.fastjson.JSONException: create instance error, class com.affinityclick.hush.views.activities.SplashActivity$Hello
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.createInstance(JavaBeanDeserializer.java:104)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(JavaBeanDeserializer.java:235)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:463)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.alibaba.fastjson.JSON.parseObject(JSON.java:206)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.alibaba.fastjson.JSON.parseObject(JSON.java:166)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.alibaba.fastjson.JSON.parseObject(JSON.java:287)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.affinityclick.hush.views.activities.SplashActivity$1.run(SplashActivity.java:57)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at android.os.Handler.handleCallback(Handler.java:615)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at android.os.Handler.dispatchMessage(Handler.java:92)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at android.os.Looper.loop(Looper.java:137)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at android.app.ActivityThread.main(ActivityThread.java:4745)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at java.lang.reflect.Method.invokeNative(Native Method)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at java.lang.reflect.Method.invoke(Method.java:511)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:786)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:553)
11-06 11:22:01.653: E/AndroidRuntime(18719):    at dalvik.system.NativeStart.main(Native Method)
11-06 11:22:01.653: E/AndroidRuntime(18719): Caused by: java.lang.NullPointerException
11-06 11:22:01.653: E/AndroidRuntime(18719):    at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.createInstance(JavaBeanDeserializer.java:101)
11-06 11:22:01.653: E/AndroidRuntime(18719):    ... 15 more

When i make the inner-class "public static" it works again.

it also fails if i don't have an empty default constructor which is the way you show it your examples... but that's not a biggy.


## zimdo (06 Nov 2012)

otherwise... pretty much everything seems to work.your library is awesome!


## wenshao (07 Nov 2012)

public class SplashActivity extends Activity {

```
static class Hello { // none static inner class how create instance?
}
```

}


