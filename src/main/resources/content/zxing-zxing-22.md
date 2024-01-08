#Need to handle onSurfaceChanged() in Barcode Scanner?

Owner: zxing

Repo: zxing

Labels: 

## srowen (18 Jan 2014)

A user reported this exception:

```
java.lang.IllegalArgumentException: Requested window android.os.BinderProxy@xxxxxxxx does not exist
```

In looking at possible causes I see that we may not be implementing onSurfaceChanged(), when it may be needed to handle rotations. This should cause the camera to re-init with a new surface. This may be one of those situations that never actually came up in practice but is still wrong.

Ported from https://code.google.com/p/zxing/issues/detail?id=1363


