#ITF wide bar width, bar wide to narrow ratio

Owner: zxing

Repo: zxing

Labels: 

## srowen (18 Jan 2014)

Hi,

in source file 

core/src/main/java/com/google/zxing/oned/ITFReader.java on line 50 we have:

private static final int W = 3; // Pixel width of a wide line

in comment you write "Wikipedia is a great reference for Interleaved 2 of 5 information"
and on Wikipedia site we have: wide line (2.0 to 3.0 times the width of a narrow line).

I implement "Wide To Narrow Ratio" in my barcode generator

http://www.generatorkodowkreskowych.pl/en/itf-14/
http://www.generatorkodowkreskowych.pl/en/2of5-interleaved/

You can generate ITF codes with diffrent wide line width (2 to 3 times the width of a narrow line)

For PNG images you can use:

at scale 100% (narrow bar = 1) and WTNR in [2, 3]                     wide bar = 2,3
at scale 200% (narrow bar = 2) and WTNR in [2, 2.5, 3]                wide bar = 4,5,6
at scale 300% (narrow bar = 3) and WTNR in [2, 2.33, 2.66, 3]         wide bar = 6,7,8,9
at scale 400% (narrow bar = 4) and WTNR in [2, 2.25, 2,5, 2.75, 3.0 ] wide bar = 8,9,10,11,12

For PDF files you can use:

scale in range 80% to 200%, Wide To Narrow Ratio in range 2.00 to 3.00 with 0.01 step.

I make tests:

generate codes with Wide/Narrow = 2, and with Wide/Narrow = 2.5, and Wide/Narrow = 3
print codes and read with manual barcode reader Argox AS-8000.

Your barcode reader can not read ITF barcode with Wide To Narrow Ratio = 2,
but can read Code-39 with Wide To Narrow Ratio = [2,3].

I assume this information can help you to improve ITFReader.

Ported from https://code.google.com/p/zxing/issues/detail?id=1831


## srowen (12 Feb 2014)

Closing; no follow-up to questions from reporter.


