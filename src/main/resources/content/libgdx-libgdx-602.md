#Pass projection matrix to SpriteBatch.begin

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 05:38:24_

Feature Request. OrthographicCamera is a pretty nifty class that does alot of nice stuff. If it had a .getProjMatrix() method and SpriteBatch .begin had a constructor that accepted Matrix projMatrix, then one could easily utilize what's already setup with camera to display sprites in the same viewport if so desired.

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=11_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 02:04:26_

OrthographicCamera has a method to give you combined projection & modelview matrix. See http://code.google.com/p/libgdx/source/browse/trunk/gdx/src/com/badlogic/gdx/graphics/OrthographicCamera.java#332 . 

Having that said your suggestion makes sense and will be included in the next release.


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 02:04:42_

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 04:10:08_

**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 09:31:24_

yeah, matrices are really beyond me at the moment, will have to sit down for a day and play. I tried passing the combinedMatrix (not really knowing what it was precisely) to spriteBatch.begin but it didn't produce the desired result. I guess this would keep the constructors for begin simple too, so sounds great!


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 18, 2010 05:17:09_

Fixed in SVN.

**Status:** Fixed  


