#Simplify Channel state event model

Owner: netty

Repo: netty

Labels: feature 

## trustin (18 Nov 2011)

When a new channel goes into service or out of service, we see the following events:
- `channelOpen`
- `channelBound`
- `channelConnected`
- `channelDisconnected`
- `channelUnbound`
- `channelClosed`

Do we really need all these 3 events per (dis)connection?

Why don't we just simplify like this:
- Make `Channel` implementations public so that people can extend it and perform additional action in the constructor - this replaces `channelOpen`
- Replace `channelBound` and `channelConnected` with `channelAttached`.
- Replace `channelDisconnected`, `channelUnbound`, and `channelClosed` with `channelDetached`.

However, `channelAttached` and `channelDetached` is not exactly same with the replaced events because a user can attach an unconnected channel to an event loop.  I think a user can simply add a listener to the returned `ChannelFuture`.

Related issues: #66, #67


## normanmaurer (18 Nov 2011)

I'm not sure I like the proposed solution. I think it would worth to still keep the channelOpen, channelConnected, channelDisconnect, channelClosed events accessable in an "easy" way. At least these are the onces I usually interested in and handle it in the SimpleChannelUpstreamHandler


## trustin (19 Nov 2011)

OK.  Here's my thoughts so far.

If we allow a user to attach / detach a `Channel` at any time as I proposed at #67 (i.e. even use the `Channel` he/she created using the NIO API directly), it means we need to add two more event types:
- `channelAttached`
- `channelDetached`

And then we need to think about `channelOpen`.  Should we keep it?  It's currently called immediately when a channel is created.  The problem with this event type is that it's never fired from an I/O thread. i.e. It's triggered even before it is registered/attached to an event loop, and it makes our event thread model awkward.

So, should we replace `channelOpen` with `channelAttached`?  I think so.

Now, let's think about `channelBound`.  It's only useful when a user deliberately called `Channel.bind(...)`.  Otherwise, `channelConnected` will be enough.  When a user calls `Channel.bind(...)`?
- Server sockets
  - It doesn't have `channelConnected`, so we could define a new event type that merges `channelBound` and `channelConnected` into `channelActivated`.
- A user binds to a certain port for a client-side socket before connecting
  - In all cases, a user does not only bind but connect after binding, so we can simply define `Channel.connect(localAddr, remoteAddr)` and `channelActivate` will be triggered after connection attempt is done.
  - We can still fire `ChannelStateEvent` and then user can override `channelStateChanged` handler method or use `ChannelFuture` if a user really wants to bind and then later connect.
- Datagram sockets
  - Similarly, we could replace `channelBound` with `channelActivated`.
  - A user might want `channelActivated` called after `Channel.connect(...)`.  We can simply provide an atomic operation `Channel.connect(localAddr, remoteAddr)` so that `channelActivated` is triggered only after the channel is both bound and connected. (This is similar to the case where a user binds a client socket before connecting.)

Therefore, here's my revised proposal:
- `channelAttached`
- `channelActivated`
- `channelDeactivated`
- `channelDetached`


## jestan (19 Nov 2011)

Here are my comments,

Event though existing channel state event model fairly complex, each event gives clear message what has happened at server or client channel :-) . 

Before start writing sctp transport, I didn't quite understood what channelOpen does. I think, it is OK to remove channelOpen since it is not quite meaning full event for Netty users.

The new proposal will make Netty user's life easy because it gives a uniform state event model, across sever and client channels.I am just wondering, will channelActivated give the additional info like "in what type of channel (client or server etc)  the event has happened without additional method calls", so users can get a  clear message what has happened.


