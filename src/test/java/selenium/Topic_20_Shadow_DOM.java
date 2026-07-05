package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Shadow_DOM {
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
    public void TC_01_Github() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement shadowHostFirst = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootFirst = shadowHostFirst.getShadowRoot();
        String someText = shadowRootFirst.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);

        WebElement shadowHostSecond = shadowRootFirst.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext shadowRootSecond = shadowHostSecond.getShadowRoot();
        String nestedText = shadowRootSecond.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);
        shadowRootFirst.findElement(By.cssSelector("input[type='text']")).sendKeys("Automation Testing");
    }

    @Test
    public void TC_02_Shop_Polymer() {
        driver.get("https://shop.polymer-project.org/");
        SearchContext shadowRootFirst = driver.findElement(By.cssSelector("shop-app[page='home']")).getShadowRoot();
        SearchContext shadowRootSecond = shadowRootFirst.findElement(By.cssSelector("shop-home.iron-selected")).getShadowRoot();
        shadowRootSecond.findElement(By.cssSelector("shop-button>a[aria-label=\"Men's Outerwear Shop Now\"]")).click();
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void TC_03_Saleforce() throws InterruptedException {
        driver.get("https://developer.salesforce.com/free-trials");
        Thread.sleep(5000);
        WebElement shadowHostFirst = driver.findElement(By.cssSelector("dx-global-header"));
        SearchContext shadowRootFirst = shadowHostFirst.getShadowRoot();
        SearchContext shadowRootSecond = shadowRootFirst.findElement(By.cssSelector("hgf-c360nav")).getShadowRoot();
        SearchContext shadowRootThird = shadowRootSecond.findElement(By.cssSelector("div.desktop-cta>hgf-button")).getShadowRoot();
        shadowRootThird.findElement(By.cssSelector("a.hgf-button")).click();
        Thread.sleep(3000);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
