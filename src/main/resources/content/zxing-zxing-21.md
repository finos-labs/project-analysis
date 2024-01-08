#PDF417 needs error correction

Owner: zxing

Repo: zxing

Labels: 

## srowen (18 Jan 2014)

This is a to-do reminder that PDF 417 really needs to finally implement error correction. It's not Reed-Solomon, quite. I will commit some pieces that will help so far but will have to work on this a while to understand and implement it.

Ported from https://code.google.com/p/zxing/issues/detail?id=1169


## angelnar87 (15 Mar 2014)

Sean, huge fan of zxing!
I was wondering if pdf417 still needs error correction.. also... how can I go about proposing a change in the pdf417 scanning decoder??


## srowen (16 Mar 2014)

Yes, it basically does error correction, but not erasure. I know, the latter should be easier. I forget what the issue was now but I just could not figure out why the implementation didn't get erasures. So it has error correction, but suboptimal.


## angelnar87 (24 Mar 2014)

So.. I messed around with ErrorCorrection and changed it to use Berlekamp–Massey as opposed to the Euclidean algorithm. It seems to work well with errors and erasures so that would remove the need to use the ambiguous values list. Would you be interested in incorporating this? I'm not very experienced in working with open source projects :S


## srowen (24 Mar 2014)

Well if it works it works. Is Berlekamp Massey really a replacement for the
simple Euclidean algorithm though? If deviating from the spec, it would be
important to have some good unit tests in place to detect whether it's
working. The test images to date do not exercise the EC much. If you've got
the time and energy, this alone would be a great contribution. And if there
is then a change in the code that provably improves the results, that's
great.

On Mon, Mar 24, 2014 at 3:04 PM, angelnar87 notifications@github.comwrote:

> So.. I messed around with ErrorCorrection and changed it to use
> Berlekamp–Massey as opposed to the Euclidean algorithm. It seems to work
> well with errors and erasures so that would remove the need to use the
> ambiguous values list. Would you be interested in incorporating this? I'm
> not very experienced in working with open source projects :S
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/zxing/zxing/issues/21#issuecomment-38455074
> .


## graug (24 Mar 2014)

This has been on my to do list for a long time. I'd definitely be interested in seeing this. I don't think that integration is straight forward as we need to decide when it's best to guess a value vs leaving the value empty. As we can have twice as many missing values compared to incorrect values, we might want to gather some experience first, when to chose which. I have quite a few samples which do exercise error correction quite a lot (can't share them, unfortunately).


## graug (24 Mar 2014)

@srowen It shouldn't really matter which algorithm we use. The papers I found about both algorithms show that they produce the same results. More test cases for error correction and especially for missing values would be much appreciated. I might be able to produce a few myself, once the code is there.


## angelnar87 (24 Mar 2014)

Berlekamp Massey is just another way to calculate the error polynomial including the missing values. its the one i was able to get my hands on and understand. and yes!, integration is not really straight forward as graug stated... maybe we can use some sort of threshold on the value count so that if it has more than a couple of ambiguous values, then it becomes an erasure...


## angelnar87 (24 Mar 2014)

What's needed to compile zxing?? since moving to java 7 i can no longer use eclipse (I haven't really devoted time to research this)


## srowen (24 Mar 2014)

It compiles fine via Maven, which is the build system for the project.
IntelliJ has no problem integrating with it. Is it some Eclipse problem? I
strongly recommend IntelliJ over Eclipse anyway, but, I don't see any
reason the project shouldn't compile with anything that uses Maven.

On Mon, Mar 24, 2014 at 3:26 PM, angelnar87 notifications@github.comwrote:

> What's needed to compile zxing?? since moving to java 7 i can no longer
> use eclipse (I haven't really devoted time to research this)
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/zxing/zxing/issues/21#issuecomment-38457740
> .


## graug (24 Mar 2014)

I haven't tried it recently, but I don't see a reason why it shouldn't work with eclipse and java7. What kind of error are you encountering?


## angelnar87 (24 Mar 2014)

Oh... its not a big deal.. its mostly about the android sdk not being compatible with java 7. I've heard of IntelliJ so I got my hands on Android Studio.. I just need to move my projects from Eclipse to "anything that will allow me to use java 7" ...


## srowen (24 Mar 2014)

The Android SDK is most certainly compatible with Java 7. Example: zxing :)
Or else, how am I releasing Android apps using Java 7 now?

On Mon, Mar 24, 2014 at 3:58 PM, angelnar87 notifications@github.comwrote:

> Oh... its not a big deal.. its mostly about the android sdk not being
> compatible with java 7. I've heard of IntelliJ so I got my hands on Android
> Studio.. I just need to move my projects from Eclipse to "anything that
> will allow me to use java 7" ...
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/zxing/zxing/issues/21#issuecomment-38462215
> .


## angelnar87 (24 Mar 2014)

Black magic at its finest my friend!  ... I will try to check out my changes in the next couple of days


## angelnar87 (26 Mar 2014)

So, I'm trying to push my changes but I'm getting a 403 error.... any clues?? I've tried to solve this but I haven't gotten anywhere... :(


## srowen (26 Mar 2014)

Push changes to this repo? no you can't do that. You can push changes to your fork of this repo and open a pull request though.


## angelnar87 (26 Mar 2014)

gotcha!


