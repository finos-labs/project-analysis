#Samsung Galaxy 1.5 - Black screen instead hello world app

Owner: libgdx

Repo: libgdx

Labels: bug wontfix 

## badlogic (14 Sept 2013)

_From [Mariusz....@gmail.com](https://code.google.com/u/103927374618490518049/) on April 19, 2010 13:35:53_

Hello,

i'm using Samsung Galaxy with standard Android 1.5 and when i'm running the 
hello world (without any modifications) app i see only black screen instead 
of the graphic and text. Do you have any idea whan can cause the problem or 
do you have any solution for that? On emulator and desktop everything is OK. 
I would appreciate any help/guidlines where to look to fix that

best regards
Mariusz

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=1_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on May 08, 2010 14:35:49_

Hi,

sorry for getting back to you so late, i completely overlooked the tracker. The
Galaxy has problems with the VBO. I assume you run vanilla 1.5 and not cyanogen or
something like it right? I'll have to add a check in the Mesh class so that on the
Galaxy vertex arrays are used. Could you tell me what the android.os.Build.MODEL
string returns for the Galaxy? Also, what version of libgdx did you use? 4.0 did not
work properly on 1.5 devices, i fixed it in the latest build.

Ciao,
Mario


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on May 26, 2010 14:22:32_

**Status:** Accepted  
**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 11, 2010 06:34:17_

Can't reproduce

**Status:** WontFix  


