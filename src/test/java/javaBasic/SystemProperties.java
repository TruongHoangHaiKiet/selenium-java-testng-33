package javaBasic;

import org.openqa.selenium.By;
import selenium.Topic_01_Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SystemProperties {
    public static void main(String[] args) {
        String firstImage = "see.jpg";
        String secondeImage = "Test.jpg";
        String thirdImage = "see_LessThan_200px.jpg";
        String uploadFile = System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator;

        System.out.println(uploadFile + firstImage);
        System.out.println(uploadFile + secondeImage);
        System.out.println(uploadFile + thirdImage);

        // Window: \\
        // MAC/ Linux: /
    }
}
