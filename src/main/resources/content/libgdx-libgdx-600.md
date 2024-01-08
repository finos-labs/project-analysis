#Change SpriteBatch.draw arguments from int to float

Owner: libgdx

Repo: libgdx

Labels: bug 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 05:04:54_

This is a feature request. I'm not experienced with java so I dont know if there are any implications to this.

Currently the arguments: x, y, originX, originY are accepted as int. Their immediate use inside of SpriteBatch .draw have them become float. If working in a space smaller then the actual pixel size of the screen, one might want to pass float values instead. Even if working solely with int, it seems java is not partial to an argument being cast as (int) when it requires (float) so it seems like a win-win

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=9_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 01:59:31_

Will change in the next release (this weekend probably). Spritebatch was originally designed to be used with integer viewports. Given that box2d has a different worldscale i might as well change this. Note however that text will be all fucked up when drawn at none integer positions. So don't complain afterwards :)

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 04:10:14_

**Owner:** badlogicgames  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 18, 2010 05:10:26_

Fixed in SVN. positions can now be passed in as floats.

**Status:** Fixed  


