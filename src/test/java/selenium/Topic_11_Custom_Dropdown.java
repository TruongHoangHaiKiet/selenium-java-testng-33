package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        // Click chuột vào 1 thẻ bên ngoài để cho nó xổ hết item ra
        driver.findElement(By.cssSelector("span#speed-button")).click();

        // Chờ cho tất cả các items được load lên
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div")));

        // Duyệt qua từng item kiểm tra text
        List<WebElement> singleElement = driver.findElements(By.cssSelector("ul#speed-menu div"));

        // Duyệt qua từng cái
            for (WebElement item: singleElement){
                // Kiểm tra từng cái
                String itemText = item.getText();
                System.out.println("Item text = " + itemText);
                // Kiểm tra từng cái
                if (item.getText().equals("Faster")){
                    // Nếu đến cái text item mình cần thì click vào là chọn thành công
                    item.click();
                    break;
                }
            }


    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
