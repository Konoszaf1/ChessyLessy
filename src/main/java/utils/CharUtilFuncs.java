package utils;

public class CharUtilFuncs {

    public static boolean isNumeric(char character){
        if (character >= 48 && character <= 57) {
            return true;
        }
        return false;
    }
    public static boolean isCapitalAlpha(char character){
        if (character >= 65 && character <= 90) {
            return true;
        }
        return false;
    }
    public static boolean isNonCapitalAlpha(char character){
        if (character >= 97 && character <= 122) {
            return true;
        }
        return false;
    }
}
