#Initial Page Loading (IE/Chrome) takes huge time if launching via Selenium 

Owner: SeleniumHQ

Repo: selenium

Labels: D-IE I-needs investigation 

## qualityking (20 Mar 2015)

Hi Folks, 
I am trying to open one of my heavy web application using selenium, and facing considerable performance issue. 
when i am opening the same application without selenium it works very very fast, not sure why such issue, (please note i am new to this community, i might be missing something here) 

I am also forcing browser to cache the contents, but looking into fiddler contents are also not getting cached.. have spent now a complete day with no satisfaction.. please help 

PS : Do we have any active community forum where we get answers from active users. 

``` java

static NewInternetExplorerDriver driver; 

    @BeforeClass
    public static void launchDriver(){
        System.setProperty("webdriver.ie.driver","IEDriverServer32.exe");
        //System.setProperty("webdriver.chrome.driver", "chromedriver32.exe");

        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();  
        cap.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, false);
        cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, false);
        cap.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);


        Capabilities cap1 = (Capabilities) cap; 

        driver = new NewInternetExplorerDriver(cap1);
        //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);


    }

    @Test
    public void Step1_LaunchApplication() throws InterruptedException {
        driver.get("http://server23:8086/home");
        System.out.println("Loading Completed"); 
        Thread.sleep(2000); 
    }
```


## lukeis (20 Mar 2015)

This is a question rather than an issue. Please send questions to the [selenium user group](https://groups.google.com/forum/#!forum/selenium-users)

For issues please provide a [concise reproducible test case](http://sscce.org/) and describe what results you are seeing and what results you expect.

See [CONTRIBUTING.md](https://github.com/SeleniumHQ/selenium/blob/master/CONTRIBUTING.md#issue-contributions)


