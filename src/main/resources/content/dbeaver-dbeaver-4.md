#Windows installer: broken unpack

Owner: dbeaver

Repo: dbeaver

Labels: bug 

## serge-rider (21 Oct 2015)

For some reason in some environments jars are not unpacked after installation.
Maybe problem is in NSIS or in permissions or in conflicts with local Java or in something else.
Investigate.


## serge-rider (24 Oct 2015)

Most likely it is a result of missing msvcr100.dll library. 
Fixed. Delivered in 3.5.2.


