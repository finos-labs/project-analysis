#[WebDriverJS] - driver.wait behavior when timeout = 0 is confusing

Owner: SeleniumHQ

Repo: selenium

Labels: R-needs code changes I-papercut 

## juliemr (16 Mar 2015)

With version 2.45.1, the behavior when the timeout is unset for `driver.wait` is confusing. It will wait indefinitely if the condition is a promise, but waits for a time of 0 if the condition is a function:

``` js
var promise = require('selenium-webdriver').promise;

var donePromise = promise.delayed(1000);
promise.controlFlow().wait(donePromise); // This waits just fine.

var done = false;
setTimeout(function() {
  done = true;
}, 1000);

promise.controlFlow().wait(function() {
  return done;
}); // This times out and throws an error.
```

Should a timeout of 0 also cause `wait` to wait indefinitely if the condition is a function?


## andreastt (16 Mar 2015)

I sense @jleyba wants to answer.


## jleyba (16 Mar 2015)

Eh, I guess. Pull requests welcome.


## jleyba (20 Mar 2015)

Should a zero-length timeout be treated the same as an undefined/null timeout?

```
flow.wait(condition);     // Waits indefinitely
flow.wait(condition, 0);  // Waits indefinitely or 1 turn of the event loop?
```


