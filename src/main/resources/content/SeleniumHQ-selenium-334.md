#Unnecessary lock.unlock() in FirefoxDriver.java

Owner: SeleniumHQ

Repo: selenium

Labels: 

## Denis-Iskhakov (16 Mar 2015)

I believe that there is unnecessary lock.unlock() method call in org.openqa.selenium.firefox.FirefoxDriver.java (selenium/java/client/src/org/openqa/selenium/firefox/FirefoxDriver.java) at line 282 in finally block. lock.lock(..) is never called before said unlock.

According to file history it was necessary earlier when lock was actually locked before that unlock. But now locking is performed in NewProfileExtensionConnection in start() method.


## andreastt (16 Mar 2015)

👍


