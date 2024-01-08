#Layout Injection Proposal

Owner: JakeWharton

Repo: butterknife

Labels: 

## JakeWharton (09 Mar 2013)

``` java
@InjectLayout(R.layout.login_activity)
public class LoginActivity extends Activity {
  @InjectView(R.id.username) TextView username;
  @InjectView(R.id.password) TextView password;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Views.inject(this);
  }
}
```

which would amount to:

``` java
activity.setContentView(R.layout.login_activity);
username = (TextView) activity.findViewById(R.id.username);
password = (TextView) activity.findViewById(R.id.password);
```

and

``` java
@InjectLayout(R.layout.custom_view)
public class CustomView extends View {
  @InjectView(R.id.thing) TextView thing;

  public CustomView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Views.inject(this);
  }
}
```

which would amount to:

``` java
View root = LayoutInflater.from(context).inflate(R.layout.custom_view, this);
thing = (TextView) root.findViewById(R.id.thing);
```


## JakeWharton (10 Mar 2013)

Been thinking about this. My main problem is that there's not a large value-add in performing the layout inflation. It's really only a reduction of a single line of code. It also isn't clear how it would fit in with the API for most cases. The only one that makes sense to me would be adding a second param to `inject` for an activity layout:

``` java
Views.inject(this, R.layout.login_activity);
```

which would handle calling `setContentView` for you but even that isn't really saving much. You can't do that for regular objects since you'd need a context to inflate.

Ultimately, I like the idea of the library not needing to know about how the view root was created.


## imminent (11 Mar 2013)

I understand your logic, I think. But I think my idea for the proposal
comes from a slightly different angle. I didn't see it as particularly
saving code, but rather as improving readability. I like `@InjectView`
because it clearly lists out the `View`s the object will be using and you
can see the IDs/`View`s it depends on without extra clutter of the other
code in the method and the `findViewById` and type-casting stuff. For that
same reason, I like having `@InjectLayout` (or @Layout, @ContentView, etc)
declared above the class, because it clearly declares what layout the class
depends on without extra clutter. I find it improves the readability, which
ultimately improves maintainability and ease of understanding. It's your
decision what the overarching philosophy for the library is, but I saw it
as improving `View` related semantics, which would include layouts from my
point of view. There was a proposal for intent extras, which would expand
that philosophy to improving Android semantics, but as of my introduction
to the library it was focused around UI.

Whatever you decide, thanks for considering my proposal. If you want to
reconsider doing layout inflation I would love to figure out the best way
to handle it for the different cases.

## 

Dandré Allison                                (323) 823-4456
KeepandShare.com http://www.keepandshare.com

```
          Schedule & Share. Simply. Securely.
```

On Sun, Mar 10, 2013 at 4:03 PM, Jake Wharton notifications@github.comwrote:

> Been thinking about this. My main problem is that there's not a large
> value-add in performing the layout inflation. It's really only a reduction
> of a single line of code. It also isn't clear how it would fit in with the
> API for most cases. The only one that makes sense to me would be adding a
> second param to inject for an activity layout:
> 
> Views.inject(this, R.layout.login_activity);
> 
> which would handle calling setContentView for you but even that isn't
> really saving much. You can't do that for regular objects since you'd need
> a context to inflate.
> 
> Ultimately, I like the idea of the library not needing to know about how
> the view root was created.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/8#issuecomment-14691499
> .


## JakeWharton (11 Mar 2013)

