package testng.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Alway_Run {
    WebDriver driver;
    @BeforeClass
    public void initBrowserDriver(){
        System.out.println("Init browser and driver!!!");
        driver = new FirefoxDriver();
        Assert.assertTrue(false);
    }

    @Test
    public void TC_01_New_Card(){

    }

    @Test
    public void TC_02_Update_Card(){

    }

    @AfterClass(alwaysRun = true)
    public void cleanBrowserDriver(){
        if (driver != null){
            System.out.println("Closed browser and driver!!!");
            driver.quit();
        }
    }
}
