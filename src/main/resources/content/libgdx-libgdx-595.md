#Eclipse crash with 0.5 libs

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [tonyo&#37;sk...@gtempaccount.com](https://code.google.com/u/102183875732111856184/) on June 05, 2010 05:53:12_

What steps will reproduce the problem? 1. Create Android project in Eclipse
2. Add the libraries to the project 

Eclipse build id: 2010-02-18-1602 What is the expected output? What do you see instead? No output expected.  I see the compiler griping: "warning: Ignoring
InnerClasses attribute for an anonymous inner class that doesn't come with
an associated EnclosingMethod attribute. (This class was probably produced
by a broken compiler.)", eating 350MB of RAM and entirely eating one processor. What version of the product are you using? On what operating system? 0.5, Win7, Eclipse build id above. Please provide any additional information below. Nothing in the project was modified but to add the libraries and adding
those to the class path.  Please help me understand where I went wrong.

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=4_


## badlogic (14 Sept 2013)

_From [tonyo%sk...@gtempaccount.com](https://code.google.com/u/102183875732111856184/) on June 04, 2010 21:04:01_

Please close this.  This is an issue with Eclipse trying to load every library on my
_()&_$# machine.


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 09, 2010 09:32:20_

**Status:** Fixed  


