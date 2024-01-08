#Add response headers to XMLHttpRequest

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## nick (31 Jan 2015)

I'm trying to get superagent working for #10 but it seems XMLHttpRequest does not support response headers. `getAllResponseHeaders()` is currently just a stub and there is no implementation of `getResponseHeader`.


## vjeux (31 Jan 2015)

Do you want to make the stub a bit less bad? :)


## vjeux (07 Feb 2015)

Closing as you made a pull request


