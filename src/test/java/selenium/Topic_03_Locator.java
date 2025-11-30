package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Locator {
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
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
