package by.training.lihodievski.final_project.util;

import org.testng.annotations.DataProvider;

public class ValidatorTestData {

    @DataProvider(name = "validateLeague")
    public static Object[] validateLeague(){

        String leagueFirst = "1234";
        String leagueSecond = "nba";
        String leagueThird = "APL";

        return  new Object[][]{
                {
                       leagueFirst, false
                },
                {
                        leagueSecond, true
                },
                {
                        leagueThird, true
                }
        };
    }

    @DataProvider(name = "validateTeam")
    public static Object[] validateTeam(){

        String teamFirst = "1234";
        String teamSecond = " ";
        String teamThird = "~!~@~";

        return  new Object[][]{
                {
                        teamFirst, true
                },
                {
                        teamSecond, false
                },
                {
                        teamThird, false
                }
        };
    }

    @DataProvider(name = "validateMoney")
    public static Object[] validateMoney(){

        String moneyFirst = "1234";
        String moneySecond = "0";
        String moneyThird = "APL";

        return  new Object[][]{
                {
                        moneyFirst, true
                },
                {
                        moneySecond, false
                },
                {
                        moneyThird, false
                }
        };
    }
}

