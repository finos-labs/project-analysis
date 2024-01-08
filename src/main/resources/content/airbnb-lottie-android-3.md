#Create a pluggable JSON parsing module

Owner: airbnb

Repo: lottie-android

Labels: 

## gpeal (11 Oct 2016)

Ideally there would be 2 (or n) optional json parsing modules.
1 would use raw json objects so there would be no transitive dependencies
The other would use jackson or another more performant json parsing library.


## gpeal (02 Feb 2017)

@felipecsl That sounds like a good plan. Having better scope protection is also definitely good.

## felipecsl (02 Feb 2017)

Alright, I'll start going down this path then :)


## pdenise708 (03 Feb 2017)

K p

## ZacSweers (18 Feb 2017)

@felipecsl did you start this? If not I'd like to contribute. I think the core of this is that a composition should be constructable via some conventional way (maybe a builder?). The default could basically be the current JSONObject implementations, with maybe some batteries-included optional artifacts that do it with gson/moshi/etc.

## felipecsl (18 Feb 2017)

@hzsweers nope, I was waiting for @gpeal to finish some major JSON parsing refactoring that he had underway. AFAIK the plan was to move the current implementation to a streaming `JsonReader` which should be more memory efficient. Not sure what's the status of that change though, @gpeal can help clarify

## gpeal (18 Feb 2017)

@felipecsl @hzsweers I spent quite a long time working on the JsonReader refactor which would have been a good end-all solution to this since it's strictly n time. However, there were some issues with the way the json was formatted that made it almost impossible to do proper parsing when only reading the json beginning to end linearly. Here is my WIP if you're interested: https://github.com/airbnb/lottie-android/commit/d7227b3a1531195b9e31b2f2bc7e9f16d586ddac

I synced up with Hernan on bodymovin about it and he made a few changes to the json that would make it more feasible:
https://github.com/bodymovin/bodymovin/commit/6ed7b0f8c34e7de7780c56e15a95ed28ebadc30e
https://github.com/bodymovin/bodymovin/commit/18201cb6a92437e7baf7c688bda26bba9b143c6c

I want to give it another try but it would break compatibility with older versions so maybe splitting json parsing into a module and using the new one if you have a new animation would be a good idea and then we can drop support for older versions eventually.

@felipecsl Do you want to take another stab at this?

## felipecsl (18 Feb 2017)

Sounds good to me, thanks for clarifying @gpeal. 
@hzsweers I think we're on the same page about how it should work. I can probably work on it this week, we can sync offline on the details. How does that sound?

## gpeal (20 Feb 2017)

For reference, the longest and most complex json file, LottieLogo2.json (90kb) takes about 30ms to parse.

## ZacSweers (20 Feb 2017)

sounds good

## 0legg (22 Feb 2017)

Few days ago there was JSON spec created for bodymovin, and I'm working on bringing it to JSON Schema — so it'll be possible in the future autogenerate parser source code.

## felipecsl (28 Feb 2017)

Alright let me explain the rest of the changes I have in mind for this issue so I can get input from you guys before sending the PR:
We'd have a new class called `Lottie` responsible for initializing the library state. It would have a builder where we could specific the desired `Converter` implementation to use (similar to how Retrofit works). We could also use this in the future to configure other settings for the library:

```java
Lottie lottie = new Lotite.Builder()
  .converter(new DefaultJsonConverter())
  .build()
```

Then, we'd need to use this `lottie` object when loading animations so it knows which Converter implementation to use:

```java
animationView.setAnimation(lottie, "hello-world.json");
```

We'd also move the current `LottieComposition.Factory#fromJson()`, etc. APIs to `Lottie#fromJson()`. That'd be the entry point for most operations.

`LottieAnimationView` would use a default implementation of `Lottie` when `app:lottie_fileName`  is used via XML since we wouldn't have the chance to initialize the `Lottie` object properly. We'll have to find a way to cover that scenario by maybe initializing it via a singleton.

Finally, the different `Converter` implementations would live in separate artifacts (also Android libraries). In order to use Lottie, you'd have to depend on the correct artifact, for example:

```groovy
  compile 'com.airbnb.android:lottie-converter-default:version'
```

The `lottie-converter-default` artifact depends on `lottie` so it would get the dependency transitively.

What do you guys think?

## 0legg (28 Feb 2017)

Looks good to me; but it's still unclear how will you manage model and logic separation. Also, it's interesting, whether it's possible to parse JSON with databinding; and to provide model automatically from JSON Schema. I wish I had enough time to contribute lot of stuff here

## felipecsl (28 Feb 2017)

our model classes are still somewhat coupled to the JSON structure, but over time we should decouple them as much as possible in a way that makes us input format agnostic. All you need is a converter that takes an `InputStream` and outputs a `LottieComposition`

## fabionuno (28 Feb 2017)

There is any advantage to use this new  `Lottie` class as a parameter of `setAnimation` method and not as a parameter of `LottieAnimationView's` constructor? 
Using as a method parameter is useful if we need to use different JSON loaders with same `LottieAnimationView` instance, but i don't see a situation where this will occur, and I need to define Lottie as a member variable of my class to use it every time I call `setAnimation`.

A `setSingletonInstance` method could be created, like Picasso, to configure the default behavior of `Lottie` and use this singleton when a custom one is not specified in `LottieAnimationView` constructor.

This will help when instantiating `LottieAnimationView` from XML as you suggested.

## gpeal (28 Feb 2017)

@felipecsl I like the overall approach. What do you think about Lottie including the default parser as a transitive dependency so that a) LottieAnimationView can create the default one and b) users don't have to add a second dependency especially when, as of now, there is only one option

## felipecsl (01 Mar 2017)

@gpeal that's not possible because it would cause a circular dependency.
@fabionuno we can't add `Lottie` as a constructor paramterer to `LottieAnimationView` because it needs to be instantiated from the XML

## fabionuno (01 Mar 2017)

@felipecsl The idea was to have a new constructor with `Lottie` as a parameter and when missing get the singleton instance, however in a situation where I declare the LottieAnimationView in XML but load the JSON animation programmatically, have it as parameter of the method `setAnimation` is a better option.

## gpeal (01 Mar 2017)

@fabionuno Adding View constructors is pretty unconventional. We could also add something like what RV does with LayoutManager: https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html#attr_android.support.v7.recyclerview:layoutManager

## felipecsl (14 Mar 2017)

Should we close this issue? Not sure we're still planning to do anything else for this.

## gpeal (14 Mar 2017)

@felipecsl Let's close this for now.

