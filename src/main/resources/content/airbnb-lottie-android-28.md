#Is this a Facebook Keyframes clone?

Owner: airbnb

Repo: lottie-android

Labels: 

## ber4444 (01 Feb 2017)

Very similar to https://github.com/facebookincubator/Keyframes, at least you should explain why do we need a new project? What is it that you are improving?

## gpeal (01 Feb 2017)

We do actually mention Keyframes and the differences in the [blog post](https://medium.com/airbnb-engineering/introducing-lottie-4ff4a0afac0e#.qm73q4xek) and [readme](https://github.com/airbnb/lottie-android#alternatives).

Keyframes was built primarily for reactions and includes the After Effects feature set that they needed for reactions. We set out to support as much of After Effects as possible. Currently Lottie supports trim paths, dash patterns, masks, mattes, more time interpolations, different kinds of shapes, and a few things that Keyframes doesn't. Keyframes currently supports gradients and Lottie doesn't. However, that's something we're looking at adding. We also have a laundry list of After Effects features that we'd like to support going forward.

Both projects were started independently and in parallel. We started ours in 2015 and Facebook started to build Reactions. We think it's awesome that Facebook saw the same need as us and we're looking forward to learning from each other's discoveries and collaborating to make the entire ecosystem better.

