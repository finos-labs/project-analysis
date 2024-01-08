#Fix DynamicChannelBuffer's inefficient memory consumption and excessive memory copy

Owner: netty

Repo: netty

Labels: improvement 

## trustin (17 Nov 2011)

`DynamicChannelBuffer` currently simply doubles its capacity when it needs more room.  This is fine when the size of the buffer is not large, but doubling the capacity of a 128MiB buffer doesn't sound right.  `DynamicChannelBuffer` should be rewritten as a composite buffer of smaller chunks (e.g. 2MiB) by taking advantage of the buffer pool proposed in #62.


## bitmanlger (24 Nov 2011)

Hi Trustin, I'm working on code using Netty. I've been looking at how to substitute a CompositeChannelBuffer instead of a DynamicChannelBuffer in some FrameDecoders. (in my case the DelimiterBasedFrameDecoder). It looks like most of the nuts and bolts are there in the CCB, but not quite enough to make it a clean drop-in. I presume this is why you're looking into this?


## normanmaurer (24 Nov 2011)

@bitmangler: For more details check this:

https://github.com/netty/netty/issues/62


## bitmanlger (24 Nov 2011)

Thanks, good discussion.


## garethcollins (25 Nov 2011)

Following binmaniger's idea, it would be nice if a DynamicChannelBuffer could also be appended by wrapping another buffer, rather than copying (perhaps only copying when the number of wrapped channel buffers becomes too large).

Sort of related, would it be reasonable to add a way to somehow tell FrameDecoder how many bytes are still to be read in a frame (so it can efficiently allocate memory)? This would be helpful to more efficiently handle very large frames (where the total length can be determined early in the frame).


## trustin (17 Aug 2012)

Inefficient memory consumption issue has been fixed by the new ensureWritableBytes() implementation.  The new implementation increases the capacity by 4 MiB at max.


