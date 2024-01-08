#Scanning PDF417 crashes on android

Owner: zxing

Repo: zxing

Labels: 

## kszczepaniec (29 Jan 2014)

Tested on latest version
 zxing core-3.0.0-SNAPSHOT  and Android app BBarcodeScanner-release.apk
on Nexus 5, Android 4.4.2

Scanning part of PDF417 barcode causes ArrayIndexOutOfBoundsException

steps to reproduce:
-install mentioned android app
- turn on aztec/PDF417 scanning
- try to scan  
  http://upload.wikimedia.org/wikipedia/commons/c/c6/Sample_PDF417.png

-scanning entire image does not work but does not cause app to crash
-now try to scan with only bottom-half of the image (or less than half) in the crop area  
-application crashes with ArrayIndexOutOfBoundsException

 FATAL EXCEPTION: Thread-2883
 Process: com.google.zxing.client.android, PID: 10770
 java.lang.ArrayIndexOutOfBoundsException: length=24; index=24
    at com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.adjustBoundingBox(PDF417ScanningDecoder.java:146)
    at com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.decode(PDF417ScanningDecoder.java:71)
    at com.google.zxing.pdf417.PDF417Reader.decode(PDF417Reader.java:61)
    at com.google.zxing.MultiFormatReader.decodeInternal(MultiFormatReader.java:171)
    at com.google.zxing.client.android.DecodeHandler.handleMessage(DecodeHandler.java:58)
    at android.os.Handler.dispatchMessage(Handler.java:102)
    at android.os.Looper.loop(Looper.java:136)
    at com.google.zxing.client.android.DecodeThread.run(DecodeThread.java:110)


## srowen (29 Jan 2014)

I don't see this error. It's possible or even likely it was fixed between whatever version you are running and now. The last release is not the same as HEAD and would not be related to a SNAPSHOT build. You can try this from HEAD to be sure.

Still I can't get the image to scan for some reason. Let me see if there's any obvious reason for that.


## srowen (29 Jan 2014)

Hmm. I think this barcode is messed up. It has 144 code words, but the first value indicates that only 80 codewords follow. It's followed by 80 latches to text compaction, which do nothing. So the barcode is empty, although there is evidently something in the other 64 codewords.

I tried uploading to http://online-barcode-reader.inliteresearch.com/ and got the same answer -- empty. (this library just rejects it, assuming no barcode is supposed to have no content)

Same with http://www.onlinebarcodereader.com/  and http://www.online-barcode-decoder.com/

It's possible at least one of those is based on zxing, so it's circular, but I don't think so.

So: we shouldn't crash, but it doesn't crash in HEAD. And then it seems to 'correctly' reject it as a 0-length PDF417.


## kszczepaniec (29 Jan 2014)

Yeah, i also suspected that this barcode is messed up in some way. 
I've checked again on a fresh git clone  and with the same steps i've got

 Process: com.google.zxing.client.android, PID: 14343
 java.lang.NullPointerException
    at com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.decode(PDF417ScanningDecoder.java:71)
    at com.google.zxing.pdf417.PDF417Reader.decode(PDF417Reader.java:61)
    at com.google.zxing.MultiFormatReader.decodeInternal(MultiFormatReader.java:171)
    at com.google.zxing.client.android.DecodeHandler.handleMessage(DecodeHandler.java:58)
    at android.os.Handler.dispatchMessage(Handler.java:102)
    at android.os.Looper.loop(Looper.java:136)
    at com.google.zxing.client.android.DecodeThread.run(DecodeThread.java:110)

weird...


## srowen (29 Jan 2014)

Ah I see that too now. One sec, fix coming.


## kszczepaniec (29 Jan 2014)

Works lik a charm now :)


