#Shared element transition

Owner: facebook

Repo: fresco

Labels: 

## jorgemf (28 Mar 2015)

Shared element transition doesn't seem to work.

Tested in a motorola device with android 5.0


## burzumrus (12 May 2016)

Hi all! In new Fresco release 0.10 with custom ScaleType transform between different ScaleTypes is trivial. Here is my implementation
https://gist.github.com/burzumrus/a589aa7e36ca003ddaf2334218c50ad0

Usage is simple

```
TransitionSet transitionSet = new TransitionSet();
transitionSet.addTransition(new ChangeBounds());
transitionSet.addTransition(new DraweeTransform(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.FIT_CENTER));
getWindow().setSharedElementEnterTransition(transitionSet);

```


## plamenko (13 May 2016)

@burzumrus that's amazing! Thanks for implementing it. That's exactly what I had in mind with `InterpolatingScaleType`. Consider making a pull-request for Fresco if not done already.


## ladia12 (23 Jul 2016)

@plamenko I am using fresco 0.12 and

```
getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(
                    ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP));
getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(
                    ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP));
```

The animation is working perfectly. But on return to first activity the image is disappearing


## gregkorossy (13 Aug 2016)

Did anyone try these with fragment-to-fragment animations? Because it doesn't seem to work on v0.12.
**Edit**: is it possible that the RecyclerView causes troubles?
**Edit 2**: Seems like the problem is that `ChangeBounds` itself only uses X and Y _window_ coords if the reparenting is set to true. Setting it via `ChangeBounds`'s `setReparenting(true)` is deprecated and `ChangeTransform` is recommended instead. So for a `RecyclerView`, `transitionSet.addTransition(new ChangeTransform());` is also required. (The returning animation seems bad still, but at least the entering animation is okay (~~except that the scale type has no effect~~ changing `startValues.view` to `endValues.view` in `createAnimator(...)` solves this) with this.)


## sperochon (14 Aug 2016)

@Gericop and @ladia12 0.12 works well in my project in fragment-to-fragment animations.
I use it also in RecyclerView.
The thing is that you can only use 'replace' fragment transaction. You can't use 'add' transaction.
If it can help you, here is an example (without fresco) of fragment-to-fragment transition that helped me to start on something that worked (you can downloads the project code on github)
http://www.androidauthority.com/using-shared-element-transitions-activities-fragments-631996/


## gregkorossy (14 Aug 2016)

