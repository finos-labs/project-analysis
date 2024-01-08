#Invalid UPC_E leads to StringIndexOutOfBoundsException in ResultParser.parseResult

Owner: zxing

Repo: zxing

Labels: 

## blacelle (23 Jan 2014)

I get several stack like the following:
01-23 22:10:43.724: E/AndroidRuntime(28903): java.lang.StringIndexOutOfBoundsException: length=1; regionStart=1; regionLength=6
01-23 22:10:43.724: E/AndroidRuntime(28903):    at java.lang.String.startEndAndLength(String.java:593)
01-23 22:10:43.724: E/AndroidRuntime(28903):    at java.lang.String.getChars(String.java:902)
01-23 22:10:43.724: E/AndroidRuntime(28903):    at com.google.zxing.oned.UPCEReader.convertUPCEtoUPCA(UPCEReader.java:124)
01-23 22:10:43.724: E/AndroidRuntime(28903):    at com.google.zxing.client.result.ProductResultParser.parse(ProductResultParser.java:51)
01-23 22:10:43.724: E/AndroidRuntime(28903):    at com.google.zxing.client.result.ProductResultParser.parse(ProductResultParser.java:28)
01-23 22:10:43.724: E/AndroidRuntime(28903):    at com.google.zxing.client.result.ResultParser.parseResult(ResultParser.java:87)

While this stack comes from calling ResultParser.parseResult with an effectively invalid UPC_E (as it has only 1 char while I understand an UPC_E should have 8 chars), I also encountered an equivalent issue with a decoded UPC_E.

If it is expected to throy, I guess it should be handled at most in ResultParser.parseResult as this method should then try the next Parser.


## blacelle (23 Jan 2014)

It happens in core2.3.0


## srowen (23 Jan 2014)

Just eyeballing the code, I am not immediately clear how a UPC-E barcode with just 1 digit was returned. There's a check that will refuse to allow any UPC/EAN with less than 8 digits to be returned: 

https://github.com/zxing/zxing/blob/master/core/src/main/java/com/google/zxing/oned/UPCEANReader.java#L180

Or are you calling this parse method directly from other code? 

Hmm. This isn't really a case of input that just can't be parsed, it's really invalid input. UPC-E can't be 1 digit and that should be addressed upstream. So I am reluctant to say the parser should just continue. 

An exception of some kind is expected then; it could be clearer but then again the current exception is pretty targeted at the problem.


## blacelle (23 Jan 2014)

The Result with a 1-char UPC-E was built manually. But I encountered a similar issue with a decoded UPC-E. I was not able to retrieve the invalid decoded UPC-E.

I see your point: as the result is invalid, it is fair to throw. However, I feel like it would be natural to handle such invalid Result with a TextParsedResult. It would enable handling invalid user inputs (my case), and in a more general sense, any invalid Result as it would be sent with an Intent.

Is there an easy way to check a Result is valid (i.e. its content is valid given its BarcodeFormat)?


## srowen (23 Jan 2014)

Tough one... it's easy to add this stuff, although I'm afraid of duplicating code that belongs elsewhere, and having to then check a lot of stuff here for consistency. Then again, it already checks for "a UPC code that isn't digits" which is equally supposed to not exist. UPC-E is already necessarily special-cased here. I think it's no big deal to also check the 8-digit condition again here.


## blacelle (23 Jan 2014)

Thanks!


