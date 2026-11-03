package javaBasic;

import java.io.File;

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
