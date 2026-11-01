package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {
    @Test
    public void TC_01(){
        // true: mong đợi 1 điều kiện trả về là ĐÚNG
        // false: mong đợi 1 điều kiện trả về là SAI
        // equals: mong đợi 2 điều kiện bằng nhau (Actual - Expected)
        // null/ not null

        String fullName = "Elon Musk";
        Assert.assertTrue(fullName.contains("Musk"));
        Assert.assertTrue(fullName.startsWith("Elon"));

        Assert.assertFalse(fullName.endsWith("Elon"));
        Assert.assertFalse(fullName.startsWith("Musk"));
        Assert.assertFalse(fullName.startsWith("Elon Musk"),"Full name is equal");

        Assert.assertEquals(fullName,"Elon Musk");
        Assert.assertNotEquals(fullName,"ELON MUSK");
        Assert.assertNotEquals(fullName,"Bill Gate");

        String firstName = null;
        Assert.assertNull(firstName);
    }
}
