package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Frame_IFrame {
    WebDriver driver;
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void TC_01_() throws InterruptedException {
        // Home (site A)
        driver.get("https://toidicodedao.com/");

        // Switch tu site A qua iframe Facebook (site B)
        // Index de thay doi vi tri khi them/ sua/ xoa
//        driver.switchTo().frame(0);
        // Name/ ID: ko co name/ id hoac ko duy nhat
//        driver.switchTo().frame("f77ac49a012a3ad29");

        // WebElement
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        Thread.sleep(5000);
        // Element link nằm trong iframe Facebook (site B) nên không thể tiìm thấy đựược tại màn hình Home cuả site A
        driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']")).isDisplayed();
        String follower =  driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText();
        Assert.assertTrue(follower.startsWith("393"));

        // Đang trong iframe muôốn quay lại trang trước đó
        driver.switchTo().defaultContent();

        // Driver đang ở màn hình trong Facebook iframe
        driver.findElement(By.cssSelector("div#content-sidebar input.search-field")).sendKeys("Selenium");
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
