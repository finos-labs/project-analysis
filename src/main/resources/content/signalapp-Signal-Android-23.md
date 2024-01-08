#No max. thread size setting

Owner: signalapp

Repo: Signal-Android

Labels: feature 

## morganbye (08 Feb 2012)

Recently due to my own ignorance and the Mrs being away I quickly amounted ~1000 messages in one thread. At which point I think I must have hit a database / memory issue.

TextSecure could not accept any new messages (it straight rejected them) but equally had no memory so I couldnt delete a single message or an entire thread at once.

The only way to resolve the problem was to go into the Settings menu -> Applications -> TextSecure and delete TextSecure's database.

I'd prepose a "max thread length" in the options, most other text programs allow you to set a max length of say 100 messages.


## moxie0 (08 Feb 2012)

Agreed, there are also simple optimizations we could do by adding
additional DB indexes that would make TextSecure perform better with
long threads.  I started to make those changes when I was developing on
a G1, but quickly forgot when I got a newer device.  =)
- moxie

## 

http://www.thoughtcrime.org


## jzr (14 Aug 2012)

I had no problems with the app becoming unusable, but it can take several seconds to load a thread that went over 5k messages recently. I would wish for better indexing instead of a max thread size, maybe loading only the last 25 messages when opening a thread and having a button to load more? 


## moxie0 (10 Jan 2013)

So the better indexing has been in there for a release or two.  Additionally, I just checked in support for auto thread length trimming: fe43ef65ab9ad2390dcc8575f8667379c566166f


