#Unable to get pop up after uploading a file using AutoIt

Owner: SeleniumHQ

Repo: selenium

Labels: 

## SunilGhargaonkar (13 Mar 2015)

Scenario: After uploading an invalid file (apart from image file) using AutoIt, pop up is not displaying. Uploading a proper file (any image file) works fine

Browser: Firefox 34.0.5
Selenium Library Version: selenium-server-standalone-2.42.2
Autoit: autoit-v3

Steps: 
1. The test is performed on "www.pspsrint.com" portal using Guest account
2. When I try to click on any product e.g "Business Card" and try to upload invalid file I don't get an error popup

<div id="ui-id-5" class="dialog-window message-dialog ui-dialog-content ui-widget-content" style="width: auto; min-height: 118px; max-height: none; height: auto;" data-bind="dialog: messageDialogOptions, dialogVisible: isMessageDialogVisible">

My Code is as below

public static  WebDriver openfirefox;
@Test(priority = 1)
public void openLink() {  
                openfirefox.get(https://www.psprint.com/);//Opens the URL
        openfirefox.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// Wait
}
@Test(priority = 2)
public void upload() throws InterruptedException {
        if(isElementPresent(By.id("fileupload_front1"))) {
        upload("front");
  }
}

private void upload(String side) {
        WebElement error = openfirefox.findElement(By.xpath(Constant.CloseError));
        if(side == "front"){
        openfirefox.findElement(By.id("fileupload_front1")).click();// Clicks on Upload now button
        Runtime.getRuntime().exec(Constant.UploadPath);
        openfirefox.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   }
        if(error.isDisplayed()) {
        openfirefox.findElement(By.xpath(CloseError)).click();//Close the error window
   }
}


## barancev (13 Mar 2015)

Please provide a _complete_ executable scenario to reproduce the issue instead of narrative description of the actions like "When I try to click on any product e.g "Business Card""


## SunilGhargaonkar (13 Mar 2015)

Hello Barancev ,

Thank you for considering this bug, please find the below steps to reproduce the bug
1. open the URL "www.psprint.com"
2. Click on Business Cards -> Standard business Cards Product link
3. Click on Upload Now button
4. Select a file with (.txt or .doc or .exe) extension
5. Click on Open button ( to upload selected file) and observe

Expected Result: An Error popup should display for invalid files

Note: Below code with xpath

public static  WebDriver openfirefox;

@Test(priority = 1)
public void openLink() {
    openfirefox.get("https://www.psprint.com/");//Opens the URL
}

@Test(priority = 2)
public void businessCard() {
    WebElement products =       openfirefox.findElement(By.xpath("/html/body/div[1]/div[2]/div/ul/li[1]/a"));// Hover over products link
    Actions action = new Actions(openfirefox);
    action.moveToElement(products).build().perform();
    openfirefox.findElement(By.xpath("//_[@class = 'has-dropdown']/div/ul/li[1]/ul/li[4]/a")).click();//Clicks on Business Card
    openfirefox.findElement(By.xpath("//_[@class = 'section-content clearfix']/div[1]/div/a/div")).click();//Clicks on Standard Business Card
}

@Test(priority = 3)
public void uploadImage() {
    if(isElementPresent(By.id("fileupload_front1"))) {
        upload("front");// Passing the argument to upload method
    }
}

private void upload(String side) {
    WebElement error = openfirefox.findElement(By.xpath(/html/body/div[11]/div[2]));
    if(side == "front"){
        openfirefox.findElement(By.id("fileupload_front1")).click();// Clicks on Upload now button

```
    if(error.isEnabled()) {
        System.out.println("------Invalid file type has been uploaded.----");// Prints invalid file upload
        openfirefox.findElement(By.xpath(Constant.CloseError)).click();//Close the error window
    }
}
```

}


## barancev (13 Mar 2015)

I've executed the same scenario with Selenium, but uploaded a file by hands instead of AutoIt -- the error box appears. So the issue is related to your AutoIt script, not Selenium.


## SunilGhargaonkar (13 Mar 2015)

Hi Barancev,

Thank you so much, I got the issue it was related to AutoIt only. :)

Thanks,
Sunil


