#SslHandlers detecting of "surpressable events" may break on different OS's

Owner: netty

Repo: netty

Labels: defect 

## normanmaurer (24 Nov 2011)

In SslHandler we use an regex to see if an Exception should be surpressed or not. This may break for various reasons in the future. Like OS Locale, JDK etc. 

So we should review this:

See for example:
https://github.com/netty/netty/blob/master/src/main/java/org/jboss/netty/handler/ssl/SslHandler.java#L494


## normanmaurer (05 Jul 2012)

Breaks on Ubuntu with german language:

java.io.IOException: Die Verbindung wurde vom Kommunikationspartner
zurückgesetzt
        at sun.nio.ch.FileDispatcherImpl.read0(Native Method)
        at sun.nio.ch.SocketDispatcher.read(SocketDispatcher.java:39)
        at sun.nio.ch.IOUtil.readIntoNativeBuffer(IOUtil.java:218)
        at sun.nio.ch.IOUtil.read(IOUtil.java:191)
        at sun.nio.ch.SocketChannelImpl.read(SocketChannelImpl.java:359)
        at io.netty.buffer.HeapByteBuf.setBytes(HeapByteBuf.java:167)
        at io.netty.buffer.DynamicByteBuf.setBytes(DynamicByteBuf.java:213)
        at io.netty.buffer.AbstractByteBuf.writeBytes(AbstractByteBuf.java:595)
        at io.netty.buffer.DynamicByteBuf.writeBytes(DynamicByteBuf.java:274)
        at io.netty.channel.socket.nio.NioSocketChannel.doReadBytes(NioSocketChannel.java:157)
        at io.netty.channel.socket.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:50)
        at io.netty.channel.socket.nio.NioChildEventLoop.processSelectedKeys(NioChildEventLoop.java:183)
        at io.netty.channel.socket.nio.NioChildEventLoop.run(NioChildEventLoop.java:123)
        at io.netty.channel.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:92)
        at java.lang.Thread.run(Thread.java:722)


