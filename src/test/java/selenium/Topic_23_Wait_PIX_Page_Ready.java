package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_23_Wait_PIX_Page_Ready {
    // Khai báo
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() {
        driver.get("https://admin-demo.nopcommerce.com");
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        Assert.assertTrue(isPageLoadedSuccess());
    }

    @Test
    public void TC_02(){
        driver.get("https://api.orangehrm.com/");
        Assert.assertTrue(isLoadingIconInvisible());
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).getText(),"OrangeHRM REST API Documentation");
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Điều kiện 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);") == true;
            }
        };

        // Điều kiện 2
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }


    public boolean isLoadingIconInvisible(){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(100));
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
    private long longTimeout = 15;
    private long pollingTime = 100;

}
