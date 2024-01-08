#Inject Fragments Proposal

Owner: JakeWharton

Repo: butterknife

Labels: 

## frankiesardo (26 Mar 2013)

I know you said no to the various proposal of adding other Injection features to butter knife. But when I think about it, it seems that an `@InjectFragment` would really be the perfect companion for `@InjectView`, enabling the developers to reference every element in the xml while avoiding the verbose `getFragmentManager().findFragmentById()`. I started developing something along [those lines](https://github.com/frankiesardo/butterknife/blob/fragment_injection/butterknife/src/main/java/butterknife/Fragments.java), enjoying the added benefit of eliminating the confusion between support fragment manager, fragment activities etc. If you're interested in expanding butter knife in that direction I can come up with a decent pull request.


## imminent (27 Mar 2013)

I will re-pitch this as being completely compatible with the focus of Butter Knife; `View` "injection". Android calls them `Fragment`s and gave them an API that is neither `Activity` nor `View`, but you can add them to XML files with other `View`s and `ViewGroup`s and the usually function as a widget on the screen. Because of this, you call easily group `Fragment`s, at least in some capacity, with `View`s. 

Now, whether it should definitely be added to the library is still completely up for questioning. I personally tend to use a `FrameLayout` fragment container, instead of the `<fragment>` tag, and if you use nested `Fragment`s, I believe the documentation states that it _must_ be a programmatically generated `Fragment`. So, I might place the usefulness of this feature as potentially niche.   


## JakeWharton (27 Mar 2013)

Swapping fragments also requires code not XML.  (mobile)


## frankiesardo (27 Mar 2013)

My personal preference is to have everything as statically typed as possible in the layout xmls. I think that a layout like:

``` xml
<LinearLayout
  ... >
  <fragment
  class="shopping.NavigationFragment" 
  ... />
  <fragment
  class="shopping.ProductListFragment" 
  ... />
  <fragment
  class="shopping.ProductDetailFragment" 
  ... />
</LinearLayout>
```

Reads a lot better and is much more expressive than:

``` xml
<LinearLayout
  ... >
  <FrameLayout 
  ... />
  <FrameLayout 
  ... />
  <FrameLayout 
  ... />
</LinearLayout>
```

But as you say, it's a matter of style. I prefer to swap fragments only as a last resource, and instead notify them via listener/Otto. I know many developers that share this same idea, and that's why I think `@InjectFragment` would be a welcomed addiction.


## JakeWharton (13 Apr 2013)

Just want to add that you could still use this without layout fragments since they are present in the `FragmentManager` upon rotation.

Your code would them become something like:

``` java
@InjectFragment(FOO_TAG) FooFragment fooFragment;

onCreate(Bundle b) {
  super.onCreate(b);
  setContentView(...);

  if (fooFragment == null) {
    fooFragment = FooFragment.newInstance();
    // perform transaction
  }
}
```


## jcampbell05 (25 Jun 2013)

This would be useful for MAps as google themsevles reccomend embedding the maps fragment in the XML


## JakeWharton (10 Jul 2013)

I'm rejecting this. The fragment API is terrible and having something like this would only serve to muddle and confuse proper behavior. Plus you shouldn't be using fragments in XML unless you like nightmares.


## imminent (10 Jul 2013)

@JakeWharton on a related note, I'm experimenting with a `Fragment` injection in a MVC framework library I'm working on. I have an `@InjectionPresentationFragment` field annotation that takes a pair of container id and screen id, along with a tag. You can then call `injectionPresentationFragments(screen_id)` in the code. This lets you define different screens, like tablet size and phone, or landscape, etc. The library then can execute the appropriate fragment transactions. You can also define that the `Fragment` should not be instantiated by the library, in which case the annotation allows the library to know which "Presentation Fragments" are used in the `Activity` still. This set up works fine for what I've needed thus far.


## thuytrinh (15 Jun 2014)

@JakeWharton Could you reasonably point out the **nightmares** of using fragments in XML? Or maybe there're several existing threads of it but I couldn't find?


## JakeWharton (15 Jun 2014)

They don't have much utility. You can't swap them out dynamically so they're no different than just composing views in layouts.