Still experimenting but [here's](https://github.com/JakeWharton/butterknife/compare/layout-injection) what activity layout injection looks like. Admittedly this is the easy case. Views and fragments are must more ambiguous.


## imminent (11 Mar 2013)

I completely agree
On Mar 10, 2013 7:45 PM, "Jake Wharton" notifications@github.com wrote:

> Still experimenting but here'shttps://github.com/JakeWharton/butterknife/compare/layout-injectionwhat activity layout injection looks like. Admittedly this is the easy
> case. Views and fragments are must more ambiguous.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/8#issuecomment-14695391
> .


## imminent (11 Mar 2013)

One caveat is that to be robust there should be a swoop just over
`@InjectLayout` for a use case where a layout is injected but no `View`s
are. It can check the map for already existing layout injections. Right?
On Mar 10, 2013 8:14 PM, "Dandre Allison" dandre.allison@gmail.com wrote:

> I completely agree
> On Mar 10, 2013 7:45 PM, "Jake Wharton" notifications@github.com wrote:
> 
> > Still experimenting but here'shttps://github.com/JakeWharton/butterknife/compare/layout-injectionwhat activity layout injection looks like. Admittedly this is the easy
> > case. Views and fragments are must more ambiguous.
> > 
> > —
> > Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/8#issuecomment-14695391
> > .


## JakeWharton (11 Mar 2013)

Yep. Done.


## JakeWharton (12 Mar 2013)

Activity:

``` java
@InjectLayout(R.layout.whatever)
public class WhateverActivity extends Activity {
  @InjectView(R.id.whatever_button) Button whateverButton;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Views.inject(this);
  }
}
```

Fragment:

``` java
@InjectLayout(R.layout.whatever)
public class WhateverFragment extends Fragment {
  @InjectView(R.id.whatever_button) Button whateverButton;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return Views.inject(this, container);
  }
}
```

View:

``` java
@InjectLayout(R.layout.whatever)
public class WhateverView extends View {
  @InjectView(R.id.whatever_button) Button button;

  public WhateverView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Views.inject(this);
  }
}
```

Thoughts? The fragment API is tragically bad. It even potentially clashes with the `inject(Object, View)` version. I don't like it much at all. Not many alternative solutions. Might need a different method than simply `inject` to clarify and differentiate.


## imminent (12 Mar 2013)

My thoughts exactly Jake. The setup of Fragments makes a free things tough.
Oh, one thing is you could have a convenience method that assumes a
FrameLayout, add that should be the common case, since as I understand it,
the container is only used to create the proper LayoutParams type. Maybe a
method called injectAndGetView, or something along the lines of doing two
things.
On Mar 12, 2013 12:03 AM, "Jake Wharton" notifications@github.com wrote:

> Activity:
> 
> @InjectLayout(R.layout.whatever)public class WhateverActivity extends Activity {
>   @InjectView(R.id.whatever_button) Button whateverButton;
> 
>   @Override public void onCreate(Bundle savedInstanceState) {
>     super.onCreate(savedInstanceState);
>     Views.inject(this);
>   }}
> 
> Fragment:
> 
> @InjectLayout(R.layout.whatever)public class WhateverFragment extends Fragment {
>   @InjectView(R.id.whatever_button) Button whateverButton;
> 
>   @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
>     return Views.inject(this, container);
>   }}
> 
> View:
> 
> @InjectLayout(R.layout.whatever)public class WhateverView extends View {
>   @InjectView(R.id.whatever_button) Button button;
> 
>   public WhateverView(Context context, AttributeSet attrs) {
>     super(context, attrs);
>     Views.inject(this);
>   }}
> 
> Thoughts? The fragment API is tragically bad. It even potentially clashes
> with the inject(Object, View) version. I don't like it much at all. Not
> many alternative solutions. Might need a different method than simply
> inject to clarify and differentiate.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/8#issuecomment-14761386
> .


## imminent (14 Mar 2013)

What do you think of this for `Fragment`s:

``` java
@InjectLayout(R.layout.whatever)
public class WhateverFragment extends Fragment {
  @InjectView(R.id.whatever_button) Button whateverButton;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return Views.inflateAndInject(this, container);
  }
}
```

It reflects a little stronger, a similarity to `inflator.inflate(id, container, false)`. I'm very split on my idea to try providing a convenience method `Views.inflateAndInject(this)` that assumes a `FrameLayout` (which is the commonplace in my experience) because those such methods tend to lead to accidental bugs that are usually hard to trace. My vote is leave it out, all a developer has to do is pass a value that was just passed to them, that's not hard.


## JakeWharton (15 Mar 2013)

Been thinking about this more and more which is causing me to like it less and less. Still not sure I want to throw it out, but going to see what a few others thing about it. I keep going back to what I said before:

> I like the idea of the library not needing to know about how the view root was created.


## imminent (15 Mar 2013)

Yeah, and that would be impossible while supporting Fragment layout
inflation. What about dropping Fragment layout injection? They complicate
stuff. I think there's still good use outside.
On Mar 14, 2013 11:25 PM, "Jake Wharton" notifications@github.com wrote:

> Been thinking about this more and more which is causing me to like it less
> and less. Still not sure I want to throw it out, but going to see what a
> few others thing about it. I keep going back to what I said before:
> 
> I like the idea of the library not needing to know about how the view root
> was created.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/8#issuecomment-14945582
> .


## frankiesardo (27 Mar 2013)

What if you declare the injection as `Layouts.inject(this)`? I know, it's just swapping one line of code for another, but you get the clarity bonus of having `@InjectLayout` at the top of your class and you solve the Fragment inflating issue. For example, you can have a `BaseFragment` like:

``` java
@InjectLayout(R.layout.whatever)
public class BaseFragment extends Fragment {
  @InjectView(R.id.whatever_button) Button whateverButton;

  @Override public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return Layouts.inject(this, container);
  }

  @Override public View onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Views.inject(this, view);
  }
}
```

You force view manipulation only in `onViewCreated` but it's a good tradeoff. Another benefit is that you state clearly what you are doing: `Views.inject` injects views, `Layouts.inject` injects layouts. Developers are free to use only what they need (and wrap calls in a helper method if they like).


## imminent (27 Mar 2013)

`inflater.inflate(R.layout.whatever, container, false);` isn't that what it would look like normally? Doing it this your way doesn't seem to give any advantage. And it doesn't solve the issue of `Fragment` inflating, because the issue is unless you provide a base `Fragment` to extend injecting layouts into `Fragment`s doesn't work like injecting layouts into other things. This is for two reasons, 1) need to pass the `container`. 2) need to return the resulting layout. Jake has stated a desire for the library to not rely on knowing the view root:

