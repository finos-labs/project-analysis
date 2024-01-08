#Using OkHttp -- Docs clarification...

Owner: facebook

Repo: fresco

Labels: 

## philipbjorge (28 Mar 2015)

So I'm reading the docs on using OkHttp and it indicates that my gradle file should not have `compile 'com.facebook.fresco:fresco:0.1.0+'` if I'm trying to use Fresco with OkHttp. However, unless I add that dependency, I have no Fresco to initialize.

Should I be including three dependencies as follows? If so, I think the docs could be clearer...

```
    compile 'com.facebook.fresco:fresco:0.1.0+'
    compile "com.facebook.fresco:drawee:0.1.0+"
    compile "com.facebook.fresco:imagepipeline-okhttp:0.1.0+"
```

http://frescolib.org/docs/using-other-network-layers.html#_


## plamenko (28 Mar 2015)

Thank you, there was a typo in the documentation. The following should be enough:

```
compile 'com.facebook.fresco:fresco:0.1.0+'
compile "com.facebook.fresco:imagepipeline-okhttp:0.1.0+"
```


## philipbjorge (28 Mar 2015)

Awesome -- Thanks for the prompt response.


## qq157755587 (29 Mar 2015)

It looks there is still a type.

```
compile: "com.facebook.fresco:fresco:0.1.0+"
compile: "com.facebook.fresco:imagepipeline-okhttp:0.1.0+"
```

should be

```
compile 'com.facebook.fresco:fresco:0.1.0+'
compile 'com.facebook.fresco:imagepipeline-okhttp:0.1.0+'
```


## broakenmedia (29 Mar 2015)

the imagepipeline-okhttp also seems to have missing classes namely:

java.lang.NoSuchMethodError: com.squareup.okhttp.OkHttpClient.open
at com.squareup.okhttp.OkUrlFactory.open(OkUrlFactory.java:44)


## michalgr (30 Mar 2015)

@xbroak : would you mind creating separate issue for the missing class ?


