package selenium;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Action {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor jsExecutor;
    String filePath = System.getProperty("user.dir") + "//src//test//resources//dragDrop.js";

    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip")).getText(),"We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_CoinMarketCap() {
        driver.get("https://coinmarketcap.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[text()='Dashboards']"))).perform();
        actions.pause(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//div[@class='section']//a[text()='Bitcoin ETFs']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'tab-inner-label')]//div[contains(@class,'c-gnlqYH')]/a[text()='BTC ETFs']")).isDisplayed());
    }

    @Test
    public void TC_03_Fahasa(){
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.pause(Duration.ofSeconds(15));

        actions.pause(Duration.ofSeconds(2)).perform();

        actions.moveToElement(driver.findElement(By.cssSelector("a[title='Sách Trong Nước']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        actions.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());

    }

  @Test
  public void TC_04_ClickAndHold_Block() {
    driver.get("https://automationfc.github.io/jquery-selectable/");
      List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
     actions.clickAndHold(numbers.get(5)) // Click và giu chuot trai vao so dau tien trong day
             .moveToElement(numbers.get(23)) // Di chuot den so cuoi cung trong day
             .release() // Nha chuot trai ra
             .perform(); // Thuc thi
      Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 15);

    }

    @Test
    public void TC_05_ClickAndHold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        String osName = System.getProperty("os.name");
        Keys keys = osName.contains("Windowns") ? Keys.CONTROL : Keys.COMMAND;
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        actions.keyDown(keys).perform();
        // 5 12 14 21 24
        actions.click(numbers.get(4))
                                .click(numbers.get(11))
                                        .click(numbers.get(13))
                                                . click(numbers.get(20))
                                                        .click(numbers.get(23)).perform();
        actions.keyUp(keys).perform();
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 5);

    }

    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.toString().contains("Firefox")){
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
            actions.pause(Duration.ofSeconds(3)).perform();
        }
        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
    }

    @Test
    public void TC_07_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        actions.pause(Duration.ofSeconds(2));

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        actions.contextClick(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }

    @Test
    public void TC_08_DragDropHTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
        actions.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(2000);
        String loginBtnRgbColor = targetCircle.getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBtnRgbColor).asHex().toUpperCase(),"#03A9F4");
    }

    @Test
    public void TC_09_DragDropHTML5() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        jsExecutor.executeScript(getContentFile(filePath));
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        jsExecutor.executeScript(getContentFile(filePath));
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");



    }

    public String getContentFile(String filePath) throws IOException, FileNotFoundException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
