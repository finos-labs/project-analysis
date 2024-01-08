#Add API for encoding byte[]

Owner: zxing

Repo: zxing

Labels: 

## srowen (18 Jan 2014)

Another placeholder for a request to augment the Writer API in some cases to allow for direct encoding of byte[], not just String. This would be relevant for QR codes and Data Matrix at least.

Ported from https://code.google.com/p/zxing/issues/detail?id=1132


## gmkarl (30 Sept 2014)

Just to note, this issue is still relevant.  There is a thorough patch in the linked code.google.com issue which includes much more correct support for structured append than the implementation currently in the tree (the current implementation misreads the SA total count as the upper 4 bits of the SA index; the patch in the thread reads them properly).  Last commit the patch applies to without a hitch is d265ccff5666679885b1bb5e41d462bebae53bd8.  After that it looks like srowen started merging it in but never finished.


## srowen (30 Sept 2014)

Feel free to reopen if you have an updated patch to propose. From reviewing the comments, my feedback was not really addressed, so I only committed parts of it that clearly made sense. This needs to be done without so much duplication. https://code.google.com/p/zxing/issues/detail?id=1132  


## gmkarl (01 Oct 2014)

I understand the duplication was intentional in order to support an interface for bytes[] as thoroughly as for String.  In DecoderResult, 'data' shows segments marked with a byte ECI, and 'text' shows segments marked with numeric, alphanumeric, or kanji.
Would you agree the only duplication that needs addressing is the parallel encoding implementations for both byte[] and String in both QRCodeWriter and Encode ?


## srowen (02 Oct 2014)

From looking at my comments, I think the broadest issue is adding a fair bit of complexity to support a relatively rare use case. I don't know if it can be made cleaner or simpler, but that would sure help motivate it. A symptom of that was some apparent code duplication. DecodeResult already has a field for text and byte segments, so I didn't see what "data" was for. Looking back at the patch, it seemed to concern Structured Append more than encoding bytes, but I'm just glancing. Are you interested in SA or encoding byte[]? both?

I would imagine the only new public API needs to be new methods that accept a byte[] rather than String as input. That seems more useful than SA, and simpler, and imagine it can be done with relatively little change as it just skips a lot of the String -> byte[] encoding.


## gmkarl (02 Oct 2014)

I'm interested in the state of Structured Append myself.  It is currently broken in the main tree (decodes but incorrectly).  This patch implements it more properly and thoroughly than the decoding implementation added much later.  

It would certainly be more organized to have the byte[] vs SA additions split apart.  I think perhaps the idea here is that QR codes can hold useful small binary files, but zxing cannot read or produce them for such a use yet.


## srowen (02 Oct 2014)

Getting a bug fixed is higher priority for sure. if that's a separable and
clean change I'd be happy to help get it in.

On Thu, Oct 2, 2014 at 6:38 PM, gmkarl notifications@github.com wrote:

> I'm interested in the state of Structured Append myself. It is currently
> broken in the main tree (decodes but incorrectly). This patch implements it
> more properly and thoroughly than the decoding implementation added much
> later.
> 
> It would certainly be more organized to have the byte[] vs SA additions
> split apart. I think perhaps the idea here is that QR codes can hold useful
> small binary files, but zxing cannot read or produce them for such a use
> yet.
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/zxing/zxing/issues/28#issuecomment-57668367.


