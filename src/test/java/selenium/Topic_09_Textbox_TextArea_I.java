package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_09_Textbox_TextArea_I {
    WebDriver driver;
    String firstName, lastName, emailAddress, password, fullname, reviewProduct;

    @BeforeClass
    public void initBrowser() {
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        firstName = "John";
        lastName = "Kennedy";
        fullname = firstName + " " + lastName;
        emailAddress = "johnkennedy" + new Random().nextInt(9999) + "@gmail.com";
        password = "123456";
        reviewProduct = "Good Phone\nBest Quality";

    }

    @Test
    public void TC_01_TechPanda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullname));
        Assert.assertTrue(contactInfo.contains(emailAddress));
        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getDomAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getDomAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getDomAttribute("value"), emailAddress);

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.cssSelector("h2>a[title='IPhone']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys(reviewProduct);
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys(reviewProduct);
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(fullname);
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");
    }
    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
