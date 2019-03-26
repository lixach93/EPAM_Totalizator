package by.training.lihodievski.final_project.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PageUtilTest {

    @Test(dataProvider = "getCountPageData", dataProviderClass = PageUtilTestData.class)
    public void testIsLeague_String_boolean(int value, int expected)  {

        int actual = PageUtil.getCountPage (value);

        Assert.assertEquals (actual, expected);
    }
}
