#Export to stock SMS database.

Owner: signalapp

Repo: Signal-Android

Labels: feature 

## shadowofdarkness (22 Dec 2011)

I think people should be able to export SMS back to the stock database in-case they decide to remove this app and don't want to lose all their SMS history.

I don't want to start using the app just so I don't have to worry about being locked in, in the future if something better comes along.


## Kudu (08 Aug 2012)

I'd like to have this too, it'd be helpful in convincing n00bs to try the app.


## kmmndr (23 Nov 2012)

This would be good, or at least a way to export database uncrypted (there are a lot of programs to import sms from text files)


## firefox78 (19 Dec 2012)

This in my oppinion should be the first option to be integrated... i have used half a year Text secure and i dont want to loose all my SMS when i uninstall Text Secure. 


## RidgebackZulu (01 Apr 2013)

Please implement this soon!


## LaNsLyDe (21 Apr 2013)

Yes I hope this feature comes asap. 


## ullebe1 (29 Apr 2013)

I agree, this is a much needed feature. 


## maertsen (07 May 2013)

This is related to issue #19 as part of the same "try the app without serious consequences" workflow. As a former textsecure user, I now have part of my history in the standard android storage and part of it in a TextSecureExport. There seems to be no way to unify the two, either in or outside the app.


## moxie0 (07 May 2013)

The major difficulty with this is that there is no public API for accessing the standard SMS database.  We currently import from it using private unpublished APIs, which is best-effort , seems to work on most devices, and is low risk since it's a read-only operation.  

Writing to the SMS database, however, could cause damage if we're dealing with a device that has modified the SMS db format.


## maertsen (07 May 2013)

Hmm, is export to a third-party app format an option? E.g. SMS Backup & Restore [1] specialises in the problem you describe. If textsecure would output in the documented xml format (see sample xml, xsl, xsd files) users could use that app to try and solve their problem. 
Other apps known to backup/restore sms messages in some text-based format are Titanium Backup, but I think this specific functionality is paid.

[1] http://android.riteshsahu.com/apps/sms-backup-restore


## jwise (24 May 2013)

Yes, I just ran into this issue in a more exciting fashion -- apparently if you lose power at just the wrong time (say, you drop the phone right after sending a text), then you can lose the entire message database.  My most recent backup is in the form of an encrypted TextSecure backup, so being able to get these data out of a messages.db that I have lying around would be nice.  (Of course, I also have the corresponding SecureSMS-Preferences.xml.)


## shadowofdarkness (31 May 2013)

The third party app format option seems like a good idea. Before TextSecure I used "SMS Backup & Restore" a lot and it always worked perfect.

Currently my motivation to export is not to leave this great app but I want my database unencrypted because I have some people that move around regularly and change numbers to there new local areas, and I want to retroactively change the old messages phone number to the new one to keep them at one thread each. Also I would not need to keep old deactivated numbers in my contact list just to have a name attached to the old messages.


## ronin13 (03 Jun 2013)

Yes, having this would be nice. My use-case for this will be to 
dump the database to my system in plain text. Is the database 
decryptable otherwise (if accessed through adb and key is 
provided)?


## EvanK (09 Jun 2013)

I'd :+1: this as a feature, but would suggest a dialog warning when you're exporting to an unencrypted format


## juneyao (12 Jun 2013)

only reason i have not moved yet to TextSecure is the fact that it makes it difficult to backup messages.
IMO there is no need to mess up with the stock sms db. It would probably suffice if TS had a Tasker plugin that allows exporting the message database in xml (same format as SMS backup and restore by Ritesh Sahu)

I am still trying to figure out how to automatically (every time i plug my phone to computer) backup messages to my encrypted Thunderbird profile without storing unencrypted files on the phone.


## moxie0 (25 Jun 2013)

Support for exporting to the "SMS Backup And Restore" format in plaintext:
aa25f94291458e6d22c9585e7b229209fb83f26a


## RealOrangeOne (14 Oct 2018)

Is this feature still implemented? I can't seem to find the functionality in the app, and the "SMS Backup and Restore" app doesn't detect the messages in Signal, only those in the main SMS database.

## greyson-signal (14 Oct 2018)

@RealOrangeOne We support encrypted backup, so we have removed the plaintext backup option.

## RealOrangeOne (14 Oct 2018)

I see. But that doesn't allow export into the stock SMS database, as it's a proprietary format. Looks like https://github.com/xeals/signal-back/ solves the problem anyway!

## GuardianMajor (14 Oct 2018)

I believe that you should provide both encrypted and also the traditional plain text. Encrypted locks you into Signal, but the plaintext version allows for portability and say populating another message app, and so long as the persons knows what that means (meaning the insecure nature of it) then that should be their call.

Plus this would not be difficult to implement as it already exists and just needs to be put back in the codebase. The current encrypted backup is very poorly done as it keeps creating huge backups instead of using incremental backups into a single file, it does it whenever it feels like it and without any rhyme or reason and that can interfere with your usability. There should really be some kind of a scheduling ability built into it.

## greyson-signal (15 Oct 2018)

Github is for bug reports. Please move any other discussion to [the forum](https://community.signalusers.org/c/feature-requests/android-feature-requests). Thanks!

