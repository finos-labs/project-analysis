#[RFC] Improve support for auto-unsubscribing observables and newer language/API levels

Owner: ReactiveX

Repo: RxAndroid

Labels: help wanted 

## mttkay (10 Sept 2014)

Currently, using Observables in Android components that have access to `Context` (or are themselves) requires one to carefully think about detaching from these sequences as part of the component life-cycle, since otherwise a closure the sequence holds a (strong) reference to might leak the current context until the time the sequence finishes and releases all subscribers.

A good example are configuration changes, where Android first destroys, then recreates an Activity, but observables might still be holding on to a subscriber created within the scope of that Activity. Even with retained fragments that detach from their host activity, it's easy enough to leak the attached context indirectly by holding on to a view first created through that context (every view in Android has a strong reference to the Activity context it was first created in.)

This ticket aims to find solutions to improve this situation, potentially by leveraging newer Android API levels where available, and/or add first class support for newer Java language levels (Java 8/Retrolambda)

Some suggestions that have been made already follow.

**Android 14+ APIs**

One option would be to make use of Activity life cycle callbacks:
http://developer.android.com/reference/android/app/Application.ActivityLifecycleCallbacks.html

This might allows us to unsubscribe once Android is about to destroy an activity.

**Java 8 lambdas and Retrolambda**

Part of the problem is that every "lambda" in Java 7 or below is an anonymous inner class that will hold a strong reference to the outer class, even if it's a pure function that does not access state outside the lambda. Java 8 + Retrolambda could help here, since pure functions will be materialized as static methods that hold no reference back to their owner.

http://cr.openjdk.java.net/~briangoetz/lambda/lambda-translation.html
https://github.com/orfjackal/retrolambda

**Weak subscribers**

We've taken several stabs at this and always dismissed the solution. The idea was to use WeakReferences to bind subscribers and lambdas. The problem with his approach is that it doesn't work with lambdas, since the only incoming reference would be weak, and they are eligible for GC the instant they're created. There might still be other ways to achieve this, however, I'll leave it open for discussion.


## roman-mazur (23 Nov 2014)

AOP approach would be simpler in implementation and usage though...


## JakeWharton (23 Nov 2014)

Yeah but it's massively worse conceptually. Although the people who tolerate Retrolamba probably wouldn't care!


## roman-mazur (23 Nov 2014)

Spent another 30 minutes :)
https://github.com/roman-mazur/rx-annotations/blob/master/test/src/main/java/rx/annotations/sample/Component.java

```
@SuperClass(
    value = SomeFrameworkComponent.class,
    methods = "onLowMemory"
)
public class Component extends Rx_SomeFrameworkComponent {

  @Override
  protected void onCreate() {
    super.onCreate();
    Observable.never().takeUntil(onDestroyObservable());
    Observable.never().takeUntil(onLowMemoryObservable());
  }

  @RxObservable
  @Override
  protected void onDestroy() {
    super.onDestroy();
    System.out.println("Custom implementation");
  }

}
```

It might be not necessary to override methods.

```
git clone git@github.com:roman-mazur/rx-annotations.git
cd rx-annotations
./gradlew :test:compileJava
cat test/build/classes/main/rx/annotations/sample/Rx_SomeFrameworkComponent.java 
```

@JakeWharton Do you think it reduces boilerplate or actually introduces it? :)


## DylanSale (23 Nov 2014)

One thing to note with using `takeUntil` that came up recently for me is that it actually causes the `Observable` to complete, i. e. `onComplete` is called. This caused an issue because "completing" and "unsubscribing due to lifecycle callbacks" had different semantics. I'm not sure if they should really be distinguishable or not, or if there is even a nice way to distinguish the two (that doesn't include using onError).

I tweaked the Subscriber to not have that distinction, so it wasn't ultimately a big deal, just something that didn't crop up before moving to using `takeUntil`.


## dlew (23 Nov 2014)

@JakeWharton Even if we used the activity lifecycle callbacks that would still not solve the problem for `Fragment` users. (inb4 yet another debate about whether to use `Fragments`)

I agree that this version of annotation processing seems to smell. Anytime you're explicitly using classes that don't actually exist until you build worries me.


## dlew (23 Nov 2014)

@DylanSale That's a really good point. I can see a lot of devs being tripped up by it. For example, if I create an `Observable<RetrofitResponse>` I would not expect to see `onComplete()` before `onNext()`. We're using a side effect here - the fact that on complete causes unsubscription.

`OperatorTakeUntil` is one of the simpler operators; we could create `OperatorTakeUntilThenUnsubscribe` that combines its functionality with something similar to `OperatorConditionalBinding`.


## mr-archano (23 Nov 2014)

