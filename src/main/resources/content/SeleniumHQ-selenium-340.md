#Focused input not allowing multiple delete / backspace keypresses

Owner: SeleniumHQ

Repo: selenium

Labels: E-less easy D-firefox 

## Chekote (16 Mar 2015)

**What steps will reproduce the problem?**

Create a fixture html file called "focustest.html" with the following content:

``` html
<!DOCTYPE html>
<html>
<head>
    <title>Focus Test</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
</head>
<body>
    <input id="field" name="data" value="Initial Value" type="text" />

    <button id="focusbtn" onclick="doFocus()">Focus</button>

    <script>
      function doFocus() {
        document.getElementById('field').focus();
      }
    </script>
</body>
</html>
```

Create a PHPUnit test called "test.php" with the following content:

``` php

class WebTest extends PHPUnit_Extensions_Selenium2TestCase {

  function setUp() {
    $this->setBrowser('firefox');
    $this->setBrowserUrl('http://localhost/');
  }

  function testFillFocusedField() {
    $value = 'Chekote';

    $this->url('/focustest.html');
    $this->byId('focusbtn')->click();

    // get the field value current length, and add backspace/delete keystrokes to remove it
    $field = $this->byId('field');
    $deletes = str_repeat(
      PHPUnit_Extensions_Selenium2TestCase_Keys::BACKSPACE . PHPUnit_Extensions_Selenium2TestCase_Keys::DELETE,
      strlen($field->value())
    );

    $this->keys($deletes . $value);

    $this->assertEquals($value, $field->value());
  }
}
```

Run the test:

phpunit test.php

**What is the expected output?**
I expect the test to pass, and the field's "Initial Value" text to be replaced with "Chekote"

**What do you see instead?**
The first character of the Initial Value (The "I") is deleted, but no other characters are deleted. The result is the field have the value "Chekotenitial Value".

Selenium version: 2.45
OS: Mac OS X 10.10.2
Browser: Firefox
Browser version: 31.4.0esr, 33.1.1, 34.0.5 & 36.0.1

This problem was first discovered and discussed in the MinkSelenium2Driver issues board here:

https://github.com/minkphp/MinkSelenium2Driver/issues/198

It was also originally reported here. I closed it thinking a typo was causing the tests to fail, but the typo was not the only reason it was failing. Sorry for the confusion!

https://code.google.com/p/selenium/issues/detail?id=8589


## ddavison (16 Mar 2015)

From the looks of it, this looks out of the scope of this project, since the PHP bindings are housed separately.  You might want to open an issue to the author of your PHP bindings.

Additionally, is there anything preventing you from doing a `clear()` **then** `keys()`?  Or are you explicitly testing this functionality.


## Chekote (16 Mar 2015)

I was thinking that myself. If I can re-create the issue with just Java, would that be sufficient to start an investigation?

In regards to the clear(). I'm actually using Behat, and the Selenium2 driver for that used to use clear() but [recently removed it](https://github.com/minkphp/MinkSelenium2Driver/commit/23ec6b4d4aaba1a6de8affe097a95682b90022bf). In fact that's what started this whole investigation.


## ddavison (16 Mar 2015)

Unless a core-contributor thinks otherwise - i think that's a great place to start, as it doesn't sound like core functionality that is breaking.


## jleyba (16 Mar 2015)

