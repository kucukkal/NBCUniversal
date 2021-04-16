package com.sample.test.demo.base;
import com.aventstack.extentreports.ExtentTest;
import com.sample.test.demo.configuration.Configuration;
import com.sample.test.demo.pages.HomePage;
import com.sample.test.demo.utils.Helper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBasePage {
    //WebDriver driver;
    HomePage homepage;
    private Configuration config;
    protected String url;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();


    Helper helper=new Helper();

    @BeforeMethod(alwaysRun = true)
    public void refresh(){
        config = new Configuration();
        url = config.getUrl();
        WebDriver driver=LocalDriver.getInstance().getDriver();
        navigateToSite(driver);
        homepage=new HomePage(driver);
        helper.waitForInputToBeEntered(driver, homepage.cost,"value","0.00",3);
    }

    private void navigateToSite(WebDriver driver) {
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            LocalDriver.getInstance().removeDriver();

        } catch (Exception e) {
            Assert.fail("TearDown");
        }
    }
    public String takeScreenShot() {
        //takes a screenshot of the app
        File scr = ((TakesScreenshot) LocalDriver.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir") + "\\screenShot\\" + System.currentTimeMillis() + ".png";
        System.out.println("projectPath: "+projectPath);
        File destination = new File(projectPath);

        try {
            FileUtils.copyFile(scr, destination);
        } catch (IOException e) {

            e.printStackTrace();
        }

        return projectPath;
    }


}
