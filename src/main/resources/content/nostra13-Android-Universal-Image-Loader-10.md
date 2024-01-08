#Potencial Memory leaks

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## lAnubisl (08 Dec 2011)

https://github.com/nostra13/Android-Universal-Image-Loader/blob/master/src/com/nostra13/universalimageloader/utils/FileUtils.java

https://github.com/nostra13/Android-Universal-Image-Loader/blob/master/src/com/nostra13/universalimageloader/imageloader/ImageLoader.java (line 190)

Noone of these methods tries to close streams in case of exceptions.


## nostra13 (09 Dec 2011)

Made work with streams more careful.

**Commit cfd2ce6213ccff8f273de39dad3cda4b0b05160d.**


