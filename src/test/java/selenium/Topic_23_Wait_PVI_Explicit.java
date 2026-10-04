package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_PVI_Explicit {
    // Khai báo
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Implicit() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='2']")));
        driver.findElement(By.xpath("//td/a[text()='2']")).click();

        // Wait loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='2']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
                "Wednesday, September 2, 2026");
    }

    @Test
    public void TC_02_Equal() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ct100_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to Display.");
        driver.findElement(By.xpath("//td/a[text()='2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='span#ct100_ContentPlaceholder1_Label1' and text()='Tuesday, June 2, 2026']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ct100_ContentPlaceholder1_Label1")).getText(),
                "Tuesday, June 2, 2026");
    }

    @Test
    public void TC_03_Equal() {
    }

    @Test
    public void TC_4_MoreThan() {
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