@sperochon I use replace and it's in a RecyclerView but it doesn't work (and honestly, it's surprising that it works for you, maybe you use a different version of RecyclerView? I use v24.1.1). I had to make a few changes in order to make the animations work:
- added `ChangeTransform` to the transition set via `transitionSet.addTransition(new ChangeTransform());`
  - this is due to the fact that the `ChangeBounds` reports incorrect position of the start view in a `RecyclerView` (it always returns the first element's X,Y coords)
- in `createAnimator(...)` replace `if (mFromScale == mToScale)` with `if(mFromScale == mToScale && startBounds.equals(endBounds))`
  - the transform won't happen otherwise if the two drawees share the same scale type, even though their sizes are not the same
- in `createAnimator(...)` replace `final GenericDraweeView draweeView = (GenericDraweeView) startValues.view;` with `final GenericDraweeView draweeView = (GenericDraweeView) endValues.view;` (mind the `startValues` -> `endValues` change)
  - use the end drawee instead of the starting one
- in `AnimatorUpdateListener` after the `scaleType.setValue(fraction)` call, insert the following lines:

``` java
Drawable drawable = draweeView.getTopLevelDrawable();

if (drawable != null) {
    drawable.setBounds(0, 0, draweeView.getWidth(), draweeView.getHeight());
}
```

This last piece of code is based on @Aohayou's solution (because I couldn't make `CustomDraweeView` work).
Tested this on Android 5.0.2 with support lib version v24.1.1.

> **NOTE** that this solution won't work if you animate between images with the same sizes.


## sperochon (14 Aug 2016)

@Gericop Here is a demo that it works fine. I've just commit it onto github. I tried to clean the code at maximum. Be careful: I used only 1 image in my recycler view because the transition name has to be different on each item of the recycler view in order to Fresco to work. So, to simplify, I used only 1 image with 1 transition name.
https://github.com/sperochon/DemoFrescoFragment2Fragment

Hope this help!


## gregkorossy (14 Aug 2016)

@sperochon Try it with multiple images.
By the way, I tested your demo with unmodified source. This is the result:

![device-2016-08-14-220810_1](https://cloud.githubusercontent.com/assets/5181621/17651699/d2930272-626c-11e6-8716-6ea020128c66.gif)

This is completely wrong. The end view starts from a different position and the scale type has no effect whatsoever. I don't know how can you say for this that "it works fine" because it clearly doesn't.


## sperochon (14 Aug 2016)

@Gericop I've just updated the code. I forgot to specify a different layout for the end fragment. Try again, please.


## gregkorossy (14 Aug 2016)

@sperochon In API 23 emulator, it works fine. On my device (API 21) it doesn't. Also tested it in an API 21 emulator, it doesn't work there either.
So the point is: the current implementation does not work on API 21 (didn't test it on API 22), but works on API 23.

**Edit**: your demo only tests the default `ChangeBounds` and `ChangeTransform` transitions, not the `DraweeTransition` implementation Facebook supplied.


## sperochon (14 Aug 2016)

Unfortunately, you're right... I've tested it on my devices:
Android 5.0 + Fresco v0.11/v0.12 -> KO
Android 6.0 + Fresco v0.11/v0.12 -> OK
Didn't notice it before...


## ladia12 (16 Aug 2016)

@Gericop @sperochon I have written a [blog post](https://medium.com/pregbuddy-engineering/how-we-integrated-material-motion-with-transforming-material-9c72384053d4#.g23brb9ap) on medium about this. Please see if it helps.


## gregkorossy (16 Aug 2016)

@ladia12 That post has nothing to do with the problem I was (we were) facing... Your blog entry is about _inter-**Activity**_ transition, while my issue is related to _inter-**Fragment**_. Furthermore, the real bug is in how API 21 handles the `ChangeBounds` and/or `ChangeTransform` transitions where the Facebook supplied `DraweeTransition` doesn't help either. My solution, on the other hand, overcomes this, if the source and target images have different dimensions (width and/or height).


## sperochon (20 Aug 2016)

Just for info:
Android 5.0 + Fresco v0.11/v0.12/0.13 -> KO
Android >= 5.1 + Fresco v0.11/0.12/0.13 -> OK


## dbrant (13 Jul 2017)

@ladia12 How did you resolve the issue of the original image disappearing when returning to the first activity?

## kycqdhl3c (16 Sept 2017)

@dbrant I have the same issue, Have you find some way to resolve?

## oprisnik (16 Sept 2017)

See #1446

## ZakAnun (08 Oct 2018)

@ladia12 How did you resolve the issue of the original image disappearing when returning to the first activity?

## bembem1011 (19 Jun 2019)

@ladia12 How did you resolve the issue of the original image disappearing when returning to the first activity? My original image is in recyclerview's viewholder

## ladia12 (21 Jun 2019)

I ended up using Picasso for the shared element transition. It wasn't fixed
in Fresco. So Picasso is lightweight and there isn'y any problem using it
alongwith Fresco.

On Wed, Jun 19, 2019 at 5:11 PM bembem1011 <notifications@github.com> wrote:

> @ladia12 <https://github.com/ladia12> How did you resolve the issue of
> the original image disappearing when returning to the first activity? My
> original image is in recyclerview's viewholder
>
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> <https://github.com/facebook/fresco/issues/22?email_source=notifications&email_token=AAXQ5WYUP5KBJYNBOZX7CCDP3ILM5A5CNFSM4A6ZMH32YY3PNVWWK3TUL52HS4DFVREXG43VMVBW63LNMVXHJKTDN5WW2ZLOORPWSZGODYBSZCI#issuecomment-503524489>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AAXQ5W5EXO5SV4R2PXVTG7LP3ILM5ANCNFSM4A6ZMH3Q>
> .
>


