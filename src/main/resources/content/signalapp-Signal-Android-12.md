#Can NOT send encrypted MMS with attachments

Owner: signalapp

Repo: Signal-Android

Labels: 

## duanqz (28 Dec 2011)

It's always failed to send an encrypted MMS containing attachments.
Perhaps because there is no TABLE attachments in the database.


## teffalump (07 Jan 2012)

Hey, yeah, I have the same problem - both ways, sending and receiving. I have explored the source a little, read my log errors, etc. and I'll look into your suggestion, but the only problem I have tackled (haven't actually generated the apk with my commit) was that the carrier MMS information was improperly extracted from telephony.db. That is, it's there, but it is skipped, so I'll see, lol.


## moxie0 (17 Oct 2012)

This should be fixed as of 0.7.4, please let me know if you continue to have problems.


