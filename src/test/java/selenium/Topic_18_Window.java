package selenium;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Window {
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
    public void TC_01_GitHub() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Lây ra Window ID cua tab hien tai ma driver dang dung (ACTIVE)
        String githubID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(1000);

        switchToWindowByID(githubID);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Donald Trump");
        Thread.sleep(1000);
        switchToWindowByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(1000);

        switchToWindowByTitle("Facebook");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("automationfc.com@gmail");
        driver.findElement(By.cssSelector("input[name='pass']")).sendKeys("automationfc.com@gmail");

    }

    @Test
    public void TC_02_Techpanda(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        String mobileWindowID = driver.getWindowHandle();
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        switchToWindowByID(mobileWindowID);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        switchToWindowByTitle("Mobile");
        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
    }
    // Chỉ ap dung voi 2 Window/ Tab
    private void switchToWindowByID(String windowID) {
        // Lay ra tat ca window 10
        Set<String> allIDs = driver.getWindowHandles();
        for (String id: allIDs){
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
            }
        }
    }
    private void switchToWindowByTitle(String title){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id: allIDs){
            System.out.println("Window ID = " + id);
            driver.switchTo().window(id);
            String pageTitle = driver.getTitle();
            if (pageTitle.equals(title)){
        System.out.println("Title tab = " + pageTitle);
                break;
            }
        }
    }

    private void closeAllWindow(String windowID) {
        // Lay ra tat ca window 10
        Set<String> allIDs = driver.getWindowHandles();
        for (String id: allIDs){
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
