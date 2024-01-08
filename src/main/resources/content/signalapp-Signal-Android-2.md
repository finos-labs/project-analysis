#Screen refresh issues on conversation deletion

Owner: signalapp

Repo: Signal-Android

Labels: 

## grrrrr (21 Dec 2011)

Phone: Nexus S
OS: ICS 4.0.3
TextSecure version: <= 0.5.7 (first OSS version)

When you go to delete conversation from the main TextSecure there is a small bug with the screen refresh

If you have multiple conversations and you want to delete one of them the following happens

---- pre delete -----
Conversation_1
Conversation_2
Conversation_3
---- pre delete -----

You go to delete Conversation_2 and get left with the following on the screen

---- post delete -----
Conversation_1
Conversation_3
Conversation_3
---- post delete -----

This will stay this way until you change screens or close out of TextSecure and re-open.  The lower of the two Conversation_3's is not selectable.

Similarly, if you have only one conversation, the screen will not refresh 

---- pre delete -----
Conversation_1
---- pre delete -----

You go to delete Conversation_2 and get left with the following on the screen

---- post delete -----
Conversation_1
---- post delete -----

This conversation_1 is not selectable.


## moxie0 (03 Aug 2012)

7cab26750b77d905400eb577cbd1adcf132e3c0f


