package testng.basic;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_05_Skip {
    WebDriver driver;

    @Test
    public void TC_01_New_Card(){

    }

    @Test
    public void TC_02_Update_Card(){

    }

    @Test (enabled = false)
    public void TC_03_View_Card(){

    }

    @Test
    public void TC_04_Remove_Card(){

    }

    @Test
    public void TC_05_Delete_Card(){

    }

}
