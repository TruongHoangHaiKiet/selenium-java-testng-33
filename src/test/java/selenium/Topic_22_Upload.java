package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.json.JsonOutput;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_22_Upload {
    WebDriver driver;
    // 1 - Lấy đường dẫn folder chứa
    String uploadFile = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String firstImage = "see.jpg";
    String secondeImage = "Test.jpg";
    String thirdImage = "see_beach.jpg";
    String fouthImage = "5M.jpg";

    String firstImagePath = uploadFile + firstImage;
    String secondImagePath = uploadFile + secondeImage;
    String thirdImagePath = uploadFile + thirdImage;
    String fouthImagePath = uploadFile + fouthImage;


    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Single_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstImagePath);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(secondImagePath);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(thirdImagePath);

        // Chuyển qua 1 máy windown nào cũng chạy được
        // Chuyển qua 1 máy MAC/ Linux khác chạy được
        // Chạy vs browser nào trên OS nào cũng được

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + secondeImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + thirdImage + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement start: startButton){
            start.click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '" + secondeImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '" + thirdImage + "']")).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_Files() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstImagePath + "\n" + secondImagePath + "\n" + thirdImagePath + "\n" + fouthImagePath);
        // Chuyển qua 1 máy windown nào cũng chạy được
        // Chuyển qua 1 máy MAC/ Linux khác chạy được
        // Chạy vs browser nào trên OS nào cũng được

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + secondeImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + thirdImage + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement start: startButton){
            start.click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '" + secondeImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()= '" + thirdImage + "']")).isDisplayed());

    }


    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
