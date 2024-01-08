#Injecting failed

Owner: JakeWharton

Repo: butterknife

Labels: 

## scruffyfox (02 Apr 2013)

Seem to be having issue injecting the views, even with the sample code in the repo.

I've tried using the straight JAR file, referencing it to export with apk etc but all no go.
FWIW i'm not using proguard, but I do have Lombok installed in Eclipse (Which shouldent be an issue). No stacktrace available as the injected views are just not being set


## javera (10 Apr 2013)

#12 maybe this will help you


## scruffyfox (10 Apr 2013)

Yes, that fixed it, thanks very much.

This should really be on the homepage so idiots like me can install and use the lib properly without wasting people's time.


## JakeWharton (10 Apr 2013)

#23 


## scruffyfox (10 Apr 2013)

Was just about to fork and do a pull request :)


