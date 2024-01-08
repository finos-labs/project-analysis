#OutOfMemoryError when loading a ludicrously huge JSON animation on a Galaxy S3

Owner: airbnb

Repo: lottie-android

Labels: 

## subtleGradient (02 Feb 2017)

JSON is 1.5 MB uncompressed, 94 KB zipped.
Lottie should probably switch from JSON to Flatbuffers. I'll open a separate issue for that.

## gpeal (02 Feb 2017)

@subtleGradient Can you attach the AE file? There may be some things we can do in the meantime to reduce overhead.

## gpeal (09 Feb 2017)

@subtleGradient so I spent way too many hours this week migrating over the JsonReader. However, the existing json structure of bodymovin makes it *incredibly* inconvenient unfortunately. I'm going work with Hernan from bodymovin to figure out a good solution here.

## gpeal (09 Jan 2018)

@subtleGradient Only took a year but I finally got this working :)

