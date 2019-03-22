package by.training.lihodievski.final_project.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlEncoder {

    private static final String PATH_PATTERN = "\\/[a-zA-Z]+";

    public static String getPath(String path){
        Matcher matcher = Pattern.compile(PATH_PATTERN).matcher(path);
        if(matcher.find ()) {
            StringBuilder sb = new StringBuilder (matcher.group ());
            return String.valueOf (sb.deleteCharAt (0));
        }
        return "totalizator";

    }

    public static String getAction(String path){
        Matcher matcher = Pattern.compile(PATH_PATTERN).matcher(path);
        if(matcher.find (1)) {
            StringBuilder sb = new StringBuilder (matcher.group ());
            return String.valueOf (sb.deleteCharAt (0));
        }
        return "";
    }
}
