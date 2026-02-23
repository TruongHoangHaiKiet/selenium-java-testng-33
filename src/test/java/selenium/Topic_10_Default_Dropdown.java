package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_10_Default_Dropdown {
    WebDriver driver;
    Select cityDropdown, districtDropdown;
    @BeforeClass
    public void initBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        driver.get("https://egov.danang.gov.vn/reg");
        //Khởi tạo biến select gắn với element là Tỉnh thành
        cityDropdown = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));

        // Kiểm tra tỉnh thành Dropdown có tất cả bao nhiêu tỉnh/ TP
        int tinhThanhNumber = cityDropdown.getOptions().size();
        Assert.assertEquals(tinhThanhNumber,67);

        // Kiểm tra dropdown là multiple/ single
        Assert.assertFalse(cityDropdown.isMultiple());

        // Index: Dễ bị thay đổi nên chạy dễ bị lỗi khi thêm sửa xóa item
        // Khó nhớ - index này tương ứng với item nào
        // Khi testcase chạy bị lỗi và cần reproduce lại thì dữ liệu test cần giả lập khó nhớ
        cityDropdown.selectByVisibleText("thành phố Hà Nội");

        // Kiểm tra chọn thành công
        Assert.assertEquals(cityDropdown.getFirstSelectedOption().getText(), "thành phố Hà Nội");

        districtDropdown = new Select(driver.findElement((By.cssSelector("select#thuongtru_quanhuyen"))));
        districtDropdown.selectByVisibleText("quận Tây Hồ");
        Assert.assertEquals(districtDropdown.getFirstSelectedOption().getText(),"quận Tây Hồ");
    }
    @Test
    public void TC_02_() {
        driver.get("https://rode.com/en-au/support/where-to-buy");
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/div//h4"));
        for (WebElement dealer : dealers){
            System.out.println(dealer.getText());
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
