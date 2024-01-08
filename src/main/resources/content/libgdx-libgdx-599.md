#Include Build Instructions

Owner: libgdx

Repo: libgdx

Labels: enhancement 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 09, 2010 17:41:44_

Is it possible to include build instructions for gdx for losers like me who haven't a clue?

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=8_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 09, 2010 09:34:04_

Building the native side is a bit icky. You will need to have CDT installed for Eclipse. On Windows you'll also need Mingw setup properly, on Linux you usually have your GCC. I'll try to get something out on that matter soon.

**Status:** Accepted  
**Labels:** -Type-Defect Type-Enhancement  


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 09, 2010 09:39:53_

thatd be great, I have CDT installed indeed and MinGW setup but forgot whats involved with those MinGW path deals that crop up. In linux, which im a little more familiar with, I was badgered to death with numerous errors and I ran the other direction

Ill just wait for some build instructions :)
Thanks

Lacking vorbis and a couple other sources, I was excluding them from the build at the time too, so dont know what that does down the line. This was borking on Box2D firstly though. Just mentioning b/c I dont know how modular each piece of gdx is going to be as it matures but itd be nice if it was


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 11, 2010 16:08:15_

It would also be cool to have build instructions for making a gdx.apk that will install com.badlogic.gdx by itself

Also its interesting in that the default install does its own com.badlogic.gdx into the phones data directory. What happens when a game version relies on 0.51 and another relies on 0.8 and has some api changes


## badlogic (14 Sept 2013)

_From [quantumg...@gmail.com](https://code.google.com/u/114308363568845445061/) on June 12, 2010 05:06:47_

Yes that is indeed a problem. Will fix it by including build numbers in the shared lib names in the next release. I gotta get a build script going :)


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 04:10:20_

**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on October 19, 2010 15:50:15_

See Wiki. Good things take time...

**Status:** Fixed  


