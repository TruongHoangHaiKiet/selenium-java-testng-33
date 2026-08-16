package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_PI_Element_Status {
    WebDriver driver;
    WebDriverWait driverWait;

    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Visible() {
        // Điều kiện 1: Element có trên UI và có trong HTML
        // Email Address error message xuất hiện
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector(("button#send2"))).click();
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("advice-required-entry-email")));
        Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_Element_Found_Found_HTML () {
        // Điều kiện 2: Element không có trên UI nhưng có trong HTML
        // Email Address error message không xuất hiện nhưng vẫn có trong HTML
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector(("button#send2"))).click();
        driver.findElement(By.id("email")).sendKeys("kietthh@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("advice-required-entry-email")));
        Assert.assertFalse(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
    }

    @Test
    public void TC_03_Invisible_Element_Not_Found_HTML() {
        // Điều kiện 3: Element không có trên UI và cũng không cos trong HTML
        // Email Address error message không có trên UI và không còn trong HTML
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector(("button#send2"))).click();

        driver.findElement(By.id("email")).sendKeys("kietthh@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.switchTo().alert().accept();

        // Step này sẽ chạy lâu vì cần tìm element (findElement) mà element lại không có trong HTML
        // Chờ và tìm đi tìm lại cho đến khi hết timeout
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("advice-required-entry-email")));

        // Step này fail ngay bước tìm element
        // Phần 1: driver.findElement(By.id("advice-required-entry-email")))
        // Tìm thấy element thì qua phần 2 - không tìm thấy thì đánh fail
        // Phần 2: Check isDisplayed()
        // Nếu có trên UI trả về true/ không có trả về false
        // Phần 3: assert giá trị trả  về
        //Assert.assertFalse(driver.findElement(By.id("advice-required-entry-email")).isDisplayed();
    }

    @Test
    public void TC_04_Presence_Found_UI() {
        // Điều kiện 1: Element có trên UI và không có trong HTML
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector(("button#send2"))).click();
        driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("advice-required-entry-email")));

        // Điều kiện 2: Element không có trên UI nhưng có trong HTML
        driver.findElement(By.id("email")).sendKeys("kietthh@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("advice-required-entry-email")));
        // Miễn sao element có trong HTML = presence
    }

    @Test
    public void TC_04_Staleness() {
        // Tại thời điểm A element đang xuất hiện = lưu element lại
        // Tại thời điếm B element không còn xuất hện trong HTML nữa = dùng element đã lưu tại thời điểm A check
        // element đó staleness tại thời điếm B
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector(("button#send2"))).click();
        WebElement emailErrorMessage = driver.findElement(By.id("advice-required-entry-email"));

        driver.findElement(By.id("email")).sendKeys("kietthh@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();
        driver.switchTo().alert().accept();

        // Điều kiện 3: Element không có trên UI và không xuất hiện trong HTML
        driverWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
