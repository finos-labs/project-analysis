#Support separated x and y values for points

Owner: airbnb

Repo: lottie-android

Labels: 

## madsbf (02 Feb 2017)

Currently, Lottie searches for "k" as a direct child of "p". However, in some cases, After Effects animations will have separated keyframes for x and y, resulting in a structure like this:

```
"p": {
    "x": {
        "k": {
        }
    }
    "y": {
        "k": {
        }
    }
}
```

LottieComposition will throw an IllegalArgumentException with the message "Point values have no keyframes", when trying to load the above JSON.

It would be nice if Lottie could support this structure as it seems to be a common approach to use.
Fixing this will also require changes further down the chain, as JsonUtils.pointFromJsonArray will throw an exception as well as it expects at least two values, but the point may now be 1-dimensional and therefore only contain one value.

Let me know, what your thoughts on this are - I might try to fix this myself, but won't waste time on it, if you are already aware of this limitation and are already working on it f.x..

## bodymovin (02 Feb 2017)

In case it helps, it gets exported as
```
"px": {
        "k": {
        },
"py": {
        "k": {
        }
```

## madsbf (02 Feb 2017)

Thanks for the input. As far as I can tell from the source code, this structure would not be supported by Lottie either.

Slightly unrelated to the original issue, but do you know if my designer is on an older version of Bodymovin or possibly After Effects if the file he exports has the structure in my original comment @bodymovin ?

## bodymovin (02 Feb 2017)

Hmm I don't remember having created it with that structure. Can you send me the exported json to my email?

## gpeal (02 Feb 2017)

Yes! This is a feature I've looked in to supporting! I'll add it to my shortlist :) 

In the meantime, you should be able to turn off separate dimensions to work around this. 

## pdenise708 (03 Feb 2017)

K

## gpeal (04 Feb 2017)

@madsbf @pdenise708 @bodymovin I've added support for this in `1.0.2` which I plan to release next week. iOS may take a little longer though.

## gpeal (04 Feb 2017)

Fixed by https://github.com/airbnb/lottie-android/commit/0bace80fc86963e19ad7b17c2bf9127e8b4a48c8

## gpeal (10 Feb 2017)

@madsbf @pdenise708 1.0.2 is now live. Can you try split dimensions? 

## madsbf (13 Feb 2017)

I have verified this is working with 1.0.2 @gpeal ! Thanks for the swift response 👍 

