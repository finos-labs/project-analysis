#dev branch: NPE in VersionedGestureDetector

Owner: Baseflow

Repo: PhotoView

Labels: 

## httpdispatch (04 Dec 2012)

This code causes NPE on android version above froyo

``` java
if (sdkVersion < 5) {
            detector = new CupcakeDetector(context);
        } else if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
            detector = new CupcakeDetector(context);
        } else if (sdkVersion < Build.VERSION_CODES.FROYO) {
            detector = new FroyoDetector(context);
        }

        detector.mListener = listener;
```


