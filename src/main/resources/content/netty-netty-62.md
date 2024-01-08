#High-performance direct / heap buffer pool

Owner: netty

Repo: netty

Labels: feature 

## trustin (17 Nov 2011)

We need a high performance direct / heap buffer pool that can be used by users.  So far, we were reluctant about exposing direct buffers to users, but with proper pooling with good documentation, it should be OK in many cases.  Heap buffer pool might also be useful when a user creates heap buffers too often because creating a byte array consumes memory bandwidth because JVM always memset the array with NUL.

However, we still need to allow people to use non-pooled heap buffers because it's often very convenient.  We can ditch non-pooled direct buffers which make all kind of troubles.

We also should give people the option to shrink or limit the pool size.  We will also have to provide some basic stats for diagnosis so that a user can determine the optimal pool size.

Since JVM does not deallocate a direct buffer timely, I'm going to allocate and deallocate it directly using `Unsafe`.  If `Unsafe` is unavailable (probably non-Sun JVM), we can always fall back to NIO's direct `ByteBuffer` and call `System.gc()` explicitly on `OutOfMemoryError`.


## burtonator (25 Dec 2011)

Yes... please share your test code... 

I was also thinking that JNA might be a valid option for this... call mmap directly and then work with the buffer via JNA.


## burtonator (25 Dec 2011)

http://jna.java.net/javadoc/overview-summary.html#library-mapping

> Buffers may also be used as a memory buffer input argument; direct byte buffers can often provide much improved performance over primitive arrays. A pointer provided by native code may be converted to a Buffer by calling Pointer.getByteBuffer(long, long).

so malloc/free , mmap/munmap could be used now with JNA and ChannelBuffers much the same way I'm using the Cleaner method to close Direct buffers now. 

I'm not sure of performance here though but it's an option vs Unsafe.


## normanmaurer (25 Dec 2011)

Not sude either but cassandra did expicit switching to unsafe. I vuees they did enough testing before..


## burtonator (27 Dec 2011)

@normanmaurer explicit switching?   What does "vuees" mean? 


## cowtowncoder (27 Dec 2011)

Wild guess: "guess". Not sure why JNA would be any faster (would expect it to be slightly slower, being more generic), but perhaps if native code did more than just single byte lookup or copy.


## normanmaurer (27 Dec 2011)

@burtonator lovely autocorrection.... vuees = guess.

As far as I know cassandra did use jna before and now moved to unsafe.


## burtonator (27 Dec 2011)

@cowtowncoder I was thinking JNA would be faster because Unsafe did additional work because those benchmarks seem insanely slow.

JNA has a new 'native' mode which for most functions is a lot faster... I should actually benchmark it though ... 

Cassandra moved away from JNA I believe to reduce dependencies... I should try to track it down to see if there were more reasons.


## cowtowncoder (28 Dec 2011)

@burtonator I think it depends on how you access things -- if you can do bulk access, either could be fast, if you have to do call per byte or so, less access. But Unsafe is in sort of better position as it's done by JDK implementors; plus, JNA docs seemed to indicate that it is not geared towards highest possible per-call performance. This is why it didnt seem likely it'd be significantly faster approach.
The right way to evaluate diffs are benchmarks of course.


## burtonator (11 Jan 2012)

@cowtowncoder I benchmarked JNA and it looks about the same performance as doing it natively with C ... 

This was testing mlock/munlock on the same region of data that was previously mmap()ed .. 


## cowtowncoder (12 Jan 2012)

Ok, that does sound good.


## normanmaurer (05 Feb 2012)

I did some prototyping the last week on this and plan to finish the implementation soon. Stay tuned...


## normanmaurer (07 Feb 2012)

Started to work on this. 

See:
https://github.com/netty/netty/tree/bufferpooling

Its still in the work


## ngocdaothanh (09 Feb 2012)

Thanks Norman.
The features are great!
https://github.com/netty/netty/blob/master/buffer/src/main/java/io/netty/buffer/package-info.java


## trustin (13 Dec 2012)

Finally. :-)


## trustin (13 Dec 2012)

This commit resolves this issue by the way: b47fc775223b9cd1b839ec5a18c26b17c5050d60


## ashutoshvsingh (02 Mar 2018)

what is the best way to disable this malloc from happening inside of Netty. We are running IMAP server in netty and under high load we are seeing JVM getting killed because it is using too much memory. It seems that there is a leak in the way memory is either not collected properly or we are flooding ourselves reading from clients and not being able to flush. Either way we would like not to use this malloced heap. 
I did add  -Dio.netty.maxDirectMemory=0 -Dio.netty.allocator.type=unpooled this option to disable this but it is not working.

## normanmaurer (02 Mar 2018)

@ashutoshvsingh you should check via a heap dump whats going on and enable leak-detection. You may not correctly release buffers back to the pool

## ashutoshvsingh (02 Mar 2018)

I just want to disable the malloc. We are seeing this only when we are using this for certain load where the proxy we are writing to is slow. How do I disable this malloc. I can take GC.

## normanmaurer (02 Mar 2018)

@ashutoshvsingh if this is only happening on slow writing you may write to fast ? Please open a new issue and explain what exactly you want to do and what the issue is. I think I may not fully understand the issue yet.

## ashutoshvsingh (02 Mar 2018)

just opened the issue.

On Thu, Mar 1, 2018 at 6:59 PM, Norman Maurer <notifications@github.com>
wrote:

> @ashutoshvsingh <https://github.com/ashutoshvsingh> if this is only
> happening on slow writing you may write to fast ? Please open a new issue
> and explain what exactly you want to do and what the issue is. I think I
> may not fully understand the issue yet.
>
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> <https://github.com/netty/netty/issues/62#issuecomment-369805257>, or mute
> the thread
> <https://github.com/notifications/unsubscribe-auth/ADfN3n1P_vSBR1XCZN-5HTGBQmx3FIACks5taLWRgaJpZM4AUBtf>
> .
>


