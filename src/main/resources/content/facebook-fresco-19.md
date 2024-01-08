#Add a means of exposing the underlying Bitmap

Owner: facebook

Repo: fresco

Labels: 

## ZacSweers (28 Mar 2015)

Right now, the library doesn't appear to make retrieving the underlying bitmap in a Drawee. While this is understandable, it also makes postprocessing impossible for any users that want to do it.

An example would be for use in the Palette library, which requires an input Bitmap for the color extraction.


## tyronen (28 Mar 2015)

Fresco has a [postprocessor](http://frescolib.org/docs/modifying-image.html) facility which sounds like what you are looking for.


