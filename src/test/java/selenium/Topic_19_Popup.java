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
import java.util.List;

public class Topic_19_Popup {
    WebDriver driver;
    long shortTime = 5;
    long longTime = 10;
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

    @Test
    public void TC_04_TIKI_NotInDOM() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        // Step 01
        List<WebElement> popupContainer = driver.findElements(By.cssSelector("div#VIP_BUNDLE"));
        // Case 1: POPUP có xuất hiện thì cần close đi và qua step tiếp theo
        if (popupContainer.size() > 0 && popupContainer.getFirst().isDisplayed()){
            driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
            Thread.sleep(3000);
            System.out.println("Popup is displayed and closed !!!");
        } else {
            // Case 2: POPUP không xuất hiện thì qua step tiếp theo
            System.out.println("Popup is not displayed !!!");
        }
        // STEP 02
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();

        WebElement loginPopup = driver.findElement(By.cssSelector("div.ReactModal__Content"));
        Assert.assertTrue(loginPopup.isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text() = 'Đăng nhập']")).click();
        driver.findElement(By.xpath("//span[@class='error-mess' and text() = 'Email không được để trống']")).isDisplayed();
        driver.findElement(By.xpath("//span[@class='error-mess' and text() = 'Mật khẩu không được để trống']")).isDisplayed();
        driver.findElement(By.cssSelector("button.btn-close")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);
    }

    @Test
    public void TC_05_NgoaiNgu24h_NotInDOM() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);
        List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.MuiDialog-container>div"));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.MuiDialog-container>div")).isDisplayed());
        Assert.assertTrue(loginPopup.size() > 0 && loginPopup.getFirst().isDisplayed());

        driver.findElement(By.cssSelector("div.input-item input[autocomplete='username']")).sendKeys("automationtesting@gmail.com");
        driver.findElement(By.cssSelector("div.input-item input[autocomplete='new-password']")).sendKeys("Pass111@@@@");
        driver.findElement(By.xpath("//div[contains(@class,'MuiDialog-container')]//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("div.SnackbarContainer-root")).isDisplayed();
        driver.findElement(By.cssSelector("div.MuiPaper-root button.close-btn")).click();
        Thread.sleep(2000);

        setImplicitTimeout(shortTime);
        loginPopup = driver.findElements(By.cssSelector("div.MuiDialog-container>div"));
        setImplicitTimeout(longTime);
        Assert.assertTrue(loginPopup.size() == 0 && loginPopup.isEmpty());

    }

    public void setImplicitTimeout(long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
