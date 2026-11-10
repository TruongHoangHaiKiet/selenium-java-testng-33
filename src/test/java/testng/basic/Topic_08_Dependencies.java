 package testng.basic;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

 public class Topic_08_Dependencies {
     WebDriver driver;

     @Test
     public void TC_01_New_Card(){
         Assert.assertTrue(false);
     }

     @Test (dependsOnMethods = "TC_01_New_Card")
     public void TC_02_Update_Card(){

     }

     @Test (dependsOnMethods = {"TC_01_New_Card","TC_02_Update_Card"})
     public void TC_03_View_Card(){

     }

     @Test (dependsOnMethods = "TC_01_New_Card")
     public void TC_04_Remove_Card(){

     }

     @Test (dependsOnMethods = "TC_01_New_Card")
     public void TC_05_Delete_Card(){

     }

 }
