#Ability to be added as a fragment in existing activity.

Owner: google

Repo: ExoPlayer

Labels: 

## jaydeepw (24 Jul 2014)

Right now it looks like the VideoRenderer is tightly coupled to SimplePlayerActivity. It becomes difficult to migrate the player into a fragment and then reuse it.


## IanDBird (24 Jul 2014)

I'm not entirely sure what you're asking, but from my experience, I simply implemented my own RendererBuilder's based on the example provided in the Demo application. I can then use them in either an Activity or a Fragment against a VideoSurfaceView.


