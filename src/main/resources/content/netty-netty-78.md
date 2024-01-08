#ChannelLocal should allow easy management of closed Channels

Owner: netty

Repo: netty

Labels: feature 

## trustin (24 Nov 2011)

See [NETTY-447](https://issues.jboss.org/browse/NETTY-447)

At the moment the user must take care of removing Channel instances from ChannelLocal when the Channel is closed. If he forget this he may see some kind of leak. I think we should register a ChannelListener to an added Channel which takes care of removing the Channel from the ChannelLocal once the Channel was closed. Doing this in an automatic way would also be more in line with what we do in other "util" classes like DefaultChannelGroup.

The idea is to make it configurabel via a constructor. Using false (not automatic remove) in 3.2 branch and true (automatic remove) in master.


