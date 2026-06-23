package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_Popup {
    WebDriver driver;
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
    public void TC_01_DeHieu_InDOM() throws InterruptedException {
        driver.get("https://dehieu.vn/");

        // Khi code xong phải DEMO chạy được cả 2 trường hợp xảy ra
        // Popup mở r khi vừa mở URL
        // Popup luôn đóng khi vừa mở ra
        // Vì sao element luôn có trong HTML thì handle rất dễ/ đơn gián
        WebElement registerPopup = driver.findElement(By.cssSelector("div.modal-dialog"));
        if (registerPopup.isDisplayed()){
            driver.findElement(By.cssSelector("button.close")).click();
            Thread.sleep(3000);
        }
        String courseName = "Khóa học Lập Trình PLC Mitsubishi";
        driver.findElement(By.cssSelector("input.search-form")).sendKeys(courseName);
        driver.findElement(By.cssSelector("i.fa-search")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3.title>a")).getDomAttribute("title"), courseName);
    }

    @Test
    public void TC_02_VNK_InDOM() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        WebElement marketingPopup = driver.findElement(By.cssSelector("div.pum-container"));
        if (marketingPopup.isDisplayed()){
            driver.findElement(By.cssSelector("button.pum-close")).click();
            Thread.sleep(3000);
            System.out.println("Close popup");
        }
        driver.findElement(By.cssSelector("button.btn-danger")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://vnk.edu.vn/lich-khai-giang/");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(),
                "Lịch Khai Giảng Trung Tâm VNK EDU");
    }

    @Test
    public void TC_03_KMPlayer_InDOM() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        WebElement kmplayerPopup = driver.findElement(By.cssSelector("div.pop-container"));
        if (kmplayerPopup.isDisplayed()){
            driver.findElement(By.cssSelector("span.close_btn_wrap")).click();
            Thread.sleep(3000);
            System.out.println("Close popup");
        }

        new Select(driver.findElement(By.cssSelector("select#selectLang"))).selectByVisibleText("한국어");
        kmplayerPopup = driver.findElement(By.cssSelector("div.pop-container"));
        if (kmplayerPopup.isDisplayed()){
            driver.findElement(By.cssSelector("span.close_btn_wrap")).click();
            Thread.sleep(3000);
            System.out.println("Close popup");
        }
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
