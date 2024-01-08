#[box2d] groupIndex on fixtureDef.filter not functioning correctly

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 08, 2010 06:19:24_

What steps will reproduce the problem? 1. Create two seperate bodies
2. Create fixtureDef's and set .filter.groupIndex = -8
3. createFixture's on each body
4. Watch them still collide What is the expected output? What do you see instead? setting the fixture's groupIndex for two bodies to the same negative number is suppose to prevent the bodies from colliding. Setting it for this jni wrapper appears to do nothing. What version of the product are you using? On what operating system? libgdx0.5

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=6_


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 08, 2010 20:02:20_

Not knowing much about java or jni stuff, this is still easily solvable for a noob like me by simply reimplementing the ContactFilter class from the cpp source since its not complex


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 08, 2010 20:22:24_

I noticed in the jni cpp file there was a shouldCollide = 0; and then in the default implementation there was a if (shouldCollide != 0) { process.stuff } and didn't see a way to set the var being checked otherwise.


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 09, 2010 09:38:06_

I have not yet looked into the group filtering stuff myself. I see what i can do this weekend. Sorry for the inconvenience.

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 11, 2010 06:37:33_

The shouldCollideID var in the CustomContactFilter has nothing to do with collision filtering actually. It's the id of the method to be invoked on the Java side to do the collision filtering. Still looking into this.


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 11, 2010 07:00:52_

Fixed by implementing the base method of b2ContactFilter in Java, see World.java::contactFilter.

**Status:** Fixed  


