#Cannot interact with Chromium sometimes

Owner: SeleniumHQ

Repo: selenium

Labels: 

## jsfan (17 Mar 2015)

Ubuntu 14.04 recently pushed a security update which updated Chromium and Chrome Driver from version 40.0.2214.111 to 41.0.2272.76. Ever since, I am experiencing erratic problems with reaching Chromium. Basically, what happens is that Chromium starts up but a get() does not result in any action and the associated promise is never fulfilled. I am using explicit flows.

Downgrading to the vulnerable version works but, obviously, that version is no longer in the repositories. The only alternative version would be 34.0.1847.116 which does not work with selenium-webdriver 2.44+ (timeout when trying to reach Chromium) and I am not sure what version of selenium-webdriver would be compatible with this version of Chromium.

The code roughly looks like this (in Coffeescript):

```
webdriver = require "selenium-webdriver"
flow = new webdriver.promise.ControlFlow().
          on "uncaughtException", (e) ->
              console.log "Uncaught exception:", e
browser = new webdriver.Builder().
                withCapabilities(webdriver.Capabilities.chrome().set("chromeOptions", {"args" : ["--incognito"]})).
                setControlFlow(flow).
                build()
browser.get "https://www.google.com"
```


## andreastt (17 Mar 2015)

chromedriver is maintained by the Chromium project, please report the bug against their bug tracker: https://code.google.com/p/chromedriver/


