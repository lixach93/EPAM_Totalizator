package by.training.lihodievski.final_project.util;

import org.testng.annotations.DataProvider;

public class PageUtilTestData {

    @DataProvider(name = "getCountPageData")
    public static Object[] getCountPageData(){

        int firstData = 4;
        int secondData = 8;
        int thirdData = 13;


        return  new Object[][]{
                {
                        firstData, 2
                },
                {
                        secondData,4
                },
                {
                        thirdData, 7
                }
        };
    }
}
