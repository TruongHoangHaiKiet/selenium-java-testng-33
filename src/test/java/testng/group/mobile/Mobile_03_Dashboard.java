package testng.group.mobile;

import org.testng.annotations.Test;

public class Mobile_03_Dashboard {

    @Test(groups = "mobile")
    public void Employee_01_Create(){
        System.out.println("Create a new employee");
    }

    @Test(groups = {"mobile", "regression"})
    public void Employee_02_View(){
        System.out.println("View employee");
    }

    @Test(groups = {"mobile","regression"})
    public void Employee_03_Delete(){
        System.out.println("Delete employee");
    }


}
