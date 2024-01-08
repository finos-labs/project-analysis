#PDF417 should support ECI segments

Owner: zxing

Repo: zxing

Labels: 

## srowen (18 Jan 2014)

The PDF417 decoder ignores ECI segments. Would be nice if it paid attention to reserved codewords 925-927 and reused the ECI stuff from QR codes.

Ported from https://code.google.com/p/zxing/issues/detail?id=1322


## srowen (08 Jun 2014)

This is actually the issue under #166 so closing this as a duplicate.


