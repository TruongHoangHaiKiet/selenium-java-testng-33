package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Element_Exercise_I {
    WebDriver driver;
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Displayed_Enabled_Selected() {
        // Áp dụng cho tất cả các loại Element
        driver.get("https://www.fahasa.com/customer/account/login/");
        driver.findElement(By.cssSelector("li.popup-login-tab-register")).click();

        // Phone: Hiển thị + enabled (có thể thao tác lên được)
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone_otp")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_password")).isDisplayed());

        // Mong đợi không hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("input#login_username")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#login_password")).isDisplayed());

        // Mong đợi thao tác được
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone")).isEnabled());

        // Mong đợi không thao tác được
        Assert.assertFalse(driver.findElement(By.cssSelector("input#register_phone_otp")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#register_password")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-register")).isEnabled());

        driver.get("https://live.techpanda.org/index.php/customer/account/create/");

        // Mong đợi nó chưa chọn
        Assert.assertFalse(driver.findElement(By.cssSelector("input#is_subscribed")).isSelected());
        driver.findElement(By.cssSelector("input#is_subscribed")).click();

        // Mong đợi nó chọn rồi
        Assert.assertTrue(driver.findElement(By.cssSelector("input#is_subscribed")).isSelected());
    }

    @Test
    public void TC_02_Login_Empty() {
        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
    }

    @Test
    public void TC_03_Login_Invalid_Email() {
        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("123@222.444");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "");
    }

    @Test
    public void TC_04_Login_Invalid_Password() {
        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("dam@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_05_Login_Incorrect() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
