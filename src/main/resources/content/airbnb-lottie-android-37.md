#Trying to use LottieDrawable: animation doesn't start

Owner: airbnb

Repo: lottie-android

Labels: 

## BoD (02 Feb 2017)

Hi!
I just tried Lottie and I could make `LottieAnimationView` work with no problems.

However, I cannot make `LottieDrawable` work, and I wonder what I am doing wrong.

Here is what I do:
```
        LottieDrawable lottieDrawable = new LottieDrawable();
        mMainBinding.imgLottie.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mMainBinding.imgLottie.setImageDrawable(lottieDrawable);
        LottieComposition.fromAssetFileName(this, "TwitterHeart.json", (composition) -> {
            lottieDrawable.setComposition(composition);
            lottieDrawable.loop(true);
            lottieDrawable.playAnimation();
        });
```

The result is I only see the first frame - the animation doesn't start.
I looked at the source of `LottieAnimationView` to compare, and didn't see any major difference.
Any idea?

## gpeal (02 Feb 2017)

If you can attach a sample file, I'll investigate. 

## BoD (02 Feb 2017)

Here is a small project demonstrating the problem:
[LottieTest.zip](https://github.com/airbnb/lottie-android/files/748621/LottieTest.zip)


## gpeal (02 Feb 2017)

Thanks for the report. I have a fix up #49 and will release 1.0.1 soon.

## BoD (02 Feb 2017)

👍 

## gpeal (02 Feb 2017)

@BoD Can you try again with Lottie 1.0.1? I just released it so it may take a few hours to propagate through maven.

## BoD (03 Feb 2017)

I just tried, and I confirm it now works :)

## erleizh (04 Feb 2020)

```kotlin
class LottieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mLottieDrawable: LottieDrawable = LottieDrawable()

    init {
        mLottieDrawable.setImagesAssetsFolder("lottie/vip/images")
        mLottieDrawable.repeatCount = LottieDrawable.INFINITE
        mLottieDrawable.callback = this
        mLottieDrawable.repeatMode = LottieDrawable.RESTART
        LottieCompositionFactory.fromAsset(context.applicationContext, "lottie/vip/vip.json").addListener { result ->
            mLottieDrawable.composition = result
            mLottieDrawable.setBounds(0, 0, 100, 100)
        }
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mLottieDrawable.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mLottieDrawable.stop()
    }

    override fun verifyDrawable(who: Drawable): Boolean {
        return who == mLottieDrawable || super.verifyDrawable(who)
    }

    override fun onDraw(canvas: Canvas) {
        mLottieDrawable.draw(canvas)
    }
}
```

