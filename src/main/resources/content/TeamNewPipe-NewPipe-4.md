#[Feature request] background pause and play

Owner: TeamNewPipe

Repo: NewPipe

Labels: 

## daniell1 (10 Sept 2015)

I like the app very much already. Thank you very much :+1: 

Though I still like to request a feature that allows pausing or playing videos in background. Currently videos stop playing completely if I switch to another app and videos start again from beginning after switching back to NewPipe. It would be nice if videos paused or played in background. Best would be if it was possible to select one out of both options - pause or play in background - in settings menu.


## theScrabi (10 Sept 2015)

That the video is not pausing is a bug :/ (https://github.com/theScrabi/NewPipe/issues/8). Also I guess you want a "listen to music" only, and "watch video" only function. I will add a "Listen" action to the VideoInfo view, witch will only play an audiostream and make the GUI behave differently. Here is an interesting link about that topik:
http://stackoverflow.com/questions/10282908/how-can-i-stream-only-the-sound-of-a-video-from-youtube


## MiningMarsh (11 Sept 2015)

An existing workaround that works is to install VLC, and then tell new pipe to use an external video player, then choose VLC as the player. Since VLC supports background playing, you can now switch apps while still listening to the video, and also pause/play the video using the VLC notification while in other apps.


## theScrabi (21 Sept 2015)

All right with one of the last commits this issue is solved :)


