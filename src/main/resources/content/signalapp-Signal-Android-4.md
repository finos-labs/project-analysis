#Switch to page-level database encryption.

Owner: signalapp

Repo: Signal-Android

Labels: feature 

## grrrrr (21 Dec 2011)

The current default is to hide the body of the text but display the senders name until TextSecure is unlocked.  It would be nice if there could be an option to allow users to also hide the senders name.


## SirGrant (14 Jan 2012)

I agree.  If you were to have your phone searched by someone like the police they would be able to at least see who you have been texting.  This information should not become available until after you enter your password.  


## moxie0 (02 Feb 2012)

This isn't hidden because that data isn't actually encrypted.  We'll eventually be moving to something like Zetetic's SQLCipher (http://sqlcipher.net/), which will allow us to encrypt those columns as well.


## baghdadbrian (10 Aug 2012)

Any idea when this might happen moxie0? It's a pretty big usability flaw at the moment, though its good to understand this has more to do with the current android functionality rather than an oversight!


## viroid (23 Sept 2012)

I would also really like to see this feature implimented.  I kind of understand the reason the data is not currently encrypted, and therefore shown in plain text.   However I dont see any reason to see anything at all if the passphrase is not cached, just block access to the conversation window.  Then when you do get the SQLCipher stuff implimented, the program will just be that much better.


## dcherian (30 Nov 2016)

Notification settings let you hide both name and message now; so this can be closed?

## baghdadbrian (30 Nov 2016)

seems reasonable to me.

On Wed, Nov 30, 2016 at 10:56 AM, dcherian <notifications@github.com> wrote:

> Notification settings let you hide both name and message now; so this can
> be closed?
>
> —
> You are receiving this because you commented.
> Reply to this email directly, view it on GitHub
> <https://github.com/WhisperSystems/Signal-Android/issues/4#issuecomment-263961250>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/ABLBzfpGrFtSuljssvpQtb0QxjrmsKWZks5rDcbOgaJpZM4AGsjc>
> .
>



-- 



Brian Conley

Co-founder, Small World News

http://smallworldnews.tv

m: 646.285.2046

Skype: brianjoelconley


## sigenc (25 Mar 2017)

Improve local storage encryption. Now only the content of the messages is encrypted. If forensics can bypass androids FDE, they can collect metadata. Please don't assume that androids FDE is safer than local encryption implemented right. Android FDE is light years away from ios and even ios is not bulletproof.

please read on the discoussion forum:

https://whispersystems.discoursehosting.net/t/passphrase-encryption-only-for-message-contents/917

Moxie commented he would want to phase it out in favor of android fde. Wich would be a mistake.

Moxie please read the discussion you answered already. I hope you change your mind. Eventually doesn't mean you are going to change it, that's clear, but please have a look at this thread and comment if you would accept a PR. I'm willing to make a big bounty for this.

## jtracey (08 Feb 2018)

is this is fixed by f36b296e2ed211893835372513da57bb135c52c1?

