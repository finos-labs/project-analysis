#Client Error and Server Error Should Not Use Hard-Coded Type

Owner: square

Repo: retrofit

Labels: 

## JakeWharton (26 Oct 2012)

Right now it assumes you get responses in the form `{'message': 'things!'}` which is an awful assumption to make. Parameterize this similar to how `Callback` is already parameterized.


## JakeWharton (28 Oct 2012)

Blocked by #80


