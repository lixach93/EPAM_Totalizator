package by.training.lihodievski.final_project.util;


import org.mindrot.jbcrypt.BCrypt;

public  final class BCryptUtil {

    private BCryptUtil(){}
    private static final int HASH_ROUNDS = 12;

    public static String hashString(String string) {
        return BCrypt.hashpw(string, BCrypt.gensalt(HASH_ROUNDS));
    }
    public static boolean isValidHash(String testString, String hash) {
        return BCrypt.checkpw(testString, hash);
    }
}