package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

public class Topic_23_Wait_PVIII_Fluent {
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
        driver.get("https://automationfc.github.io/dynamic-loading/");
        waitAndFindElement("//div[@id='start']/button").click();
        waitAndGetText("//div[@id='finish']/h4");
        Assert.assertEquals(waitAndGetText("//div[@id='finish']/h4"),"Hello World!");

    }

    @Test
    public void TC_02(){
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countdown = waitAndFindElement("//div[@id='javascript_countdown_time']");
        Assert.assertTrue(countDownText(countdown));
    }

    private String waitAndGetText(String xpathLocator) {
        // 2- Khai báo + Khởi tạo
        FluentWait<WebDriver> driverFluent = new FluentWait<WebDriver>(driver);
        // 3- Set timeout
        driverFluent.withTimeout(Duration.ofSeconds(longTimeout)) // Tổng Time = 10s
                .pollingEvery(Duration.ofMillis(pollingTime)). // 1/10s tìm lại điều kiện 1 lần nếu không thấy
                ignoring(NoSuchElementException.class); // Nếu kiểm tra điều kiện chưa đáp ứng và gặp exception thì ignore đi

        // 4 - Điều kiện
        return driverFluent.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath(xpathLocator)).getText();
            }
        });
    }

    // Tìm 1 element
    // T là gì: cần driver mới tìm element được (R)
    // R là gì: cái cần lấy ra (WebElement)
    private WebElement waitAndFindElement(String xpathLocator){
        FluentWait<WebDriver> driverFluent = new FluentWait<WebDriver>(driver);
        driverFluent.withTimeout(Duration.ofSeconds(longTimeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);
        return driverFluent.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath(xpathLocator));
            }
        });
    }

    private WebElement waitElementVisible(String xpathLocator){
        FluentWait<WebDriver> driverFluent = new FluentWait<WebDriver>(driver);
        driverFluent.withTimeout(Duration.ofSeconds(longTimeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);
        return driverFluent.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
    }

    private Boolean countDownText(WebElement element){
        FluentWait<WebElement> driverElement = new FluentWait<WebElement>(element);
        driverElement.withTimeout(Duration.ofSeconds(longTimeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);
        return driverElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                System.out.println(element.getText());
                return element.getText().endsWith("00");
            }
        });
    }


    @Test
    public void TC_02_Only_Explicit() {
    }

    @Test
    public void TC_03_Implicit_Explicit_By() {
    }

    @Test
    public void TC_4_MoreThan() {
    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
    private long longTimeout = 15;
    private long pollingTime = 100;

}
