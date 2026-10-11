package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class Topic_23_Wait_PVII_Mixing {
    // Khai báo
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFile = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Only_Implicit() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            System.out.println("Start time = " + new Date().toString());
            driver.findElement(By.cssSelector("input[name='email']"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time = " + new Date().toString());
        }

    }

    @Test
    public void TC_02_Only_Explicit() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            System.out.println("Start time = " + new Date().toString());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time = " + new Date().toString());
        }
    }

    @Test
    public void TC_03_Implicit_Explicit_By() {
        // Equal = 5s
        // Less = 10s
        // More = 10s 
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Case 1
        try {
            System.out.println("Start time = " + new Date().toString());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time = " + new Date().toString());
        }

        // Case 2
        try {
            System.out.println("Start time = " + new Date().toString());
            WebElement element = driver.findElement(By.cssSelector("input[name='email']"));
            explicitWait.until(ExpectedConditions.visibilityOfAllElements(element));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time = " + new Date().toString());
        }

        // Case 3
        try {
            System.out.println("Start time = " + new Date().toString());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time = " + new Date().toString());
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Case 4
        try {
            System.out.println("Start time = " + new Date().toString());
            WebElement element = driver.findElement(By.cssSelector("input[name='email']"));
            explicitWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time = " + new Date().toString());
        }
    }

    @Test
    public void TC_4_MoreThan() {
    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
