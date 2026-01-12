package testng;

import org.testng.Assert;

public class Topic_01_Assert {
    public static void main(String[] args) {
        // Kiểm tra tính đúng sai
        String name = "Automation FC";
        Assert.assertTrue(name.contains("FC"));
        Assert.assertFalse(name.contains("CF"));
    }
}
