package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.devtools.v145.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;

public class Topic_15_Authen_Alert {
    WebDriver driver;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Authen_Alert() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_02_Authen_Alert() {
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomAttribute("href");
        String[] urlSplit = basicAuthenUrl.split("//");
        basicAuthenUrl = urlSplit[0] + "//" + "admin:admin@" + urlSplit[1];
        driver.get(basicAuthenUrl);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_03_Authen_Alert() {
        // Get Devtool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        //Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty()));

        //Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic" + new String(new Base64().encode(String.format("%s:%s","admin","admin").getBytes()));
        headers.put("Authorization",basicAuthen);

        //Set to header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
