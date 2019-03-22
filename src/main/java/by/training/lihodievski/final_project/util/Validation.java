package by.training.lihodievski.final_project.util;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.Rate;
import by.training.lihodievski.final_project.bean.RoleType;

public class Validation {

    private static final String LEAGUE_PATTERN = "[a-zA-Z]+";
    private static final String TEAM_PATTERN = "[a-zA-ZА-я0-9 ]+";
    private static final String MONEY_PATTERN = "\\d+(\\.\\d+)?";
    private static final String POSITIVE_NUMBER_PATTERN = "\\d+";
    private static final String PERCENT_PATTERN = "^\\d+(\\.)?(\\d+)?$";
    private static final String CARD_NUMBER_PATTERN = "[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}";


    public static boolean isLeague(String value){
        return value.matches (LEAGUE_PATTERN);
    }

    public static boolean isTeam(String value){
        return value.matches (TEAM_PATTERN);
    }

    public static boolean isNull(Object value){
        return value == null;
    }

    public static boolean isMoney(String value) {
        return value.matches (MONEY_PATTERN);
    }

    public static boolean isPositiveNumber(String value) {
        return value.matches (POSITIVE_NUMBER_PATTERN);
    }
    public static boolean isPercent(String value) {
        return value.matches (PERCENT_PATTERN);
    }
    public static boolean isId(String value) {
        if(isPositiveNumber (value)){
            return Integer.parseInt (value) > 0;
        }else{
            return false;
        }
    }
    public static boolean isCardNumber(String value){
        return value.matches (CARD_NUMBER_PATTERN);
    }

    public static boolean isRole(String role) {
        RoleType[] roleTypes = new RoleType[]{RoleType.MODERATOR, RoleType.USER, RoleType.ADMINISTRATOR};
        boolean flag = false;
        for(RoleType currentRole:roleTypes){
            if(currentRole.getValue ().equals (role)){
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isCategory(String category) {
        Category[] categories = new Category[]{Category.HOCKEY,Category.FOOTBALL,Category.BASKETBALL};
        boolean flag = false;
        for(Category currentCategory: categories){
            if(currentCategory.getCategoryName ().equals (category)){
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isRate(String typeRate){
        if(typeRate.equals ("all")){
            return true;
        }
        Rate[] rate = new Rate[]{Rate.TEAM,Rate.TOTAL};
        boolean flag = false;
        for(Rate currentRate: rate){
            if(currentRate.getValue ().equals (typeRate)){
                flag = true;
            }
        }
        return flag;
    }
}
