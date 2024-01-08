#SpriteBatch.enableBlending/disableBlending needs to flush the cache

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 13:09:55_

spriteBatch.begin();
spriteBatch.draw();
spriteBatch.disableBlending(); <-- nuuu, last draw will also be without blending
spriteBatch.draw();
spriteBatch.end();

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=15_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 18, 2010 05:20:24_

Fixed in SVN

**Status:** Fixed  


