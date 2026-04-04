package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_14_Custom_Checkbox_Radio {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        // 1 - Thẻ input bị ẩn không click được - lại dùng để verify được
        // Thẻ input có thể verify selected
//      By ubuntuAccountRadio = By.xpath("//span[text()='I don’t have an Ubuntu One account']/parent::label/preceding-sibling::input");

        // 2 - Thẻ input ko dùng để click chọn nữa - chỉ dùng để verify
        // Dùng thẻ label đại diện để click
        // Nhưng label lại không verify được
//        By ubuntuAccountRadio = By.xpath("//label[@class='new-user']");
//        driver.findElement(ubuntuAccountRadio).click();
//        Thread.sleep(4000);

        // 3 - Thẻ label để chọn - Thẻ input dùng để verify
        //        By ubuntuAccountRadioInput = By.xpath("//span[text()='I don’t have an Ubuntu One account']/parent::label/preceding-sibling::input");
        //        By ubuntuAccountRadioLabel = By.xpath("//label[@class='new-user']");
        //        driver.findElement(ubuntuAccountRadioLabel).click();
        //        Assert.assertTrue(driver.findElement(ubuntuAccountRadioInput).isSelected());
        //        Thread.sleep(4000);

        // 4 - Không dùng click của Selenium
        // Vẫn define checkbox/ radio thành 1 locator (Không có tách 1 element bằng nhiều cách bắt locator)
        By ubuntuAccountRadioInput = By.xpath("//span[text()='I don’t have an Ubuntu One account']/parent::label/preceding-sibling::input");

        // Click
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(ubuntuAccountRadioInput));
        Thread.sleep(4000);
        Assert.assertTrue(driver.findElement(ubuntuAccountRadioInput).isSelected());
    }

    @Test
    public void TC_02_Google_Form() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        // Radio
        By canthoCity = By.xpath("//div[@aria-label='Cần Thơ']");
        Assert.assertEquals(driver.findElement(canthoCity).getDomAttribute("aria-checked"), "false");
        driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(canthoCity).getDomAttribute("aria-checked"), "true");

        // Checkbox
        By QuangNgaiCity = By.xpath("//div[@aria-label='Quảng Ngãi']");
        Assert.assertEquals(driver.findElement(QuangNgaiCity).getDomAttribute("aria-checked"), "false");
        driver.findElement(By.xpath("//div[@aria-label='Quảng Ngãi']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(QuangNgaiCity).getDomAttribute("aria-checked"), "true");


    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
