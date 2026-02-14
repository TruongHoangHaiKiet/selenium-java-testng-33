package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_Element_Exercise_Register {
    WebDriver driver;
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_SignUp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/?locale=en");

        // Empty Data
        WebElement signUpBtn = driver.findElement(By.cssSelector("button#create-account-enabled"));
        WebElement closeBtn = driver.findElement(By.cssSelector("div#onetrust-close-btn-container>button.onetrust-close-btn-handler"));
        if (closeBtn.isDisplayed()){
            closeBtn.click();
            signUpBtn.click();
        }
        Thread.sleep(Duration.ofSeconds(2));
        Assert.assertEquals(driver.findElement(By.xpath("//label[@for='email']/following-sibling::span")).getText(),"An email address must contain a single @.");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@for='new_username']/following-sibling::span")).getText(),"Please enter a value");

        // Email Invalid
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail@!$&com");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),"An email address must contain a single @.");

        // Email Invalid
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("123@345.345");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),"The domain portion of the email address is invalid (the portion after the @: 345.345)");

        // Invalid Password - LowerCase
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.net");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());

        // Invalid Password - UpperCase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());

        // Invalid Password - Number
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());

        // Invalid Password - Special Character
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!@#$%");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());

        // Invalid Password - 8 Character
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("automation123");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());

        //Valid Password
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Automation123!@#");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(Duration.ofSeconds(2));

        Assert.assertTrue(driver.findElements(By.cssSelector("li.lowercase-char.completed")).isEmpty());
        Assert.assertTrue(driver.findElements(By.cssSelector("li.uppercase-char.completed")).isEmpty());
        Assert.assertTrue(driver.findElements(By.cssSelector("li.special-char.completed")).isEmpty());
        Assert.assertTrue(driver.findElements(By.cssSelector("li.special-char.completed")).isEmpty());
        Assert.assertTrue(driver.findElements(By.cssSelector("li[class='8-char completed']")).isEmpty());
        Assert.assertTrue(driver.findElement(By.cssSelector("span[class='invalid-error']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
