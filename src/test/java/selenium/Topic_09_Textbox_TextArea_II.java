package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_09_Textbox_TextArea_II {
    WebDriver driver;
    By loadingIcon = By.cssSelector("div.oxd-loading-spinner");
    String firstName,lastName, employeeID, userName, password, passportNumber, passportComment;

    @BeforeClass
    public void initBrowser() {
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        // Chờ cho element xuất hiện để thao tác trong 30s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com");
        firstName = "John";
        lastName = "Phillip";
        userName = "john.phillip" + new Random().nextInt(9999) + "@gmail.com";
        password = "Phillip123!@#";
        passportNumber = "1234-5678-9101";
        passportComment = "123 PO Box\nLos Angeles\nUSA";
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon)));

        // Employee list
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon)));

        // Add Employee
        driver.findElement(By.xpath("//button[contains(string(),'Add')]")).click();
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon)));
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

        // Get Employee ID
        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println("Employee ID = " + employeeID);
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(4000);

        // Verify Text
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-toast-content--success')]/p[text()='Successfully Saved']")).isDisplayed());
        //Loading icon 1 (Add new employee)
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Loading icon 2 (Personal Detail)
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"),employeeID);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(2000);

        //Verify text
        Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-toast-content>p.oxd-text--toast-message")).getText(),"Successfully Saved");

        //Loading icon 1 (Add new employee)
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));
        driver.findElement(By.xpath("//div[text()='" + passportNumber + "']/parent::div/following-sibling::div//i[contains(@class,'bi-pencil-fill')]")).click();
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getDomProperty("value"),passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getDomProperty("value"),passportComment);
    }
    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
