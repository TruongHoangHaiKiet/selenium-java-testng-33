package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
    public void TC_01_iframe() throws InterruptedException {
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
    public void TC_02_Jquery() throws InterruptedException {
        driver.get("https://jqueryui.com/dialog/");
        // Switch to iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#content iframe.demo-frame")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-dialog")).isDisplayed());
        driver.findElement(By.cssSelector("button.ui-dialog-titlebar-close")).click();
        Thread.sleep(3000);

        Assert.assertFalse(driver.findElement(By.cssSelector("div.ui-dialog")).isDisplayed());
        // Quay ve Main
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input[name='s']")).sendKeys("Dialog");
        Thread.sleep(3000);

    }

    @Test
    public void TC_03_FormSite() throws InterruptedException{
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement((By.cssSelector("img[alt='Campus Safety Survey']"))).click();
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#frame-one85593366")));
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Year')]/following-sibling::select"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Residence')]/following-sibling::select"))).selectByVisibleText("West Dorm");
        new Select(driver.findElement(By.xpath("//label[contains(string(),'Public Safety')]/following-sibling::select"))).selectByVisibleText("Good");
        driver.findElement(By.xpath("//input[@id='FSsubmit']")).isDisplayed();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Class Registration Form')]")).click();

    }

    @Test
    public void TC_04_FormSite() throws InterruptedException{
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.xpath("//frame")));
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("john.terry");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("john.terry2026");
        driver.findElement(By.cssSelector("a.loginBtn")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(),"abc");
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
