#Fetcher Buffer Read Should Use '!= -1'

Owner: square

Repo: retrofit

Labels: 

## JakeWharton (16 Oct 2012)

From @swankjesse on #62:

> Use != -1 rather than > -1. Doing > -1 is less precise, and it implies a bunch of impossible scenarios that can't happen, like read() returning 0 or -2.


