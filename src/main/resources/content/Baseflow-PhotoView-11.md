#Horizontal multi-touch doesn't work with ViewPager

Owner: Baseflow

Repo: PhotoView

Labels: 

## cachapa (23 Nov 2012)

In the sample app, with the ViewPager an horizontal multi-touch gesture is very difficult to accomplish.
If the two fingers are vertical to each other, then it's easy. I suppose that the ViewPager is "stealing" any horizontal drags.

Perhaps a simple solution would be to disable the pager when two fingers are detected.


## chrisjenx (29 Nov 2012)

I would like to point out it does work, but yes is tricky, I'll see if I can alture the view pager to stop intercepting when two fingers are on the screen. I'll work on it in my fork.


## DazzyG (07 Jan 2013)

I'm seeing this issue in my own application as well. The ImageView is in a FragmentPagerAdapter. two finger zooming on the vertical works fine but crashes with the following on the horizontal. http://pastebin.com/BDdVqNu9


## chrisbanes (07 Jan 2013)

@DazzyG: That is #4 


## DazzyG (08 Jan 2013)

@chrisbanes I thought #4 was fixed in fbe7598?


## chrisbanes (08 Jan 2013)

@DazzyG: What branch are you using? fbe7598ffdbdc40aa92590e079fcca7b9fdaf7d9 is in dev.


## DazzyG (08 Jan 2013)

@chrisbanes I'm using the dev branch.


## AwesomelyAmazing (21 Jan 2013)

I also caught an "pointerIndex out of range" when using dev branch.
Seems to happen only when you zoom out as much as possible.


## robUx4 (13 Mar 2013)

I also have this problem with the dev branch and latest support library.

If I patch the support library like this in MotionEvent.ACTION_MOVE, it works:

```
             if (pointerIndex == INVALID_POINTER) {
                 // If we don't have a valid id, the touch down wasn't on content.
                 break;
             }

             final float x = MotionEventCompat.getX(ev, pointerIndex);
```

Seems like it's using an id (1 instead of 0 in normal case) of the event that it can't handle. I have similar crashes in other libs too, so I suspect the issue is in the support library.


## chrisjenx (13 Mar 2013)

Yeah there is a bug in the support lib, I think fixes have been pushed but
no accepted, which is a shame as the r12 was meant to fix a lot of the
ViewPager issues.

On 13 March 2013 10:39, Steve Lhomme notifications@github.com wrote:

> I also have this problem with the dev branch and latest support library.
> 
> If I patch the support library like this in MotionEvent.ACTION_MOVE, it
> works:
> - if (pointerIndex == INVALID_POINTER) {
> - // If we don't have a valid id, the touch down wasn't on content.
> - break;
> - } final float x = MotionEventCompat.getX(ev, pointerIndex);
> 
> Seems like it's using an id (1 instead of 0 in normal case) of the event
> that it can't handle. I have similar crashes in other libs too, so I
> suspect the issue is in the support library.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/chrisbanes/PhotoView/issues/11#issuecomment-14834393
> .


## smarek (14 Sept 2013)

I'm closing this issue, as I don't see any more complains from you guys. This issue is bound to Support v4 library, which is now r18 (6 new updates), I suppose this is fixed now, if not, reopen this issue or create new one. Thanks


