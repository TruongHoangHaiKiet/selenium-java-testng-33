package testng.group.mobile;

import org.testng.annotations.Test;

public class Mobile_02_Timesheet {

    @Test(groups = "mobile")
    public void Employee_01_Create(){
        System.out.println("Create a new Timesheet");
    }

    @Test(groups = {"mobile", "regression"})
    public void Employee_02_View(){
        System.out.println("View Timesheet");
    }

    @Test(groups = {"mobile","regression"})
    public void Employee_03_Delete(){
        System.out.println("Delete Timesheet");
    }


}
