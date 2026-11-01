package testng.group.web;

import org.testng.annotations.Test;

public class Web_01_Admin {
    @Test(groups = "web")
    public void Employee_01_Create_User(){
        System.out.println("Create a new employee");
    }

    @Test(groups = {"web", "regression"})
    public void Employee_02_View_User(){
        System.out.println("View employee");
    }

    @Test(groups = {"web","regression"})
    public void Employee_03_Edit_User(){
        System.out.println("Delete employee");
    }
}
