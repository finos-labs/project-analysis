#Improve the initial SMS database populating process (force the screen to stay on?)

Owner: signalapp

Repo: Signal-Android

Labels: 

## zorun (22 Jan 2012)

When launching TextSecure for the first time, it allows one to import the standard SMS database.

However, this process can take quite a long time (tried with 3000+ SMS). The screen has the time to turns off, and when you turn it on again, TextSecure asks again if you want to import the standard SMS database. If you say "Yes" again, a lot of messages are duplicated…

The easiest fix would be to inhibate the screen saver, but it wouldn't handle more complex cases (such as the user launching another program while TextSecure is processing the SMS)


## moxie0 (03 Aug 2012)

cffedb09a158acd17aaaad668e949fb02baeb7f5


