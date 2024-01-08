#Message encrypted for non-existent session

Owner: signalapp

Repo: Signal-Android

Labels: 

## cloudragon (26 Dec 2011)

I recv a sms which show me "Message encrypted for non-existent session", is there anyway I can get the sms content?


## moxie0 (17 Oct 2012)

I think this is fixed in recent builds, please let me know if you continue to see it.


## sarciszewski (25 Jun 2013)

Had to uninstall because I forgot my password, reinstalled with a new password... people who had TextSecure on their phones now cannot communicate with me. Is there any way for them to clear my old public key from their cache (or whatever is causing this to appear)?


## moxie0 (25 Jun 2013)

They need to tap the padlock in the actionbar for the thread with you and select "abort secure session."

Alternately, you can initiate a new secure session with them.  Your identity key will have changed, however, so they'll have to manually accept the new one.


