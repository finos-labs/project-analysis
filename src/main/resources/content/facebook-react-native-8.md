#Support webpack-dev-server

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## gaearon (30 Jan 2015)

I want to implement support for Webpack dev server.

This is essential to my workflow as I rely on transforms such as 6to5. I also want to integrate Hot Module Replacement to React Native so that updates without losing component state or unmounting are possible.

Where should I start looking?


## subtleGradient (30 Jan 2015)

https://github.com/facebook/react-native/search?utf8=✓&q=localhost

Host your files on localhost and you're done. Search through the code for localhost to see the specific paths you need to get working.


## gaearon (30 Jan 2015)

Gotcha! I'll try that.


## subtleGradient (30 Jan 2015)

Also see here: https://github.com/facebook/react-native/tree/master/packager


## vjeux (31 Jan 2015)

Closing this for now as we're probably not going to support webpack-dev-server, but I really want to get hot loader working. Please re-open if you need help on react hot loader :)


## gaearon (31 Jan 2015)

Sure, once I'm more comfortable with the setup I'll take another look!


