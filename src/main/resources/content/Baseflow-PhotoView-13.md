#Fully zoomed image scroll and viewpager flip not smooth

Owner: Baseflow

Repo: PhotoView

Labels: 

## httpdispatch (03 Dec 2012)

Just tried you demo and it is very impressive. Can you please confirm such issue.
Viewpager demo:
- Zoom image fully (better in landscape)
- scroll it to the right (not till the end but leave some space suppose 100 dp)
- put the thinger to the right part of the screen and start to scroll to the left so the viewpager flip will be activated once you scroll all the image.
- When the image fully scrolled and viewpager starts to flip view jumps to the left for a 100 or so dp. So the viewpager work become not so smooth as in the native gallery application.

My system is ICS 4.0.3


## chrisbanes (03 Dec 2012)

You're correct, I'll see if I can find out why now.


## httpdispatch (03 Dec 2012)

Did you think about canScroll solution proposed here? https://github.com/sephiroth74/ImageViewZoom/pull/10


## httpdispatch (03 Dec 2012)

Taking my words about canScroll solution back. It works worse than your solution.


## chrisbanes (03 Dec 2012)

I have looked at a similar solution, but agreed it's not optimal. I'll see if I can use both.


## httpdispatch (04 Dec 2012)

Any glues how to fix this?


## chrisbanes (04 Dec 2012)

The problem is that even when it's been set to `requestDisallowInterceptTouchEvent(true)`, ViewPager still updates it's internal X co-ordinates from the touch event.

So when PhotoView is on the edge, and lets ViewPager continue the scroll (by calling `requestDisallowInterceptTouchEvent(false)`), ViewPager thinks it's been scrolled already.

I need to have a think of how to fix it nicely.


## httpdispatch (20 Mar 2013)

@chrisbanes what commit does fix it?


## httpdispatch (25 Mar 2013)

@chrisbanes I've checked latest dev version of the PhotoView and this is still not fixed. Can you please reopen?


