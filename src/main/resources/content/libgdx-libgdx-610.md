#FixtureDef filter.groupIndex bug

Owner: libgdx

Repo: libgdx

Labels: bug wontfix 

## badlogic (14 Sept 2013)

_From [cpasju...@gmail.com](https://code.google.com/u/107955730618067784412/) on June 28, 2010 20:53:31_

What steps will reproduce the problem? 1. Define a fixtureDef groupeIndex do not seems to work, resulting in a fixture that will never collide. This make fixturedef unusable. What version of the product are you using? On what operating system? Libgdx 0.52

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=22_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 29, 2010 08:40:56_

Hm, i'll be looking into it tomorrow.

**Status:** Accepted  
**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [cpasju...@gmail.com](https://code.google.com/u/107955730618067784412/) on June 29, 2010 09:28:09_

I did look again at it, and i was sucessfully able to use it. There is a difference tought with stock box2d/jbox2d (it seems). I had to set my ground fixture def and collide bodys groupeIndex to 1 then it work, while with jbox2d/box2d the ground groupeIndex is not set and other collide body are set to -1.
Hope it help.


## badlogic (14 Sept 2013)

_From [cpasju...@gmail.com](https://code.google.com/u/107955730618067784412/) on June 29, 2010 10:45:05_

Sorry for the spam ... would it be possible to get a mail address ? I'd like to send you a code i wrote. It don't react the same way on the computer and on the phone. I'v wrote a motorcycle code, it run very well on the computer side, but it does react strange on the phone. The wheel (RevoluteJoint) is not solid to the chassis. Can't explain very well :)


## badlogic (14 Sept 2013)

_From [cpasju...@gmail.com](https://code.google.com/u/107955730618067784412/) on June 29, 2010 13:52:04_

My last problem seems to be related to a fixture "restitution" parameter bug on the android side. A low value will cause the body to bug when it is in contact with an oriented shape while a high value will of course make it too "elastic".


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on July 15, 2010 03:26:54_

I modified the Box2DTest like this: ground fixture has groupIndex 0, all boxes have group index set to -1. Boxes don't collide with each other but only with the ground. I guess without a sample i can't reproduce your problem. I set this to WontFix in the meantime, if you happen to be able to provide me with a test case i'll reopen it.

Concerning your "restitution" problem i again would need a sample. As we discussed seperately it is connected to you not using a fixed time step, which i could reproduce with a slow software OpenGL renderer on my desktop.

**Status:** WontFix  


