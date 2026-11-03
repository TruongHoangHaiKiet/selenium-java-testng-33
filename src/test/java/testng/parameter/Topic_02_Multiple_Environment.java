package testng.parameter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_02_Multiple_Environment {
        WebDriver driver;
        private String appURL;

        @Parameters({"browser","enviroment"})
        @BeforeClass
        public void initBrowser(@Optional("Firefox") String browserName, String environmentName) {
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

            switch(environmentName){
                case "Dev":
                    appURL = "https://egov.danang.gov.vn/reg";
                    break;
                case "Testing":
                    appURL = "https://testing.egov.danang.gov.vn/reg";
                    break;
                case "Staging":
                    appURL = "https://stg.egov.danang.gov.vn/reg";
                    break;
                default:
                    throw new RuntimeException("Environment name is invalid ");
            }
            driver.get(appURL);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }



        @Test
        public void TC_01_TechPanda() throws InterruptedException {

        }

        @AfterClass
        public void closeBrowser() {
            driver.quit();
        }
}
