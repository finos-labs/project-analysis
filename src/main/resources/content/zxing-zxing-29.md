#TRY_HARDER mode should scan for QR codes at 45-degree rotations

Owner: zxing

Repo: zxing

Labels: 

## srowen (18 Jan 2014)

QR code scanning is notably weaker when the code is rotated 45 degrees (plus multiples of 90) due to the assumptions made by heuristics. TRY_HARDER mode could cause it to also try a rotation of 45 degrees, just for QR codes, to account for this.

Ported from https://code.google.com/p/zxing/issues/detail?id=1284


