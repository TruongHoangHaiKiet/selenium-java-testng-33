package testng.basic;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_04_Priority {
    WebDriver driver;

    @Test(priority = 1)
    public void TC_01_New_Card(){

    }

    @Test(priority = 3)
    public void TC_02_Update_Card(){

    }

    @Test (priority = 4)
    public void TC_03_View_Card(){

    }

    @Test(priority = 5)
    public void TC_04_Remove_Card(){

    }

    @Test(priority = 2)
    public void TC_05_Delete_Card(){

    }

}
