package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_PV_Explicit {
    // Khai báo
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        // Khởi tạo 1 biến WebDriverWait với tổng thời gian là 5s - polling time là 0.5s (mặc định)
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Khởi tạo 1 biến WebDriverWait với tổng thời gian là 5s - polling time custom 0.5s (mặc định)
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(100));

    }

    @Test
    public void TC_01_Method() {
        //Visible: Chờ cho 1 or nhiều element xuất hiện/ hiển thị
        // Lưu ý: Tham số của hàm
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("input#Email")), driver.findElement(By.cssSelector("input#Password"))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("")));

        // Invisible
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("input#Email")), driver.findElement(By.cssSelector("input#Password"))));
        explicitWait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(""), "Hello World!"));

        //Presence + Staleness
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        // Clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input#Email"))));

        // Selected
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.cssSelector("input#Email"))));

        // Frame/ Alert
        explicitWait.until(ExpectedConditions.alertIsPresent());
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

        // and/ or/ not
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),ExpectedConditions.elementToBeClickable(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),ExpectedConditions.elementToBeClickable(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        // Title/ URL
        explicitWait.until(ExpectedConditions.titleIs("Dynamic loading to demo wait in Selenium"));
        explicitWait.until(ExpectedConditions.titleContains("Dynamic loading"));
        explicitWait.until(ExpectedConditions.urlToBe("https://automationfc.github.io/dynamic-loading/"));
        explicitWait.until(ExpectedConditions.urlContains("/dynamic-loading/"));

        // Atribute/ Property
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"value", "Enter to textbox"));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"value", "Enter to textbox"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),"value", "Enter to textbox"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),"value", "Enter to textbox"));

        // Number
        explicitWait.until((ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),15)));
    }

    @Test
    public void TC_02_Set() {
    }

    @Test
    public void TC_03_Equal() {
    }

    @Test
    public void TC_4_MoreThan() {
    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
