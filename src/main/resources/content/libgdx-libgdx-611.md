#Prismatic joint bug

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [cpasju...@gmail.com](https://code.google.com/u/107955730618067784412/) on June 28, 2010 20:57:33_

What steps will reproduce the problem? 1. Creating a prismatic joint seems to cause the whole body to go silly. What version of the product are you using? On what operating system? Libgdx 0.52

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=23_


## badlogic (14 Sept 2013)

_From [cpasju...@gmail.com](https://code.google.com/u/107955730618067784412/) on June 28, 2010 11:58:54_

Forget to say that the same joint code works on native box2d and jbox2d.


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 29, 2010 08:41:46_

Probably a fuck up in the Prismjoint JNI binding. Some typo or similar. Looking into it.

**Status:** Accepted  
**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [rtaylor205@gmail.com](https://code.google.com/u/rtaylor205@gmail.com/) on July 25, 2010 02:35:16_

Just had this one sent at my end

Line 48,
localAxis1.set(bodyA.getLocalVector( anchor ));

Should be:
localAxis1.set(bodyA.getLocalVector( axis ));


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on July 25, 2010 02:56:18_

Thanks! Fixed. Verification in a few hours in test bed

**Status:** Fixed  


