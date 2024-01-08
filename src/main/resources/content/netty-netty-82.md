#Add A Way To Validate Charsets Using Channel Buffers

Owner: netty

Repo: netty

Labels: feature 

## garethcollins (25 Nov 2011)

Initial discussion for this request is here:

http://groups.google.com/group/netty/browse_thread/thread/55e267a48aedb2ab#

Basically, it would be nice if ChannelBuffers could (somehow) validate that received messages are compliant with a specified Charset (at least UTF-8).

This would be useful for text based protocols or protocols which have text based frames (such as web sockets :)).
Currently the only way to do the validation is to actually do the String/CharBuffer conversion (which requires a copy). It seems to be a fit for the ChannelBuffers as I assume a goal of ChannelBuffers is to minimize memory copies.

It would be nice if this could work with a dynamic buffer, verifying that the data is compliant with a specified charset as new data is added...and allowing for partial characters at the end of the buffer (assuming there would be additional data entered to complete any partial character).


## lw346 (12 Feb 2013)

With UTF-8, there are two main problems with validating: characters outside the BMP (which Java does not natively support), and characters encoded using the non-shortest form (which are _invalid_ according to the Unicode specification, but were accepted by Java prior to JDK6u11).  UTF-16 would share the BMP issue, but not the shortest form issue.

The code required to validate the UTF-8 string would be fairly simple due to the bitmasking to indicate byte length, but we would need to make the decision on whether to reject characters encoded using the non-shortest form.

US-ASCII would be a simple validation of highest bit not set in all incoming bytes, so would be incredibly easy to do.

If we convert the incoming text to a String in Java, the widest range we can ever get is the Unicode characters covered by UCS-2 (which is subtly different to UTF-16, but this is a good thing for anyone working in mobile telephony).  UCS-2 does not support characters outside of the BMP either.

I can start working on this issue - would anyone recommend a handler approach, or would eg. ByteBuf#validateUtf8() method be preferred instead?


## normanmaurer (20 Jun 2016)

Fixed by https://github.com/netty/netty/pull/5422


