#Bound nextIndex on seek to be 0 <= nextIndex <= numSegments

Owner: google

Repo: ExoPlayer

Labels: 

## ojw28 (01 Jul 2014)

This helps avoid issues when seeking very close to the start and end of the track, in particular if the seek is to t=0 but the start time of the first segment is very slightly greater than 0.

If this makes sense, bounding should be applied in the following places:

https://github.com/google/ExoPlayer/blob/master/library/src/main/java/com/google/android/exoplayer/dash/DashMp4ChunkSource.java#L174

https://github.com/google/ExoPlayer/blob/master/library/src/main/java/com/google/android/exoplayer/dash/DashWebmChunkSource.java#L155

https://github.com/google/ExoPlayer/blob/master/library/src/main/java/com/google/android/exoplayer/smoothstreaming/SmoothStreamingManifest.java#L200


