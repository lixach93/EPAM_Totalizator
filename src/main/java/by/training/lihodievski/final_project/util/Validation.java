package by.training.lihodievski.final_project.util;

public class Validation {

    private static final String STRING_REG_EXP = "[a-zA-Z]+";
    private static final String MONEY_REG_EXP = "\\d+(\\.\\d+)?";
    private static final String POSITIVE_NUMBER_REG_EXP = "\\d+";


    public static boolean isString(String value){
        return value.matches (STRING_REG_EXP);
    }

    public static boolean isNull(Object value){
        return value == null;
    }

    public static boolean isMoney(String value) {
        return value.matches (MONEY_REG_EXP);
    }

    public static boolean isPositiveNumber(String value) {
        return value.matches (POSITIVE_NUMBER_REG_EXP);
    }

}
