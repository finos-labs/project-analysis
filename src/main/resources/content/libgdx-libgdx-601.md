#Seperate OriginX and OriginY from SpriteBatch.draw

Owner: libgdx

Repo: libgdx

Labels: bug wontfix 

## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 05:23:46_

Another feature request. OriginX and OriginY are currently set in the draw method of SpriteBatch . I get mostly unexpected results as what I pass in is dependent on other factors like scale. I have yet to get a rotation on the origin point to work correctly (my own shortcoming im sure).

Another library I used for opengl was pyglet, and when creating a Texture, you would set the origin point on the texture object. This was via an anchor_x and anchor_y which maybe is not the same concept as origin?. But in this case, it would make sense to be able to set an origin/anchor on the Texture object and this would be referenced for calculations like rotation.

The benefit to this is one calculates the origin of the texture simply based on its pixel size, so if i have a 32x32 texture and i set the origin to (16, 16) then its pretty obvious what should happen when applying various transformations during draw.

_Original issue: http://code.google.com/p/libgdx/issues/detail?id=10_


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 02:02:07_

Spritebatch is not meant to draw whole textures (at least not in the common case) but rather parts of a texture. This is known as a texture atlas. It does not make sense to specify an origin for the whole texture in that regards. 

I have a look into pyglet. Accepted for now, but i have yet to decide whether i want to change that.

**Status:** Accepted  


## badlogic (14 Sept 2013)

_From [badlogicgames](https://code.google.com/u/badlogicgames/) on June 17, 2010 04:08:55_

I looked up pyglet, and while the anchor concept is similar to the origin concept in libgdx it is not equivalent. Please have a look at http://code.google.com/p/libgdx/source/browse/trunk/gdx-tests/src/com/badlogic/gdx/tests/SpriteBatchRotationTest.java which demonstrates the concepts of position, origin, rotation and scale. I'm afraid i won't fix this behaviour.


## badlogic (14 Sept 2013)

_From [dasac...@gmail.com](https://code.google.com/u/105164384981209237729/) on June 17, 2010 09:28:48_

Yeah i completely forgot about atlas textures, and pyglet has a seperate textureatlas. Texture here is doing the whole job which is probably alot more common for phone. Bad suggestion, will try and nail down my failure today to see if theres anything worth mentioning from it


