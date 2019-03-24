package by.training.lihodievski.final_project.util;


import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidatorTest {

    @Test(dataProvider = "validateLeague", dataProviderClass = ValidatorTestData.class)
    public void testIsLeague_String_boolean(String value, boolean expected)  {

        boolean result = Validator.isLeague (value);

        Assert.assertEquals (result, expected);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testIsLeague_String_exception()  {

        Validator.isLeague (null);

    }

    @Test(dataProvider = "validateTeam", dataProviderClass = ValidatorTestData.class)
    public void testIsTeam_String_boolean(String value, boolean expected)  {

        boolean result = Validator.isTeam (value);

        Assert.assertEquals (result, expected);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testIsTeam_String_exception()  {

        Validator.isTeam (null);

    }
    @Test(dataProvider = "validateMoney", dataProviderClass = ValidatorTestData.class)
    public void testIsMoney_String_boolean(String value, boolean expected)  {

        boolean result = Validator.isMoney (value);

        Assert.assertEquals (result, expected);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testIsMoney_String_exception()  {

        Validator.isMoney (null);

    }

}