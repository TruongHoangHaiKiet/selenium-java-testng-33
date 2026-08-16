package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_23_Wait_PII_FindElement {
    WebDriver driver;
    WebDriverWait driverWait;

    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    // 1 - findElement/s bị ảnh hưởng bởi implicitWait
    // Nếu có set timeout thì lấy mốc đó làm tổng time
    // Nếu không set thì tổng time = 0

    @Test
    public void TC_01_FindElement() {
        // Điều kiện 1: Element có trên UI và có trong HTML
        // Email Address error message xuất hiện
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        // Xét 3 trường hợp để xem phản ứng và kết quả của từng trường hợp khi sử dụng các hàm
        // Trường hợp 1: Tìm nhưng thấy 1 element
        // Trả về element đó (đầu tiên)
        driver.findElement(By.cssSelector("input#email"));

        // Trường hợp 2: Tìm nhưng thấy nhiều Element
        // Trả về Element đầu tiên - các element còn lại không quan tâm
        System.out.println(driver.findElement(By.xpath("//input")).getDomAttribute("name"));

        // Trường hợp 3: Tìm nhưng không thấy element
        // Lặp lại tìm mỗi nửa giây nếu tìm thấy thì trả về element đầu tiên (không chờ hết time còn lại)
        // Nếu lặp lại tìm không thấy đến giây cuối cùng (hết time) thì đánh fail step và trả về exception NoSuchElementException

        driver.findElement(By.cssSelector("input#selenium"));
    }

    @Test
    public void TC_02_FindElements() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        List<WebElement> elements;

        // Trường hợp 1: Tìm nhưng thấy 1 Element
        // Trả về 1 List Element chứa 1 element đó
        elements = driver.findElements(By.cssSelector("input#email"));
        System.out.println(elements.size());

        // Trường hợp 2: Tìm nhưng thấy nhiều Element
        // Trả về 1 List Element chứa nhiều element đó
        elements = driver.findElements(By.xpath("//input"));
        System.out.println(elements.size());

        // Trường hợp 3: Tìm nhưng không thấy element
        // Lặp lại tìm mỗi nửa giây nếu tìm thấy thì trả về số lượng element tương ứng(không chờ hết time còn lại)
        // Nếu lặp lại tìm không thấy đến giây cuối cùng (hết time) thì không đánh fail step và trả về 1 List Element rỗng (empty/ =0)
        elements = driver.findElements(By.cssSelector("input#selenium"));
        System.out.println(elements.size());
    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
