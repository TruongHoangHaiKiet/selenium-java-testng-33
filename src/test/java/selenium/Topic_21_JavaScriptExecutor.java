package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_21_JavaScriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...

        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Techpanda() {
        navigateToUrlByJS("https://live.techpanda.org/");
        Assert.assertEquals(getDomainName(),"live.techpanda.org");
        Assert.assertEquals(executeForBrowser("return document.URL"),"https://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']/button");

        String samsungText = getInnerText();
        //1
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"),"Samsung Galaxy was added to your shopping cart.");

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        scrollToElementOnTop("//input[@id='newsletter']");
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']", "claude" + new Random().nextInt(9999) + "@gmail.com");

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        driver.switchTo().alert().accept();

        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"), "Thank you for your subscription.");
        navigateToUrlByJS("https://www.facebook.com/");
        Assert.assertEquals(getDomainName(),"www.facebook.com");
    }

    @Test
    public void TC_02_Ubuntu_Validation_Msg() {
        navigateToUrlByJS("https://login.ubuntu.com/");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();
        String htmlValidationMsg = getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']");
        Assert.assertEquals(htmlValidationMsg,"Please fill out this field.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("a");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();

        htmlValidationMsg = getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']");
        Assert.assertEquals(htmlValidationMsg, "Please enter an email address.");
        Assert.assertEquals(driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).getDomProperty("validationMessage"),"Please fill out this field.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("ab@c.com");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();

        htmlValidationMsg = getElementValidationMessage("//form[@id='login-form']//input[@id='id_password']");
        Assert.assertEquals(htmlValidationMsg, "Please fill out this field.");
        Assert.assertEquals(driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_password']")).getDomProperty("validationMessage"),"Please fill out this field.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_password']")).sendKeys("abc@test");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();
    }

    @Test
    public void TC_03_Scroll() {
        driver.get("https://live.techpanda.org/index.php/customer-service/");
        scrollToElementOnTop("//input[@id='search']");
        sleepInSecond(3);

        scrollToElementOnDown("//input[@id='newsletter']");
        sleepInSecond(3);

        driver.get("https://www.fahasa.com/");
        scrollToElementOnTop("//div[contains(text(),'TỦ SÁCH NỔI BẬT')]");
        sleepInSecond(3);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }
    public String getDomainName() {
        return (String) jsExecutor.executeScript("return document.domain;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

}
