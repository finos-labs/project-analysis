#Infinite loop in android audio device

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [meurant....@gmail.com](https://code.google.com/u/108052645462476761281/) on May 25, 2010 09:18:58_

Hi !

I think there is a little problem on
gdx-backend-android/src/com/badlogic/gdx/backends/android/AndroidAudioDevice.java

The writeSamples can lead to an infinite loop, I don't how to post a 
tiny-patch, so I attach it here.

Thanks for your time. :)

Olivier.

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=3_


## badlogic (14 Sept 2013)

_From [quantumg...@gmail.com](https://code.google.com/u/114308363568845445061/) on May 26, 2010 14:20:20_

woah, awesome! Thanks for the patch! I'll do another release with your fix this weekend.


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on May 26, 2010 14:21:38_

**Status:** Started  
**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on May 27, 2010 05:50:11_

**Status:** Fixed  


