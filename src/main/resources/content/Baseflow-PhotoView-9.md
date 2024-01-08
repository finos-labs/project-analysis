#Attaching PhotoAttacher before setting Bitamp causes ImageView to display image incorrectly 

Owner: Baseflow

Repo: PhotoView

Labels: 

## mimminito (22 Nov 2012)

I am currently using the BitmapFun (Google) ImageCache system inside my app, along with the PhotoView library.

Using these together, when I attach the PhotoAttacher to the ImageView, the Bitmap (when loaded) appears in the top left of the ImageView unti a gesture is recognised, upon which the ImageView displays the Bitmap correctly.


## chrisbanes (22 Nov 2012)

Did you call update() on the PhotoViewAttacher after the image has been loaded?


## mimminito (22 Nov 2012)

Hmm, ill see if I can get this implemented with the current system (need to find a callback).


## chrisbanes (22 Nov 2012)

I'll see there's a way if I can implement this automatically, for now though you'll need to call update so that it knows to recalculate the correct Matrix.


## mimminito (22 Nov 2012)

Thats brilliant thanks. Ill see if I can implement a callback for now, and then update the attacher. Your welcome to close if this is the correct fix.


## chrisbanes (23 Nov 2012)

That is the correct fix, but I'll see if I can do it automatically.


## chrisbanes (27 Nov 2012)

Doesn't look like I can do this automatically so I'll close this for now.


