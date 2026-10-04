package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_23_Wait_PVI_Explicit_II {
    // Khai báo
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFile = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
    JavascriptExecutor jsExecutor;

    String firstImage = "see.jpg";
    String secondImage = "Test.jpg";
    String thirdImage = "see_beach.jpg";

    String firstImagePath = uploadFile + firstImage;
    String secondImagePath = uploadFile + secondImage;
    String thirdImagePath = uploadFile + thirdImage;

    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_LessThan() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Trước khi click thì wait clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon đang hiển thị -> wait cho loading icon biến mất
        // 1 - Chờ cho step trước được hoàn thành -> Rồi mới qua các step (Không quân tâm cái stip)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // Trước khi getText thi wait for text
        // 2- Sau khi step trước được bắt đầu
        // -> Nó sẽ chờ cho 1 đói tượng của step sau được xuất hiện (Không quan tâm cái step trước )
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"),"Hello World!"));
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#finish>h4"), "Hello World!"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_02_Equal() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Trước khi click thì wait clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon đang hiển thị -> wait cho loading icon biến mất
        // 1 - Chờ cho step trước được hoàn thành -> Rồi mới qua các step (Không quân tâm cái stip)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // Trước khi getText thi wait for text
        // 2- Sau khi step trước được bắt đầu
        // -> Nó sẽ chờ cho 1 đói tượng của step sau được xuất hiện (Không quan tâm cái step trước )
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_03_Upload() {
        driver.get("https://gofile.io/");
        // Wait cho tất cả các Loading Icon trên màn hình không còn xuất hiện nữa
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.animate-spin.rounded-full")))));

        // Wait cho File Manager button được thao tác (Click)
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a.gap-4 span.size-9")))).click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div.mt-5>button.btn-primary"))));
        driver.findElement(By.cssSelector("div#fm-toolbar button[data-action='create-folder']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#fm-new-folder-name"))).sendKeys("Selenium");
        driver.findElement(By.cssSelector("footer.items-center button.btn-primary")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.animate-spin.rounded-full")));
        String successText = driver.findElement(By.cssSelector("div.min-w-0>p.font-semibold")).getText();
        Assert.assertTrue(successText.contains("Folder") && successText.contains("Selenium") && successText.contains("created"));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.min-w-0>p.font-semibold")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Selenium']")).isDisplayed());
        driver.findElement(By.xpath("//p[text()='Selenium']")).click();

        // Wait cho tất cả các Loading Icon trên màn hình không còn xuất hiện nữa
        explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.rounded-full"))));
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstImagePath + "\n" + secondImagePath + "\n" + thirdImagePath);
        explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.overflow-hidden.rounded-full"))));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'truncate') and text() ='" + firstImage + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(@class,'truncate') and text() ='see.jpg']")).isDisplayed());

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'truncate') and text() ='"+ secondImage + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(@class,'truncate') and text() ='Test.jpg']")).isDisplayed());

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'truncate') and text() ='" + thirdImage +"']")));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(@class,'truncate') and text() ='see_beach.jpg']")).isDisplayed());
    }

    @Test
    public void TC_4_MoreThan() {
    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
