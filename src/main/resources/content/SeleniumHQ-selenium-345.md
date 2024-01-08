#Need Serializable InternetExplorerDriver

Owner: SeleniumHQ

Repo: selenium

Labels: D-IE C-java 

## qualityking (18 Mar 2015)

Hi Folks, 
Was Searching google for long hrs to find solution to resume Driver object but didn't find a concrete solution,  so that I thought it would be a good idea to Serialize Driver instance and load it again when we rerun. 
I am trying to achieve this to expedite automation development process, everytime I rerun the test while development I have to close the browser and re launch it again and again this is very annoying. 

So thought that below code would help me, but it is throwing exception that InternetExplorerDriver is not implementing Serializable.

Can someone please help me to fork this class and make it Serializable.

``` java
class WebDriverExtention {
    public static WebDriver getDriverObject(){
        WebDriver driver; 

        File f = new File("c:\\tmp\\driver.ser");
        if (!f.exists()){
            System.setProperty("webdriver.ie.driver","IEDriverServer32.exe");
            driver = new InternetExplorerDriver();
            FileOutputStream fout = new FileOutputStream("c:\\tmp\\driver.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(driver);
            oos.close();
            return driver; 
        }
        else{
            f = null; 
            FileInputStream fileIn = new FileInputStream("c:\\tmp\\driver.ser");
            ObjectInputStream ins = new ObjectInputStream(fileIn);
            driver = (WebDriver) ins.readObject();
            ins.close();
            fileIn.close();
            return driver; 
        }

    }
}
```


## andreastt (18 Mar 2015)

Even if it would be possible to serialise the `InternetExplorerDriver` class state, most of the logic happens in `IEDriverServer32.exe`, which then wraps the browser process.  I can't see how we could guarantee a serialisation of the state in this setup.


## lukeis (18 Mar 2015)

we're not going to support serialization of the drivers, this sounds more like a question for the users group rather than an issue in selenium itself.


