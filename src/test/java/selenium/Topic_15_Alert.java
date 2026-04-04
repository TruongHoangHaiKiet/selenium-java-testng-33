package selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Alert {
    WebDriver driver;
    // Khi nào điều kiện xuất hiện thì mới khởi tạo
    Alert alert;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // Ví dụ khởi tạo trong method setup hoặc ngay đầu test
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        driver.findElement(By.cssSelector("button.search-button")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();
        Assert.assertTrue(alertMessage.contains("The information you have entered on this page will be sent over an insecure connection and could be read by a third party.\n"));
        Assert.assertTrue(alertMessage.contains("Are you sure you want to send this information?"));
        alert.accept();

        List<WebElement> products = driver.findElements(By.cssSelector("ul.products-grid>li.item"));
        Assert.assertEquals(products.size(),2);
    }

    @Test
    public void TC_03_Prompt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String name = "Automation FC";
        alert.sendKeys(name);
        Thread.sleep(2000);

        alert.accept();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: " + name);
    }

    @Test
    public void TC_04_Authentication_Alert() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
