package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Locator_2 {
    // Phạm vi truy cập của hàm: private/ public/ protected/ default
    // Kiểu dữ liệu trả về: getter/ setter
    // Tên hàm
    // Tham số của hàm nằm trong dấu ()
    // Thân hàm nằm trong dấu {}
    WebDriver driver;
    @BeforeClass
    public void initBrowser(){
        // Mở Browser lên
        driver = new FirefoxDriver();
        driver.get("https://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void TC_01_Selenium_Locator() {
        // Tìm Element để thao tác lên
        // ID
        driver.findElement(By.id("firstname")).sendKeys("AutomationFC");

        // className
        driver.findElement(By.className("customer-name-middlename"));

        // Name
        driver.findElement(By.name("middlename"));

        // Tagname
        driver.findElements(By.tagName("a"));

        //Link
        driver.findElements(By.linkText("MY ACCOUNT"));

        //Partial link
        driver.findElements(By.partialLinkText("SEARCH"));

        // Css
        driver.findElements(By.cssSelector("input#password"));

        // Xpath
        driver.findElement(By.xpath("//input[@id='email_address']"));

    }

    @Test
    public void TC_02_Css() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElements(By.xpath("//input[@id='email']"));
        driver.findElements(By.cssSelector("input[id='email']"));
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