> I like the idea of the library not needing to know about how the view root was created.

Which you can't do while supporting `Fragment` layout injection. And the complication of required difference in the API for injecting layouts for `Fragment`s versus others. 


## frankiesardo (27 Mar 2013)

> inflater.inflate(R.layout.whatever, container, false); isn't that what it would look like normally? 

Exactly, and the benefit is that you state the layout resource on top of the class instead of burying it down the code. It feels like you're just adding lines of code instead of helping lazy developers, but then you can have an utility method like `ButterKnife.inject(this)` that injects `Layouts`, `Views`, `Fragments` etc. all in one shot.

> This is for two reasons, 1) need to pass the container. 2) need to return the resulting layout.

I don't find passing the view root into the call is that ugly. In the end, it is not far away from the `Views.inject(Object target, View source)` API. On the other hand, having to return the inflated view is indeed awful. But at least you complicate the API only for the `Layouts` class, leaving `Views` untouched. It may be worth a try.


## imminent (27 Mar 2013)

The only line of code in one method isn't what I would call "buried down in
the code". And the method is named pretty obviously for creating the View.
Is it really any different than declaring a static final variable at the
top of you class with the layout resource ID?

You can't think of the Fragment case in isolation, it will be part of the
overall API. It's not just aesthics, but the completely different API
requirements to inject Fragment layouts. For others you don't pass a
container and you don't need to receive the resulting layout. Any attempt
to group them results in requiring a developer to remember that sometimes
the "same" method needs to be used differently. That's what I would say is
bad design.

This proposal exists to try this out, I think some of the proposal is even
implemented. It's Jake's deduction to make and he's picky (for good
reason), and I dot see your proposal changing any of what's be considered
thus far. We'll see where this goes.

On Wednesday, March 27, 2013, Frankie Sardo wrote:

> inflater.inflate(R.layout.whatever, container, false); isn't that what it
> would look like normally?
> 
> Exactly, and the benefit is that you state the layout resource on top of
> the class instead of burying it down the code. It feels like you're just
> adding lines of code instead of helping lazy developers, but then you can
> have an utility method like ButterKnife.inject(this) that injects Layouts,
> Views, Fragments etc. all in one shot.
> 
> This is for two reasons, 1) need to pass the container. 2) need to return
> the resulting layout.
> 
> I don't find passing the view root into the call is that ugly. In the end,
> it is not far away from the Views.inject(Object target, View source) API.
> On the other hand, having to return the inflated view is indeed awful. But
> at least you complicate the API only for the Layouts class, leaving Viewsuntouched. It may be worth a try.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/8#issuecomment-15521408
> .

## 

## 

Dandré Allison                                (323) 823-4456
KeepandShare.com http://www.keepandshare.com

```
          Schedule & Share. Simply. Securely.
```


## jamolkhon (20 Dec 2013)

Regarding the first case, the only change is `setContentView` is moving into `Views.inject`, and the actual layout id is specified with an annotation on the activity class. There's not much to gain other than the easy-to-spot layout name.

Actually, I'm doing the reverse, moving `Views.inject` into `setContentView`. In my base activity I have overridden the three overloaded `setContentView` methods like this:

``` java
@Override
public void setContentView(int layoutResID) {
  super.setContentView(layoutResID);
  Views.inject(this);
}

@Override
public void setContentView(View view) {
  super.setContentView(view);
  Views.inject(this);
}

@Override
public void setContentView(View view, ViewGroup.LayoutParams params) {
  super.setContentView(view, params);
  Views.inject(this);
}
```

Now, I don't have to worry about forgetting to call `Views.inject`.


## JakeWharton (12 Jan 2014)

I'm giving up on this.


## stephanenicolas (27 Jan 2014)

Maybe this could do : https://github.com/RomainPiel/Michelangelo


## romainpiel (29 Jan 2014)

@stephanenicolas By making Michelangelo, I pretty much wanted to bring the feature mentioned in this thread. But at the end I'm facing the same blocker and I have the feeling that it's not that relevant as a library.
For a compound View it replaces a couple of lines of code and makes the layout resource id more obvious at the beginning of the class.
But for Fragments and Activities it's the same as replacing one line of code by an annotation.


## OneLiteCore (17 Jul 2017)

Why not just getting layout id and let developers inflate it by themselves?
I created a lib for this: <https://github.com/DrkCore/ContentViewAnnotation>

```java
// Annotate layout id
@ContentView(R.layout.activity_first)
public class FirstActivity extends BaseActivity {
}
```
In BaseActivity:

```java
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set ContentView by youself
        setContentView(ContentViews.get(this));
    }
}
```

In Fragment:

```java
public abstract class BaseFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate it by youself
        return inflater.inflate(ContentViews.get(this), container, false);
    }
}
```

It works perfectly with ButterKnife in my project.

