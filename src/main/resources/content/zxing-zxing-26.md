#AZTEC and DATA_MATRIX do not honor EncodeHintType.MARGIN

Owner: zxing

Repo: zxing

Labels: bug 

## srowen (18 Jan 2014)

Set EncodeHintType.MARGIN to 4 to QR_CODE, AZTEC, DATA_MATRIX while rendering (in MultiFormatWriter)

We should see rendered margin in all square barcode types. Instead, only QR_CODE honors this hint type.

https://github.com/zxing/zxing/blob/master/core/src/main/java/com/google/zxing/aztec/AztecWriter.java

Ported from https://code.google.com/p/zxing/issues/detail?id=1754


