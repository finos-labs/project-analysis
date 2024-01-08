#Configurable wait() time for input Listener

Owner: libgdx

Repo: libgdx

Labels: enhancement 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 19, 2010 07:23:20_

Feature Request (hate not being able to select that)

I feel this is an issue since libgdx is pretty much all about the opengl. Touch events can hammer your framerate. In some cases maybe its not noticable, in others its very noticable. I solved this before I rewrote my game to solely use the facilities of libgdx, now coming back to it...

reference this thread for a discussion about it, http://groups.google.com/group/android-developers/browse_thread/thread/39eea4d7f6e6dfca The solution is simple enough but I dont think theres a one-size-fits-all here. For me, in AndroidInput.onTouch for MotionEvent.ACTION_MOVE I added an eventQueue.wait(40L); which seems to suit my own game just fine. The default behaviour makes my game rather unplayable due to its fast nature.

Maybe worth some consideration; providing a mechanism to throttle Listener events

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=19_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 19, 2010 03:14:07_

I'm well aware of the problem. Instead it is a bit more complicated even. See http://apistudios.com/hosted/marzec/badlogic/wordpress/?p=537 . The sleep alone does not solve all problems, especially when one of your heavy duty threads is doing a shitload of work. I'm also a bit baffled by the fact that you run a 2.0.1 ROM on your device and still get it. The situation has become a bit better with the 2.x releases and we were hoping that this would also work on 1st gen devices. Seems like it doesn't :/. 

Anyways, i'll add a second AndroidApplication.initialize() method which allows you to specify a sleep time for the input event handler.

**Status:** Accepted  
**Owner:** badlogicgames  
**Labels:** -Type-Defect Type-Enhancement  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 21, 2010 16:18:18_

fixed, in SVN. You can now specify the number of milliseconds to be slept in the touch event handler. Use the overloaded AndroidApplication.initialize() method to specify the sleep time in milliseconds. When using the old initialie() method no sleeping is performed.

**Status:** Fixed  


