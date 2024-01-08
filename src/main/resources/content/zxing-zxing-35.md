#Feature Request: Add the ParsedResult as part of IntentResult

Owner: zxing

Repo: zxing

Labels: enhancement android 

## srowen (18 Jan 2014)

This applies to android-integration. Currently, IntentIntegrator#parseActivityResult returns an IntentResult, which has a getContents() method returning a String.

In a lot of use cases, this String is not very helpful - it has to be parsed using the appropriate ResultParser anyway. This can of course be done as follows (taking the example of WiFi result):

```
IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
Result result = new Result(scanResult.getContents(), null, null, BarcodeFormat.QR_CODE);
WifiParsedResult parsedResult = (WifiParsedResult) WifiResultParser.parseResult(result);
```

However, this requires the addition of the core.jar as a dependency. It seems overkill to add a 500KB+ dependency just for parsing the result (particularly since the ability to parse the result is already present in the Barcode Scanner app itself). 

I was able to avoid this by copying the following files into my project and making minor modifications to eliminate all parsers except the WifiResultParser:
- BarcodeFormat.java
- MathUtils.java 
- ParsedResult.java
- ParsedResultType.java 
- Result.java
- ResultType.java
- ResultMetadataType.java
- ResultParser.java
- ResultPoint.java
- TextParsedResult.java
- WifiParsedResult.java
- WifiResultParser.java

But since this is a frequent requirement, I propose to enhance the IntentResult API to also include the ParsedResult itself - exposed as a getParsedResult() method. I realize this is pretty simple thing to do and I would have made the changes and issued a pull request myself. However, this calls for a decision to be made that is best left to the project authors:

How to deal with exposing the ParsedResult and sub-classes to an Android app that uses IntentIntegrator? 
- Would you move these classes to the android-integration module? This would require the Barcode Scanner app to include android-integration as a dependency.
- Would you rather move these files to a separate module and add this module as a dependency for both Barcode Scanner app and the android-integration module?

Ported from https://code.google.com/p/zxing/issues/detail?id=1832


