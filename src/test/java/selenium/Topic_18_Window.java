package selenium;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
        closeAllWindow(githubID);
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

    @Test
    public void TC_03_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String courseWindowID = driver.getWindowHandle();
        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        Thread.sleep(2000);
        switchToWindowByID(courseWindowID);
        Assert.assertEquals(driver.findElement(By.cssSelector("header>h1")).getText(),"DCE Login Portal");
        closeAllWindow(courseWindowID);
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p.sam-wait__message")).getText(),"Authentication was not successful. Please try again.");
        driver.findElement(By.cssSelector("button.sam-wait__close")).click();

        String courseName = "Human Evolution";
        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys(courseName);
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2026");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Any Part of Term");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button#search-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Human Evolution']")).getText(),"Human Evolution");
    }

    @Test
    public void TC_04_Selenium_4x() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        driver.switchTo().newWindow(WindowType.TAB).get("https://admin-demo.nopcommerce.com/login");
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        switchToWindowByTitle("Home page");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
    }

    // Chỉ ap dung voi 2 Window/ Tab
    private void switchToWindowByID(String windowID) {
        // Lay ra tat ca window tab
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

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
