package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_13_Default_Checkbox_Radio {
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
    public void TC_01_Select_All_Checkboxes_1_in_ALL() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");
        By checkBox = By.xpath("//label[contains(text(),\"Arthritis\")]/preceding-sibling::input");

        // Chọn
        if (!driver.findElement(checkBox).isSelected()) {
            driver.findElement(checkBox).click();
        }
        Assert.assertTrue(driver.findElement(checkBox).isSelected());
        Thread.sleep(2000);
        // Bỏ chọn
        if (driver.findElement(checkBox).isSelected()) {
            driver.findElement(checkBox).click();
        }
        Assert.assertFalse(driver.findElement(checkBox).isSelected());

        // Chọn hết
        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("span.form-checkbox-item input.form-checkbox"));
        for (WebElement checkboxitem : allCheckbox) {
            if (!checkboxitem.isSelected()) {
                checkboxitem.click();
            }
        }
        Thread.sleep(2000);

        for (WebElement checkboxitem : allCheckbox) {
            Assert.assertTrue(checkboxitem.isSelected());
        }

        // Bỏ chọn hết
        for (WebElement checkboxitem : allCheckbox) {
            if (checkboxitem.isSelected()) {
                checkboxitem.click();
            }
        }
        Thread.sleep(2000);

        for (WebElement checkboxitem : allCheckbox) {
            Assert.assertFalse(checkboxitem.isSelected());
        }

        for (WebElement checkboxitem : allCheckbox) {
            if (!checkboxitem.isSelected() && checkboxitem.getDomAttribute("value").equals("Heart Attack")) {
                checkboxitem.click();
            }
        }
        Thread.sleep(2000);

        for (WebElement checkboxitem : allCheckbox) {
            if (checkboxitem.getDomAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkboxitem.isSelected());
            }
        }
    }

    @Test
    public void TC_02_Default_Checkbox_or_Radio_Button() {
        driver.get("https://material.angular.dev/components/checkbox/examples");
        By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div//input");
        By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div//input");
        By disableCheckbox = By.xpath("//h2[text()='Checkbox configuration']/ancestor::div[@class='docs-example-viewer-body']//label[text()='Disabled']/parent::div//input");
        By resultCheckbox = By.xpath("//label[contains(text(),\" I'm a checkbox \")]/preceding-sibling::div//input");
        By afterRadiobtn = By.xpath("//label[text()='After']/preceding-sibling::div//input");
        By beforeRadiobtn = By.xpath("//label[text()='Before']/preceding-sibling::div//input");

        // Verify checkbox deselected
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(disableCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(resultCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(afterRadiobtn).isSelected());
        Assert.assertFalse(driver.findElement(beforeRadiobtn).isSelected());


        // Click
        driver.findElement(checkedCheckbox).click();
        driver.findElement(indeterminateCheckbox).click();
        driver.findElement(disableCheckbox).click();
        driver.findElement(beforeRadiobtn).click();

        // Verify checkbox selected
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(disableCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(resultCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(beforeRadiobtn).isSelected());

        // Verify checkbox/ radio disabled and deselected
        Assert.assertFalse(driver.findElement(resultCheckbox).isEnabled());
        Assert.assertFalse(driver.findElement(afterRadiobtn).isSelected());

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
