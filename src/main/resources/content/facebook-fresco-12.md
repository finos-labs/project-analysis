#RoundingParams.setBorder(..) does not work

Owner: facebook

Repo: fresco

Labels: 

## azfarsiddiqui (27 Mar 2015)

I am not able to show a border to an image. Neither through code, nor XML.

```
    GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
    RoundingParams roundingParams = RoundingParams.fromCornersRadius(25.0f).setBorder(android.R.color.holo_red_dark, 2.0f);
    GenericDraweeHierarchy hierarchy = builder
            .setFadeDuration(1000)
            .setRoundingParams(roundingParams)
            .setPlaceholderImage(getResources().getDrawable(R.drawable.ic_launcher))
            .build();

    imageView.setHierarchy(hierarchy);
    imageView.setImageURI(uri);
```


## IanChilds (27 Mar 2015)

Do you see the rounded corners correctly? Is it just the border that is the problem? 


## plamenko (27 Mar 2015)

Also, if you add .setOverlayColor(0xFFFFFF00) while building RoundingParams, what happens? This will help us diagnose where the issue might be.


## azfarsiddiqui (28 Mar 2015)

@IanChilds, I was able to round the image with the code I provided. I am currently only able to circle an image by using:

RoundingParams.asCircle()

@plamenko, If I use setOverlayColor() in conjunction with RoundingParams.asCircle(), the rectangular area holding the circle is painted using that overlay color. However, that color is not drawn over the actual image itself. 

if I use setOverlayColor in conjunction with RoundingParams.fromCornersRadius(), there is no effect at all, as the image is itself rectangular therefore no area is left to be filled by the color.


## azfarsiddiqui (28 Mar 2015)

Alright, I think i've narrowed down the problem. The setBorder() method seems to ignore android color references. For example If I run the following, I don't get expected result:

```
    RoundingParams roundingParams = RoundingParams.fromCornersRadius(25.0f).setBorder(android.R.color.holo_blue_bright, 5.0f);
```

However, when I remove the color reference and provide the color value directly, it works as expected:

```
    RoundingParams roundingParams = RoundingParams.fromCornersRadius(25.0f).setBorder(0xff00ddff, 5.0f);
```


## plamenko (28 Mar 2015)

Yes, this should be ARGB color, not a resource reference. Both of those use `int` type, and it makes more sense to interpret it as a color than as a resource id.


## plamenko (28 Mar 2015)

You can always get the color value from the resource id with `getResources().getColor(R.color.holo_blue_bright)`
I'm glad that you resolved the issue.


## azfarsiddiqui (28 Mar 2015)

Thanks a lot @plamenko.. 