Firefox likes to ignore focus/blur events when it is not the active application. I'd wager your test would pass if you made sure Firefox was active while it ran. If this is what's happening, there is nothing we can do - especially on OSX. The behavior is rooted within the browser and cannot be changed (things might be different with Marionette, Mozilla's WebDriver implementation)


## Chekote (17 Mar 2015)

@ddavison OK, I knocked up a quick Java example [here](https://github.com/Chekote/selenium_tests). The problem persists there also. Please let me know if you need anything else.

@jleyba Thanks for the input. I'll take a look into that! I'll let you know what I find.


## Chekote (17 Mar 2015)

@jleyba 

If I understood you correctly, I believe I have verified that Firefox being focused does not affect the result of the test. I added a sleep in the test (see below) so I could manually ensure that Firefox was focused before it continued, and the results were the same.

``` patch
diff --git a/src/main/java/com/fuzzycoder/FocusTest.java b/src/main/java/com/fuzzycoder/FocusTest.java
index 4186363..b92ca59 100644
--- a/src/main/java/com/fuzzycoder/FocusTest.java
+++ b/src/main/java/com/fuzzycoder/FocusTest.java
@@ -12,6 +12,12 @@ public class FocusTest  {

         driver.get("http://localhost/focustest.html");

+        try {
+            Thread.sleep(5000);
+        } catch (InterruptedException e) {
+            e.printStackTrace();
+        }
+
         driver.findElement(By.id("focusbtn")).click();

         WebElement input = driver.findElement(By.id("field"));
```


## barancev (30 Mar 2015)

Reproduced for multiple Delete keys.

Works well for multiple Backspaces:

```
  int length = input.getAttribute("value").length();
  StringBuffer sb = new StringBuffer();
  sb.append(Keys.END);
  for (int i = 0; i < length; ++i) {
    //sb.append(Keys.DELETE);
    sb.append(Keys.BACK_SPACE);
  }
```


## Chekote (08 Apr 2015)

Does anyone have any insights as to what the cause of this might be? Even some insights into where in the Selenium codebase I should start looking would be greatly appreciated.


## lukeis (08 Apr 2015)

@Chekote here's the entry point into FirefoxDriver:
https://github.com/SeleniumHQ/selenium/blob/master/javascript/firefox-driver/js/wrappedElement.js#L178

note this is inside the firefox extension so it has elevated privileges (slightly different than executing javascript directly on a page).

Also it calls Utils.type which is here:
https://github.com/SeleniumHQ/selenium/blob/master/javascript/firefox-driver/js/utils.js#L290

I have not looked into this issue, so I don't have any particular insights to where the problem actually is, but it'll be in one of those somewhere ^  :)


## Chekote (08 Apr 2015)

@lukeis Thanks for the quick response! I'll go dig around in there now.


## Chekote (08 Apr 2015)

I'm wondering if I can get some pointers on how to effectively debug the methods in the FirefoxDriver. I have no experience with Closure, or WebDriver development. But all I really need to do to get started is be able to either debug the running code with IntelliJ's inspector, or put log entries in the code and review them.

I have no idea how to do the former with Selenium's codebase, and so I feel the log entries would be a quicker route. I've seen various references to `goog.log` in the wrappedElement.js file, and I've ensured that `goog.debug.LOGGING_ENABLED` is set to true, but I can't seem to work out where these log entries should appear. They aren't visible in the terminal window where Selenium is running, and they aren't visible in the Firefox developer console.

I added the following line into WebElement.sendKeysToElement:

``` javascript
goog.log.info(WebElement.LOG_, 'Sending Keys');
```

Any pointers on what I'm doing wrong, or any docs I should go read?

Thanks.


## lukeis (08 Apr 2015)

seems like a useful post about debugging: http://stackoverflow.com/a/1079453  (in generic terms, you are trying to debug a Firefox extension, .xpi)

but i think i usually resort to console logging.


## Chekote (08 Apr 2015)

@lukeis 

Thanks again for the input. I read the info you referenced and managed to get Firefox configured to work with the sources of the selenium extension.

Unfortunately, it seems that wrappedElement.js is not part of the extension. The extension appears to be located in `javascript/firefox-driver/extension`, but wrappedElement.js is located in `javascript/firefox-driver/js`. So I don't seem to be able to use the articles on XPI debugging to help with this particular issue.

I did originally try to just access the console, but that isn't defined in the scope of wrappedElement.js. The DOM does not appear to be either, so I can't create a `goog.debug.FancyWindow` to view the logs because `document` is not accessible either.

It looks like I might just be out of my depth for this particular issue. I'd really love to investigate this, but I don't feel like I can be effective without better understanding of Closure, Firefox extensions, and Selenium in general. Especially what's going on with what appears to be a non-xpi "thing" that is wrappedElement.js.


## cbe (24 Sept 2015)

I'm having the same issue, are there any news on this?

OS: Mac 10.10.5
Selenium Version: 2.47.1
Browser: Firefox 40.0.3


## aik099 (24 Sept 2015)

> https://github.com/SeleniumHQ/selenium/issues/340#issuecomment-87830574

@barancev , does DELETE key work, when run test on OSX? Maybe DELETE key have no effect, because input cursor is on last symbol?


## Chekote (24 Sept 2015)

@aik099 it's definitely not that. My test illustrating the problem uses both backspace and delete, so it doesn't matter where the cursor is.

I haven't looked into this since my last post, so I have no idea if it's been fixed or not. You can try out the test I wrote [here](https://github.com/Chekote/selenium_tests) against the latest Selenium version to see if it's fixed.


## aik099 (24 Sept 2015)

Most likely not fixed because @cbe is getting same error on latest version.


## Chekote (24 Sept 2015)

@cbe: The earlier [post](https://github.com/SeleniumHQ/selenium/issues/340#issuecomment-87830574) by @barancev used a workaround of sending an "END" keypress and using only backspaces to clear the field. That might be a workaround you can use.


## cbe (25 Sept 2015)

@Chekote thanks for your tip. That's a workaround I've tried, too. I came up with [another workaround](https://github.com/minkphp/MinkSelenium2Driver/pull/217/files) which also works for `textarea`.


## Chekote (10 Feb 2016)

I'm not sure at exactly which version this was fixed, but it's working now with Firefox 44 and Selenium 2.51. Whoever fixed it: thank you!


