#ImageResizer requires two InputStreams

Owner: bumptech

Repo: glide

Labels: enhancement 

## sjudd (16 Jul 2013)

This hasn't been a problem for us internally since we typically are loading images from files and so only have to open a second InputStream. It is however a more substantial problem for users who might want to load images via http where two separate calls would be required.

We originally required two InputStreams because using mark and reset on a BufferedInputStream to determine the size would occasionally throw a "Mark has been invalidated" IOException. 

Having determined the reason why occurred so frequently (see https://code.google.com/p/android/issues/detail?id=57578&q=BitmapFactory&colspec=ID%20Type%20Status%20Owner%20Summary%20Stars and), we should be able to revert back to using a single InputStream. 


## sjudd (18 Jul 2013)

Fixed in one_input_stream branch


