 package testng;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_06_Description {
    WebDriver driver;

    @Test(description = "#578 - Admin can create a new VISA Card")
    public void TC_01_New_Card(){

    }

    @Test(description = "Cung cấp thêm thông tin cho team member/" + "non-technical(Manual Test/BA/PO/Stackholder/...)")
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
