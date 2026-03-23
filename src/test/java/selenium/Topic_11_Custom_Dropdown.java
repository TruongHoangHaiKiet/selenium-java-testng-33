package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_11_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    By loadingIcon = By.cssSelector("div.oxd-loading-spinner");
    String firstName,lastName, employeeID, userName, password, passportNumber, passportComment;

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
        firstName = "John";
        lastName = "Phillip";
        userName = "john.phillip" + new Random().nextInt(9999) + "@gmail.com";
        password = "Phillip123!@#";
        passportNumber = "1234-5678-9101";
        passportComment = "123 PO Box\nLos Angeles\nUSA";

    }

    @Test
    public void TC_01_JQuery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        /*---- Thao tác với SALUTATION ------*/
        // Click chuột vào 1 thẻ bên ngoài để cho nó xổ hết item ra
        selectItemDropdownByCss("span#salutation-button", "ul#salutation-menu div", "Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Dr.");
        selectItemDropdownByCss("span#salutation-button", "ul#salutation-menu div", "Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Mrs.");


    }

    // Reusable Method: Hàm tái sử dụng
    private void selectItemDropdownByCss(String parentLocator, String childLocator, String itemValue) {
        // Click chuột vào 1 thẻ bên ngoài để cho nó xổ hết item ra
        driver.findElement(By.cssSelector(parentLocator)).click();

        // Duyệt qua từng item kiểm tra text
        List<WebElement> singleElement = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));;

        // Duyệt qua từng cái
        for (WebElement item : singleElement) {
            // Kiểm tra từng cái
            String itemText = item.getText();
            System.out.println("Item text = " + itemText);
            // Kiểm tra từng cái
            if (item.getText().equals(itemValue)) {
                // Nếu đến cái text item mình cần thì click vào là chọn thành công
                item.click();
                break;
            }
        }
    }

    private void selectItemDropdownByXpath(String parentLocator, String childLocator, String itemValue) {
        // Click chuột vào 1 thẻ bên ngoài để cho nó xổ hết item ra
        driver.findElement(By.xpath(parentLocator)).click();
        sleepInSecond(2);
        // Duyệt qua từng item kiểm tra text
        List<WebElement> singleElement = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));;

        // Duyệt qua từng cái
        for (WebElement item : singleElement) {
            // Kiểm tra từng cái
            String itemText = item.getText();
            System.out.println("Item text = " + itemText);
            // Kiểm tra từng cái
            if (item.getText().equals(itemValue)) {
                // Nếu đến cái text item mình cần thì click vào là chọn thành công
                item.click();
                break;
            }
        }
    }
    private void selectItemDropdown(By parentLocator, By childLocator, String itemValue) {
        // Click chuột vào 1 thẻ bên ngoài để cho nó xổ hết item ra
        driver.findElement(parentLocator).click();
        sleepInSecond(2);
        // Duyệt qua từng item kiểm tra text
        List<WebElement> singleElement = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childLocator));;

        // Duyệt qua từng cái
        for (WebElement item : singleElement) {
            // Kiểm tra từng cái
            String itemText = item.getText();
            System.out.println("Item text = " + itemText);
            // Kiểm tra từng cái
            if (item.getText().equals(itemValue)) {
                // Nếu đến cái text ite m mình cần thì click vào là chọn thành công
                item.click();
                break;
            }
        }
    }

    private void inputSelectItemDropdownByCss(String parentLocator, String childLocator, String itemValue) {
        // Click chuột vào 1 thẻ bên ngoài để cho nó xổ hết item ra
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(itemValue);
        sleepInSecond(2);

        // Duyệt qua từng item kiểm tra text
        List<WebElement> singleElement = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));;

        // Duyệt qua từng cái
        for (WebElement item : singleElement) {
            // Kiểm tra từng cái
            String itemText = item.getText();
            System.out.println("Item text = " + itemText);
            // Kiểm tra từng cái
            if (item.getText().equals(itemValue)) {
                // Nếu đến cái text item mình cần thì click vào là chọn thành công
                item.click();
                break;
            }
        }
    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemDropdownByCss("div.fluid.selection","div.item span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fluid.selection>div.divider.text")).getText(),"Matt");

        selectItemDropdownByCss("div.fluid.selection","div.item span", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fluid.selection>div.divider.text")).getText(),"Elliot Fu");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemDropdownByCss("li.dropdown-toggle","ul.dropdown-menu a", "Second Option");
        selectItemDropdown(By.cssSelector("li.dropdown-toggle"), By.cssSelector("ul.dropdown-menu a"), "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li")).getText(),"Second Option");
        Thread.sleep(3000);

        selectItemDropdownByCss("li.dropdown-toggle","ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li")).getText(),"Third Option");
        Thread.sleep(3000);
    }

    @Test
    public void TC_04_OrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        // Login
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // Loading icon 1 (Add new employee)
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));
        // Employee list
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        // Add Employee
        driver.findElement(By.xpath("//button[contains(string(),'Add')]")).click();
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon)));
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

        // Get Employee ID
        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println("Employee ID = " + employeeID);
        driver.findElement((By.xpath("//p[text()='Create Login Details']/following-sibling::div/label"))).click();

        //Create Employee ID
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        // Loading icon 1 (Add new employee)
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));
        //Loading icon 2 (Personal Detail)
        Assert.assertTrue(new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"),employeeID);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        //Nationality dropdown
        selectItemDropdown(By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//i"),By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"),"Vietnamese");
        selectItemDropdown(By.xpath("//label[text()='Marital Status']/parent::div/following-sibling::div//i"),By.xpath("//label[text()='Marital Status']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"),"Vietnamese");
        selectItemDropdown(By.xpath("//label[text()='Blood Type']/parent::div/following-sibling::div//i"),By.xpath("//label[text()='Blood Type']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"),"Vietnamese");
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        // Selectable
        selectItemDropdownByCss("div.fluid.selection","div.item span", "Armenia");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fluid.selection>div.divider.text")).getText(),"Armenia");

        selectItemDropdownByCss("div.fluid.selection","div.item span", "Aruba");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fluid.selection>div.divider.text")).getText(),"Aruba");

        // Editable
        inputSelectItemDropdownByCss("div.fluid.selection>input","div.item span", "Armenia");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fluid.selection>div.divider.text")).getText(),"Armenia");

        inputSelectItemDropdownByCss("div.fluid.selection>input","div.item span", "Aruba");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fluid.selection>div.divider.text")).getText(),"Aruba");
    }

    @Test
    public void TC_05_Editable_FinPeace() {
        driver.get("https://sps.finpeace.vn/tools/sktccn");
        inputSelectItemDropdownByCss("input#job_id", "div#job_id_list~div div.ant-select-item","Công nghệ thông tin");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='job_id']/parent::span/following-sibling::span")).getText(),"Công nghệ thông tin");

        inputSelectItemDropdownByCss("input#gender", "div#gender_list~div div.ant-select-item","Nam");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='gender']/parent::span/following-sibling::span")).getText(),"Nam");

        inputSelectItemDropdownByCss("input#married_status", "div#married_status_list~div div.ant-select-item","Độc thân, chưa có con");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='married_status']/parent::span/following-sibling::span")).getText(),"Độc thân, chưa có con");
    }

    public void sleepInSecond(long timeInsecond){
        try {
            Thread.sleep(timeInsecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
