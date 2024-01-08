#Add callbacks for when a Drawee view is ready

Owner: facebook

Repo: fresco

Labels: 

## ZacSweers (28 Mar 2015)

Forgive me if there is something for this already and I just missed it. It's often convenient to postpone some action until after the image is done loading. Examples include showing and dismissing a progress dialog, generating color extraction (which would go in tandem well with #19), etc.


## tyronen (28 Mar 2015)

You can do this with [ControllerListeners](http://frescolib.org/docs/listening-download-events.html).


