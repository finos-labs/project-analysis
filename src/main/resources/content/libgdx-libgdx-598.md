#ContactListener Contact arg contains same Fixture for getFixtureA and getFixtureB

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 09, 2010 05:16:01_

What steps will reproduce the problem? 1. Implement custom ContactListener from svn
2. Inspect getFixtureA and getFixtureB, they are the same object What is the expected output? What do you see instead? A and B are different objects What version of the product are you using? On what operating system? libgdx svn Please provide any additional information below. I filed this earlier but its not showing up anymore, not sure what happened to it, but this is much more concise about the problem.

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=7_


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 08, 2010 20:19:42_

I could add to elaborate. In my example i have a few things going on. setUserData on each body to give it a unique name for the test. There are bounding boxes that surround the world. If the player collides with a bounding box, and then in the ContactListener, both getFixtureA().getBody().getUserData() and likewise B will both return "Player" though one should read "BoundingBox". If I fire a bullet from the player, A and B will read "BoundingBox".


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 08, 2010 20:51:51_

as one would think, its a silly fix, now if i could just shake off these python roots and figure out how to get my feet dirty and the jni rebuilt ..

### Eclipse Workspace Patch 1.0

#P gdx

# Index: jni/Box2D/Contact.cpp

--- jni/Box2D/Contact.cpp   ( revision 302 )
+++ jni/Box2D/Contact.cpp   (working copy)
@@ -86,5 +86,5 @@
   (JNIEnv _, jobject, jlong addr)
 {
    b2Contact_ contact = (b2Contact*)addr;
-   return (jlong)contact->GetFixtureA();
-   return (jlong)contact->GetFixtureB();
  }


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 09, 2010 09:36:11_

Oh sorry, that happens when you code late at night. I'll fix that asap and update the binaries in SVN. Will also release a new version with the fixes. The Box2D wrapper still needs a lot of testing which i don't have time for at the moment so you guys have to suffer a bit :/

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 09, 2010 09:43:34_

It's cool, I dont mind testing anything and fixing basic stuff like this. Just the fact that the majority of this is working is an amazing benefit to me.

My dev'ing just comes to a standing halt from stuff like this b/c im to lame to figure out jni compilation stuff


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 11, 2010 06:27:59_

**Status:** Fixed  