My 2c after reading the entire discussion few times and have seen @dlew's PR:
- :+1: for the idea of a separate component and the reactive approach to deal with automatic unsubscribing, especially using a custom operator that explicitly models the unsubscribing in `onNext()`
- :-1: for the idea to bring an `Observable<SomeEvent>`: I see this bringing the problem of how to generate such `Observable` in an elegant way in your Activity/Fragment, and to be honest I see it as too convoluted. Especially the filter/matching mechanism in [LifecycleObservable#107](https://github.com/ReactiveX/RxAndroid/pull/75/files#diff-c990fb835e09a42f9171ab3a4b7acdacR107) is definitely not appealing to me.

I would love to see a simpler solution, and I quickly drafted something in a [gist](https://gist.github.com/mr-archano/0d1c5885229229bed637). It is basically a mix of some of the ideas above:
- uses the 'reactive' approach proposed by @DylanSale for the unsubscribing mechanism
- uses @dlew `OperatorSubscribeUntil` to model the onNext() -> unsubscribe
- uses composition vs inheritance


## dlew (24 Nov 2014)

From your gist it looks like your solution still requires inheritance. More
of it than mine does.

That solution looks a lot like my original work. The problem is that it
adds a lot of unnecessary framework. It stores state outside of a reactive
context. It depends on smart overrides of methods in the life cycle.

When I really reduced the problem to its core parts I realized the
Observable of the life cycle was all I really needed.

On Sun, Nov 23, 2014, 15:06 Antonio Bertucci notifications@github.com
wrote:

> My 2c after reading the entire discussion few times and have seen @dlew
> https://github.com/dlew's PR:
> - [image: :+1:] for the idea of a separate component and the reactive
>   approach to deal with automatic unsubscribing, especially using a custom
>   operator that explicitly models the unsubscribing in onNext()
> - [image: :-1:] for the idea to bring an Observable<SomeEvent>: I see
>   this will bring the problem of how to generate such Observable in an
>   elegant way in your Activity/Fragment, and to be honest I see it as too
>   convoluted. Especially the filter/matching mechanism in
>   LifecycleObservable#107
>   https://github.com/ReactiveX/RxAndroid/pull/75/files#diff-c990fb835e09a42f9171ab3a4b7acdacR107
>   is definitely not appealing to me.
> 
> I would love to see a simpler solution, and I quickly drafted something in
> a gist https://gist.github.com/mr-archano/0d1c5885229229bed637. It is
> basically a mix of some of the ideas above:
> - uses the 'reactive' approach proposed by @DylanSale
>   https://github.com/DylanSale for unsubscribing mechanism
> - uses @dlew https://github.com/dlew OperatorSubscribeUntil to model
>   the onNext() -> unsubscribe mechanism
> - uses composition vs inheritance
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/ReactiveX/RxAndroid/issues/12#issuecomment-64134638.


## omo (24 Nov 2014)

I love to have Observable<LifecycleEvent>. I almost did it when I worked on bindView(), and was thinking about the generalisation. I'm eager to rewrite bindView() using LifecycleObservable.


## mr-archano (24 Nov 2014)

@dlew I don't want to pollute this thread too much, so I put some comment on my gist. We might want to continue the [discussion there](https://gist.github.com/mr-archano/0d1c5885229229bed637#comment-1343424).


## austynmahoney (25 Nov 2014)

Not sure if their implementation is all that great, but the Facebook SDK uses a [lifecycle helper](https://developers.facebook.com/docs/reference/android/current/class/UiLifecycleHelper) class that is similar to the suggested implementation here. A lot of developers may already be used to having to call a helper in `onCreate` and the other lifecycle methods.

I'm not sure you can make it any nicer if you aren't subclassing `Activity` or `Fragment`.


## Alexander-- (28 Nov 2014)

> _I'm not sure you can make it any nicer if you aren't subclassing Activity or Fragment._

We all really must accept a need to use AOP on Android. And here is how to do it right.

##### What we have

There are plenty of similar tools: [AndroidAnnotations](https://github.com/excilys/androidannotations), [ButterKnife](https://github.com/JakeWharton/butterknife), [FragmentArgs](https://github.com/sockeqwe/fragmentargs), [IcePick](https://github.com/frankiesardo/icepick) and now this "LifecycleManager", inserting a line of code in well-known locations to greatly improve code quality, turning Java code into a mix of powerful  domain-specific languages. Those lines are small, but their count grows, showing tendency to turn arbitrary object lifecycle callbacks into small event buses, distributing events between multiple libraries. Those buses _don't need_ commonly accepted event identifiers and notification strategies, instead they rely on common API, provided by class authors, and operate by means of bytecode itself.

_Every lifecycle method of any object amounts to an event._

##### What we need

Imagine something like [Mimic](https://github.com/stephanenicolas/mimic) in reverse - like AspectJ advices, but without diverting into different language or going down to bytecode level:

``` java
@PluginFor({Fragment.class})
public abstract class ButterknifeFragmentInjection extends Fragment {

    @PlugInto(PluggingMode.AT_BEGINNING)
    public void onViewCreated(View view) {
        ButterKnife.inject(this, view);
    }
}
```

Simple, typesafe and straightforward. Extending Fragment here is just a convenience: the code can be written in Java, but rather than being turned into bytecode during compilation it's source code would be distributed with library to act as template for actual class, created by annotation processor:

``` java
class ExampleFragment$$Helper {
    private final WeakReference<ExampleFragment> instance;

    ExampleFragment$$Helper(ExampleFragment instance) {
        this.instance = new WeakReference(instance);
    }

    public void onViewCreated(View view) {
        final ExampleFragment self = instance.get();
        ButterKnife.inject(self, view);
        ...
        self = null;
    }

    public void onSaveInstanceState(Bundle state) {
        final ExampleFragment self = instance.get();
        Icepick.saveInstanceState(self, state);
        self = null;
    }
    ...
}
```

The only things, that would have to be inserted directly in bytecode of `ExampleFragment`, are calls to generated methods (and, perhaps, constructor of generated helper). Slightly tricky part is choosing _which_ classes to put those calls into, but that is unlikely to pose much trouble.

##### What should be done

Pretty much everything needed is already here: ready-to-go annotation processing and [bytecode manipulation](https://github.com/stephanenicolas/injects/) tools already exists. The only real challenge is avoiding obscure side effects and general lack of transparency, that may come from abusing AOP, but with source code being before developer's eyes that would not be as much of issue.

PS Wow, it escalated into a plan of little NIH-induced AOP framework faster than I expected. Still, I believe, that this has at least indirect relation to main topic of this issue, so please comment on the idea, if you have anything to say. Similar solutions, ideas, perhaps?


## JakeWharton (28 Nov 2014)

AspectJ already does both type-safe declaration in Java and weaving in the bytecode.

Also, no. Just no. You'll never get everyone on board with this.


## Alexander-- (28 Nov 2014)

> AspectJ already does both type-safe declaration in Java and weaving in the bytecode.

RxAndroid-AspectJ integration, when?

> Also, no. Just no. You'll never get everyone on board with this.

I am sorry, if I have created an impression of someone seeking to steal your time here :) Would you, please, describe a solution to problem in question, using AspectJ? I am not a tool fanatic, so if that solution had even half of features I need, I would gladly use it. The concept, described at #93 is fine, but sadly useless in most cases, because it means giving up on inheritance altogether.


## JakeWharton (28 Nov 2014)

I meant that AspectJ already allows you to write directly in Java and weave class files at compile-time rather than at runtime.

An AOP-based approach is compelling if you have the tolerance for bytecode-manipulating tools. I just think it would be better served as a standalone project than something directly in a library like this one.


## Alexander-- (28 Nov 2014)

Look at this code:

``` java
public FooActivity extends Activity {
    @InjectView(R.id.text) TextView text;

    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.text);
    }

    public void onPostCreate(Bundle savedInstanceState) {
        text.setText("Is this good enough?");
    }
}
```

Let's say, that it works. Does it matter, if the code relies on `ActivityLifecycleCallbacks` implemented somewhere else, or employs a bytecode manipulation to have job done? If former, would that be good enough to be used in practice? If later, so what?

> I meant that AspectJ already allows you to write directly in Java and weave class files at compile-time rather than at runtime.

@JakeWharton, It still does too little to be trully useful. If I were to tell you, that I 'd like to see a better version of AspectJ without separate language elements (please, don't pretend, that those do not exist), with pointcuts and most of joint types removed and source code of aspects being properly viewable and debuggable, would that be descriptive enough? Just move those code blobs from magical inner classes into source files, generated by annotation processor, and it would be exactly, what I described above. If creating something like that were possible on top of AspectJ in it's current state..


## mr-archano (28 Nov 2014)

@Alexander-- I think a lot of people (including me) would like to see/use annotations as a solution, but the aim here is to maintain this module as closer as possible to nuts and bolts.
An `rxandroid-framework` module has been defined to include some (opinionated) utilities on top of RxAndroid. You could propose to introduce a new external module (eg `rxandroid-annotations`) to this repo, and kick off the discussion in another GH issue. Or just start it as a separate project and be in touch with the maintainers here.


## JakeWharton (28 Nov 2014)

You can't generate inner-classes with an annotation processor. Also I've lost track of whatever you're getting at so unless there's a concrete proposal you're better off playing in a separate project. This project has a hard enough time figuring out what should actually be included without conflating other opinionated technologies.


## hamidp (02 Jan 2015)

Do we consider https://github.com/ReactiveX/RxAndroid/pull/75 as good enough of a solution for now?


## JakeWharton (30 Jun 2015)

As part of #172 the auto-unsubscribing things have been removed for future release. At this time, there's a wide variety of ways people create streams and want to unsubscribe from them. There's also a lot of potentially useful magic solutions that will appeal to a subset of users but not all. Knowledge of the strong references that are kept and continually trying to improve the pattern of using RxJava on Android will be the path forward for now unless some amazingly awesome solution that everyone can agree on and use.


