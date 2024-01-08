#Browser window stops responding after driver process dies

Owner: SeleniumHQ

Repo: selenium

Labels: C-java I-needs investigation 

## rd1992 (18 Mar 2015)

Hi Selenium Devs, 
I am using chromedriver and ran into  the problem of process dying which results in the browser window to stop responding. I filed a bug with the chromedriver project but they referred me here. To see the actual thread please go [here](https://code.google.com/p/chromedriver/issues/detail?id=1043)

To reproduce: 
fire up a chrome window via chromedriver
kill the chromedriver process manually
try navigating to another url

I tried to check for if driver == null or call driver.getUrl() but that does not seem to work either.

Ideally there should be an exception thrown if the driver process was killed


## andreastt (18 Mar 2015)

Which language binding do you use?


## rd1992 (18 Mar 2015)

i used java


## Krishnanand-codewriter (18 Mar 2015)

Hi rajan,

Can you correct me, if I'm wrong. Is killing chrome driver process manually
is similar to terminating script?
 On 18-Mar-2015 4:48 pm, "Rajan Dhir" notifications@github.com wrote:

> i used java
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/SeleniumHQ/selenium/issues/344#issuecomment-82908867.


## ddavison (18 Mar 2015)

There **is** an exception that is thrown here.

`org.openqa.selenium.WebDriverException: chrome not reachable`

per your steps to reproduce.. I wasn't able to reproduce.  I got an exception stating that chrome was not reachable


## ddavison (18 Mar 2015)

I'm sorry no.. that exception is thrown if the **browser** is closed.  if you force close **chromedriver**, then I get this exception:

`org.openqa.selenium.remote.UnreachableBrowserException: Error communicating with the remote browser. It may have died.`

I get the same exception when i run `driver.getCurrentUrl()` after closing chromedriver.


## rd1992 (18 Mar 2015)

@ddavison could you tell me what version of chromedriver you are using? 2.14 or earlier? Funny thing am not getting this exception. But am on 2.11


## ddavison (18 Mar 2015)

I was using `2.14`, and I built the latest selenium from source and ran it using that.


## ddavison (18 Mar 2015)

actually.. I literally **just** realized that the windows are still open ... they were hidden in my dock.

They are by no means "not responding" I'm able to click through them, and close the windows.

If the driver process closes before the `quit()` method is called, then Selenium has **no** way of interacting with the window.

Selenium ->  Chromedriver -> Chrome

If the `Chromedriver` piece dies, Selenium can't do anything about the stray chrome window since that was "driven" by Chromedriver.


## barancev (18 Mar 2015)

@rd1992 What do you get as the result of driver.getUrl() if you call it after killing chromedriver?


## rd1992 (18 Mar 2015)

@barancev that call does not return ever. Its just stuck there.
@ddavison yeah am getting the same behavior. what i meant was that since the chrome-driver process was killed, the desired behavior would be that the chrome window chrome-driver was handling should also close. 


## ddavison (18 Mar 2015)

then that indeed should go in the chromedriver issues, though my gut says they can't do anything about it either since you seem to be arbitrarily killing the chromedriver process for no reason, and when you force quit something, those side-effects tend to happen.


## lukeis (18 Mar 2015)

please log chromedriver issues with the chromedriver project:

https://code.google.com/p/chromedriver/issues/list


