package testng;

import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_01_Annotation {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Run BeforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Run AfterClass");
    }

    @BeforeTest
    public  void beforeTest(){
        System.out.println("Run BeforeTest");
    }

    @AfterTest
    public  void afterTest(){
        System.out.println("Run AfterTest");
    }

    @BeforeSuite
    public  void beforeSuite(){
        System.out.println("Run BeforeSuite");
    }

    @AfterSuite
    public  void AfterSuite(){
        System.out.println("Run AfterSuite");
    }

    @BeforeMethod
    public  void BeforeMethod(){
        System.out.println("Run BeforeMethod");
    }

    @AfterMethod
    public  void AfterMethod(){
        System.out.println("Run AfterMethod");
    }

    @BeforeGroups
    public  void BeforeGroups(){
        System.out.println("Run BeforeGroups");
    }

    @AfterClass
    public  void AfterGroups(){
        System.out.println("Run AfterGroups");
    }

    @Test
    public void TC_01_Register(){
        System.out.println("Run testcase Register");
    }

    @Test
    public void TC_02_Login(){
        System.out.println("Run testcase Login");
    }

}
