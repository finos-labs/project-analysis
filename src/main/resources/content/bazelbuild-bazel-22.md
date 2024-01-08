#compile.sh Unknown option: n Unknown option: 1

Owner: bazelbuild

Repo: bazel

Labels: 

## leemcd56 (24 Mar 2015)

Compiling on Mac OS X Yosemite 10.10.3 from instructions ends with this:

![screenshot at mar 24 12-46-21](https://cloud.githubusercontent.com/assets/1885663/6808751/d7fedf90-d223-11e4-9c42-c16042f7d9f6.png)


## kchodorow (24 Mar 2015)

It looks like head might not support the -n1 format in Yosemite?  Can you try running:

```
$ head --help
```

and paste the output here?  (Please paste the text, not a screenshot, if possible.)


## damienmg (24 Mar 2015)

It actually looks like you have a tool head that do a HEAD request on website. Is that a standard Yosemite or did you installed some other tools? What is `which head` outputting?


## leemcd56 (24 Mar 2015)

@kchodorow 
bash-3.2$ head --help
Unknown option: help
Usage: head [-options] <url>...
    -m <method>   use method for the request (default is 'HEAD')
    -f            make request even if head believes method is illegal
    -b <base>     Use the specified URL as base
    -t <timeout>  Set timeout value
    -i <time>     Set the If-Modified-Since header on the request
    -c <conttype> use this content-type for POST, PUT, CHECKIN
    -a            Use text mode for content I/O
    -p <proxyurl> use this as a proxy
    -P            don't load proxy settings from environment
    -H <header>   send this HTTP header (you can specify several)

```
-u            Display method and URL before any response
-U            Display request headers (implies -u)
-s            Display response status code
-S            Display response status chain
-e            Display response headers
-d            Do not display content
-o <format>   Process HTML content in various ways

-v            Show program version
-h            Print this message

-x            Extra debugging output
```


## leemcd56 (24 Mar 2015)

@damienmg You were correct; head was referring to /Applications/XAMPP/xamppfiles/bin/head instead of /usr/bin/head. Fixed and compiled!


## damienmg (24 Mar 2015)

Great!


