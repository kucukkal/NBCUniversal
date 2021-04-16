package com.sample.test.demo.base;
import org.openqa.selenium.WebDriver;
import com.sample.test.demo.configuration.Configuration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LocalDriver {
    private Configuration config;
    private LocalDriver(){}

    private static LocalDriver instance = new LocalDriver();

    public static LocalDriver getInstance()
    {
        return instance;
    }
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue()
        {
            config = new Configuration();
            if (config.getBrowser().equalsIgnoreCase("chrome")) {
                if (config.getPlatform().equalsIgnoreCase("mac")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
                } else {
                    System.setProperty("webdriver.chrome.driver",
                            "src/test/resources/chromedriver/windows/chromedriver.exe");
                }

                return new ChromeDriver(); // can be replaced with other browser drivers
            }
            else {
                Assert.fail("Unsupported browser " + config.getBrowser());
                return null;
            }
        }

    };

    public WebDriver getDriver()
    {
        return driver.get();
    }
    public void removeDriver()
    {
        driver.get().quit();
        driver.remove();
    }
}

