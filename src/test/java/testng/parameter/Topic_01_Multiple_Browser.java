package testng.parameter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_01_Multiple_Browser {
        WebDriver driver;

        @Parameters({"browser"})
        @BeforeClass
        public void initBrowser(String browserName, String environmentName) {
            // browserName = Firefox
            switch(browserName){
                case "Firefox":
                    driver = new FirefoxDriver();
                    break;
                case "Chrome":
                    driver = new ChromeDriver();
                    break;
                case "Edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Browser name is invalid ");
            }

            switch(browserName){
                case "Dev":
                    driver = new FirefoxDriver();
                    break;
                case "Testing":
                    driver = new ChromeDriver();
                    break;
                case "Staging":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Browser name is invalid ");
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }



        @Test
        public void TC_01_TechPanda() throws InterruptedException {
            driver.get("https://egov.danang.gov.vn/reg");
        }

        @AfterClass
        public void closeBrowser() {
            driver.quit();
        }
}
