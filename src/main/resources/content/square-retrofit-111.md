#Sending a file

Owner: square

Repo: retrofit

Labels: 

## stephanenicolas (06 Dec 2012)

Hello, 

first of all thanks for retrofit the lib looks promising.

Is there any way to send a file to a server ? If so, could you add it to the sample in the README.md ?

Thx in advance, 
 Stéphane


## pforhan (06 Dec 2012)

Take a look at TypedBytes / TypedFile.  If a TypedBytes is a parameter, it will be added as a multipart upload for POSTs.


## JakeWharton (06 Dec 2012)

We'll add support for just specifying a `File` as a param which will be uploaded as a simple `application/octet-stream`. Most servers are happy enough with that and it'll potentially save from having to do anything with mime types on the client.


## JakeWharton (06 Dec 2012)

And there will be a documentation website with examples coming soon...


## stephanenicolas (07 Dec 2012)

Nice I am impatient to see a more complete doc for retrofit.
I have been thinking about it a lot these days and we plan to add a
robospice module for it.

Also, I have another question. Is it worth opening a new issue ? I just
wanna debate on it, it's more some kind of fireside talk.
;)

Stéphane

2012/12/6 Jake Wharton notifications@github.com

> And there will be a documentation website with examples coming soon...
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/square/retrofit/issues/111#issuecomment-11092288.

## 

Stéphane _NICOLAS_,
_OCTO Technology_
Développeur & Consultant Android / Java
..........................................................
50, Avenue des Champs-Elysées
75008 Paris
+33 (0)6.26.32.34.09
www.octo.com - blog.octo.com
www.usievents.com
...........................................................


## JakeWharton (09 Dec 2012)

I will be starting the documentation website this week which will live inside the repo.


## JakeWharton (18 Dec 2012)

@stephanenicolas You can open another issue for now. We'll have a proper place to discuss non-issues soon.


## JakeWharton (18 Dec 2012)

#124 


