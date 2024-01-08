#Aspect ratio as display image option?

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## cgerold (17 Jan 2012)

First of all thanks for sharing this nice stuff :)

I'm trying to implement the image loader into a gridview.
Therefore I need the images to be cropped to a aspect ratio of 1:1.

Is it possible to do this with your current state of code?


## nostra13 (17 Jan 2012)

Thanks :)
ImageView has a parameter **android:scaleType** which can have next values: matrix, fitXY, fitStart, fitCenter, fitEnd, center, **centerCrop**, centerInside. Did you try to apply **centerCrop** to your ImageViews? Or this is not suitable for you?


## cgerold (17 Jan 2012)

You made my day :)

I see its not needed to be implemented to your library, thanks a lot!!


