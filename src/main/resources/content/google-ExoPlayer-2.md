#Support wider range of DASH manifests

Owner: google

Repo: ExoPlayer

Labels: 

## ojw28 (23 Jun 2014)

Currently ExoPlayer only supports a narrow subset of possible DASH manifests. Support should be added for manifests containing segment lists and segment templates.

Note: As a workaround, application developers can already support arbitrary DASH manifests through implementing their own parser and ChunkSource (it's probably useful to use DashMp4ChunkSource as a starting point).


## ojw28 (09 Jul 2014)

There are a whole bunch of test mpds here:
https://github.com/Dash-Industry-Forum/dash.js/blob/master/app/sources.json


## droidmax (11 Jul 2014)

Any recommendation for supporting multiple periods in MPD? Would be helpful for implementing Ad insertion using only one instance of player. Is it possible to add multiple DASH video chunk sources to the renderer and seek between the chunk sources?


## rogerjstringer (16 Jul 2014)

Is there a MPD file which current works with ExoPlayer, even the 'dev' branch?   

I've tested a number of the sample MPD files provided by the sources.json file in dash.js, files which play with dash.js but all fail with ExoPlayer.   The current problem appears to be in the processing of the XML-format MPD file.

Example MPD files which should play are:
http://dash.edgesuite.net/digitalprimates/fraunhofer/480p_video/heaac_5_1_with_video/Sintel/sintel_480p_heaac5_1.mpd
http://www.digitalprimates.net/dash/streams/mp4-live-template/mp4-live-mpd-AV-BS.mpd
http://dash.edgesuite.net/digitalprimates/fraunhofer/480p_video/heaac_2_0_with_video/ElephantsDream/elephants_dream_480p_heaac2_0.mpd
http://yt-dash-mse-test.commondatastorage.googleapis.com/cenc_samples/oops-20131021-DASH_FMP4_H264_1080P_CENC.mp4
http://dash.edgesuite.net/dash264/TestCases/1a/netflix/exMPD_BIP_TC1.mpd  

NB:  I'm testing on KitKat


## ojw28 (16 Jul 2014)

- The first and third of those mpd links already play for me on the dev branch.
- The second one requires SegmentTemplate support. I have this working locally, and it will be pushed next week. Stay tuned. Ditto for any mpds that contain SegmentList elements.
- The fourth link isn't an mpd.
- I'm not sure what to do about the fifth one yet. That style of mpd neglects to define a segment index (either by specifying where the sidx is located in the stream, or by defining an index in the manifest itself). You end up having to make an unbounded request from the head of each stream until you find the index. That doesn't seem ideal, and I'm not sure we'll be supporting that case.

Thanks!


## rogerjstringer (16 Jul 2014)

OK, I concur on #1 once I fiddled with the parameters:  
 new Sample("DASH-Sintel 480p", "uid:misc:sintel480",  
         "http://dash.edgesuite.net/digitalprimates/fraunhofer/480p_video/heaac_5_1_with_video/Sintel/sintel_480p_heaac5_1.mpd",  
         DemoUtil.TYPE_DASH_VOD, false, true),

Sorry for the misleading comment  


## ojw28 (16 Jul 2014)

Np. The big missing piece here at the moment is support for SegmentTemplate and SegmentList, both of which are fairly commonly used (as in the second mpd). This will be fixed very soon. 


## ojw28 (18 Jul 2014)

Support for SegmentTemplate and SegmentList have been merged into both dev and master.


## IanDBird (22 Jul 2014)

Thanks for the update to include support for SegmentTemplate and SegmentList. I've been testing it out over the last day or two and it works beautifully!


## hafidben (04 Feb 2015)

Do you have an example of an MPD file with segmentList ?


## ojw28 (04 Feb 2015)

You could try: http://www-itec.uni-klu.ac.at/dash/ddash/mpdGenerator.php?segmentlength=6&type=full


