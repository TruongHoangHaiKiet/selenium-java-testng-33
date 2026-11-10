package testng.loop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Timeout {
    WebDriver driver;
    Random rand;
    String firstName, lastName, emailAddress, password, fullName;

    @BeforeClass
    public void initialBrowser() throws FileNotFoundException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://live.techpanda.org/index.php/");
        driver = new ChromeDriver(chromeOptions);
        rand = new Random();

        firstName = "Joe";
        lastName = "Biden";
        fullName = firstName + " " + lastName;
        password = "123456789";

    }

    @Test(timeOut = 10000, description = "Register to system", invocationCount = 3, enabled = true, groups = "register")
    public void TC_01_Register() throws InterruptedException, IOException {
        driver.get("http://live.techpanda.org/index.php/");
        emailAddress = "joebiden" + rand.nextInt(99999) + "@gmail.com";

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);

        driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

    }

    @AfterClass
    public void afterClass() throws IOException {
        driver.quit();
    }
}
