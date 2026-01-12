package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Element_Exception {
    WebDriver driver;
    // SAI - Chưa đúng thời điểm
    // Lúc này thì Browser chưa được mở lên
    WebElement emailTextbox = driver.findElement(By.cssSelector("input#email_address"));

    // Chỉ là 1 biến để tham chiếu thôi chưa có đi tìm Element
    By emailAddressBy = By.cssSelector("input#email_address");
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        // SAI - Chưa đúng thời điểm
        // Lúc này th Browser chưa được mở lên
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email_address"));
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_01_() {
        // SAI - Chưa đúng thời điểm
        // Lúc này th Browser chưa được mở lên
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email_address"));


        // Vào màn hình Register
        driver.get("https://live.techpanda.org/index.php/customer/account/create/");

        // Đúng - đúng vị trí
        emailTextbox = driver.findElement(emailAddressBy);

        // Vào màn hình Login
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        emailTextbox = driver.findElement(emailAddressBy);

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
