#If no parameters are present after creating request, do not append ? to URI

Owner: square

Repo: retrofit

Labels: 

## thorinside (06 Dec 2012)

I have noticed that the ? is always present at the end of URIs. This is completely valid, I suspect, but it was unexpected when I was making some Robolectric unit tests to check if the thing was sending the information to the correct URI. Perhaps, if the parameters are empty (as in there are no parameters to be appended) don't add the ? query parameters separator.


## thorinside (10 Dec 2012)

See #115 for pull request.


