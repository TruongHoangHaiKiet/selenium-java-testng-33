package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12_Button {
    WebDriver driver;
    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_() {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        By registerButton = By.cssSelector("div.hwid-btn-reg");

        // Verify button disabled
        Assert.assertTrue(driver.findElement(registerButton).getDomProperty("className").contains("hwid-disabled"));
        Assert.assertTrue(driver.findElement(registerButton).getDomAttribute("class").contains("hwid-disabled"));

        // Verify button text
        Assert.assertEquals(driver.findElement(registerButton).getText(),"ĐĂNG KÝ");

        // Background color
        String registerBtnRgb= driver.findElement(registerButton).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(registerBtnRgb).asHex().toUpperCase(),"#007DFF");
    }

    @Test
    public void TC_02_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login>a")).click();
        By loginBtn = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(driver.findElement(loginBtn).isEnabled());

        String loginBtnRgb = driver.findElement(loginBtn).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBtnRgb).asHex().toUpperCase(),"#000000");
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("kiet@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Auto123!@#");

        Assert.assertTrue(driver.findElement(loginBtn).isEnabled());
        loginBtnRgb = driver.findElement(loginBtn).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBtnRgb).asHex().toUpperCase(),"#C92127");
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
