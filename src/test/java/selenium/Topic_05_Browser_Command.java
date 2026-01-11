package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_05_Browser_Command {
    WebDriver driver;
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_01_() {
        // Tương tác với Browser
        driver.get("https://live.techpanda.vn"); //***

        // Dùng biến
        String browserLink = "https://live.techpanda.vn";
        driver.get(browserLink);

        // Đóng Browser (Đóng hết all tab/ window)
        driver.quit(); //***

        // Đóng Browser ( Đóng Tab/ windown đang đứng)
        driver.close();
        driver.switchTo().window("");

        // Switch qua 1 window

        // Tìm ra 1 element
        driver.findElement(By.cssSelector("")); //***

        // Tìm ra nhiều element giống nhau
        driver.findElements(By.cssSelector("")); //***

        // Lấy ra URL của page hiện tại
        driver.getCurrentUrl();

        // Lấy ra title của page hiện tại
        driver.getTitle();

        // Lấy ra source code (HTML/ CSS/ JS/...) của page hiện tại
        driver.getPageSource();

        // ================ Handle Tab/ Windows ==============

        // Lấy ra Window/ TabID của page hiện tại
        String loginWindowID = driver.getWindowHandle();
        driver.getWindowHandle();

        // Lấy ra Window/ TabID của tất cả các tab/ window khác
        driver.getWindowHandles();

        // Đóng Browser (Đóng tab/ window đang đứng)
        driver.close();
        driver.switchTo().window(loginWindowID);

        // ================Time out================
        // Chờ cho việc tìm element thành công trong khoảng thời gian cho trước
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));  //***
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        // Chờ cho việc 1 page load thành công trong khoảng thời gian cho trước
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));

        // Chờ cho đoạn script được thực thi thành công trong khoảng thời gian cho trước
        // Chỉ áp dụng cho thư viện: JavascriptExecutor
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(3));

        driver.manage().timeouts().getScriptTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getImplicitWaitTimeout();

        //================Browser Window ===========================
        // Phóng to/ thu nhỏ/ tràn viền Browser khi lên chạy
        driver.manage().window().maximize();  //***
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();

        // Vị trí của Browser trên màn hình
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Kích thước chiều rộng/ cao của Browser
        driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().window().getSize();

        // ================Log===================
        // Lấy ra tất cả các loại Log Type
        Set<String> logType = driver.manage().logs().getAvailableLogTypes();

        // Lấy ra 1 loại bất kỳ
        driver.manage().logs().get(LogType.DRIVER);
        driver.manage().logs().get(LogType.BROWSER);
        driver.manage().logs().get(LogType.CLIENT);

        // ================ Cookies ============= ==
        // Lấy ra tất cả các loại Cookie
        Set<Cookie> allCookies = driver.manage().getCookies();

        // Set cookie cho 1 page bất kì
        for (Cookie cookie: allCookies){
            // Set cookie cho 1 page bất kì
            driver.manage().addCookie(cookie);

            // Xóa từng cái
            driver.manage().deleteCookie(cookie);
        }

        // Xóa hết toàn bộ
        driver.manage().deleteAllCookies();

        // ================ Alert ================
        driver.switchTo().alert().accept();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        // ================ Frame/ iFrame ================
        driver.switchTo().frame("");
        driver.switchTo().frame(0);
        driver.switchTo().frame(1);
        driver.switchTo().frame(driver.findElement(By.cssSelector("")));

        driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();
        driver.switchTo().activeElement();

        // ==================== Navigation =====================
        // Tải lại trang hiện tại (F5/ Refresh)
         driver.navigate().refresh();

         // Quay lại trang trước đó
         driver.navigate().back();

         // Chuyển tiếp đến trang trước đó
         driver.navigate().forward();

         // Mở ra page URL
        driver.navigate().to("https://live.techpanda.vn");

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
